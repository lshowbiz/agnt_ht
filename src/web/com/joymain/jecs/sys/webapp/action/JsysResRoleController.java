package com.joymain.jecs.sys.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.service.JsysResRoleManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JsysResRoleController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JsysResRoleController.class);
    private JsysResRoleManager jsysResRoleManager = null;

    public void setJsysResRoleManager(JsysResRoleManager jsysResRoleManager) {
        this.jsysResRoleManager = jsysResRoleManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JsysResRole jsysResRole = new JsysResRole();
        // populate object with request parameters
        BeanUtils.populate(jsysResRole, request.getParameterMap());

	//List jsysResRoles = jsysResRoleManager.getJsysResRoles(jsysResRole);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jsysResRoleListTable",request, 20);
        List jsysResRoles = jsysResRoleManager.getJsysResRolesByCrm(crm,pager);
        request.setAttribute("jsysResRoleListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/jsysResRoleList", Constants.JSYSRESROLE_LIST, jsysResRoles);
    }
}
