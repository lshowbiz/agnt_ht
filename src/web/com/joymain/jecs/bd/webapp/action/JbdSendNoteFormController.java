package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdSendNoteFormController extends BaseFormController {
    private JbdSendNoteManager jbdSendNoteManager = null;

    public void setJbdSendNoteManager(JbdSendNoteManager jbdSendNoteManager) {
        this.jbdSendNoteManager = jbdSendNoteManager;
    }
    public JbdSendNoteFormController() {
        setCommandName("jbdSendNote");
        setCommandClass(JbdSendNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSendNote jbdSendNote = null;

        if (!StringUtils.isEmpty(id)) {
            jbdSendNote = jbdSendNoteManager.getJbdSendNote(id);
        } else {
            jbdSendNote = new JbdSendNote();
        }

        return jbdSendNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdSendNote jbdSendNote = (JbdSendNote) command;
        boolean isNew = (jbdSendNote.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdSendNote".equals(strAction)  ) {
		jbdSendNoteManager.removeJbdSendNote(jbdSendNote.getId().toString());
		key="jbdSendNote.delete";
	}else{
		jbdSendNoteManager.saveJbdSendNote(jbdSendNote);
		key="jbdSendNote.update";
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
