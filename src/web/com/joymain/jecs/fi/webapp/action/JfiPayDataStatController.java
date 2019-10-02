package com.joymain.jecs.fi.webapp.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 到款条目统计报表
 * @author Alvin
 *
 */
public class JfiPayDataStatController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiPayDataStatController.class);
	private JfiPayDataManager jfiPayDataManager = null;
	private JfiPayBankManager jfiPayBankManager = null;

	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}

	public void setJfiPayDataManager(JfiPayDataManager jfiPayDataManager) {
		this.jfiPayDataManager = jfiPayDataManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		List jfiPayBanks=null;
		if("AA".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		}else{
			jfiPayBanks=this.jfiPayBankManager.getJfiPayBanksByCompany(SessionLogin.getLoginUser(request).getCompanyCode());
		}
		request.setAttribute("jfiPayBanks", jfiPayBanks);

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		if (StringUtils.isEmpty(request.getParameter("companyCode"))) {
			crm.addField(new CustomField("companyCode", 1111, SessionLogin.getLoginUser(request).getCompanyCode()));
		}

		Pager pager = new Pager("jfiPayDataListTable", request, 20);

		List jfiPayDatas = null;
		if (request.getParameter("search") != null) {
			jfiPayDatas = this.jfiPayDataManager.getJfiPayDataStatsByCrm(crm, pager);
		}
		//根据数据重新设置分页数据
		request.setAttribute("jfiPayDataListTable_totalRows", pager.getTotalObjects());

		if (request.getParameter("search") != null) {
			HashMap totalMap = this.jfiPayDataManager.getJfiPayDataSum(crm);
			request.setAttribute("totalMap", totalMap);
		}

		return new ModelAndView("fi/jfi_pay_data_stat", Constants.JFIPAYDATA_LIST, jfiPayDatas);
	}
}