package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.service.SysManagerModlPowManager;
import com.joymain.jecs.sys.service.SysManagerUserManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysPowerCloneFormController extends BaseFormController {
	private AlCompanyManager alCompanyManager = null;
	private SysManagerUserManager sysManagerUserManager = null;
	private SysManagerModlPowManager sysManagerModlPowManager = null;

	public void setSysManagerModlPowManager(SysManagerModlPowManager sysManagerModlPowManager) {
		this.sysManagerModlPowManager = sysManagerModlPowManager;
	}

	public void setSysManagerUserManager(SysManagerUserManager sysManagerUserManager) {
		this.sysManagerUserManager = sysManagerUserManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public SysPowerCloneFormController() {
		setCommandName("sysModule");
		setCommandClass(SysModule.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		//读取所管理的人员所处公司
		List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), null, false);
		request.setAttribute("alCompanys", alCompanys);
		//读取对应人员信息
		List sysManagers = this.sysManagerUserManager.getSysManagersWithJoin(SessionLogin.getLoginUser(request), null, null,
		        false);
		request.setAttribute("sysManagers", sysManagers);

		return new SysModule();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
	        throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("byUser".equals(request.getParameter("doType"))) {
			String fromUser = request.getParameter("fromUser");
			String[] fromUserCompanys = request.getParameterValues("fromUserCompany");
			String[] toUsers = request.getParameterValues("toUser");

			this.sysManagerModlPowManager.saveSysPowerCloneByUser(fromUser, fromUserCompanys, toUsers);

			saveMessage(request, getText("power.cloned.successfully"));
		} else if ("byCompany".equals(request.getParameter("doType"))) {
			String fromUser = request.getParameter("fromUser");
			String fromUserCompany = request.getParameter("fromUserCompany");
			String[] toUserCompanys = request.getParameterValues("toUserCompany");

			this.sysManagerModlPowManager.saveSysPowerCloneByCompany(fromUser, fromUserCompany, toUserCompanys);

			saveMessage(request, getText("power.cloned.successfully"));
		}

		return new ModelAndView(getSuccessView());
	}
}
