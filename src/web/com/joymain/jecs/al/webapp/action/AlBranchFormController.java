package com.joymain.jecs.al.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlBranch;
import com.joymain.jecs.al.service.AlBranchManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AlBranchFormController extends BaseFormController {
	private AlBranchManager alBranchManager = null;

	public void setAlBranchManager(AlBranchManager alBranchManager) {
		this.alBranchManager = alBranchManager;
	}

	public AlBranchFormController() {
		setCommandName("alBranch");
		setCommandClass(AlBranch.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String branchId = request.getParameter("branchId");
		AlBranch alBranch = null;

		if (!StringUtils.isEmpty(branchId)) {
			alBranch = alBranchManager.getAlBranch(branchId);
		} else {
			alBranch = new AlBranch();
		}

		return alBranch;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AlBranch alBranch = (AlBranch) command;
		boolean isNew = (alBranch.getBranchId() == null);

		if (request.getParameter("delete") != null) {
			alBranchManager.removeAlBranch(alBranch.getBranchId().toString());

			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), "alBranch.deleted"));
		} else {
			alBranchManager.saveAlBranch(alBranch);

			String key = (isNew) ? "alBranch.added" : "alBranch.updated";
			saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));

			if (!isNew) {
				return new ModelAndView("redirect:editAlBranch.html", "branchId", alBranch.getBranchId());
			}
		}

		return new ModelAndView(getSuccessView());
	}
}
