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
import com.joymain.jecs.bd.model.JbdTravelPointLog2012;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2012Manager;
import com.joymain.jecs.bd.service.JbdTravelPointDetailManager;
import com.joymain.jecs.bd.service.JbdTravelPointLog2012Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointLog2012FormController extends BaseFormController {
    private JbdTravelPointLog2012Manager jbdTravelPointLog2012Manager = null;
    private JmiMemberManager jmiMemberManager;

    private JbdTravelPointDetail2012Manager jbdTravelPointDetail2012Manager;
    public void setJbdTravelPointDetail2012Manager(
			JbdTravelPointDetail2012Manager jbdTravelPointDetail2012Manager) {
		this.jbdTravelPointDetail2012Manager = jbdTravelPointDetail2012Manager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    public void setJbdTravelPointLog2012Manager(JbdTravelPointLog2012Manager jbdTravelPointLog2012Manager) {
        this.jbdTravelPointLog2012Manager = jbdTravelPointLog2012Manager;
    }
    public JbdTravelPointLog2012FormController() {
        setCommandName("jbdTravelPointLog2012");
        setCommandClass(JbdTravelPointLog2012.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JbdTravelPointLog2012 jbdTravelPointLog2012 = null;

        if (!StringUtils.isEmpty(logId)) {
            jbdTravelPointLog2012 = jbdTravelPointLog2012Manager.getJbdTravelPointLog2012(logId);
        } else {
            jbdTravelPointLog2012 = new JbdTravelPointLog2012();
        }

        return jbdTravelPointLog2012;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPointLog2012 jbdTravelPointLog2012 = (JbdTravelPointLog2012) command;
        
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        
   	 if(this.checkForm(jbdTravelPointLog2012, errors)){
    		return showForm(request, response, errors);
        }
        
   	
   	
       try {
       	jbdTravelPointDetail2012Manager.changeJbdTravelPoint2012(jbdTravelPointLog2012, defSysUser);
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

    private boolean checkForm(JbdTravelPointLog2012 jbdTravelPointLog2012,BindException errors){

    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jbdTravelPointLog2012.getUserCode())){
			errors.reject("会员不能为空","会员不能为空");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jbdTravelPointLog2012.getUserCode())){
			errors.reject("会员不存在","会员不存在");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jbdTravelPointLog2012.getDealType())){
			errors.reject("请选择类型","请选择类型");
			isNotPass = true;
    	}

    	if(null==jbdTravelPointLog2012.getUsePoint()){
			errors.reject("请输入积分","请输入积分");
			isNotPass = true;
    	}else if(!StringUtil.isInteger(jbdTravelPointLog2012.getUsePoint().toString())){
			errors.reject("请输入整数积分","请输入整数积分");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
}
