package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiMemberTeamFormController extends BaseFormController {
	private JmiMemberTeamManager jmiMemberTeamManager = null;

	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}
	public JmiMemberTeamFormController() {
		setCommandName("jmiMemberTeam");
		setCommandClass(JmiMemberTeam.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String code = request.getParameter("code");
		JmiMemberTeam jmiMemberTeam = null;
		String strAction = request.getParameter("strAction"); 
		if ("editJmiMemberTeam".equals(strAction)) {
			jmiMemberTeam = jmiMemberTeamManager.getJmiMemberTeam(code);
		} else { 
			jmiMemberTeam = new JmiMemberTeam();
		}

		return jmiMemberTeam;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		JmiMemberTeam jmiMemberTeam = (JmiMemberTeam) command;
		boolean isNew = (jmiMemberTeam.getCode() == null);
		Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteJmiMemberTeam".equals(strAction)  ) {
			jmiMemberTeamManager.removeJmiMemberTeam(jmiMemberTeam.getCode().toString());
			key="jmiMemberTeam.delete";
		}else if ("addJmiMemberTeam".equals(strAction)  ) {
			CommonRecord crm=RequestUtil.toCommonRecord(request);
			crm.setValue("code", jmiMemberTeam.getCode()); 
			//判断是否已经存在团队编码
			boolean isExist = jmiMemberTeamManager.isExist(crm, "0");
			
			if(isExist){
				errors.rejectValue("code","error.jmimemberteam.existed");
				return showForm(request, response, errors); 
			} 
			
			jmiMemberTeam.setCode(jmiMemberTeam.getCode().replace(" ", "").trim());
			jmiMemberTeam.setName(jmiMemberTeam.getName().replace(" ", "").trim());
			jmiMemberTeamManager.saveJmiMemberTeam(jmiMemberTeam); 
			key="jmiMemberTeam.add";
		}else if ("editJmiMemberTeam".equals(strAction)  ) {
			jmiMemberTeamManager.saveJmiMemberTeam(jmiMemberTeam);
			key="jmiMemberTeam.update";
		} 

		saveMessage(request, getText(SessionLogin.getLoginUser(request)
				.getDefCharacterCoding(), key));

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
