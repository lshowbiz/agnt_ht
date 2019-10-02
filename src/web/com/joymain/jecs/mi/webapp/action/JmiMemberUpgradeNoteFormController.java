package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiMemberUpgradeNote;
import com.joymain.jecs.mi.service.JmiMemberUpgradeNoteManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiMemberUpgradeNoteFormController extends BaseFormController {
    private JmiMemberUpgradeNoteManager jmiMemberUpgradeNoteManager = null;

    public void setJmiMemberUpgradeNoteManager(JmiMemberUpgradeNoteManager jmiMemberUpgradeNoteManager) {
        this.jmiMemberUpgradeNoteManager = jmiMemberUpgradeNoteManager;
    }
    public JmiMemberUpgradeNoteFormController() {
        setCommandName("jmiMemberUpgradeNote");
        setCommandClass(JmiMemberUpgradeNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String munId = request.getParameter("munId");
        JmiMemberUpgradeNote jmiMemberUpgradeNote = null;

        if (!StringUtils.isEmpty(munId)) {
            jmiMemberUpgradeNote = jmiMemberUpgradeNoteManager.getJmiMemberUpgradeNote(munId);
        } else {
            jmiMemberUpgradeNote = new JmiMemberUpgradeNote();
        }

        return jmiMemberUpgradeNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiMemberUpgradeNote jmiMemberUpgradeNote = (JmiMemberUpgradeNote) command;
        boolean isNew = (jmiMemberUpgradeNote.getMunId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiMemberUpgradeNote".equals(strAction)  ) {
		jmiMemberUpgradeNoteManager.removeJmiMemberUpgradeNote(jmiMemberUpgradeNote.getMunId().toString());
		key="jmiMemberUpgradeNote.delete";
	}else{
		jmiMemberUpgradeNoteManager.saveJmiMemberUpgradeNote(jmiMemberUpgradeNote);
		key="jmiMemberUpgradeNote.update";
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
