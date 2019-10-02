package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdYkJiandianList;
import com.joymain.jecs.bd.service.JbdYkJiandianListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdYkJiandianListFormController extends BaseFormController {
    private JbdYkJiandianListManager jbdYkJiandianListManager = null;

    public void setJbdYkJiandianListManager(JbdYkJiandianListManager jbdYkJiandianListManager) {
        this.jbdYkJiandianListManager = jbdYkJiandianListManager;
    }
    public JbdYkJiandianListFormController() {
        setCommandName("jbdYkJiandianList");
        setCommandClass(JbdYkJiandianList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdYkJiandianList jbdYkJiandianList = null;

        if (!StringUtils.isEmpty(id)) {
            jbdYkJiandianList = jbdYkJiandianListManager.getJbdYkJiandianList(id);
        } else {
            jbdYkJiandianList = new JbdYkJiandianList();
        }

        return jbdYkJiandianList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdYkJiandianList jbdYkJiandianList = (JbdYkJiandianList) command;
        boolean isNew = (jbdYkJiandianList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdYkJiandianList".equals(strAction)  ) {
		jbdYkJiandianListManager.removeJbdYkJiandianList(jbdYkJiandianList.getId().toString());
		key="jbdYkJiandianList.delete";
	}else{
		jbdYkJiandianListManager.saveJbdYkJiandianList(jbdYkJiandianList);
		key="jbdYkJiandianList.update";
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
