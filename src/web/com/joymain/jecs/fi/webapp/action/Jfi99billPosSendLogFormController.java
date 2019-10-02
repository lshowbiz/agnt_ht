package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.Jfi99billPosSendLog;
import com.joymain.jecs.fi.service.Jfi99billPosSendLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class Jfi99billPosSendLogFormController extends BaseFormController {
    private Jfi99billPosSendLogManager jfi99billPosSendLogManager = null;

    public void setJfi99billPosSendLogManager(Jfi99billPosSendLogManager jfi99billPosSendLogManager) {
        this.jfi99billPosSendLogManager = jfi99billPosSendLogManager;
    }
    public Jfi99billPosSendLogFormController() {
        setCommandName("jfi99billPosSendLog");
        setCommandClass(Jfi99billPosSendLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        Jfi99billPosSendLog jfi99billPosSendLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfi99billPosSendLog = jfi99billPosSendLogManager.getJfi99billPosSendLog(logId);
        } else {
            jfi99billPosSendLog = new Jfi99billPosSendLog();
        }

        return jfi99billPosSendLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        Jfi99billPosSendLog jfi99billPosSendLog = (Jfi99billPosSendLog) command;
        boolean isNew = (jfi99billPosSendLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfi99billPosSendLog".equals(strAction)  ) {
		jfi99billPosSendLogManager.removeJfi99billPosSendLog(jfi99billPosSendLog.getLogId().toString());
		key="jfi99billPosSendLog.delete";
	}else{
		jfi99billPosSendLogManager.saveJfi99billPosSendLog(jfi99billPosSendLog);
		key="jfi99billPosSendLog.update";
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
