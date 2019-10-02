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
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdFlitWarehouseDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdFlitWarehouseDetailController.class);
    private PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager = null;

    public void setPdFlitWarehouseDetailManager(PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager) {
        this.pdFlitWarehouseDetailManager = pdFlitWarehouseDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdFlitWarehouseDetail pdFlitWarehouseDetail = new PdFlitWarehouseDetail();
        // populate object with request parameters
        BeanUtils.populate(pdFlitWarehouseDetail, request.getParameterMap());

	//List pdFlitWarehouseDetails = pdFlitWarehouseDetailManager.getPdFlitWarehouseDetails(pdFlitWarehouseDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdFlitWarehouseDetailListTable",request, 20);
        List pdFlitWarehouseDetails = pdFlitWarehouseDetailManager.getPdFlitWarehouseDetailsByCrm(crm,pager);
        request.setAttribute("pdFlitWarehouseDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdFlitWarehouseDetailList", Constants.PDFLITWAREHOUSEDETAIL_LIST, pdFlitWarehouseDetails);
    }
}
