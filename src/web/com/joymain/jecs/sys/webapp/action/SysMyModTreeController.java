package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysMyModTreeController implements Controller {
	private final Log log = LogFactory.getLog(SysMyModTreeController.class);
	private SysModuleManager sysModuleManager = null;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String slaveUserCode = request.getParameter("slaveUserCode");
		if (!StringUtils.isEmpty(slaveUserCode)) {
			List sysModules = this.sysModuleManager.getMyManSysModules(SessionLogin.getLoginUser(request).getUserCode(), slaveUserCode, request.getParameter("companyCode"));

			request.setAttribute("sysModules", sysModules);

			//读取所管理的人员所处公司
			List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), null,false);
			request.setAttribute("alCompanys", alCompanys);

			if (!StringUtils.isEmpty(request.getParameter("companyCode"))) {
				request.setAttribute("companyCode", request.getParameter("companyCode"));
			} else {
				request.setAttribute("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
			}
		}

		return new ModelAndView("sys/sys_my_mod_tree");
	}
}
