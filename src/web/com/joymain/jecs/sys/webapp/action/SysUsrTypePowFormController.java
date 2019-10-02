package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysUsrTypePow;
import com.joymain.jecs.sys.service.SysUsrTypePowManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysUsrTypePowFormController extends BaseFormController {
    private SysUsrTypePowManager sysUsrTypePowManager = null;

    public void setSysUsrTypePowManager(SysUsrTypePowManager sysUsrTypePowManager) {
        this.sysUsrTypePowManager = sysUsrTypePowManager;
    }
    public SysUsrTypePowFormController() {
        setCommandName("sysUsrTypePow");
        setCommandClass(SysUsrTypePow.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        SysUsrTypePow sysUsrTypePow = null;

        if (!StringUtils.isEmpty(id)) {
            sysUsrTypePow = sysUsrTypePowManager.getSysUsrTypePow(id);
        } else {
            sysUsrTypePow = new SysUsrTypePow();
        }

        return sysUsrTypePow;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUsrTypePow sysUsrTypePow = (SysUsrTypePow) command;
        boolean isNew = (sysUsrTypePow.getId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysUsrTypePow".equals(request.getParameter("strAction"))  ) {
            sysUsrTypePowManager.removeSysUsrTypePow(sysUsrTypePow.getId().toString());

           // saveMessage(request, getText("sysUsrTypePow.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysUsrTypePow.deleted"));
        } else {
            String key = "sysUsrTypePow.updated";
            if("addSysUsrTypePow".equals(request.getParameter("strAction"))){
            		key ="sysUsrTypePow.added";
            }
            sysUsrTypePowManager.saveSysUsrTypePow(sysUsrTypePow);

           // String key = (isNew) ? "sysUsrTypePow.added" : "sysUsrTypePow.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
