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

import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
//养生馆各店铺重消明细
public class JpoMemberOrderShopRController extends BaseController implements
		Controller {

	private JdbcTemplate jdbcTemplate = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String search = request.getParameter("search");
		if (StringUtils.isNotEmpty(search)) {
			try {
				String fyear = request.getParameter("fyear");
				String sql = "";
				String sql2 = "";
				
				if (StringUtils.isNotEmpty(fyear)) {
					// sql=" select * from view_jpomemberordertype where
					// p_view_param.set_param('"+fyear+"')='"+fyear+"'";
					sql = "select  po.user_code  user_code, sum(qty*price) amount,f_month from jpo_member_order po,"
							+ " jpo_member_order_list pol, jbd_period  bd where  po.company_code='CN' "
							+ "and status='2' and order_type in('9','14') "
							+ "and po.mo_id=pol.mo_id and f_year='"
							+ fyear
							+ "' "
							+ "and check_time>=start_time and check_time<end_time "
							+ "group by po.user_code,f_month" +
									"order by f_month";
					 sql2="select * from view_jpostore where p_view_param.set_param('"+fyear+"')='"+fyear+"'";
					
				} else {
					throw new AppException("工作年份不允许为空！");
				}
				List poMemberOrderAmount = this.jdbcTemplate.queryForList(sql);
				List poMemberOrderUser = this.jdbcTemplate.queryForList(sql2);
			
				Object[] object = reBuildList(poMemberOrderAmount);
				
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition","attachment; filename=Generate Report(Members Vs Commissions)to MemberOrderProducts_"+ new Date() + ".xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30");
				OutputStream os = response.getOutputStream();
				ExcelUtil excelUtil = new ExcelUtil();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet wsheet = wwb.createSheet("Sheet1", 0);

				int col = 0;
				int row = 0;
			
				excelUtil.addString(wsheet,0,row,fyear+ ""+LocaleUtil.getLocalText("jpomemberordertype.shopR"));
				
				Map dataMap=(Map)object[0];//数据
				List fmonthList=(ArrayList)object[1];//月份
				
				// 标题
				excelUtil.addString(wsheet, col=0, ++row,head[0]);
				excelUtil.addString(wsheet, ++col, row,head[1]);
	            for (int h = 0; h < fmonthList.size(); h++) {
					excelUtil.addString(wsheet, ++col, row, fyear+"财年"+fmonthList.get(h)+"财政月重消" );
				}
				for (int h = 2; h < head.length; h++) {
					excelUtil.addString(wsheet, ++col, row,head[h].toString());
				}
				
				
				// 总金额
				String firstName=null;
				String lastName=null;
				for(int i=0;i<poMemberOrderUser.size();i++){
					Map map=(Map)poMemberOrderUser.get(i);
					firstName=map.get("first_name").toString();
					lastName=map.get("last_name").toString();
					excelUtil.addString(wsheet, col=0, ++row, map.get("user_code").toString());
					excelUtil.addString(wsheet,  ++col, row, firstName+""+lastName);
					excelUtil.addString(wsheet, col=0, ++row, map.get("state_province_name").toString());
					excelUtil.addString(wsheet, col=0, ++row, map.get("city_name").toString());
					excelUtil.addString(wsheet, col=0, ++row, map.get("address").toString());
					excelUtil.addString(wsheet, col=0, ++row, map.get("mobiletele").toString());
					 for (int h = 0; h < fmonthList.size(); h++) {
							if(dataMap.get(map.get("user_code")+"#"+fmonthList.get(h))!=null)
							{
							    excelUtil.addString(wsheet, ++col, row,dataMap.get(map.get("user_code")+"#"+fmonthList.get(h)).toString());
							}else
							{
								 excelUtil.addString(wsheet, ++col, row,"0");
							}
						}
				}
		
				excelUtil.writeExcel(wwb);
				excelUtil.closeWritableWorkbook(wwb);
				os.close();
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("po/jpoMemberOrderShopR");
	}

	private static String[] head = new String[] { "编号", "姓名",
			"申请加盟时间", "联系方式", "所属省份", "所属城市","所属区/县" };


	/**
	 * 重构数据
	 * 
	 * @param list
	 * @param area
	 * @return
	 */
	public Object[] reBuildList(List amountList) {

		Map<String, Object> dataMap = new HashMap<String, Object>();
        List fmonthList=new ArrayList();
		if (amountList != null) {
			String month = null;
			String userCode = null;
			List dataList = new ArrayList();
			String key = null;
			Map temp = null;
			for (Iterator<Map> it = amountList.iterator(); it.hasNext();) {
				temp = it.next();
				month = String.valueOf(temp.get("f_month"));
				userCode = String.valueOf(temp.get("user_code"));
				key = month + "#" + userCode;
				dataList.add(temp.get("amount"));
				dataMap.put(key, dataList);
                if(!fmonthList.contains(month))
                {
                	fmonthList.add(month);
                }
			}
			//System.out.print(dataMap);
		}

		return new Object[]{dataMap,fmonthList};
	}
}
