package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdTaiwanTravelPoint;
import com.joymain.jecs.bd.service.JbdTaiwanTravelPointManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTaiwanTravelPointFormController extends BaseFormController {
    private JbdTaiwanTravelPointManager jbdTaiwanTravelPointManager = null;

    public void setJbdTaiwanTravelPointManager(JbdTaiwanTravelPointManager jbdTaiwanTravelPointManager) {
        this.jbdTaiwanTravelPointManager = jbdTaiwanTravelPointManager;
    }
    public JbdTaiwanTravelPointFormController() {
        setCommandName("jbdTaiwanTravelPoint");
        setCommandClass(JbdTaiwanTravelPoint.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdTaiwanTravelPoint jbdTaiwanTravelPoint = null;

        if (!StringUtils.isEmpty(id)) {
            jbdTaiwanTravelPoint = jbdTaiwanTravelPointManager.getJbdTaiwanTravelPoint(id);
        } else {
            jbdTaiwanTravelPoint = new JbdTaiwanTravelPoint();
        }

        return jbdTaiwanTravelPoint;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTaiwanTravelPoint jbdTaiwanTravelPoint = (JbdTaiwanTravelPoint) command;
        boolean isNew = (jbdTaiwanTravelPoint.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdTaiwanTravelPoint".equals(strAction)  ) {
		jbdTaiwanTravelPointManager.removeJbdTaiwanTravelPoint(jbdTaiwanTravelPoint.getId().toString());
		key="jbdTaiwanTravelPoint.delete";
	}else{
		jbdTaiwanTravelPointManager.saveJbdTaiwanTravelPoint(jbdTaiwanTravelPoint);
		key="jbdTaiwanTravelPoint.update";
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
