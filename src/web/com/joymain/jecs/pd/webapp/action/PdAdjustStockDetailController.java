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
import com.joymain.jecs.pd.model.PdAdjustStockDetail;
import com.joymain.jecs.pd.service.PdAdjustStockDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdAdjustStockDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdAdjustStockDetailController.class);
    private PdAdjustStockDetailManager pdAdjustStockDetailManager = null;

    public void setPdAdjustStockDetailManager(PdAdjustStockDetailManager pdAdjustStockDetailManager) {
        this.pdAdjustStockDetailManager = pdAdjustStockDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdAdjustStockDetail pdAdjustStockDetail = new PdAdjustStockDetail();
        // populate object with request parameters
        BeanUtils.populate(pdAdjustStockDetail, request.getParameterMap());

	//List pdAdjustStockDetails = pdAdjustStockDetailManager.getPdAdjustStockDetails(pdAdjustStockDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdAdjustStockDetailListTable",request, 20);
        List pdAdjustStockDetails = pdAdjustStockDetailManager.getPdAdjustStockDetailsByCrm(crm,pager);
        request.setAttribute("pdAdjustStockDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdAdjustStockDetailList", Constants.PDADJUSTSTOCKDETAIL_LIST, pdAdjustStockDetails);
    }
}
