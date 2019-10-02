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
import com.joymain.jecs.pd.model.PdWarehouseFrozenBatch;
import com.joymain.jecs.pd.service.PdWarehouseFrozenBatchManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdWarehouseFrozenBatchController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdWarehouseFrozenBatchController.class);
    private PdWarehouseFrozenBatchManager pdWarehouseFrozenBatchManager = null;

    public void setPdWarehouseFrozenBatchManager(PdWarehouseFrozenBatchManager pdWarehouseFrozenBatchManager) {
        this.pdWarehouseFrozenBatchManager = pdWarehouseFrozenBatchManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdWarehouseFrozenBatch pdWarehouseFrozenBatch = new PdWarehouseFrozenBatch();
        // populate object with request parameters
        BeanUtils.populate(pdWarehouseFrozenBatch, request.getParameterMap());

	//List pdWarehouseFrozenBatchs = pdWarehouseFrozenBatchManager.getPdWarehouseFrozenBatchs(pdWarehouseFrozenBatch);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdWarehouseFrozenBatchListTable",request, 20);
        List pdWarehouseFrozenBatchs = pdWarehouseFrozenBatchManager.getPdWarehouseFrozenBatchsByCrm(crm,pager);
        request.setAttribute("pdWarehouseFrozenBatchListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdWarehouseFrozenBatchList", Constants.PDWAREHOUSEFROZENBATCH_LIST, pdWarehouseFrozenBatchs);
    }
}
