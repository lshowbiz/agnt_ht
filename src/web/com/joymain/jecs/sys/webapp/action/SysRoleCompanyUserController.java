package com.joymain.jecs.sys.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.model.SysManagerModlPow;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysRolePower;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.sys.service.SysManagerModlPowManager;
import com.joymain.jecs.sys.service.SysManagerUserManager;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysRolePowerManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysRoleCompanyUserController extends BaseFormController {
	private AlCompanyManager alCompanyManager = null;
	private SysDepartmentManager sysDepartmentManager = null;
	private SysManagerUserManager sysManagerUserManager = null;
	private SysRolePowerManager sysRolePowerManager = null;
	private SysRoleManager sysRoleManager = null;
	private SysManagerModlPowManager sysManagerModlPowManager = null;
	
	public void setSysManagerModlPowManager(SysManagerModlPowManager sysManagerModlPowManager) {
        this.sysManagerModlPowManager = sysManagerModlPowManager;
    }

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	public void setSysRolePowerManager(SysRolePowerManager sysRolePowerManager) {
		this.sysRolePowerManager = sysRolePowerManager;
	}

	public void setSysManagerUserManager(SysManagerUserManager sysManagerUserManager) {
		this.sysManagerUserManager = sysManagerUserManager;
	}

	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public SysRoleCompanyUserController() {
		setCommandName("sysRole");
		setCommandClass(SysRole.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String roleId = request.getParameter("roleId");
		SysRole sysRole = null;

		if (!StringUtils.isEmpty(roleId)) {
			sysRole = sysRoleManager.getSysRole(roleId);
		} else {
			sysRole = new SysRole();
		}

		//读取所管理的人员所处公司
		List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), sysRole.getCompanyCode(), false);
		request.setAttribute("alCompanys", alCompanys);
		//读取管理人员所能管理的部门
		List sysDepartments = this.sysDepartmentManager.getMyAllDepartments(SessionLogin.getLoginUser(request), sysRole.getCompanyCode(), false);
		request.setAttribute("sysDepartments", sysDepartments);
		//读取对应人员信息
		List sysManagers = this.sysManagerUserManager.getSysManagersWithJoin(SessionLogin.getLoginUser(request), null, sysRole.getCompanyCode(), false);
		request.setAttribute("sysManagers", sysManagers);

		return sysRole;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysRole sysRole = (SysRole) command;
		if ("sysRoleCompanyUser".equals(request.getParameter("strAction"))) {
			List<SysManagerModlPow> sysManagerModlPows=new ArrayList<SysManagerModlPow>();
			String selectedUser=request.getParameter("selectedUser");//所选择的可管理人员 格式: M#code1,M#code2,M#code3
			String[] selectedUsers=selectedUser.split(",");
			//List<String> slaveUserCodeList=new ArrayList<String>();
			if(selectedUser!=null && selectedUsers.length>0){
				for(int i=0;i<selectedUsers.length;i++){
					if(!StringUtils.isEmpty(selectedUsers[i])){
						String[] tmpStr=selectedUsers[i].split("#");
						if(!StringUtils.isEmpty(tmpStr[1])){
							//删除此用户对应的模块权限
							this.sysManagerModlPowManager.removeSysManagerModlPowsByRole(tmpStr[1], sysRole.getCompanyCode(), sysRole);
							//获取此角色对应的所有模块
							List sysRolePowers=this.sysRolePowerManager.getSysRolePowersByRoleId(sysRole.getRoleId().toString());
							if(sysRolePowers!=null && sysRolePowers.size()>0){
								for(int k=0;k<sysRolePowers.size();k++){
									SysRolePower sysRolePower=(SysRolePower)sysRolePowers.get(k);
									
									SysManagerModlPow sysManagerModlPow=this.sysManagerModlPowManager.getSysManagerModlPow(tmpStr[1],sysRole.getCompanyCode() , sysRolePower.getModuleId().toString());
									if(sysManagerModlPow==null){
										sysManagerModlPow=new SysManagerModlPow();
										
										sysManagerModlPow.setCompanyCode(sysRole.getCompanyCode());
										sysManagerModlPow.setModuleId(sysRolePower.getModuleId());
										sysManagerModlPow.setUserCode(tmpStr[1]);
										
										sysManagerModlPows.add(sysManagerModlPow);
									}
								}
							}
						}
					}
				}
			}
			this.sysManagerModlPowManager.saveSysManagerModlPows(sysManagerModlPows);
		}

		saveMessage(request, getText("sysModuel.setting.sucess"));

		return new ModelAndView(getSuccessView());
	}
}