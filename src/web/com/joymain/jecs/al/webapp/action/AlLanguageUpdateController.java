package com.joymain.jecs.al.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCharacterCoding;
import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCharacterValueManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.listener.StartupListener;

public class AlLanguageUpdateController extends BaseFormController {
	private AlCharacterCodingManager alCharacterCodingManager = null;
	private AlCharacterValueManager alCharacterValueManager = null;

	public void setAlCharacterValueManager(AlCharacterValueManager alCharacterValueManager) {
		this.alCharacterValueManager = alCharacterValueManager;
	}

	public void setAlCharacterCodingManager(AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}

	public AlLanguageUpdateController() {
		setCommandName("alCharacterCoding");
		setCommandClass(AlCharacterCoding.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		//AlCharacterCoding alCharacterCoding = this.alCharacterCodingManager.getAlCharacterCodingByCode("zh_TW");
		AlCharacterCoding alCharacterCoding=new AlCharacterCoding();

		List alCharacterCodings = this.alCharacterCodingManager.getAlCharacterCodings(null);
		request.setAttribute("alCharacterCodings", alCharacterCodings);

		return alCharacterCoding;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AlCharacterCoding alCharacterCoding = (AlCharacterCoding) command;

		if ("alLanguageUpdate".equals(request.getParameter("strAction"))) {
			String updateCharacterCode = request.getParameter("updateCharacterCode");
			//清空需更新的语言编码对应的值
			this.alCharacterValueManager.removeAlCharacterValues(updateCharacterCode);

			List alCharacterValues = this.alCharacterValueManager.getAlCharacterValuesByCode(alCharacterCoding.getCharacterCode());
			if (alCharacterValues != null && alCharacterValues.size() > 0) {
				List<AlCharacterValue> newAlCharacterValues = new ArrayList<AlCharacterValue>();
				for (int i = 0; i < alCharacterValues.size(); i++) {
					AlCharacterValue alCharacterValue = (AlCharacterValue) alCharacterValues.get(i);
					AlCharacterValue newAlCharacterValue = new AlCharacterValue();

					newAlCharacterValue.setAlCharacterKey(alCharacterValue.getAlCharacterKey());
					newAlCharacterValue.setCharacterCode(updateCharacterCode);
					newAlCharacterValue.setCharacterValue(alCharacterValue.getCharacterValue());

					newAlCharacterValues.add(newAlCharacterValue);
				}
				this.alCharacterValueManager.saveAlCharacterValues(newAlCharacterValues);
				StartupListener.setupContext(request.getSession().getServletContext(),true);
			}
			this.saveMessage(request, getText("alCharacterValue.update"));
		}

		return new ModelAndView(getSuccessView());
	}
}
