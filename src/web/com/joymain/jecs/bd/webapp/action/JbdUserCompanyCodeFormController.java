package com.joymain.jecs.bd.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdUserCompanyCode;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdUserCompanyCodeManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdUserCompanyCodeFormController extends BaseFormController {
    private JbdUserCompanyCodeManager jbdUserCompanyCodeManager = null;
    private JmiMemberManager jmiMemberManager;
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJbdUserCompanyCodeManager(JbdUserCompanyCodeManager jbdUserCompanyCodeManager) {
        this.jbdUserCompanyCodeManager = jbdUserCompanyCodeManager;
    }
    public JbdUserCompanyCodeFormController() {
        setCommandName("jbdUserCompanyCode");
        setCommandClass(JbdUserCompanyCode.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdUserCompanyCode jbdUserCompanyCode = null;

        if (!StringUtils.isEmpty(id)) {
            jbdUserCompanyCode = jbdUserCompanyCodeManager.getJbdUserCompanyCode(id);
        } else {
            jbdUserCompanyCode = new JbdUserCompanyCode();
        }

        return jbdUserCompanyCode;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        JbdUserCompanyCode jbdUserCompanyCode = (JbdUserCompanyCode) command;
        
        JmiMember jmiMember =jmiMemberManager.getJmiMember(jbdUserCompanyCode.getUserCode());

        //2011-10-13 修改
//    	if(1==jmiMember.getBeforeFreezeStatus()){//会员冻结，不能转移
//        	this.saveMessage(request, this.getText("bdsendrecord.sendlateremark.17"));
//        	return showForm(request, response, errors);
//    	}
        
		BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",jbdUserCompanyCode.getWweek().toString()));

        if(bdPeriod==null){
        	this.saveMessage(request, this.getText("report.nullWweek"));
        	return showForm(request, response, errors);
        }else if(bdPeriod.getArchivingStatus()==1){
        	this.saveMessage(request, this.getText("bd.send.Archiving"));
        	return showForm(request, response, errors);
        }
        
        if(jmiMember==null){
        	this.saveMessage(request, this.getText("miMember.notFound"));
        	return showForm(request, response, errors);
        }
        if(StringUtils.isEmpty(jbdUserCompanyCode.getNewCompany())){
        	this.saveMessage(request, this.getText("please.select.company"));
        	return showForm(request, response, errors);
        }
        if(jbdUserCompanyCode.getNewCompany().equals(jmiMember.getCompanyCode())){
        	this.saveMessage(request, this.getText("jbdUserCompanyCode.samecompany.fail"));
        	return showForm(request, response, errors);
        }
        
        	jbdUserCompanyCodeManager.changeCompanyCode(jbdUserCompanyCode.getUserCode(), WeekFormatUtil.getFormatWeek("f",jbdUserCompanyCode.getWweek()), jbdUserCompanyCode.getNewCompany(), "1", request, defSysUser);
	
	        return new ModelAndView("redirect:jbdUserCompanyCodes.html?userCode="+jmiMember.getUserCode());
	    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
