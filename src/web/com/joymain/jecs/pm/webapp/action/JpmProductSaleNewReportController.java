package com.joymain.jecs.pm.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class JpmProductSaleNewReportController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JpmProductSaleNewReportController.class);

	private JdbcTemplate jdbcTemplateReport;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private Map shipStrategyMap = null;
	private Map logisticsStrategyMap = null;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplateReport) {
		this.jdbcTemplateReport = jdbcTemplateReport;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm=initCommonRecord(request);//存放request中的值例如：strAction
		
		//如果不是全球，则设置区域
		String companyCode = request.getParameter("companyCode");
		SysUser loginUser = SessionLogin.getLoginUser(request);
		if(StringUtils.isNotEmpty(companyCode)){
			crm.setValue("companyCode", companyCode);
		}else{
			if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
				crm.setValue("companyCode",loginUser.getCompanyCode());
			} 
		}
		companyCode = crm.getString("companyCode");
		
		//获得请求参数
		String productNo = request.getParameter("productNo");
    	String productName = request.getParameter("productName");
    	String status = request.getParameter("status");
    	String confirm = request.getParameter("confirm");
    	String cxFlag = request.getParameter("cxFlag");
    	String productCategory = request.getParameter("productCategory");
    	
    	crm.setValue("productNo", productNo);
    	crm.setValue("productName", productName);
    	crm.setValue("status", status);
    	crm.setValue("confirm", confirm);
    	crm.setValue("cxFlag", cxFlag);
    	crm.setValue("productCategory", productCategory);
	
    	OutputStream os = null;
    	WritableWorkbook wwb = null;
    	
		//生成excel文件
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=jpm_product_sale_new_report.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30" ); 
		//获得输出流
		os = response.getOutputStream();
		eu = new ExcelUtil();
		wwb = Workbook.createWorkbook(os);
		
		//创建一栏
		wsheet = wwb.createSheet("Sheet1", 0);
		
		//加入期别时间段显示
		eu.addString(wsheet, 0, 0, 	"商品信息报表");
		//标题
		int i=0;

		eu.addString(wsheet, i++, 1, "EC编码");
		eu.addString(wsheet, i++, 1, "NC编码");
		eu.addString(wsheet, i++, 1, "商品名称");
		eu.addString(wsheet, i++, 1, "起始销售日期");
		eu.addString(wsheet, i++, 1, "截止销售日期");
		eu.addString(wsheet, i++, 1, "发货策略");
		eu.addString(wsheet, i++, 1, "物流策略");
		eu.addString(wsheet, i++, 1, "重量");
		eu.addString(wsheet, i++, 1, "体积");
		eu.addString(wsheet, i++, 1, "备注");
		
		//设置列宽
		eu.setColumnWidth(wsheet, 0, 20);
		eu.setColumnWidth(wsheet, 1, 20);
		eu.setColumnWidth(wsheet, 2, 60);
		eu.setColumnWidth(wsheet, 3, 20);
		eu.setColumnWidth(wsheet, 4, 20);
		eu.setColumnWidth(wsheet, 5, 15);
		eu.setColumnWidth(wsheet, 6, 15);
		eu.setColumnWidth(wsheet, 7, 10);
		eu.setColumnWidth(wsheet, 8, 10);
		eu.setColumnWidth(wsheet, 9, 50);
		
		String sql = "SELECT jpsn.product_no AS product_no,jp.erp_product_no AS erp_product_no, "
					+" jpsn.product_name AS product_name,jpsn.start_on_sale AS start_on_sale, "
					+" jpsn.end_on_sale AS end_on_sale,jpsn.SHIP_STRATEGY AS ship_strategy, "
					+" jpsn.logistics_strategy AS logistics_strategy,jpsn.weight AS weight, "
					+" jpsn.volume AS volume,jpsn.remark AS remark "
					+" FROM jpm_product_sale_new jpsn,jpm_product jp "
					+" WHERE jpsn.product_no = jp.product_no "
					+" and jpsn.COMPANY_CODE = '" + companyCode + "' ";
		if(!StringUtil.blankOrNull(productNo)){
			productNo = productNo.trim();
			sql += " and jpsn.product_no = '" + productNo + "' ";
			
		}	
		if(!StringUtil.blankOrNull(productName)){
			productName = productName.trim();
			sql += " and jpsn.product_name like '%" + productName + "%' ";
		}
		if(!StringUtil.blankOrNull(status)){
			sql += " and jpsn.status in('"+status.replace(",", "','")+"') ";
		}
		if(!StringUtil.blankOrNull(productCategory)){
			sql += " and jp.PRODUCT_CATEGORY = '" + productCategory + "'";
		}
		
		
		System.out.println("sql==="+sql);
		
		
		//输出list翻译的值
		shipStrategyMap = ListUtil.getListOptions(companyCode, "ship.strategy");
		logisticsStrategyMap = ListUtil.getListOptions(companyCode, "ship.logisticsstrategy");
		
		this.jdbcTemplateReport.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException {
				try {
					int kk = 2;
					while (rs.next()) {
						String product_no = rs.getString("product_no");
						String erp_product_no = rs.getString("erp_product_no");
						String product_name = rs.getString("product_name");
						Date start_on_sale = rs.getDate("start_on_sale");
						Date end_on_sale = rs.getDate("end_on_sale");

						String ship_strategy = rs.getString("ship_strategy");
						String logistics_strategy = rs.getString("logistics_strategy");
						String weight=rs.getString("weight");
						String volume = rs.getString("volume");
						String remark=rs.getString("remark");
						
						
						int index=0;

						eu.addString(wsheet, index++, kk, product_no);
						eu.addString(wsheet, index++, kk, erp_product_no);
						eu.addString(wsheet, index++, kk, product_name);
						
						eu.addString(wsheet, index++, kk, formatDate(start_on_sale));
						eu.addString(wsheet, index++, kk, formatDate(end_on_sale));
						eu.addString(wsheet, index++, kk, shipStrategyMap.get(ship_strategy)==null ? "":LocaleUtil.getLocalText(shipStrategyMap.get(ship_strategy).toString()));
						eu.addString(wsheet, index++, kk, logisticsStrategyMap.get(logistics_strategy)==null ?  "":LocaleUtil.getLocalText(logisticsStrategyMap.get(logistics_strategy).toString()));
						eu.addString(wsheet, index++, kk, weight);
						eu.addString(wsheet, index++, kk, volume);
						eu.addString(wsheet, index++, kk, remark);
						
						kk++;
					} 
					
				} catch (Exception e) {
					log.info("商品信息统计表");
					log.info(e);
					e.printStackTrace();
				}finally {  
					JdbcUtils.closeResultSet(rs);
				}
				return null;
			}
		});
		eu.writeExcel(wwb);
		eu.closeWritableWorkbook(wwb);
		os.close();

		return null;
		
	}
	
	public static String formatDate(Date date){
		if(date == null){
			return "";
		}
		return sdf.format(date);
	}
	
	
}