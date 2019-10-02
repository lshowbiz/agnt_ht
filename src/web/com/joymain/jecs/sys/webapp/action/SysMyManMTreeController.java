package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.sys.service.SysManagerUserManager;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysMyManMTreeController implements Controller {
	private final Log log = LogFactory.getLog(SysMyManMTreeController.class);
	private AlCompanyManager alCompanyManager = null;
	private SysDepartmentManager sysDepartmentManager = null;
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
		String view = "sys/sys_my_man_m_tree";
		String moduleId = request.getParameter("moduleId");
		//Modify By WuCF 20140506
		//仓库权限控制修改成类似系统菜单权限分配
		String warehouseNo = request.getParameter("warehouseNo");
		String strAction = request.getParameter("strAction");
		if("pdWarehouseUserTree".equals(strAction)){//1.仓库权限管理：批量分配
			//读取当前操作人所管理的人员所处的公司
			String companyCode = request.getParameter("companyCode");
			if(companyCode==null || "".equals(companyCode)){
				companyCode = "CN";
			}
			List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), companyCode,false);
			request.setAttribute("alCompanys", alCompanys);
			//读取当前操作人所管理的人员所处的部门
			List sysDepartments = this.sysDepartmentManager.getMyAllDepartments(SessionLogin.getLoginUser(request), companyCode,false);
			request.setAttribute("sysDepartments", sysDepartments);

			//读取对应人员信息
			List sysManagers = this.sysManagerUserManager.getPdWarehouseWithPowerJoin(SessionLogin.getLoginUser(request), warehouseNo, companyCode,false);
			request.setAttribute("sysManagers", sysManagers);
			
			request.setAttribute("companyCode", request.getParameter("companyCode"));
			request.setAttribute("warehouseNo", warehouseNo);
			view = "sys/sys_pdwarehouse_tree";//?strAction=pdWarehouseUserTree
//			view = "redirect:sys/sys_pdwarehouse_tree";//?strAction=pdWarehouseUserTree
		}else if("pdWarehouseUserTree2".equals(strAction)){//EC需求优化5、仓库权限新增以员工账号为单位勾选仓库信息
			//读取当前操作人所管理的人员所处的公司
			String companyCode = request.getParameter("companyCode");
			if(companyCode==null || "".equals(companyCode)){
				companyCode = "CN";
			}
			List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), companyCode,false);
			request.setAttribute("alCompanys", alCompanys);
			//读取当前操作人所管理的人员所处的部门
			List sysDepartments = this.sysDepartmentManager.getMyAllDepartments(SessionLogin.getLoginUser(request), companyCode,false);
			request.setAttribute("sysDepartments", sysDepartments);

			//读取对应人员信息
			List sysManagers = this.sysManagerUserManager.getPdWarehouseWithPowerJoin(SessionLogin.getLoginUser(request), "", companyCode,false);
			request.setAttribute("sysManagers", sysManagers);
			
			request.setAttribute("companyCode", request.getParameter("companyCode"));
			request.setAttribute("warehouseNo", warehouseNo);
			view = "sys/sys_pdwarehouse_tree2";
		}else{//2.系统菜单权限分配
			if (!StringUtils.isEmpty(moduleId)) {
				//读取当前操作人所管理的人员所处的公司
				List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), request.getParameter("companyCode"),false);
				request.setAttribute("alCompanys", alCompanys);
				//读取当前操作人所管理的人员所处的部门
				List sysDepartments = this.sysDepartmentManager.getMyAllDepartments(SessionLogin.getLoginUser(request), request.getParameter("companyCode"),false);
				request.setAttribute("sysDepartments", sysDepartments);
	
				//读取对应人员信息
				List sysManagers = this.sysManagerUserManager.getSysManagersWithPowerJoin(SessionLogin.getLoginUser(request), moduleId, request.getParameter("companyCode"),false);
				request.setAttribute("sysManagers", sysManagers);
			}
		}

		return new ModelAndView(view);
	}
}