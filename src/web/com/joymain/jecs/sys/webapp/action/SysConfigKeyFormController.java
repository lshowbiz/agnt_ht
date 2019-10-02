package com.joymain.jecs.sys.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysConfigKey;
import com.joymain.jecs.sys.service.SysConfigKeyManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.listener.StartupListener;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class SysConfigKeyFormController extends BaseFormController {
	private SysConfigKeyManager sysConfigKeyManager = null;

	public void setSysConfigKeyManager(SysConfigKeyManager sysConfigKeyManager) {
		this.sysConfigKeyManager = sysConfigKeyManager;
	}

	public SysConfigKeyFormController() {
		setCommandName("sysConfigKey");
		setCommandClass(SysConfigKey.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String keyId = request.getParameter("keyId");
		SysConfigKey sysConfigKey = null;

		if (!StringUtils.isEmpty(keyId)) {
			sysConfigKey = sysConfigKeyManager.getSysConfigKey(keyId);
		} else {
			sysConfigKey = new SysConfigKey();
		}

		return sysConfigKey;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysConfigKey sysConfigKey = (SysConfigKey) command;
		boolean isNew = (sysConfigKey.getKeyId() == null);

		if ("deleteSysConfigKey".equalsIgnoreCase(request.getParameter("strAction"))) {
			sysConfigKeyManager.removeSysConfigKey(sysConfigKey.getKeyId().toString());
			StartupListener.setupContext(request.getSession().getServletContext(),true);

			saveMessage(request, getText("sysConfigKey.deleted"));
		} else {
			sysConfigKey.setConfigCode(sysConfigKey.getConfigCode().toLowerCase());
			
			if ("addSysConfigKey".equalsIgnoreCase(request.getParameter("strAction"))) {
				// 判断是否存在
				SysConfigKey oldSysConfigKey = this.sysConfigKeyManager.getSysConfigKeyByCode(sysConfigKey.getConfigCode());
				if (oldSysConfigKey != null) {
					// 存在
					errors.reject("sysConfigKey.configCodeExits", new Object[] {},LocaleUtil.getLocalText("sysConfigKey.configCodeExits"));
					return showForm(request, response, errors);
				}
			}
			
			sysConfigKeyManager.saveSysConfigKey(sysConfigKey);
			
			StartupListener.setupContext(request.getSession().getServletContext(),true);

			String key = (isNew) ? "sysConfigKey.added" : "sysConfigKey.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}
