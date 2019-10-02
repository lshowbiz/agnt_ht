package com.joymain.jecs.al.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCharacterKey;
import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.al.service.AlCharacterKeyManager;
import com.joymain.jecs.al.service.AlCharacterValueManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.listener.StartupListener;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AlCharacterValueFormController extends BaseFormController {
	private AlCharacterValueManager alCharacterValueManager = null;
	private AlCharacterKeyManager alCharacterKeyManager = null;

	public void setAlCharacterKeyManager(AlCharacterKeyManager alCharacterKeyManager) {
		this.alCharacterKeyManager = alCharacterKeyManager;
	}

	public void setAlCharacterValueManager(AlCharacterValueManager alCharacterValueManager) {
		this.alCharacterValueManager = alCharacterValueManager;
	}

	public AlCharacterValueFormController() {
		setCommandName("alCharacterValue");
		setCommandClass(AlCharacterValue.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String keyId = request.getParameter("keyId");
		List alCharacterValues = null;
		if (!StringUtils.isEmpty(keyId)) {
			alCharacterValues = alCharacterValueManager.getAlCharacterValuesAll(new Long(keyId), SessionLogin.getLoginUser(request).getUserCode());
		}

		request.setAttribute("alCharacterValues", alCharacterValues);

		return new AlCharacterValue();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		System.out.println(errors);
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		String keyId = request.getParameter("keyId");
		if (request.getParameter("save") != null) {
			String[] valueIds = request.getParameterValues("valueId1");
			String[] characterValues = request.getParameterValues("characterValue");
			String[] characterCodes = request.getParameterValues("characterCode");

			try {
				if (valueIds != null && valueIds.length > 0) {
					AlCharacterKey alCharacterKey = alCharacterKeyManager.getAlCharacterKey(keyId);
					List<AlCharacterValue> alCharacterValues = new ArrayList<AlCharacterValue>();
					for (int i = 0; i < valueIds.length; i++) {
						AlCharacterValue alCharacterValue = new AlCharacterValue();
						if (!StringUtils.isEmpty(valueIds[i])) {
							alCharacterValue = this.alCharacterValueManager.getAlCharacterValue(valueIds[i]);
						} else {
							alCharacterValue.setValueId(null);
						}
						alCharacterValue.setAlCharacterKey(alCharacterKey);
						alCharacterValue.setCharacterCode(characterCodes[i]);
						alCharacterValue.setCharacterValue(characterValues[i]);
						alCharacterValues.add(alCharacterValue);
					}
					this.alCharacterValueManager.saveAlCharacterValues(alCharacterValues);
					StartupListener.setupContext(request.getSession().getServletContext(),true);
					saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),"alCharacterValue.update"));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return new ModelAndView("redirect:editAlCharacterValue.html", "keyId", keyId);
	}
}
