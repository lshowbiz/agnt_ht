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
import com.joymain.jecs.mi.model.JmiGradeLock;
import com.joymain.jecs.mi.service.JmiGradeLockManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiGradeLockController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiGradeLockController.class);
    private JmiGradeLockManager jmiGradeLockManager = null;

    public void setJmiGradeLockManager(JmiGradeLockManager jmiGradeLockManager) {
        this.jmiGradeLockManager = jmiGradeLockManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiGradeLock jmiGradeLock = new JmiGradeLock();
        // populate object with request parameters
        BeanUtils.populate(jmiGradeLock, request.getParameterMap());

	//List jmiGradeLocks = jmiGradeLockManager.getJmiGradeLocks(jmiGradeLock);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiGradeLockListTable",request, 20);
        List jmiGradeLocks = jmiGradeLockManager.getJmiGradeLocksByCrm(crm,pager);
        request.setAttribute("jmiGradeLockListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiGradeLockList", "jmiGradeLockList", jmiGradeLocks);
    }
}
