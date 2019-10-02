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
import com.joymain.jecs.sys.model.SysManagerModlPow;
import com.joymain.jecs.sys.service.SysManagerModlPowManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysManagerModlPowController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysManagerModlPowController.class);
    private SysManagerModlPowManager sysManagerModlPowManager = null;

    public void setSysManagerModlPowManager(SysManagerModlPowManager sysManagerModlPowManager) {
        this.sysManagerModlPowManager = sysManagerModlPowManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysManagerModlPow sysManagerModlPow = new SysManagerModlPow();
        // populate object with request parameters
        BeanUtils.populate(sysManagerModlPow, request.getParameterMap());

				//List sysManagerModlPows = sysManagerModlPowManager.getSysManagerModlPows(sysManagerModlPow);
				/**** auto pagination ***/
				CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysManagerModlPowListTable",request, 20);
        List sysManagerModlPows = sysManagerModlPowManager.getSysManagerModlPowsByCrm(crm,pager);
        request.setAttribute("sysManagerModlPowListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/sysManagerModlPowList", Constants.SYSMANAGERMODLROLL_LIST, sysManagerModlPows);
    }
}
