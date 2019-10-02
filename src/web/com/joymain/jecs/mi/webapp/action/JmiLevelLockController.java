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
import com.joymain.jecs.mi.model.JmiLevelLock;
import com.joymain.jecs.mi.service.JmiLevelLockManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiLevelLockController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiLevelLockController.class);
    private JmiLevelLockManager jmiLevelLockManager = null;

    public void setJmiLevelLockManager(JmiLevelLockManager jmiLevelLockManager) {
        this.jmiLevelLockManager = jmiLevelLockManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

	/**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiLevelLockListTable",request, 20);
        List jmiLevelLocks = jmiLevelLockManager.getJmiLevelLocksByCrm(crm,pager);
        request.setAttribute("jmiLevelLockListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiLevelLockList", "jmiLevelLockList", jmiLevelLocks);
    }
}
