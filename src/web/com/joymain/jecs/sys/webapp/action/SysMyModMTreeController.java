package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysMyModMTreeController implements Controller {
	private final Log log = LogFactory.getLog(SysMyModMTreeController.class);
	private SysModuleManager sysModuleManager = null;

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String companyCode = request.getParameter("companyCode");
		if (StringUtils.isEmpty(companyCode)) {
			companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
		}
		List sysModules = this.sysModuleManager.getMyManSysModules(SessionLogin.getLoginUser(request).getUserCode(), null, companyCode);

		request.setAttribute("sysModules", sysModules);

		request.setAttribute("companyCode", companyCode);

		return new ModelAndView("sys/sys_my_mod_m_tree");
	}
}
