package com.joymain.jecs.al.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCharacterType;
import com.joymain.jecs.al.service.AlCharacterTypeManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class AlCharacterTypeFormController extends BaseFormController {
	private AlCharacterTypeManager alCharacterTypeManager = null;

	public void setAlCharacterTypeManager(AlCharacterTypeManager alCharacterTypeManager) {
		this.alCharacterTypeManager = alCharacterTypeManager;
	}

	public AlCharacterTypeFormController() {
		setCommandName("alCharacterType");
		setCommandClass(AlCharacterType.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String typeId = request.getParameter("typeId");

		AlCharacterType alCharacterType = null;

		if (!StringUtils.isEmpty(typeId)) {
			alCharacterType = alCharacterTypeManager.getAlCharacterType(typeId);
			
			AlCharacterType parentAlCharacterType = new AlCharacterType();
			if (!StringUtils.isEmpty(alCharacterType.getParentId().toString())) {
				parentAlCharacterType = alCharacterTypeManager.getAlCharacterType(alCharacterType.getParentId().toString());
			}
			request.setAttribute("parentAlCharacterType", parentAlCharacterType);
		} else {
			String parentId = request.getParameter("parentId");
			alCharacterType = new AlCharacterType();

			AlCharacterType parentAlCharacterType = new AlCharacterType();
			if (!StringUtils.isEmpty(parentId)) {
				parentAlCharacterType = alCharacterTypeManager.getAlCharacterType(parentId);
			}
			request.setAttribute("parentAlCharacterType", parentAlCharacterType);
		}

		return alCharacterType;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AlCharacterType alCharacterType = (AlCharacterType) command;
		boolean isNew = (alCharacterType.getTypeId() == null);

		if (request.getParameter("delete") != null) {
			List childTypes = this.alCharacterTypeManager.getDirectChildAlCharacterTypes(alCharacterType.getTypeId().toString());
			if (childTypes != null && childTypes.size() > 0) {
				saveMessage(request, getText("alCharacterType.error.child.exists"));
			} else {
				alCharacterTypeManager.removeAlCharacterType(alCharacterType.getTypeId().toString());

				saveMessage(request, getText("alCharacterType.deleted"));
			}
		} else {
			String parentId = request.getParameter("parentId");
			AlCharacterType parentAlCharacterType = new AlCharacterType();
			if (!StringUtils.isEmpty(parentId)) {
				parentAlCharacterType = alCharacterTypeManager.getAlCharacterType(parentId);
				alCharacterType.setTypeLevel(parentAlCharacterType.getTypeLevel()+1);
			}else{
				parentId="0";
				alCharacterType.setTypeLevel(1);
			}
			alCharacterType.setParentId(new Long(parentId));

			alCharacterTypeManager.saveAlCharacterType(alCharacterType);

			String key = (isNew) ? "alCharacterType.added" : "alCharacterType.updated";
			saveMessage(request, getText(key));
		}

		return new ModelAndView("redirect:editAlCharacterType.html");
	}
}
