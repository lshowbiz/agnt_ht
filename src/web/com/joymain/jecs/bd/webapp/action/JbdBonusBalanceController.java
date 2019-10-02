package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.service.JbdBonusBalanceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdBonusBalanceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdBonusBalanceController.class);
    private JbdBonusBalanceManager jbdBonusBalanceManager = null;

    public void setJbdBonusBalanceManager(JbdBonusBalanceManager jbdBonusBalanceManager) {
        this.jbdBonusBalanceManager = jbdBonusBalanceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdBonusBalance jbdBonusBalance = new JbdBonusBalance();
        // populate object with request parameters
        BeanUtils.populate(jbdBonusBalance, request.getParameterMap());

	//List jbdBonusBalances = jbdBonusBalanceManager.getJbdBonusBalances(jbdBonusBalance);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdBonusBalanceListTable",request, 20);
        List jbdBonusBalances = jbdBonusBalanceManager.getJbdBonusBalancesByCrm(crm,pager);
        request.setAttribute("jbdBonusBalanceListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdBonusBalanceList", Constants.JBDBONUSBALANCE_LIST, jbdBonusBalances);
    }
}
