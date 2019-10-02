package com.joymain.jecs.sys.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysRoleController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysRoleController.class);
	private SysRoleManager sysRoleManager = null;
	private AlCompanyManager alCompanyManager = null;
	
	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		if("AA".equals(SessionLogin.getLoginUser(request).getCompanyCode())){
			List alCompanys=this.alCompanyManager.getAlCompanysExceptAA();
			request.setAttribute("alCompanys", alCompanys);
		}
		
		Map userTypes=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "user_type");
		request.setAttribute("userTypes", userTypes);
		
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		
		if(StringUtils.isEmpty(request.getParameter("companyCode"))){
			crm.addField(new CustomField("companyCode", 1111, SessionLogin.getLoginUser(request).getCompanyCode()));
		}		
		
		Pager pager = new Pager("sysRoleListTable", request, 20);
		List sysRoles = sysRoleManager.getSysRolesByCrm(crm, pager);
		request.setAttribute("sysRoleListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("sys/sysRoleList", Constants.SYSROLE_LIST, sysRoles);
	}
}
