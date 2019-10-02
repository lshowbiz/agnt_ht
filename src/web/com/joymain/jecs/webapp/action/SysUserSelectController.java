package com.joymain.jecs.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysUserSelectController implements Controller {
	private SysUserManager sysUserManager = null;
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
//		sysUserManager.get
		int num =0;
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("sysUserListTable",request, 150);
		String searchFlag = request.getParameter("searchFlag");
		String view = "common/sysUserSelect";
		crm.setValue("state", "1");
		String selectControl = request.getParameter("selectControl");
		if("company".equals(selectControl)){
			crm.setValue("userType", "C");
		}
		if("manager".equals(selectControl)){
			crm.setValue("userType", "C");
			crm.setValue("companyCode", "AA");
		}
		if("agent".equals(selectControl)){
			crm.setValue("userType", "P','Q");
		}
		if("member".equals(selectControl)){
			crm.setValue("userType", "M");
		}
		if("agentAndMember".equals(selectControl)){
			crm.setValue("userType", "P','Q','M");
			
			//view = "sys/sysUserSearch";
		}
//		String userCodeElementId = request.getParameter("userCodeElementId");
//		String userNameElementId = request.getParameter("userNameElementId");
		Map map = RequestUtil.populateMap(request);
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		if(!sessionLogin.getIsManager()){
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		}
		
//		List list = sysUserManager.get
		String userCode = request.getParameter("userCode");
		List list = new ArrayList();
		if(StringUtils.isNotEmpty(userCode)){
			searchFlag="show";
		}
			
		if("show".equals(searchFlag)){
			list = sysUserManager.getSysUsers(crm, pager);
			num=pager.getTotalObjects();
		}
		request.setAttribute("sysUserListTable_totalRows", num);
		request.setAttribute("sysUserList", list);
		request.setAttribute("requestMap", map);
		return new ModelAndView(view);
	}
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

}
