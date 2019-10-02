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

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 奖金报表B
 * 
 * 
 */
public class JbdMonthBonusSortReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
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
	private Double amountDouble =null;
	
	
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
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JbdMonthBonusSortReportController() {
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

		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdMonthBonusSortReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			 String formatedWeekStart = request.getParameter("formatedWeekStart");
			 String formatedWeekEnd = request.getParameter("formatedWeekEnd");
			final String amount = request.getParameter("amount");
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=PassStarReport_"+formatedWeekStart+"~"+formatedWeekEnd+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			

			formatedWeekStart=WeekFormatUtil.getFormatWeek("f",formatedWeekStart);
			formatedWeekEnd=WeekFormatUtil.getFormatWeek("f",formatedWeekEnd);
			
			BdPeriod bdPeriodStart=bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeekStart);
			BdPeriod bdPeriodEnd=bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeekEnd);
			eu.addString(wsheet, 0, 0, WeekFormatUtil.getFormatWeek("w",formatedWeekStart)+"~"+WeekFormatUtil.getFormatWeek("w",formatedWeekEnd));
			//标题
			int i=0;
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("label.month"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));

			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.finance.amount"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("member.memberType"));
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.mobiletele"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("sysUser.address"));
			
			if("AA".equals(company)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.poMemberOrder.company"));
			}
			
			amountDouble=new Double(amount);

			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			passStarMap=ListUtil.getListOptions(companyCode, "pass.star.zero");
			
			String sql = "Select a.User_Code,a.month,a.Send_Money,b.Mi_User_Name, b.Mobiletele,b.Member_Type," +
					"c.state_province_name || d.city_name || w.district_name as address,b.Company_Code From (Select User_Code, w_Year || Lpad(w_Month, 2, 0) As Month, Sum(Send_Money) As Send_Money " +
					"From Jbd_Send_Record_Hist Where Send_Money > 0 ";
			
			if(!StringUtil.isEmpty(formatedWeekStart)){
				sql+=" and w_week>= " +formatedWeekStart ;
			}
			if(!StringUtil.isEmpty(formatedWeekEnd)){
				sql+=" and w_week<= " +formatedWeekEnd ;
			}
			sql+= " Group By User_Code, w_Year || Lpad(w_Month, 2, 0)) a,Jmi_Member b,jal_state_province c,jal_city d, jal_district w " +
					" Where a.User_Code = b.User_Code And b.province = c.state_province_id(+)  And b.city = d.city_id(+) And b.district = w.district_id(+) " +
					" And a.Send_Money >= "+amountDouble; 
			if(!"AA".equals(company)){
				sql+=" and b.company_code='"+company+"'";
			}
			sql += " Order By a.Send_Money Desc ";
			
			
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						while (rs.next()) {
							
							String user_code = rs.getString("user_code");
							String name = rs.getString("Mi_User_Name");
							String month = rs.getString("month");
							Double send_money = rs.getDouble("Send_Money");
							String Mobiletele = rs.getString("Mobiletele");
							String address = rs.getString("address");
							String member_type = rs.getString("member_type");
							if(!StringUtil.isEmpty(member_type)){
								member_type=member_type.trim();
							}
							String company_code = rs.getString("company_code");
	

							
							int index=0;
							
							eu.addString(wsheet, index++, kk, user_code);
							eu.addString(wsheet, index++, kk, WeekFormatUtil.getFormatMonth("w",month));
							eu.addString(wsheet, index++, kk, name);
							eu.addNumber(wsheet, index++, kk, send_money);
							eu.addString(wsheet, index++, kk, memberTypeMap.get(member_type)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(member_type).toString()));
							eu.addString(wsheet, index++, kk, Mobiletele);
							eu.addString(wsheet, index++, kk, address);
							if("AA".equals(company)){
								eu.addString(wsheet, index++, kk, company_code);
							}
							
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