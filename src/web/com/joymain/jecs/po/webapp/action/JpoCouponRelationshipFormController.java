package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoCouponRelationship;
import com.joymain.jecs.po.service.JpoCouponRelationshipManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoCouponRelationshipFormController extends BaseFormController {
    private JpoCouponRelationshipManager jpoCouponRelationshipManager = null;

    public void setJpoCouponRelationshipManager(JpoCouponRelationshipManager jpoCouponRelationshipManager) {
        this.jpoCouponRelationshipManager = jpoCouponRelationshipManager;
    }
    public JpoCouponRelationshipFormController() {
        setCommandName("jpoCouponRelationship");
        setCommandClass(JpoCouponRelationship.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JpoCouponRelationship jpoCouponRelationship = null;

        if (!StringUtils.isEmpty(id)) {
            jpoCouponRelationship = jpoCouponRelationshipManager.getJpoCouponRelationship(id);
        } else {
            jpoCouponRelationship = new JpoCouponRelationship();
        }

        return jpoCouponRelationship;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoCouponRelationship jpoCouponRelationship = (JpoCouponRelationship) command;
        boolean isNew = (jpoCouponRelationship.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpoCouponRelationship".equals(strAction)  ) {
		jpoCouponRelationshipManager.removeJpoCouponRelationship(jpoCouponRelationship.getId().toString());
		key="jpoCouponRelationship.delete";
	}else{
		jpoCouponRelationshipManager.saveJpoCouponRelationship(jpoCouponRelationship);
		key="jpoCouponRelationship.update";
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
