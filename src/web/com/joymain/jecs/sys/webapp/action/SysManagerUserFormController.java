package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysManagerUser;
import com.joymain.jecs.sys.service.SysManagerUserManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysManagerUserFormController extends BaseFormController {
    private SysManagerUserManager sysManagerUserManager = null;

    public void setSysManagerUserManager(SysManagerUserManager sysManagerUserManager) {
        this.sysManagerUserManager = sysManagerUserManager;
    }
    public SysManagerUserFormController() {
        setCommandName("sysManagerUser");
        setCommandClass(SysManagerUser.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String rollId = request.getParameter("rollId");
        SysManagerUser sysManagerUser = null;

        if (!StringUtils.isEmpty(rollId)) {
            sysManagerUser = sysManagerUserManager.getSysManagerUser(rollId);
        } else {
            sysManagerUser = new SysManagerUser();
        }

        return sysManagerUser;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysManagerUser sysManagerUser = (SysManagerUser) command;
        boolean isNew = (sysManagerUser.getRollId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysManagerUser".equals(request.getParameter("strAction"))  ) {
            sysManagerUserManager.removeSysManagerUser(sysManagerUser.getRollId().toString());

           // saveMessage(request, getText("sysManagerUser.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysManagerUser.deleted"));
        } else {
            String key = "sysManagerUser.updated";
            if("addSysManagerUser".equals(request.getParameter("strAction"))){
            		key ="sysManagerUser.added";
            }
            sysManagerUserManager.saveSysManagerUser(sysManagerUser);

           // String key = (isNew) ? "sysManagerUser.added" : "sysManagerUser.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
