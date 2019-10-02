package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.MiCoinLog;
import com.joymain.jecs.mi.service.MiCoinLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class MiCoinLogFormController extends BaseFormController {
    private MiCoinLogManager miCoinLogManager = null;

    public void setMiCoinLogManager(MiCoinLogManager miCoinLogManager) {
        this.miCoinLogManager = miCoinLogManager;
    }
    public MiCoinLogFormController() {
        setCommandName("miCoinLog");
        setCommandClass(MiCoinLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        MiCoinLog miCoinLog = null;

        if (!StringUtils.isEmpty(id)) {
            miCoinLog = miCoinLogManager.getMiCoinLog(id);
        } else {
            miCoinLog = new MiCoinLog();
        }

        return miCoinLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        MiCoinLog miCoinLog = (MiCoinLog) command;
        boolean isNew = (miCoinLog.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteMiCoinLog".equals(strAction)  ) {
		miCoinLogManager.removeMiCoinLog(miCoinLog.getId().toString());
		key="miCoinLog.delete";
	}else{
		miCoinLogManager.saveMiCoinLog(miCoinLog);
		key="miCoinLog.update";
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
