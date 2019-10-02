package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.bd.model.JbdTravelPointLog;
import com.joymain.jecs.bd.service.JbdTravelPointDetailManager;
import com.joymain.jecs.bd.service.JbdTravelPointLogManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointLogFormController extends BaseFormController {
    private JbdTravelPointLogManager jbdTravelPointLogManager = null;
    private JbdTravelPointDetailManager jbdTravelPointDetailManager;
    public void setJbdTravelPointDetailManager(
			JbdTravelPointDetailManager jbdTravelPointDetailManager) {
		this.jbdTravelPointDetailManager = jbdTravelPointDetailManager;
	}
	public void setJbdTravelPointLogManager(JbdTravelPointLogManager jbdTravelPointLogManager) {
        this.jbdTravelPointLogManager = jbdTravelPointLogManager;
    }

    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    public JbdTravelPointLogFormController() {
        setCommandName("jbdTravelPointLog");
        setCommandClass(JbdTravelPointLog.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JbdTravelPointLog jbdTravelPointLog = null;

        if (!StringUtils.isEmpty(logId)) {
            jbdTravelPointLog = jbdTravelPointLogManager.getJbdTravelPointLog(logId);
        } else {
            jbdTravelPointLog = new JbdTravelPointLog();
        }

        return jbdTravelPointLog;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPointLog jbdTravelPointLog = (JbdTravelPointLog) command;

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        
    	 if(this.checkForm(jbdTravelPointLog, errors)){
     		return showForm(request, response, errors);
         }
         
    	
    	
        try {
        	jbdTravelPointDetailManager.changeJbdTravelPoint(jbdTravelPointLog, defSysUser);
        	this.saveMessage(request, this.getText("sys.message.updateSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
        	this.saveMessage(request, this.getText("sys.message.updateFail"));
		}
        

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
    private boolean checkForm(JbdTravelPointLog jbdTravelPointLog,BindException errors){

    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jbdTravelPointLog.getUserCode())){
			errors.reject("会员不能为空","会员不能为空");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jbdTravelPointLog.getUserCode())){
			errors.reject("会员不存在","会员不存在");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jbdTravelPointLog.getDealType())){
			errors.reject("请选择类型","请选择类型");
			isNotPass = true;
    	}

    	if(null==jbdTravelPointLog.getUsePoint()){
			errors.reject("请输入积分","请输入积分");
			isNotPass = true;
    	}else if(!StringUtil.isInteger(jbdTravelPointLog.getUsePoint().toString())){
			errors.reject("请输入整数积分","请输入整数积分");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
}
