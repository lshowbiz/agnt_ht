package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.mi.service.JmiMemberLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiMemberLogFormController extends BaseFormController {
    private JmiMemberLogManager jmiMemberLogManager = null;

    public void setJmiMemberLogManager(JmiMemberLogManager jmiMemberLogManager) {
        this.jmiMemberLogManager = jmiMemberLogManager;
    }
    public JmiMemberLogFormController() {
        setCommandName("jmiMemberLog");
        setCommandClass(JmiMemberLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JmiMemberLog jmiMemberLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jmiMemberLog = jmiMemberLogManager.getJmiMemberLog(logId);
        } else {
            jmiMemberLog = new JmiMemberLog();
        }

        return jmiMemberLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiMemberLog jmiMemberLog = (JmiMemberLog) command;
        boolean isNew = (jmiMemberLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiMemberLog".equals(strAction)  ) {
		jmiMemberLogManager.removeJmiMemberLog(jmiMemberLog.getLogId().toString());
		key="jmiMemberLog.delete";
	}else{
		jmiMemberLogManager.saveJmiMemberLog(jmiMemberLog);
		key="jmiMemberLog.update";
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
