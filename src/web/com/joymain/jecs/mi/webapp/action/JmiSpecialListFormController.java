package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiSpecialList;
import com.joymain.jecs.mi.service.JmiSpecialListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiSpecialListFormController extends BaseFormController {
    private JmiSpecialListManager jmiSpecialListManager = null;

    public void setJmiSpecialListManager(JmiSpecialListManager jmiSpecialListManager) {
        this.jmiSpecialListManager = jmiSpecialListManager;
    }
    public JmiSpecialListFormController() {
        setCommandName("jmiSpecialList");
        setCommandClass(JmiSpecialList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiSpecialList jmiSpecialList = null;

        if (!StringUtils.isEmpty(id)) {
            jmiSpecialList = jmiSpecialListManager.getJmiSpecialList(id);
        } else {
            jmiSpecialList = new JmiSpecialList();
        }

        return jmiSpecialList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiSpecialList jmiSpecialList = (JmiSpecialList) command;
        boolean isNew = (jmiSpecialList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiSpecialList".equals(strAction)  ) {
		jmiSpecialListManager.removeJmiSpecialList(jmiSpecialList.getId().toString());
		key="jmiSpecialList.delete";
	}else{
		jmiSpecialListManager.saveJmiSpecialList(jmiSpecialList);
		key="jmiSpecialList.update";
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
