package com.joymain.jecs.pr.webapp.action;

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
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.service.JprRefundListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JprRefundListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JprRefundListController.class);
    private JprRefundListManager jprRefundListManager = null;

    public void setJprRefundListManager(JprRefundListManager jprRefundListManager) {
        this.jprRefundListManager = jprRefundListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JprRefundList jprRefundList = new JprRefundList();
        // populate object with request parameters
        BeanUtils.populate(jprRefundList, request.getParameterMap());

	//List jprRefundLists = jprRefundListManager.getJprRefundLists(jprRefundList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jprRefundListListTable",request, 20);
        List jprRefundLists = jprRefundListManager.getJprRefundListsByCrm(crm,pager);
        request.setAttribute("jprRefundListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pr/jprRefundListList", Constants.JPRREFUNDLIST_LIST, jprRefundLists);
    }
}
