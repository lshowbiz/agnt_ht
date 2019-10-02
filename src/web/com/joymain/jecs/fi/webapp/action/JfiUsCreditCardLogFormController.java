package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.service.JfiUsCreditCardLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiUsCreditCardLogFormController extends BaseFormController {
    private JfiUsCreditCardLogManager jfiUsCreditCardLogManager = null;

    public void setJfiUsCreditCardLogManager(JfiUsCreditCardLogManager jfiUsCreditCardLogManager) {
        this.jfiUsCreditCardLogManager = jfiUsCreditCardLogManager;
    }
    public JfiUsCreditCardLogFormController() {
        setCommandName("jfiUsCreditCardLog");
        setCommandClass(JfiUsCreditCardLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String jucclId = request.getParameter("jucclId");
        JfiUsCreditCardLog jfiUsCreditCardLog = null;

        if (!StringUtils.isEmpty(jucclId)) {
            jfiUsCreditCardLog = jfiUsCreditCardLogManager.getJfiUsCreditCardLog(jucclId);
        } else {
            jfiUsCreditCardLog = new JfiUsCreditCardLog();
        }

        return jfiUsCreditCardLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiUsCreditCardLog jfiUsCreditCardLog = (JfiUsCreditCardLog) command;
        boolean isNew = (jfiUsCreditCardLog.getJucclId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiUsCreditCardLog".equals(strAction)  ) {
		jfiUsCreditCardLogManager.removeJfiUsCreditCardLog(jfiUsCreditCardLog.getJucclId().toString());
		key="jfiUsCreditCardLog.delete";
	}else{
		jfiUsCreditCardLogManager.saveJfiUsCreditCardLog(jfiUsCreditCardLog);
		key="jfiUsCreditCardLog.update";
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
