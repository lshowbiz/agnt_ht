package com.joymain.jecs.mi.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 
 * 
 * 
 */
public class JmiMemberUSPromotionsReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private JmiMemberManager jmiMemberManager;
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}


	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}


	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}


	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}


	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private Map cardTypeMap=null;
	private Map yesNoMap=null;
	private Map provinceMap=null;
	private Map cityMap=null;
	private Map districtMap=null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JmiMemberUSPromotionsReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		

		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiMemberUSPromotionsReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			final String formatedWeek = request.getParameter("formatedWeek");
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=us_promotion.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
//			String startDate=request.getParameter("startDate");
//			String endDate=request.getParameter("endDate");
			
//			eu.addString(wsheet, 0, 0, startDate+"-"+endDate);
			//标题
			int i=0;
			
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("miMember.cardType"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("member.recommend.pv"));

//			eu.addString(wsheet, i++, 0, "用户编号");
//			eu.addString(wsheet, i++, 0, "卡别");
			eu.addString(wsheet, i++, 0, "被推荐人编号");
			eu.addString(wsheet, i++, 0, "推荐订单编号");
			eu.addString(wsheet, i++, 0, "订单PV");
			eu.addString(wsheet, i++, 0, "审核时间");
			eu.addString(wsheet, i++, 0, "审核日期");


			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			String dateSql="";
			
			
//			if(StringUtil.isDate(startDate) && StringUtil.isDate(startDate) ){
//				dateSql=" and o.check_date >=  to_date('"+startDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " +
//				"and o.check_date <=  to_date('"+endDate+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ";
//			}

			
			String sql1=" select t.recommend_no, nvl(sum(o.pv_amt),0) as pv_amt from jmi_member t, jpo_member_order o,jmi_member r where t.user_code = o.user_code  and r.card_type in ('1', '2') and t.recommend_no=r.user_code " +
					"and o.order_type in ('1', '2') and r.company_code = '"+companyCode+"'  and o.status='2' "+dateSql+"  group by t.recommend_no having sum(o.pv_amt) >= 700 ";
			
			String sql2=" select t.recommend_no, nvl(sum(o.pv_amt), 0) as pv_amt from jmi_member t, jpo_member_order o,jmi_member r " +
					"where t.user_code = o.user_code "+dateSql+"  and o.status='2'  and r.card_type in ('3') and t.recommend_no=r.user_code and r.company_code = '"+companyCode+"' and o.pv_amt >= 700  group by t.recommend_no" ;
			
			
			
			List list1=jdbcTemplate.queryForList(sql1); 
			List list2=jdbcTemplate.queryForList(sql2);
			

			int kk = 1;
			
			for (int j = 0; j < list1.size(); j++) {
				Map map=(Map) list1.get(j);
				String recommend_no=map.get("recommend_no").toString();
				String pv_amt=map.get("pv_amt").toString();
				JmiMember jmiMember=jmiMemberManager.getJmiMember(recommend_no);
				int index=0;
				
				

				String card_type=cardTypeMap.get(jmiMember.getCardType()).toString();
				
				eu.addString(wsheet, index++, kk, recommend_no);
				eu.addString(wsheet, index++, kk, jmiMember.getMiUserName());
				eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(card_type));
				eu.addNumber(wsheet, index++, kk, new Double(pv_amt));

				kk++;
				kk=setData(recommend_no,  kk,"'1', '2', '4'");
				
//				kk++;
			}

			kk++;
			for (int j = 0; j < list2.size(); j++) {
				Map map=(Map) list2.get(j);
				
				String recommend_no=map.get("recommend_no").toString();
				String pv_amt=map.get("pv_amt").toString();
				JmiMember jmiMember=jmiMemberManager.getJmiMember(recommend_no);
				int index=0;
				
				String card_type=cardTypeMap.get(jmiMember.getCardType()).toString();
				eu.addString(wsheet, index++, kk, recommend_no);
				eu.addString(wsheet, index++, kk, jmiMember.getMiUserName());
				eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(card_type));
				eu.addNumber(wsheet, index++, kk, new Double(pv_amt));

				kk++;
				kk=setData(recommend_no,  kk,"'1', '4'");
				
//				kk++;
				
				
			}
			
			
			
			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();

			return null;
		}

		return new ModelAndView(getSuccessView());
	}

	private Integer setData(String recommend_no,Integer kk,String orderType) throws Exception{
		String sql="select t.user_code,o.member_order_no,o.pv_amt,o.check_time,o.check_date from  jmi_member t, jpo_member_order o " +
		"where t.user_code = o.user_code and o.order_type in ( "+orderType+" ) and o.status='2' and t.recommend_no ='"+recommend_no+"'";
		if("'1'".equals(orderType)){
			sql+=" and o.pv_amt>=700";
		}
		sql+=" order by o.check_date";
		List list=jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			int index=4;
			eu.addString(wsheet, index++, kk, map.get("user_code").toString());
			eu.addString(wsheet, index++, kk, map.get("member_order_no").toString());
			eu.addNumber(wsheet, index++, kk, new Double(map.get("pv_amt").toString()));
			eu.addString(wsheet, index++, kk, map.get("check_time").toString());
			eu.addString(wsheet, index++, kk, map.get("check_date").toString());

			kk++;
		}
		return kk;
	}

}