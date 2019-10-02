package com.joymain.jecs.fi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FiCcoinJournal;
import com.joymain.jecs.fi.service.FiCcoinJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class FiCcoinJournalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiCcoinJournalController.class);
    private FiCcoinJournalManager fiCcoinJournalManager = null;

    public void setFiCcoinJournalManager(FiCcoinJournalManager fiCcoinJournalManager) {
        this.fiCcoinJournalManager = fiCcoinJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String strAction = request.getParameter("strAction") != null ? request.getParameter("strAction") : "";
        if (strAction.equals("viewFiCcoinJournal")) {
        	String journalId = request.getParameter("journalId");
        	FiCcoinJournal fiCcoinJournal= fiCcoinJournalManager.getFiCcoinJournal(journalId);
    		if(!StringUtils.isEmpty(fiCcoinJournal.getRemark())){
    			fiCcoinJournal.setRemark(StringUtils.replace(fiCcoinJournal.getRemark(), "\n", "<br>"));
    		}
    		
    		if(!StringUtils.isEmpty(fiCcoinJournal.getNotes())){
    			fiCcoinJournal.setNotes(StringUtils.replace(fiCcoinJournal.getNotes(), "\n", "<br>"));
    		}
    		request.setAttribute("fiCcoinJournal", fiCcoinJournal);
    		return new ModelAndView("fi/viewFiCcoinJournals");
        }
        SysUser su = SessionLogin.getLoginUser(request);
        String userCode = su.getUserCode();
        CommonRecord crm=RequestUtil.toCommonRecord(request);
//        crm.addField("userCode", userCode);
        Pager pager = new Pager("fiCcoinJournalListTable", request, 20);
        List fiCcoinJournals = fiCcoinJournalManager.getFiCcoinJournalsByCrm(crm, pager);
        if (fiCcoinJournals != null) {
        	request.setAttribute("fiCcoinJournalList", fiCcoinJournals);
        }
        //根据数据重新设置分页数据
		request.setAttribute("fiCcoinJournalListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/fiCcoinJournalList");
    }
}
