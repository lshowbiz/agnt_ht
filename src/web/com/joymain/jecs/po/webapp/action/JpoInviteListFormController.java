package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoInviteList;
import com.joymain.jecs.po.service.JpoInviteListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoInviteListFormController extends BaseFormController {
    private JpoInviteListManager jpoInviteListManager = null;

    public void setJpoInviteListManager(JpoInviteListManager jpoInviteListManager) {
        this.jpoInviteListManager = jpoInviteListManager;
    }
    public JpoInviteListFormController() {
        setCommandName("jpoInviteList");
        setCommandClass(JpoInviteList.class);
    }

    @Override
	protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JpoInviteList jpoInviteList = null;

        if (!StringUtils.isEmpty(id)) {
            jpoInviteList = jpoInviteListManager.getJpoInviteList(id);
        } else {
            jpoInviteList = new JpoInviteList();
        }

        return jpoInviteList;
    }

    @Override
	public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoInviteList jpoInviteList = (JpoInviteList) command;
        boolean isNew = (jpoInviteList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpoInviteList".equals(strAction)  ) {
		jpoInviteListManager.removeJpoInviteList(jpoInviteList.getId().toString());
		key="jpoInviteList.delete";
	}else{
		jpoInviteListManager.saveJpoInviteList(jpoInviteList);
		key="jpoInviteList.update";
	}

        return new ModelAndView(getSuccessView());
    }
    @Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
