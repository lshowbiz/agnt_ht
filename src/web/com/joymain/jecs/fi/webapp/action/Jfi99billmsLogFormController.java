package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.fi.service.Jfi99billmsLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class Jfi99billmsLogFormController extends BaseFormController {
    private Jfi99billmsLogManager jfi99billmsLogManager = null;

    public void setJfi99billmsLogManager(Jfi99billmsLogManager jfi99billmsLogManager) {
        this.jfi99billmsLogManager = jfi99billmsLogManager;
    }
    public Jfi99billmsLogFormController() {
        setCommandName("jfi99billmsLog");
        setCommandClass(Jfi99billmsLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        Jfi99billmsLog jfi99billmsLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfi99billmsLog = jfi99billmsLogManager.getJfi99billmsLog(logId);
        } else {
            jfi99billmsLog = new Jfi99billmsLog();
        }

        return jfi99billmsLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        Jfi99billmsLog jfi99billmsLog = (Jfi99billmsLog) command;
        boolean isNew = (jfi99billmsLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfi99billmsLog".equals(strAction)  ) {
		jfi99billmsLogManager.removeJfi99billmsLog(jfi99billmsLog.getLogId().toString());
		key="jfi99billmsLog.delete";
	}else{
		jfi99billmsLogManager.saveJfi99billmsLog(jfi99billmsLog);
		key="jfi99billmsLog.update";
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
