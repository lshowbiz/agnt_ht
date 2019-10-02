package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.fi.service.JfiYeepayLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiYeepayLogFormController extends BaseFormController {
    private JfiYeepayLogManager jfiYeepayLogManager = null;

    public void setJfiYeepayLogManager(JfiYeepayLogManager jfiYeepayLogManager) {
        this.jfiYeepayLogManager = jfiYeepayLogManager;
    }
    public JfiYeepayLogFormController() {
        setCommandName("jfiYeepayLog");
        setCommandClass(JfiYeepayLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiYeepayLog jfiYeepayLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiYeepayLog = jfiYeepayLogManager.getJfiYeepayLog(logId);
        } else {
            jfiYeepayLog = new JfiYeepayLog();
        }

        return jfiYeepayLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiYeepayLog jfiYeepayLog = (JfiYeepayLog) command;
        boolean isNew = (jfiYeepayLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiYeepayLog".equals(strAction)  ) {
		jfiYeepayLogManager.removeJfiYeepayLog(jfiYeepayLog.getLogId().toString());
		key="jfiYeepayLog.delete";
	}else{
		jfiYeepayLogManager.saveJfiYeepayLog(jfiYeepayLog);
		key="jfiYeepayLog.update";
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
