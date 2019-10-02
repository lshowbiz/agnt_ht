package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiSecurityDepositJournal;
import com.joymain.jecs.fi.service.FiSecurityDepositJournalManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiSecurityDepositJournalFormController extends BaseFormController {
    private FiSecurityDepositJournalManager fiSecurityDepositJournalManager = null;

    public void setFiSecurityDepositJournalManager(FiSecurityDepositJournalManager fiSecurityDepositJournalManager) {
        this.fiSecurityDepositJournalManager = fiSecurityDepositJournalManager;
    }
    public FiSecurityDepositJournalFormController() {
        setCommandName("fiSecurityDepositJournal");
        setCommandClass(FiSecurityDepositJournal.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");
        FiSecurityDepositJournal fiSecurityDepositJournal = null;

        if (!StringUtils.isEmpty(journalId)) {
            fiSecurityDepositJournal = fiSecurityDepositJournalManager.getFiSecurityDepositJournal(journalId);
        } else {
            fiSecurityDepositJournal = new FiSecurityDepositJournal();
        }

        return fiSecurityDepositJournal;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiSecurityDepositJournal fiSecurityDepositJournal = (FiSecurityDepositJournal) command;
        boolean isNew = (fiSecurityDepositJournal.getJournalId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiSecurityDepositJournal".equals(strAction)  ) {
		fiSecurityDepositJournalManager.removeFiSecurityDepositJournal(fiSecurityDepositJournal.getJournalId().toString());
		key="fiSecurityDepositJournal.delete";
	}else{
		fiSecurityDepositJournalManager.saveFiSecurityDepositJournal(fiSecurityDepositJournal);
		key="fiSecurityDepositJournal.update";
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
