package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdVentureLeaderSubHist;
import com.joymain.jecs.bd.service.JbdVentureLeaderSubHistManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdVentureLeaderSubHistFormController extends BaseFormController {
    private JbdVentureLeaderSubHistManager jbdVentureLeaderSubHistManager = null;

    public void setJbdVentureLeaderSubHistManager(JbdVentureLeaderSubHistManager jbdVentureLeaderSubHistManager) {
        this.jbdVentureLeaderSubHistManager = jbdVentureLeaderSubHistManager;
    }
    public JbdVentureLeaderSubHistFormController() {
        setCommandName("jbdVentureLeaderSubHist");
        setCommandClass(JbdVentureLeaderSubHist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdVentureLeaderSubHist jbdVentureLeaderSubHist = null;

        if (!StringUtils.isEmpty(id)) {
            jbdVentureLeaderSubHist = jbdVentureLeaderSubHistManager.getJbdVentureLeaderSubHist(id);
        } else {
            jbdVentureLeaderSubHist = new JbdVentureLeaderSubHist();
        }

        return jbdVentureLeaderSubHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdVentureLeaderSubHist jbdVentureLeaderSubHist = (JbdVentureLeaderSubHist) command;
        boolean isNew = (jbdVentureLeaderSubHist.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdVentureLeaderSubHist".equals(strAction)  ) {
		jbdVentureLeaderSubHistManager.removeJbdVentureLeaderSubHist(jbdVentureLeaderSubHist.getId().toString());
		key="jbdVentureLeaderSubHist.delete";
	}else{
		jbdVentureLeaderSubHistManager.saveJbdVentureLeaderSubHist(jbdVentureLeaderSubHist);
		key="jbdVentureLeaderSubHist.update";
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
