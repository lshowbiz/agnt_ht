package com.joymain.jecs.pm.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.pm.service.JpmProductSaleTeamTypeManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpmProductSaleTeamTypeController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JpmProductSaleTeamTypeController.class);
	private JpmProductSaleTeamTypeManager jpmProductSaleTeamTypeManager = null;
	private JmiMemberTeamManager jmiMemberTeamManager = null;

	public JmiMemberTeamManager getJmiMemberTeamManager() {
		return jmiMemberTeamManager;
	}

	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}

	public Log getLog() {
		return log;
	}

	public JpmProductSaleTeamTypeManager getJpmProductSaleTeamTypeManager() {
		return jpmProductSaleTeamTypeManager;
	}

	public void setJpmProductSaleTeamTypeManager(JpmProductSaleTeamTypeManager jpmProductSaleTeamTypeManager) {
		this.jpmProductSaleTeamTypeManager = jpmProductSaleTeamTypeManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		JpmProductSaleTeamType jpmProductSaleTeamType = new JpmProductSaleTeamType();
		// populate object with request parameters
		BeanUtils.populate(jpmProductSaleTeamType, request.getParameterMap());
		

		String uniNoStr = request.getParameter("uniNoStr");
		String status2 = request.getParameter("status2"); 
		if(StringUtils.isNotEmpty(uniNoStr) && StringUtils.isNotEmpty(uniNoStr)){
			int i = jpmProductSaleTeamTypeManager.batchAuditProductTeamTypes(uniNoStr, status2); 
		}
		
		//List jpmProductSaleTeamTypes = jpmProductSaleTeamTypeManager.getJpmProductSaleTeamTypes(jpmProductSaleTeamType);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("jpmProductSaleTeamTypeListTable",request, 20);
		//如果不是全球，则设置区域
		String companyCode = request.getParameter("companyCode");
		SysUser loginUser = SessionLogin.getLoginUser(request);
		if(StringUtils.isNotEmpty(companyCode)){
			crm.setValue("companyCode", companyCode);
		}else{
			if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
				crm.setValue("companyCode",loginUser.getCompanyCode());
	
			} 
		} 
		 
		String productNo = request.getParameter("productNo");
    	String productName = request.getParameter("productName");
    	String state = request.getParameter("state");
    	String teamCode = request.getParameter("teamCode");
    	String orderType = request.getParameter("orderType");
    	String isHidden = request.getParameter("isHidden");
    	String cxFlag = request.getParameter("cxFlag");
    	
    	crm.setValue("productNo", productNo);
    	crm.setValue("productName", productName);
    	crm.setValue("state", state); 
    	crm.setValue("teamCode", teamCode);
    	crm.setValue("orderType", orderType);
    	crm.setValue("isHidden", isHidden);
    	crm.setValue("cxFlag", cxFlag);
    	crm.setValue("status", "0");
    	
    	//获得团队数据
    	List jmiMemberTeams = jmiMemberTeamManager.getJmiMemberTeamsByCrm(crm); 
        request.setAttribute("jmiMemberTeams", jmiMemberTeams);  
    	
		
		List jpmProductSaleTeamTypes = jpmProductSaleTeamTypeManager.getJpmProductSaleTeamTypesByCrm(crm,pager);
		request.setAttribute("jpmProductSaleTeamTypeListTable_totalRows", pager.getTotalObjects());
		/*****/
		 
		request.setAttribute("companyCode", companyCode);
		return new ModelAndView("pm/jpmProductSaleTeamTypeList", Constants.JPMPRODUCTSALETEAMTYPE_LIST, jpmProductSaleTeamTypes);
	}
}
