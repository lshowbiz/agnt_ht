package com.joymain.jecs.pr.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 显示退货单
 * @author Alvin
 *
 */
public class JprRefundViewController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JprRefundViewController.class);
    private JprRefundManager jprRefundManager = null;

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================
    	
        String roNo = request.getParameter("roNo");
        JprRefund jprRefund = jprRefundManager.getJprRefund(roNo);

        if("C".equals(defSysUser.getUserType())){
        	if("AA".equals(defSysUser.getCompanyCode())){
        		
        	}else{
	        	if(!jprRefund.getCompanyCode().equals(defSysUser.getCompanyCode())){
	        		return new ModelAndView("pr/jprRefundView", "jprRefund", new JprRefund());
	        	}
        	}
        }else{//代理商
        	if(!defSysUser.getUserCode().equals(jprRefund.getSysUser().getUserCode())){
        		return new ModelAndView("pr/jprRefundView", "jprRefund", null);
        	}
        }
	    return new ModelAndView("pr/jprRefundView", "jprRefund", jprRefund);
    }

	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}
}
