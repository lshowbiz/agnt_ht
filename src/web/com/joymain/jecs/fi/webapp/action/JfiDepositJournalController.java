package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiDepositJournal;
import com.joymain.jecs.fi.service.JfiDepositJournalManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiDepositJournalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiDepositJournalController.class);
    private JfiDepositJournalManager jfiDepositJournalManager = null;

    public void setJfiDepositJournalManager(JfiDepositJournalManager jfiDepositJournalManager) {
        this.jfiDepositJournalManager = jfiDepositJournalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        JfiDepositJournal jfiDepositJournal = new JfiDepositJournal();
      

        CommonRecord crm=RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
        Pager pager = new Pager("jfiDepositJournalListTable",request, 20);
        List jfiDepositJournals = null;
        String userCode=request.getParameter("userCode");
        if(userCode!=null){
        	  jfiDepositJournals =  jfiDepositJournalManager.getJfiDepositJournalsByCrm(crm,pager);

  			Map sumMap = this.jfiDepositJournalManager.getSumAmountByCrm(crm);
  			request.setAttribute("sumMap", sumMap);
        }
        
      
        request.setAttribute("jfiDepositJournalListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiDepositJournalList", "jfiDepositJournalList", jfiDepositJournals);
    }
}
