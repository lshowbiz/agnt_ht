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
import com.joymain.jecs.pd.model.PdCheckstockOrderDetail;
import com.joymain.jecs.pd.service.PdCheckstockOrderDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdCheckstockOrderDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdCheckstockOrderDetailController.class);
    private PdCheckstockOrderDetailManager pdCheckstockOrderDetailManager = null;

    public void setPdCheckstockOrderDetailManager(PdCheckstockOrderDetailManager pdCheckstockOrderDetailManager) {
        this.pdCheckstockOrderDetailManager = pdCheckstockOrderDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdCheckstockOrderDetail pdCheckstockOrderDetail = new PdCheckstockOrderDetail();
        // populate object with request parameters
        BeanUtils.populate(pdCheckstockOrderDetail, request.getParameterMap());

	//List pdCheckstockOrderDetails = pdCheckstockOrderDetailManager.getPdCheckstockOrderDetails(pdCheckstockOrderDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdCheckstockOrderDetailListTable",request, 20);
        List pdCheckstockOrderDetails = pdCheckstockOrderDetailManager.getPdCheckstockOrderDetailsByCrm(crm,pager);
        request.setAttribute("pdCheckstockOrderDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdCheckstockOrderDetailList", Constants.PDCHECKSTOCKORDERDETAIL_LIST, pdCheckstockOrderDetails);
    }
}
