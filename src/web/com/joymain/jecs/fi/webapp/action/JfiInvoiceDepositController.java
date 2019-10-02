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
import com.joymain.jecs.fi.model.JfiInvoiceDeposit;
import com.joymain.jecs.fi.service.JfiInvoiceDepositManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiInvoiceDepositController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiInvoiceDepositController.class);
    private JfiInvoiceDepositManager jfiInvoiceDepositManager = null;

    public void setJfiInvoiceDepositManager(JfiInvoiceDepositManager jfiInvoiceDepositManager) {
        this.jfiInvoiceDepositManager = jfiInvoiceDepositManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiInvoiceDeposit jfiInvoiceDeposit = new JfiInvoiceDeposit();
        // populate object with request parameters
        BeanUtils.populate(jfiInvoiceDeposit, request.getParameterMap());

	//List jfiInvoiceDeposits = jfiInvoiceDepositManager.getJfiInvoiceDeposits(jfiInvoiceDeposit);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiInvoiceDepositListTable",request, 20);
        List jfiInvoiceDeposits = jfiInvoiceDepositManager.getJfiInvoiceDepositsByCrm(crm,pager);
        request.setAttribute("jfiInvoiceDepositListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiInvoiceDepositList", "jfiInvoiceDepositList", jfiInvoiceDeposits);
    }
}
