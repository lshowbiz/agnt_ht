package com.joymain.jecs.fi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.fi.model.JfiPayBank;
import com.joymain.jecs.fi.service.JfiPayAdviceManager;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 银行提款报表
 * @author Alvin
 *
 */
public class JfiBankOutReportController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiBankOutReportController.class);
	private JfiPayAdviceManager jfiPayAdviceManager = null;
	private JfiPayBankManager jfiPayBankManager = null;
	public void setJfiPayAdviceManager(JfiPayAdviceManager jfiPayAdviceManager) {
		this.jfiPayAdviceManager = jfiPayAdviceManager;
	}

	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	private AlCompanyManager alCompanyManager = null;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "fiBankOutReport".equals(request.getParameter("strAction"))) {
			CommonRecord crm = RequestUtil.toCommonRecord(request);
			crm.addField("status", "1,4");
			
			if(StringUtils.isEmpty(request.getParameter("companyCode"))){
				crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
			}

			List jfiPayAdviceGroups = this.jfiPayAdviceManager.getJfiPayAdvicesStatGroup(crm);
			request.setAttribute("jfiPayAdviceGroups", jfiPayAdviceGroups);
			
			request.setAttribute("jfiPayAdviceManager", jfiPayAdviceManager);
			
			JfiPayBank fiPayBank=this.jfiPayBankManager.getJfiPayBank(request.getParameter("accountCode"));
			request.setAttribute("fiPayBank", fiPayBank);
			
			AlCompany alCompany=this.alCompanyManager.getAlCompanyByCode(crm.getString("companyCode", "AA"));
			request.setAttribute("alCompany", alCompany);

			request.setAttribute("isReporting", "1");
			request.setAttribute("currentTime", DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		}

		List jfiPayBanks=null;
		if("AA".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		}else{
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanksByCompany(SessionLogin.getLoginUser(request).getCompanyCode());
		}
		request.setAttribute("jfiPayBanks", jfiPayBanks);

		return new ModelAndView("fi/jfi_bank_out_report");
	}
}