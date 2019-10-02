package com.joymain.jecs.bd.webapp.action;

import java.io.OutputStream;
import java.math.BigDecimal;
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
public class JbdBonusListReportController extends BaseFormController {
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
	
	
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private AlCountry alCountry = new AlCountry();
	private Map memberTypeMap=null;
	private Map storeTypeMap=null;
	private Map identityTypeMap=null;
	private Map freezeStatusMap=null;
	private Map activeMap=null;
	private Map passStarMap=null;
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JbdBonusListReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        RequestUtil.freshSession(request,"jbdBonusListReport", Constants.COMPANY_TIME);

		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdBonusListReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			if(RequestUtil.saveOperationSession(request, "jbdBonusListReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:jbdBonusListReport.html");
	       	 }
			
			company=companyCode;
			String formatedWeekStart = request.getParameter("formatedWeekStart");
			String formatedWeekEnd = request.getParameter("formatedWeekEnd");
			String amount = request.getParameter("amount");

			
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=BonsuListReport_"+WeekFormatUtil.getFormatWeek("f",formatedWeekStart)+"~"+WeekFormatUtil.getFormatWeek("f",formatedWeekEnd)+".xls");
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
			
			
			
			
			eu.addString(wsheet, 0, 0, formatedWeekStart+"~"+formatedWeekEnd);
			//标题
			int i=0;		

			eu.addString(wsheet, i++, 1, "财政月");
			eu.addString(wsheet, i++, 1, "会员编号");
			eu.addString(wsheet, i++, 1, "实发加发展基金");
			eu.addString(wsheet, i++, 1, "实发金额");
			eu.addString(wsheet, i++, 1, "发展基金");
			eu.addString(wsheet, i++, 1, "姓名");
			eu.addString(wsheet, i++, 1, "手机号码");
			eu.addString(wsheet, i++, 1, "会员类别");
			eu.addString(wsheet, i++, 1, "联系地址");
			eu.addString(wsheet, i++, 1, "国别");
			eu.addString(wsheet, i++, 1, "1为死点，0为正常");
			eu.addString(wsheet, i++, 1, "1为冻结，0为正常");
			eu.addString(wsheet, i++, 1, "退出时间");
			
			

			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			freezeStatusMap=ListUtil.getListOptions(companyCode, "mimember.freezestatus");
			activeMap=ListUtil.getListOptions(companyCode, "yesno");

			
			
			
			String sql = "Select a.User_Code,a.month, a.total,a.Send_Money, a.current_dev,b.Mi_User_Name,b.Mobiletele," +
					"b.Member_Type, c.state_province_name||d.city_name||w.district_name  as address,b.Company_Code,b.active,b.freeze_status,b.exit_date " +
					"From (Select User_Code, w_Year || Lpad(w_Month, 2, 0) As Month,Sum(nvl(send_money,0)+nvl(current_dev,0)) As total," +
					"Sum(current_dev) As current_dev,Sum(Send_Money) As Send_Money From jbd_member_link_calc_hist " +
					"Where " ;
		if(!StringUtil.isEmpty(formatedWeekStart)){
				sql+=" w_week>= " +formatedWeekStart ;
			}
			if(!StringUtil.isEmpty(formatedWeekEnd)){
				sql+=" and w_week<= " +formatedWeekEnd ;
			}
			sql+=" And Send_Money > 0 " +
					"Group By User_Code,w_Year || Lpad(w_Month, 2, 0)) a,Jmi_Member b,jal_state_province c,jal_city d, jal_district w" +
					" Where a.User_Code = b.User_Code And b.province=c.state_province_id(+) And b.city = d.city_id(+) And b.district = w.district_id(+) " +
					"And a.Send_Money >= "+amount;
			
			if(!"AA".equals(companyCode)){
				sql+=" and b.company_code='"+companyCode+"'";
			}
					
				sql+="  Order By a.Send_Money Desc";
			
			
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						while (rs.next()) {
							
							String user_code = rs.getString("user_code");
							String mi_user_name = rs.getString("mi_user_name");
							String month = rs.getString("month");
							double total = rs.getDouble("total");
							
							double send_money = rs.getDouble("send_money");
							double current_dev = rs.getDouble("current_dev");
							String mobiletele = rs.getString("mobiletele");
							String member_type = rs.getString("member_type");
							
							String address = rs.getString("address");
							String company_code = rs.getString("company_code");
							
							if(!StringUtil.isEmpty(member_type)){
								member_type=member_type.trim();
							}
							
							String freeze_status = rs.getString("freeze_status");
							String active = rs.getString("active");
							String exit_date = rs.getString("exit_date");
							
	

							
							int index=0;

							eu.addString(wsheet, index++, kk, WeekFormatUtil.getFormatMonth("w",month));
							eu.addString(wsheet, index++, kk, user_code);
							eu.addNumber(wsheet, index++, kk, total);
							eu.addNumber(wsheet, index++, kk, send_money);
							eu.addNumber(wsheet, index++, kk, current_dev);
							

							eu.addString(wsheet, index++, kk, mi_user_name);
							eu.addString(wsheet, index++, kk, mobiletele);
							eu.addString(wsheet, index++, kk, memberTypeMap.get(member_type)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(member_type).toString()));

							eu.addString(wsheet, index++, kk, address);

							eu.addString(wsheet, index++, kk, company_code);

							eu.addString(wsheet, index++, kk, active);

							eu.addString(wsheet, index++, kk, freeze_status);
							eu.addString(wsheet, index++, kk, exit_date);
							
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