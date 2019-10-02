package com.joymain.jecs.po.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
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

//12财年销售业绩统计报表
public class JpoMemberOrderShopSellReportController  extends BaseController
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
			String sql2="";
			if(StringUtils.isNotEmpty(fyear))
			{
				   sql=" select * from view_yearsell0 where p_view_param.set_param('"+fyear+"')='"+fyear+"'";
				   sql2=" select * from view_yearsell1 where p_view_param.set_param('"+fyear+"')='"+fyear+"'";
			}else
			{
				throw new AppException("工作年份不允许为空！");
			}
			List poMemberOrderTotal = this.jdbcTemplate.queryForList(sql);
			List poMemberOrderCoin = this.jdbcTemplate.queryForList(sql2);
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
		
			excelUtil.addString(wsheet, 0, row, fyear+""+LocaleUtil.getLocalText("jpomemberordertype.shopSell"));
					
			//左边列的标题
			excelUtil.addString(wsheet, col=0, ++row , LocaleUtil.getLocalText("bdBounsDeduct.fmonth"));//结算月
			for(int h=0;h<typeLcell.length;h++){
				excelUtil.addString(wsheet, col, ++row , typeLcell[h]) ;
			}
		//上边列跟里面的数据
		    Map temp=null;
		    Map tempCoin=null;
		    String coinFmont=null;
		    String fMonth=null;
		    BigDecimal totalamount=new BigDecimal(0);//订单总金额
		    BigDecimal discountAmount=new BigDecimal(0);//积分使用总金额
		    BigDecimal amount=new BigDecimal(0);//积分换购产生业绩
		    BigDecimal coinProportion=new BigDecimal(0);//积分占比=积分使用总金额/订单总金额
		    BigDecimal  coinTotal=new BigDecimal(0);//积分合计=积分换购产生业绩+积分使用总金额；
		    BigDecimal coinActualResults=new BigDecimal(0);//积分换购实际业绩=订单总金额-积分使用总金额
		   
			for(int i=0;i<poMemberOrderTotal.size();i++)		
			{
				temp=(Map)poMemberOrderTotal.get(i);
				fMonth=String.valueOf(temp.get("f_month"));
				totalamount=new BigDecimal(String.valueOf(temp.get("totalamount")));//订单总金额
				excelUtil.addString(wsheet, ++col, row =1, fMonth) ;
				excelUtil.addString(wsheet, col, ++row , totalamount.toString());
				for(int j=0;j<poMemberOrderCoin.size();j++)
				{

					tempCoin=(Map)poMemberOrderCoin.get(j);
					coinFmont=String.valueOf(tempCoin.get("f_month"));//获取月份
				
					if(fMonth.equals(coinFmont))
					{
						discountAmount=new BigDecimal(String.valueOf(tempCoin.get("discountAmount")));//积分使用总金额
						amount=new BigDecimal(String.valueOf(tempCoin.get("amount")));//积分换购产生业绩
						coinProportion=discountAmount.divide(totalamount,2,BigDecimal.ROUND_HALF_UP);//积分占比
						coinTotal=amount.add(discountAmount);//积分合计
						coinActualResults=totalamount.subtract(discountAmount);//积分换购实际业绩
						excelUtil.addString(wsheet, col, ++row , coinActualResults.toString());
						excelUtil.addString(wsheet, col, ++row , coinProportion.toString());
						excelUtil.addString(wsheet, col, ++row , amount.toString());
						excelUtil.addString(wsheet, col, ++row , discountAmount.toString());
						excelUtil.addString(wsheet, col, ++row , coinTotal.toString());
					}
					
				}
			
				
			}			
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
			return null;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return new ModelAndView("po/jpoMemberOrderShopSellReport");
	}
	
	
    private static String[] typeLcell=new String[]
                                                        
   {"达成业绩", "积分换购实际业绩", "积分换购业绩占比", "积分换购产生业绩", "积分", "合计"};
   

	
	
	

}
