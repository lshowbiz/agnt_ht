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
import com.joymain.jecs.fi.model.FiPayAccount;
import com.joymain.jecs.fi.service.FiPayAccountManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiPayAccountController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiPayAccountController.class);
    private FiPayAccountManager fiPayAccountManager = null;

    public void setFiPayAccountManager(FiPayAccountManager fiPayAccountManager) {
        this.fiPayAccountManager = fiPayAccountManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiPayAccount fiPayAccount = new FiPayAccount();
        // populate object with request parameters
        BeanUtils.populate(fiPayAccount, request.getParameterMap());

	//List fiPayAccounts = fiPayAccountManager.getFiPayAccounts(fiPayAccount);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiPayAccountListTable",request, 20);
        List fiPayAccounts = fiPayAccountManager.getFiPayAccountsByCrm(crm,pager);
        request.setAttribute("fiPayAccountListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiPayAccountList", "fiPayAccountList", fiPayAccounts);
    }
}
