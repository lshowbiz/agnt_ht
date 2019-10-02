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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.BillAccount;
import com.joymain.jecs.fi.service.BillAccountManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BillAccountController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BillAccountController.class);
    private BillAccountManager billAccountManager = null;

    public void setBillAccountManager(BillAccountManager billAccountManager) {
        this.billAccountManager = billAccountManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        String baCode=request.getParameter("baCode");
        Pager pager = new Pager("billAccountListTable",request, 20);
        List billAccounts=null;
        if(!StringUtil.isEmpty(baCode)){
            billAccounts = billAccountManager.getBillAccountsByCrm(crm,pager);
        }
        request.setAttribute("billAccountListTable_totalRows", pager.getTotalObjects());



        return new ModelAndView("fi/billAccountList", Constants.BILLACCOUNT_LIST, billAccounts);
    }
}
