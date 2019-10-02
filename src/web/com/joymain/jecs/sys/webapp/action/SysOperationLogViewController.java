package com.joymain.jecs.sys.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysOperationLog;
import com.joymain.jecs.sys.service.SysDataLogManager;
import com.joymain.jecs.sys.service.SysOperationLogManager;
import com.joymain.jecs.webapp.action.BaseController;

public class SysOperationLogViewController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysOperationLogViewController.class);
	private SysOperationLogManager sysOperationLogManager = null;
	private SysDataLogManager sysDataLogManager = null;

	public void setSysDataLogManager(SysDataLogManager sysDataLogManager) {
		this.sysDataLogManager = sysDataLogManager;
	}

	public void setSysOperationLogManager(SysOperationLogManager sysOperationLogManager) {
		this.sysOperationLogManager = sysOperationLogManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		
		request.setAttribute("sysDataLogManager", sysDataLogManager);

		String logId = request.getParameter("logId");
		SysOperationLog sysOperationLog = null;

		if (!StringUtils.isEmpty(logId)) {
			sysOperationLog = sysOperationLogManager.getSysOperationLog(logId);
		} else {
			sysOperationLog = new SysOperationLog();
		}
		request.setAttribute("sysOperationLog", sysOperationLog);

		return new ModelAndView("sys/sysOperationLogView");
	}
}