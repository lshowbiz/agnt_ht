package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiFundbookJournal;
import com.joymain.jecs.fi.service.FiFundbookJournalManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiFundbookJournalFormController extends BaseFormController {
    private FiFundbookJournalManager fiFundbookJournalManager = null;

    public void setFiFundbookJournalManager(FiFundbookJournalManager fiFundbookJournalManager) {
        this.fiFundbookJournalManager = fiFundbookJournalManager;
    }
    public FiFundbookJournalFormController() {
        setCommandName("fiFundbookJournal");
        setCommandClass(FiFundbookJournal.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");
        FiFundbookJournal fiFundbookJournal = null;

        if (!StringUtils.isEmpty(journalId)) {
            fiFundbookJournal = fiFundbookJournalManager.getFiFundbookJournal(journalId);
        } else {
            fiFundbookJournal = new FiFundbookJournal();
        }

        return fiFundbookJournal;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiFundbookJournal fiFundbookJournal = (FiFundbookJournal) command;
        boolean isNew = (fiFundbookJournal.getJournalId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiFundbookJournal".equals(strAction)  ) {
		fiFundbookJournalManager.removeFiFundbookJournal(fiFundbookJournal.getJournalId().toString());
		key="fiFundbookJournal.delete";
	}else{
		fiFundbookJournalManager.saveFiFundbookJournal(fiFundbookJournal);
		key="fiFundbookJournal.update";
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
