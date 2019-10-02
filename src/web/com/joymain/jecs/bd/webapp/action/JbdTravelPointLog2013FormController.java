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
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2013Manager;
import com.joymain.jecs.bd.service.JbdTravelPointLog2013Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointLog2013FormController extends BaseFormController {
    private JbdTravelPointLog2013Manager jbdTravelPointLog2013Manager = null;
    private JbdTravelPointDetail2013Manager jbdTravelPointDetail2013Manager;
    private JmiMemberManager jmiMemberManager;

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJbdTravelPointLog2013Manager(JbdTravelPointLog2013Manager jbdTravelPointLog2013Manager) {
        this.jbdTravelPointLog2013Manager = jbdTravelPointLog2013Manager;
    }
    public JbdTravelPointLog2013FormController() {
        setCommandName("jbdTravelPointLog2013");
        setCommandClass(JbdTravelPointLog2013.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JbdTravelPointLog2013 jbdTravelPointLog2013 = null;

        if (!StringUtils.isEmpty(logId)) {
            jbdTravelPointLog2013 = jbdTravelPointLog2013Manager.getJbdTravelPointLog2013(logId);
        } else {
            jbdTravelPointLog2013 = new JbdTravelPointLog2013();
        }

        return jbdTravelPointLog2013;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPointLog2013 jbdTravelPointLog2013 = (JbdTravelPointLog2013) command;
        
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        
   	 if(this.checkForm(jbdTravelPointLog2013, errors)){
    		return showForm(request, response, errors);
        }
        
   	
   	
       try { 
       		jbdTravelPointDetail2013Manager.changeJbdTravelPoint2013(jbdTravelPointLog2013, defSysUser);
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

    private boolean checkForm(JbdTravelPointLog2013 jbdTravelPointLog2013,BindException errors){

    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jbdTravelPointLog2013.getUserCode())){
			errors.reject("会员不能为空","会员不能为空");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jbdTravelPointLog2013.getUserCode())){
			errors.reject("会员不存在","会员不存在");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jbdTravelPointLog2013.getDealType())){
			errors.reject("请选择类型","请选择类型");
			isNotPass = true;
    	}

    	if(null==jbdTravelPointLog2013.getUsePoint()){
			errors.reject("请输入积分","请输入积分");
			isNotPass = true;
    	}else if(!StringUtil.isInteger(jbdTravelPointLog2013.getUsePoint().toString())){
			errors.reject("请输入整数积分","请输入整数积分");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
	public void setJbdTravelPointDetail2013Manager(
			JbdTravelPointDetail2013Manager jbdTravelPointDetail2013Manager) {
		this.jbdTravelPointDetail2013Manager = jbdTravelPointDetail2013Manager;
	}
}
