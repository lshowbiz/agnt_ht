package com.joymain.jecs.fi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.fi.service.JfiChinapnrLogManager;
import com.joymain.jecs.webapp.action.BaseFormController;
@SuppressWarnings("unused")
public class JfiChinapnrLogFormController extends BaseFormController {
	private JfiChinapnrLogManager jfiChinapnrLogManager = null;

	public void setJfiChinapnrLogManager(JfiChinapnrLogManager jfiChinapnrLogManager) {
		this.jfiChinapnrLogManager = jfiChinapnrLogManager;
	}

	public JfiChinapnrLogFormController() {
		setCommandName("jfiChinapnrLog");
		setCommandClass(JfiChinapnrLog.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String logId = request.getParameter("logId");
		JfiChinapnrLog jfiChinapnrLog = null;

		if (!StringUtils.isEmpty(logId)) {
			jfiChinapnrLog = jfiChinapnrLogManager.getJfiChinapnrLog(logId);
		} else {
			jfiChinapnrLog = new JfiChinapnrLog();
		}

		return jfiChinapnrLog;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JfiChinapnrLog jfiChinapnrLog = (JfiChinapnrLog) command;
	//	boolean isNew = (jfiChinapnrLog.getLogId() == null);
	//	Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		if ("deleteJfiChinapnrLog".equals(strAction)) {
			jfiChinapnrLogManager.removeJfiChinapnrLog(jfiChinapnrLog.getLogId().toString());
			key = "jfiChinapnrLog.delete";
		} else {
			jfiChinapnrLogManager.saveJfiChinapnrLog(jfiChinapnrLog);
			key = "jfiChinapnrLog.update";
		}

		return new ModelAndView(getSuccessView());
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
