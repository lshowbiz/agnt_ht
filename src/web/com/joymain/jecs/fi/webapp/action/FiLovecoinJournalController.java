package com.joymain.jecs.fi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FiLovecoinJournal;
import com.joymain.jecs.fi.service.FiLovecoinJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 爱心积分明细查询
 * @author shiyh
 *
 */
public class FiLovecoinJournalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiLovecoinJournalController.class);
    private FiLovecoinJournalManager fiLovecoinJournalManager;

    public void setFiLovecoinJournalManager(FiLovecoinJournalManager fiLovecoinJournalManager) {
        this.fiLovecoinJournalManager = fiLovecoinJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String strAction = request.getParameter("strAction") != null ? request.getParameter("strAction") : "";
        if (strAction.equals("viewFiLovecoinJournal")) {
        	String journalId = request.getParameter("journalId");
        	FiLovecoinJournal fiLovecoinJournal= fiLovecoinJournalManager.getFiLovecoinJournal(journalId);
    		if(!StringUtils.isEmpty(fiLovecoinJournal.getRemark())){
    			fiLovecoinJournal.setRemark(StringUtils.replace(fiLovecoinJournal.getRemark(), "\n", "<br>"));
    		}
    		
    		if(!StringUtils.isEmpty(fiLovecoinJournal.getNotes())){
    			fiLovecoinJournal.setNotes(StringUtils.replace(fiLovecoinJournal.getNotes(), "\n", "<br>"));
    		}
    		request.setAttribute("fiLovecoinJournal", fiLovecoinJournal);
    		return new ModelAndView("fi/viewFiLovecoinJournals");
        }
        SysUser su = SessionLogin.getLoginUser(request);
        String userCode = su.getUserCode();
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        if("M".equals(su.getUserType())){
        	crm.addField("userCode", userCode);
        }
        Pager pager = new Pager("fiLovecoinJournalListTable", request, 20);
        List fiLovecoinJournals = fiLovecoinJournalManager.getFiLovecoinJournalsByCrm(crm, pager);
        if (fiLovecoinJournals != null) {
        	request.setAttribute("fiLovecoinJournalList", fiLovecoinJournals);
        }
        //根据数据重新设置分页数据
		request.setAttribute("fiLovecoinJournalListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("fi/fiLovecoinJournals");
    }
}
