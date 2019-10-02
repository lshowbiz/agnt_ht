package com.joymain.jecs.sys.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.sys.service.SysDataLogManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysDataLogFormController extends BaseFormController {
	private SysDataLogManager sysDataLogManager = null;

	public void setSysDataLogManager(SysDataLogManager sysDataLogManager) {
		this.sysDataLogManager = sysDataLogManager;
	}

	public SysDataLogFormController() {
		setCommandName("sysDataLog");
		setCommandClass(SysDataLog.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String logId = request.getParameter("logId");
		SysDataLog sysDataLog = null;

		if (!StringUtils.isEmpty(logId)) {
			sysDataLog = sysDataLogManager.getSysDataLog(logId);
		} else {
			sysDataLog = new SysDataLog();
		}

		return sysDataLog;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysDataLog sysDataLog = (SysDataLog) command;
		boolean isNew = (sysDataLog.getLogId() == null);

		if (request.getParameter("delete") != null) {
			sysDataLogManager.removeSysDataLog(sysDataLog.getLogId().toString());

			// saveMessage(request, getText("sysDataLog.deleted", locale));
			saveMessage(request, getText("sysDataLog.deleted"));
		} else {
			sysDataLogManager.saveSysDataLog(sysDataLog);

			String key = (isNew) ? "sysDataLog.added" : "sysDataLog.updated";
			// saveMessage(request, getText(key, locale));
			saveMessage(request, getText(key));
			if (!isNew) {
				return new ModelAndView("redirect:editSysDataLog.html", "logId", sysDataLog.getLogId());
			}
		}

		return new ModelAndView(getSuccessView());
	}
}
