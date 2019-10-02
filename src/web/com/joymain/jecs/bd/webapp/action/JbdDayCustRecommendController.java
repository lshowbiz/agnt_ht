package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.service.JbdDayCustRecommendManager;
import com.joymain.jecs.bd.service.JbdDayCustRecommendOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdDayCustRecommendController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JbdDayCustRecommendController.class);
	private JbdDayCustRecommendManager jbdDayCustRecommendManager = null;
	private JbdDayCustRecommendOrderManager jbdDayCustRecommendOrderManager = null;

	public void setJbdDayCustRecommendManager(JbdDayCustRecommendManager jbdDayCustRecommendManager) {
		this.jbdDayCustRecommendManager = jbdDayCustRecommendManager;
	}
	public void setJbdDayCustRecommendOrderManager(JbdDayCustRecommendOrderManager jbdDayCustRecommendOrderManager) {
		this.jbdDayCustRecommendOrderManager = jbdDayCustRecommendOrderManager;
	}

	
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String companyCode = "";
		SysUser defSysUser = SessionLogin.getLoginUser(request);
/*		if ("C".equals(defSysUser.getUserType())) {
			companyCode = defSysUser.getCompanyCode();
			if ("AA".equals(defSysUser.getCompanyCode())) {
				companyCode = null;
			}
		}else if("M".equals(defSysUser.getUserType())){
    		crm.addField("userCode", defSysUser.getUserCode());
    	}*/
		String strAction=request.getParameter("strAction");
	
		Pager pager = new Pager("jbdDayCustRecommendListTable", request, 1000);
		
		String userCode=request.getParameter("userCode");
	/*	 if("M".equals(defSysUser.getUserType())){
			 userCode=defSysUser.getUserCode();
	    }*/
		 
		String startCreateTime=request.getParameter("startCreateTime");
		String endCreateTime=request.getParameter("endCreateTime");
		String wweek=request.getParameter("wweek");
		crm.addField("userCode", userCode);
		crm.addField("startCreateTime", startCreateTime);
		crm.addField("endCreateTime", endCreateTime);
		crm.addField("wweek", wweek);
		
		
		if("jbdDayCustRecommendDetail".equals(strAction)){
			List jbdDayCustRecommendOrderList = jbdDayCustRecommendOrderManager.getJbdDayCustRecommendOrdersByCrmByHql(crm,  pager);
			request.setAttribute("jbdDayCustRecommendOrderListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("bd/jbdDayCustRecommendsDetail", "jbdDayCustRecommendOrderList", jbdDayCustRecommendOrderList);
		}
		
		if(StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(startCreateTime)&&StringUtil.isEmpty(endCreateTime)){
			request.setAttribute("jbdDayCustRecommendListTable_totalRows", pager.getTotalObjects());

			return new ModelAndView("bd/jbdDayCustRecommends", "jbdDayCustRecommendList", null);
			
		}else{
			List jbdDayCustRecommends = jbdDayCustRecommendManager.getJbdDayCustRecommendsByCrm(crm, pager);
			request.setAttribute("jbdDayCustRecommendListTable_totalRows", pager.getTotalObjects());

			return new ModelAndView("bd/jbdDayCustRecommends", "jbdDayCustRecommendList", jbdDayCustRecommends);
		}
		
	}
}
