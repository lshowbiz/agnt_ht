package com.joymain.jecs.pd.webapp.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.pd.service.PdSendInfoDetailManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.report.CSVUtils;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdShipDetailReportController extends BaseController implements
		Controller {
	private PdSendInfoManager pdSendInfoManager = null;
	private PdSendInfoDetailManager pdSendInfoDetailManager = null;
	public void setPdSendInfoDetailManager(
			PdSendInfoDetailManager pdSendInfoDetailManager) {
		this.pdSendInfoDetailManager = pdSendInfoDetailManager;
	}
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.initStateCodeParem(request);
		String view = "pd/pdShipDetailReport";
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		String strAction = request.getParameter("strAction");
		String reportFlag = request.getParameter("reportFlag");
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String companyCode = request.getParameter("companyCode");
		if(sessionLogin.getIsCompany()){
			companyCode = sessionLogin.getCompanyCode();
			
		}
		crm.setValue("companyCode", companyCode);
		
		if("shipDetailReport".equals(strAction)){
			view = "pd/pdShipDetailReport";
		}
		if("showShipByWarehouse".equals(reportFlag)){
			showShipByWarehouse(request,response);
			return null;
		}else if("showShipByState".equals(reportFlag)){
			showShipByState(request,response);
			return null;
		}else if("showShipByWarehouseCsv".equals(reportFlag)){//Modify By WuCF 20170516 导出csv文件
			String returnUrl = showShipByWarehouseCsv(request,response);
			
			String returnUrlStr = request.getParameter("returnUrl");
			if(returnUrlStr!=null && !"".equals(returnUrlStr)){
				returnUrl = returnUrlStr;
			}
			System.out.println("===returnUrlStr："+returnUrl);
			
			view = "redirect:pdShipDetailReports.html?strAction=shipDetailReport&returnUrl="+returnUrl;
			
			request.setAttribute("returnUrl", returnUrl);
			return new ModelAndView(view);
		}
		String returnUrlStr = request.getParameter("returnUrl");
		System.out.println("===returnUrlStr："+returnUrlStr);
		System.out.println("===view："+view);
		if(returnUrlStr!=null && !"".equals(returnUrlStr)){
			request.setAttribute("returnUrl", returnUrlStr);
		}
		return new ModelAndView(view);
	}
	private void showShipByState(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//开始日志--start
		Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "pdShipDetailsByState", "");
		//开始日志--end
		
		// TODO Auto-generated method stub
		List list = new ArrayList();
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String config = "/WEB-INF/xls/pdShipDetailsByState.xls";
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		String companyCode = request.getParameter("companyCode");
		if(sessionLogin.getIsCompany()){
			companyCode = sessionLogin.getCompanyCode();
			
		}
		crm.setValue("companyCode", companyCode);
		
		if("PH".equals(companyCode)){
			config = "/WEB-INF/xls/pdShipDetailsByState-ph.xls";
			list = pdSendInfoDetailManager.getShipDetailsByTown(crm);
		}else{
			list = pdSendInfoDetailManager.getShipDetailsByState(crm);
		}
		
		
		
		log.info("listsize->"+list.size());
		ExcelUtils.addValue("list", list);
//		ExcelUtils.addValue("warehouseNo", crm.getString("warehouseNo", ""));
		ExcelUtils.addValue("startime", crm.getString("startTime", ""));
		ExcelUtils.addValue("endTime", crm.getString("endTime", ""));
//		view = "pd/pdSendGoogsPrint";
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    String filename="pdShipmentsByArea_"+companyCode+"_("+crm.getString("startTime", "")+"-"+crm.getString("endTime", "");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + ").xls\"");
	    ExcelUtils.export(request.getSession().getServletContext(),
	                          config,response.getOutputStream());
	  //结束日志--start
		ReportLogUtil.endReport(refId, "pdShipDetailsByState");
	  //结束日志--end
