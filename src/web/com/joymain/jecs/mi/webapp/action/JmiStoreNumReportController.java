package com.joymain.jecs.mi.webapp.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.io.FileUtil;
import com.joymain.jecs.util.math.MathUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiStoreNumReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JmiStoreNumReportController.class);

	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private HttpServletRequest innerRequest;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public JmiStoreNumReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiStoreNumReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}


			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=jmiStoreNumReport.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);


			String formatedWeek = request.getParameter("formatedWeek");
			List list = this.jdbcTemplate.queryForList("select distinct b.w_month as w_month from jecs.jbd_period b where b.f_year="+formatedWeek+" order by b.w_month");
			
			Integer column=0;
			Integer row=1;
			eu.addString(wsheet, column++, 0, "周期");
			
			eu.mergeCells(wsheet, column++, 0, column++, 0);
			column-=2;
			eu.addString(wsheet, column++, 0, "店铺首购（一级）");
			eu.addString(wsheet, column++, 0, null);
			
			eu.mergeCells(wsheet, column++, 0, column++, 0);
			column-=2;
			eu.addString(wsheet, column++, 0, "店铺首购（二级）");
			eu.addString(wsheet, column++, 0, null);
			
			eu.mergeCells(wsheet, column++, 0, column++, 0);
			column-=2;
			eu.addString(wsheet, column++, 0, "店铺升级（二级）");
			eu.addString(wsheet, column++, 0, null);
			
			eu.addString(wsheet, column++, 0, "生活馆首购总额");
			eu.addString(wsheet, column++, 0, "总业绩");
			eu.addString(wsheet, column++, 0, "养生馆首单占总业绩比");
			
			column=0;
			

			LinkedHashMap<String,Double> totalMoneyMap=new LinkedHashMap<String,Double>();
			
			
			for (int i = 0; i < list.size(); i++) {
				String w_month=((Map)list.get(i)).get("w_month").toString();
				totalMoneyMap.put(w_month, new Double(0));
				
				
				eu.addString(wsheet, column, row++, w_month+"财月");
				
				
			}

			eu.addString(wsheet, column, row++, "总计");
			

			String sql1="select aa.f_month,nvl(bb.amount,0) as amount,nvl(bb.qty,0) as qty from ( select d.f_month " +
					"from jbd_period d where f_year = " + formatedWeek+" group by d.f_month ) aa " +
					"left join ( select b.f_month, sum(nvl(a.amount,0))+sum(nvl(a.jj_amount,0)) as amount,count(a.mo_id) qty " +
					"from jpo_member_order a, jbd_period b where a.check_time >= b.start_time and a.check_time < b.end_time " +
					"and a.company_code='"+companyCode+"' and b.f_year = " + formatedWeek;
			String orderTypeSql=" and a.order_type = '6'";
			String sql2=" group by b.f_month order by b.f_month  ) bb on aa.f_month=bb.f_month order by aa.f_month";
			
			column=1;
			row=1;
			List list1 = this.jdbcTemplate.queryForList(sql1+orderTypeSql+sql2);//店铺首购（一级）
			
			
			Integer totalQty=new Integer(0);
			Double totalAmount=new Double(0);
			
			
			this.setWsheet(list1, totalQty, totalAmount, column, row,1,totalMoneyMap);
			
			
			
			orderTypeSql=" and a.order_type = '11'";
			List list2 = this.jdbcTemplate.queryForList(sql1+orderTypeSql+sql2);//店铺首购（二级）
			
			
			column=3;
			this.setWsheet(list2, totalQty, totalAmount, column, row,3,totalMoneyMap);
			
			

			orderTypeSql=" and a.order_type = '12'";
			List list3 = this.jdbcTemplate.queryForList(sql1+orderTypeSql+sql2);//店铺首购（二级）
			
			
			column=5;
			this.setWsheet(list3, totalQty, totalAmount, column, row,5,totalMoneyMap);
			
			
			column=7;
			totalAmount=this.setWsheet(totalMoneyMap, totalAmount, column, row);//生活馆首购总额

			
			String sql="select aa.f_month,nvl(bb.amount,0) as amount from  (select distinct b.f_month from jbd_period b) aa left join (select d.f_month, nvl(d.amount, 0) - nvl(e.amount, 0) amount from (select sum(c.price * c.qty) amount, b.f_month " +
					"from jecs.jpo_member_order      a,jecs.jbd_period            b,jecs.jpo_member_order_list c " +
					"where a.check_time >= b.start_time and a.check_time < b.end_time and a.company_code = '"+companyCode+"' and a.mo_id = c.mo_id " +
					"and b.f_year = " + formatedWeek+" group by b.f_year, b.f_month) d left join (select sum(amount) amount, b.f_month " +
					"from jbd_period b, jpr_refund r where f_year = " + formatedWeek+" and company_code = '"+companyCode+"' and r.refund_time >= b.start_time " +
					"and r.refund_time < b.end_time group by b.f_year, b.f_month) e on d.f_month = e.f_month  ) bb on  aa.f_month=bb.f_month order by 1";
			

			List list4 = this.jdbcTemplate.queryForList(sql);//总业绩

			

			column=8;
			Double amount_all=new Double(0);
			
			for (int i = 0; i < list4.size(); i++) {
				Map map=((Map)list4.get(i));
				Double amount=StringUtil.formatDouble(map.get("amount").toString());
				String fmonth=map.get("f_month").toString();
				Double f_amount=(Double)totalMoneyMap.get(fmonth);

				
				eu.addNumber(wsheet, column++, row, amount);
				
				
				if(amount==0){
					eu.addString(wsheet, column, row, "0%");
				}else{
					Double percentage= MathUtil.mul(MathUtil.div(f_amount, amount, 2),new Double(100));
					eu.addString(wsheet, column, row, new BigDecimal(percentage)+"%");
				}
				
				
				
				amount_all+=amount;
				

				row++;
				column=8;
			}

			eu.addNumber(wsheet, column++, row, amount_all);
			
			if(amount_all==0){
				eu.addString(wsheet, column, row, "0%");
			}else{
				Double percentage= MathUtil.mul(MathUtil.div(totalAmount, amount_all, 2),new Double(100));
				eu.addString(wsheet, column, row, new BigDecimal(percentage)+"%");
			}
			
			
			
			
			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();
			
			
			
			
			return null;
		}
		return new ModelAndView(getSuccessView());
	}
	private Double setWsheet(Map map,Double totalAmount,Integer column,Integer row){

		Iterator memberIt = map.entrySet().iterator();

		totalAmount=new Double(0);
		
		while (memberIt.hasNext()) {
			Map.Entry entry = (Map.Entry) memberIt.next(); 
		    String key = entry.getKey().toString(); 
		    String val = entry.getValue().toString();

			eu.addNumber(wsheet, column, row, new Double(val));

			totalAmount+=new Double(val);
			row++;
		    
		}
		eu.addNumber(wsheet, column, row, totalAmount);
		return totalAmount;
	}
	private void setWsheet(List list,Integer totalQty,Double  totalAmount,Integer column,Integer row,Integer resetColumn,Map totalMoney){
		

		totalQty=new Integer(0);
		totalAmount=new Double(0);
		
		
		for (int i = 0; i < list.size(); i++) {
			Map map=((Map)list.get(i));
			Integer qty=StringUtil.formatInt(map.get("qty").toString());
			Double amount=StringUtil.formatDouble(map.get("amount").toString());
			
			String fmonth=map.get("f_month").toString();
			
			Double money=(Double)totalMoney.get(fmonth);
			money+=amount;
			totalMoney.put(fmonth, money);
			
			eu.addNumber(wsheet, column++, row, qty);
			eu.addNumber(wsheet, column, row, amount);
			
			totalQty+=qty;
			totalAmount+=amount;
			
			row++;
			column=resetColumn;
		}	

		eu.addNumber(wsheet, column++, row, totalQty);
		eu.addNumber(wsheet, column, row, totalAmount);
	}
	


}