package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.service.JsysResRoleManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JsysResRoleFormController extends BaseFormController {
    private JsysResRoleManager jsysResRoleManager = null;

    public void setJsysResRoleManager(JsysResRoleManager jsysResRoleManager) {
        this.jsysResRoleManager = jsysResRoleManager;
    }
    public JsysResRoleFormController() {
        setCommandName("jsysResRole");
        setCommandClass(JsysResRole.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String pid = request.getParameter("pid");
        JsysResRole jsysResRole = null;

        if (!StringUtils.isEmpty(pid)) {
            jsysResRole = jsysResRoleManager.getJsysResRole(pid);
        } else {
            jsysResRole = new JsysResRole();
        }

        return jsysResRole;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JsysResRole jsysResRole = (JsysResRole) command;
        boolean isNew = (jsysResRole.getPid() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJsysResRole".equals(strAction)  ) {
		jsysResRoleManager.removeJsysResRole(jsysResRole.getPid().toString());
		key="jsysResRole.delete";
	}else{
		jsysResRoleManager.saveJsysResRole(jsysResRole);
		key="jsysResRole.update";
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
