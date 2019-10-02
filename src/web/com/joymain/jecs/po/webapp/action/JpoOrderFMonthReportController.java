package com.joymain.jecs.po.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 产品销售明细分析（按财月分）
 * @author Alvin
 *
 */
public class JpoOrderFMonthReportController extends BaseFormController {
	private AlCompanyManager alCompanyManager = null;
	private JdbcTemplate jdbcTemplate = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JpoOrderFMonthReportController() {
		setCommandName("JpoMemberOrder");
		setCommandClass(JpoMemberOrder.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
        RequestUtil.freshSession(request,"jpoOrderFMonthReport",10l);
        JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
		
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

		return jpoMemberOrder;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
		if("C".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"jpoOrderFMonthReport",10l)!=0){
    			return new ModelAndView("redirect:jpoOrderFMonthReport.html");
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"jpoOrderFMonthReport",1l)!=0){
    			return new ModelAndView("redirect:jpoOrderFMonthReport.html");
    		}
    	}
		
		String companyCode=SessionLogin.getLoginUser(request).getCompanyCode();
		if(!StringUtils.isEmpty(request.getParameter("companyCode"))){
			companyCode=request.getParameter("companyCode");
		}
		
		String sWeek = request.getParameter("sWeek");
		
		String eWeek = request.getParameter("eWeek");
		
		String sql = "select nvl(sum(jpol.price * qty),'0') price,nvl(sum(qty),'0') qty,";
		sql += "       jbp.f_year || lpad(jbp.f_month,2,0) fmonth,";
		sql += "       nvl(jp.statistics_category,' ') product_category,";
		sql += "       nvl(jps.product_name,' ') product_name";
		sql += "  from jpo_member_order      jpo,";
		sql += "       jpo_member_order_list jpol,";
		sql += "       jpm_product_sale_new      jps,";
		sql += "       jpm_product           jp,";
		sql += "       jbd_period            jbp";
		sql += " where jpo.mo_id = jpol.mo_id";
		sql += "   and jpol.product_id = jps.uni_no";
		sql += "   and jps.product_no = jp.product_no";
		sql += "   and jpo.check_date >= jbp.start_time";
		sql += "   and jpo.check_date < jbp.end_time";
		sql += "   and jpo.company_code = '"+companyCode+"'";
		sql += "   and jbp.f_year || lpad(jbp.f_month,2,0) = " + sWeek;
		sql += " group by jbp.f_year || lpad(jbp.f_month,2,0), jp.statistics_category, jps.product_name";
		sql += " order by jbp.f_year || lpad(jbp.f_month,2,0), jp.statistics_category, jps.product_name desc ";
		List orderSMoneyList = this.jdbcTemplate.queryForList(sql);

		sql = "select nvl(sum(jpol.price * qty),'0') price,nvl(sum(qty),'0') qty,";
		sql += "       jbp.f_year || lpad(jbp.f_month,2,0) fmonth,";
		sql += "       nvl(jp.statistics_category,' ') product_category,";
		sql += "       nvl(jps.product_name,' ') product_name";
		sql += "  from jpo_member_order      jpo,";
		sql += "       jpo_member_order_list jpol,";
		sql += "       jpm_product_sale_new      jps,";
		sql += "       jpm_product           jp,";
		sql += "       jbd_period            jbp";
		sql += " where jpo.mo_id = jpol.mo_id";
		sql += "   and jpol.product_id = jps.uni_no";
		sql += "   and jps.product_no = jp.product_no";
		sql += "   and jpo.check_date >= jbp.start_time";
		sql += "   and jpo.check_date < jbp.end_time";
		sql += "   and jpo.company_code = '"+companyCode+"'";
		sql += "   and jpo.pay_by_coin = 1";
		sql += "   and jbp.f_year || lpad(jbp.f_month,2,0) = " + sWeek;
		sql += " group by jbp.f_year || lpad(jbp.f_month,2,0), jp.statistics_category, jps.product_name";
		sql += " order by jbp.f_year || lpad(jbp.f_month,2,0), jp.statistics_category, jps.product_name desc ";
		List orderSCoinList = this.jdbcTemplate.queryForList(sql);

		sql = "select nvl(sum(jpol.price * qty),'0') price,nvl(sum(qty),'0') qty,";
		sql += "       jbp.f_year || lpad(jbp.f_month,2,0) fmonth,";
		sql += "       nvl(jp.statistics_category,' ') product_category,";
		sql += "       nvl(jps.product_name,' ') product_name";
		sql += "  from jpo_member_order      jpo,";
		sql += "       jpo_member_order_list jpol,";
		sql += "       jpm_product_sale_new      jps,";
		sql += "       jpm_product           jp,";
		sql += "       jbd_period            jbp";
		sql += " where jpo.mo_id = jpol.mo_id";
		sql += "   and jpol.product_id = jps.uni_no";
		sql += "   and jps.product_no = jp.product_no";
		sql += "   and jpo.check_date >= jbp.start_time";
		sql += "   and jpo.check_date < jbp.end_time";
		sql += "   and jpo.company_code = '"+companyCode+"'";
		sql += "   and jbp.f_year || lpad(jbp.f_month,2,0) = " + eWeek;
		sql += " group by jbp.f_year || lpad(jbp.f_month,2,0), jp.statistics_category, jps.product_name";
		sql += " order by jbp.f_year || lpad(jbp.f_month,2,0), jp.statistics_category, jps.product_name desc ";
		List orderEMoneyList = this.jdbcTemplate.queryForList(sql);

		sql = "select nvl(sum(jpol.price * qty),'0') price,nvl(sum(qty),'0') qty,";
		sql += "       jbp.f_year || lpad(jbp.f_month,2,0) fmonth,";
		sql += "       nvl(jp.statistics_category,' ') product_category,";
		sql += "       nvl(jps.product_name,' ') product_name";
		sql += "  from jpo_member_order      jpo,";
		sql += "       jpo_member_order_list jpol,";
		sql += "       jpm_product_sale_new      jps,";
		sql += "       jpm_product           jp,";
		sql += "       jbd_period            jbp";
		sql += " where jpo.mo_id = jpol.mo_id";
		sql += "   and jpol.product_id = jps.uni_no";
		sql += "   and jps.product_no = jp.product_no";
		sql += "   and jpo.check_date >= jbp.start_time";
		sql += "   and jpo.check_date < jbp.end_time";
		sql += "   and jpo.company_code = '"+companyCode+"'";
		sql += "   and jpo.pay_by_coin = 1";
		sql += "   and jbp.f_year || lpad(jbp.f_month,2,0) = " + eWeek;
		sql += " group by jbp.f_year || lpad(jbp.f_month,2,0), jp.statistics_category, jps.product_name";
		sql += " order by jbp.f_year || lpad(jbp.f_month,2,0), jp.statistics_category, jps.product_name desc ";
		List orderECoinList = this.jdbcTemplate.queryForList(sql);
		
		Map<String,Map> productCategory = new HashMap<String,Map>();
		
		for(int i = 0 ; i < orderSMoneyList.size() ; i++){
			Map map = (Map)orderSMoneyList.get(i);
			if(productCategory.get(map.get("product_category").toString())==null){
				Map<String,String> product = new HashMap<String,String>();
				product.put(map.get("product_name").toString(), map.get("product_name").toString());
				productCategory.put(map.get("product_category").toString(), product);
			}else{
				Map productCategoryMap = (Map)productCategory.get(map.get("product_category").toString());
				if(productCategoryMap.get(map.get("product_name").toString())==null){
					productCategoryMap.put(map.get("product_name").toString(), map.get("product_name").toString());
				}
			}
		}
		for(int i = 0 ; i < orderSCoinList.size() ; i++){
			Map map = (Map)orderSCoinList.get(i);
			if(productCategory.get(map.get("product_category").toString())==null){
				Map<String,String> product = new HashMap<String,String>();
				product.put(map.get("product_name").toString(), map.get("product_name").toString());
				productCategory.put(map.get("product_category").toString(), product);
			}else{
				Map productCategoryMap = (Map)productCategory.get(map.get("product_category").toString());
				if(productCategoryMap.get(map.get("product_name").toString())==null){
					productCategoryMap.put(map.get("product_name").toString(), map.get("product_name").toString());
				}
			}
		}
		for(int i = 0 ; i < orderEMoneyList.size() ; i++){
			Map map = (Map)orderEMoneyList.get(i);
			if(productCategory.get(map.get("product_category").toString())==null){
				Map<String,String> product = new HashMap<String,String>();
				product.put(map.get("product_name").toString(), map.get("product_name").toString());
				productCategory.put(map.get("product_category").toString(), product);
			}else{
				Map productCategoryMap = (Map)productCategory.get(map.get("product_category").toString());
				if(productCategoryMap.get(map.get("product_name").toString())==null){
					productCategoryMap.put(map.get("product_name").toString(), map.get("product_name").toString());
				}
			}
		}
		for(int i = 0 ; i < orderECoinList.size() ; i++){
			Map map = (Map)orderECoinList.get(i);
			if(productCategory.get(map.get("product_category").toString())==null){
				Map<String,String> product = new HashMap<String,String>();
				product.put(map.get("product_name").toString(), map.get("product_name").toString());
				productCategory.put(map.get("product_category").toString(), product);
			}else{
				Map productCategoryMap = (Map)productCategory.get(map.get("product_category").toString());
				if(productCategoryMap.get(map.get("product_name").toString())==null){
					productCategoryMap.put(map.get("product_name").toString(), map.get("product_name").toString());
				}
			}
		}
		
