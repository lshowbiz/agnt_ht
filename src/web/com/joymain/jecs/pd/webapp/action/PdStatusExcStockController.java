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
import com.joymain.jecs.pd.model.PdStatusExcStock;
import com.joymain.jecs.pd.service.PdStatusExcStockManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdStatusExcStockController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdStatusExcStockController.class);
    private PdStatusExcStockManager pdStatusExcStockManager = null;

    public void setPdStatusExcStockManager(PdStatusExcStockManager pdStatusExcStockManager) {
        this.pdStatusExcStockManager = pdStatusExcStockManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        super.initPmProductMap(request);
        PdStatusExcStock pdStatusExcStock = new PdStatusExcStock();
        // populate object with request parameters
        BeanUtils.populate(pdStatusExcStock, request.getParameterMap());

	//List pdStatusExcStocks = pdStatusExcStockManager.getPdStatusExcStocks(pdStatusExcStock);
	/**** auto pagination ***/
        CommonRecord crm=super.initCommonRecord(request);
        Pager pager = new Pager("pdStatusExcStockListTable",request, 20);
        List pdStatusExcStocks = pdStatusExcStockManager.getPdStatusExcStocksByCrm(crm,pager);
        request.setAttribute("pdStatusExcStockListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdStatusExcStockList", Constants.PDSTATUSEXCSTOCK_LIST, pdStatusExcStocks);
    }
}
