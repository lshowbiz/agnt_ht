package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.sys.model.UpsInteractiveLog;
import com.joymain.jecs.sys.service.UpsInteractiveLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class UpsInteractiveLogFormController extends BaseFormController {
    private UpsInteractiveLogManager upsInteractiveLogManager = null;

    public void setUpsInteractiveLogManager(UpsInteractiveLogManager upsInteractiveLogManager) {
        this.upsInteractiveLogManager = upsInteractiveLogManager;
    }
    public UpsInteractiveLogFormController() {
        setCommandName("upsInteractiveLog");
        setCommandClass(UpsInteractiveLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String uniId = request.getParameter("uniId");
        UpsInteractiveLog upsInteractiveLog = null;

        if (!StringUtils.isEmpty(uniId)) {
            upsInteractiveLog = upsInteractiveLogManager.getUpsInteractiveLog(uniId);
        } else {
            upsInteractiveLog = new UpsInteractiveLog();
        }

        return upsInteractiveLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        UpsInteractiveLog upsInteractiveLog = (UpsInteractiveLog) command;
        boolean isNew = (upsInteractiveLog.getUniId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteUpsInteractiveLog".equals(strAction)  ) {
		upsInteractiveLogManager.removeUpsInteractiveLog(upsInteractiveLog.getUniId().toString());
		key="upsInteractiveLog.delete";
	}else{
		upsInteractiveLogManager.saveUpsInteractiveLog(upsInteractiveLog);
		key="upsInteractiveLog.update";
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
