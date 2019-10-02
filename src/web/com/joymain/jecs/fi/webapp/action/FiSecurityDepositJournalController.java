package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FiSecurityDepositJournal;
import com.joymain.jecs.fi.service.FiSecurityDepositJournalManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiSecurityDepositJournalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiSecurityDepositJournalController.class);
    private FiSecurityDepositJournalManager fiSecurityDepositJournalManager = null;

    public void setFiSecurityDepositJournalManager(FiSecurityDepositJournalManager fiSecurityDepositJournalManager) {
        this.fiSecurityDepositJournalManager = fiSecurityDepositJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiSecurityDepositJournal fiSecurityDepositJournal = new FiSecurityDepositJournal();
        // populate object with request parameters
        BeanUtils.populate(fiSecurityDepositJournal, request.getParameterMap());

	//List fiSecurityDepositJournals = fiSecurityDepositJournalManager.getFiSecurityDepositJournals(fiSecurityDepositJournal);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiSecurityDepositJournalListTable",request, 20);
        List fiSecurityDepositJournals = fiSecurityDepositJournalManager.getFiSecurityDepositJournalsByCrm(crm,pager);
        request.setAttribute("fiSecurityDepositJournalListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiSecurityDepositJournalList", Constants.FISECURITYDEPOSITJOURNAL_LIST, fiSecurityDepositJournals);
    }
}
