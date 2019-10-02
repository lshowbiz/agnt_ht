package com.joymain.jecs.sys.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysClickLog;
import com.joymain.jecs.sys.service.SysClickLogManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysClickLogFormController extends BaseFormController {
	private SysClickLogManager sysClickLogManager = null;

	public void setSysClickLogManager(SysClickLogManager sysClickLogManager) {
		this.sysClickLogManager = sysClickLogManager;
	}

	public SysClickLogFormController() {
		setCommandName("sysClickLog");
		setCommandClass(SysClickLog.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String logId = request.getParameter("logId");
		SysClickLog sysClickLog = null;

		if (!StringUtils.isEmpty(logId)) {
			sysClickLog = sysClickLogManager.getSysClickLog(logId);
		} else {
			sysClickLog = new SysClickLog();
		}

		return sysClickLog;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysClickLog sysClickLog = (SysClickLog) command;
		boolean isNew = (sysClickLog.getLogId() == null);

		if (request.getParameter("delete") != null) {
			sysClickLogManager.removeSysClickLog(sysClickLog.getLogId().toString());

			// saveMessage(request, getText("sysClickLog.deleted", locale));
			saveMessage(request, getText("sysClickLog.deleted"));
		} else {
			sysClickLogManager.saveSysClickLog(sysClickLog);

			String key = (isNew) ? "sysClickLog.added" : "sysClickLog.updated";
			// saveMessage(request, getText(key, locale));
			saveMessage(request, getText(key));
			if (!isNew) {
				return new ModelAndView("redirect:editSysClickLog.html", "logId", sysClickLog.getLogId());
			}
		}

		return new ModelAndView(getSuccessView());
	}
}
