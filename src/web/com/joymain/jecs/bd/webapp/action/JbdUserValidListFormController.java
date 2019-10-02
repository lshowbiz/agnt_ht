package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdUserValidList;
import com.joymain.jecs.bd.service.JbdUserValidListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdUserValidListFormController extends BaseFormController {
    private JbdUserValidListManager jbdUserValidListManager = null;

    public void setJbdUserValidListManager(JbdUserValidListManager jbdUserValidListManager) {
        this.jbdUserValidListManager = jbdUserValidListManager;
    }
    public JbdUserValidListFormController() {
        setCommandName("jbdUserValidList");
        setCommandClass(JbdUserValidList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdUserValidList jbdUserValidList = null;

        if (!StringUtils.isEmpty(id)) {
            jbdUserValidList = jbdUserValidListManager.getJbdUserValidList(id);
        } else {
            jbdUserValidList = new JbdUserValidList();
        }

        return jbdUserValidList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdUserValidList jbdUserValidList = (JbdUserValidList) command;
        boolean isNew = (jbdUserValidList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdUserValidList".equals(strAction)  ) {
		jbdUserValidListManager.removeJbdUserValidList(jbdUserValidList.getId().toString());
		key="jbdUserValidList.delete";
	}else{
		jbdUserValidListManager.saveJbdUserValidList(jbdUserValidList);
		key="jbdUserValidList.update";
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