//    	生成excel文件
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=jpoOrderFMonthReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30" ); 
		OutputStream os = response.getOutputStream();
		ExcelUtil eu = new ExcelUtil();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		//在此创建的新excel文件创建一工作表
		WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
		int row = 0;
		int categoryRow = 0;
		for (Iterator<String> keys = productCategory.keySet().iterator(); keys.hasNext();) {
			   String key = (String) keys.next();
			   Map productCategoryMap = (Map)productCategory.get(key);
			   categoryRow += 1;
			   eu.addString(wsheet, 0, row+1, "系列" + categoryRow);
			   eu.addString(wsheet, 1, row+1, "商品名称");
			   eu.addString(wsheet, 2, row+0, sWeek+"财政月");
			   eu.addString(wsheet, 2, row+1, "数量");
			   eu.addString(wsheet, 3, row+1, "金额");
			   eu.addString(wsheet, 4, row+0, sWeek+"财政月积分换购");
			   eu.addString(wsheet, 4, row+1, "数量");
			   eu.addString(wsheet, 5, row+1, "金额");
			   eu.addString(wsheet, 6, row+0, eWeek+"财政月");
			   eu.addString(wsheet, 6, row+1, "数量");
			   eu.addString(wsheet, 7, row+1, "金额");
			   eu.addString(wsheet, 8, row+0, eWeek+"财政月积分换购");
			   eu.addString(wsheet, 8, row+1, "数量");
			   eu.addString(wsheet, 9, row+1, "金额");
			   eu.addString(wsheet, 10, row+1, sWeek+"财政月VS"+eWeek+"财政月");
			   row += 2;
				
			   eu.addString(wsheet, 0, row, LocaleUtil.getLocalText(ListUtil.getListValue("CN", "pmproduct.productcategory", key)));
			   for (Iterator<String> productKeys = productCategoryMap.keySet().iterator(); productKeys.hasNext();) {
				   String productName = (String) productKeys.next();
				   eu.addString(wsheet, 1, row, productName);
				   eu.addString(wsheet, 2, row, "0");
				   eu.addString(wsheet, 3, row, "0");
				   eu.addString(wsheet, 4, row, "0");
				   eu.addString(wsheet, 5, row, "0");
				   eu.addString(wsheet, 6, row, "0");
				   eu.addString(wsheet, 7, row, "0");
				   eu.addString(wsheet, 8, row, "0");
				   eu.addString(wsheet, 9, row, "0");
				   BigDecimal sMoney = new BigDecimal("0");
				   BigDecimal eMoney = new BigDecimal("0");
				   for(int i = 0 ; i < orderSMoneyList.size() ; i++){
					   Map map = (Map)orderSMoneyList.get(i);
					   if(productName.equals(map.get("product_name"))){
						   eu.addString(wsheet, 2, row, map.get("qty").toString());
						   eu.addString(wsheet, 3, row, map.get("price").toString());
						   sMoney = new BigDecimal(map.get("price").toString());
					   }
				   }
				   for(int i = 0 ; i < orderSCoinList.size() ; i++){
					   Map map = (Map)orderSCoinList.get(i);
					   if(productName.equals(map.get("product_name"))){
						   eu.addString(wsheet, 4, row, map.get("qty").toString());
						   eu.addString(wsheet, 5, row, map.get("price").toString());
					   }
				   }
				   for(int i = 0 ; i < orderEMoneyList.size() ; i++){
					   Map map = (Map)orderEMoneyList.get(i);
					   if(productName.equals(map.get("product_name"))){
						   eu.addString(wsheet, 6, row, map.get("qty").toString());
						   eu.addString(wsheet, 7, row, map.get("price").toString());
						   eMoney = new BigDecimal(map.get("price").toString());
					   }
				   }
				   for(int i = 0 ; i < orderECoinList.size() ; i++){
					   Map map = (Map)orderECoinList.get(i);
					   if(productName.equals(map.get("product_name"))){
						   eu.addString(wsheet, 8, row, map.get("qty").toString());
						   eu.addString(wsheet, 9, row, map.get("price").toString());
					   }
				   }
				   if(sMoney.compareTo(new BigDecimal("0"))==0 || eMoney.compareTo(new BigDecimal("0"))==0){
					   eu.addString(wsheet, 10, row, "");
				   }else{
					   eu.addString(wsheet, 10, row, (eMoney.divide(sMoney,4,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1"))).multiply(new BigDecimal("100")) + "%");
				   }
				   row += 1;
			   }
		} 
		
		eu.writeExcel(wwb);
		eu.closeWritableWorkbook(wwb);
		os.close();
        return null;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}