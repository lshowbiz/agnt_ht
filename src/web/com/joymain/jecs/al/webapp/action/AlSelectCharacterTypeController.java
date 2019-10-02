package com.joymain.jecs.al.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCharacterTypeManager;

public class AlSelectCharacterTypeController implements Controller {
	private final Log log = LogFactory.getLog(AlSelectCharacterTypeController.class);
	private AlCharacterTypeManager alCharacterTypeManager = null;

	public void setAlCharacterTypeManager(AlCharacterTypeManager alCharacterTypeManager) {
		this.alCharacterTypeManager = alCharacterTypeManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		List alCharacterTypes = alCharacterTypeManager.getAlCharacterTypes(null);

		return new ModelAndView("al/al_select_character_type", Constants.ALCHARACTERTYPE_LIST, alCharacterTypes);
	}
}
