package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysUserRoleFormController extends BaseFormController {
    private SysUserRoleManager sysUserRoleManager = null;

    public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
        this.sysUserRoleManager = sysUserRoleManager;
    }
    public SysUserRoleFormController() {
        setCommandName("sysUserRole");
        setCommandClass(SysUserRole.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String ruId = request.getParameter("ruId");
        SysUserRole sysUserRole = null;

        if (!StringUtils.isEmpty(ruId)) {
            sysUserRole = sysUserRoleManager.getSysUserRole(ruId);
        } else {
            sysUserRole = new SysUserRole();
        }

        return sysUserRole;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUserRole sysUserRole = (SysUserRole) command;
        boolean isNew = (sysUserRole.getRuId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysUserRole".equals(request.getParameter("strAction"))  ) {
            sysUserRoleManager.removeSysUserRole(sysUserRole.getRuId().toString());

           // saveMessage(request, getText("sysUserRole.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysUserRole.deleted"));
        } else {
            String key = "sysUserRole.updated";
            if("addSysUserRole".equals(request.getParameter("strAction"))){
            		key ="sysUserRole.added";
            }
            sysUserRoleManager.saveSysUserRole(sysUserRole);

           // String key = (isNew) ? "sysUserRole.added" : "sysUserRole.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
