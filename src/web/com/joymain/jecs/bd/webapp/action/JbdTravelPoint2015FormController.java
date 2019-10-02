package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdTravelPoint2015;
import com.joymain.jecs.bd.service.JbdTravelPoint2015Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPoint2015FormController extends BaseFormController {
    private JbdTravelPoint2015Manager jbdTravelPoint2015Manager = null;

    public void setJbdTravelPoint2015Manager(JbdTravelPoint2015Manager jbdTravelPoint2015Manager) {
        this.jbdTravelPoint2015Manager = jbdTravelPoint2015Manager;
    }
    public JbdTravelPoint2015FormController() {
        setCommandName("jbdTravelPoint2015");
        setCommandClass(JbdTravelPoint2015.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JbdTravelPoint2015 jbdTravelPoint2015 = null;

        if (!StringUtils.isEmpty(userCode)) {
            jbdTravelPoint2015 = jbdTravelPoint2015Manager.getJbdTravelPoint2015(userCode);
        } else {
            jbdTravelPoint2015 = new JbdTravelPoint2015();
        }

        return jbdTravelPoint2015;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPoint2015 jbdTravelPoint2015 = (JbdTravelPoint2015) command;
        boolean isNew = (jbdTravelPoint2015.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdTravelPoint2015".equals(strAction)  ) {
		jbdTravelPoint2015Manager.removeJbdTravelPoint2015(jbdTravelPoint2015.getUserCode().toString());
		key="jbdTravelPoint2015.delete";
	}else{
		jbdTravelPoint2015Manager.saveJbdTravelPoint2015(jbdTravelPoint2015);
		key="jbdTravelPoint2015.update";
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
