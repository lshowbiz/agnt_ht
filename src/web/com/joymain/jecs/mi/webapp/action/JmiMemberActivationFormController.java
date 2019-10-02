package com.joymain.jecs.mi.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberActivationFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;
    
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    public JmiMemberActivationFormController() {
        setCommandName("jmiMember");
        setCommandClass(JmiMember.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {

    	//SysUser defSysUser=SessionLogin.getLoginUser(request);
    	JmiMember jmiMember = new JmiMember();
        
        return jmiMember;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        String yesno=request.getParameter("yesno");
        if("yes".equals(yesno)){

            try {
                jmiMemberManager.saveJmiMemberActive(defSysUser.getUserCode());
                request.getSession().setAttribute("active_success", this.getText("active.success"));
                return new ModelAndView("redirect:login.html");
    		} catch (Exception e) {
    			e.printStackTrace();
    			this.saveMessage(request, this.getText("bdSendRegister.operaterFail"));
    		}
    		
        }else if("no".equals(yesno)){
            try {
            	JmiMember jmiMember=defSysUser.getJmiMember();
            	jmiMember.setExitDate(new Date());
            	jmiMember.getSysUser().setState("0");
            	jmiMember.setFlag("2");
            	jmiMemberManager.saveJmiMember(jmiMember);
                return new ModelAndView("redirect:login.html");
    		} catch (Exception e) {
    			e.printStackTrace();
    			this.saveMessage(request, this.getText("bdSendRegister.operaterFail"));
    		}
        }
        

		return showForm(request, response, errors);
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	
		super.initBinder(request, binder);
	}

}
