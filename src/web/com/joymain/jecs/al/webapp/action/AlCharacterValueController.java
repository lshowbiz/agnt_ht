package com.joymain.jecs.al.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCharacterKey;
import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.al.service.AlCharacterKeyManager;
import com.joymain.jecs.al.service.AlCharacterValueManager;

public class AlCharacterValueController implements Controller {
	private final Log log = LogFactory.getLog(AlCharacterValueController.class);
	private AlCharacterValueManager alCharacterValueManager = null;
	private AlCharacterKeyManager alCharacterKeyManager = null;

	public void setAlCharacterKeyManager(AlCharacterKeyManager alCharacterKeyManager) {
		this.alCharacterKeyManager = alCharacterKeyManager;
	}

	public void setAlCharacterValueManager(AlCharacterValueManager alCharacterValueManager) {
		this.alCharacterValueManager = alCharacterValueManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String keyId = request.getParameter("keyId");
		if ("post".equalsIgnoreCase(request.getMethod())) {
			String[] valueIds = request.getParameterValues("valueId");
			String[] characterValues = request.getParameterValues("characterValue");
			String[] characterCodes = request.getParameterValues("characterCode");

			if (valueIds != null && valueIds.length > 0) {
				AlCharacterKey alCharacterKey = alCharacterKeyManager.getAlCharacterKey(keyId);
				for (int i = 0; i < valueIds.length; i++) {
					AlCharacterValue alCharacterValue = new AlCharacterValue();
					if (!StringUtils.isEmpty(valueIds[i])) {
						alCharacterValue = this.alCharacterValueManager.getAlCharacterValue(valueIds[i]);
					}else{
						alCharacterValue.setValueId(null);
					}
					alCharacterValue.setAlCharacterKey(alCharacterKey);
					alCharacterValue.setCharacterCode(characterCodes[i]);
					alCharacterValue.setCharacterValue(characterValues[i]);
				}
				return new ModelAndView("redirect:alCharacterKeys.html", "keyId", alCharacterKey.getKeyId());
			}
		}
		List alCharacterValues = new ArrayList();
		if (!StringUtils.isEmpty(keyId)) {
			alCharacterValues = alCharacterValueManager.getAlCharacterValuesAll(new Long(keyId));
		}

		return new ModelAndView("al/alCharacterValueList", Constants.ALCHARACTERVALUE_LIST, alCharacterValues);
	}
}
