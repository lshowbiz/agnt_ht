package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.service.JfiPayAdviceManager;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 付款通知统计
 * @author Alvin
 *
 */
public class JfiPayAdviceStatController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiPayAdviceStatController.class);
	private JfiPayAdviceManager jfiPayAdviceManager = null;
	private JfiPayBankManager jfiPayBankManager = null;
	private SysUserManager sysUserManager = null;
	
	public void setJfiPayAdviceManager(JfiPayAdviceManager jfiPayAdviceManager) {
		this.jfiPayAdviceManager = jfiPayAdviceManager;
	}

	public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);

		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		crm.addField("status", "2,4");
		
		List jfiPayBanks=null;
		if("AA".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
			jfiPayBanks = this.jfiPayBankManager.getJfiPayBanks(null);
		}else{
			jfiPayBanks=this.jfiPayBankManager.getJfiPayBanksByCompany(SessionLogin.getLoginUser(request).getCompanyCode());
		}
		request.setAttribute("jfiPayBanks", jfiPayBanks);

		Pager pager = new Pager("jfiPayAdviceListTable", request, 20);
		List jfiPayAdvices = jfiPayAdviceManager.getJfiPayAdvicesByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("jfiPayAdviceListTable_totalRows", pager.getTotalObjects());
		
		Map statMap=this.jfiPayAdviceManager.getJfiPayAdviceStatMap(crm);
		request.setAttribute("statMap", statMap);

		request.setAttribute(Constants.JFIPAYADVICE_LIST, jfiPayAdvices);
		ModelAndView mv=new ModelAndView("fi/jfi_pay_advice_stat");
		return mv;
	}
}