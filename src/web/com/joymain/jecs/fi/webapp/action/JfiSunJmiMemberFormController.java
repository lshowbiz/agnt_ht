package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiSunJmiMember;
import com.joymain.jecs.fi.service.JfiSunJmiMemberManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiSunJmiMemberFormController extends BaseFormController {
    private JfiSunJmiMemberManager jfiSunJmiMemberManager = null;

    public void setJfiSunJmiMemberManager(JfiSunJmiMemberManager jfiSunJmiMemberManager) {
        this.jfiSunJmiMemberManager = jfiSunJmiMemberManager;
    }
    public JfiSunJmiMemberFormController() {
        setCommandName("jfiSunJmiMember");
        setCommandClass(JfiSunJmiMember.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JfiSunJmiMember jfiSunJmiMember = null;

        if (!StringUtils.isEmpty(userCode)) {
            jfiSunJmiMember = jfiSunJmiMemberManager.getJfiSunJmiMember(userCode);
        } else {
            jfiSunJmiMember = new JfiSunJmiMember();
        }

        return jfiSunJmiMember;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiSunJmiMember jfiSunJmiMember = (JfiSunJmiMember) command;
        boolean isNew = (jfiSunJmiMember.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiSunJmiMember".equals(strAction)  ) {
		jfiSunJmiMemberManager.removeJfiSunJmiMember(jfiSunJmiMember.getUserCode().toString());
		key="jfiSunJmiMember.delete";
	}else{
		jfiSunJmiMemberManager.saveJfiSunJmiMember(jfiSunJmiMember);
		key="jfiSunJmiMember.update";
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
