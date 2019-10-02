package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiCcoinJournal;
import com.joymain.jecs.fi.service.FiCcoinJournalManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiCcoinJournalFormController extends BaseFormController {
    private FiCcoinJournalManager fiCcoinJournalManager = null;

    public void setFiCcoinJournalManager(FiCcoinJournalManager fiCcoinJournalManager) {
        this.fiCcoinJournalManager = fiCcoinJournalManager;
    }
    public FiCcoinJournalFormController() {
        setCommandName("fiCcoinJournal");
        setCommandClass(FiCcoinJournal.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");
        FiCcoinJournal fiCcoinJournal = null;

        if (!StringUtils.isEmpty(journalId)) {
            fiCcoinJournal = fiCcoinJournalManager.getFiCcoinJournal(journalId);
        } else {
            fiCcoinJournal = new FiCcoinJournal();
        }

        return fiCcoinJournal;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiCcoinJournal fiCcoinJournal = (FiCcoinJournal) command;
        boolean isNew = (fiCcoinJournal.getJournalId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiCcoinJournal".equals(strAction)  ) {
		fiCcoinJournalManager.removeFiCcoinJournal(fiCcoinJournal.getJournalId().toString());
		key="fiCcoinJournal.delete";
	}else{
		fiCcoinJournalManager.saveFiCcoinJournal(fiCcoinJournal);
		key="fiCcoinJournal.update";
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
