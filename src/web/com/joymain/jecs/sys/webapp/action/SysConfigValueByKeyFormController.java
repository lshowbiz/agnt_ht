package com.joymain.jecs.sys.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysConfigKey;
import com.joymain.jecs.sys.model.SysConfigValue;
import com.joymain.jecs.sys.service.SysConfigKeyManager;
import com.joymain.jecs.sys.service.SysConfigValueManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.listener.StartupListener;

public class SysConfigValueByKeyFormController extends BaseFormController {
	private SysConfigValueManager sysConfigValueManager = null;
	private SysConfigKeyManager sysConfigKeyManager = null;

	public void setSysConfigKeyManager(SysConfigKeyManager sysConfigKeyManager) {
		this.sysConfigKeyManager = sysConfigKeyManager;
	}

	public void setSysConfigValueManager(SysConfigValueManager sysConfigValueManager) {
		this.sysConfigValueManager = sysConfigValueManager;
	}

	public SysConfigValueByKeyFormController() {
		setCommandName("sysConfigValue");
		setCommandClass(SysConfigValue.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String configCode = request.getParameter("strAction");
		SysConfigKey sysConfigKey=sysConfigKeyManager.getSysConfigKeyByCode(configCode);
		
		
		List	sysConfigValues = sysConfigValueManager.getSysConfigValuesAll(sysConfigKey.getKeyId());
	

		request.setAttribute("sysConfigValues", sysConfigValues);

		return new SysConfigValue();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		Locale locale = request.getLocale();
		String configCode = request.getParameter("strAction");
		SysConfigKey sysConfigKey=sysConfigKeyManager.getSysConfigKeyByCode(configCode);
		if (request.getParameter("save") != null) {
			String[] valueIds = request.getParameterValues("valueId1");
			String[] configValues = request.getParameterValues("configValue");
			String[] companyCodes = request.getParameterValues("companyCode");

			try {
				if (valueIds != null && valueIds.length > 0) {
//					SysConfigKey sysConfigKey = sysConfigKeyManager.getSysConfigKey(keyId);
					List<SysConfigValue> sysConfigValues = new ArrayList<SysConfigValue>();
					for (int i = 0; i < valueIds.length; i++) {
						SysConfigValue sysConfigValue = new SysConfigValue();
						if (!StringUtils.isEmpty(valueIds[i])) {
							sysConfigValue = this.sysConfigValueManager.getSysConfigValue(valueIds[i]);
						} else {
							sysConfigValue.setValueId(null);
						}
						sysConfigValue.setSysConfigKey(sysConfigKey);
						sysConfigValue.setCompanyCode(companyCodes[i]);
						sysConfigValue.setConfigValue(configValues[i]);
						sysConfigValues.add(sysConfigValue);
					}
					this.sysConfigValueManager.saveSysConfigValues(sysConfigValues);
					StartupListener.setupContext(request.getSession().getServletContext(),true);
					saveMessage(request, getText("sysConfigValue.update"));
				}
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}
		return new ModelAndView("redirect:editSysConfigValue.html", "strAction", configCode);
	}
}
