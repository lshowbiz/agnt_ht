package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysRolePower;
import com.joymain.jecs.sys.service.SysRolePowerManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysRolePowerFormController extends BaseFormController {
    private SysRolePowerManager sysRolePowerManager = null;

    public void setSysRolePowerManager(SysRolePowerManager sysRolePowerManager) {
        this.sysRolePowerManager = sysRolePowerManager;
    }
    public SysRolePowerFormController() {
        setCommandName("sysRolePower");
        setCommandClass(SysRolePower.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String rpId = request.getParameter("rpId");
        SysRolePower sysRolePower = null;

        if (!StringUtils.isEmpty(rpId)) {
            sysRolePower = sysRolePowerManager.getSysRolePower(rpId);
        } else {
            sysRolePower = new SysRolePower();
        }

        return sysRolePower;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysRolePower sysRolePower = (SysRolePower) command;
        boolean isNew = (sysRolePower.getRpId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysRolePower".equals(request.getParameter("strAction"))  ) {
            sysRolePowerManager.removeSysRolePower(sysRolePower.getRpId().toString());

           // saveMessage(request, getText("sysRolePower.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysRolePower.deleted"));
        } else {
            String key = "sysRolePower.updated";
            if("addSysRolePower".equals(request.getParameter("strAction"))){
            		key ="sysRolePower.added";
            }
            sysRolePowerManager.saveSysRolePower(sysRolePower);

           // String key = (isNew) ? "sysRolePower.added" : "sysRolePower.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
