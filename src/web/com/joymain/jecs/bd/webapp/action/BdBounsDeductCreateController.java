package com.joymain.jecs.bd.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class BdBounsDeductCreateController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(BdBounsDeductCreateController.class);
	private BdBounsDeductManager bdBounsDeductManager = null;

	public void setBdBounsDeductManager(BdBounsDeductManager bdBounsDeductManager) {
		this.bdBounsDeductManager = bdBounsDeductManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String companyCode = "";
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		if ("C".equals(defSysUser.getUserType())) {
			companyCode = defSysUser.getCompanyCode();
			if ("AA".equals(defSysUser.getCompanyCode())) {
				companyCode = null;
			}
		}

		//删除
		if ("get".equalsIgnoreCase(request.getMethod()) && "deleteDeduct".equals(request.getParameter("strActionOperation"))) {
			String[] strAdvicesCodes = request.getParameter("strAdvicesCodes").split(",");
			List<BdBounsDeduct> bdBounsDeductDeleteList = new ArrayList<BdBounsDeduct>();
			for (int i = 0; i < strAdvicesCodes.length; i++) {
				if (!StringUtils.isEmpty(strAdvicesCodes[i])) {
					BdBounsDeduct bdBounsDeduct = bdBounsDeductManager.getBdBounsDeduct(strAdvicesCodes[i]);
					bdBounsDeductDeleteList.add(bdBounsDeduct);
				}
			}
			try {
				bdBounsDeductManager.removeBdBounsDeducts(bdBounsDeductDeleteList);
				saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteFail"));
			}
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", companyCode);
		if (!"AA".equals(defSysUser.getCompanyCode())) {
			crm.addField("createCode", defSysUser.getUserCode());
		}

		Pager pager = new Pager("bdBounsDeductCreateListTable", request, 20);

		String startCreateTime = request.getParameter("startCreateTime");
		String endCreateTime = request.getParameter("endCreateTime");
		String userCode = request.getParameter("userCode");
		String name = request.getParameter("name");
		String startMoney = request.getParameter("startMoney");
		String endMoney = request.getParameter("endMoney");

		if (StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(name)&&!StringUtil.isDate(startCreateTime)&&!StringUtil.isDate(endCreateTime)) {
			request.setAttribute("bdBounsDeductCreateListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("bd/bdBounsDeductCreateList");
		} else {

			List bdBounsDeductsCreate = bdBounsDeductManager.getBdBounsDeductsByCrm(crm, pager);
			request.setAttribute("bdBounsDeductCreateListTable_totalRows", pager.getTotalObjects());

			return new ModelAndView("bd/bdBounsDeductCreateList", "bdBounsDeductCreateList", bdBounsDeductsCreate);
		}
	}
}
