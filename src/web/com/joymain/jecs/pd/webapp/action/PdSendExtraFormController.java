package com.joymain.jecs.pd.webapp.action;

import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdSendExtra;
import com.joymain.jecs.pd.service.PdSendExtraManager;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdSendExtraFormController extends BaseFormController {
	private PdSendExtraManager pdSendExtraManager = null;

	public void setPdSendExtraManager(PdSendExtraManager pdSendExtraManager) {
		this.pdSendExtraManager = pdSendExtraManager;
	}

	public PdSendExtraFormController() {
		setCommandName("pdSendExtra");
		setCommandClass(PdSendExtra.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String uniId = request.getParameter("uniId");
		String siNo = request.getParameter("siNo");
		PdSendExtra pdSendExtra = null;

		if (!StringUtils.isEmpty(uniId)) {
			pdSendExtra = pdSendExtraManager.getPdSendExtra(uniId);
		} else {
			pdSendExtra = new PdSendExtra();
			pdSendExtra.setSiNo(siNo);
		}

		return pdSendExtra;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		PdSendExtra pdSendExtra = (PdSendExtra) command;
		String view = "redirect:pdSendExtras.html?strAction=editPdSendExtra&siNo="
				+ pdSendExtra.getSiNo();
		// boolean isNew = (pdSendExtra.getUniId() == null);
		// Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		if ("deletePdSendExtra".equals(strAction)) {
			pdSendExtraManager.removePdSendExtra(pdSendExtra.getUniId()
					.toString());
			key = "pdSendExtra.delete";
		}else {
			pdSendExtra.setOperaterCode(sessionLogin.getUserCode());
			pdSendExtra.setCreateTime(new Date());
			pdSendExtraManager.savePdSendExtra(pdSendExtra);
			key = "pdSendExtra.update";
		}
		saveMessage(request, getText(sessionLogin.getDefCharacterCoding(), key));
		return new ModelAndView(view);
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
