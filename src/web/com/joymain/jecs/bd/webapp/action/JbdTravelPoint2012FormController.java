package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdTravelPoint2012;
import com.joymain.jecs.bd.service.JbdTravelPoint2012Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPoint2012FormController extends BaseFormController {
    private JbdTravelPoint2012Manager jbdTravelPoint2012Manager = null;

    public void setJbdTravelPoint2012Manager(JbdTravelPoint2012Manager jbdTravelPoint2012Manager) {
        this.jbdTravelPoint2012Manager = jbdTravelPoint2012Manager;
    }
    public JbdTravelPoint2012FormController() {
        setCommandName("jbdTravelPoint2012");
        setCommandClass(JbdTravelPoint2012.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JbdTravelPoint2012 jbdTravelPoint2012 = null;

        if (!StringUtils.isEmpty(userCode)) {
            jbdTravelPoint2012 = jbdTravelPoint2012Manager.getJbdTravelPoint2012(userCode);
        } else {
            jbdTravelPoint2012 = new JbdTravelPoint2012();
        }

        return jbdTravelPoint2012;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPoint2012 jbdTravelPoint2012 = (JbdTravelPoint2012) command;
        boolean isNew = (jbdTravelPoint2012.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdTravelPoint2012".equals(strAction)  ) {
		jbdTravelPoint2012Manager.removeJbdTravelPoint2012(jbdTravelPoint2012.getUserCode().toString());
		key="jbdTravelPoint2012.delete";
	}else{
		jbdTravelPoint2012Manager.saveJbdTravelPoint2012(jbdTravelPoint2012);
		key="jbdTravelPoint2012.update";
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
