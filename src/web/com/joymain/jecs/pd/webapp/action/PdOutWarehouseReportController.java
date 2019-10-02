package com.joymain.jecs.pd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.pd.service.PdOutWarehouseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdOutWarehouseReportController extends BaseController implements
		Controller {

	private PdOutWarehouseManager pdOutWarehouseManager;
	public void setPdOutWarehouseManager(PdOutWarehouseManager pdOutWarehouseManager) {
		this.pdOutWarehouseManager = pdOutWarehouseManager;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//开始日志--start
		Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "pdOutWarehouseReports", "");
		//开始日志--end
		
		// TODO Auto-generated method stub
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String strAction = request.getParameter("strAction");
		String view = "pd/pdOutWarehouseReports";
		String exportXsl = "/WEB-INF/xls/pdOutWarehouseReport.xls";
		String showFlag = request.getParameter("showFlag");
		if ("show".equals(showFlag)) {
			List list = pdOutWarehouseManager.getSumGroupByOwt(crm);
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String warehouseNo = request.getParameter("warehouseNo");
			String companyCode = request.getParameter("companyCode");

			ExcelUtils.addValue("list", list);
			ExcelUtils.addValue("startTime", startTime);
			ExcelUtils.addValue("endTime", endTime);
			ExcelUtils.addValue("warehouseNo", warehouseNo);
			ExcelUtils.addValue("companyCode", companyCode);
			// view = "pd/pdSendGoogsPrint";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ companyCode + "-" + warehouseNo + "(" + startTime + "-"
					+ endTime + ")" + ".xls\"");
			ExcelUtils.export(request.getSession().getServletContext(),
					exportXsl, response.getOutputStream());

			//结束日志--start
			ReportLogUtil.endReport(refId, "pdOutWarehouseReports");
			//结束日志--end
			
			return null;
		} else {
			//结束日志--start
			ReportLogUtil.endReport(refId, "pdOutWarehouseReports");
			//结束日志--end
			return new ModelAndView(view);
		} 
	}

}
