package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.service.Jfi99billLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class Jfi99billLogFormController extends BaseFormController {
    private Jfi99billLogManager jfi99billLogManager = null;

    public void setJfi99billLogManager(Jfi99billLogManager jfi99billLogManager) {
        this.jfi99billLogManager = jfi99billLogManager;
    }
    public Jfi99billLogFormController() {
        setCommandName("jfi99billLog");
        setCommandClass(Jfi99billLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        Jfi99billLog jfi99billLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfi99billLog = jfi99billLogManager.getJfi99billLog(logId);
        } else {
            jfi99billLog = new Jfi99billLog();
        }

        return jfi99billLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        Jfi99billLog jfi99billLog = (Jfi99billLog) command;
        boolean isNew = (jfi99billLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfi99billLog".equals(strAction)  ) {
		jfi99billLogManager.removeJfi99billLog(jfi99billLog.getLogId().toString());
		key="jfi99billLog.delete";
	}else{
		jfi99billLogManager.saveJfi99billLog(jfi99billLog);
		key="jfi99billLog.update";
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
