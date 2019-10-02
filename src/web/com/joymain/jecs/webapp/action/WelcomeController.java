package com.joymain.jecs.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
 
/**
 * 框架页面
 * @author Aidy.Q
 *
 */
public class WelcomeController implements Controller {
	private final Log log = LogFactory.getLog(WelcomeController.class);

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		
		return new ModelAndView("welcome");
	}
}
