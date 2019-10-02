package com.joymain.jecs.al.webapp.action;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCharacterCoding;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AlCharacterCodingFormController extends BaseFormController {
	private AlCharacterCodingManager alCharacterCodingManager = null;

	public void setAlCharacterCodingManager(AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}

	public AlCharacterCodingFormController() {
		setCommandName("alCharacterCoding");
		setCommandClass(AlCharacterCoding.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String characterId = request.getParameter("characterId");
		AlCharacterCoding alCharacterCoding = null;

		if (!StringUtils.isEmpty(characterId)) {
			alCharacterCoding = alCharacterCodingManager.getAlCharacterCoding(characterId);
		} else {
			alCharacterCoding = new AlCharacterCoding();
		}
		//获取可用的语言编码
		Locale[] locales=Locale.getAvailableLocales();
		String[] currentLanguage=SessionLogin.getLoginUser(request).getDefCharacterCoding().split("_");
		Map<String, String> languageMap=new LinkedHashMap<String, String>();
		for(int i=0;i<locales.length;i++){
			if(!StringUtils.isEmpty(locales[i].getCountry())){
				languageMap.put(locales[i].getLanguage()+"_"+locales[i].getCountry(), locales[i].getDisplayName(new Locale(currentLanguage[0],currentLanguage[1])));
			}
		}
//		languageMap.put("en_PH", getText("lang.en_PH"));
		request.setAttribute("languageMap", languageMap);

		return alCharacterCoding;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AlCharacterCoding alCharacterCoding = (AlCharacterCoding) command;
		boolean isNew = (alCharacterCoding.getCharacterId() == null);

		if ("deleteAlCharacterCoding".equalsIgnoreCase(request.getParameter("strAction"))) {
			alCharacterCodingManager.removeAlCharacterCoding(alCharacterCoding.getCharacterId().toString());

			saveMessage(request, getText("alCharacterCoding.deleted"));
		} else {
			if ("addAlCharacterCoding".equalsIgnoreCase(request.getParameter("strAction"))) {
				// 判断是否存在
				AlCharacterCoding oldAlCharacterCoding = this.alCharacterCodingManager.getAlCharacterCodingByCode(alCharacterCoding.getCharacterCode());
				if (oldAlCharacterCoding != null) {
					// 存在
					errors.rejectValue("characterCode", "error.characterCode.existed");
					return showForm(request, response, errors);
				}
			}
			alCharacterCodingManager.saveAlCharacterCoding(alCharacterCoding);

			String key = (isNew) ? "alCharacterCoding.added" : "alCharacterCoding.updated";
			saveMessage(request, getText(key));
		}

		return new ModelAndView(getSuccessView());
	}
}
