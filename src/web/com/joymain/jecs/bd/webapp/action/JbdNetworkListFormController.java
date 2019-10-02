package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdNetworkList;
import com.joymain.jecs.bd.service.JbdNetworkListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdNetworkListFormController extends BaseFormController {
    private JbdNetworkListManager jbdNetworkListManager = null;

    public void setJbdNetworkListManager(JbdNetworkListManager jbdNetworkListManager) {
        this.jbdNetworkListManager = jbdNetworkListManager;
    }
    public JbdNetworkListFormController() {
        setCommandName("jbdNetworkList");
        setCommandClass(JbdNetworkList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdNetworkList jbdNetworkList = null;

        if (!StringUtils.isEmpty(id)) {
            jbdNetworkList = jbdNetworkListManager.getJbdNetworkList(id);
        } else {
            jbdNetworkList = new JbdNetworkList();
        }

        return jbdNetworkList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdNetworkList jbdNetworkList = (JbdNetworkList) command;
        boolean isNew = (jbdNetworkList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdNetworkList".equals(strAction)  ) {
		jbdNetworkListManager.removeJbdNetworkList(jbdNetworkList.getId().toString());
		key="jbdNetworkList.delete";
	}else{
		jbdNetworkListManager.saveJbdNetworkList(jbdNetworkList);
		key="jbdNetworkList.update";
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
