package com.joymain.jecs.fi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FiProductPointJournal;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.webapp.action.BaseController;
/**
 * 查看流水账条目
 * @author Alvin
 *
 */
public class FiViewProductPointJournalController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(FiViewProductPointJournalController.class);
	private FiBankbookJournalManager fiBankbookJournalManager = null;

	public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String journalId = request.getParameter("journalId");
		FiProductPointJournal fiProductPointJournal=fiBankbookJournalManager.getFiProductPointJournal(journalId);
		if(!StringUtils.isEmpty(fiProductPointJournal.getRemark())){
			fiProductPointJournal.setRemark(StringUtils.replace(fiProductPointJournal.getRemark(), "\n", "<br>"));
		}
		
		if(!StringUtils.isEmpty(fiProductPointJournal.getNotes())){
			fiProductPointJournal.setNotes(StringUtils.replace(fiProductPointJournal.getNotes(), "\n", "<br>"));
		}

		return new ModelAndView("fi/fiViewProductPointJournal", "fiBankbookJournal", fiProductPointJournal);
	}
}
