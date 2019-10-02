package com.joymain.jecs.sys.webapp.action;

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
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SysLoginLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysLoginLogController.class);
    private SysLoginLogManager sysLoginLogManager = null;

    public void setSysLoginLogManager(SysLoginLogManager sysLoginLogManager) {
        this.sysLoginLogManager = sysLoginLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysLoginLog sysLoginLog = new SysLoginLog();
        // populate object with request parameters
        BeanUtils.populate(sysLoginLog, request.getParameterMap());

	//List sysLoginLogs = sysLoginLogManager.getSysLoginLogs(sysLoginLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysLoginLogListTable",request, 20);
        List sysLoginLogs = sysLoginLogManager.getSysLoginLogsByCrm(crm,pager);
        request.setAttribute("sysLoginLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/sysLoginLogList", Constants.SYSLOGINLOG_LIST, sysLoginLogs);
    }
}
