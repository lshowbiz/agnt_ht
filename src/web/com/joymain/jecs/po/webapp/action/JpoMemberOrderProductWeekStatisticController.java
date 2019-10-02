package com.joymain.jecs.po.webapp.action;

import java.io.OutputStream;
import java.util.ArrayList;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 订单统计(商品按期别)
 * 
 * @author Alvin
 * 
 */
public class JpoMemberOrderProductWeekStatisticController extends BaseController
		implements Controller {
	private final Log log = LogFactory
			.getLog(JpoMemberOrderProductWeekStatisticController.class);

	private JdbcTemplate jdbcTemplate = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		 RequestUtil.freshSession(request,"bdOrderStatisticPrintB",20l);
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
		String search = request.getParameter("search");
		if(RequestUtil.saveOperationSession(request,"bdOrderStatisticPrintB",20l)!=0){
			return new ModelAndView("po/jpoMemberOrderProductWeekStatistic");
		}
		if(!StringUtil.isEmpty(search)){
			String type = request.getParameter("type");
			String orderType = "";
			String excelTitle = "";
			if("1".equals(type)){
				orderType = "'1','2','4'";
				excelTitle = LocaleUtil.getLocalText("memberOrderProduct.statistic");//"会员产品订购(首购,升级,重销)";
			}else if("2".equals(type)){
				orderType = "'6','9'";
				excelTitle = LocaleUtil.getLocalText("storeOrderProduct.statistic");//"店铺产品订购(首购,重销)";
			}else if("3".equals(type)){
				orderType = "'11','12','14'";
				excelTitle = LocaleUtil.getLocalText("subStoreOrderProduct.statistic");//"二级店铺产品订购(首购,升级,重销)";
			}
	//获取是审核日期还是审核时间
			String time=request.getParameter("dateType");
			String sql="";
			String checkTimeS = request.getParameter("createBTime");
			String checkTimeE = request.getParameter("createETime");
			String sqlProduct="";
			if("1".equals(time))//审核时间
			{
				sql = "Select w.Week, Jps.Product_Name, qty From jpm_product_sale_new Jps, (Select a.Week, Product_Id, Sum(qty) qty From (Select f_Year || Lpad(f_Week, 2, 0) Week, Start_Time, End_Time From Jbd_Period) a, (Select Product_Id, Check_Date,qty From Jpo_Member_Order Jmo, Jpo_Member_Order_List Jmol Where order_type In (" + orderType + ") And COMPANY_CODE ='" + loginSysUser.getCompanyCode() + "' And Jmo.Mo_Id = Jmol.Mo_Id  And Jmo.check_time>=to_date('"+checkTimeS+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') And Jmo.check_time<=to_date('"+checkTimeE+" 00:00:00','yyyy-mm-dd hh24:mi:ss')) b Where a.Start_Time <= b.Check_Date And a.End_Time > b.Check_Date Group By a.Week, b.Product_Id) w Where Jps.Uni_No = w.Product_Id Order By w.Week";
				sqlProduct = "Select Jps.Product_Name From jpm_product_sale_new Jps, (Select a.Week, Product_Id, Sum(qty) qty From (Select f_Year || Lpad(f_Week, 2, 0) Week, Start_Time, End_Time From Jbd_Period) a, (Select Product_Id, Check_Date,qty From Jpo_Member_Order Jmo, Jpo_Member_Order_List Jmol Where order_type In (" + orderType + ") And COMPANY_CODE ='" + loginSysUser.getCompanyCode() + "' And Jmo.Mo_Id = Jmol.Mo_Id  And Jmo.check_time>=to_date('"+checkTimeS+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')  And Jmo.check_time<=to_date('"+checkTimeE+" 00:00:00','yyyy-mm-dd hh24:mi:ss')) b Where a.Start_Time <= b.Check_Date And a.End_Time > b.Check_Date Group By a.Week, b.Product_Id) w Where Jps.Uni_No = w.Product_Id Group By Jps.Product_Name";
			
			}else if("2".equals(time))//审核日期
			{
		    	 sql = "Select w.Week, Jps.Product_Name, qty From jpm_product_sale_new Jps, (Select a.Week, Product_Id, Sum(qty) qty From (Select f_Year || Lpad(f_Week, 2, 0) Week, Start_Time, End_Time From Jbd_Period) a, (Select Product_Id, Check_Date,qty From Jpo_Member_Order Jmo, Jpo_Member_Order_List Jmol Where order_type In (" + orderType + ") And COMPANY_CODE ='" + loginSysUser.getCompanyCode() + "' And Jmo.Mo_Id = Jmol.Mo_Id  And Jmo.check_date>=to_date('"+checkTimeS+" 00:00:00','yyyy-mm-dd hh24:mi:ss')  And Jmo.check_date<=to_date('"+checkTimeE+" 00:00:00','yyyy-mm-dd hh24:mi:ss')) b Where a.Start_Time <= b.Check_Date And a.End_Time > b.Check_Date Group By a.Week, b.Product_Id) w Where Jps.Uni_No = w.Product_Id Order By w.Week";
		    	 sqlProduct = "Select Jps.Product_Name From jpm_product_sale_new Jps, (Select a.Week, Product_Id, Sum(qty) qty From (Select f_Year || Lpad(f_Week, 2, 0) Week, Start_Time, End_Time From Jbd_Period) a, (Select Product_Id, Check_Date,qty From Jpo_Member_Order Jmo, Jpo_Member_Order_List Jmol Where order_type In (" + orderType + ") And COMPANY_CODE ='" + loginSysUser.getCompanyCode() + "' And Jmo.Mo_Id = Jmol.Mo_Id  And Jmo.check_date>=to_date('"+checkTimeS+" 00:00:00','yyyy-mm-dd hh24:mi:ss')  And Jmo.check_date<=to_date('"+checkTimeE+" 00:00:00','yyyy-mm-dd hh24:mi:ss')) b Where a.Start_Time <= b.Check_Date And a.End_Time > b.Check_Date Group By a.Week, b.Product_Id) w Where Jps.Uni_No = w.Product_Id Group By Jps.Product_Name";
			}
			List poMemberOrders = this.jdbcTemplate.queryForList(sql);
			
			
			List poMemberOrderProducts = this.jdbcTemplate.queryForList(sqlProduct);
			List reBuildList = reBuildList(poMemberOrders);
			
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions)to MemberOrderProducts_" + new Date() + ".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil excelUtil = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			excelUtil.addString(wsheet, 0, 0, excelTitle);
			excelUtil.mergeCells(wsheet, 0, 0, poMemberOrderProducts.size(), 0);
			//标题
			excelUtil.addString(wsheet, 0, 1, LocaleUtil.getLocalText("bdBounsDeduct.wweek"));
			for(int m = 0 ; m < poMemberOrderProducts.size() ; m++){
				Map mapArea = (Map)poMemberOrderProducts.get(m);
				excelUtil.addString(wsheet, m + 1, 1, mapArea.get("product_name").toString());

				for (int i = 0; i < reBuildList.size(); i++) {
					Map reBuildMap = (Map) reBuildList.get(i);
					Iterator ite = reBuildMap.keySet().iterator();
					if(ite.hasNext()){
						String week = (String)ite.next();
							int index=0;
							excelUtil.addString(wsheet, 0, i + 2, week);
							Map mapTmp = (Map)reBuildMap.get(week);
							
							if(mapTmp.get(mapArea.get("product_name").toString()) == null){
								excelUtil.addNumber(wsheet, m + 1, i + 2, Double.parseDouble("0"));
							}else{
								excelUtil.addNumber(wsheet, m + 1, i + 2, Double.parseDouble(mapTmp.get(mapArea.get("product_name").toString()).toString()));
							}
							
					}
				}
			}
			
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
			return null;
		}
		return new ModelAndView("po/jpoMemberOrderProductWeekStatistic");
	}
	
	/**
	 * 重构数据
	 * @param list
	 * @param area
	 * @return
	 */
	public List reBuildList(List list){
		List newList = new ArrayList();
		//Map newMap = new HashMap();
//		if( area.size() != 0 ){
			for(int i = 0 ; i < list.size() ; i++){
				Map map = (Map)list.get(i);
				boolean isWeekIn = false;
				for(int m = 0 ; m < newList.size() ; m++){
					Map newMap = (Map)newList.get(m);
					if(newMap.get(map.get("week").toString())!=null){
						Map newMapMap = (Map)newMap.get(map.get("week").toString());
						if(newMapMap.get(map.get("product_name").toString())==null){
							newMapMap.put(map.get("product_name").toString(), map.get("qty").toString());
						}else{
							int qty = Integer.parseInt(newMapMap.get(map.get("product_name").toString()).toString());
							int sqlQty = Integer.parseInt(map.get("qty").toString());
							newMapMap.put(map.get("product_name").toString(), qty+sqlQty+"");
						}
						isWeekIn = true;
						break;
					}
				}
				if(isWeekIn==false){
					Map newMap = new HashMap();
					newMap.put(map.get("week").toString(), new HashMap());
					Map newMapMap = (Map)newMap.get(map.get("week").toString());
					newMapMap.put(map.get("product_name").toString(), map.get("qty").toString());
					newList.add(newMap);
				}
			}
//		}
		return newList;
	}
}
