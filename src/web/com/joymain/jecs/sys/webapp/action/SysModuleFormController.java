package com.joymain.jecs.sys.webapp.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.model.SysCompanyPow;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.model.SysUsrTypePow;
import com.joymain.jecs.sys.service.SysCompanyPowManager;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.sys.service.SysUsrTypePowManager;
import com.joymain.jecs.sys.service.SysVisitLogManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysModuleFormController extends BaseFormController {
	private SysModuleManager sysModuleManager = null;
	private AlCompanyManager alCompanyManager = null;
	private SysCompanyPowManager sysCompanyPowManager = null;
	private SysUsrTypePowManager sysUsrTypePowManager = null;
	private SysVisitLogManager sysVisitLogManager = null;

    public void setSysVisitLogManager(SysVisitLogManager sysVisitLogManager) {
        this.sysVisitLogManager = sysVisitLogManager;
    }

	public void setSysUsrTypePowManager(SysUsrTypePowManager sysUsrTypePowManager) {
		this.sysUsrTypePowManager = sysUsrTypePowManager;
	}

	public void setSysCompanyPowManager(SysCompanyPowManager sysCompanyPowManager) {
		this.sysCompanyPowManager = sysCompanyPowManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

	public SysModuleFormController() {
		setCommandName("sysModule");
		setCommandClass(SysModule.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		SysModule sysModule = null;
		SysModule parentSysModule = null;
		if ("addSysModule".equals(request.getParameter("strAction"))) {
			sysModule = new SysModule();
			String parentId = request.getParameter("parentId");
			if (!StringUtils.isEmpty(parentId)) {
				parentSysModule = this.sysModuleManager.getSysModule(parentId);
				sysModule.setParentId(new Long(parentId));
			} else {
				sysModule.setParentId(new Long(0));
			}
		} else if ("editSysModule".equals(request.getParameter("strAction")) || "deleteSysModule".equals(request.getParameter("strAction"))) {
			String moduleId = request.getParameter("moduleId");
			sysModule = sysModuleManager.getSysModule(moduleId);
			parentSysModule = this.sysModuleManager.getSysModule(sysModule.getParentId().toString());
		}
		if (parentSysModule == null) {
			parentSysModule = new SysModule();
			parentSysModule.setModuleName("node.root.name");
		}
		request.setAttribute("parentSysModule", parentSysModule);

		Map userTypes = ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "user_type");
		request.setAttribute("userTypes", userTypes);

		List alCompanys = alCompanyManager.getAlCompanys(null);
		request.setAttribute("alCompanys", alCompanys);

		return sysModule;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysModule sysModule = (SysModule) command;

		if ("deleteSysModule".equals(request.getParameter("strAction"))) {
			List childSysModules = this.sysModuleManager.getDirectChildModules(sysModule.getModuleId().toString(), "orderNo");
			if (childSysModules != null && !childSysModules.isEmpty()) {
				errors.reject("sysModule.error.child.exists");
				return showForm(request, response, errors);
			}
			sysModuleManager.removeSysModule(sysModule.getModuleId().toString());

			//重新整理模块库
			this.sysModuleManager.saveSysModulesRebuild();
			//删除所有访问记录, 避免用户访问不了最新的模块
			this.sysVisitLogManager.removeAllSysVisitLog();

			saveMessage(request, getText("sysModule.deleted"));
		} else {
			String key = "sysModule.updated";

			String[] companyCodes = request.getParameterValues("companyCode");
			String[] userTypes = request.getParameterValues("userType");

			if ("addSysModule".equals(request.getParameter("strAction"))) {
				key = "sysModule.added";

				String parentId = request.getParameter("parentId");
				SysModule parentSysModule = null;
				if (!StringUtils.isEmpty(parentId)) {
					parentSysModule = this.sysModuleManager.getSysModule(parentId);
				}
				if (parentSysModule != null) {
					sysModule.setParentId(new Long(parentId));
				} else {
					sysModule.setParentId(new Long(0));
				}

				//验证是否存在
				SysModule oldSysModule = this.sysModuleManager.getSysModuleByCode(sysModule.getModuleCode());
				if (oldSysModule != null) {
					// 存在
					errors.rejectValue("moduleCode", "sysModule.moduleCode.exists");
					return showForm(request, response, errors);
				}
			} else if ("editSysModule".equals(request.getParameter("strAction"))) {
				//验证是否存在
				SysModule oldSysModule = this.sysModuleManager.getSysModuleByCode(sysModule.getModuleCode());
				if (oldSysModule != null && oldSysModule.getModuleId().longValue() != sysModule.getModuleId().longValue()) {
					// 存在
					errors.rejectValue("moduleCode", "sysModule.moduleCode.exists");
					return showForm(request, response, errors);
				}
				//验证所选择的父模块是否是自己的子模块
				if (sysModule.getParentId() != null && sysModule.getParentId().longValue() != 0) {
					SysModule parentSysModule = this.sysModuleManager.getSysModule(sysModule.getParentId().toString());
					if (StringUtils.contains(parentSysModule.getTreeIndex(), sysModule.getTreeIndex())) {
						// 存在
						errors.reject("sysModule.error.cannot.select.child");
						return showForm(request, response, errors);
					}
				}

				this.sysCompanyPowManager.removeSysCompanyPowsNotInCompany(sysModule.getModuleId().toString(), companyCodes);
				this.sysUsrTypePowManager.removeSysUsrTypePowsNotInUsrType(sysModule.getModuleId().toString(), userTypes);
			}

			//保存可以使用的公司记录
			if (companyCodes != null) {
				HashSet<SysCompanyPow> sysCompanyPows = new HashSet<SysCompanyPow>();
				for (int i = 0; i < companyCodes.length; i++) {
					SysCompanyPow sysCompanyPow = null;
					if (sysModule.getModuleId() != null) {
						sysCompanyPow = this.sysCompanyPowManager.getSysCompanyPow(sysModule.getModuleId().toString(), companyCodes[i]);
					}
					if (sysCompanyPow == null) {
						sysCompanyPow = new SysCompanyPow();
					}
					sysCompanyPow.setCompanyCode(companyCodes[i]);
					sysCompanyPow.setSysModule(sysModule);

					sysCompanyPows.add(sysCompanyPow);
				}
				sysModule.setSysCompanyPows(sysCompanyPows);
			}
			//保存可以使用的用户类型
			if (userTypes != null) {
				HashSet<SysUsrTypePow> sysUsrTypePows = new HashSet<SysUsrTypePow>();
				for (int i = 0; i < userTypes.length; i++) {
					SysUsrTypePow sysUsrTypePow = null;
					if (sysModule.getModuleId() != null) {
						sysUsrTypePow = this.sysUsrTypePowManager.getSysUsrTypePow(sysModule.getModuleId().toString(), userTypes[i]);
					}
					if (sysUsrTypePow == null) {
						sysUsrTypePow = new SysUsrTypePow();
					}
					sysUsrTypePow.setUserType(userTypes[i]);
					sysUsrTypePow.setSysModule(sysModule);

					sysUsrTypePows.add(sysUsrTypePow);
				}
				sysModule.setSysUsrTypePows(sysUsrTypePows);
			}

			sysModuleManager.saveSysModule(sysModule);

			//重新整理模块库
			this.sysModuleManager.saveSysModulesRebuild();
			//删除所有访问记录, 避免用户访问不了最新的模块
			this.sysVisitLogManager.removeAllSysVisitLog();

			saveMessage(request, getText(key));
		}

		ModelAndView mv = new ModelAndView(getSuccessView());
		mv.addObject("parentId", request.getParameter("parentId"));
		return mv;
	}
}