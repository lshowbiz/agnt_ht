package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysConfigValue;
import com.joymain.jecs.sys.service.SysConfigValueManager;

public class SysConfigValueController implements Controller {
    private final Log log = LogFactory.getLog(SysConfigValueController.class);
    private SysConfigValueManager sysConfigValueManager = null;

    public void setSysConfigValueManager(SysConfigValueManager sysConfigValueManager) {
        this.sysConfigValueManager = sysConfigValueManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysConfigValue sysConfigValue = new SysConfigValue();
        // populate object with request parameters
        BeanUtils.populate(sysConfigValue, request.getParameterMap());

        List sysConfigValues = sysConfigValueManager.getSysConfigValues(sysConfigValue);

        return new ModelAndView("sys/sysConfigValueList", Constants.SYSCONFIGVALUE_LIST, sysConfigValues);
    }
}
