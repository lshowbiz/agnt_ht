package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiBankbookJournal;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiProductPointJournalFormController extends BaseFormController {
    private FiBankbookJournalManager fiBankbookJournalManager = null;

    public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager) {
        this.fiBankbookJournalManager = fiBankbookJournalManager;
    }
    public FiProductPointJournalFormController() {
        setCommandName("fiBankbookJournal");
        setCommandClass(FiBankbookJournal.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");
        FiBankbookJournal fiBankbookJournal = null;

        if (!StringUtils.isEmpty(journalId)) {
            fiBankbookJournal = fiBankbookJournalManager.getFiBankbookJournal(journalId);
        } else {
            fiBankbookJournal = new FiBankbookJournal();
        }

        return fiBankbookJournal;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiBankbookJournal fiBankbookJournal = (FiBankbookJournal) command;
        boolean isNew = (fiBankbookJournal.getJournalId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiBankbookJournal".equals(strAction)  ) {
		fiBankbookJournalManager.removeFiBankbookJournal(fiBankbookJournal.getJournalId().toString());
		key="fiBankbookJournal.delete";
	}else{
		fiBankbookJournalManager.saveFiBankbookJournal(fiBankbookJournal);
		key="fiBankbookJournal.update";
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
