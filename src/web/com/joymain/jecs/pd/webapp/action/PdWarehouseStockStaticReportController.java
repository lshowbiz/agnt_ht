package com.joymain.jecs.pd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pd.service.PdWarehouseStockTraceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdWarehouseStockStaticReportController extends BaseController
		implements Controller {

	private PdWarehouseStockManager pdWarehouseStockManager = null;
	private PdWarehouseStockTraceManager pdWarehouseStockTraceManager = null;

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void setPdWarehouseStockTraceManager(
			PdWarehouseStockTraceManager pdWarehouseStockTraceManager) {
		this.pdWarehouseStockTraceManager = pdWarehouseStockTraceManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String strAction = request.getParameter("strAction");
		String view = "pd/pdWarehouseStockStaticReports";
		String exportXsl = "/WEB-INF/xls/pdWarehouseStocksStaticReport.xls";
		String showFlag = request.getParameter("showFlag");
		if ("showDetail".equals(showFlag)) {
			//开始日志--start
			Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "pd-showDetail-show", "");
			//开始日志--end
			
			exportXsl = "/WEB-INF/xls/pdWarehouseStocksTraceDetailReport.xls";
			List list = pdWarehouseStockTraceManager
					.getStockTraceDetailByCrm(crm);
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
			ReportLogUtil.endReport(refId, "pd-showDetail-show");
			//结束日志--end
			
			return null;
		} else if ("show".equals(showFlag)) {
			//开始日志--start
			Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "pd-ship-show", "");
			//开始日志--end
			List list = pdWarehouseStockTraceManager
					.getPdWarehouseStockReportByCrm(crm);
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
			ReportLogUtil.endReport(refId, "pd-ship-show");
			//结束日志--end
			
			return null;
		} else if ("showDetailFince".equals(showFlag)) {	//�����ECϵͳ������-�������汨��
			//开始日志--start
			Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "pd-showDetailFince-show", "");
			//开始日志--end
			
			exportXsl = "/WEB-INF/xls/pdWarehouseStocksTraceFinceReport.xls";
//			List list = pdWarehouseStockTraceManager
//			.getPbWarehouseStocksTraceFinceReport(crm);
			List list = pdWarehouseStockTraceManager
			.getPbWarehouseStocksTraceFinceReport(crm);
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
			ReportLogUtil.endReport(refId, "pd-showDetailFince-show");
			//结束日志--end
			
			return null;
		} else {
			return new ModelAndView(view);
		}
 
	}

}
