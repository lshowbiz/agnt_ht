package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysReportLog;
import com.joymain.jecs.sys.service.SysReportLogManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysReportLogFormController extends BaseFormController {
    private SysReportLogManager sysReportLogManager = null;

    public void setSysReportLogManager(SysReportLogManager sysReportLogManager) {
        this.sysReportLogManager = sysReportLogManager;
    }
    public SysReportLogFormController() {
        setCommandName("sysReportLog");
        setCommandClass(SysReportLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String reportId = request.getParameter("reportId");
        SysReportLog sysReportLog = null;

        if (!StringUtils.isEmpty(reportId)) {
            sysReportLog = sysReportLogManager.getSysReportLog(reportId);
        } else {
            sysReportLog = new SysReportLog();
        }

        return sysReportLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysReportLog sysReportLog = (SysReportLog) command;
        boolean isNew = (sysReportLog.getReportId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysReportLog".equals(request.getParameter("strAction"))  ) {
            sysReportLogManager.removeSysReportLog(sysReportLog.getReportId().toString());

           // saveMessage(request, getText("sysReportLog.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysReportLog.deleted"));
        } else {
            String key = "sysReportLog.updated";
            if("addSysReportLog".equals(request.getParameter("strAction"))){
            		key ="sysReportLog.added";
            }
            sysReportLogManager.saveSysReportLog(sysReportLog);

           // String key = (isNew) ? "sysReportLog.added" : "sysReportLog.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
