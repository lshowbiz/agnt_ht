package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdSendRecordNote;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdSendRecordNoteFormController extends BaseFormController {
    private JbdSendRecordNoteManager jbdSendRecordNoteManager = null;

    public void setJbdSendRecordNoteManager(JbdSendRecordNoteManager jbdSendRecordNoteManager) {
        this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
    }
    public JbdSendRecordNoteFormController() {
        setCommandName("jbdSendRecordNote");
        setCommandClass(JbdSendRecordNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSendRecordNote jbdSendRecordNote = null;

        if (!StringUtils.isEmpty(id)) {
            jbdSendRecordNote = jbdSendRecordNoteManager.getJbdSendRecordNote(id);
        } else {
            jbdSendRecordNote = new JbdSendRecordNote();
        }

        return jbdSendRecordNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdSendRecordNote jbdSendRecordNote = (JbdSendRecordNote) command;
        boolean isNew = (jbdSendRecordNote.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdSendRecordNote".equals(strAction)  ) {
		jbdSendRecordNoteManager.removeJbdSendRecordNote(jbdSendRecordNote.getId().toString());
		key="jbdSendRecordNote.delete";
	}else{
		jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
		key="jbdSendRecordNote.update";
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
