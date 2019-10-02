package com.joymain.jecs.vt.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.vt.model.VtVoteNote;
import com.joymain.jecs.vt.service.VtVoteNoteManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class VtVoteNoteFormController extends BaseFormController {
    private VtVoteNoteManager vtVoteNoteManager = null;

    public void setVtVoteNoteManager(VtVoteNoteManager vtVoteNoteManager) {
        this.vtVoteNoteManager = vtVoteNoteManager;
    }
    public VtVoteNoteFormController() {
        setCommandName("vtVoteNote");
        setCommandClass(VtVoteNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String vnId = request.getParameter("vnId");
        VtVoteNote vtVoteNote = null;

        if (!StringUtils.isEmpty(vnId)) {
            vtVoteNote = vtVoteNoteManager.getVtVoteNote(vnId);
        } else {
            vtVoteNote = new VtVoteNote();
        }

        return vtVoteNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        VtVoteNote vtVoteNote = (VtVoteNote) command;
        boolean isNew = (vtVoteNote.getVnId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteVtVoteNote".equals(strAction)  ) {
		vtVoteNoteManager.removeVtVoteNote(vtVoteNote.getVnId().toString());
		key="vtVoteNote.delete";
	}else{
		vtVoteNoteManager.saveVtVoteNote(vtVoteNote);
		key="vtVoteNote.update";
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
