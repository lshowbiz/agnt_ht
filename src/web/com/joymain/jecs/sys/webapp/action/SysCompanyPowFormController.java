package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysCompanyPow;
import com.joymain.jecs.sys.service.SysCompanyPowManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysCompanyPowFormController extends BaseFormController {
    private SysCompanyPowManager sysCompanyPowManager = null;

    public void setSysCompanyPowManager(SysCompanyPowManager sysCompanyPowManager) {
        this.sysCompanyPowManager = sysCompanyPowManager;
    }
    public SysCompanyPowFormController() {
        setCommandName("sysCompanyPow");
        setCommandClass(SysCompanyPow.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        SysCompanyPow sysCompanyPow = null;

        if (!StringUtils.isEmpty(id)) {
            sysCompanyPow = sysCompanyPowManager.getSysCompanyPow(id);
        } else {
            sysCompanyPow = new SysCompanyPow();
        }

        return sysCompanyPow;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysCompanyPow sysCompanyPow = (SysCompanyPow) command;
        boolean isNew = (sysCompanyPow.getId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysCompanyPow".equals(request.getParameter("strAction"))  ) {
            sysCompanyPowManager.removeSysCompanyPow(sysCompanyPow.getId().toString());

           // saveMessage(request, getText("sysCompanyPow.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysCompanyPow.deleted"));
        } else {
            String key = "sysCompanyPow.updated";
            if("addSysCompanyPow".equals(request.getParameter("strAction"))){
            		key ="sysCompanyPow.added";
            }
            sysCompanyPowManager.saveSysCompanyPow(sysCompanyPow);

           // String key = (isNew) ? "sysCompanyPow.added" : "sysCompanyPow.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
