package com.joymain.jecs.sys.webapp.action;

import java.util.Date;
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
import com.joymain.jecs.util.DateUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysOperationLogController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysOperationLogController.class);
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
		String currentMonth = DateUtil.getDateTime("yyyyMM", new Date());
		List<String> months = DateUtil.getMonthsBetween2Mon("200910", currentMonth, "yyyyMM");
		request.setAttribute("months", months);
		request.setAttribute("currentMonth", currentMonth);
		request.setAttribute("sysDataLogManager", sysDataLogManager);

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField(new CustomField("companyCode", 1111, SessionLogin.getLoginUser(request).getCompanyCode()));
		crm.addField(new CustomField("masterUserCode", 1111, SessionLogin.getLoginUser(request).getUserCode()));
		crm.addField(new CustomField("characterCode", 1111, SessionLogin.getLoginUser(request).getDefCharacterCoding()));
		crm.addField(new CustomField("viewType", 1111, "listSysOperationLogs"));

		Pager pager = new Pager("sysOperationLogListTable", request, 20);
		List sysOperationLogs=null;
		if(request.getParameter("search")!=null){
			sysOperationLogs = this.sysOperationLogManager.getSysOperationLogsNewVersion(crm, pager);
		}
		//根据数据重新设置分页数据
		request.setAttribute("sysOperationLogListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("sys/sysOperationLogList", Constants.SYSOPERATIONLOG_LIST, sysOperationLogs);
	}
}