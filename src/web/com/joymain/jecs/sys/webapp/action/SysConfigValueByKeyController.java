package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysConfigKey;
import com.joymain.jecs.sys.service.SysConfigKeyManager;
import com.joymain.jecs.sys.service.SysConfigValueManager;

public class SysConfigValueByKeyController implements Controller {

	private SysConfigKeyManager sysConfigKeyManager = null;
	private SysConfigValueManager sysConfigValueManager = null;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String configCode=request.getParameter("strAction");
		SysConfigKey sysConfigKey = sysConfigKeyManager.getSysConfigKey(configCode);
		List sysConfigValues = sysConfigValueManager.getSysConfigValuesAll(new Long(configCode));
		request.setAttribute("sysConfigValues", sysConfigValues);
		return null;
	}

	public void setSysConfigKeyManager(SysConfigKeyManager sysConfigKeyManager) {
		this.sysConfigKeyManager = sysConfigKeyManager;
	}

	public void setSysConfigValueManager(SysConfigValueManager sysConfigValueManager) {
		this.sysConfigValueManager = sysConfigValueManager;
	}

}
