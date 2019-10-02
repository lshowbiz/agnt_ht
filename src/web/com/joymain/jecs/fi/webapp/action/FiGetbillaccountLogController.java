package com.joymain.jecs.fi.webapp.action;

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
import com.joymain.jecs.fi.model.FiGetbillaccountLog;
import com.joymain.jecs.fi.service.FiGetbillaccountLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiGetbillaccountLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiGetbillaccountLogController.class);
    private FiGetbillaccountLogManager fiGetbillaccountLogManager = null;

    public void setFiGetbillaccountLogManager(FiGetbillaccountLogManager fiGetbillaccountLogManager) {
        this.fiGetbillaccountLogManager = fiGetbillaccountLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiGetbillaccountLog fiGetbillaccountLog = new FiGetbillaccountLog();
        // populate object with request parameters
        BeanUtils.populate(fiGetbillaccountLog, request.getParameterMap());

	//List fiGetbillaccountLogs = fiGetbillaccountLogManager.getFiGetbillaccountLogs(fiGetbillaccountLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiGetbillaccountLogListTable",request, 20);
        List fiGetbillaccountLogs = fiGetbillaccountLogManager.getFiGetbillaccountLogsByCrm(crm,pager);
        request.setAttribute("fiGetbillaccountLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiGetbillaccountLogList", "fiGetbillaccountLogList", fiGetbillaccountLogs);
    }
}
