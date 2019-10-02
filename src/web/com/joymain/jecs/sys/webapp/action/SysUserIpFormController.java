package com.joymain.jecs.sys.webapp.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysUserIp;
import com.joymain.jecs.sys.service.SysUserIpManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysUserIpFormController extends BaseFormController {
    private SysUserIpManager sysUserIpManager = null;

    public void setSysUserIpManager(SysUserIpManager sysUserIpManager) {
        this.sysUserIpManager = sysUserIpManager;
    }
    public SysUserIpFormController() {
        setCommandName("sysUserIp");
        setCommandClass(SysUserIp.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String ipId = request.getParameter("ipId");
        SysUserIp sysUserIp = null;

        if (!StringUtils.isEmpty(ipId)) {
            sysUserIp = sysUserIpManager.getSysUserIp(ipId);
        } else {
            sysUserIp = new SysUserIp();
        }

        return sysUserIp;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUserIp sysUserIp = (SysUserIp) command;
        boolean isNew = (sysUserIp.getIpId() == null);
        Locale locale = request.getLocale();
        if ("deleteSysUserIp".equals(request.getParameter("strAction"))  ) {
            sysUserIpManager.removeSysUserIp(sysUserIp.getIpId().toString());

           // saveMessage(request, getText("sysUserIp.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysUserIp.deleted"));
        } else {
            String key = "sysUserIp.updated";
            if("addSysUserIp".equals(request.getParameter("strAction"))){
            		key ="sysUserIp.added";
            }
            sysUserIpManager.saveSysUserIp(sysUserIp);

           // String key = (isNew) ? "sysUserIp.added" : "sysUserIp.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView(getSuccessView());
    }
}
