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
import com.joymain.jecs.bd.model.JbdTravelPointLog2015;
import com.joymain.jecs.bd.service.JbdTravelPointLog2015Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointLog2015FormController extends BaseFormController {
    private JbdTravelPointLog2015Manager jbdTravelPointLog2015Manager = null;
    private JmiMemberManager jmiMemberManager;

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJbdTravelPointLog2015Manager(JbdTravelPointLog2015Manager jbdTravelPointLog2015Manager) {
        this.jbdTravelPointLog2015Manager = jbdTravelPointLog2015Manager;
    }
    public JbdTravelPointLog2015FormController() {
        setCommandName("jbdTravelPointLog2015");
        setCommandClass(JbdTravelPointLog2015.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");
        JbdTravelPointLog2015 jbdTravelPointLog2015 = null;

        if (!StringUtils.isEmpty(logId)) {
            jbdTravelPointLog2015 = jbdTravelPointLog2015Manager.getJbdTravelPointLog2015(logId);
        } else {
            jbdTravelPointLog2015 = new JbdTravelPointLog2015();
        }

        return jbdTravelPointLog2015;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPointLog2015 jbdTravelPointLog2015 = (JbdTravelPointLog2015) command;
        
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        
   	 if(this.checkForm(jbdTravelPointLog2015, errors)){
    		return showForm(request, response, errors);
        }
        
   	
   	
       try { 
    	   jbdTravelPointLog2015Manager.changeJbdTravelPoint2015(jbdTravelPointLog2015, defSysUser);
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

    private boolean checkForm(JbdTravelPointLog2015 jbdTravelPointLog2015,BindException errors){

    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jbdTravelPointLog2015.getUserCode())){
			errors.reject("会员不能为空","会员不能为空");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jbdTravelPointLog2015.getUserCode())){
			errors.reject("会员不存在","会员不存在");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jbdTravelPointLog2015.getDealType())){
			errors.reject("请选择类型","请选择类型");
			isNotPass = true;
    	}

    	if(null==jbdTravelPointLog2015.getUsePoint()){
			errors.reject("请输入积分","请输入积分");
			isNotPass = true;
    	}else if(!StringUtil.isInteger(jbdTravelPointLog2015.getUsePoint().toString())){
			errors.reject("请输入整数积分","请输入整数积分");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
}
