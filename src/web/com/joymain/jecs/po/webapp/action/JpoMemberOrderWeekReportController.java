package com.joymain.jecs.po.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
/**
 * 周业绩销售情况
 * @author Administrator
 *
 */         
public class JpoMemberOrderWeekReportController extends BaseController
implements Controller{
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
	private final Map<String,String> fmonthNameMap;
	private final Map<String,String> weekNameMap;
	public JpoMemberOrderWeekReportController(){
		fmonthNameMap=new HashMap<String,String>();
		fmonthNameMap.put("01", "一月");
		fmonthNameMap.put("02", "二月");
		fmonthNameMap.put("03", "三月");
		fmonthNameMap.put("04", "四月");
		fmonthNameMap.put("05", "五月");
		fmonthNameMap.put("06", "六月");
		fmonthNameMap.put("07", "七月");
		fmonthNameMap.put("08", "八月");
		fmonthNameMap.put("09", "九月");
		fmonthNameMap.put("10", "十月");
		fmonthNameMap.put("11", "十一月");
		fmonthNameMap.put("12", "十二月");
		fmonthNameMap.put("13", "十三月");
		
		weekNameMap=new HashMap<String, String>();
		weekNameMap.put("1", "第一周");
		weekNameMap.put("2", "第二周");
		weekNameMap.put("3", "第三周");
		weekNameMap.put("4", "第四周");
		weekNameMap.put("5", "第五周");
		weekNameMap.put("6", "第六周");
		weekNameMap.put("7", "第七周");
		weekNameMap.put("8", "第八周");
	}
	private String[] sortOrderTypehead=new String[]{
	    	"日期","月份","周期", "每周销售额","扣除积分实际业绩"
	};
	
	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
	
		String search = request.getParameter("search");
		if( StringUtils.isNotEmpty(search)){
			try{
			String fyear = request.getParameter("fyear");
			String sql="";
			if(StringUtils.isNotEmpty(fyear)){
				   sql=" select * from view_jpomemberweek where p_view_param.set_param('"+fyear+"')='"+fyear+"'";
			}else{
				throw new AppException("工作年份不允许为空！");
			}
			List poMemberOrders = this.jdbcTemplate.queryForList(sql);

			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions)to MemberOrderProducts_" + new Date() + ".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil excelUtil = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
		
			int col=0;
			int row=0;
			excelUtil.mergeCells(wsheet, 0, row, sortOrderTypehead.length+2, row);
			excelUtil.addString(wsheet, 0, row, fyear+""+LocaleUtil.getLocalText("jpomemberordertype.week"));
					
			//标题
			//excelUtil.addString(wsheet, col=0, ++row , LocaleUtil.getLocalText("bdBounsDeduct.fmonth"));
			row++;
			for(int h=0;h<sortOrderTypehead.length;h++){
				excelUtil.addString(wsheet, col++, row , sortOrderTypehead[h]) ;
			}
			//总金额
			//excelUtil.addString(wsheet, ++col, row , "总金额");
			
			
			Map temp=null;
			String m=null;
			int    r=-1;
			String strMonth=null;
			Date ed=null;
			Calendar calendar=Calendar.getInstance();
			for(int i=0;i<poMemberOrders.size();i++){
				temp=(Map)poMemberOrders.get(i);
				row++;
				strMonth=temp.get("f_month")+"";
				
				//结束时间-1//start
				ed=(Date)temp.get("end_time");
				calendar.setTime(ed);
				calendar.add( Calendar.DATE, -1);
				//结束时间-1//end
				
				excelUtil.addString(wsheet, col=0 , row , sdf.format(  (Date)temp.get("start_time") )+"-"+ sdf.format( calendar.getTime()  ) );
				excelUtil.addString(wsheet, ++col , row ,  fmonthNameMap.get( StringUtils.leftPad( strMonth,2, "0")  ));
				excelUtil.addString(wsheet, ++col , row , weekNameMap.get( temp.get("a_week")+"")  );
				excelUtil.addString(wsheet, ++col , row , temp.get("amount").toString() );
				
				if(m==null){
					m=strMonth;
					r=row;
				}
				if(m!=null && !m.equals( strMonth )){
					excelUtil.mergeCells(wsheet, 1, r, 1, row-1);
					r=row;
					m=strMonth;
				}
			}
			if( m.equals( strMonth ) ){
				excelUtil.mergeCells(wsheet, 1, r, 1, row-1);
				m=null;
			}
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
			return null;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return new ModelAndView("po/jpoMemberOrderWeekReport");
	}
}
