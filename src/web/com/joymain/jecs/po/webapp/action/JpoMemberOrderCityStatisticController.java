package com.joymain.jecs.po.webapp.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 订单统计(市区)
 * 
 * @author Alvin
 * 
 */
public class JpoMemberOrderCityStatisticController extends BaseController
		implements Controller {
	private final Log log = LogFactory
			.getLog(JpoMemberOrderCityStatisticController.class);

	private JdbcTemplate jdbcTemplate = null;
    private AlCountryManager alCountryManager = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("province", alCountry.getAlStateProvinces());
        RequestUtil.freshSession(request,"jpoMemberOrderCityStatistic",60l);

		String search = request.getParameter("search");
		if(!StringUtil.isEmpty(search)){
			
			/*if(RequestUtil.saveOperationSession(request,"jpoMemberOrderCityStatistic",60l)!=0){
    			return new ModelAndView("redirect:jpoMemberOrderCityStatistic.html");
    		}*/
			
			String province = request.getParameter("province");
			String checkTimeS = request.getParameter("createBTime");
			String checkTimeE = request.getParameter("createETime");
				if(StringUtil.isEmpty(checkTimeS)){
					this.saveMessage(request, "请选择日期!");
					return new ModelAndView("po/jpoMemberOrderCityStatistic");
				}
				if(StringUtil.isEmpty(checkTimeE)){
					this.saveMessage(request, "请选择日期!");
					return new ModelAndView("po/jpoMemberOrderCityStatistic");
				}
			
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=MemberOrders_"+request.getParameter("createBTime")+"_"+request.getParameter("createETime")+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			ExcelUtil excelUtil = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			
			String sql = "select nvl(city,' ') as city,nvl(sum(amount),'0') as amount,nvl(sum(jj_amount),'0')as jj_amount,nvl(sum(pv_amt),'0') as pv_amt,nvl(sum(DISCOUNT_AMOUNT),'0') as discount_amount  from jpo_member_order";
			sql += " where check_time >= to_date('" + checkTimeS + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			sql += " and check_time <= to_date('" + checkTimeE + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			sql += " and status = '2'";
			sql+= " and IS_RETREAT_ORDER='0'";//不包括退过的单
			sql += " and company_code = '" + loginSysUser.getCompanyCode() + "'";
			sql += " and province = '" + province + "'";
			sql += " group by city";
			List poMemberOrders = this.jdbcTemplate.queryForList(sql);
			
			String sqlArea = "select * from jal_city where state_province_id = '" + province + "'";
			List poMemberOrderAreas = this.jdbcTemplate.queryForList(sqlArea);
			//标题
			/**
			 * 罗婷 2011-11-11
			 * */
			for (int i = 0; i < poMemberOrderAreas.size(); i++) {
				Map poMemberOrderMap = (Map)poMemberOrderAreas.get(i);
				excelUtil.addString(wsheet,i*4,0,poMemberOrderMap.get("city_name").toString());
				excelUtil.addString(wsheet,i*4+1,0,"");
				excelUtil.addString(wsheet,i*4+2,0,"");
				excelUtil.addString(wsheet,i*4+3,0,"");
				
				excelUtil.addString(wsheet,i*4,1,"总价格");
				excelUtil.addString(wsheet,i*4+1,1,"基金金额");
				excelUtil.addString(wsheet,i*4+2,1,"总pv");
				excelUtil.addString(wsheet,i*4+3, 1, "抵扣总积分");
				for (int m = 0; m < poMemberOrders.size(); m++) {
					Map poMemberOrdersMap = (Map)poMemberOrders.get(m);
					if(poMemberOrderMap.get("city_id").toString().equals(poMemberOrdersMap.get("city").toString())){
						
						excelUtil.addString(wsheet,i*4,2,poMemberOrdersMap.get("amount").toString());
						excelUtil.addString(wsheet,i*4+1,2,poMemberOrdersMap.get("jj_amount").toString());
						excelUtil.addString(wsheet,i*4+2,2,poMemberOrdersMap.get("pv_amt").toString());
						excelUtil.addString(wsheet,i*4+3,2, poMemberOrdersMap.get("discount_amount").toString());
						break;
					}
				}
			}
			for (int m = 0; m < poMemberOrders.size(); m++) {
				Map poMemberOrdersMap = (Map)poMemberOrders.get(m);
				if(" ".equals(poMemberOrdersMap.get("city").toString())){
					excelUtil.addString(wsheet,poMemberOrderAreas.size()*4,2,poMemberOrdersMap.get("amount").toString());
					excelUtil.addString(wsheet,poMemberOrderAreas.size()*4+1,2,poMemberOrdersMap.get("jj_amount").toString());
					excelUtil.addString(wsheet,poMemberOrderAreas.size()*4+2,2,poMemberOrdersMap.get("pv_amt").toString());
					excelUtil.addString(wsheet,poMemberOrderAreas.size()*4+3,2,poMemberOrdersMap.get("discount_amount").toString());
				}
			}
			
			excelUtil.writeExcel(wwb);
			excelUtil.closeWritableWorkbook(wwb);
			os.close();
			return null;
		}

		return new ModelAndView("po/jpoMemberOrderCityStatistic");
	}

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
}
