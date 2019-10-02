package com.joymain.jecs.al.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCharacterCodingManager;

public class AlCharacterCodingController implements Controller {
	private final Log log = LogFactory.getLog(AlCharacterCodingController.class);
	private AlCharacterCodingManager alCharacterCodingManager = null;

	public void setAlCharacterCodingManager(AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		List alCharacterCodings = alCharacterCodingManager.getAlCharacterCodings(null);

		return new ModelAndView("al/alCharacterCodingList", Constants.ALCHARACTERCODING_LIST, alCharacterCodings);
	}
}
