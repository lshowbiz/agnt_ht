package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class SysLoginLogFormController extends BaseFormController {
    private SysLoginLogManager sysLoginLogManager = null;

    public void setSysLoginLogManager(SysLoginLogManager sysLoginLogManager) {
        this.sysLoginLogManager = sysLoginLogManager;
    }
    public SysLoginLogFormController() {
        setCommandName("sysLoginLog");
        setCommandClass(SysLoginLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String llId = request.getParameter("llId");
        SysLoginLog sysLoginLog = null;

        if (!StringUtils.isEmpty(llId)) {
            sysLoginLog = sysLoginLogManager.getSysLoginLog(llId);
        } else {
            sysLoginLog = new SysLoginLog();
        }

        return sysLoginLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysLoginLog sysLoginLog = (SysLoginLog) command;
        boolean isNew = (sysLoginLog.getLlId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteSysLoginLog".equals(strAction)  ) {
		sysLoginLogManager.removeSysLoginLog(sysLoginLog.getLlId().toString());
		key="sysLoginLog.delete";
	}else{
		sysLoginLogManager.saveSysLoginLog(sysLoginLog);
		key="sysLoginLog.update";
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
