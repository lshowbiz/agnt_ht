package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiCoinLog;
import com.joymain.jecs.fi.service.FiCoinLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiCoinLogFormController extends BaseFormController {
    private FiCoinLogManager fiCoinLogManager = null;

    public void setFiCoinLogManager(FiCoinLogManager fiCoinLogManager) {
        this.fiCoinLogManager = fiCoinLogManager;
    }
    public FiCoinLogFormController() {
        setCommandName("fiCoinLog");
        setCommandClass(FiCoinLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String fclId = request.getParameter("fclId");
        FiCoinLog fiCoinLog = null;

        if (!StringUtils.isEmpty(fclId)) {
            fiCoinLog = fiCoinLogManager.getFiCoinLog(fclId);
        } else {
            fiCoinLog = new FiCoinLog();
        }

        return fiCoinLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiCoinLog fiCoinLog = (FiCoinLog) command;
        boolean isNew = (fiCoinLog.getFclId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiCoinLog".equals(strAction)  ) {
		fiCoinLogManager.removeFiCoinLog(fiCoinLog.getFclId().toString());
		key="fiCoinLog.delete";
	}else{
		fiCoinLogManager.saveFiCoinLog(fiCoinLog);
		key="fiCoinLog.update";
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
