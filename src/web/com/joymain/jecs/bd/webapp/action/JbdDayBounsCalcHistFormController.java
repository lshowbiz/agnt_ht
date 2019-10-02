package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdDayBounsCalcHist;
import com.joymain.jecs.bd.service.JbdDayBounsCalcHistManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdDayBounsCalcHistFormController extends BaseFormController {
    private JbdDayBounsCalcHistManager jbdDayBounsCalcHistManager = null;

    public void setJbdDayBounsCalcHistManager(JbdDayBounsCalcHistManager jbdDayBounsCalcHistManager) {
        this.jbdDayBounsCalcHistManager = jbdDayBounsCalcHistManager;
    }
    public JbdDayBounsCalcHistFormController() {
        setCommandName("jbdDayBounsCalcHist");
        setCommandClass(JbdDayBounsCalcHist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdDayBounsCalcHist jbdDayBounsCalcHist = null;

        if (!StringUtils.isEmpty(id)) {
            jbdDayBounsCalcHist = jbdDayBounsCalcHistManager.getJbdDayBounsCalcHist(id);
        } else {
            jbdDayBounsCalcHist = new JbdDayBounsCalcHist();
        }

        return jbdDayBounsCalcHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdDayBounsCalcHist jbdDayBounsCalcHist = (JbdDayBounsCalcHist) command;
        boolean isNew = (jbdDayBounsCalcHist.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdDayBounsCalcHist".equals(strAction)  ) {
		jbdDayBounsCalcHistManager.removeJbdDayBounsCalcHist(jbdDayBounsCalcHist.getId().toString());
		key="jbdDayBounsCalcHist.delete";
	}else{
		jbdDayBounsCalcHistManager.saveJbdDayBounsCalcHist(jbdDayBounsCalcHist);
		key="jbdDayBounsCalcHist.update";
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
