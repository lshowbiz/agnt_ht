package com.joymain.jecs.am.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.am.model.InwIntegrationTotal;
import com.joymain.jecs.am.service.InwIntegrationTotalManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwIntegrationTotalController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwIntegrationTotalController.class);
    private InwIntegrationTotalManager inwIntegrationTotalManager = null;

    public void setInwIntegrationTotalManager(InwIntegrationTotalManager inwIntegrationTotalManager) {
        this.inwIntegrationTotalManager = inwIntegrationTotalManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        InwIntegrationTotal inwIntegrationTotal = new InwIntegrationTotal();
        // populate object with request parameters
        BeanUtils.populate(inwIntegrationTotal, request.getParameterMap());

	//List inwIntegrationTotals = inwIntegrationTotalManager.getInwIntegrationTotals(inwIntegrationTotal);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("inwIntegrationTotalListTable",request, 20);
        List inwIntegrationTotals = inwIntegrationTotalManager.getInwIntegrationTotalsByCrm(crm,pager);
        request.setAttribute("inwIntegrationTotalListTable_totalRows", pager.getTotalObjects());
        /*****/
        
        request.setCharacterEncoding("UTF-8");
        
        return new ModelAndView("am/inwIntegrationTotalList", "inwIntegrationTotalList", inwIntegrationTotals);
    }
}
