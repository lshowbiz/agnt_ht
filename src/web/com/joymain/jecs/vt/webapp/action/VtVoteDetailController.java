package com.joymain.jecs.vt.webapp.action;

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
import com.joymain.jecs.vt.model.VtVoteDetail;
import com.joymain.jecs.vt.service.VtVoteDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class VtVoteDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(VtVoteDetailController.class);
    private VtVoteDetailManager vtVoteDetailManager = null;

    public void setVtVoteDetailManager(VtVoteDetailManager vtVoteDetailManager) {
        this.vtVoteDetailManager = vtVoteDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        VtVoteDetail vtVoteDetail = new VtVoteDetail();
        // populate object with request parameters
        BeanUtils.populate(vtVoteDetail, request.getParameterMap());

	//List vtVoteDetails = vtVoteDetailManager.getVtVoteDetails(vtVoteDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("vtVoteDetailListTable",request, 20);
        List vtVoteDetails = vtVoteDetailManager.getVtVoteDetailsByCrm(crm,pager);
        request.setAttribute("vtVoteDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("vt/vtVoteDetailList", Constants.VTVOTEDETAIL_LIST, vtVoteDetails);
    }
}
