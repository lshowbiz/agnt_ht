package com.joymain.jecs.am.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.am.service.InwDepartPersonManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwDepartPersonController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwDepartPersonController.class);
    private InwDepartPersonManager inwDepartPersonManager = null;

    public void setInwDepartPersonManager(InwDepartPersonManager inwDepartPersonManager) {
        this.inwDepartPersonManager = inwDepartPersonManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

	    CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("inwDepartPersonListTable",request, 20);
        List inwDepartPersons = inwDepartPersonManager.getInwDepartPersonsByCrm(crm,pager);
        request.setAttribute("inwDepartPersonListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("am/inwDepartPersonList", "inwDepartPersonList", inwDepartPersons);
        
    }
    
}
