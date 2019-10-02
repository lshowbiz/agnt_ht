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
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.service.JamMsnTypeManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JamMsnTypeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JamMsnTypeController.class);
    private JamMsnTypeManager jamMsnTypeManager = null;

    public void setJamMsnTypeManager(JamMsnTypeManager jamMsnTypeManager) {
        this.jamMsnTypeManager = jamMsnTypeManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jamMsnTypeListTable",request, 20);
        List jamMsnTypes = jamMsnTypeManager.getJamMsnTypesByCrm(crm,pager);
        request.setAttribute("jamMsnTypeListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("am/jamMsnTypeList", Constants.JAMMSNTYPE_LIST, jamMsnTypes);
    }
}
