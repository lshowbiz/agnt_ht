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
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2014Manager;
import com.joymain.jecs.bd.service.JbdTravelPointLog2014Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointLog2014FormController extends BaseFormController {
    private JbdTravelPointLog2014Manager jbdTravelPointLog2014Manager = null;
    private JbdTravelPointDetail2014Manager jbdTravelPointDetail2014Manager;
    private JmiMemberManager jmiMemberManager;

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJbdTravelPointLog2014Manager(JbdTravelPointLog2014Manager jbdTravelPointLog2014Manager) {
        this.jbdTravelPointLog2014Manager = jbdTravelPointLog2014Manager;
    }
    public JbdTravelPointLog2014FormController() {
        setCommandName("jbdTravelPointLog2014");
        setCommandClass(JbdTravelPointLog2014.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JbdTravelPointLog2014 jbdTravelPointLog2014 = null;

        if (!StringUtils.isEmpty(logId)) {
            jbdTravelPointLog2014 = jbdTravelPointLog2014Manager.getJbdTravelPointLog2014(logId);
        } else {
            jbdTravelPointLog2014 = new JbdTravelPointLog2014();
        }

        return jbdTravelPointLog2014;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPointLog2014 jbdTravelPointLog2014 = (JbdTravelPointLog2014) command;
        
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        
   	 if(this.checkForm(jbdTravelPointLog2014, errors)){
    		return showForm(request, response, errors);
        }
        
   	
   	
       try { 
       		jbdTravelPointDetail2014Manager.changeJbdTravelPoint2014(jbdTravelPointLog2014, defSysUser);
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

    private boolean checkForm(JbdTravelPointLog2014 jbdTravelPointLog2014,BindException errors){

    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jbdTravelPointLog2014.getUserCode())){
			errors.reject("会员不能为空","会员不能为空");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jbdTravelPointLog2014.getUserCode())){
			errors.reject("会员不存在","会员不存在");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jbdTravelPointLog2014.getDealType())){
			errors.reject("请选择类型","请选择类型");
			isNotPass = true;
    	}

    	if(null==jbdTravelPointLog2014.getUsePoint()){
			errors.reject("请输入积分","请输入积分");
			isNotPass = true;
    	}else if(!StringUtil.isInteger(jbdTravelPointLog2014.getUsePoint().toString())){
			errors.reject("请输入整数积分","请输入整数积分");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
	public void setJbdTravelPointDetail2014Manager(
			JbdTravelPointDetail2014Manager jbdTravelPointDetail2014Manager) {
		this.jbdTravelPointDetail2014Manager = jbdTravelPointDetail2014Manager;
	}
}
