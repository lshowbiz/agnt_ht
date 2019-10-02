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
import com.joymain.jecs.vt.model.VtVotePow;
import com.joymain.jecs.vt.service.VtVotePowManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class VtVotePowController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(VtVotePowController.class);
    private VtVotePowManager vtVotePowManager = null;

    public void setVtVotePowManager(VtVotePowManager vtVotePowManager) {
        this.vtVotePowManager = vtVotePowManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        VtVotePow vtVotePow = new VtVotePow();
        // populate object with request parameters
        BeanUtils.populate(vtVotePow, request.getParameterMap());

	//List vtVotePows = vtVotePowManager.getVtVotePows(vtVotePow);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("vtVotePowListTable",request, 20);
        List vtVotePows = vtVotePowManager.getVtVotePowsByCrm(crm,pager);
        request.setAttribute("vtVotePowListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("vt/vtVotePowList", Constants.VTVOTEPOW_LIST, vtVotePows);
    }
}
