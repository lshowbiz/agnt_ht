package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pm.model.JocsInterfaceRetransmission;
import com.joymain.jecs.pm.service.JocsInterfaceRetransmissionManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JocsInterfaceRetransmissionFormController extends BaseFormController {
	private JocsInterfaceRetransmissionManager jocsInterfaceRetransmissionManager = null;

	public void setJocsInterfaceRetransmissionManager(JocsInterfaceRetransmissionManager jocsInterfaceRetransmissionManager) {
		this.jocsInterfaceRetransmissionManager = jocsInterfaceRetransmissionManager;
	}
	public JocsInterfaceRetransmissionFormController() {
		setCommandName("jocsInterfaceRetransmission");
		setCommandClass(JocsInterfaceRetransmission.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String logId = request.getParameter("logId");
		JocsInterfaceRetransmission jocsInterfaceRetransmission = null;
		String strAction = request.getParameter("strAction");
		if (!StringUtils.isEmpty(logId)) {
			jocsInterfaceRetransmission = jocsInterfaceRetransmissionManager.getJocsInterfaceRetransmission(logId);
		} else {
			jocsInterfaceRetransmission = new JocsInterfaceRetransmission();
		}
		request.setAttribute("strAction", strAction);
		return jocsInterfaceRetransmission;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JocsInterfaceRetransmission jocsInterfaceRetransmission = (JocsInterfaceRetransmission) command;
		boolean isNew = (jocsInterfaceRetransmission.getLogId() == null);
		Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteJocsInterfaceRetransmission".equals(strAction)  ) {
			jocsInterfaceRetransmissionManager.removeJocsInterfaceRetransmission(jocsInterfaceRetransmission.getLogId().toString());
			key="jocsInterfaceRetransmission.delete";
		}else if ("editJocsInterfaceRetransmission".equals(strAction)  ) {
			jocsInterfaceRetransmissionManager.saveJocsInterfaceRetransmission(jocsInterfaceRetransmission);
			key="update.success";
		}

		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));  
		return new ModelAndView(getSuccessView());
	}
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
