package com.joymain.jecs.mi.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberRecommendReportController extends BaseFormController {
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
	private Double totalBonusPv = null;
	private Double totalRemittanceMoney = null;
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private Map cardTypeMap=null;
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JmiMemberRecommendReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        RequestUtil.freshSession(request,"jmiMemberRecommendReport", Constants.COMPANY_TIME);


		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jmiMemberRecommendReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			if(RequestUtil.saveOperationSession(request, "jmiMemberRecommendReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:jmiMemberRecommendReport.html");
	       	 }
			company=companyCode;
			String formatedWeek = request.getParameter("formatedWeek");

			formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=MemberRecommend_"+WeekFormatUtil.getFormatWeek("w",formatedWeek)+"_"+companyCode+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
			
			//加入期别时间段显示
			String startDate=request.getParameter("startDate")+" 00:00:00";
			String endDate=request.getParameter("endDate")+" 23:59:59";
			
			if(bdPeriod!=null){
				startDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  bdPeriod.getStartTime());
				endDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  bdPeriod.getEndTime());
			}
			if(bdPeriod!=null){
				eu.addString(wsheet, 0, 0, WeekFormatUtil.getFormatWeek("w",formatedWeek));
			}
			eu.addString(wsheet, 1, 0,startDate+" - "+endDate);
			//标题
			int i=0;
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdBonusRankingReport.recommandPerson"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("pd.createTime"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("logType.BC"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.recommendName"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.cardType"));


			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			

			
			String sql = "select t.recommend_no, t.card_type,s.user_name,t.create_time,t.check_date from " +
					"jmi_member t left join jsys_user s on t.recommend_no=s.user_code where t.card_type != '0' and t.company_code='"+companyCode+"'" +
					"and t.check_date>= to_date('"+startDate+"','yyyy-MM-dd hh24:mi:ss') " +
							"and t.check_date<= to_date('"+endDate+"','yyyy-MM-dd hh24:mi:ss')  order by t.recommend_no,card_type" ;
			
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						String recommendNo="";
						while (rs.next()) {
							String recommend_no = rs.getString("recommend_no");
							String user_name = rs.getString("user_name");
							String cardType = rs.getString("card_type");
							String create_time = rs.getString("create_time");
							String check_date = rs.getString("check_date");
			
							
							
						
							int index=0;
							if(!recommendNo.equals(recommend_no)){
								eu.addString(wsheet, index++, kk, recommend_no);
								
								eu.addString(wsheet, index++, kk, create_time);

								eu.addString(wsheet, index++, kk, check_date);
								
								recommendNo=recommend_no;
							}else{
								index++;
								index++;
								index++;
							}
			
							eu.addString(wsheet, index++, kk, user_name);
							
							eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(cardTypeMap.get(cardType).toString()));

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