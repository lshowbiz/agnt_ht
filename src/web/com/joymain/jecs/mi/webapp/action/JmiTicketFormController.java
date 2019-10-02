package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiTicket;
import com.joymain.jecs.mi.service.JmiTicketManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiTicketFormController extends BaseFormController {
    private JmiTicketManager jmiTicketManager = null;

    public void setJmiTicketManager(JmiTicketManager jmiTicketManager) {
        this.jmiTicketManager = jmiTicketManager;
    }
    public JmiTicketFormController() {
        setCommandName("jmiTicket");
        setCommandClass(JmiTicket.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiTicket jmiTicket = null;

        if (!StringUtils.isEmpty(id)) {
            jmiTicket = jmiTicketManager.getJmiTicket(id);
        } else {
            jmiTicket = new JmiTicket();
        }

        return jmiTicket;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiTicket jmiTicket = (JmiTicket) command;
        boolean isNew = (jmiTicket.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiTicket".equals(strAction)  ) {
		jmiTicketManager.removeJmiTicket(jmiTicket.getId().toString());
		key="jmiTicket.delete";
	}else{
		jmiTicketManager.saveJmiTicket(jmiTicket);
		key="jmiTicket.update";
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
