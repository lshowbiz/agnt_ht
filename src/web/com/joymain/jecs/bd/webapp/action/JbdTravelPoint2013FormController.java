package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdTravelPoint2013;
import com.joymain.jecs.bd.service.JbdTravelPoint2013Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPoint2013FormController extends BaseFormController {
    private JbdTravelPoint2013Manager jbdTravelPoint2013Manager = null;

    public void setJbdTravelPoint2013Manager(JbdTravelPoint2013Manager jbdTravelPoint2013Manager) {
        this.jbdTravelPoint2013Manager = jbdTravelPoint2013Manager;
    }
    public JbdTravelPoint2013FormController() {
        setCommandName("jbdTravelPoint2013");
        setCommandClass(JbdTravelPoint2013.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JbdTravelPoint2013 jbdTravelPoint2013 = null;

        if (!StringUtils.isEmpty(userCode)) {
            jbdTravelPoint2013 = jbdTravelPoint2013Manager.getJbdTravelPoint2013(userCode);
        } else {
            jbdTravelPoint2013 = new JbdTravelPoint2013();
        }

        return jbdTravelPoint2013;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPoint2013 jbdTravelPoint2013 = (JbdTravelPoint2013) command;
        boolean isNew = (jbdTravelPoint2013.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdTravelPoint2013".equals(strAction)  ) {
		jbdTravelPoint2013Manager.removeJbdTravelPoint2013(jbdTravelPoint2013.getUserCode().toString());
		key="jbdTravelPoint2013.delete";
	}else{
		jbdTravelPoint2013Manager.saveJbdTravelPoint2013(jbdTravelPoint2013);
		key="jbdTravelPoint2013.update";
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
