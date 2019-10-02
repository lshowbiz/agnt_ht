package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.fi.service.JfiChinapayPosLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiChinapayPosLogFormController extends BaseFormController {
    private JfiChinapayPosLogManager jfiChinapayPosLogManager = null;

    public void setJfiChinapayPosLogManager(JfiChinapayPosLogManager jfiChinapayPosLogManager) {
        this.jfiChinapayPosLogManager = jfiChinapayPosLogManager;
    }
    public JfiChinapayPosLogFormController() {
        setCommandName("jfiChinapayPosLog");
        setCommandClass(JfiChinapayPosLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiChinapayPosLog jfiChinapayPosLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiChinapayPosLog = jfiChinapayPosLogManager.getJfiChinapayPosLog(logId);
        } else {
            jfiChinapayPosLog = new JfiChinapayPosLog();
        }

        return jfiChinapayPosLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiChinapayPosLog jfiChinapayPosLog = (JfiChinapayPosLog) command;
        boolean isNew = (jfiChinapayPosLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiChinapayPosLog".equals(strAction)  ) {
		jfiChinapayPosLogManager.removeJfiChinapayPosLog(jfiChinapayPosLog.getLogId().toString());
		key="jfiChinapayPosLog.delete";
	}else{
		jfiChinapayPosLogManager.saveJfiChinapayPosLog(jfiChinapayPosLog);
		key="jfiChinapayPosLog.update";
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
