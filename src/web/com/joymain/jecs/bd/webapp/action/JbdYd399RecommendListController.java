package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.service.JbdYd399RecommendListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class JbdYd399RecommendListController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JbdYd399RecommendListController.class);
	private JbdYd399RecommendListManager jbdYd399RecommendListManager = null;

	public void setJbdYd399RecommendListManager(JbdYd399RecommendListManager jbdYd399RecommendListManager) {
		this.jbdYd399RecommendListManager = jbdYd399RecommendListManager;
	}
	
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		CommonRecord crm = RequestUtil.toCommonRecord(request);
	
		Pager pager = new Pager("jbdYd399RecommendListTable", request, 20);
		
		String userCode=request.getParameter("userCode");
		 
		String startCreateTime=request.getParameter("startCreateTime");
		String endCreateTime=request.getParameter("endCreateTime");
		String memberType=request.getParameter("memberType");
		String freezeStatus=request.getParameter("freezeStatus");
		String sendStatus=request.getParameter("sendStatus");
		crm.addField("userCode", userCode);
		crm.addField("startCreateTime", startCreateTime);
		crm.addField("endCreateTime", endCreateTime);
		crm.addField("memberType", memberType);
		crm.addField("freezeStatus", freezeStatus);
		crm.addField("sendStatus", sendStatus);
		
		if(StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(startCreateTime)&&StringUtil.isEmpty(endCreateTime)&&StringUtil.isEmpty(memberType)&&StringUtil.isEmpty(freezeStatus)&&StringUtil.isEmpty(sendStatus)){
			request.setAttribute("jbdYd399RecommendListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("bd/jbdYd399RecommendLists", "jbdYd399RecommendList", null);
		}else{
			List jbdYd399RecommendLists = jbdYd399RecommendListManager.getJbdYd399RecommendListsByCrm(crm, pager);
			request.setAttribute("jbdYd399RecommendListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("bd/jbdYd399RecommendLists", "jbdYd399RecommendList", jbdYd399RecommendLists);
		}
	}
	
	
}
