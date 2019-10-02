package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.service.SysDataLogManager;
import com.joymain.jecs.sys.service.SysOperationLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysClickLogController implements Controller {
	private final Log log = LogFactory.getLog(SysClickLogController.class);
	private SysOperationLogManager sysOperationLogManager = null;
	private SysDataLogManager sysDataLogManager = null;

	public void setSysDataLogManager(SysDataLogManager sysDataLogManager) {
		this.sysDataLogManager = sysDataLogManager;
	}

	public void setSysOperationLogManager(SysOperationLogManager sysOperationLogManager) {
		this.sysOperationLogManager = sysOperationLogManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		request.setAttribute("sysDataLogManager", sysDataLogManager);

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField(new CustomField("companyCode", 1111, SessionLogin.getLoginUser(request).getCompanyCode()));
		crm.addField(new CustomField("masterUserCode", 1111, SessionLogin.getLoginUser(request).getUserCode()));

		Pager pager = new Pager("sysOperationLogListTable", request, 20);
		List sysOperationLogs = null;
		if (request.getParameter("search") != null) {
			sysOperationLogs = this.sysOperationLogManager.getSysOperationLogsByCrm(crm, pager);
		}
		//根据数据重新设置分页数据
		request.setAttribute("sysOperationLogListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("sys/sysClickLogList", Constants.SYSOPERATIONLOG_LIST, sysOperationLogs);
	}
}