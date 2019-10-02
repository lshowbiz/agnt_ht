package com.joymain.jecs.sys.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.JsysResourceManager;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 角色资源授权
 * 
 * @author lenovo
 * 
 */
public class JSysRoleResourcesController extends BaseController implements
		Controller {
	private final Log log = LogFactory
			.getLog(JSysRoleResourcesController.class);
	private JsysResourceManager jsysResourceManager = null;

	public void setJsysResourceManager(JsysResourceManager jsysResourceManager) {
		this.jsysResourceManager = jsysResourceManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		String roleId = request.getParameter("roleId");
		SysUser defSysUser=SessionLogin.getLoginUser(request);
		Map unViewResources=new HashMap();
        if(!"root".equals(defSysUser.getUserCode())){
        	unViewResources=ListUtil.getListOptions("CN", "un.view.code");
        	
        }
        request.setAttribute("unViewResources", unViewResources);
		SysRole sysRole = new SysRole();
		if (!StringUtils.isEmpty(roleId)) {
			sysRole.setRoleId(new Long(roleId));
		}
		List jsysResources = jsysResourceManager.getJsysResourcesJoinRole(sysRole);
        request.setAttribute("jsysResources", jsysResources);

		return new ModelAndView("sys/jsysRoleResourceList");
	}
}
