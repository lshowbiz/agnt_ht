package com.joymain.jecs.am.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.service.InwDepartCompetenceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwDepartCompetenceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwDepartCompetenceController.class);
    private InwDepartCompetenceManager inwDepartCompetenceManager = null;

    public void setInwDepartCompetenceManager(InwDepartCompetenceManager inwDepartCompetenceManager) {
        this.inwDepartCompetenceManager = inwDepartCompetenceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

	    CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("inwDepartCompetenceListTable",request, 20);
        List inwDepartCompetences = inwDepartCompetenceManager.getInwDepartCompetencesByCrm(crm,pager);
        request.setAttribute("inwDepartCompetenceListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("am/inwDepartCompetenceList", Constants.INWDEPARTCOMPETENCE_LIST, inwDepartCompetences);
    }
}
