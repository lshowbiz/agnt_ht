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
import com.joymain.jecs.sys.model.SysUserIp;
import com.joymain.jecs.sys.service.SysUserIpManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysUserIpController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysUserIpController.class);
    private SysUserIpManager sysUserIpManager = null;

    public void setSysUserIpManager(SysUserIpManager sysUserIpManager) {
        this.sysUserIpManager = sysUserIpManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUserIp sysUserIp = new SysUserIp();
        // populate object with request parameters
        BeanUtils.populate(sysUserIp, request.getParameterMap());

				//List sysUserIps = sysUserIpManager.getSysUserIps(sysUserIp);
				/**** auto pagination ***/
				CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysUserIpListTable",request, 20);
        List sysUserIps = sysUserIpManager.getSysUserIpsByCrm(crm,pager);
        request.setAttribute("sysUserIpListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/sysUserIpList", Constants.SYSUSERIP_LIST, sysUserIps);
    }
}
