package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdTravelPoint2014;
import com.joymain.jecs.bd.service.JbdTravelPoint2014Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPoint2014FormController extends BaseFormController {
    private JbdTravelPoint2014Manager jbdTravelPoint2014Manager = null;

    public void setJbdTravelPoint2014Manager(JbdTravelPoint2014Manager jbdTravelPoint2014Manager) {
        this.jbdTravelPoint2014Manager = jbdTravelPoint2014Manager;
    }
    public JbdTravelPoint2014FormController() {
        setCommandName("jbdTravelPoint2014");
        setCommandClass(JbdTravelPoint2014.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JbdTravelPoint2014 jbdTravelPoint2014 = null;

        if (!StringUtils.isEmpty(userCode)) {
            jbdTravelPoint2014 = jbdTravelPoint2014Manager.getJbdTravelPoint2014(userCode);
        } else {
            jbdTravelPoint2014 = new JbdTravelPoint2014();
        }

        return jbdTravelPoint2014;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPoint2014 jbdTravelPoint2014 = (JbdTravelPoint2014) command;
        boolean isNew = (jbdTravelPoint2014.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdTravelPoint2014".equals(strAction)  ) {
		jbdTravelPoint2014Manager.removeJbdTravelPoint2014(jbdTravelPoint2014.getUserCode().toString());
		key="jbdTravelPoint2014.delete";
	}else{
		jbdTravelPoint2014Manager.saveJbdTravelPoint2014(jbdTravelPoint2014);
		key="jbdTravelPoint2014.update";
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
