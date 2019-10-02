package com.joymain.jecs.al.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCharacterCoding;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AlLanguageTreeController implements Controller {
	private final Log log = LogFactory.getLog(AlLanguageTreeController.class);
	private AlCharacterCodingManager alCharacterCodingManager;

	public void setAlCharacterCodingManager(
			AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
		List<AlCharacterCoding> alLanguages = alCharacterCodingManager.getAlCharacterCodings(null);
		List<AlCharacterCoding> canModifyLanguages=new ArrayList<AlCharacterCoding>();
		for (AlCharacterCoding coding : alLanguages) {
			if(!StringUtil.isEmpty(coding.getAllowedUser())&&coding.getAllowedUser().contains(defSysUser.getUserCode())){
				canModifyLanguages.add(coding);
			}
		}

		return new ModelAndView("al/al_language_tree", "alLanguages", canModifyLanguages);
	}
}
