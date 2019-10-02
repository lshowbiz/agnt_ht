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

public class JmiStoreRepeatOrderReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JmiStoreRepeatOrderReportController.class);

	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private HttpServletRequest innerRequest;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public JmiStoreRepeatOrderReportController() {
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

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiStoreRepeatOrderReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			Map map=request.getParameterMap();


			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=jmiStoreRepeatOrderReport.xls");
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
			int row=2;


			eu.mergeCells(wsheet, 1, 0, 5, 0);
			eu.addString(wsheet, 1, 0, "一级馆");
			eu.addString(wsheet, 2, 0, null);
			eu.addString(wsheet, 3, 0, null);
			eu.addString(wsheet, 4, 0, null);
			eu.addString(wsheet, 5, 0, null);
			

			eu.mergeCells(wsheet, 6, 0, 10, 0);
			eu.addString(wsheet, 6, 0, "二级馆");
			eu.addString(wsheet, 7, 0, null);
			eu.addString(wsheet, 8, 0, null);
			eu.addString(wsheet, 9, 0, null);
			eu.addString(wsheet, 10, 0, null);
			
			
			eu.addString(wsheet, column++, 1, "结算月 ");
			eu.addString(wsheet, column++, 1, "一级生活馆数");
			eu.addString(wsheet, column++, 1, "重销家数（一级）");
			eu.addString(wsheet, column++, 1, "重销率");
			eu.addString(wsheet, column++, 1, "总金额");
			eu.addString(wsheet, column++, 1, "总平均订单");
			eu.addString(wsheet, column++, 1, "二级生活馆数");
			eu.addString(wsheet, column++, 1, "重销家数（二级）");
			eu.addString(wsheet, column++, 1, "重销率");
			eu.addString(wsheet, column++, 1, "总金额");
			eu.addString(wsheet, column++, 1, "总平均订单");
			

			column=0;

			
			for (int i = 0; i < list.size(); i++) {
				String w_month=((Map)list.get(i)).get("w_month").toString();
				eu.addString(wsheet, column, row++, w_month+"月");
			}
			
			
			String sql="select aa.f_month,nvl(bb.qty,0) as qty,nvl(bb.amount,0) as amount from (select d.f_month from jbd_period d " +
					"where f_year = "+formatedWeek+" group by d.f_month) aa " +
							"left join (select b.f_month,count(distinct a.user_code) as qty,sum(a.amount) as amount from jpo_member_order a, jbd_period b " +
							"where a.check_time >= b.start_time and a.check_time < b.end_time and a.company_code = '"+companyCode+"' and b.f_year = "+formatedWeek;
			String sqlWhere=" and a.order_type = '9'";
									
          String sql1= " group by b.f_month order by b.f_month) bb on aa.f_month = bb.f_month order by aa.f_month";


			column=2;
			row=2;
			List list1 = this.jdbcTemplate.queryForList(sql+sqlWhere+sql1);//店铺首购（一级）
			
			this.setWsheet(list1, column, row, 2);
			
		
			String sqlWhere1=" and a.order_type = '11'";
			column=7;
			row=2;
			List list2 = this.jdbcTemplate.queryForList(sql+sqlWhere1+sql1);//店铺首购（一级）
			
			this.setWsheet(list2, column, row, 7);
			
			
			
			

			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();
			
			
			
			
			return null;
		}
		return new ModelAndView(getSuccessView());
	}
	
	private void setWsheet(List list,Integer column,Integer row,Integer resetColumn) throws Exception{
		
		
		
		for (int i = 0; i < list.size(); i++) {
			Map map=((Map)list.get(i));
			Integer qty=StringUtil.formatInt(map.get("qty").toString());
			Double amount=StringUtil.formatDouble(map.get("amount").toString());
			
			String fmonth=map.get("f_month").toString();
			
			
			eu.addNumber(wsheet, column++, row, qty);
			eu.addNumber(wsheet, ++column, row, amount);
			
			++column;
			if(qty==0){
				eu.addNumber(wsheet, column, row, 0);
			}else{
				Double percentage= MathUtil.div( amount, new Double(qty),0);
				eu.addNumber(wsheet, column, row, percentage);
			}
			
			
			
			
			row++;
			column=resetColumn;
		}	
	}
	


}