package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiAlipayLogFormController extends BaseFormController {
    private JfiAlipayLogManager jfiAlipayLogManager = null;

    public void setJfiAlipayLogManager(JfiAlipayLogManager jfiAlipayLogManager) {
        this.jfiAlipayLogManager = jfiAlipayLogManager;
    }
    public JfiAlipayLogFormController() {
        setCommandName("jfiAlipayLog");
        setCommandClass(JfiAlipayLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiAlipayLog jfiAlipayLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiAlipayLog = jfiAlipayLogManager.getJfiAlipayLog(logId);
        } else {
            jfiAlipayLog = new JfiAlipayLog();
        }

        return jfiAlipayLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiAlipayLog jfiAlipayLog = (JfiAlipayLog) command;
        boolean isNew = (jfiAlipayLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiAlipayLog".equals(strAction)  ) {
		jfiAlipayLogManager.removeJfiAlipayLog(jfiAlipayLog.getLogId().toString());
		key="jfiAlipayLog.delete";
	}else{
		jfiAlipayLogManager.saveJfiAlipayLog(jfiAlipayLog);
		key="jfiAlipayLog.update";
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
