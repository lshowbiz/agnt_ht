package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiSunMemberOrder;
import com.joymain.jecs.fi.service.JfiSunMemberOrderManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiSunMemberOrderFormController extends BaseFormController {
    private JfiSunMemberOrderManager jfiSunMemberOrderManager = null;

    public void setJfiSunMemberOrderManager(JfiSunMemberOrderManager jfiSunMemberOrderManager) {
        this.jfiSunMemberOrderManager = jfiSunMemberOrderManager;
    }
    public JfiSunMemberOrderFormController() {
        setCommandName("jfiSunMemberOrder");
        setCommandClass(JfiSunMemberOrder.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String moId = request.getParameter("moId");
        JfiSunMemberOrder jfiSunMemberOrder = null;

        if (!StringUtils.isEmpty(moId)) {
            jfiSunMemberOrder = jfiSunMemberOrderManager.getJfiSunMemberOrder(moId);
        } else {
            jfiSunMemberOrder = new JfiSunMemberOrder();
        }

        return jfiSunMemberOrder;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiSunMemberOrder jfiSunMemberOrder = (JfiSunMemberOrder) command;
        boolean isNew = (jfiSunMemberOrder.getMoId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiSunMemberOrder".equals(strAction)  ) {
		jfiSunMemberOrderManager.removeJfiSunMemberOrder(jfiSunMemberOrder.getMoId().toString());
		key="jfiSunMemberOrder.delete";
	}else{
		jfiSunMemberOrderManager.saveJfiSunMemberOrder(jfiSunMemberOrder);
		key="jfiSunMemberOrder.update";
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
