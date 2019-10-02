package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.sys.service.SysManagerUserManager;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysMyManTreeController implements Controller {
	private final Log log = LogFactory.getLog(SysMyManTreeController.class);
	private AlCompanyManager alCompanyManager = null;
	private SysDepartmentManager sysDepartmentManager;
	private SysManagerUserManager sysManagerUserManager = null;

	public void setSysManagerUserManager(SysManagerUserManager sysManagerUserManager) {
		this.sysManagerUserManager = sysManagerUserManager;
	}
	
	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//读取所管理的人员所处公司
		List alCompanys= this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request),request.getParameter("companyCode"),false);
		request.setAttribute("alCompanys", alCompanys);
		//读取管理人员所能管理的部门
		List sysDepartments=this.sysDepartmentManager.getMyAllDepartments(SessionLogin.getLoginUser(request),request.getParameter("companyCode"),false);
		request.setAttribute("sysDepartments", sysDepartments);
		//读取对应人员信息
		List sysManagers =  this.sysManagerUserManager.getSysManagersWithJoin(SessionLogin.getLoginUser(request), null,request.getParameter("companyCode"),false);
		request.setAttribute("sysManagers", sysManagers);

		return new ModelAndView("sys/sys_my_man_tree");
	}
}