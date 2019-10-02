package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.mi.model.JmiEcmallNote;
import com.joymain.jecs.mi.service.JmiEcmallNoteManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiEcmallNoteFormController extends BaseFormController {
    private JmiEcmallNoteManager jmiEcmallNoteManager = null;

    public void setJmiEcmallNoteManager(JmiEcmallNoteManager jmiEcmallNoteManager) {
        this.jmiEcmallNoteManager = jmiEcmallNoteManager;
    }
    public JmiEcmallNoteFormController() {
        setCommandName("jmiEcmallNote");
        setCommandClass(JmiEcmallNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiEcmallNote jmiEcmallNote = null;

        if (!StringUtils.isEmpty(id)) {
            jmiEcmallNote = jmiEcmallNoteManager.getJmiEcmallNote(id);
        } else {
            jmiEcmallNote = new JmiEcmallNote();
        }

        return jmiEcmallNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiEcmallNote jmiEcmallNote = (JmiEcmallNote) command;
        boolean isNew = (jmiEcmallNote.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJmiEcmallNote".equals(strAction)  ) {
		jmiEcmallNoteManager.removeJmiEcmallNote(jmiEcmallNote.getId().toString());
		key="jmiEcmallNote.delete";
	}else{
		jmiEcmallNoteManager.saveJmiEcmallNote(jmiEcmallNote);
		key="jmiEcmallNote.update";
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
