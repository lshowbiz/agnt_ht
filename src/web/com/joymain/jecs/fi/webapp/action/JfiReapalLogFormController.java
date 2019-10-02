package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.fi.service.JfiReapalLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiReapalLogFormController extends BaseFormController {
    private JfiReapalLogManager jfiReapalLogManager = null;

    public void setJfiReapalLogManager(JfiReapalLogManager jfiReapalLogManager) {
        this.jfiReapalLogManager = jfiReapalLogManager;
    }
    public JfiReapalLogFormController() {
        setCommandName("jfiReapalLog");
        setCommandClass(JfiReapalLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiReapalLog jfiReapalLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiReapalLog = jfiReapalLogManager.getJfiReapalLog(logId);
        } else {
            jfiReapalLog = new JfiReapalLog();
        }

        return jfiReapalLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiReapalLog jfiReapalLog = (JfiReapalLog) command;
        boolean isNew = (jfiReapalLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiReapalLog".equals(strAction)  ) {
		jfiReapalLogManager.removeJfiReapalLog(jfiReapalLog.getLogId().toString());
		key="jfiReapalLog.delete";
	}else{
		jfiReapalLogManager.saveJfiReapalLog(jfiReapalLog);
		key="jfiReapalLog.update";
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
