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
import com.joymain.jecs.sys.model.SysUsrTypePow;
import com.joymain.jecs.sys.service.SysUsrTypePowManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysUsrTypePowController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysUsrTypePowController.class);
    private SysUsrTypePowManager sysUsrTypePowManager = null;

    public void setSysUsrTypePowManager(SysUsrTypePowManager sysUsrTypePowManager) {
        this.sysUsrTypePowManager = sysUsrTypePowManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUsrTypePow sysUsrTypePow = new SysUsrTypePow();
        // populate object with request parameters
        BeanUtils.populate(sysUsrTypePow, request.getParameterMap());

				//List sysUsrTypePows = sysUsrTypePowManager.getSysUsrTypePows(sysUsrTypePow);
				/**** auto pagination ***/
				CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysUsrTypePowListTable",request, 20);
        List sysUsrTypePows = sysUsrTypePowManager.getSysUsrTypePowsByCrm(crm,pager);
        request.setAttribute("sysUsrTypePowListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/sysUsrTypePowList", Constants.SYSUSRTYPEPOW_LIST, sysUsrTypePows);
    }
}
