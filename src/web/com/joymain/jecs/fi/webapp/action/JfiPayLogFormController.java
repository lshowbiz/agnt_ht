package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.service.JfiPayLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiPayLogFormController extends BaseFormController {
    private JfiPayLogManager jfiPayLogManager = null;

    public void setJfiPayLogManager(JfiPayLogManager jfiPayLogManager) {
        this.jfiPayLogManager = jfiPayLogManager;
    }
    public JfiPayLogFormController() {
        setCommandName("jfiPayLog");
        setCommandClass(JfiPayLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiPayLog jfiPayLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiPayLog = jfiPayLogManager.getJfiPayLog(logId);
        } else {
            jfiPayLog = new JfiPayLog();
        }

        return jfiPayLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiPayLog jfiPayLog = (JfiPayLog) command;
        boolean isNew = (jfiPayLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiPayLog".equals(strAction)  ) {
		jfiPayLogManager.removeJfiPayLog(jfiPayLog.getLogId().toString());
		key="jfiPayLog.delete";
	}else{
		jfiPayLogManager.saveJfiPayLog(jfiPayLog);
		key="jfiPayLog.update";
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
