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
import com.joymain.jecs.bd.model.JbdTravelPointDetail;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2012;
import com.joymain.jecs.bd.service.JbdTravelPointDetail2012Manager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointDetail2012FormController extends BaseFormController {
    private JbdTravelPointDetail2012Manager jbdTravelPointDetail2012Manager = null;

    public void setJbdTravelPointDetail2012Manager(JbdTravelPointDetail2012Manager jbdTravelPointDetail2012Manager) {
        this.jbdTravelPointDetail2012Manager = jbdTravelPointDetail2012Manager;
    }
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    public JbdTravelPointDetail2012FormController() {
        setCommandName("jbdTravelPointDetail2012");
        setCommandClass(JbdTravelPointDetail2012.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdTravelPointDetail2012 jbdTravelPointDetail2012 = null;

        if (!StringUtils.isEmpty(id)) {
            jbdTravelPointDetail2012 = jbdTravelPointDetail2012Manager.getJbdTravelPointDetail2012(id);
        } else {
            jbdTravelPointDetail2012 = new JbdTravelPointDetail2012();
        }

        return jbdTravelPointDetail2012;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdTravelPointDetail2012 jbdTravelPointDetail2012 = (JbdTravelPointDetail2012) command;
        
        SysUser defSysUser=SessionLogin.getLoginUser(request);
        
        
        if(this.checkForm(request, errors, defSysUser)){
    		return showForm(request, response, errors);
        }
        
        String userCode=null;
        if("C".equals(defSysUser.getUserType())){
        	userCode=request.getParameter("userCode");
        }else{
        	userCode=defSysUser.getUserCode();
        }
        
        
        try {
        	jbdTravelPointDetail2012Manager.addJbdTravelPointRecord2012(userCode, jbdTravelPointDetail2012.getTravelType(), defSysUser);
        	this.saveMessage(request, "申请成功");
		} catch (Exception e) {
            log.debug(e.getMessage());
        	this.saveMessage(request, e.getMessage());
		}
        
 
        return new ModelAndView("redirect:jbdTravelPointDetail2012s.html?strAction=jbdTravelPointDetail2012s");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    private boolean checkForm(HttpServletRequest request,BindException errors,SysUser defSysUser){

    	boolean isNotPass=false;
    	if("C".equals(defSysUser.getUserType())){
        	if(StringUtil.isEmpty(request.getParameter("userCode"))){
    			errors.reject("会员不能为空","会员不能为空");
    			isNotPass = true;
        	}else if(null==jmiMemberManager.getJmiMember(request.getParameter("userCode"))){
    			errors.reject("会员不存在","会员不存在");
    			isNotPass = true;
        	}
    	}
    	if(StringUtil.isEmpty(request.getParameter("travelType"))){
			errors.reject("请选择申请类型","请选择申请类型");
			isNotPass = true;
    	}
    	
    	return isNotPass;
    }
}
