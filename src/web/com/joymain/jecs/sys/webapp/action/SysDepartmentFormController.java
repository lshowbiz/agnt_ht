package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysDepartment;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.sys.service.SysManagerManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysDepartmentFormController extends BaseFormController {
	private SysDepartmentManager sysDepartmentManager = null;
	private SysManagerManager sysManagerManager = null;
	
	public void setSysManagerManager(SysManagerManager sysManagerManager) {
		this.sysManagerManager = sysManagerManager;
	}

	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public SysDepartmentFormController() {
		setCommandName("sysDepartment");
		setCommandClass(SysDepartment.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		SysDepartment sysDepartment = null;
		SysDepartment parentSysDepartment = null;
		if ("addSysDepartment".equals(request.getParameter("strAction"))) {
			String companyCode = request.getParameter("companyCode");
			String parentId = request.getParameter("parentId");
			if (StringUtils.isEmpty(companyCode) || StringUtils.isEmpty(parentId)) {
				throw new AppException("缺少参数");
			}
			sysDepartment = new SysDepartment();
			sysDepartment.setCompanyCode(request.getParameter("companyCode"));
			sysDepartment.setParentId(new Long(parentId));

			parentSysDepartment = this.sysDepartmentManager.getSysDepartment(parentId);
		} else {
			String departmentId = request.getParameter("departmentId");
			sysDepartment = sysDepartmentManager.getSysDepartment(departmentId);
			parentSysDepartment = this.sysDepartmentManager.getSysDepartment(sysDepartment.getParentId().toString());
		}
		request.setAttribute("parentSysDepartment", parentSysDepartment);

		return sysDepartment;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysDepartment sysDepartment = (SysDepartment) command;

		if ("deleteSysDepartment".equals(request.getParameter("strAction"))) {
			List childSysDepartments = this.sysDepartmentManager.getDirectChildDepartments(sysDepartment.getDepartmentId().toString(), "orderNo");
			List sysManagers=this.sysManagerManager.getSysManagersByDepartment(sysDepartment.getDepartmentId().toString());
			if ((childSysDepartments != null && !childSysDepartments.isEmpty()) || (sysManagers!=null && !sysManagers.isEmpty())) {
				errors.reject("sysDepartment.error.child.exists");
				return showForm(request, response, errors);
			}
			sysDepartmentManager.removeSysDepartment(sysDepartment.getDepartmentId().toString());

			saveMessage(request, getText("sysDepartment.deleted"));
		} else {
			String key = "sysDepartment.updated";
			if ("addSysDepartment".equals(request.getParameter("strAction"))) {
				key = "sysDepartment.added";

				String parentId = request.getParameter("parentId");
				SysDepartment parentSysDepartment = null;
				if (!StringUtils.isEmpty(parentId)) {
					parentSysDepartment = this.sysDepartmentManager.getSysDepartment(parentId);
				}
				if (parentSysDepartment != null) {
					sysDepartment.setParentId(new Long(parentId));
				} else {
					sysDepartment.setParentId(new Long(0));
				}
				sysDepartment.setAllowedUser(SessionLogin.getLoginUser(request).getUserCode());
			}
			sysDepartmentManager.saveSysDepartment(sysDepartment);
			sysDepartmentManager.saveSysDepartmentsRebuild(sysDepartment.getCompanyCode());

			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("companyCode", request.getParameter("companyCode"));
		mv.addObject("parentId", request.getParameter("parentId"));
		return mv;
	}
	
	/*public void onBindAndValidate(HttpServletRequest request, Object command, BindException errors){
	}*/
}