package com.joymain.jecs.mi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.webapp.util.RequestUtil;


public class MiMemberCreatedController implements Controller {
	private final Log log = LogFactory.getLog(MiMemberCreatedController.class);
	private JmiMemberManager jmiMemberManager;
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		String userCode=request.getParameter("userCode");
		JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
    	
		request.setAttribute("jmiMember", jmiMember);
		
		

        if(RequestUtil.isMobileRequest(request)){
    		return new ModelAndView("mobile/mi/miMemberCreatedList");
        }
		
		
		return new ModelAndView("mi/miMemberCreatedList");
	}
}
