package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pm.model.JpmCouponRelationship;
import com.joymain.jecs.pm.service.JpmCouponRelationshipManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmCouponRelationshipFormController extends BaseFormController {
    private JpmCouponRelationshipManager jpmCouponRelationshipManager = null;

    public void setJpmCouponRelationshipManager(JpmCouponRelationshipManager jpmCouponRelationshipManager) {
        this.jpmCouponRelationshipManager = jpmCouponRelationshipManager;
    }
    public JpmCouponRelationshipFormController() {
        setCommandName("jpmCouponRelationship");
        setCommandClass(JpmCouponRelationship.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JpmCouponRelationship jpmCouponRelationship = null;

        if (!StringUtils.isEmpty(id)) {
            jpmCouponRelationship = jpmCouponRelationshipManager.getJpmCouponRelationship(id);
        } else {
            jpmCouponRelationship = new JpmCouponRelationship();
        }

        return jpmCouponRelationship;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmCouponRelationship jpmCouponRelationship = (JpmCouponRelationship) command;
        boolean isNew = (jpmCouponRelationship.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpmCouponRelationship".equals(strAction)  ) {
		jpmCouponRelationshipManager.removeJpmCouponRelationship(jpmCouponRelationship.getId().toString());
		key="jpmCouponRelationship.delete";
	}else{
		jpmCouponRelationshipManager.saveJpmCouponRelationship(jpmCouponRelationship);
		key="jpmCouponRelationship.update";
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
