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
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.service.PdSendInfoDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdSendInfoDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdSendInfoDetailController.class);
    private PdSendInfoDetailManager pdSendInfoDetailManager = null;

    public void setPdSendInfoDetailManager(PdSendInfoDetailManager pdSendInfoDetailManager) {
        this.pdSendInfoDetailManager = pdSendInfoDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
        // populate object with request parameters
        BeanUtils.populate(pdSendInfoDetail, request.getParameterMap());

	//List pdSendInfoDetails = pdSendInfoDetailManager.getPdSendInfoDetails(pdSendInfoDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdSendInfoDetailListTable",request, 20);
        List pdSendInfoDetails = pdSendInfoDetailManager.getPdSendInfoDetailsByCrm(crm,pager);
        request.setAttribute("pdSendInfoDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdSendInfoDetailList", Constants.PDSENDINFODETAIL_LIST, pdSendInfoDetails);
    }
}
