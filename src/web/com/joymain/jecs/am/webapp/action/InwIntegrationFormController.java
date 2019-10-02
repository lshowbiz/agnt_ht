package com.joymain.jecs.am.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.am.model.InwIntegration;
import com.joymain.jecs.am.service.InwIntegrationManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class InwIntegrationFormController extends BaseFormController {
    private InwIntegrationManager inwIntegrationManager = null;

    public void setInwIntegrationManager(InwIntegrationManager inwIntegrationManager) {
        this.inwIntegrationManager = inwIntegrationManager;
    }
    public InwIntegrationFormController() {
        setCommandName("inwIntegration");
        setCommandClass(InwIntegration.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        InwIntegration inwIntegration = null;

        if (!StringUtils.isEmpty(id)) {
            inwIntegration = inwIntegrationManager.getInwIntegration(id);
        } else {
            inwIntegration = new InwIntegration();
        }

        return inwIntegration;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        InwIntegration inwIntegration = (InwIntegration) command;
        boolean isNew = (inwIntegration.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteInwIntegration".equals(strAction)  ) {
		inwIntegrationManager.removeInwIntegration(inwIntegration.getId().toString());
		key="inwIntegration.delete";
	}else{
		inwIntegrationManager.saveInwIntegration(inwIntegration);
		key="inwIntegration.update";
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
