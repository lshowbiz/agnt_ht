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
import com.joymain.jecs.util.DateUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

public class SysDataLogController implements Controller {
	private final Log log = LogFactory.getLog(SysDataLogController.class);
	private SysDataLogManager sysDataLogManager = null;

	public void setSysDataLogManager(SysDataLogManager sysDataLogManager) {
		this.sysDataLogManager = sysDataLogManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		String view = "sys/sysDataLogList";
//		request.setAttribute("sysDataLogManager", sysDataLogManager);
		String viewType = request.getParameter("viewType");
		if("showSingleOperation".equals(viewType)){
			view = "sys/singleOperationDataList";
		}
		String currentMonth = DateUtil.getDateTime("yyyyMM", new Date());
		List<String> months = DateUtil.getMonthsBetween2Mon("200910", currentMonth, "yyyyMM");
		request.setAttribute("months", months);
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		// 设置分页参数
		Pager pager = new Pager("sysDataLogListTable", request, 20);
		// 分页获取数据
		List sysDataLogs=null;
		if(request.getParameter("search")!=null){
			sysDataLogs = sysDataLogManager.getSysDataLogsNewVersionByPage(crm, pager);
		}
		// 根据数据重新设置分页数据
		request.setAttribute("sysDataLogListTable_totalRows", pager.getTotalObjects());
		request.setAttribute("currentMonth", currentMonth);
		return new ModelAndView(view, Constants.SYSDATALOG_LIST, sysDataLogs);
	}
}
