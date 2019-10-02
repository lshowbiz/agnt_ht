package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.webapp.action.BaseController;

public class SysModuleTreeController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysModuleTreeController.class);
	private SysModuleManager sysModuleManager = null;

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		List sysModules = sysModuleManager.getSysModules(null);
		request.setAttribute("sysModules", sysModules);

		return new ModelAndView("sys/sys_module_tree");
	}
}
