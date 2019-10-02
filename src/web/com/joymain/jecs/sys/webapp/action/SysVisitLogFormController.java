package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysVisitLog;
import com.joymain.jecs.sys.service.SysVisitLogManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysVisitLogFormController extends BaseFormController {
    private SysVisitLogManager sysVisitLogManager = null;

    public void setSysVisitLogManager(SysVisitLogManager sysVisitLogManager) {
        this.sysVisitLogManager = sysVisitLogManager;
    }
    public SysVisitLogFormController() {
        setCommandName("sysVisitLog");
        setCommandClass(SysVisitLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        SysVisitLog sysVisitLog = null;

        if (!StringUtils.isEmpty(logId)) {
            sysVisitLog = sysVisitLogManager.getSysVisitLog(logId);
        } else {
            sysVisitLog = new SysVisitLog();
        }

        return sysVisitLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysVisitLog sysVisitLog = (SysVisitLog) command;
        boolean isNew = (sysVisitLog.getLogId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysVisitLog".equals(request.getParameter("strAction"))  ) {
            sysVisitLogManager.removeSysVisitLog(sysVisitLog.getLogId().toString());

           // saveMessage(request, getText("sysVisitLog.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysVisitLog.deleted"));
        } else {
            String key = "sysVisitLog.updated";
            if("addSysVisitLog".equals(request.getParameter("strAction"))){
            		key ="sysVisitLog.added";
            }
            sysVisitLogManager.saveSysVisitLog(sysVisitLog);

           // String key = (isNew) ? "sysVisitLog.added" : "sysVisitLog.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