//		return null;
	}
	private void showShipByWarehouse(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//开始日志--start
		Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "pdShipDetailsByWarehouse", "");
		//开始日志--end
		// TODO Auto-generated method stub
		List list = new ArrayList();
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String config = "/WEB-INF/xls/pdShipDetailsByWarehouse.xls";
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		String companyCode = request.getParameter("companyCode");
		if(sessionLogin.getIsCompany()){
			companyCode = sessionLogin.getCompanyCode();
			
		}
		crm.setValue("companyCode", companyCode);
		if("PH".equals(companyCode)){
			config = "/WEB-INF/xls/pdShipDetailsByWarehouse-ph.xls";
			
		}
		//Modify By WuCF 20140514  查询记录条数，控制记录条数
		Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "export.listnum"));
		crm.setValue("listNum", listNum);
		list = pdSendInfoDetailManager.getShipDetails(crm);
		
		//判断结果集合是否超过限度
		if(list!=null && list.size()>=1 && "1".equals(list.get(0).toString())){
			String key = "导出数据数量超过最大限制数："+listNum+"，导出失败！请缩小查询条件范围！";
			Map<String,String> map = new HashMap<String,String>();
			map.put("SI_NO", key);
			list = new ArrayList();
			list.add(map);
		}
		
		log.info("listsize->"+list.size());
		ExcelUtils.addValue("list", list);
//		ExcelUtils.addValue("warehouseNo", crm.getString("warehouseNo", ""));
		ExcelUtils.addValue("startime", crm.getString("startTime", ""));
		ExcelUtils.addValue("endTime", crm.getString("endTime", ""));
