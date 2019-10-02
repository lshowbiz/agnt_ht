package com.joymain.jecs.fi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.webapp.action.BaseController;
/**
 * 查看流水账条目
 * @author Alvin
 *
 */
public class JfiViewBankbookJournalController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiViewBankbookJournalController.class);
	private JfiBankbookJournalManager jfiBankbookJournalManager = null;

	public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String journalId = request.getParameter("journalId");
		JfiBankbookJournal jfiBankbookJournal=jfiBankbookJournalManager.getJfiBankbookJournal(journalId);
		if(!StringUtils.isEmpty(jfiBankbookJournal.getRemark())){
			jfiBankbookJournal.setRemark(StringUtils.replace(jfiBankbookJournal.getRemark(), "\n", "<br>"));
		}
		
		if(!StringUtils.isEmpty(jfiBankbookJournal.getNotes())){
			jfiBankbookJournal.setNotes(StringUtils.replace(jfiBankbookJournal.getNotes(), "\n", "<br>"));
		}

		return new ModelAndView("fi/jfiViewBankbookJournal", "jfiBankbookJournal", jfiBankbookJournal);
	}
}
