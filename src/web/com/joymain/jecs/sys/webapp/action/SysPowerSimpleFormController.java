package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysManagerModlPowManager;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysPowerSimpleFormController extends BaseFormController {
	private SysModuleManager sysModuleManager = null;
	private AlCompanyManager alCompanyManager = null;
	private SysManagerModlPowManager sysManagerModlPowManager = null;

	public void setSysManagerModlPowManager(SysManagerModlPowManager sysManagerModlPowManager) {
		this.sysManagerModlPowManager = sysManagerModlPowManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public SysPowerSimpleFormController() {
		setCommandName("sysUser");
		setCommandClass(SysUser.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		//读取当前登录人员所拥有权限的公司
		List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), null, false);
		request.setAttribute("alCompanys", alCompanys);

		String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
		if (!StringUtils.isEmpty(request.getParameter("companyCode"))) {
			companyCode = request.getParameter("companyCode");
		}
		request.setAttribute("companyCode", companyCode);

		//读取当前登录人员所拥有的权限
		List sysModules = this.sysModuleManager.getMyManSysModules(SessionLogin.getLoginUser(request).getUserCode(), request
		        .getParameter("userCode"), companyCode);

		request.setAttribute("sysModules", sysModules);

		//request.setAttribute("sysModuleListTable_totalRows", pager.getTotalObjects());

		return new SysUser();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
	        throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("sysPowerSimple".equalsIgnoreCase(request.getParameter("strAction"))) {
			String selectedModule = "";
			String[] moduleIds = request.getParameterValues("moduleId");
			selectedModule = StringUtils.join(moduleIds, ",");
			this.sysManagerModlPowManager.saveSysManagerPowers(SessionLogin.getLoginUser(request).getUserCode(), request
			        .getParameter("userCode"), selectedModule, request.getParameter("companyCode"));
			saveMessage(request, getText("sysModuel.setting.sucess"));
		}

		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}
