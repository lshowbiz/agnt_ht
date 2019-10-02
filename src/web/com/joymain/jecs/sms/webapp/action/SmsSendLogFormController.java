package com.joymain.jecs.sms.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.sms.model.SmsSendLog;
import com.joymain.jecs.sms.service.SmsSendLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class SmsSendLogFormController extends BaseFormController {
    private SmsSendLogManager smsSendLogManager = null;

    public void setSmsSendLogManager(SmsSendLogManager smsSendLogManager) {
        this.smsSendLogManager = smsSendLogManager;
    }
    public SmsSendLogFormController() {
        setCommandName("smsSendLog");
        setCommandClass(SmsSendLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sslId = request.getParameter("sslId");
        SmsSendLog smsSendLog = null;

        if (!StringUtils.isEmpty(sslId)) {
            smsSendLog = smsSendLogManager.getSmsSendLog(sslId);
        } else {
            smsSendLog = new SmsSendLog();
        }

        return smsSendLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SmsSendLog smsSendLog = (SmsSendLog) command;
        boolean isNew = (smsSendLog.getSslId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteSmsSendLog".equals(strAction)  ) {
		smsSendLogManager.removeSmsSendLog(smsSendLog.getSslId().toString());
		key="smsSendLog.delete";
	}else{
		smsSendLogManager.saveSmsSendLog(smsSendLog);
		key="smsSendLog.update";
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
