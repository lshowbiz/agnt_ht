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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiJoinNumReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JmiJoinNumReportController.class);

	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private HttpServletRequest innerRequest;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public JmiJoinNumReportController() {
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

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiJoinNumReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			Map map=request.getParameterMap();


			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=jmiJoinNumReport.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);


			String formatedWeek = request.getParameter("formatedWeek");
			List list = this.jdbcTemplate.queryForList("select distinct b.w_month as w_month from jecs.jbd_period b where b.f_year="+formatedWeek+" order by b.w_month");
//			if(list.isEmpty()){
//				
//			}
			int column=0;
			eu.addString(wsheet, column++, 0, "财政月");

			for (int j = 0; j < list.size(); j++) {
				String w_month=((Map)list.get(j)).get("w_month").toString();
				eu.addString(wsheet, column++, 0, w_month+"月");
			}
			
			
			int row=1;
			eu.addString(wsheet, 0, row++, "总业绩");
			eu.addString(wsheet, 0, row++, "会员首单");
			eu.addString(wsheet, 0, row++, "会员总数");
			eu.addString(wsheet, 0, row++, "VIP");
			eu.addString(wsheet, 0, row++, "钻石级");
			eu.addString(wsheet, 0, row++, "白金级");
			eu.addString(wsheet, 0, row++, "金级");
			eu.addString(wsheet, 0, row++, "优惠顾客");
			

			String card_type1_pv="70";
			String card_type2_pv="350";
			String card_type3_pv="700";
			String card_type4_pv="1400";
			String card_type5_pv="70";
			String card_type6_pv_2012="2100";
			String card_type6_pv_2013="2750";
			
			
			column=1;
			row=1;
			
			for (int j = 0; j < list.size(); j++) {
				String w_month=((Map)list.get(j)).get("w_month").toString();
				//总金额
				String totalMoneySql1="select nvl(sum(c.price*c.qty),0) as amount from jecs.jpo_member_order a, jecs.jbd_period b,jecs.jpo_member_order_list c " +
						" where a.check_time >= b.start_time and a.check_time < b.end_time and b.f_year =  " + formatedWeek +
						" and b.f_month ="+w_month+"  and a.mo_id=c.mo_id and a.company_code='"+companyCode+"' ";
				
				String totalMoneySql2=" and a.order_type = '1'";
				List list1 = this.jdbcTemplate.queryForList(totalMoneySql1);
				String t_amount=((Map)list1.get(0)).get("amount").toString();//总业绩
				List list2 = this.jdbcTemplate.queryForList(totalMoneySql1+totalMoneySql2);
				String f_amount=((Map)list2.get(0)).get("amount").toString();//首单业绩
				
				String memberSql="";
				if(StringUtil.formatInt(formatedWeek)<201301){
					memberSql="select Count(Decode(card_type, 1, 1)) as card_type1, Count(Decode(card_type, 2, 1)) as card_type2," +
							"Count(Decode(card_type, 3, 1)) as card_type3,Count(Decode(card_type, 4, 1)) as card_type4," +
							"Count(Decode(card_type, 6, 1)) as card_type6 from (select Case When (a.Pv_Amt >= "+card_type1_pv+" and a.Pv_Amt < "+card_type2_pv+") Then 1 " +
							"When (a.Pv_Amt >= "+card_type2_pv+" and a.Pv_Amt < "+card_type3_pv+") Then 2 " +
									"When (a.Pv_Amt >= "+card_type3_pv+" and a.Pv_Amt < "+card_type4_pv+") Then 3 " +
							"When (a.Pv_Amt >= "+card_type4_pv+" and a.Pv_Amt < "+card_type6_pv_2012+") Then 4 When (a.Pv_Amt >= "+card_type6_pv_2012+") " +
									"Then 6 End as card_type";
				}else{
					memberSql="select Count(Decode(card_type, 5, 1)) as card_type1, Count(Decode(card_type, 2, 1)) as card_type2," +
							"Count(Decode(card_type, 3, 1)) as card_type3,Count(Decode(card_type, 4, 1)) as card_type4," +
							"Count(Decode(card_type, 6, 1)) as card_type6 from (select Case When (a.Pv_Amt >= "+card_type5_pv+" and a.Pv_Amt < "+card_type2_pv+") Then 5 " +
							"When (a.Pv_Amt >= "+card_type2_pv+" and a.Pv_Amt < "+card_type3_pv+") Then 2 " +
									"When (a.Pv_Amt >= "+card_type3_pv+" and a.Pv_Amt < "+card_type4_pv+") Then 3 " +
							"When (a.Pv_Amt >= "+card_type4_pv+" and a.Pv_Amt < "+card_type6_pv_2013+") Then 4 When (a.Pv_Amt >= "+card_type6_pv_2013+") " +
									"Then 6 End as card_type";
				}
				memberSql+=" from jecs.jpo_member_order a, jecs.jbd_period b where a.check_time >= b.start_time and a.check_time < b.end_time " +
						"and b.f_year = " + formatedWeek +" and b.f_month = "+w_month+" and a.order_type = '1') and a.company_code='"+companyCode+"'";
				
				

				List list3 = this.jdbcTemplate.queryForList(memberSql);
				
				
				Integer member_num1=StringUtil.formatInt(((Map)list3.get(0)).get("card_type1").toString());
				Integer member_num2=StringUtil.formatInt(((Map)list3.get(0)).get("card_type2").toString());
				Integer member_num3=StringUtil.formatInt(((Map)list3.get(0)).get("card_type3").toString());
				Integer member_num4=StringUtil.formatInt(((Map)list3.get(0)).get("card_type4").toString());
				Integer member_num6=StringUtil.formatInt(((Map)list3.get(0)).get("card_type6").toString());
				
				Integer total_member_num=member_num1+member_num2+member_num3+member_num4+member_num6;
				
				
				eu.addNumber(wsheet, column, row++, new Double(t_amount));
				eu.addNumber(wsheet, column, row++, new Double(f_amount));
				
				eu.addNumber(wsheet, column, row++, total_member_num);
				eu.addNumber(wsheet, column, row++, member_num6);
				eu.addNumber(wsheet, column, row++, member_num4);
				eu.addNumber(wsheet, column, row++, member_num3);
				eu.addNumber(wsheet, column, row++, member_num2);
				eu.addNumber(wsheet, column, row++, member_num1);
				
				
				row=1;
				column++;
				
			}
			
			
			

			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();
			
			
			
			
			return null;
		}
		return new ModelAndView(getSuccessView());
	}
	
	
	


}