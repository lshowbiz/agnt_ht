package com.joymain.jecs.bd.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.model.VJbdMemberLinkCalc;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberLinkCalcHistFormController extends BaseFormController {
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager = null;
	public void setJbdMemberLinkCalcHistManager(JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
        this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
    }
    public JbdMemberLinkCalcHistFormController() {
        setCommandName("jbdMemberLinkCalcHist");
        setCommandClass(VJbdMemberLinkCalc.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {

        String userCode = request.getParameter("userCode");
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("M".equals(defSysUser.getUserType())){
        	userCode=defSysUser.getUserCode();
        }
        String wweek = request.getParameter("wweek");
        
        VJbdMemberLinkCalc jbdMemberLinkCalcHist = null;

        if (!StringUtils.isEmpty(userCode)&&!StringUtils.isEmpty(wweek)) {
            jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHistsByUserCodeWeek(userCode, wweek);
            
            if(jbdMemberLinkCalcHist!=null && jbdMemberLinkCalcHist.getWweek()>201516){
            	Map jbdLevel=jbdMemberLinkCalcHistManager.getJBdLevel(userCode, wweek);
            	request.setAttribute("jbdLevel", jbdLevel);
            }
            
            if(jbdMemberLinkCalcHist==null||(!"AA".equals(defSysUser.getCompanyCode())&&!defSysUser.getCompanyCode().equals(jbdMemberLinkCalcHist.getCompanyCode()))){
            	return new JbdMemberLinkCalcHist();
            }
        } else {
            jbdMemberLinkCalcHist = new VJbdMemberLinkCalc();
        }

        if(RequestUtil.isMobileRequest(request)){
        	this.setFormView("/mobile/bd/jbdMemberLinkCalcHistForm");
        }else{
        	

        	String newWeek=(String) Constants.sysConfigMap.get("AA").get("new.week");
        	if(StringUtil.formatInt(wweek)>=StringUtil.formatInt("201805")){
        		this.setFormView("/bd/jbdMemberLinkCalcHistForm201805");
        	}else  if(StringUtil.formatInt(wweek)>=StringUtil.formatInt("201607") && StringUtil.formatInt(wweek)< StringUtil.formatInt("201805")){
        		this.setFormView("/bd/jbdMemberLinkCalcHistForm201607");
        	}else if(StringUtil.formatInt(wweek)>=StringUtil.formatInt(newWeek)){
            	this.setFormView("/bd/jbdMemberLinkCalcHistForm2015");
        	}else{
        		this.setFormView("/bd/jbdMemberLinkCalcHistForm");
        	}
        	
        }

        return jbdMemberLinkCalcHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
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
