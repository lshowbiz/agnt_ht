package com.joymain.jecs.mi.webapp.action;

import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.mi.model.JmiLevelLock;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLevelLockManager;
import com.joymain.jecs.mi.service.JmiMemberManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiLevelLockFormController extends BaseFormController {
    private JmiLevelLockManager jmiLevelLockManager = null;

    public void setJmiLevelLockManager(JmiLevelLockManager jmiLevelLockManager) {
        this.jmiLevelLockManager = jmiLevelLockManager;
    }
    private JmiMemberManager jmiMemberManager;
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    public JmiLevelLockFormController() {
        setCommandName("jmiLevelLock");
        setCommandClass(JmiLevelLock.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiLevelLock jmiLevelLock = null;

        if (!StringUtils.isEmpty(id)) {
            jmiLevelLock = jmiLevelLockManager.getJmiLevelLock(id);
        } else {
            jmiLevelLock = new JmiLevelLock();
        }

        return jmiLevelLock;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiLevelLock jmiLevelLock = (JmiLevelLock) command;
     	String strAction=request.getParameter("strAction");
    	SysUser defSysUser=SessionLogin.getLoginUser(request);

    	
    	
    	
    	JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiLevelLock.getUserCode());
    	if(null==jmiMember){

			errors.reject("miMember.notFound");
    		return showForm(request, response, errors);
    	}
    	
	

    	JmiLevelLock resJmiLevelLock=jmiLevelLockManager.getJmiLevelLockByUserCode(jmiLevelLock.getUserCode());
    	if(resJmiLevelLock!=null){
    		resJmiLevelLock.setMemberLevel(jmiLevelLock.getMemberLevel());
    		jmiLevelLock=resJmiLevelLock;
    	}
    	
    	
    	jmiLevelLock.setCreateNo(defSysUser.getUserCode());
    	jmiLevelLock.setIsValid("1");
    	
    	jmiLevelLockManager.saveJmiLevelLockAndMiMember(jmiLevelLock,defSysUser);
		MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
    	
    	
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
