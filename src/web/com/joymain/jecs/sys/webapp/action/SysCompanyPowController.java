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
import com.joymain.jecs.sys.model.SysCompanyPow;
import com.joymain.jecs.sys.service.SysCompanyPowManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysCompanyPowController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysCompanyPowController.class);
    private SysCompanyPowManager sysCompanyPowManager = null;

    public void setSysCompanyPowManager(SysCompanyPowManager sysCompanyPowManager) {
        this.sysCompanyPowManager = sysCompanyPowManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysCompanyPow sysCompanyPow = new SysCompanyPow();
        // populate object with request parameters
        BeanUtils.populate(sysCompanyPow, request.getParameterMap());

				//List sysCompanyPows = sysCompanyPowManager.getSysCompanyPows(sysCompanyPow);
				/**** auto pagination ***/
				CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysCompanyPowListTable",request, 20);
        List sysCompanyPows = sysCompanyPowManager.getSysCompanyPowsByCrm(crm,pager);
        request.setAttribute("sysCompanyPowListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/sysCompanyPowList", Constants.SYSCOMPANYPOW_LIST, sysCompanyPows);
    }
}
