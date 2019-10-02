package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pm.model.JpmSendConsignment;
import com.joymain.jecs.pm.service.JpmSendConsignmentManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmSendConsignmentFormController extends BaseFormController {
    private JpmSendConsignmentManager jpmSendConsignmentManager = null;

    public void setJpmSendConsignmentManager(JpmSendConsignmentManager jpmSendConsignmentManager) {
        this.jpmSendConsignmentManager = jpmSendConsignmentManager;
    }
    public JpmSendConsignmentFormController() {
        setCommandName("jpmSendConsignment");
        setCommandClass(JpmSendConsignment.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String consignmentNo = request.getParameter("consignmentNo");
        JpmSendConsignment jpmSendConsignment = null;

        if (!StringUtils.isEmpty(consignmentNo)) {
            jpmSendConsignment = jpmSendConsignmentManager.getJpmSendConsignment(consignmentNo);
        } else {
            jpmSendConsignment = new JpmSendConsignment();
        }

        return jpmSendConsignment;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmSendConsignment jpmSendConsignment = (JpmSendConsignment) command;
        boolean isNew = (jpmSendConsignment.getConsignmentNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpmSendConsignment".equals(strAction)  ) {
		jpmSendConsignmentManager.removeJpmSendConsignment(jpmSendConsignment.getConsignmentNo().toString());
		key="jpmSendConsignment.delete";
	}else{
		jpmSendConsignmentManager.saveJpmSendConsignment(jpmSendConsignment);
		key="jpmSendConsignment.update";
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
