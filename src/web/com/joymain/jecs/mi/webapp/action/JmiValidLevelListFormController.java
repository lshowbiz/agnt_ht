package com.joymain.jecs.mi.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiValidLevelList;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiValidLevelListManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiValidLevelListFormController extends BaseFormController {
    private JmiValidLevelListManager jmiValidLevelListManager = null;
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public void setJmiValidLevelListManager(JmiValidLevelListManager jmiValidLevelListManager) {
        this.jmiValidLevelListManager = jmiValidLevelListManager;
    }
    public JmiValidLevelListFormController() {
        setCommandName("jmiValidLevelList");
        setCommandClass(JmiValidLevelList.class);
    }
    private JmiMemberManager jmiMemberManager;
    
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiValidLevelList jmiValidLevelList = null;

        if (!StringUtils.isEmpty(id)) {
            jmiValidLevelList = jmiValidLevelListManager.getJmiValidLevelList(id);
        } else {
            jmiValidLevelList = new JmiValidLevelList();
        }

        return jmiValidLevelList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JmiValidLevelList jmiValidLevelList = (JmiValidLevelList) command;
  

    	String strAction=request.getParameter("strAction");
    	SysUser defSysUser=SessionLogin.getLoginUser(request);

    	JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiValidLevelList.getUserCode());
    	if(null==jmiMember){

			errors.reject("miMember.notFound");
    		return showForm(request, response, errors);
    	}
        if(jmiValidLevelList.getWweek()==null){

			errors.reject("week.isError");
    		return showForm(request, response, errors);
        }else{
        	BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",jmiValidLevelList.getWweek().toString()));
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


 
		jmiValidLevelList.setOldMemberLevel(jmiMember.getMemberLevel());
		

    	
    	
		jmiValidLevelListManager.saveJmiValidLevelList(jmiValidLevelList,defSysUser);
		
		
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
