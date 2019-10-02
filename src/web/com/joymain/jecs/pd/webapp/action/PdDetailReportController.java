package com.joymain.jecs.pd.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.pd.service.PdEnterWarehouseManager;
import com.joymain.jecs.pd.service.PdFlitWarehouseManager;
import com.joymain.jecs.pd.service.PdOutWarehouseManager;
import com.joymain.jecs.pd.service.PdReturnPurchaseManager;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdDetailReportController extends BaseController implements
		Controller {

	private PdOutWarehouseManager pdOutWarehouseManager;
	private PdReturnPurchaseManager pdReturnPurchaseManager;
	private PdEnterWarehouseManager pdEnterWarehouseManager;
	private PdFlitWarehouseManager pdFlitWarehouseManager;
	public void setPdOutWarehouseManager(PdOutWarehouseManager pdOutWarehouseManager) {
		this.pdOutWarehouseManager = pdOutWarehouseManager;
	}

	
	public void setPdReturnPurchaseManager(
			PdReturnPurchaseManager pdReturnPurchaseManager) {
		this.pdReturnPurchaseManager = pdReturnPurchaseManager;
	}


	public void setPdEnterWarehouseManager(
			PdEnterWarehouseManager pdEnterWarehouseManager) {
		this.pdEnterWarehouseManager = pdEnterWarehouseManager;
	}


	public void setPdFlitWarehouseManager(
			PdFlitWarehouseManager pdFlitWarehouseManager) {
		this.pdFlitWarehouseManager = pdFlitWarehouseManager;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String strAction = request.getParameter("strAction");
		String view = "pd/pdDetailReports";
		String showFlag = request.getParameter("showFlag");
		String exportXsl = "";
		
		//开始日志--start
		Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), showFlag, "");
		//开始日志--end
		
		if("showOutDetailReport".equals(showFlag)){
			List list = pdOutWarehouseManager.getOutDetails(crm);
			ExcelUtils.addValue("list", list);
			exportXsl="/WEB-INF/xls/pdOutWarehouseDetailReport.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=pdOutDetailsReport.xls\"");
			ExcelUtils.export(request.getSession().getServletContext(),
					exportXsl, response.getOutputStream());

			//结束日志--start
			ReportLogUtil.endReport(refId, showFlag);
			//结束日志--end
			
			return null;
		}else if("showReturnDetailReport".equals(showFlag)){
			List list = pdReturnPurchaseManager.getReturnDetails(crm);
			ExcelUtils.addValue("list", list);
			exportXsl="/WEB-INF/xls/pdReturnReport.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=pdReturnDetailsReport.xls\"");
			ExcelUtils.export(request.getSession().getServletContext(),
					exportXsl, response.getOutputStream());

			//结束日志--start
			ReportLogUtil.endReport(refId, showFlag);
			//结束日志--end
			
			return null;
		}else if("showEnterDetailReport".equals(showFlag)){
			List list = pdEnterWarehouseManager.getEnterDetails(crm);
			ExcelUtils.addValue("list", list);
			exportXsl="/WEB-INF/xls/pdEnterDetailReport.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=pdEnterDetailsReport.xls\"");
			ExcelUtils.export(request.getSession().getServletContext(),
					exportXsl, response.getOutputStream());
			
			//结束日志--start
			ReportLogUtil.endReport(refId, showFlag);
			//结束日志--end
			
			return null;
		}else if("showFlitDetailReport".equals(showFlag)){
			//Modify By WuCF 20140728  查询记录条数，控制记录条数
			Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(SessionLogin.getLoginUser(request).getCompanyCode(), "export.listnum"));
			crm.setValue("listNum", listNum);
			
			List list = pdFlitWarehouseManager.getFlitDetail(crm);
			ExcelUtils.addValue("list", list);
			exportXsl="/WEB-INF/xls/pdFlitDetailReport.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=pdFlitDetailsReport.xls\"");
			ExcelUtils.export(request.getSession().getServletContext(),
					exportXsl, response.getOutputStream());
			//结束日志--start
			ReportLogUtil.endReport(refId, showFlag);
			//结束日志--end
			return null;
		}
		return new ModelAndView(view);
	}

}
