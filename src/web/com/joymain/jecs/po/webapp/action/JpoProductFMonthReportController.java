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
 * 产品销售系列（按财月分）
 * @author Alvin
 *
 */
public class JpoProductFMonthReportController extends BaseFormController {
	private AlCompanyManager alCompanyManager = null;
	private JdbcTemplate jdbcTemplate = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JpoProductFMonthReportController() {
		setCommandName("JpoMemberOrder");
		setCommandClass(JpoMemberOrder.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
        RequestUtil.freshSession(request,"jpoProductFMonthReport",10l);
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
    		if(RequestUtil.saveOperationSession(request,"jpoProductFMonthReport",10l)!=0){
    			return new ModelAndView("redirect:jpoProductFMonthReport.html");
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"jpoProductFMonthReport",1l)!=0){
    			return new ModelAndView("redirect:jpoProductFMonthReport.html");
    		}
    	}
		
		String companyCode=SessionLogin.getLoginUser(request).getCompanyCode();
		if(!StringUtils.isEmpty(request.getParameter("companyCode"))){
			companyCode=request.getParameter("companyCode");
		}
		
		String sWeek = request.getParameter("sWeek");
		
		String eWeek = request.getParameter("eWeek");
		
		String sql = "select nvl(sum(jpol.price * qty),'0') price,";
		sql += "       jbp.f_year || lpad(jbp.f_month,2,0) fmonth,";
		sql += "       nvl(jp.product_category,' ') product_category";
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
		sql += "   and jbp.f_year || lpad(jbp.f_month,2,0) >= " + sWeek;
		sql += "   and jbp.f_year || lpad(jbp.f_month,2,0) <= " + eWeek;
		sql += " group by jbp.f_year || lpad(jbp.f_month,2,0), jp.product_category";
		sql += " order by jbp.f_year || lpad(jbp.f_month,2,0), jp.product_category desc ";
		List orderMoneyList = this.jdbcTemplate.queryForList(sql);
		
		sql = "select nvl(sum(jpol.price * qty),'0') price,";
		sql += "       jbp.f_year || lpad(jbp.f_month,2,0) fmonth,";
		sql += "       nvl(jp.product_category,' ') product_category";
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
		sql += "   and jbp.f_year || lpad(jbp.f_month,2,0) >= " + sWeek;
		sql += "   and jbp.f_year || lpad(jbp.f_month,2,0) <= " + eWeek;
		sql += " group by jbp.f_year || lpad(jbp.f_month,2,0), jp.product_category";
		sql += " order by jbp.f_year || lpad(jbp.f_month,2,0), jp.product_category desc ";
		List orderCoinList = this.jdbcTemplate.queryForList(sql);
		
		Map<String,Integer> productCategory = new HashMap<String,Integer>();
		
		Map<String,BigDecimal> productCategoryMoney = new HashMap<String,BigDecimal>();
		
		for(int i = 0 ; i < orderMoneyList.size() ; i++){
			Map map = (Map)orderMoneyList.get(i);
			productCategory.put(map.get("product_category").toString(), 0);
		}
		for(int i = 0 ; i < orderCoinList.size() ; i++){
			Map map = (Map)orderCoinList.get(i);
			productCategory.put(map.get("product_category").toString(), 0);
		}
		
//    	生成excel文件
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=jpoProductFMonthReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30" ); 
		OutputStream os = response.getOutputStream();
		ExcelUtil eu = new ExcelUtil();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		//在此创建的新excel文件创建一工作表
		WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
		int row = 0;
		for (Iterator<String> keys = productCategory.keySet().iterator(); keys.hasNext();) {
			String key = (String) keys.next();
			row++;
			eu.addString(wsheet, 0, row, key);
			productCategory.put(key, row);
		}

		String fmonth = "";
		int column = 0;
		BigDecimal allMoney = new BigDecimal("0");
		for(int i = 0 ; i < orderMoneyList.size() ; i++){
			Map map = (Map)orderMoneyList.get(i);
			if(fmonth.equals(map.get("fmonth").toString())){
				row = productCategory.get(map.get("product_category").toString());
				
				eu.addString(wsheet, column, row, map.get("price").toString());
				BigDecimal price = new BigDecimal(map.get("price").toString());
				BigDecimal product_category_price = productCategoryMoney.get(fmonth);
				if(product_category_price.compareTo(new BigDecimal("0"))!=0){
					BigDecimal priceTmp = price.multiply(new BigDecimal("100"));
					priceTmp = priceTmp.divide(product_category_price,2,BigDecimal.ROUND_HALF_UP);
					eu.addString(wsheet, column + 2, row, priceTmp.toString()+"%");
				}
			}else{
				fmonth = map.get("fmonth").toString();
				if(column ==0){
					column +=1;
				}else{
					column +=3;
				}
				eu.addString(wsheet, column, 0, map.get("fmonth").toString()+"月");
				row = productCategory.get(map.get("product_category").toString());
				eu.addString(wsheet, column, row, map.get("price").toString());
				eu.addString(wsheet, column+1, 0, map.get("fmonth").toString()+"月积分换购");
				for(int m = 0 ; m < orderCoinList.size() ; m++){
					Map mapCoin = (Map)orderCoinList.get(m);
					if(fmonth.equals(mapCoin.get("fmonth").toString())){
						row = productCategory.get(mapCoin.get("product_category").toString());
						eu.addString(wsheet, column + 1, row, map.get("price").toString());
					}
				}
				eu.addString(wsheet, column + 2, 0, map.get("fmonth").toString()+"月占比");
				eu.addString(wsheet, column, row, map.get("price").toString());

				BigDecimal allCMoney = new BigDecimal("0");
				for(int m = 0 ; m < orderMoneyList.size() ; m++){
					Map mapMoney = (Map)orderMoneyList.get(m);
					if(fmonth.equals(mapMoney.get("fmonth").toString())){
						allCMoney = allCMoney.add(new BigDecimal(mapMoney.get("price").toString()));
					}
					
				}
				productCategoryMoney.put(fmonth, allCMoney);
				
				if(allMoney.compareTo(new BigDecimal("0"))!=0){
					BigDecimal price = new BigDecimal(map.get("price").toString());
					BigDecimal product_category_price = productCategoryMoney.get(fmonth);
					BigDecimal priceTmp = price.multiply(new BigDecimal("100"));
					priceTmp = priceTmp.divide(product_category_price,2,BigDecimal.ROUND_HALF_UP);
					eu.addString(wsheet, column + 2, row, priceTmp.toString()+"%");
				}
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