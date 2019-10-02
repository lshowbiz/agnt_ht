package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.am.model.JamDownload;
import com.joymain.jecs.mi.model.JmiBlacklist;
import com.joymain.jecs.mi.service.JmiBlacklistManager;
import com.joymain.jecs.mi.service.JmiMemberManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiBlacklistFormController extends BaseFormController {
    private JmiBlacklistManager jmiBlacklistManager = null;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJmiBlacklistManager(JmiBlacklistManager jmiBlacklistManager) {
        this.jmiBlacklistManager = jmiBlacklistManager;
    }
    public JmiBlacklistFormController() {
        setCommandName("jmiBlacklist");
        setCommandClass(JmiBlacklist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String blId = request.getParameter("blId");
        JmiBlacklist jmiBlacklist = null;

        if (!StringUtils.isEmpty(blId)) {
            jmiBlacklist = jmiBlacklistManager.getJmiBlacklist(blId);
        } else {
            jmiBlacklist = new JmiBlacklist();
        }

        return jmiBlacklist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        JmiBlacklist jmiBlacklist = (JmiBlacklist) command;
        
        
        
        
        
        
        String strAction = request.getParameter("strAction");
		if ("deleteJmiBlacklist".equals(strAction)  ) {
			jmiBlacklistManager.removeJmiBlacklist(jmiBlacklist.getBlId().toString());
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}else{
			if("addJmiBlacklist".equals(strAction)&&checkJmiBlacklist(jmiBlacklist, errors)){
				return showForm(request, response, errors);
			}
			if("addJmiBlacklist".equals(strAction)){
				jmiBlacklist.setCreateNo(defSysUser.getUserCode());
				jmiBlacklist.setCreateTime(new Date());
				jmiBlacklist.setCompanyCode(defSysUser.getCompanyCode());
				//jmiBlacklist.setStatus("0");
			}
			
			jmiBlacklistManager.saveJmiBlacklist(jmiBlacklist);
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			
		}
        
        
        


        return new ModelAndView("redirect:jmiBlacklists.html?strAction=jmiBlacklists");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
    

    private boolean checkJmiBlacklist(JmiBlacklist jmiBlacklist,BindException errors){
    	boolean isNotPass=false;
    	if (StringUtil.isEmpty(jmiBlacklist.getPapertype())) {
			errors.rejectValue("papertype", "isNotNull",new Object[] { LocaleUtil.getLocalText("miMember.papertype") }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiBlacklist.getPapernumber())) {
			errors.rejectValue("papernumber", "isNotNull",new Object[] { LocaleUtil.getLocalText("miMember.papernumber") }, "");
			isNotPass = true;
    	}else if(!jmiBlacklistManager.getCheckJmiBlacklist(jmiBlacklist.getPapertype(), jmiBlacklist.getPapernumber())){
			errors.reject("miMember.idNo.isIn");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiBlacklist.getBlackType())) {
			errors.rejectValue("blackType", "isNotNull",new Object[] { LocaleUtil.getLocalText("customerRecord.type") }, "");
			isNotPass = true;
    	}
    	if(!StringUtil.isEmpty(jmiBlacklist.getUserCode()) &&  null==jmiMemberManager.getJmiMember(jmiBlacklist.getUserCode())){
    		errors.reject("miMember.notFound");
			isNotPass = true;
    	}
    	return isNotPass;
    }

}
