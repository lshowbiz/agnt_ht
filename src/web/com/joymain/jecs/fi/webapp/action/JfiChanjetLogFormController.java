package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.fi.service.JfiChanjetLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiChanjetLogFormController extends BaseFormController {
    private JfiChanjetLogManager jfiChanjetLogManager = null;

    public void setJfiChanjetLogManager(JfiChanjetLogManager jfiChanjetLogManager) {
        this.jfiChanjetLogManager = jfiChanjetLogManager;
    }
    public JfiChanjetLogFormController() {
        setCommandName("jfiChanjetLog");
        setCommandClass(JfiChanjetLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiChanjetLog jfiChanjetLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiChanjetLog = jfiChanjetLogManager.getJfiChanjetLog(logId);
        } else {
            jfiChanjetLog = new JfiChanjetLog();
        }

        return jfiChanjetLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiChanjetLog jfiChanjetLog = (JfiChanjetLog) command;
        boolean isNew = (jfiChanjetLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiChanjetLog".equals(strAction)  ) {
		jfiChanjetLogManager.removeJfiChanjetLog(jfiChanjetLog.getLogId().toString());
		key="jfiChanjetLog.delete";
	}else{
		jfiChanjetLogManager.saveJfiChanjetLog(jfiChanjetLog);
		key="jfiChanjetLog.update";
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
