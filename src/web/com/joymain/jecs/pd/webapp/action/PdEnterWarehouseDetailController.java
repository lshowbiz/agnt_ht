package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdEnterWarehouseDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdEnterWarehouseDetailController.class);
    private PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager = null;
    
    public void setPdEnterWarehouseDetailManager(PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager) {
        this.pdEnterWarehouseDetailManager = pdEnterWarehouseDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String ewNo = request.getParameter("ewNo");
        
        PdEnterWarehouseDetail pdEnterWarehouseDetail = new PdEnterWarehouseDetail();
        // populate object with request parameters
        BeanUtils.populate(pdEnterWarehouseDetail, request.getParameterMap());
        
        CommonRecord crm = initCommonRecord(request);//����ѯ���д��session
        crm.setValue("ewNo", ewNo);
        Pager pager = new Pager("pdEnterWarehouseDetailListTable",request, 20);
        List pdEnterWarehouseDetails = pdEnterWarehouseDetailManager.getPdEnterWarehouseDetailsByCrm(crm, pager);
        
        request.setAttribute("ewNo", ewNo);
//        List pdEnterWarehouseDetails = pdEnterWarehouseDetailManager.getPdEnterWarehouseDetails(pdEnterWarehouseDetail);

        return new ModelAndView("pd/pdEnterWarehouseDetailList", Constants.PDENTERWAREHOUSEDETAIL_LIST, pdEnterWarehouseDetails);
    }
}
