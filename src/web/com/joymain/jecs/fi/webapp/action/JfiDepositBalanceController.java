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
import com.joymain.jecs.fi.model.JfiDepositBalance;
import com.joymain.jecs.fi.service.JfiDepositBalanceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiDepositBalanceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiDepositBalanceController.class);
    private JfiDepositBalanceManager jfiDepositBalanceManager = null;

    public void setJfiDepositBalanceManager(JfiDepositBalanceManager jfiDepositBalanceManager) {
        this.jfiDepositBalanceManager = jfiDepositBalanceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiDepositBalance jfiDepositBalance = new JfiDepositBalance();
        // populate object with request parameters
        BeanUtils.populate(jfiDepositBalance, request.getParameterMap());

	//List jfiDepositBalances = jfiDepositBalanceManager.getJfiDepositBalances(jfiDepositBalance);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiDepositBalanceListTable",request, 20);
        List jfiDepositBalances = jfiDepositBalanceManager.getJfiDepositBalancesByCrm(crm,pager);
        request.setAttribute("jfiDepositBalanceListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiDepositBalanceList", "jfiDepositBalanceList", jfiDepositBalances);
    }
}
