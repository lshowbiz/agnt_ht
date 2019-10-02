package com.joymain.jecs.al.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCharacterKey;
import com.joymain.jecs.al.model.AlCharacterType;
import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.al.service.AlCharacterKeyManager;
import com.joymain.jecs.al.service.AlCharacterTypeManager;
import com.joymain.jecs.al.service.AlCharacterValueManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.listener.StartupListener;

public class AlCharacterKeyFormController extends BaseFormController {
	private AlCharacterKeyManager alCharacterKeyManager = null;
	private AlCharacterTypeManager alCharacterTypeManager = null;
	private AlCharacterValueManager alCharacterValueManager = null;

	public void setAlCharacterValueManager(AlCharacterValueManager alCharacterValueManager) {
		this.alCharacterValueManager = alCharacterValueManager;
	}

	public void setAlCharacterTypeManager(AlCharacterTypeManager alCharacterTypeManager) {
		this.alCharacterTypeManager = alCharacterTypeManager;
	}

	public void setAlCharacterKeyManager(AlCharacterKeyManager alCharacterKeyManager) {
		this.alCharacterKeyManager = alCharacterKeyManager;
	}

	public AlCharacterKeyFormController() {
		setCommandName("alCharacterKey");
		setCommandClass(AlCharacterKey.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String keyId = request.getParameter("keyId");
		AlCharacterKey alCharacterKey = null;

		if (!StringUtils.isEmpty(keyId)) {
			alCharacterKey = alCharacterKeyManager.getAlCharacterKey(keyId);
		} else {
			alCharacterKey = new AlCharacterKey();

			String typeId = request.getParameter("typeId");
			AlCharacterType alCharacterType = new AlCharacterType();
			if (!StringUtils.isEmpty(typeId)) {
				alCharacterType = alCharacterTypeManager.getAlCharacterType(typeId);
			}
			alCharacterKey.setAlCharacterType(alCharacterType);
		}

		return alCharacterKey;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AlCharacterKey alCharacterKey = (AlCharacterKey) command;
		boolean isNew = (alCharacterKey.getKeyId() == null);

		if (request.getParameter("delete") != null) {
			alCharacterKeyManager.removeAlCharacterKey(alCharacterKey.getKeyId().toString());

			StartupListener.setupContext(request.getSession().getServletContext(), true);

			saveMessage(request, getText("alCharacterKey.deleted"));
		} else {
			if (isNew) {
				// 判断是否存在
				AlCharacterKey oldAlCharacterKey = this.alCharacterKeyManager.getAlCharacterKeyByKey(alCharacterKey.getCharacterKey());
				if (oldAlCharacterKey != null) {
					// 存在
					errors.rejectValue("characterKey", "error.characterKey.existed");
					return showForm(request, response, errors);
				}
			}

			String typeId = request.getParameter("typeId");
			AlCharacterType alCharacterType = null;
			if (!StringUtils.isEmpty(typeId)) {
				alCharacterType = alCharacterTypeManager.getAlCharacterType(typeId);
			}
			alCharacterKey.setAlCharacterType(alCharacterType);
			alCharacterKey.setCharacterKey(alCharacterKey.getCharacterKey().trim());

			alCharacterKeyManager.saveAlCharacterKey(alCharacterKey);
			if (isNew) {
				//如果为新增,则将键值说明做为缺省值插入其它语言,以方便操作
				List alCharacterValues = this.alCharacterValueManager.getAlCharacterValuesAll(alCharacterKey.getKeyId());
				if (alCharacterValues != null && alCharacterValues.size() > 0) {
					List<AlCharacterValue> newAlCharacterValues = new ArrayList<AlCharacterValue>();
					for (int i = 0; i < alCharacterValues.size(); i++) {
						AlCharacterValue alCharacterValue = new AlCharacterValue();
						Map alCharacterValueMap = (HashMap) alCharacterValues.get(i);
						alCharacterValue.setValueId(null);
						alCharacterValue.setAlCharacterKey(alCharacterKey);
						alCharacterValue.setCharacterCode((String) alCharacterValueMap.get("character_code"));
						alCharacterValue.setCharacterValue(alCharacterKey.getKeyDesc());
						newAlCharacterValues.add(alCharacterValue);
					}
					this.alCharacterValueManager.saveAlCharacterValues(newAlCharacterValues);
				}
			}

			StartupListener.setupContext(request.getSession().getServletContext(), true);

			String key = (isNew) ? "alCharacterKey.added" : "alCharacterKey.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("strAction", "listAlCharacterKeys");
		mv.addObject("needReload", "1");
		return mv;
	}
}