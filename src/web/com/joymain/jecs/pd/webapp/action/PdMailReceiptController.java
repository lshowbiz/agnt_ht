package com.joymain.jecs.pd.webapp.action;

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
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.service.PdMailReceiptManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdMailReceiptController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdMailReceiptController.class);
    private PdMailReceiptManager pdMailReceiptManager = null;

    public void setPdMailReceiptManager(PdMailReceiptManager pdMailReceiptManager) {
        this.pdMailReceiptManager = pdMailReceiptManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdMailReceipt pdMailReceipt = new PdMailReceipt();
        // populate object with request parameters
        BeanUtils.populate(pdMailReceipt, request.getParameterMap());

	//List pdMailReceipts = pdMailReceiptManager.getPdMailReceipts(pdMailReceipt);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdMailReceiptListTable",request, 20);
        List pdMailReceipts = pdMailReceiptManager.getPdMailReceiptsByCrm(crm,pager);
        request.setAttribute("pdMailReceiptListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdMailReceiptList", "pdMailReceiptList", pdMailReceipts);
    }
}
