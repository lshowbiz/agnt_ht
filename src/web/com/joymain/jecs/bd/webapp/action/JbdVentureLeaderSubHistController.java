package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.bd.model.JbdVentureLeaderSubHist;
import com.joymain.jecs.bd.service.JbdVentureLeaderSubHistManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdVentureLeaderSubHistController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdVentureLeaderSubHistController.class);
    private JbdVentureLeaderSubHistManager jbdVentureLeaderSubHistManager = null;

    public void setJbdVentureLeaderSubHistManager(JbdVentureLeaderSubHistManager jbdVentureLeaderSubHistManager) {
        this.jbdVentureLeaderSubHistManager = jbdVentureLeaderSubHistManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdVentureLeaderSubHist jbdVentureLeaderSubHist = new JbdVentureLeaderSubHist();
        // populate object with request parameters
        BeanUtils.populate(jbdVentureLeaderSubHist, request.getParameterMap());

	//List jbdVentureLeaderSubHists = jbdVentureLeaderSubHistManager.getJbdVentureLeaderSubHists(jbdVentureLeaderSubHist);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdVentureLeaderSubHistListTable",request, 20);
        List jbdVentureLeaderSubHists = jbdVentureLeaderSubHistManager.getJbdVentureLeaderSubHistsByCrm(crm,pager);
        request.setAttribute("jbdVentureLeaderSubHistListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdVentureLeaderSubHistList", Constants.JBDVENTURELEADERSUBHIST_LIST, jbdVentureLeaderSubHists);
    }
}
