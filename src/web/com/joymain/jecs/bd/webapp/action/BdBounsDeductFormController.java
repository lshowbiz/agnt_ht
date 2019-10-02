package com.joymain.jecs.bd.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdBounsDeduct;
import com.joymain.jecs.bd.service.BdBounsDeductManager;
import com.joymain.jecs.webapp.action.BaseFormController;


public class BdBounsDeductFormController extends BaseFormController {
	private BdBounsDeductManager bdBounsDeductManager = null;

	public void setBdBounsDeductManager(BdBounsDeductManager bdBounsDeductManager) {
		this.bdBounsDeductManager = bdBounsDeductManager;
	}

	public BdBounsDeductFormController() {
		setCommandName("bdBounsDeduct");
		setCommandClass(BdBounsDeduct.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		BdBounsDeduct bdBounsDeduct = null;

		if (!StringUtils.isEmpty(id)) {
			bdBounsDeduct = bdBounsDeductManager.getBdBounsDeduct(id);
		} else {
			bdBounsDeduct = new BdBounsDeduct();
		}

		return bdBounsDeduct;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		BdBounsDeduct bdBounsDeduct = (BdBounsDeduct) command;

		if ("deleteBdBounsDeduct".equals(request.getParameter("strAction"))) {
			bdBounsDeductManager.removeBdBounsDeduct(bdBounsDeduct.getId().toString());

			saveMessage(request, getText("bdBounsDeduct.deleted"));
		} else {
			String key = "bdBounsDeduct.updated";
			if ("addBdBounsDeduct".equals(request.getParameter("strAction"))) {
				key = "bdBounsDeduct.added";
			}
			bdBounsDeductManager.saveBdBounsDeduct(bdBounsDeduct);

			saveMessage(request, getText(key));
		}

		return new ModelAndView(getSuccessView());
	}
}
