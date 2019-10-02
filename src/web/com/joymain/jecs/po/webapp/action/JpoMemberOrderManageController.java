package com.joymain.jecs.po.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.RequestUtil;

public class JpoMemberOrderManageController extends BaseController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(RequestUtil.isMobileRequest(request)){
        	return new ModelAndView("mobile/po/jpoMemberOrderManageList");
        }
		return null;
	}

}
