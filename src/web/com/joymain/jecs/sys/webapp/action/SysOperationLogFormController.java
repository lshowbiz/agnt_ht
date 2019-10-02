package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysOperationLog;
import com.joymain.jecs.sys.service.SysOperationLogManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysOperationLogFormController extends BaseFormController {
	private SysOperationLogManager sysOperationLogManager = null;

	public void setSysOperationLogManager(SysOperationLogManager sysOperationLogManager) {
		this.sysOperationLogManager = sysOperationLogManager;
	}

	public SysOperationLogFormController() {
		setCommandName("sysOperationLog");
		setCommandClass(SysOperationLog.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String logId = request.getParameter("logId");
		SysOperationLog sysOperationLog = null;

		if (!StringUtils.isEmpty(logId)) {
			sysOperationLog = sysOperationLogManager.getSysOperationLog(logId);
		} else {
			sysOperationLog = new SysOperationLog();
		}

		return sysOperationLog;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysOperationLog sysOperationLog = (SysOperationLog) command;
		boolean isNew = (sysOperationLog.getLogId() == null);
		Locale locale = request.getLocale();

		if ("deleteSysOperationLog".equals(request.getParameter("strAction"))) {
			sysOperationLogManager.removeSysOperationLog(sysOperationLog.getLogId().toString());

			saveMessage(request, getText("sysOperationLog.deleted"));
		} else {
			String key = "sysOperationLog.updated";
			if ("addSysOperationLog".equals(request.getParameter("strAction"))) {
				key = "sysOperationLog.added";
			}
			sysOperationLogManager.saveSysOperationLog(sysOperationLog);

			saveMessage(request, getText(key));
		}

		return new ModelAndView(getSuccessView());
	}
}
