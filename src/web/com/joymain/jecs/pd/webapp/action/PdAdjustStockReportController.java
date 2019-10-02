package com.joymain.jecs.pd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pd.service.PdAdjustStockManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class PdAdjustStockReportController extends BaseController implements
		Controller {

	PdAdjustStockManager pdAdjustStockManager;
	public void setPdAdjustStockManager(PdAdjustStockManager pdAdjustStockManager) {
		this.pdAdjustStockManager = pdAdjustStockManager;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String strAction = request.getParameter("strAction");
		String view = "pd/pdAdjustStockReports";
		String exportXsl = "/WEB-INF/xls/pdOutWarehouseReport.xls";
		String showFlag = request.getParameter("showFlag");
		if ("show".equals(showFlag)) {
			List list = pdAdjustStockManager.getSumGroupByAst(crm);
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

			return null;
		} else {
			return new ModelAndView(view);
		}

	}

}
