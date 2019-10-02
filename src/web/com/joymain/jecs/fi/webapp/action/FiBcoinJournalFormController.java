package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiBcoinJournal;
import com.joymain.jecs.fi.service.FiBcoinJournalManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiBcoinJournalFormController extends BaseFormController {
    private FiBcoinJournalManager fiBcoinJournalManager = null;

    public void setFiBcoinJournalManager(FiBcoinJournalManager fiBcoinJournalManager) {
        this.fiBcoinJournalManager = fiBcoinJournalManager;
    }
    public FiBcoinJournalFormController() {
        setCommandName("fiBcoinJournal");
        setCommandClass(FiBcoinJournal.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");
        FiBcoinJournal fiBcoinJournal = null;

        if (!StringUtils.isEmpty(journalId)) {
            fiBcoinJournal = fiBcoinJournalManager.getFiBcoinJournal(journalId);
        } else {
            fiBcoinJournal = new FiBcoinJournal();
        }

        return fiBcoinJournal;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiBcoinJournal fiBcoinJournal = (FiBcoinJournal) command;
        boolean isNew = (fiBcoinJournal.getJournalId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiBcoinJournal".equals(strAction)  ) {
		fiBcoinJournalManager.removeFiBcoinJournal(fiBcoinJournal.getJournalId().toString());
		key="fiBcoinJournal.delete";
	}else{
		fiBcoinJournalManager.saveFiBcoinJournal(fiBcoinJournal);
		key="fiBcoinJournal.update";
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
