package com.joymain.jecs.am.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.am.model.PublicSchedule;
import com.joymain.jecs.am.service.PublicScheduleManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PublicScheduleFormController extends BaseFormController {
    private PublicScheduleManager publicScheduleManager = null;

    public void setPublicScheduleManager(PublicScheduleManager publicScheduleManager) {
        this.publicScheduleManager = publicScheduleManager;
    }
    public PublicScheduleFormController() {
        setCommandName("publicSchedule");
        setCommandClass(PublicSchedule.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String psId = request.getParameter("psId");
        PublicSchedule publicSchedule = null;

        if (!StringUtils.isEmpty(psId)) {
            publicSchedule = publicScheduleManager.getPublicSchedule(psId);
        } else {
            publicSchedule = new PublicSchedule();
        }

        return publicSchedule;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        PublicSchedule publicSchedule = (PublicSchedule) command;
        boolean isNew = (publicSchedule.getPsId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deletePublicSchedule".equals(strAction)  ) {
		publicScheduleManager.removePublicSchedule(publicSchedule.getPsId().toString());
		key="publicSchedule.delete";
	}else{
		publicScheduleManager.savePublicSchedule(publicSchedule);
		key="publicSchedule.update";
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
