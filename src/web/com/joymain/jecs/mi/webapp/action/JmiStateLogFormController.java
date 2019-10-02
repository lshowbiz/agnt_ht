package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiStateLog;
import com.joymain.jecs.mi.service.JmiStateLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiStateLogFormController extends BaseFormController {
    private JmiStateLogManager jmiStateLogManager = null;

    public void setJmiStateLogManager(JmiStateLogManager jmiStateLogManager) {
        this.jmiStateLogManager = jmiStateLogManager;
    }
    public JmiStateLogFormController() {
        setCommandName("jmiStateLog");
        setCommandClass(JmiStateLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiStateLog jmiStateLog = null;

        if (!StringUtils.isEmpty(id)) {
            jmiStateLog = jmiStateLogManager.getJmiStateLog(id);
        } else {
            jmiStateLog = new JmiStateLog();
        }

        return jmiStateLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiStateLog jmiStateLog = (JmiStateLog) command;
        boolean isNew = (jmiStateLog.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiStateLog".equals(strAction)  ) {
		jmiStateLogManager.removeJmiStateLog(jmiStateLog.getId().toString());
		key="jmiStateLog.delete";
	}else{
		jmiStateLogManager.saveJmiStateLog(jmiStateLog);
		key="jmiStateLog.update";
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
