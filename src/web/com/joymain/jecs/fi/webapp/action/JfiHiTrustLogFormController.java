package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.fi.service.JfiHiTrustLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiHiTrustLogFormController extends BaseFormController {
    private JfiHiTrustLogManager jfiHiTrustLogManager = null;

    public void setJfiHiTrustLogManager(JfiHiTrustLogManager jfiHiTrustLogManager) {
        this.jfiHiTrustLogManager = jfiHiTrustLogManager;
    }
    public JfiHiTrustLogFormController() {
        setCommandName("jfiHiTrustLog");
        setCommandClass(JfiHiTrustLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiHiTrustLog jfiHiTrustLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiHiTrustLog = jfiHiTrustLogManager.getJfiHiTrustLog(logId);
        } else {
            jfiHiTrustLog = new JfiHiTrustLog();
        }

        return jfiHiTrustLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiHiTrustLog jfiHiTrustLog = (JfiHiTrustLog) command;
        boolean isNew = (jfiHiTrustLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiHiTrustLog".equals(strAction)  ) {
		jfiHiTrustLogManager.removeJfiHiTrustLog(jfiHiTrustLog.getLogId().toString());
		key="jfiHiTrustLog.delete";
	}else{
		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
		key="jfiHiTrustLog.update";
	}

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
