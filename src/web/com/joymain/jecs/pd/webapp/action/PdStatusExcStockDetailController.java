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
import com.joymain.jecs.pd.model.PdStatusExcStockDetail;
import com.joymain.jecs.pd.service.PdStatusExcStockDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdStatusExcStockDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdStatusExcStockDetailController.class);
    private PdStatusExcStockDetailManager pdStatusExcStockDetailManager = null;

    public void setPdStatusExcStockDetailManager(PdStatusExcStockDetailManager pdStatusExcStockDetailManager) {
        this.pdStatusExcStockDetailManager = pdStatusExcStockDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdStatusExcStockDetail pdStatusExcStockDetail = new PdStatusExcStockDetail();
        // populate object with request parameters
        BeanUtils.populate(pdStatusExcStockDetail, request.getParameterMap());

	//List pdStatusExcStockDetails = pdStatusExcStockDetailManager.getPdStatusExcStockDetails(pdStatusExcStockDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdStatusExcStockDetailListTable",request, 20);
        List pdStatusExcStockDetails = pdStatusExcStockDetailManager.getPdStatusExcStockDetailsByCrm(crm,pager);
        request.setAttribute("pdStatusExcStockDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdStatusExcStockDetailList", Constants.PDSTATUSEXCSTOCKDETAIL_LIST, pdStatusExcStockDetails);
    }
}
