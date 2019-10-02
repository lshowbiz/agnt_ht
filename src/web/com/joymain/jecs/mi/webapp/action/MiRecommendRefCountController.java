package com.joymain.jecs.mi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MiRecommendRefCountController implements Controller {

	private JmiRecommendRefManager miRecommendRefManager;
	private JmiMemberManager jmiMemberManager;
	private SysUserManager sysUserManager;
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberNo = request.getParameter("memberNo");
		String userName = request.getParameter("userName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String recommendCount = request.getParameter("recommendCount");
		String strAction=request.getParameter("strActionOperator");
		String cardType=request.getParameter("cardType");
		SysUser curUser = SessionLogin.getLoginUser(request);
		String auditBDate = request.getParameter("auditBDate");
		String auditEDate = request.getParameter("auditEDate");
		String companyCode="";
        if("C".equals(curUser.getUserType())){
        	companyCode=curUser.getCompanyCode();  
    		if("AA".equals(curUser.getCompanyCode())){
    			companyCode = null;
    		}
    	}
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("companyCode", companyCode);
		
		Pager pager = new Pager("miRecommendCountTable", request, Constants.PAGE_SIZE);
		if(StringUtil.isEmpty(memberNo)&&StringUtil.isEmpty(userName)&&StringUtil.isEmpty(recommendCount)&&!StringUtil.isDate(startTime)&&!StringUtil.isDate(endTime)&&!StringUtil.isDate(auditBDate)&&!StringUtil.isDate(auditEDate)){
			request.setAttribute("miRecommendCountTable_totalRows", 0);
			return new ModelAndView("mi/miRecommendRefCount");
		}else{
			if ("search".equals(strAction)) {
				request.setAttribute("miRecommendCountTable_totalRows", 0);
				return new ModelAndView("mi/miRecommendRefCount");
			}
			if("countList".equals(request.getParameter("strActionOperator"))){
				List countList=jmiMemberManager.getMiRecommendRefsCountList(memberNo, startTime, endTime);
				request.setAttribute("miRecommendCountTable_totalRows",countList.size());
				return new ModelAndView("mi/miRecommendRefCount","miRecommendRefList",countList);
			}
			List list = jmiMemberManager.getMiRecommendRefsCounts(crm, pager);
			request.setAttribute("miRecommendCountTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("mi/miRecommendRefCount", "miRecommendRefList", list);
		}
		
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setMiRecommendRefManager(
			JmiRecommendRefManager miRecommendRefManager) {
		this.miRecommendRefManager = miRecommendRefManager;
	}


}
