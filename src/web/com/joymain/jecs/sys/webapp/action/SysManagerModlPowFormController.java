package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysManagerModlPow;
import com.joymain.jecs.sys.service.SysManagerModlPowManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysManagerModlPowFormController extends BaseFormController {
    private SysManagerModlPowManager sysManagerModlPowManager = null;

    public void setSysManagerModlPowManager(SysManagerModlPowManager sysManagerModlPowManager) {
        this.sysManagerModlPowManager = sysManagerModlPowManager;
    }
    public SysManagerModlPowFormController() {
        setCommandName("sysManagerModlPow");
        setCommandClass(SysManagerModlPow.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String powerId = request.getParameter("powerId");
        SysManagerModlPow sysManagerModlPow = null;

        if (!StringUtils.isEmpty(powerId)) {
            sysManagerModlPow = sysManagerModlPowManager.getSysManagerModlPow(powerId);
        } else {
            sysManagerModlPow = new SysManagerModlPow();
        }

        return sysManagerModlPow;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysManagerModlPow sysManagerModlPow = (SysManagerModlPow) command;
        boolean isNew = (sysManagerModlPow.getPowerId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysManagerModlPow".equals(request.getParameter("strAction"))  ) {
            sysManagerModlPowManager.removeSysManagerModlPow(sysManagerModlPow.getPowerId().toString());

           // saveMessage(request, getText("sysManagerModlPow.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysManagerModlPow.deleted"));
        } else {
            String key = "sysManagerModlPow.updated";
            if("addSysManagerModlPow".equals(request.getParameter("strAction"))){
            		key ="sysManagerModlPow.added";
            }
            sysManagerModlPowManager.saveSysManagerModlPow(sysManagerModlPow);

           // String key = (isNew) ? "sysManagerModlPow.added" : "sysManagerModlPow.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
