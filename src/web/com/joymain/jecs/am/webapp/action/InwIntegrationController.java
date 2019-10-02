package com.joymain.jecs.am.webapp.action;

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
import com.joymain.jecs.am.model.InwIntegration;
import com.joymain.jecs.am.service.InwIntegrationManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwIntegrationController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwIntegrationController.class);
    private InwIntegrationManager inwIntegrationManager = null;

    public void setInwIntegrationManager(InwIntegrationManager inwIntegrationManager) {
        this.inwIntegrationManager = inwIntegrationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        InwIntegration inwIntegration = new InwIntegration();
        // populate object with request parameters
        BeanUtils.populate(inwIntegration, request.getParameterMap());

	//List inwIntegrations = inwIntegrationManager.getInwIntegrations(inwIntegration);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("inwIntegrationListTable",request, 20);
        List inwIntegrations = inwIntegrationManager.getInwIntegrationsByCrm(crm,pager);
        request.setAttribute("inwIntegrationListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("am/inwIntegrationList", "inwIntegrationList", inwIntegrations);
    }
}
