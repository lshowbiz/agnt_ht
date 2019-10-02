package com.joymain.jecs.am.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.am.model.InwIntegrationTotal;
import com.joymain.jecs.am.service.InwIntegrationTotalManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class InwIntegrationTotalFormController extends BaseFormController {
    private InwIntegrationTotalManager inwIntegrationTotalManager = null;

    public void setInwIntegrationTotalManager(InwIntegrationTotalManager inwIntegrationTotalManager) {
        this.inwIntegrationTotalManager = inwIntegrationTotalManager;
    }
    public InwIntegrationTotalFormController() {
        setCommandName("inwIntegrationTotal");
        setCommandClass(InwIntegrationTotal.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        InwIntegrationTotal inwIntegrationTotal = null;

        if (!StringUtils.isEmpty(id)) {
            inwIntegrationTotal = inwIntegrationTotalManager.getInwIntegrationTotal(id);
        } else {
            inwIntegrationTotal = new InwIntegrationTotal();
        }

        return inwIntegrationTotal;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        InwIntegrationTotal inwIntegrationTotal = (InwIntegrationTotal) command;
        boolean isNew = (inwIntegrationTotal.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteInwIntegrationTotal".equals(strAction)  ) {
		inwIntegrationTotalManager.removeInwIntegrationTotal(inwIntegrationTotal.getId().toString());
		key="inwIntegrationTotal.delete";
	}else{
		inwIntegrationTotalManager.saveInwIntegrationTotal(inwIntegrationTotal);
		key="inwIntegrationTotal.update";
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
