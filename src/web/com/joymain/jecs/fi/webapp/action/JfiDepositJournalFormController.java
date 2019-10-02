package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiDepositJournal;
import com.joymain.jecs.fi.service.JfiDepositJournalManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiDepositJournalFormController extends BaseFormController {
    private JfiDepositJournalManager jfiDepositJournalManager = null;

    public void setJfiDepositJournalManager(JfiDepositJournalManager jfiDepositJournalManager) {
        this.jfiDepositJournalManager = jfiDepositJournalManager;
    }
    public JfiDepositJournalFormController() {
        setCommandName("jfiDepositJournal");
        setCommandClass(JfiDepositJournal.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");
        JfiDepositJournal jfiDepositJournal = null;

        if (!StringUtils.isEmpty(journalId)) {
            jfiDepositJournal = jfiDepositJournalManager.getJfiDepositJournal(journalId);
        } else {
            jfiDepositJournal = new JfiDepositJournal();
        }

        return jfiDepositJournal;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiDepositJournal jfiDepositJournal = (JfiDepositJournal) command;
        boolean isNew = (jfiDepositJournal.getJournalId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiDepositJournal".equals(strAction)  ) {
		jfiDepositJournalManager.removeJfiDepositJournal(jfiDepositJournal.getJournalId().toString());
		key="jfiDepositJournal.delete";
	}else{
		jfiDepositJournalManager.saveJfiDepositJournal(jfiDepositJournal);
		key="jfiDepositJournal.update";
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
