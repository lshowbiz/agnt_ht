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
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysRolePower;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysRolePowerManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysRoleFormController extends BaseFormController {
	private SysRoleManager sysRoleManager = null;
	private AlCompanyManager alCompanyManager = null;
	private SysRolePowerManager sysRolePowerManager = null;

	public void setSysRolePowerManager(SysRolePowerManager sysRolePowerManager) {
		this.sysRolePowerManager = sysRolePowerManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	public SysRoleFormController() {
		setCommandName("sysRole");
		setCommandClass(SysRole.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		SysRole sysRole = null;

		if (!StringUtils.isEmpty(roleId)) {
			sysRole = sysRoleManager.getSysRole(roleId);
			List sysRolePowers = this.sysRolePowerManager.getSysRolePowersByRoleId(roleId);
			String moduleIds = "";
			if (sysRolePowers != null && !sysRolePowers.isEmpty()) {
				for (int i = 0; i < sysRolePowers.size(); i++) {
					if (i > 0) {
						moduleIds += ((SysRolePower) sysRolePowers.get(i)).getModuleId().toString();
					}
				}
			}
			request.setAttribute("moduleIds", moduleIds);
		} else {
			sysRole = new SysRole();
		}

		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

		Map userTypes = ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "user_type");
		request.setAttribute("userTypes", userTypes);

		return sysRole;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysRole sysRole = (SysRole) command;
		if ("deleteSysRole".equals(request.getParameter("strAction"))) {
			sysRoleManager.removeSysRole(sysRole.getRoleId().toString());

			saveMessage(request, getText("sysRole.deleted"));
		} else {
			String key = "sysRole.updated";
			if ("addSysRole".equals(request.getParameter("strAction"))) {
				key = "sysRole.added";
				//验证是否存在
				SysRole oldSysRole = this.sysRoleManager.getSysRoleByCode(sysRole.getRoleCode());
				if (oldSysRole != null) {
					// 存在
					errors.rejectValue("roleCode", "sysRole.roleCode.exists");
					return showForm(request, response, errors);
				}
			} else if ("editSysRole".equals(request.getParameter("strAction"))) {
				//验证是否存在
				SysRole oldSysRole = this.sysRoleManager.getSysRoleByCode(sysRole.getRoleCode());
				if (oldSysRole != null && oldSysRole.getRoleId().longValue() != sysRole.getRoleId().longValue()) {
					// 存在
					errors.rejectValue("roleCode", "sysRole.roleCode.exists");
					return showForm(request, response, errors);
				}
			}

			sysRoleManager.saveSysRole(sysRole);

			/*---- 保存角色权限 ---- */
			//清除不在所选权限范围内的权限
			String moduleIds = request.getParameter("moduleIds");
			List<String> moduleIdList = new ArrayList<String>();
			if (!StringUtils.isEmpty(moduleIds)) {
				String[] selectedModules = moduleIds.split(",");
				for (int i = 0; i < selectedModules.length; i++) {
					if (!StringUtils.isEmpty(selectedModules[i])) {
						moduleIdList.add(selectedModules[i]);
					}
				}
			}
			String[] moduleId = (String[]) moduleIdList.toArray(new String[moduleIdList.size()]);
			this.sysRolePowerManager.removeSysRolePowersNotIn(sysRole.getRoleId().toString(), moduleId);
			//重新设置
			if (moduleId != null && moduleId.length > 0) {
				List<SysRolePower> sysRolePowers = new ArrayList<SysRolePower>();
				for (int i = 0; i < moduleId.length; i++) {
					SysRolePower sysRolePower = this.sysRolePowerManager.getSysRolePower(sysRole.getRoleId().toString(), moduleId[i]);
					if (sysRolePower == null) {
						sysRolePower = new SysRolePower();
						sysRolePower.setModuleId(new Long(moduleId[i]));
						sysRolePower.setRoleId(sysRole.getRoleId());
						sysRolePowers.add(sysRolePower);
					}
				}
				this.sysRolePowerManager.saveSysRolePowers(sysRolePowers);
			}

			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}