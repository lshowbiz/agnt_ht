package com.joymain.jecs.bd.webapp.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdManuallyAdjustStarManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdManuallyAdjustStarFormController extends BaseFormController {
    private JbdManuallyAdjustStarManager jbdManuallyAdjustStarManager = null;
    private JmiMemberManager jmiMemberManager;
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJbdManuallyAdjustStarManager(JbdManuallyAdjustStarManager jbdManuallyAdjustStarManager) {
        this.jbdManuallyAdjustStarManager = jbdManuallyAdjustStarManager;
    }
    public JbdManuallyAdjustStarFormController() {
        setCommandName("jbdManuallyAdjustStar");
        setCommandClass(JbdManuallyAdjustStar.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdManuallyAdjustStar jbdManuallyAdjustStar = null;

        if (!StringUtils.isEmpty(id)) {
            jbdManuallyAdjustStar = jbdManuallyAdjustStarManager.getJbdManuallyAdjustStar(id);
        } else {
            jbdManuallyAdjustStar = new JbdManuallyAdjustStar();
        }

        return jbdManuallyAdjustStar;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        JbdManuallyAdjustStar jbdManuallyAdjustStar = (JbdManuallyAdjustStar) command;

        if(null==jbdManuallyAdjustStar.getWweek()){
			MessageUtil.saveLocaleMessage(request, "report.nullWweek");
			return showForm(request, response, errors);
        	
        }else{//判断是否月结周
        	
        	
            BdPeriod bdPeriod=bdPeriodManager.getMonthBdPeriod(WeekFormatUtil.getFormatWeek("f", jbdManuallyAdjustStar.getWweek().toString()));
            if(bdPeriod==null){
    			MessageUtil.saveLocaleMessage(request, "month.week.input");
    			return showForm(request, response, errors);
            }
        }
        
        JmiMember jmiMember=jmiMemberManager.getJmiMember(jbdManuallyAdjustStar.getUserCode());
        if(jmiMember==null){
			MessageUtil.saveLocaleMessage(request, "miMember.notFound");
			return showForm(request, response, errors);
        }
        
        
        
		String strAction = request.getParameter("strAction");
		
		
		if ("deleteJbdManuallyAdjustStar".equals(strAction)  ) {
			jbdManuallyAdjustStarManager.removeJbdManuallyAdjustStar(jbdManuallyAdjustStar.getId().toString());
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}else{
			
			if(jbdManuallyAdjustStar.getId()==null){
				  JbdManuallyAdjustStar curJbdManuallyAdjustStar= jbdManuallyAdjustStarManager.getJbdManuallyAdjustStarByUserCodeAndWeek(jmiMember.getUserCode(), jbdManuallyAdjustStar.getWweek().toString());
				  if(curJbdManuallyAdjustStar!=null){
						MessageUtil.saveLocaleMessage(request, "JbdManuallyAdjustStar.exist");
						return showForm(request, response, errors);
				  }
			}
			jbdManuallyAdjustStar.setCreateNo(defSysUser.getUserCode());
			jbdManuallyAdjustStar.setCreateTime(new Date());
			jbdManuallyAdjustStar.setWweek(WeekFormatUtil.getFormatWeek("f", jbdManuallyAdjustStar.getWweek()));
			jbdManuallyAdjustStarManager.saveJbdManuallyAdjustStar(jbdManuallyAdjustStar);
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
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
}
