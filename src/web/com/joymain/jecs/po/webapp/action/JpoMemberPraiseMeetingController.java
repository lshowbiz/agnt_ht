package com.joymain.jecs.po.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpoMemberPraiseMeetingController  extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberROrderController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	Map poMemberMeetingUserCode = null;
    	if("C".equals(loginSysUser.getUserType())){
    	   poMemberMeetingUserCode = jpoMemberOrderManager.getJpoMemberPraiseMeetingUserCode();
    	   log.info(poMemberMeetingUserCode);
    	
	    }
    	  request.setAttribute("poMemberMeetingUserCode", poMemberMeetingUserCode);
    	return new ModelAndView("po/jpoMemberPraiseMeeting", Constants.JPOMEMBERORDER_LIST, poMemberMeetingUserCode);

}
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
}
