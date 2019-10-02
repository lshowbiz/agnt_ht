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

import com.joymain.jecs.Constants;
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
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 
 * 
 * 
 */
public class JmiMemberActiveReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
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
	private Map memberLevelMap=null;
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JmiMemberActiveReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        RequestUtil.freshSession(request,"jmiMemberActiveReport", Constants.COMPANY_TIME);

		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiMemberActiveReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			if(RequestUtil.saveOperationSession(request, "jmiMemberActiveReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:jmiMemberActiveReport.html");
	       	 }
			
			company=companyCode;
			final String formatedWeek = request.getParameter("formatedWeek");
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=member_active.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			eu.addString(wsheet, 0, 0, startDate+"-"+endDate);
			//标题
			int i=0;
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.cardType.old"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.cardType"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.province"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.idAddr2"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.district"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.idAddr"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.common.store"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.honorStar"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("pd.createTime"));


			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberLevelMap=ListUtil.getListOptions(companyCode, "member.level");
			
			yesNoMap=ListUtil.getListOptions(companyCode, "yesno");
			
			provinceMap=alStateProvinceManager.getAlStateProvincesMapByCountry(companyCode);
			cityMap=alCityManager.getAlCityMap(companyCode);
			districtMap=alDistrictManager.getAlDistrictMap(companyCode);

			
			String sql=" Select a.member_level,a.User_Code,mi_user_name,a.province,a.city,a.district,a.address,a.isstore,b.honor_star,a.card_type,a.create_time From Jmi_Member a,jsys_user b " +
					"Where a.not_first=1 and a.User_Code Not In (Select Recommend_No From Jmi_Member) " +
					"And a.User_Code Not In (Select User_Code From Jpo_Member_Order Where " +
					"Check_Date >=To_Date('"+startDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss') " +
					"And Check_Date < To_Date('"+endDate+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') And Status = '2')" +
					" and a.company_code= '"+companyCode+"' and a.user_code=b.user_code";
			
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						int totalCountRs=0;
						while (rs.next()) {
							String userCode = rs.getString("user_code");
							String userName = rs.getString("mi_user_name");
							String card_type = rs.getString("card_type");
							String member_level = rs.getString("member_level");
							card_type=cardTypeMap.get(card_type).toString();
							member_level=memberLevelMap.get(member_level).toString();
							
							String province = rs.getString("province");
							province=provinceMap.get(province)==null?"":provinceMap.get(province).toString();
							
							String city = rs.getString("city");
							city=cityMap.get(city)==null?"":cityMap.get(city).toString();
							
							String district = rs.getString("district");
							district=districtMap.get(district)==null?"":districtMap.get(district).toString();
							
							String address = rs.getString("address");
							
							String isstore = rs.getString("isstore");
							isstore=yesNoMap.get(isstore).toString();
							
							String honor_star = rs.getString("honor_star");
							honor_star=yesNoMap.get(honor_star)==null?yesNoMap.get("0").toString():yesNoMap.get(honor_star).toString();
							

							String create_time = rs.getString("create_time");
							int index=0;
			
							eu.addString(wsheet, index++, kk, userCode);
							eu.addString(wsheet, index++, kk, userName);

							eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(card_type));
							eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(member_level));
							eu.addString(wsheet, index++, kk, province);
							eu.addString(wsheet, index++, kk, city);
							eu.addString(wsheet, index++, kk, district);
							eu.addString(wsheet, index++, kk, address);
							eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(isstore));
							eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(honor_star));
							eu.addString(wsheet, index++, kk, create_time);

							
							kk++;
							totalCountRs++;
						} 
						
						eu.addString(wsheet, 0, kk,  LocaleUtil.getLocalText("report.allTotal")+":");
						eu.addNumber(wsheet, 1, kk, totalCountRs);
				
			
	
					} catch (Exception e) {
						e.printStackTrace();
					}finally {  
						JdbcUtils.closeResultSet(rs);
					}
					return null;
				}
			});
			eu.writeExcel(wwb);
			eu.closeWritableWorkbook(wwb);
			os.close();

			return null;
		}

		return new ModelAndView(getSuccessView());
	}



}