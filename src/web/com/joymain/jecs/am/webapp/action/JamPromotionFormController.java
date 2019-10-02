package com.joymain.jecs.am.webapp.action;

import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.am.model.JamPromotion;
import com.joymain.jecs.am.service.JamPromotionManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JamPromotionFormController extends BaseFormController {
    private JamPromotionManager jamPromotionManager = null;

    public void setJamPromotionManager(JamPromotionManager jamPromotionManager) {
        this.jamPromotionManager = jamPromotionManager;
    }
    public JamPromotionFormController() {
        setCommandName("jamPromotion");
        setCommandClass(JamPromotion.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JamPromotion jamPromotion = null;

        if (!StringUtils.isEmpty(id)) {
            jamPromotion = jamPromotionManager.getJamPromotion(id);
        } else {
            jamPromotion = new JamPromotion();
        }

        return jamPromotion;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        JamPromotion jamPromotion = (JamPromotion) command;
       
		String strAction = request.getParameter("strAction");
		if ("deleteJamPromotion".equals(strAction)  ) {
			jamPromotionManager.removeJamPromotion(jamPromotion.getId().toString());
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}else{
			if(checkForm(jamPromotion, errors)){
	    		return showForm(request, response, errors);
			}
			if("addJamPromotion".equals(strAction)){
				jamPromotion.setCreateUser(defSysUser.getUserCode());
				jamPromotion.setCreateDate(new Date());
				jamPromotion.setCompanyCode(defSysUser.getCompanyCode());
			}
			jamPromotionManager.saveJamPromotion(jamPromotion);
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
    
    private boolean checkForm(JamPromotion jamPromotion,BindException errors){
    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jamPromotion.getPmName())){
			errors.rejectValue("pmName", "isNotNull",new Object[] { LocaleUtil.getLocalText("jamPromotion.pmName") }, "");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jamPromotion.getPmType())){
			errors.rejectValue("pmType", "isNotNull",new Object[] { LocaleUtil.getLocalText("customerRecord.type") }, "");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jamPromotion.getPmWay())){
			errors.rejectValue("pmWay", "isNotNull",new Object[] { LocaleUtil.getLocalText("jamPromotion.pmWay") }, "");
			isNotPass = true;
    	}
    	return isNotPass;
    }
}
