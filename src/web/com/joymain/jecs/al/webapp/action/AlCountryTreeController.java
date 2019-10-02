package com.joymain.jecs.al.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.service.AlCountryManager;

public class AlCountryTreeController implements Controller {
	private final Log log = LogFactory.getLog(AlCountryTreeController.class);
	private AlCountryManager alCountryManager = null;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		List alCountrys = alCountryManager.getAlCountrys(null);

		return new ModelAndView("al/al_country_tree", "alCountrys", alCountrys);
	}
}
