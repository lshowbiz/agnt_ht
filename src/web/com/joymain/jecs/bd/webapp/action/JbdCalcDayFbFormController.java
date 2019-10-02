package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdCalcDayFb;
import com.joymain.jecs.bd.service.JbdCalcDayFbManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdCalcDayFbFormController extends BaseFormController {
    private JbdCalcDayFbManager jbdCalcDayFbManager = null;

    public void setJbdCalcDayFbManager(JbdCalcDayFbManager jbdCalcDayFbManager) {
        this.jbdCalcDayFbManager = jbdCalcDayFbManager;
    }
    public JbdCalcDayFbFormController() {
        setCommandName("jbdCalcDayFb");
        setCommandClass(JbdCalcDayFb.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdCalcDayFb jbdCalcDayFb = null;

        if (!StringUtils.isEmpty(id)) {
            //jbdCalcDayFb = jbdCalcDayFbManager.getJbdCalcDayFb(id);
        } else {
            jbdCalcDayFb = new JbdCalcDayFb();
        }


        String userCode = request.getParameter("userCode");
        String wweek = request.getParameter("wweek");
        List jbdCalcDayFbList=jbdCalcDayFbManager.getJbdCalcDayFbList(userCode, wweek);
        request.setAttribute("jbdCalcDayFbList", jbdCalcDayFbList);
        return jbdCalcDayFb;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdCalcDayFb jbdCalcDayFb = (JbdCalcDayFb) command;
        boolean isNew = (jbdCalcDayFb.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdCalcDayFb".equals(strAction)  ) {
		jbdCalcDayFbManager.removeJbdCalcDayFb(jbdCalcDayFb.getId().toString());
		key="jbdCalcDayFb.delete";
	}else{
		jbdCalcDayFbManager.saveJbdCalcDayFb(jbdCalcDayFb);
		key="jbdCalcDayFb.update";
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
