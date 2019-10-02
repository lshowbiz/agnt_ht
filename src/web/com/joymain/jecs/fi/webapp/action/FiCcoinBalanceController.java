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
import com.joymain.jecs.fi.model.FiCcoinBalance;
import com.joymain.jecs.fi.service.FiCcoinBalanceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiCcoinBalanceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiCcoinBalanceController.class);
    private FiCcoinBalanceManager fiCcoinBalanceManager = null;

    public void setFiCcoinBalanceManager(FiCcoinBalanceManager fiCcoinBalanceManager) {
        this.fiCcoinBalanceManager = fiCcoinBalanceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiCcoinBalance fiCcoinBalance = new FiCcoinBalance();
        // populate object with request parameters
        BeanUtils.populate(fiCcoinBalance, request.getParameterMap());

	//List fiCcoinBalances = fiCcoinBalanceManager.getFiCcoinBalances(fiCcoinBalance);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiCcoinBalanceListTable",request, 20);
        List fiCcoinBalances = fiCcoinBalanceManager.getFiCcoinBalancesByCrm(crm,pager);
        request.setAttribute("fiCcoinBalanceListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiCcoinBalanceList", Constants.FICCOINBALANCE_LIST, fiCcoinBalances);
    }
}
