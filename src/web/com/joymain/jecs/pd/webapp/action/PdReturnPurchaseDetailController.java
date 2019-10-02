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
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdReturnPurchaseDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdReturnPurchaseDetailController.class);
    private PdReturnPurchaseDetailManager pdReturnPurchaseDetailManager = null;

    public void setPdReturnPurchaseDetailManager(PdReturnPurchaseDetailManager pdReturnPurchaseDetailManager) {
        this.pdReturnPurchaseDetailManager = pdReturnPurchaseDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdReturnPurchaseDetail pdReturnPurchaseDetail = new PdReturnPurchaseDetail();
        // populate object with request parameters
        BeanUtils.populate(pdReturnPurchaseDetail, request.getParameterMap());

	//List pdReturnPurchaseDetails = pdReturnPurchaseDetailManager.getPdReturnPurchaseDetails(pdReturnPurchaseDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdReturnPurchaseDetailListTable",request, 20);
        List pdReturnPurchaseDetails = pdReturnPurchaseDetailManager.getPdReturnPurchaseDetailsByCrm(crm,pager);
        request.setAttribute("pdReturnPurchaseDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdReturnPurchaseDetailList", Constants.PDRETURNPURCHASEDETAIL_LIST, pdReturnPurchaseDetails);
    }
}
