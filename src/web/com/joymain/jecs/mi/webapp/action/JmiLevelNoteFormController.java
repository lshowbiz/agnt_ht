package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.service.JmiLevelNoteManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiLevelNoteFormController extends BaseFormController {
    private JmiLevelNoteManager jmiLevelNoteManager = null;

    public void setJmiLevelNoteManager(JmiLevelNoteManager jmiLevelNoteManager) {
        this.jmiLevelNoteManager = jmiLevelNoteManager;
    }
    public JmiLevelNoteFormController() {
        setCommandName("jmiLevelNote");
        setCommandClass(JmiLevelNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiLevelNote jmiLevelNote = null;

        if (!StringUtils.isEmpty(id)) {
            jmiLevelNote = jmiLevelNoteManager.getJmiLevelNote(id);
        } else {
            jmiLevelNote = new JmiLevelNote();
        }

        return jmiLevelNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiLevelNote jmiLevelNote = (JmiLevelNote) command;
        boolean isNew = (jmiLevelNote.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiLevelNote".equals(strAction)  ) {
		jmiLevelNoteManager.removeJmiLevelNote(jmiLevelNote.getId().toString());
		key="jmiLevelNote.delete";
	}else{
		jmiLevelNoteManager.saveJmiLevelNote(jmiLevelNote);
		key="jmiLevelNote.update";
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
