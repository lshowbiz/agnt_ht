package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdDayBounsCalc;
import com.joymain.jecs.bd.service.JbdDayBounsCalcManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdDayBounsCalcFormController extends BaseFormController {
    private JbdDayBounsCalcManager jbdDayBounsCalcManager = null;

    public void setJbdDayBounsCalcManager(JbdDayBounsCalcManager jbdDayBounsCalcManager) {
        this.jbdDayBounsCalcManager = jbdDayBounsCalcManager;
    }
    public JbdDayBounsCalcFormController() {
        setCommandName("jbdDayBounsCalc");
        setCommandClass(JbdDayBounsCalc.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdDayBounsCalc jbdDayBounsCalc = null;

        if (!StringUtils.isEmpty(id)) {
            jbdDayBounsCalc = jbdDayBounsCalcManager.getJbdDayBounsCalc(id);
        } else {
            jbdDayBounsCalc = new JbdDayBounsCalc();
        }

        return jbdDayBounsCalc;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdDayBounsCalc jbdDayBounsCalc = (JbdDayBounsCalc) command;
        boolean isNew = (jbdDayBounsCalc.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdDayBounsCalc".equals(strAction)  ) {
		jbdDayBounsCalcManager.removeJbdDayBounsCalc(jbdDayBounsCalc.getId().toString());
		key="jbdDayBounsCalc.delete";
	}else{
		jbdDayBounsCalcManager.saveJbdDayBounsCalc(jbdDayBounsCalc);
		key="jbdDayBounsCalc.update";
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
