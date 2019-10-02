package com.joymain.jecs.po.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;

/**
 * 各订单类型业绩柱形图统计报表
 * @author Administrator
 *
 */
public class JpoMemberOrderTypeCylindricalReportController extends BaseController
implements Controller{
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
			if(StringUtils.isNotEmpty(fyear))
			{
				   sql=" select * from view_jpomemberordertype where p_view_param.set_param('"+fyear+"')='"+fyear+"'";
			}else
			{
				throw new AppException("工作年份不允许为空！");
			}
			List poMemberOrders = this.jdbcTemplate.queryForList(sql);
			
			
		
			Map<String,Object> map = reBuildList(poMemberOrders);
			Map<Integer,Integer> monthMap=(Map<Integer,Integer>)map.get("monthMap");
			Map<String,BigDecimal> dataMap=(Map<String,BigDecimal>)map.get("dataMap");
			
			int[] intMonth=new int[monthMap.size()];
			Integer[] month=monthMap.keySet().toArray( new Integer[monthMap.size()]) ;
			for(int i=0;i<intMonth.length;i++){
				if(month[i]!=null){
					intMonth[i]=month[i].intValue();
				}
			}
			Arrays.sort( intMonth );

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
			excelUtil.addString(wsheet, 0, row, fyear+""+LocaleUtil.getLocalText("jpomemberordertype.cylindrical"));
					
			//标题
			excelUtil.addString(wsheet, col=0, ++row , LocaleUtil.getLocalText("bdBounsDeduct.fmonth"));
			for(int h=0;h<sortOrderTypehead.length;h++){
				excelUtil.addString(wsheet, ++col, row , sortOrderTypehead[h][1]) ;
			}
			//总金额
			excelUtil.addString(wsheet, ++col, row , "总金额");
			
			String strMonth=null;
			String strOrderType=null;
			BigDecimal amount=null;
			String dataKey=null;
			
			BigDecimal sumRowAmount=null;
			for(int i=0;i<intMonth.length;i++){
				sumRowAmount=new BigDecimal("0");
				
				strMonth=StringUtils.leftPad( intMonth[i]+"",2, "0");
				excelUtil.addString(wsheet, col=0 , ++row , fmonthNameMap.get(strMonth) );
				for(int h=0;h<sortOrderTypehead.length;h++){
					strOrderType=sortOrderTypehead[h][0];
					dataKey=strMonth+"#"+strOrderType;
					amount=dataMap.get( dataKey );
					if(amount==null){
						amount=new BigDecimal("0");
					}
					excelUtil.addString(wsheet, ++col , row , amount.toString() );
					
					sumRowAmount=sumRowAmount.add( amount );
				}
				excelUtil.addString(wsheet, ++col , row , sumRowAmount.toString() );
			}
			
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
			return null;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return new ModelAndView("po/jpoMemberOrderTypeCylindricalReport");
	}
	
	
    private static String[][] sortOrderTypehead=new String[][]{
    	{"1", "会员首购"},{"2", "会员升级"},{"3", "会员续约"},{"4", "会员重消"},
    	{"5", "辅销品"},{"6", "店铺首购"},{"9", "店铺重消"},{"12", "店铺升级"},
    	{"40", "退单"}
    };
/*    
	private static Map<String,String> orderTypeNameMap=new HashMap<String,String>();
	static{
		orderTypeNameMap.put("1", "会员首购");
		orderTypeNameMap.put("2", "会员升级");
		orderTypeNameMap.put("3", "会员续约");
		orderTypeNameMap.put("4", "会员重消");
		orderTypeNameMap.put("5", "辅销品");
		orderTypeNameMap.put("6", "店铺首购");
		orderTypeNameMap.put("9", "店铺重消");
		orderTypeNameMap.put("12", "店铺升级");
		orderTypeNameMap.put("40", "退单");
	}*/
	private static Map<String,String> fmonthNameMap=new HashMap<String,String>();
	static{
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
/*	public String orderTypeName(String orderType){
		return orderTypeNameMap.get( orderType );
	}*/
	
	/**
	 * 重构数据
	 * @param list
	 * @param area
	 * @return
	 */
	public Map<String,Object> reBuildList(List list){
		Map<String,Object> map=new HashMap<String,Object>();
		Map<Integer,Integer> monthMap=new HashMap<Integer,Integer>();
		Map<String,BigDecimal> dataMap=new HashMap<String,BigDecimal>();
		
		if(list!=null){
			
			String month=null;
			String orderType=null;
			BigDecimal bdAmount;
			Integer intMonth=null;
			//Number numAmount;
			
			String key=null;
			Map temp=null;
			for(Iterator<Map> it=list.iterator();it.hasNext();){
				temp=it.next();
				month=String.valueOf( temp.get("f_month") );
				orderType=String.valueOf( temp.get("order_type") );
				
				
				bdAmount=( BigDecimal )temp.get("amount") ;
				//System.out.print("amount="+ String.valueOf( bdAmount ) );
				if(bdAmount==null){
					bdAmount=new BigDecimal("0");
				}
				key=month+"#"+orderType;
				dataMap.put(key, bdAmount);
				
				intMonth=new Integer(month);
				monthMap.put( intMonth, intMonth );
				
				System.out.print(monthMap );
				//System.out.println("");
			}
			
		}
		map.put("dataMap", dataMap);
		map.put("monthMap", monthMap);
		return map;
	}

}
