package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiMemberCompanyNote;
import com.joymain.jecs.mi.service.JmiMemberCompanyNoteManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiMemberCompanyNoteFormController extends BaseFormController {
    private JmiMemberCompanyNoteManager jmiMemberCompanyNoteManager = null;

    public void setJmiMemberCompanyNoteManager(JmiMemberCompanyNoteManager jmiMemberCompanyNoteManager) {
        this.jmiMemberCompanyNoteManager = jmiMemberCompanyNoteManager;
    }
    public JmiMemberCompanyNoteFormController() {
        setCommandName("jmiMemberCompanyNote");
        setCommandClass(JmiMemberCompanyNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiMemberCompanyNote jmiMemberCompanyNote = null;

        if (!StringUtils.isEmpty(id)) {
            jmiMemberCompanyNote = jmiMemberCompanyNoteManager.getJmiMemberCompanyNote(id);
        } else {
            jmiMemberCompanyNote = new JmiMemberCompanyNote();
        }

        return jmiMemberCompanyNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiMemberCompanyNote jmiMemberCompanyNote = (JmiMemberCompanyNote) command;
        boolean isNew = (jmiMemberCompanyNote.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiMemberCompanyNote".equals(strAction)  ) {
		jmiMemberCompanyNoteManager.removeJmiMemberCompanyNote(jmiMemberCompanyNote.getId().toString());
		key="jmiMemberCompanyNote.delete";
	}else{
		jmiMemberCompanyNoteManager.saveJmiMemberCompanyNote(jmiMemberCompanyNote);
		key="jmiMemberCompanyNote.update";
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
