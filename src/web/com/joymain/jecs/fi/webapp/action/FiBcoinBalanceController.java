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
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.service.FiBcoinBalanceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiBcoinBalanceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiBcoinBalanceController.class);
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;

    public void setFiBcoinBalanceManager(FiBcoinBalanceManager fiBcoinBalanceManager) {
        this.fiBcoinBalanceManager = fiBcoinBalanceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiBcoinBalance fiBcoinBalance = new FiBcoinBalance();
        // populate object with request parameters
        BeanUtils.populate(fiBcoinBalance, request.getParameterMap());

	//List fiBcoinBalances = fiBcoinBalanceManager.getFiBcoinBalances(fiBcoinBalance);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiBcoinBalanceListTable",request, 20);
        List fiBcoinBalances = fiBcoinBalanceManager.getFiBcoinBalancesByCrm(crm,pager);
        request.setAttribute("fiBcoinBalanceListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiBcoinBalanceList", Constants.FIBCOINBALANCE_LIST, fiBcoinBalances);
    }
}
