package com.joymain.jecs.sys.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysUserController extends BaseController implements Controller {
	private SysUserRoleManager sysUserRoleManager = null;
	private SysUserManager sysUserManager = null;
	private SysRoleManager sysRoleManager = null;
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//String strAction = request.getParameter("strAction");
		String searchFlag = request.getParameter("searchFlag");
		String view = "sys/sysUserList";
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		String strAction = crm.getString("strAction");
	    Pager pager = new Pager("sysUserListTable",request, 20);
		List userList = new ArrayList();
		
		if(!sessionLogin.getIsManager()){
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		}
		
		if("memberManager".equalsIgnoreCase(strAction)){//会员管理
			crm.setValue("userType", "M");
//			view = "sys/memberList";
		}else if("agentManager".equalsIgnoreCase(strAction)){//代理商管理
			crm.setValue("userType", "P','Q");
//			view = "sys/agentList";
		}else if("manageRole".equalsIgnoreCase(strAction)){//会员/代理商角色管理
			String uCode = crm.getString("uCode");
			request.setAttribute("uCode", uCode);//这里的值其实是userCode
			//String uName = crm.getString("uName");
			//request.setAttribute("uName", uName);//这里的值其实是userCode
			
			//crm.setValue("companyCode", sysUserManager.getSysUser(uCode).getCompanyCode());
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
			crm.setValue("userType", sysUserManager.getSysUser(uCode).getUserType());
			
			//查询出该用户适合的所有角色中,占有哪些角色
			userList = sysUserManager.getSysUserRoleList(crm, pager);
			
			view = "sys/sysUserRoleManage";
		}else if("listUsersNotInRole".equalsIgnoreCase(strAction)){//列出不在角色中的用户
			String roleId = request.getParameter("roleId");
			SysRole sysRole= sysRoleManager.getSysRole(roleId);
			if(sysRole == null){
				this.saveMessage(request, LocaleUtil.getLocalText(sessionLogin.getDefCharacterCoding(),"erro.hasNotTheRole"));
			}else{
				crm.setValue("companyCode", sysRole.getCompanyCode());
				crm.setValue("userType", sysRole.getUserType());
				crm.setValue("roleId", roleId);
			}
			userList = sysUserManager.getSysUserNotInRole(crm, pager);
			view = "sys/sysUserWithOutRoleList";
		}
		
		if("show".equals(searchFlag)){
			userList = sysUserManager.getSysUsers(crm, pager);
		}
		
		request.setAttribute("sysUserListTable_totalRows", pager.getTotalObjects());
		request.setAttribute("sysUserList", userList);
		return new ModelAndView(view);
	}
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}
	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

}
