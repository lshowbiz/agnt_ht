package com.joymain.jecs.po.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
 * 会员重消统计报表
 * @author Administrator
 *
 */
public class JpoMemberOrderRepeatReportController extends BaseController
implements Controller{

	private static String[][] tableColHead=new String[][]{
    	{"1", "会员重销"},{"2", "总业绩"},{"3", "占比"},{"4", "会员重销订单数（笔）"},
    	{"5", "重销会员数（人"},{"6", "大于等于42PV订单数（笔）"},{"7", "小于42PV大于10PV订单数（笔）"}
    };
	private final Map<String,String> fmonthNameMap;
	public JpoMemberOrderRepeatReportController(){
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
	}
	
	
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
			String fyear = request.getParameter("fyear");
			String sql="";
			if(StringUtils.isNotEmpty(fyear)){
				   sql="select * from view_reorder where p_view_param.set_param('"+fyear+"')='"+fyear+"'";
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
			excelUtil.mergeCells(wsheet, 0, row, poMemberOrders.size()+1, row);
			excelUtil.addString(wsheet, 0, row, fyear+""+LocaleUtil.getLocalText("jpomemberordertype.reorder"));
				
			//第一列
			//标题
			int tempRow=row;
			excelUtil.addString(wsheet, col=0, ++row , LocaleUtil.getLocalText("bdBounsDeduct.fmonth"));
			for(int i=0;i<tableColHead.length;i++){
				excelUtil.addString(wsheet, col, ++row , tableColHead[i][1]) ;
			}////第一列//end
			
			//总金额
		//	excelUtil.addString(wsheet, ++col, row , "总金额");
			
			String strMonth=null;

			
			BigDecimal amount=null;
			BigDecimal  proportion=null; 
			BigDecimal  totalAmount=null;
		
			Map temp=null;
			if(poMemberOrders!=null){
				
				for(int i=0;i<poMemberOrders.size();i++){
					
					temp=(Map)poMemberOrders.get(i);
					strMonth=temp.get("f_month").toString() ;
					strMonth=StringUtils.leftPad( strMonth ,2, "0");
					
					amount=new BigDecimal(temp.get("amount").toString());//会员重消总收入
					totalAmount=new BigDecimal(temp.get("totalamount").toString());//总业绩
					proportion=amount.multiply(totalAmount);// 占比
				
				   
					row=tempRow;
					excelUtil.addString(wsheet, ++col , ++row , fmonthNameMap.get( strMonth ) );
					excelUtil.addString(wsheet, col , ++row , temp.get("amount").toString() );
					excelUtil.addString(wsheet, col , ++row , totalAmount.toString() );
					excelUtil.addString(wsheet, col , ++row , proportion.toString() );
					
					excelUtil.addString(wsheet, col, ++row , temp.get("reordercount").toString() );//会员重销订单数（笔）
					excelUtil.addString(wsheet, col , ++row , temp.get("peoplecount").toString().toString());//重销会员数（人）
					excelUtil.addString(wsheet, col , ++row , temp.get("ordercountpvH").toString().toString());//大于42pv订单数量
					excelUtil.addString(wsheet, col , ++row , temp.get("ordercountpvL").toString().toString());//小于42PV大于10PV订单数（笔）
				}
				
			}
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
			return null;
			
		}
		return new ModelAndView("po/jpoMemberOrderRepeatReport");
	}
	
	
    

	

}
