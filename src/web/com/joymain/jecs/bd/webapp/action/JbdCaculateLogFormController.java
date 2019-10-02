package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdCaculateLog;
import com.joymain.jecs.bd.service.JbdCaculateLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdCaculateLogFormController extends BaseFormController {
    private JbdCaculateLogManager jbdCaculateLogManager = null;

    public void setJbdCaculateLogManager(JbdCaculateLogManager jbdCaculateLogManager) {
        this.jbdCaculateLogManager = jbdCaculateLogManager;
    }
    public JbdCaculateLogFormController() {
        setCommandName("jbdCaculateLog");
        setCommandClass(JbdCaculateLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdCaculateLog jbdCaculateLog = null;

        if (!StringUtils.isEmpty(id)) {
            jbdCaculateLog = jbdCaculateLogManager.getJbdCaculateLog(id);
        } else {
            jbdCaculateLog = new JbdCaculateLog();
        }

        return jbdCaculateLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdCaculateLog jbdCaculateLog = (JbdCaculateLog) command;
        boolean isNew = (jbdCaculateLog.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdCaculateLog".equals(strAction)  ) {
		jbdCaculateLogManager.removeJbdCaculateLog(jbdCaculateLog.getId().toString());
		key="jbdCaculateLog.delete";
	}else{
		jbdCaculateLogManager.saveJbdCaculateLog(jbdCaculateLog);
		key="jbdCaculateLog.update";
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
