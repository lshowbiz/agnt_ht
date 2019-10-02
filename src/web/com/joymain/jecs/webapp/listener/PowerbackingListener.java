package com.joymain.jecs.webapp.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import com.joymain.jecs.util.web.RequestUtil;

public class PowerbackingListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent en) {
		RequestUtil.removeLocalRequest();
	}

	public void requestInitialized(ServletRequestEvent en) {
		RequestUtil.setLocalRequest(en.getServletRequest());
	}
}
