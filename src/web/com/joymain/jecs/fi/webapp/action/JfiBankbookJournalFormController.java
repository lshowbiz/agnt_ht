package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiBankbookJournalFormController extends BaseFormController {
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;

    public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
        this.jfiBankbookJournalManager = jfiBankbookJournalManager;
    }
    public JfiBankbookJournalFormController() {
        setCommandName("jfiBankbookJournal");
        setCommandClass(JfiBankbookJournal.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");
        JfiBankbookJournal jfiBankbookJournal = null;

        if (!StringUtils.isEmpty(journalId)) {
            jfiBankbookJournal = jfiBankbookJournalManager.getJfiBankbookJournal(journalId);
        } else {
            jfiBankbookJournal = new JfiBankbookJournal();
        }

        return jfiBankbookJournal;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiBankbookJournal jfiBankbookJournal = (JfiBankbookJournal) command;
        boolean isNew = (jfiBankbookJournal.getJournalId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiBankbookJournal".equals(strAction)  ) {
		jfiBankbookJournalManager.removeJfiBankbookJournal(jfiBankbookJournal.getJournalId().toString());
		key="jfiBankbookJournal.delete";
	}else{
		jfiBankbookJournalManager.saveJfiBankbookJournal(jfiBankbookJournal);
		key="jfiBankbookJournal.update";
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
