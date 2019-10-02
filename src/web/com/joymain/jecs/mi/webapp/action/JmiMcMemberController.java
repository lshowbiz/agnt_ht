package com.joymain.jecs.mi.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class JmiMcMemberController extends BaseController implements Controller {

	private JmiMemberManager jmiMemberManager;
	private JmiLinkRefManager jmiLinkRefManager;
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String view="";
//		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String strAction = request.getParameter("strAction");
		String userCode =  request.getParameter("userCode");
		List errorMsgs = new ArrayList();
		if("statusView".equals(strAction)){
			view="mi/statusView";
			if(StringUtils.isNotEmpty(userCode)){
				if(jmiMemberManager.getIsJumper(userCode) || jmiLinkRefManager.getJmiLinkRefIsM(userCode)){
					JmiMember jmiMember =jmiMemberManager.getJmiMember(userCode);
					request.setAttribute("jmiMember", jmiMember);
				}else{
					errorMsgs.add(LocaleUtil.getLocalText("miMember.notFound"));
					request.setAttribute("errorMsgs", errorMsgs);
				}
			}
			
		}else if("modifyView".equals(strAction)){
			view="mi/modifyView";
			if(StringUtils.isNotEmpty(userCode)){
			if(jmiLinkRefManager.getJmiLinkRefIsM(userCode)){
				Map oldInfo = jmiMemberManager.getMemberInfo(userCode, "trans.jmi_member");
				Map newInfo = jmiMemberManager.getMemberInfo(userCode, "jmi_member");
				request.setAttribute("oldInfo",oldInfo);
				request.setAttribute("newInfo",newInfo);
			}else{
				errorMsgs.add(LocaleUtil.getLocalText("miMember.notFound"));
				request.setAttribute("errorMsgs", errorMsgs);
			}
			}
		}
		 return new ModelAndView(view);
	}

}
