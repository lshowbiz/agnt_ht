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
import com.joymain.jecs.bd.service.JbdTravelPointDetailManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdTravelPointDetailFormController extends BaseFormController {
    private JbdTravelPointDetailManager jbdTravelPointDetailManager = null;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJbdTravelPointDetailManager(JbdTravelPointDetailManager jbdTravelPointDetailManager) {
        this.jbdTravelPointDetailManager = jbdTravelPointDetailManager;
    }
    public JbdTravelPointDetailFormController() {
        setCommandName("jbdTravelPointDetail");
        setCommandClass(JbdTravelPointDetail.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdTravelPointDetail jbdTravelPointDetail = null;

        if (!StringUtils.isEmpty(id)) {
            jbdTravelPointDetail = jbdTravelPointDetailManager.getJbdTravelPointDetail(id);
        } else {
            jbdTravelPointDetail = new JbdTravelPointDetail();
        }

        return jbdTravelPointDetail;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        JbdTravelPointDetail jbdTravelPointDetail = (JbdTravelPointDetail) command;
        
        
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
        	jbdTravelPointDetailManager.addJbdTravelPointRecord(userCode, jbdTravelPointDetail.getTravelType(), defSysUser);
        	this.saveMessage(request, "申请成功");
		} catch (Exception e) {
            log.debug(e.getMessage());
        	this.saveMessage(request, e.getMessage());
		}
        
 
        return new ModelAndView("redirect:jbdTravelPointDetails.html?strAction=jbdTravelPointDetails");
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
