package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JmiZcwMember;
import com.joymain.jecs.fi.service.JmiZcwMemberManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiZcwMemberFormController extends BaseFormController {
    private JmiZcwMemberManager jmiZcwMemberManager = null;

    public void setJmiZcwMemberManager(JmiZcwMemberManager jmiZcwMemberManager) {
        this.jmiZcwMemberManager = jmiZcwMemberManager;
    }
    public JmiZcwMemberFormController() {
        setCommandName("jmiZcwMember");
        setCommandClass(JmiZcwMember.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String zcwId = request.getParameter("zcwId");
        JmiZcwMember jmiZcwMember = null;

        if (!StringUtils.isEmpty(zcwId)) {
            jmiZcwMember = jmiZcwMemberManager.getJmiZcwMember(zcwId);
        } else {
            jmiZcwMember = new JmiZcwMember();
        }

        return jmiZcwMember;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiZcwMember jmiZcwMember = (JmiZcwMember) command;
        boolean isNew = (jmiZcwMember.getZcwId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiZcwMember".equals(strAction)  ) {
		jmiZcwMemberManager.removeJmiZcwMember(jmiZcwMember.getZcwId().toString());
		key="jmiZcwMember.delete";
	}else{
		jmiZcwMemberManager.saveJmiZcwMember(jmiZcwMember);
		key="jmiZcwMember.update";
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
