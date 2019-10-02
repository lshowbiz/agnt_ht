package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.mi.model.JmiValidLevelList;
import com.joymain.jecs.mi.service.JmiValidLevelListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiValidLevelListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiValidLevelListController.class);
    private JmiValidLevelListManager jmiValidLevelListManager = null;

    public void setJmiValidLevelListManager(JmiValidLevelListManager jmiValidLevelListManager) {
        this.jmiValidLevelListManager = jmiValidLevelListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiValidLevelListListTable",request, 20);
        List jmiValidLevelLists = jmiValidLevelListManager.getJmiValidLevelListsByCrm(crm,pager);
        request.setAttribute("jmiValidLevelListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiValidLevelListList", "jmiValidLevelListList", jmiValidLevelLists);
    }
}
