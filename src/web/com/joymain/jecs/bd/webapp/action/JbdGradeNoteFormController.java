package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdGradeNote;
import com.joymain.jecs.bd.service.JbdGradeNoteManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdGradeNoteFormController extends BaseFormController {
    private JbdGradeNoteManager jbdGradeNoteManager = null;

    public void setJbdGradeNoteManager(JbdGradeNoteManager jbdGradeNoteManager) {
        this.jbdGradeNoteManager = jbdGradeNoteManager;
    }
    public JbdGradeNoteFormController() {
        setCommandName("jbdGradeNote");
        setCommandClass(JbdGradeNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdGradeNote jbdGradeNote = null;

        if (!StringUtils.isEmpty(id)) {
            jbdGradeNote = jbdGradeNoteManager.getJbdGradeNote(id);
        } else {
            jbdGradeNote = new JbdGradeNote();
        }

        return jbdGradeNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdGradeNote jbdGradeNote = (JbdGradeNote) command;
        boolean isNew = (jbdGradeNote.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdGradeNote".equals(strAction)  ) {
		jbdGradeNoteManager.removeJbdGradeNote(jbdGradeNote.getId().toString());
		key="jbdGradeNote.delete";
	}else{
		jbdGradeNoteManager.saveJbdGradeNote(jbdGradeNote);
		key="jbdGradeNote.update";
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
