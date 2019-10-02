package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.mi.model.JmiTaiwanTravel;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pm.model.JpmCardSeq;
import com.joymain.jecs.pm.service.JpmCardSeqManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmCardSeqFormController extends BaseFormController {
    private JpmCardSeqManager jpmCardSeqManager = null;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    private JpoMemberOrderManager jpoMemberOrderManager;
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public void setJpmCardSeqManager(JpmCardSeqManager jpmCardSeqManager) {
        this.jpmCardSeqManager = jpmCardSeqManager;
    }
    public JpmCardSeqFormController() {
        setCommandName("jpmCardSeq");
        setCommandClass(JpmCardSeq.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        JpmCardSeq jpmCardSeq = null;


            jpmCardSeq = new JpmCardSeq();

        return jpmCardSeq;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        JpmCardSeq jpmCardSeq = (JpmCardSeq) command;
        
        if(checkJpmCardSeq(jpmCardSeq, errors, request)){
    		return showForm(request, response, errors);
        }
        
        try {
        	jpmCardSeqManager.saveUserJpmCardSeqByUser(jpmCardSeq, new Integer(request.getParameter("qty")));
        	this.saveMessage(request,  this.getText("sys.message.updateSuccess"));
		} catch (Exception e) {
			this.saveMessage(request, this.getText(e.getMessage()));
		}
      

        return new ModelAndView("redirect:editJpmCardSeq.html");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
    
    private boolean checkJpmCardSeq(JpmCardSeq jpmCardSeq ,BindException errors,HttpServletRequest request){

    	boolean isNotPass=false;
    	if(StringUtil.isEmpty(jpmCardSeq.getUserCode())){
			errors.rejectValue("userCode", "isNotNull",new Object[] {this.getText("miMember.memberNo")}, "");
			isNotPass = true;
    	}else if (null==jmiMemberManager.getJmiMember(jpmCardSeq.getUserCode())) {
			errors.reject("miMember.notFound");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(jpmCardSeq.getMemberOrderNo())){
			errors.rejectValue("userCode", "isNotNull",new Object[] {this.getText("miMember.memberNo")}, "");
			isNotPass = true;
    	}else if (null==jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(jpmCardSeq.getMemberOrderNo())) {
			errors.reject("error.poMemberOrder.null");
			isNotPass = true;
    	}else if(!jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(jpmCardSeq.getMemberOrderNo()).getSysUser().getUserCode().equals(jpmCardSeq.getUserCode())){
			errors.reject("error.poMemberOrder.null");
			isNotPass = true;
    	}
    	if(StringUtil.isEmpty(request.getParameter("qty"))||!StringUtil.isInteger(request.getParameter("qty"))){
			errors.reject("register.us.select.number");
			isNotPass = true;
    	}else if(new Integer(request.getParameter("qty"))<=0){
			errors.reject("register.us.select.number");
			isNotPass = true;
    	}
    	return isNotPass;
    }
}
