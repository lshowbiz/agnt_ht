package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysVisitLog;
import com.joymain.jecs.sys.service.SysVisitLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysVisitLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysVisitLogController.class);
    private SysVisitLogManager sysVisitLogManager = null;

    public void setSysVisitLogManager(SysVisitLogManager sysVisitLogManager) {
        this.sysVisitLogManager = sysVisitLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysVisitLog sysVisitLog = new SysVisitLog();
        // populate object with request parameters
        BeanUtils.populate(sysVisitLog, request.getParameterMap());

				//List sysVisitLogs = sysVisitLogManager.getSysVisitLogs(sysVisitLog);
				/**** auto pagination ***/
				CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysVisitLogListTable",request, 20);
        List sysVisitLogs = sysVisitLogManager.getSysVisitLogsByCrm(crm,pager);
        request.setAttribute("sysVisitLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/sysVisitLogList", Constants.SYSVISITLOG_LIST, sysVisitLogs);
    }
}
