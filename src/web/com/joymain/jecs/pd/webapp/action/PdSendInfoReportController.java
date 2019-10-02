package com.joymain.jecs.pd.webapp.action;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.CsvTool;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdSendInfoReportController extends BaseController implements
		Controller {

	private static final String CHARSET_CODE = "BIG5";
	private static final String REP_STR = " ";
	private PdSendInfoManager pdSendInfoManager = null;
	private CsvTool csvTool = null;
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public void setCsvTool(CsvTool csvTool) {
		this.csvTool = csvTool;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String view = "pd/pdSendInfoReport";
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		String strAction = request.getParameter("strAction");
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String companyCode = request.getParameter("companyCode");
		if (sessionLogin.getIsCompany()) {
			companyCode = sessionLogin.getCompanyCode();

		}
		crm.setValue("companyCode", companyCode);
		// list = pdSendInfoManager.getShippingReportList(crm);
		String config = "";
		if ("pdSendInfoReport".equals(strAction)) {
			view = "pd/pdSendInfoReport";
		} else if ("showPdSendInfoReport".equals(strAction)) {
            log.info("在类PdSendInfoReportController的方法handleRequest中运行，发货报表执行报表开始运行");
			config = "/WEB-INF/xls/pdShipmentsReport.xls";
			if ("TW".equalsIgnoreCase(companyCode)) {
				config = "/WEB-INF/xls/pdShipReportTW.xls";
				crm.setValue("shipType", "0");
			}

            log.info("在类PdSendInfoReportController的方法handleRequest中运行，发货报表执行报表最大导出条数查询");
			//Modify By WuCF 20140514  查询记录条数，控制记录条数
			Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "export.listnum"));
			crm.setValue("listNum", listNum);
			//只导出正常发货的发货单数据------modify fu 2015-10-20
			crm.setValue("shipType","shipTypeShippingRepor");
			log.info("在类PdSendInfoReportController的方法handleRequest中运行，发货报表执行报表最大导出条数查询为："+listNum);
			
			list = pdSendInfoManager.getShippingReportListByCompany(crm);
			if(null!=list && list.size()>0){
			//判断结果集合是否超过限度
			if("1".equals(list.get(0).toString())){
				log.info("在类PdSendInfoReportController的方法handleRequest中运行，发货报表执行报表导出总条数超过总条数");
				String key = "导出数据数量超过最大限制数："+listNum+"，导出失败！请缩小查询条件范围！";
				Map<String,String> map = new HashMap<String,String>();
				map.put("siNo", key);
				list = new ArrayList();
				list.add(map);
			}
			}
			log.info("在类PdSendInfoReportController的方法handleRequest中运行，发货报表执行报表导出总条数为："+list.size());
			log.info("listsize->" + list.size());
			ExcelUtils.addValue("list", list);
			ExcelUtils
					.addValue("warehouseNo", crm.getString("warehouseNo", ""));
			ExcelUtils.addValue("startime", crm.getString("startTime", ""));
			ExcelUtils.addValue("endTime", crm.getString("endTime", ""));
			// view = "pd/pdSendGoogsPrint";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String filename = "pdShipments_" + companyCode + "_"
					+"_("
					+ crm.getString("startTime", "") + "-"
					+ crm.getString("endTime", "");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + ").xls\"");
			ExcelUtils.export(request.getSession().getServletContext(), config,
					response.getOutputStream());

			return null;
		} else if ("showPdSendInfoDetailReport".equals(strAction)) {
			config = "/WEB-INF/xls/pdShipmentsDetailReport.xls";
			if ("TW".equalsIgnoreCase(companyCode)) {
				config = "/WEB-INF/xls/pdShipReportDetailTW.xls";
//				crm.setValue("shipType", "0");
			}else if("US".equalsIgnoreCase(companyCode)){
				config = "/WEB-INF/xls/pdShipmentsDetailReport-us.xls";
			}
			
			//Modify By WuCF 20140514  查询记录条数，控制记录条数
			Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "export.listnum"));
			crm.setValue("listNum", listNum);
			//只导出正常发货的发货单数据------modify fu 2015-10-20
			crm.setValue("shipType","shipTypeShippingRepor");
			//add comment by lihao,发货单管理-发货报表只允许导出“已审核”-“正常发货”的单据。
			list = pdSendInfoManager.getShippingReportList(crm);
			if(null!=list && list.size()>0){
			//判断结果集合是否超过限度
			if("1".equals(list.get(0).toString())){
				String key = "导出数据数量超过最大限制数："+listNum+"，导出失败！请缩小查询条件范围！";
				Map<String,String> map = new HashMap<String,String>();
				map.put("SI_NO", key);
				list = new ArrayList();
				list.add(map);
			}
			}
			log.info("listsize->" + list.size());
			ExcelUtils.addValue("list", list);
			ExcelUtils
					.addValue("warehouseNo", crm.getString("warehouseNo", ""));
			ExcelUtils.addValue("startime", crm.getString("startTime", ""));
			ExcelUtils.addValue("endTime", crm.getString("endTime", ""));
			// view = "pd/pdSendGoogsPrint";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String filename = "pdShipmentsDetail_" + companyCode + "_"
					+ crm.getString("warehouseNo", "") + "_("
					+ crm.getString("startTime", "") + "-"
					+ crm.getString("endTime", "");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + ").xls\"");
			ExcelUtils.export(request.getSession().getServletContext(), config,
					response.getOutputStream());

			return null;
		} else if ("exportEDI".equals(strAction)) {
			if ("TW".equalsIgnoreCase(companyCode)) {
				// config = "/WEB-INF/xls/pdShipReportDetailTW.xls";
//				crm.setValue("shipType", "0");
			}
			list = pdSendInfoManager.getShippingReportList(crm);
			response.reset();
			response.setContentType("application/x-msdownload"); // download
			response.setHeader("Content-Disposition",
					"attachment;   filename=EDI.txt");
			OutputStream out = response.getOutputStream();
			try {
				log.info("exportEDI=" + getSendInfoBuffer(list).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("exportEDI,erro=" + e);
			}
			BufferedWriter bufWriter = new BufferedWriter(
					new OutputStreamWriter(out, CHARSET_CODE));
			// log.info("exportEDI2="+getSendInfoBuffer(list).toString());
			bufWriter.write(this.getSendInfoBuffer(list).toString());
			bufWriter.flush();
			bufWriter.close();
			out.close();
			return null;

		}else if("US3PL".equals(strAction)){
			crm.setValue("exportType", "US3PL");
			response.setContentType("application/x-msdownload");   //download
		      response.setHeader("Content-Disposition",   "attachment;   filename=" + crm.getString("startTime", "") + "-"
						+ crm.getString("endTime", "") +   "-shipment.csv"); 
			
			
			OutputStream out = response.getOutputStream();
			csvTool.excute(crm, out);
		}else if("mNewsPaper".equals(strAction)){
			config = "/WEB-INF/xls/pdMobileReport.xls";
			
//			list = pdSendInfoManager.getShippingReportListByCompany(crm);
			
			//Modify By WuCF 20140514  查询记录条数，控制记录条数
			Integer listNum = Integer.parseInt(ConfigUtil.getConfigValue(sessionLogin.getCompanyCode(), "export.listnum"));
			crm.setValue("listNum", listNum);
			list = pdSendInfoManager.getShippingReportListByCompany(crm);
			
			//判断结果集合是否超过限度
			if("1".equals(list.get(0).toString())){
				String key = "导出数据数量超过最大限制数："+listNum+"，导出失败！请缩小查询条件范围！";
				Map<String,String> map = new HashMap<String,String>();
				map.put("siNo", key);
				list = new ArrayList();
				list.add(map);
			}
			
			log.info("listsize->" + list.size());
			ExcelUtils.addValue("list", list);
			ExcelUtils
					.addValue("warehouseNo", crm.getString("warehouseNo", ""));
			ExcelUtils.addValue("startime", crm.getString("startTime", ""));
			ExcelUtils.addValue("endTime", crm.getString("endTime", ""));
			// view = "pd/pdSendGoogsPrint";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			String filename = "mobileNewsPaper_" + companyCode + "_"
					+ crm.getString("warehouseNo", "") + "_("
					+ crm.getString("startTime", "") + "-"
					+ crm.getString("endTime", "");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + ").xls\"");
			ExcelUtils.export(request.getSession().getServletContext(), config,
					response.getOutputStream());

			return null;
		}
		return new ModelAndView(view);
	}

	private StringBuffer getSendInfoBuffer(List list)
			throws UnsupportedEncodingException {
		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String ys1 = StringUtil.getFLString("", CHARSET_CODE, REP_STR, 10);
			String ys2 = StringUtil.getFLString(DateUtil.getDateTime(
					"yyyy/MM/dd", (Date) map.get("CREATE_TIME")), CHARSET_CODE,
					REP_STR, 10);

			String ys3 = StringUtil.getFLString(DateUtil.getDateTime(
					"yyyy/MM/dd", new Date()), CHARSET_CODE, REP_STR, 10);
			String ys4 = StringUtil.getFLString(
					(String) map.get("CUSTOM_CODE"), CHARSET_CODE, REP_STR, 10);
			String ys5 = StringUtil.getFLString((String) map.get("USER_NAME"),
					CHARSET_CODE, REP_STR, 20);
			String ys6 = StringUtil.getFLString((String) map
					.get("RECIPIENT_PHONE"), CHARSET_CODE, REP_STR, 20);
			String ys7 = StringUtil.getFLString((String) map.get("CITY_NAME")
					+ (String) map.get("RECIPIENT_ADDR"), CHARSET_CODE,
					REP_STR, 80);
			String ys8 = StringUtil.getFLString((String) map
					.get("RECIPIENT_NAME"), CHARSET_CODE, REP_STR, 20);
			String ys9 = StringUtil.getFLString((String) map
					.get("RECIPIENT_PHONE"), CHARSET_CODE, REP_STR, 10);
			String ys10 = StringUtil.getFLString((String) map.get("CITY_NAME")
					+ (String) map.get("RECIPIENT_ADDR"), CHARSET_CODE,
					REP_STR, 80);
			String ys11 = StringUtil.getFLString(
					(String) map.get("PRODUCT_NO"), CHARSET_CODE, REP_STR, 60);
			String ys12 = StringUtil.getFLString((String) map
					.get("PRODUCT_NAME"), CHARSET_CODE, REP_STR, 60);
			String ys13 = StringUtil.getFLString(((BigDecimal) map.get("QTY"))
					.toString(), CHARSET_CODE, REP_STR, 8);
			String ys14 = StringUtil.getFLString("", CHARSET_CODE, REP_STR, 30);
			String ys15 = StringUtil.getFLString("", CHARSET_CODE, REP_STR, 60);
			String ys16 = StringUtil.getFLString(
					((BigDecimal) map.get("PRICE")).toString(), CHARSET_CODE,
					REP_STR, 11);
			String ys17 = StringUtil.getFLString((((BigDecimal) map
					.get("PRICE")).multiply((BigDecimal) map.get("QTY")))
					.toString(), CHARSET_CODE, REP_STR, 11);
			String ys18_23 = StringUtil.getFLString("", CHARSET_CODE, REP_STR,
					123);
			String ys24 = "1";
			String ys25 = StringUtil.getFLString((String) map.get("ORDER_NO"),
					CHARSET_CODE, REP_STR, 20);
			String ys26_31 = StringUtil.getFLString("", CHARSET_CODE, REP_STR,
					166);
			String ys32 = StringUtil.getFLString("4007", CHARSET_CODE, REP_STR,
					10);
			byte[] return_line = { Character.LINE_SEPARATOR,
					Character.LETTER_NUMBER };

			buffer.append(ys1).append(ys2).append(ys3).append(ys4).append(ys5)
					.append(ys6).append(ys7).append(ys8).append(ys9).append(
							ys10).append(ys11).append(ys12).append(ys13)
					.append(ys14).append(ys15).append(ys16).append(ys17)
					.append(ys18_23).append(ys24).append(ys25).append(ys26_31)
					.append(ys32).append(new String(return_line));
		}
		return buffer;
	}

}
