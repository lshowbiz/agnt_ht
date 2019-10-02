package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.service.JfiTenpayLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiTenpayLogFormController extends BaseFormController {
    private JfiTenpayLogManager jfiTenpayLogManager = null;

    public void setJfiTenpayLogManager(JfiTenpayLogManager jfiTenpayLogManager) {
        this.jfiTenpayLogManager = jfiTenpayLogManager;
    }
    public JfiTenpayLogFormController() {
        setCommandName("jfiTenpayLog");
        setCommandClass(JfiTenpayLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiTenpayLog jfiTenpayLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiTenpayLog = jfiTenpayLogManager.getJfiTenpayLog(logId);
        } else {
            jfiTenpayLog = new JfiTenpayLog();
        }

        return jfiTenpayLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiTenpayLog jfiTenpayLog = (JfiTenpayLog) command;
        boolean isNew = (jfiTenpayLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiTenpayLog".equals(strAction)  ) {
		jfiTenpayLogManager.removeJfiTenpayLog(jfiTenpayLog.getLogId().toString());
		key="jfiTenpayLog.delete";
	}else{
		jfiTenpayLogManager.saveJfiTenpayLog(jfiTenpayLog);
		key="jfiTenpayLog.update";
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
