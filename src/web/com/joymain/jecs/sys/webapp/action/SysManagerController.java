package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.model.SysDepartment;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.sys.service.SysManagerManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysManagerController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysManagerController.class);
	private SysManagerManager sysManagerManager = null;
	private SysDepartmentManager sysDepartmentManager = null;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public void setSysManagerManager(SysManagerManager sysManagerManager) {
		this.sysManagerManager = sysManagerManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("sysManagerListTable", request, 20);
		crm.addField(new CustomField("masterUserCode", 1111, SessionLogin.getLoginUser(request).getUserCode()));
		
		List sysManagers = sysManagerManager.getSysManagersByCrm(crm, pager);
		request.setAttribute("sysManagerListTable_totalRows", pager.getTotalObjects());
		
		String departmentId=request.getParameter("departmentId");
		if(!StringUtils.isEmpty(departmentId)){
			SysDepartment sysDepartment=this.sysDepartmentManager.getSysDepartment(departmentId);
			request.setAttribute("sysDepartment", sysDepartment);
			
			//判断当前登录人是否有权限管理此部门
			boolean hasPermit = false;
			if (SessionLogin.getLoginUser(request).getIsAdmin()) {
				hasPermit = true;
			} else {
				if (sysDepartment != null && !StringUtils.isEmpty(sysDepartment.getAllowedUser())) {
					String[] allowedUsers = sysDepartment.getAllowedUser().split(",");
					if (StringUtil.hasString(allowedUsers, SessionLogin.getLoginUser(request).getUserCode(), false)) {
						hasPermit = true;
					}
				}
			}

			request.setAttribute("hasPermit", hasPermit);
		}
		
		String companyCode=request.getParameter("companyCode");
		if(!StringUtils.isEmpty(companyCode)){
			AlCompany alCompany=this.alCompanyManager.getAlCompanyByCode(companyCode);
			request.setAttribute("alCompany", alCompany);
		}

		return new ModelAndView("sys/sysManagerList", Constants.SYSMANAGER_LIST, sysManagers);
	}
}
