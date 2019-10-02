package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.webapp.action.BaseController;

public class SysRolePowerController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysRolePowerController.class);
	private SysModuleManager sysModuleManager = null;

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		String roleId=request.getParameter("roleId");
		String userType=request.getParameter("userType");
		String companyCode=request.getParameter("companyCode");
		if(!StringUtils.isEmpty(userType) && !StringUtils.isEmpty(companyCode)){
			SysRole sysRole=new SysRole();
			if(!StringUtils.isEmpty(roleId)){
				sysRole.setRoleId(new Long(roleId));
			}
			sysRole.setUserType(userType);
			sysRole.setCompanyCode(companyCode);
			List sysModules=this.sysModuleManager.getSysModulesJoinRole(sysRole);
			
			request.setAttribute("sysModules", sysModules);
		}

		return new ModelAndView("sys/sysRolePowerList");
	}
}
