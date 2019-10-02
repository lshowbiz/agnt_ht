package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiUmbpayLog;
import com.joymain.jecs.fi.service.JfiUmbpayLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiUmbpayLogFormController extends BaseFormController {
    private JfiUmbpayLogManager jfiUmbpayLogManager = null;

    public void setJfiUmbpayLogManager(JfiUmbpayLogManager jfiUmbpayLogManager) {
        this.jfiUmbpayLogManager = jfiUmbpayLogManager;
    }
    public JfiUmbpayLogFormController() {
        setCommandName("jfiUmbpayLog");
        setCommandClass(JfiUmbpayLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JfiUmbpayLog jfiUmbpayLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jfiUmbpayLog = jfiUmbpayLogManager.getJfiUmbpayLog(logId);
        } else {
            jfiUmbpayLog = new JfiUmbpayLog();
        }

        return jfiUmbpayLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiUmbpayLog jfiUmbpayLog = (JfiUmbpayLog) command;
        boolean isNew = (jfiUmbpayLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiUmbpayLog".equals(strAction)  ) {
		jfiUmbpayLogManager.removeJfiUmbpayLog(jfiUmbpayLog.getLogId().toString());
		key="jfiUmbpayLog.delete";
	}else{
		jfiUmbpayLogManager.saveJfiUmbpayLog(jfiUmbpayLog);
		key="jfiUmbpayLog.update";
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