//		view = "pd/pdSendGoogsPrint";
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	    String filename="pdShipmentsByWarehouse_"+companyCode+"_("+crm.getString("startTime", "")+"-"+crm.getString("endTime", "");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + ").xls\"");
	    ExcelUtils.export(request.getSession().getServletContext(),
	                          config,response.getOutputStream());
	  //结束日志--start
		ReportLogUtil.endReport(refId, "pdShipDetailsByWarehouse");
	  //结束日志--end
	}

	
	private String showShipByWarehouseCsv(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String returnUrl = "";
		//开始日志--start
		Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "pdShipDetailsByWarehouse", "");
		//开始日志--end
		// TODO Auto-generated method stub
		List list = new ArrayList();
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String config = "/WEB-INF/xls/pdShipDetailsByWarehouse.xls";
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		String companyCode = request.getParameter("companyCode");
		if(sessionLogin.getIsCompany()){
			companyCode = sessionLogin.getCompanyCode();
			
		}
		crm.setValue("companyCode", companyCode);
		if("PH".equals(companyCode)){
			config = "/WEB-INF/xls/pdShipDetailsByWarehouse-ph.xls";
			
		}
		//Modify By WuCF 20140514  查询记录条数，控制记录条数
		Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "export.listnum2"));
		crm.setValue("listNum", listNum);
		list = pdSendInfoDetailManager.getShipDetails(crm);
		
		//判断结果集合是否超过限度
		String flag = "";
		if(list!=null && list.size()>=1 && "1".equals(list.get(0).toString())){
			String key = "导出数据数量超过最大限制数："+listNum+"，导出失败！请缩小查询条件范围！";
			Map<String,String> map = new HashMap<String,String>();
			map.put("SI_NO", key);
			list = new ArrayList();
			list.add(map);
			flag = "N";
			
			returnUrl = ""+listNum;
		}
		
		System.out.println("==========flag:"+flag+"---"+list.size());
		log.info("==========flag:"+flag+"---"+list.size());
		log.debug("==========flag:"+flag+"---"+list.size());
		
		//=======================Modify By WuCF 20170515 导出数据问题
		if(!"N".equals(flag) && list!=null && list.size()>=1){
			String xlsPath = ConfigUtil.getConfigValue("CN", "xls.path.url");
			List<String> dataList=new ArrayList<String>();
			dataList.add("发货单号,订单号,客户编号,创建者,创建时间,确认者,确认时间,发货仓,产品编码,产品名称,价格,数量,收货人,电话,移动电话,收货地址,物流公司,物流跟踪号,接口物流单号,条形码,确认备注,发货方式,是否备货");
			Map<String,String> map = null;
			StringBuffer strBuf = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(Object object : list){
				map = (Map<String,String>)object;
				strBuf = new StringBuffer(map.get("SI_NO")!=null ? String.valueOf(map.get("SI_NO")) : "");
				strBuf.append(",");
				strBuf.append(map.get("ORDER_NO")!=null ? String.valueOf(map.get("ORDER_NO")) : "");
				strBuf.append(",");
				strBuf.append(map.get("CUSTOM_CODE")!=null ? String.valueOf(map.get("CUSTOM_CODE")) : "");
				strBuf.append(",");
				strBuf.append(map.get("CREATE_USR_CODE")!=null ? String.valueOf(map.get("CREATE_USR_CODE")) : "");
				strBuf.append(",");
				strBuf.append(sdf.format(map.get("CREATE_TIME")));
				strBuf.append(",");
				
				strBuf.append(map.get("OK_USR_CODE")!=null ? String.valueOf(map.get("OK_USR_CODE")) : "");
				strBuf.append(",");
				strBuf.append(sdf.format(map.get("OK_TIME")));
				strBuf.append(",");
				strBuf.append(map.get("WAREHOUSE_NO")!=null ? String.valueOf(map.get("WAREHOUSE_NO")) : "");
				strBuf.append(",");
				strBuf.append(map.get("PRODUCT_NO")!=null ? String.valueOf(map.get("PRODUCT_NO")) : "");
				strBuf.append(",");
				strBuf.append(map.get("PRODUCT_NAME")!=null ? String.valueOf(map.get("PRODUCT_NAME")) : "");
				strBuf.append(",");
				
				strBuf.append(String.valueOf(map.get("PRICE")));
				strBuf.append(",");
				strBuf.append(String.valueOf(map.get("QTY")));
				strBuf.append(",");
				strBuf.append(map.get("RECIPIENT_NAME")!=null ? String.valueOf(map.get("RECIPIENT_NAME")) : "");
				strBuf.append(",");
				strBuf.append(map.get("RECIPIENT_PHONE")!=null ? String.valueOf(map.get("RECIPIENT_PHONE")) : "");
				strBuf.append(",");
				strBuf.append(map.get("RECIPIENT_MOBILE")!=null ? String.valueOf(map.get("RECIPIENT_MOBILE")) : "");
				strBuf.append(",");
				
				strBuf.append(map.get("STATE_PROVINCE_NAME")+"."+map.get("CITY_NAME")+"."+map.get("RECIPIENT_ADDR"));
				strBuf.append(",");
				strBuf.append(map.get("SH_NO")!=null ? String.valueOf(map.get("SH_NO")) : "");
				strBuf.append(",");
				strBuf.append(map.get("TRACKING_NO")!=null ? "单号："+String.valueOf(map.get("TRACKING_NO")) : "");
				strBuf.append(",");
				strBuf.append(map.get("LOGISTICS_DO")!=null ? String.valueOf(map.get("LOGISTICS_DO")) : "");
				strBuf.append(",");
				strBuf.append(map.get("BAR_CODE")!=null ? String.valueOf(map.get("BAR_CODE")) : "");
				strBuf.append(",");
				
				strBuf.append(map.get("OK_REMARK")!=null ? String.valueOf("OK_REMARK") : "");
				strBuf.append(",");
				strBuf.append(map.get("SHIP_TYPE")!=null ? String.valueOf(map.get("SHIP_TYPE")) : "");
				strBuf.append(",");
				strBuf.append(map.get("WHETHER_STOCK")!=null ? String.valueOf(map.get("WHETHER_STOCK")) : "");
				
				dataList.add(strBuf.toString());
			}
			
			SimpleDateFormat sdfName = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String fileName = "pdSendInfoDetail_"+sdfName.format(new Date())+".csv";
			boolean isSuccess=CSVUtils.exportCsv(new File(xlsPath+fileName), dataList);
			if(isSuccess){
				String FILE_URL = ListUtil.getListValue("CN", "jpmproductsaleimage.url", "1")+"xls/";
				returnUrl = FILE_URL+fileName;
			}
			
			System.out.println("==========isSuccess:"+isSuccess);
			log.info("==========isSuccess:"+isSuccess);
			log.debug("==========isSuccess:"+isSuccess);
		}
		return returnUrl;
	}
}
