package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiCreditCardLog;
import com.joymain.jecs.fi.service.JfiCreditCardLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiCreditCardLogFormController extends BaseFormController {
    private JfiCreditCardLogManager jfiCreditCardLogManager = null;

    public void setJfiCreditCardLogManager(JfiCreditCardLogManager jfiCreditCardLogManager) {
        this.jfiCreditCardLogManager = jfiCreditCardLogManager;
    }
    public JfiCreditCardLogFormController() {
        setCommandName("jfiCreditCardLog");
        setCommandClass(JfiCreditCardLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiCreditCardLog jfiCreditCardLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiCreditCardLog = jfiCreditCardLogManager.getJfiCreditCardLog(logId);
        } else {
            jfiCreditCardLog = new JfiCreditCardLog();
        }

        return jfiCreditCardLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiCreditCardLog jfiCreditCardLog = (JfiCreditCardLog) command;
        boolean isNew = (jfiCreditCardLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiCreditCardLog".equals(strAction)  ) {
		jfiCreditCardLogManager.removeJfiCreditCardLog(jfiCreditCardLog.getLogId().toString());
		key="jfiCreditCardLog.delete";
	}else{
		jfiCreditCardLogManager.saveJfiCreditCardLog(jfiCreditCardLog);
		key="jfiCreditCardLog.update";
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
