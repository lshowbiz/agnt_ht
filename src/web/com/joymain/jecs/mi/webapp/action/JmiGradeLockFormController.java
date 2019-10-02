package com.joymain.jecs.mi.webapp.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiGradeLock;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiGradeLockManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiGradeLockFormController extends BaseFormController {
    private JmiGradeLockManager jmiGradeLockManager = null;
    private JmiMemberManager jmiMemberManager;
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}    
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
    public void setJmiGradeLockManager(JmiGradeLockManager jmiGradeLockManager) {
        this.jmiGradeLockManager = jmiGradeLockManager;
    }
    public JmiGradeLockFormController() {
        setCommandName("jmiGradeLock");
        setCommandClass(JmiGradeLock.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiGradeLock jmiGradeLock = null;

        if (!StringUtils.isEmpty(id)) {
            jmiGradeLock = jmiGradeLockManager.getJmiGradeLock(id);
        } else {
            jmiGradeLock = new JmiGradeLock();
        }

        return jmiGradeLock;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiGradeLock jmiGradeLock = (JmiGradeLock) command;
 
        
        

    	String strAction=request.getParameter("strAction");
    	SysUser defSysUser=SessionLogin.getLoginUser(request);

    	JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiGradeLock.getUserCode());
    	if(null==jmiMember){

			errors.reject("miMember.notFound");
    		return showForm(request, response, errors);
    	}
    	
    	if(jmiGradeLock.getGradeType()==null){
    		errors.reject("请选择身份","请选择身份");
    		return showForm(request, response, errors);
    	}
    	
    	
        if(jmiGradeLock.getValidWeek()==null){

			errors.reject("week.isError");
    		return showForm(request, response, errors);
        }else{
        	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",jmiGradeLock.getValidWeek().toString()));
        	if(bdPeriod==null){
    			errors.reject("week.isError");
        		return showForm(request, response, errors);
        	}
        	//已结算的期别不能增加
        	if(bdPeriod.getBonusSend()!=null&&bdPeriod.getBonusSend()==1){
        	    errors.reject("week.isError");
                return showForm(request, response, errors);
        	}
        }


        JmiGradeLock resJmiGradeLock=jmiGradeLockManager.getJmiGradeLockByUserCode(jmiGradeLock.getUserCode(), new Integer(WeekFormatUtil.getFormatWeek("f",jmiGradeLock.getValidWeek().toString())));
	    	
    	
    	if(resJmiGradeLock!=null){
    		resJmiGradeLock.setGradeType(jmiGradeLock.getGradeType());
    		jmiGradeLock=resJmiGradeLock;
    	}
    	
    	
    	

		jmiGradeLock.setCreateNo(defSysUser.getUserCode());
		jmiGradeLock.setCreateTime(new Date());
		

    	if(resJmiGradeLock==null){
    		jmiGradeLock.setValidWeek(new Integer(WeekFormatUtil.getFormatWeek("f",jmiGradeLock.getValidWeek().toString())));
    	}
    	
    	
    	
    	
    	
		jmiGradeLockManager.saveJmiGradeLock(jmiGradeLock ,defSysUser);
		
		
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
