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
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.bd.model.JbdManuallyAdjustAlgebra;
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.bd.service.JbdManuallyAdjustAlgebraManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdManuallyAdjustAlgebraFormController extends BaseFormController {
    private JbdManuallyAdjustAlgebraManager jbdManuallyAdjustAlgebraManager = null;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJbdManuallyAdjustAlgebraManager(JbdManuallyAdjustAlgebraManager jbdManuallyAdjustAlgebraManager) {
        this.jbdManuallyAdjustAlgebraManager = jbdManuallyAdjustAlgebraManager;
    }
    public JbdManuallyAdjustAlgebraFormController() {
        setCommandName("jbdManuallyAdjustAlgebra");
        setCommandClass(JbdManuallyAdjustAlgebra.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
        
        String id = request.getParameter("id");
        JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra = null;

        if (!StringUtils.isEmpty(id)) {
            jbdManuallyAdjustAlgebra = jbdManuallyAdjustAlgebraManager.getJbdManuallyAdjustAlgebra(id);
            if(jbdManuallyAdjustAlgebra!=null){
            	jbdManuallyAdjustAlgebra.setWweek(WeekFormatUtil.getFormatWeek("w",jbdManuallyAdjustAlgebra.getWweek()));
            }
        } else {
            jbdManuallyAdjustAlgebra = new JbdManuallyAdjustAlgebra();
        }

        return jbdManuallyAdjustAlgebra;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        JbdManuallyAdjustAlgebra jbdManuallyAdjustAlgebra = (JbdManuallyAdjustAlgebra) command;
        
        
        if(null==jbdManuallyAdjustAlgebra.getWweek()){
			MessageUtil.saveLocaleMessage(request, "report.nullWweek");
			return showForm(request, response, errors);
        }
        JmiMember jmiMember=jmiMemberManager.getJmiMember(jbdManuallyAdjustAlgebra.getUserCode());
        if(jmiMember==null){
			MessageUtil.saveLocaleMessage(request, "miMember.notFound");
			return showForm(request, response, errors);
        }
        if(null==jbdManuallyAdjustAlgebra.getAlgebra()){
        	errors.rejectValue("algebra", "isNotNull",new Object[] { this.getText("bdMemberBounsCalcSell.level") }, "");
			return showForm(request, response, errors);
        	
        }
        
        
        

		String strAction = request.getParameter("strAction");
        
        
		if ("deleteJbdManuallyAdjustAlgebra".equals(strAction)  ) {
			jbdManuallyAdjustAlgebraManager.removeJbdManuallyAdjustAlgebra(jbdManuallyAdjustAlgebra.getId().toString());
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}else{
			
			if(jbdManuallyAdjustAlgebra.getId()==null){
				JbdManuallyAdjustAlgebra curJbdManuallyAdjustAlgebra= jbdManuallyAdjustAlgebraManager.getJbdManuallyAdjustAlgebraByUserCodeAndWeek(jmiMember.getUserCode(), jbdManuallyAdjustAlgebra.getWweek().toString());
				  if(curJbdManuallyAdjustAlgebra!=null){
						MessageUtil.saveLocaleMessage(request, "JbdManuallyAdjustStar.exist");
						return showForm(request, response, errors);
				  }
			}
			jbdManuallyAdjustAlgebra.setCreateNo(defSysUser.getUserCode());
			jbdManuallyAdjustAlgebra.setCreateTime(new Date());
			jbdManuallyAdjustAlgebra.setWweek(WeekFormatUtil.getFormatWeek("f",jbdManuallyAdjustAlgebra.getWweek()));
			jbdManuallyAdjustAlgebraManager.saveJbdManuallyAdjustAlgebra(jbdManuallyAdjustAlgebra);
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
