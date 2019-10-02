package com.joymain.jecs.sys.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysRolePower;
import com.joymain.jecs.sys.service.JsysResRoleManager;
import com.joymain.jecs.sys.service.JsysResourceManager;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysRolePowerManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JSysRoleResourceFormController extends BaseFormController {
	private SysRoleManager sysRoleManager = null;
	
	private JsysResourceManager jsysResourceManager = null;
	private JsysResRoleManager jsysResRoleManager = null;

	public void setJsysResourceManager(JsysResourceManager jsysResourceManager) {
		this.jsysResourceManager = jsysResourceManager;
	}

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	public void setJsysResRoleManager(JsysResRoleManager jsysResRoleManager) {
		this.jsysResRoleManager = jsysResRoleManager;
	}
	public JSysRoleResourceFormController() {
		setCommandName("sysRole");
		setCommandClass(SysRole.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		String resIds = request.getParameter("resIds");
		SysRole sysRole = null;

		if (!StringUtils.isEmpty(roleId)) {
			sysRole = sysRoleManager.getSysRole(roleId);
			//List sysRolePowers = this.sysRolePowerManager.getSysRolePowersByRoleId(roleId);
//			String moduleIds = "";
//			if (sysRolePowers != null && !sysRolePowers.isEmpty()) {
//				for (int i = 0; i < sysRolePowers.size(); i++) {
//					if (i > 0) {
//						moduleIds += ((SysRolePower) sysRolePowers.get(i)).getModuleId().toString();
//					}
//				}
//			}
//			request.setAttribute("moduleIds", moduleIds);
		} else {
			//sysRole = new SysRole();
			
		}

		return sysRole;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		String roleId = request.getParameter("roleId");
		//SysRole sysRole = (SysRole) command;

			String key = "sysResRole.updated";
			if ("editSysRoleResource".equals(request.getParameter("strAction"))) {
				//验证是否存在
				SysRole oldSysRole = this.sysRoleManager.getSysRole(roleId);
				//.getSysRoleByCode(sysRole.getRoleCode());
				if (oldSysRole == null) {
					// 存在
					errors.rejectValue("roleCode", "sysRole.roleCode.notexists");
					return showForm(request, response, errors);
				}
			}
			
			String resIds = request.getParameter("resIds");
			List<String> resIdList = new ArrayList<String>();
			if (!StringUtils.isEmpty(resIds)) {
				String[] selectedModules = resIds.split(",");
				for (int i = 0; i < selectedModules.length; i++) {
					if (!StringUtils.isEmpty(selectedModules[i])) {
						resIdList.add(selectedModules[i]);
					}
				}
			}
			String[] resId = (String[]) resIdList.toArray(new String[resIdList.size()]);
			//移除
			jsysResourceManager.removeSysRoleResByRoleId(new Long(roleId));
			//this.sysRolePowerManager.removeSysRolePowersNotIn(sysRole.getRoleId().toString(), moduleId);
			//保存所选的资源
			if (resId != null && resId.length > 0) {
			List<JsysResRole> jList = new ArrayList<JsysResRole>();
			for (int i = 0; i < resId.length; i++) {
				
				JsysResRole jSysRoleRes = new JsysResRole();
				jSysRoleRes.setResId(new Long(resId[i]));
				jSysRoleRes.setRoleId(new Long(roleId));
				
				//jsysResRoleManager.saveJsysResRole(jSysRoleRes);
				jList.add(jSysRoleRes);
			}
			//jsysResRoleManager.saveSysRoleResList(jList);
			this.jsysResourceManager.saveSysRoleRes(jList);
		}

			//sysRoleManager.saveSysRole(sysRole);



			saveMessage(request, getText(key));
		

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}