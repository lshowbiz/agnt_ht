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
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.pd.service.PdCombinationDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdCombinationDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdCombinationDetailController.class);
    private PdCombinationDetailManager pdCombinationDetailManager = null;

    public void setPdCombinationDetailManager(PdCombinationDetailManager pdCombinationDetailManager) {
        this.pdCombinationDetailManager = pdCombinationDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdCombinationDetail pdCombinationDetail = new PdCombinationDetail();
        // populate object with request parameters
        BeanUtils.populate(pdCombinationDetail, request.getParameterMap());

	//List pdCombinationDetails = pdCombinationDetailManager.getPdCombinationDetails(pdCombinationDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdCombinationDetailListTable",request, 20);
        List pdCombinationDetails = pdCombinationDetailManager.getPdCombinationDetailsByCrm(crm,pager);
        request.setAttribute("pdCombinationDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdCombinationDetailList", Constants.PDCOMBINATIONDETAIL_LIST, pdCombinationDetails);
    }
}
