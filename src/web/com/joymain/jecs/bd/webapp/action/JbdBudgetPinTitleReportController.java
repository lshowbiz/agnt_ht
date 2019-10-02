package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 奖金报表B
 * 
 * 
 */
public class JbdBudgetPinTitleReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	private JmiMemberManager jmiMemberManager;

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private JdbcTemplate jdbcTemplate = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private Double franchisePvTotal   = null;
	private Double franchiseMoneyTotal   = null;
	private Double consumerAmountTotal  = null;
	private Double ventureSalesPvTotal  = null;
	private Double ventureLeaderPvTotal   = null;
	private Double successSalesPvTotal    = null;
	private Double successLeaderPvTotal   = null;
	private Double deductMoneyTotal   = null;
	private Double deductTaxTotal   = null;
	private Double bounsPvTotal   = null;
	private Double bounsMoneyTotal   = null;
	private Double sendMoneyTotal    = null;
	private Double recommendBonusPvTotal=null;
	private Double ventureFundPvTotal=null;
	
	
	private Double storeExpandPvTotal = null;
	private Double storeServePvTotal = null;
	private Double storeRecommendPvTotal =null;
	
	
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private AlCountry alCountry = new AlCountry();
	private Map memberTypeMap=null;
	private Map storeTypeMap=null;
	private Map identityTypeMap=null;
	private Map freezeStatusMap=null;
	private Map passStarMap=null;

	private Map cardTypeMap=null;
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JbdBudgetPinTitleReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		

        RequestUtil.freshSession(request,"jbdBudgetPinTitleReport", Constants.COMPANY_TIME);
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdBudgetPinTitleReport".equalsIgnoreCase(request.getParameter("strAction"))) {


			if(RequestUtil.saveOperationSession(request, "jbdBudgetPinTitleReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:jbdBudgetPinTitleReport.html");
	       	 }
			
			
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			String formatedWeek = request.getParameter("formatedWeek");
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=BudgetPinTitleReport_"+formatedWeek+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			//加入期别时间段显示
			formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
			BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
			eu.addString(wsheet, 0, 0, WeekFormatUtil.getFormatWeek("w",formatedWeek));
			eu.addString(wsheet, 1, 0, bdPeriod.getStartTime()+" - "+bdPeriod.getEndTime());
			//标题
			int i=0;
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("star.week"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("star.month"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("star.level"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("group.area.pv"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.mobiletele"));

            //增加所属大区
            eu.addString(wsheet, i++, 1, "所属区域");
            
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("sysUser.address"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("mi.join.week"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("member.memberType"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.poMemberOrder.company"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.common.remark"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.titleRemark"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.postalcode"));
			eu.addString(wsheet, i++, 1, "是否中策");
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.papernumber"));
			
			

			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			passStarMap=ListUtil.getListOptions(companyCode, "pass.star.zero");
			
			String sql = "Select c.User_Code,c.Name,c.w_Week,c.w_Month,c.Pass_Star,c.Month_Area_Total_Pv,c.Mobiletele,d.belong_area,d.State_Province_Name || e.City_Name || c.Address as address," +
					"f.w_Year || Lpad(f.w_Week, 2, 0) as joinweek,c.Member_Type,c.Company_Code, c.Remark,c.title_remark,c.postalcode,c.papernumber From (Select a.User_Code,a.Name,a.w_Week,a.w_Month," +
					"a.Pass_Star,a.Month_Area_Total_Pv,b.Mobiletele, b.Province,b.City,b.Address,b.Check_Date,b.Member_Type,b.Company_Code,b.Remark,b.title_remark,b.postalcode,b.papernumber " +
					"From (Select User_Code, Name, w_Week, w_Month, Pass_Star,Month_Area_Total_Pv From v_Jbd_Day_Bouns_Calc " +
					"Where Pass_Star > 0 And w_Week = "+formatedWeek+" Order By w_Week) a, Jmi_Member b Where a.User_Code = b.User_Code) c, " +
					"Jal_State_Province d, Jal_City e,Jbd_Period f Where c.Province = d.State_Province_Id(+) And c.City = e.City_Id(+) " +
					"And c.Check_Date < f.End_Time(+) And c.Check_Date >= f.Start_Time(+) Order By w_Week, User_Code";
			
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						while (rs.next()) {
							
							String user_code = rs.getString("user_code");
							String name = rs.getString("name");
							String w_week = rs.getString("w_week");
							String w_month = rs.getString("w_month");
							String Pass_Star = rs.getString("Pass_Star");
							double Month_Area_Total_Pv = rs.getDouble("Month_Area_Total_Pv");
							String Mobiletele = rs.getString("Mobiletele");
							//所属区域
                            String belongArea = rs.getString("belong_area");
                            
							String address = rs.getString("address");
							String joinweek = rs.getString("joinweek");
							String member_type = rs.getString("member_type");
							if(!StringUtil.isEmpty(member_type)){
								member_type=member_type.trim();
							}
							String company_code = rs.getString("company_code");
							String remark = rs.getString("remark");
							String title_remark = rs.getString("title_remark");
							String postalcode = rs.getString("postalcode");
							String papernumber = rs.getString("papernumber");
	

							
							int index=0;
							
							eu.addString(wsheet, index++, kk, user_code);
							eu.addString(wsheet, index++, kk, name);
							eu.addString(wsheet, index++, kk, WeekFormatUtil.getFormatWeek("w",w_week));
							eu.addString(wsheet, index++, kk, WeekFormatUtil.getFormatMonth("w",w_month));
							eu.addString(wsheet, index++, kk, passStarMap.get(Pass_Star)==null?"":LocaleUtil.getLocalText(passStarMap.get(Pass_Star).toString()));
							eu.addNumber(wsheet, index++, kk, Month_Area_Total_Pv);
							eu.addString(wsheet, index++, kk, Mobiletele);
							//所属区域
                            eu.addString(wsheet, index++, kk, belongArea);
                            
							eu.addString(wsheet, index++, kk, address);
							eu.addString(wsheet, index++, kk,  WeekFormatUtil.getFormatWeek("w",joinweek));
							eu.addString(wsheet, index++, kk, memberTypeMap.get(member_type)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(member_type).toString()));
							eu.addString(wsheet, index++, kk, company_code);
							eu.addString(wsheet, index++, kk, remark);
							eu.addString(wsheet, index++, kk, title_remark);
							eu.addString(wsheet, index++, kk, postalcode);
							
							Map zcwMap=jmiMemberManager.getZcwMemberByUserCode(user_code);
							if(zcwMap==null){
								eu.addString(wsheet, index++, kk, "否");
							}else{
								eu.addString(wsheet, index++, kk, "是");
							}

							eu.addString(wsheet, index++, kk, papernumber);
							kk++;
						} 

			
	
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