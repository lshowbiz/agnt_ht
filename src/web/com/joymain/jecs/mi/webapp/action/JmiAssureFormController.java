package com.joymain.jecs.mi.webapp.action;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.mi.model.JmiAssure;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAssureManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.MeteorUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiAssureFormController extends BaseFormController {
	private JmiAssureManager jmiAssureManager = null;
	private JmiMemberManager jmiMemberManager = null;
	
	public void setJmiAssureManager(JmiAssureManager jmiAssureManager) {
		this.jmiAssureManager = jmiAssureManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public JmiAssureFormController() {
		setCommandName("jmiAssure");
		setCommandClass(JmiAssure.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		JmiAssure jmiAssure = null;
		String strAction = request.getParameter("strAction");
		if ("editJmiAssure".equals(strAction)) {
			if (!StringUtils.isEmpty(id)) {
				jmiAssure = jmiAssureManager.getJmiAssure(Long.parseLong(id));
			}
		} else if("viewJmiAssure".equals(strAction)) {
			if (!StringUtils.isEmpty(id)) {
				jmiAssure = jmiAssureManager.getJmiAssure(Long.parseLong(id));
			}
			request.setAttribute("disabled", true);
		} else {
			jmiAssure = new JmiAssure();
		}
		return jmiAssure;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		JmiAssure jmiAssure = (JmiAssure) command;
		String key = null;
		String strAction = request.getParameter("strAction");
		
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if ("deleteJmiAssure".equals(strAction)) {
			String ids = request.getParameter("ids");
			if(!MeteorUtil.isBlank(ids)){
				String [] s =  ids.split(",");
				for(String id :s){
					jmiAssureManager.removeJmiAssure(Long.valueOf(id));
				}
				key = "删除成功";
			}
			
		} else if("editJmiAssure".equals(strAction)) {
			jmiAssure.setUpdateTime(new Date());
			jmiAssure.setUpdateUserCode(sessionLogin.getUserCode());
			jmiAssureManager.updateJmiAssure(jmiAssure);
			key = "jmiAssure.update";
		} else {
			boolean result = jmiAssureManager.getCheckAssure(errors, jmiAssure, sessionLogin.getDefCharacterCoding());
			if(!result) {
				return showForm(request, response, errors);
			}
			jmiAssure.setCreateTime(new Date());
			jmiAssure.setCreateUserCode(sessionLogin.getUserCode());
			jmiAssureManager.saveJmiAssure(jmiAssure);
		}
		return new ModelAndView(getSuccessView());
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
	public static void main(String[] args) {
		String ids = "10302665,10302664,10302663,10302656";
		if(!StringUtils.isEmpty(ids)) {
    		//ids = ids.replace(",", "','");
    	}
    	String sql = "delete from jmi_assure where id in("+ids+")";
    	System.out.println(sql);
	}
}
