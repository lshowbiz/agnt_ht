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
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.service.JbdSummaryListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSummaryListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSummaryListController.class);
    private JbdSummaryListManager jbdSummaryListManager = null;

    public void setJbdSummaryListManager(JbdSummaryListManager jbdSummaryListManager) {
        this.jbdSummaryListManager = jbdSummaryListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdSummaryList jbdSummaryList = new JbdSummaryList();
        // populate object with request parameters
        BeanUtils.populate(jbdSummaryList, request.getParameterMap());

	//List jbdSummaryLists = jbdSummaryListManager.getJbdSummaryLists(jbdSummaryList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSummaryListListTable",request, 20);
        List jbdSummaryLists = jbdSummaryListManager.getJbdSummaryListsByCrm(crm,pager);
        request.setAttribute("jbdSummaryListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdSummaryListList", Constants.JBDSUMMARYLIST_LIST, jbdSummaryLists);
    }
}
