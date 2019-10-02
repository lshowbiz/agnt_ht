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
import com.joymain.jecs.sys.model.SysReportLog;
import com.joymain.jecs.sys.service.SysReportLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysReportLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysReportLogController.class);
    private SysReportLogManager sysReportLogManager = null;

    public void setSysReportLogManager(SysReportLogManager sysReportLogManager) {
        this.sysReportLogManager = sysReportLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysReportLog sysReportLog = new SysReportLog();
        // populate object with request parameters
        BeanUtils.populate(sysReportLog, request.getParameterMap());

				//List sysReportLogs = sysReportLogManager.getSysReportLogs(sysReportLog);
				/**** auto pagination ***/
				CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysReportLogListTable",request, 20);
        List sysReportLogs = sysReportLogManager.getSysReportLogsByCrm(crm,pager);
        request.setAttribute("sysReportLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/sysReportLogList", Constants.SYSREPORTLOG_LIST, sysReportLogs);
    }
}
