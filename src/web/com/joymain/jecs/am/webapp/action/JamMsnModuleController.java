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
import com.joymain.jecs.am.model.JamMsnModule;
import com.joymain.jecs.am.service.JamMsnModuleManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JamMsnModuleController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JamMsnModuleController.class);
    private JamMsnModuleManager jamMsnModuleManager = null;

    public void setJamMsnModuleManager(JamMsnModuleManager jamMsnModuleManager) {
        this.jamMsnModuleManager = jamMsnModuleManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String mtId=request.getParameter("mtId");
        request.setAttribute("mtId", mtId);
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jamMsnModuleListTable",request, 20);
        List jamMsnModules = jamMsnModuleManager.getJamMsnModulesByCrm(crm,pager);
        request.setAttribute("jamMsnModuleListTable_totalRows", pager.getTotalObjects());


        return new ModelAndView("am/jamMsnModuleList", Constants.JAMMSNMODULE_LIST, jamMsnModules);
    }
}
