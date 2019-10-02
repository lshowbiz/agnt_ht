package com.joymain.jecs.sys.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysId;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class SysIdFormController extends BaseFormController {
	private SysIdManager sysIdManager = null;

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public SysIdFormController() {
		setCommandName("sysId");
		setCommandClass(SysId.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		SysId sysId = null;

		if (!StringUtils.isEmpty(id)) {
			sysId = sysIdManager.getSysId(id);
		} else {
			sysId = new SysId();
		}

		return sysId;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysId sysId = (SysId) command;
		boolean isNew = (sysId.getId() == null);

		if ("deleteSysId".equalsIgnoreCase(request.getParameter("strAction"))) {
			sysIdManager.removeSysId(sysId.getId().toString());

			saveMessage(request, getText("sysId.deleted"));
		} else {
			sysId.setIdCode(sysId.getIdCode().toLowerCase());

			if ("addSysId".equalsIgnoreCase(request.getParameter("strAction"))) {
				// 判断是否存在
				SysId oldSysId = this.sysIdManager.getSysIdByCode(sysId.getIdCode());
				if (oldSysId != null) {
					// 存在
					errors.reject("sysId.idCodeExits", new Object[] {}, LocaleUtil.getLocalText("oldSysId.idCodeExits"));
					return showForm(request, response, errors);
				}
			}

			sysId.setDateValue(request.getParameter("dateValue"));
			sysIdManager.saveSysId(sysId);

			String key = (isNew) ? "sysId.added" : "sysId.updated";
			saveMessage(request, getText(key));

			if (sysId.getFixed().intValue() == 1) {
				return new ModelAndView(getSuccessView());
			}
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}
