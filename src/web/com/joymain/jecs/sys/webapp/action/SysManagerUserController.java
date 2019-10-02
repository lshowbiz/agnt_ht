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
import com.joymain.jecs.sys.model.SysManagerUser;
import com.joymain.jecs.sys.service.SysManagerUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class SysManagerUserController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysManagerUserController.class);
    private SysManagerUserManager sysManagerUserManager = null;

    public void setSysManagerUserManager(SysManagerUserManager sysManagerUserManager) {
        this.sysManagerUserManager = sysManagerUserManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysManagerUser sysManagerUser = new SysManagerUser();
        // populate object with request parameters
        BeanUtils.populate(sysManagerUser, request.getParameterMap());

				//List sysManagerUsers = sysManagerUserManager.getSysManagerUsers(sysManagerUser);
				/**** auto pagination ***/
				CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysManagerUserListTable",request, 20);
        List sysManagerUsers = sysManagerUserManager.getSysManagerUsersByCrm(crm,pager);
        request.setAttribute("sysManagerUserListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/sysManagerUserList", Constants.SYSMANAGERUSER_LIST, sysManagerUsers);
    }
}
