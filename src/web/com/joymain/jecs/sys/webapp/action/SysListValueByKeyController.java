package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysListKey;
import com.joymain.jecs.sys.service.SysListKeyManager;
import com.joymain.jecs.sys.service.SysListValueManager;

public class SysListValueByKeyController implements Controller {
	private final Log log = LogFactory.getLog(SysListValueByKeyController.class);
	private SysListValueManager sysListValueManager = null;
	private SysListKeyManager sysListKeyManager = null;

	public void setSysListKeyManager(SysListKeyManager sysListKeyManager) {
		this.sysListKeyManager = sysListKeyManager;
	}

	public void setSysListValueManager(SysListValueManager sysListValueManager) {
		this.sysListValueManager = sysListValueManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		String listCode=request.getParameter("strAction");
		SysListKey sysListKey=sysListKeyManager.getSysListKeyByCode(listCode);
		List sysListValues=sysListValueManager.getSysListValuesByKeyId(sysListKey.getKeyId().toString());
		request.setAttribute("keyId", sysListKey.getKeyId());
		return new ModelAndView("sys/sysListValueByKeyList", Constants.SYSLISTVALUE_LIST, sysListValues);
	}
}
