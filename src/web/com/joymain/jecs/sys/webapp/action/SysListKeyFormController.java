package com.joymain.jecs.sys.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysListKey;
import com.joymain.jecs.sys.service.SysListKeyManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class SysListKeyFormController extends BaseFormController {
	private SysListKeyManager sysListKeyManager = null;

	public void setSysListKeyManager(SysListKeyManager sysListKeyManager) {
		this.sysListKeyManager = sysListKeyManager;
	}

	public SysListKeyFormController() {
		setCommandName("sysListKey");
		setCommandClass(SysListKey.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String keyId = request.getParameter("keyId");
		SysListKey sysListKey = null;

		if (!StringUtils.isEmpty(keyId)) {
			sysListKey = sysListKeyManager.getSysListKey(keyId);
		} else {
			sysListKey = new SysListKey();
			sysListKey.setIsReadOnly(0);
		}

		return sysListKey;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysListKey sysListKey = (SysListKey) command;
		boolean isNew = (sysListKey.getKeyId() == null);

		if ("deleteSysListKey".equalsIgnoreCase(request.getParameter("strAction"))) {
			sysListKeyManager.removeSysListKey(sysListKey.getKeyId().toString());

			saveMessage(request, getText("sysListKey.deleted"));
		} else {
			sysListKey.setListCode(sysListKey.getListCode().toLowerCase());
			
			if ("addSysListKey".equalsIgnoreCase(request.getParameter("strAction"))) {
				// 判断是否存在
				SysListKey oldSysListKey = this.sysListKeyManager.getSysListKeyByCode(sysListKey.getListCode());
				if (oldSysListKey != null) {
					// 存在
					errors.reject("sysListKey.listCodeExits", new Object[] {},LocaleUtil.getLocalText("sysListKey.listCodeExits"));
					return showForm(request, response, errors);
				}
			}
			if(sysListKey.getIsReadOnly()==null){
				sysListKey.setIsReadOnly(0);
			}
			
			sysListKeyManager.saveSysListKey(sysListKey);

			String key = (isNew) ? "sysListKey.added" : "sysListKey.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}