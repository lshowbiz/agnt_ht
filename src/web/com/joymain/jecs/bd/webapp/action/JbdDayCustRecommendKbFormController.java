package com.joymain.jecs.bd.webapp.action;

import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.bd.model.JbdDayCustRecommendKb;
import com.joymain.jecs.bd.service.JbdDayCustRecommendKbManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdDayCustRecommendKbFormController extends BaseFormController {
    private JbdDayCustRecommendKbManager jbdDayCustRecommendKbManager = null;

    public void setJbdDayCustRecommendKbManager(JbdDayCustRecommendKbManager jbdDayCustRecommendKbManager) {
        this.jbdDayCustRecommendKbManager = jbdDayCustRecommendKbManager;
    }
    public JbdDayCustRecommendKbFormController() {
        setCommandName("jbdDayCustRecommendKb");
        setCommandClass(JbdDayCustRecommendKb.class);
    }
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdDayCustRecommendKb jbdDayCustRecommendKb = null;

        if (!StringUtils.isEmpty(id)) {
            jbdDayCustRecommendKb = jbdDayCustRecommendKbManager.getJbdDayCustRecommendKb(id);
        } else {
            jbdDayCustRecommendKb = new JbdDayCustRecommendKb();
            jbdDayCustRecommendKb.setKb_money(new BigDecimal(0));
        }
        return jbdDayCustRecommendKb;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
    
		String strAction = request.getParameter("strAction");
        JbdDayCustRecommendKb jbdDayCustRecommendKb = (JbdDayCustRecommendKb) command;
        if(jbdDayCustRecommendKb.getStatus()!=null && jbdDayCustRecommendKb.getStatus().intValue()==1){
    		errors.reject("已结算，操作失败","已结算，操作失败");
    		return showForm(request, response, errors);
    	}
        if("addJbdDayCustRecommendKb".equals(strAction)||"editJbdDayCustRecommendKb".equals(strAction)){
        	if(StringUtil.isEmpty(jbdDayCustRecommendKb.getUserCode())){
        		errors.rejectValue("userCode", "isNotNull",new Object[] { this.getText("miMember.memberNo") }, "");
    			return showForm(request, response, errors);
        	}else{
        		JmiMember jmiMember=jmiMemberManager.getJmiMember(jbdDayCustRecommendKb.getUserCode());
            	if(null==jmiMember){

        			errors.reject("miMember.notFound");
            		return showForm(request, response, errors);
            	}
        	}
        	
        }
        boolean isNew = (jbdDayCustRecommendKb.getId() == null);
        Locale locale = request.getLocale();
		String key=null;
		if ("deleteJbdDayCustRecommendKb".equals(strAction)  ) {
			jbdDayCustRecommendKbManager.removeJbdDayCustRecommendKb(jbdDayCustRecommendKb.getId().toString());
		}else{
			jbdDayCustRecommendKb.setOperation_date(new Date());
			jbdDayCustRecommendKb.setOperation_no(defSysUser.getUserCode());
			 if("addJbdDayCustRecommendKb".equals(strAction)){
				 jbdDayCustRecommendKb.setStatus(0);
			 }
			jbdDayCustRecommendKbManager.saveJbdDayCustRecommendKb(jbdDayCustRecommendKb);
		}
		this.saveMessage(request, "更新成功");
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
