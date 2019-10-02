package com.joymain.jecs.fi.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.service.JfiPayAdviceManager;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 银行到款条目(转核实)
 * @author Alvin
 *
 */
public class JfiPayDataVerifyController extends BaseFormController {
	private JfiPayDataManager jfiPayDataManager = null;
	private JfiPayAdviceManager jfiPayAdviceManager = null;
	private JfiPayBankManager jfiPayBankManager = null;

	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}

	public void setJfiPayAdviceManager(JfiPayAdviceManager jfiPayAdviceManager) {
		this.jfiPayAdviceManager = jfiPayAdviceManager;
	}

	public void setJfiPayDataManager(JfiPayDataManager jfiPayDataManager) {
		this.jfiPayDataManager = jfiPayDataManager;
	}

	public JfiPayDataVerifyController() {
		setCommandName("jfiPayData");
		setCommandClass(JfiPayData.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String dataId = request.getParameter("dataId");
		JfiPayData jfiPayData = null;

		if (!StringUtils.isEmpty(dataId)) {
			jfiPayData = jfiPayDataManager.getJfiPayData(dataId);
			if (jfiPayData.getStatus() == 2) {
				//如果已确认,则不能修改
				throw new AppException(LocaleUtil.getLocalText("error.jfiPayData.has.verified"));
			}
		} else {
			jfiPayData = new JfiPayData();
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		//crm.addField("status", "1"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (request.getParameter("searchBtn") == null) {
			//默认设置全部选择
			crm.addField("accountCode", jfiPayData.getAccountCode());
			crm.addField("payDate", dateFormat.format(jfiPayData.getDealDate()));
			crm.addField("payMoney", jfiPayData.getIncomeMoney().toString());
			if(jfiPayData.getSysUser()!=null){
				crm.addField("userCode", jfiPayData.getSysUser().getUserCode());
			}

			request.setAttribute("selectedAccountCode", jfiPayData.getAccountCode());
			request.setAttribute("selectedDealDate", dateFormat.format(jfiPayData.getDealDate()));
			request.setAttribute("selectedIncomeMoney", jfiPayData.getIncomeMoney());
			if(jfiPayData.getSysUser()!=null){
				request.setAttribute("selectedUserCode", jfiPayData.getSysUser().getUserCode());
			}
		} else {
			request.setAttribute("selectedAccountCode", crm.getString("accountCode", ""));
			request.setAttribute("selectedDealDate", crm.getString("payDate", ""));
			request.setAttribute("selectedIncomeMoney", crm.getString("payMoney", ""));
			request.setAttribute("selectedUserCode", crm.getString("userCode", ""));
		}
		crm.addField("isForVerify", "1");

		Pager pager = new Pager("jfiPayAdviceListTable", request, 20);
		List jfiPayAdvices = jfiPayAdviceManager.getJfiPayAdvicesByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("jfiPayAdviceListTable_totalRows", pager.getTotalObjects());
		request.setAttribute(Constants.JFIPAYADVICE_LIST, jfiPayAdvices);

		//获取银行列表
		List jfiPayBanks = new ArrayList();
		if (SessionLogin.getLoginUser(request).getIsManager()) {
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		} else if (SessionLogin.getLoginUser(request).getIsCompany()) {
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanksByCompany(SessionLogin.getLoginUser(request).getCompanyCode());
		}
		request.setAttribute("jfiPayBanks", jfiPayBanks);

		return jfiPayData;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JfiPayData jfiPayData = this.jfiPayDataManager.getJfiPayData(request.getParameter("dataId"));

		if (jfiPayData.getStatus() == 2) {
			//已核实
			saveMessage(request, LocaleUtil.getLocalText("error.jfiPayData.has.verified"));
		} else {
			if ("verifyFiPayData".equals(request.getParameter("strAction"))) {
				String strAdviceCode = request.getParameter("strAdviceCode");
				if (!StringUtils.isEmpty(strAdviceCode)) {
					JfiPayAdvice jfiPayAdvice = this.jfiPayAdviceManager.getJfiPayAdvice(strAdviceCode);
					if (jfiPayAdvice.getStatus() == 2) {
						saveMessage(request, LocaleUtil.getLocalText("error.jfiPayAdvice.verified"));
					} else {
						if (request.getParameter("save") != null) {
							String notice = LocaleUtil.getLocalText("fiPayData.verify.notice");
							if ("fiPayData.verify.notice".equals(notice)) {
								saveMessage(request, LocaleUtil.getLocalText("error.get.language"));
							} else {
								this.jfiPayDataManager.saveJfiPayDataVerify(jfiPayData.getDataId().toString(), jfiPayAdvice.getSysUser().getUserCode(), strAdviceCode, SessionLogin.getOperatorUser(request).getUserCode(), SessionLogin.getOperatorUser(request).getUserName(), LocaleUtil.getLocalText("fiPayData.verify.notice"));
								saveMessage(request, LocaleUtil.getLocalText("fiPayData.verified.success"));
							}
						} else if (request.getParameter("notDeal") != null) {
							jfiPayAdvice.setStatus(3);
							this.jfiPayAdviceManager.saveJfiPayAdvice(jfiPayAdvice);
							saveMessage(request, LocaleUtil.getLocalText("fiPayAdvice.doNotDealed"));
						}
					}
				}
			}
		}

		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listFiPayDatas");
		mv.addObject("needReload", "1");
		return mv;
	}
}
