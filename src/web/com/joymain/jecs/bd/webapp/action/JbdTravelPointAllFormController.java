package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdTravelPointAll;
import com.joymain.jecs.bd.service.JbdTravelPointAllManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointAllFormController extends BaseFormController {
    private JbdTravelPointAllManager jbdTravelPointAllManager = null;

    public void setJbdTravelPointAllManager(JbdTravelPointAllManager jbdTravelPointAllManager) {
        this.jbdTravelPointAllManager = jbdTravelPointAllManager;
    }
    public JbdTravelPointAllFormController() {
        setCommandName("jbdTravelPointAll");
        setCommandClass(JbdTravelPointAll.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String comp_id = request.getParameter("comp_id");
        JbdTravelPointAll jbdTravelPointAll = null;

        if (!StringUtils.isEmpty(comp_id)) {
            jbdTravelPointAll = jbdTravelPointAllManager.getJbdTravelPointAll(comp_id);
        } else {
            jbdTravelPointAll = new JbdTravelPointAll();
        }

        return jbdTravelPointAll;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPointAll jbdTravelPointAll = (JbdTravelPointAll) command;
        boolean isNew = (jbdTravelPointAll.getComp_id() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdTravelPointAll".equals(strAction)  ) {
		jbdTravelPointAllManager.removeJbdTravelPointAll(jbdTravelPointAll.getComp_id().toString());
		key="jbdTravelPointAll.delete";
	}else{
		jbdTravelPointAllManager.saveJbdTravelPointAll(jbdTravelPointAll);
		key="jbdTravelPointAll.update";
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
