package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiGetbillaccountLog;
import com.joymain.jecs.fi.service.FiGetbillaccountLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiGetbillaccountLogFormController extends BaseFormController {
    private FiGetbillaccountLogManager fiGetbillaccountLogManager = null;

    public void setFiGetbillaccountLogManager(FiGetbillaccountLogManager fiGetbillaccountLogManager) {
        this.fiGetbillaccountLogManager = fiGetbillaccountLogManager;
    }
    public FiGetbillaccountLogFormController() {
        setCommandName("fiGetbillaccountLog");
        setCommandClass(FiGetbillaccountLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        FiGetbillaccountLog fiGetbillaccountLog = null;

        if (!StringUtils.isEmpty(logId)) {
            fiGetbillaccountLog = fiGetbillaccountLogManager.getFiGetbillaccountLog(logId);
        } else {
            fiGetbillaccountLog = new FiGetbillaccountLog();
        }

        return fiGetbillaccountLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiGetbillaccountLog fiGetbillaccountLog = (FiGetbillaccountLog) command;
        boolean isNew = (fiGetbillaccountLog.getLogId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiGetbillaccountLog".equals(strAction)  ) {
		fiGetbillaccountLogManager.removeFiGetbillaccountLog(fiGetbillaccountLog.getLogId().toString());
		key="fiGetbillaccountLog.delete";
	}else{
		fiGetbillaccountLogManager.saveFiGetbillaccountLog(fiGetbillaccountLog);
		key="fiGetbillaccountLog.update";
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
