package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysOperationLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


public class UserOperationLogController extends BaseController implements
		Controller {
	private SysOperationLogManager sysOperationLogManager = null;
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SysUser sessionLogin  = SessionLogin.getLoginUser(request);
		String view = "sys/userOperationLogList";
		String strAction=request.getParameter("strAction");
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		if(sessionLogin.getIsCompany()){
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		}
		
		if("agent".equalsIgnoreCase(strAction)){
			crm.setValue("userType","P','Q");
		}else if("member".equalsIgnoreCase(strAction)){
			crm.setValue("userType","M");
		}
		
		Pager pager = new Pager("sysOperationLogListTable", request, 20);
		List sysOperationLogs = this.sysOperationLogManager.getSysOperationLogsByCrm(crm, pager);
		request.setAttribute("sysOperationLogListTable_totalRows", pager.getTotalObjects());
		request.setAttribute("sysOperationLogList", sysOperationLogs);
		return new ModelAndView(view);
	}
	public void setSysOperationLogManager(
			SysOperationLogManager sysOperationLogManager) {
		this.sysOperationLogManager = sysOperationLogManager;
	}

}
