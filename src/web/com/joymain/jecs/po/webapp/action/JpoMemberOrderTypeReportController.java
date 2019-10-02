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

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;

/**
 * 各订单类型占比统计报表
 * @author Administrator
 *
 */
public class JpoMemberOrderTypeReportController extends BaseController
implements Controller{
	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
	
		 RequestUtil.freshSession(request,"bdOrderStatisticPrintB",20l);
	
		String search = request.getParameter("search");

		if(!StringUtil.isEmpty(search)){
			String fyear = request.getParameter("fyear");

	//获取是审核日期还是审核时间
			//String time=request.getParameter("dateType");
			String sql="";
			if(!"".equals(fyear))
			{
				   sql=" select * from view_jpomemberordertype where p_view_param.set_param('"+fyear+"')='"+fyear+"'";
			}else
			{
				throw new AppException("工作年份不允许为空！");
			}
			List poMemberOrders = this.jdbcTemplate.queryForList(sql);
			List reBuildList = reBuildList(poMemberOrders);
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions)to MemberOrderProducts_" + new Date() + ".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil excelUtil = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
		
			excelUtil.mergeCells(wsheet, 0, 0, poMemberOrders.size(), 0);
			String excelTitle=fyear+""+LocaleUtil.getLocalText("jpomemberordertype.proportion");
			excelUtil.addString(wsheet, 0, 0,excelTitle );
			
			//标题
			excelUtil.addString(wsheet, 0, 1, LocaleUtil.getLocalText("bdBounsDeduct.fmonth"));
			String oldMont="ss";
			int oldNumber=0;
			for(int m = 0 ; m < poMemberOrders.size() ; m++){
				Map mapArea = (Map)poMemberOrders.get(m);
				if(!oldMont.equals(mapArea.get("f_month").toString()))
				{
				     excelUtil.addString(wsheet, oldNumber + 1, 1, mapArea.get("f_month").toString());
				    
						for (int i = 0; i < reBuildList.size(); i++) {
							Map reBuildMap = (Map) reBuildList.get(i);
							Iterator ite = reBuildMap.keySet().iterator();
							if(ite.hasNext()){
								String order_type = (String)ite.next();
								String ordre_type_string="";
								switch(Integer.parseInt(order_type)){
								case  1:
								    ordre_type_string="会员首购";	
								    break;
								case 2:
									ordre_type_string="会员升级";
									 break;
								case 3:
									ordre_type_string="会员续约";
									 break;
								case 4:
									ordre_type_string="会员重消";
									 break;
								case 5:
									ordre_type_string="辅销品";
									 break;
								case 6:
									ordre_type_string="店铺首购";
									 break;
								case 9:
									ordre_type_string="店铺重消";
									 break;
								case 12:
								    ordre_type_string="店铺升级";
								    break;
								case 40:
									ordre_type_string="退单";
									 break;
								}
								
									excelUtil.addString(wsheet, 0, i + 2, ordre_type_string);
									Map mapTmp = (Map)reBuildMap.get(order_type);
									excelUtil.addNumber(wsheet, oldNumber + 1, i + 2, Double.parseDouble(mapTmp.get(mapArea.get("f_month").toString()).toString()));
									oldMont=mapArea.get("f_month").toString();	
							}
						}
						 oldNumber=oldNumber+1;
				}else
				{
					continue;
				}
				
		
			}
			
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
			return null;
		}
		return new ModelAndView("po/jpoMemberOrderTypeReport");
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
					if(newMap.get(map.get("order_type").toString())!=null){
						Map newMapMap = (Map)newMap.get(map.get("order_type").toString());
						//if(newMapMap.get(map.get("f_month").toString())==null){
							newMapMap.put(map.get("f_month").toString(), map.get("amount").toString());
						//}else{
					
						isWeekIn = true;
						break;
					}
				}
				if(isWeekIn==false){
					Map newMap = new HashMap();
					newMap.put(map.get("order_type").toString(), new HashMap());
					Map newMapMap = (Map)newMap.get(map.get("order_type").toString());
					newMapMap.put(map.get("f_month").toString(), map.get("amount").toString());
					newList.add(newMap);
				}
			}
//		}
			
			//System.out.println("newList============"+newList);
		return newList;
	}
	}


