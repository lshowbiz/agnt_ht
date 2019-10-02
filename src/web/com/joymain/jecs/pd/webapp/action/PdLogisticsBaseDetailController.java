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
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.service.PdLogisticsBaseDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdLogisticsBaseDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdLogisticsBaseDetailController.class);
    private PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager = null;

    public void setPdLogisticsBaseDetailManager(PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager) {
        this.pdLogisticsBaseDetailManager = pdLogisticsBaseDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdLogisticsBaseDetail pdLogisticsBaseDetail = new PdLogisticsBaseDetail();
        // populate object with request parameters
        BeanUtils.populate(pdLogisticsBaseDetail, request.getParameterMap());

	//List pdLogisticsBaseDetails = pdLogisticsBaseDetailManager.getPdLogisticsBaseDetails(pdLogisticsBaseDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdLogisticsBaseDetailListTable",request, 20);
        List pdLogisticsBaseDetails = pdLogisticsBaseDetailManager.getPdLogisticsBaseDetailsByCrm(crm,pager);
        request.setAttribute("pdLogisticsBaseDetailListTable_totalRows", pager.getTotalObjects());
        /*****/
  
        //"Constants.PDLOGISTICSBASEDETAIL_LIST"
        return new ModelAndView("pd/pdLogisticsBaseDetailList", "pdLogisticsBaseDetailList", pdLogisticsBaseDetails);
    }
}
