package com.joymain.jecs.bd.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class JbdSendTypeReportController extends BaseFormController {
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
	private JmiMemberManager jmiMemberManager;
	
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
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
	private Double bounsFranchisePvTotal = null;
	private Double bounsMoneyTotal   = null;
	private Double sendMoneyTotal    = null;
	private Double recommendBonusPvTotal=null;
	private Double ventureFundPvTotal=null;
	

	private Double totaldevTotal = null;
	private Double deductedDevTotal = null;
	private Double currentDevTotal = null;
	
	private Double storeExpandPvTotal = null;
	private Double storeServePvTotal = null;
	private Double storeRecommendPvTotal =null;
	private Double networkMoneyTotal = null;
	
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private AlCountry alCountry = new AlCountry();
	private Map memberTypeMap=null;
	private Map storeTypeMap=null;
	private Map identityTypeMap=null;
	private Map freezeStatusMap=null;
	private Map sendTypeMap=null;
	private Map cardTypeMap=null;
	private Boolean flag=false;
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JbdSendTypeReportController() {
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

		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdSendTypeReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			


			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			final String formatedWeek = request.getParameter("formatedWeek");
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=bdsendreport.xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			
			BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",formatedWeek));
			eu.addString(wsheet, 0, 0, formatedWeek);
			eu.addString(wsheet, 1, 0, bdPeriod.getStartTime()+" - "+bdPeriod.getEndTime());
			//标题
			
			int i=0;

			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.bonusMoney"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bd.currentDev.bd"));
			eu.addString(wsheet, i++, 1, "电子存折金额");
			eu.addString(wsheet, i++, 1, "发放情况");
			eu.addString(wsheet, i++, 1, "选择方式");
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("sysOperationLog.operateTime"));
			
			
			
			
			
			
			
			


			sendTypeMap=ListUtil.getListOptions(companyCode, "jbd.send.type");
			
			
			
			
			
			//设置进度条总记录数
			String condition="and  t.w_week ="+WeekFormatUtil.getFormatWeek("f",formatedWeek);
			if(!"AA".equals(company)){
				condition+=" and t.company_code='"+companyCode+"' ";
			}
			//condition+="  order by b.user_code ";
			
			String sql = "select t.user_code,t.name,nvl(t.remittance_money,0) as remittance_money,t.current_dev,t.send_date,t.send_type,t.operater_time,nvl(b.balance,0) as balance  from jbd_send_record_hist  t  left join jfi_bankbook_balance b on b.user_code=t.user_code where t.remittance_money>0 and t.freeze_status=0 " + condition;
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						

						
			        	
			        	
						int kk = 2;
						while (rs.next()) {
							
							String user_code = rs.getString("user_code");
							String name = rs.getString("name");
							double remittance_money = rs.getDouble("remittance_money");
							double current_dev = rs.getDouble("current_dev");
							String send_date = rs.getString("send_date");
							String send_type = rs.getString("send_type");
							String operater_time = rs.getString("operater_time");
							double balance = rs.getDouble("balance");
							
							
							
							
							
							
							
							int index=0;
							
							eu.addString(wsheet, index++, kk, user_code);
							
							eu.addString(wsheet, index++, kk, name);
							eu.addNumber(wsheet, index++, kk, remittance_money);
							eu.addNumber(wsheet, index++, kk, current_dev);
							eu.addNumber(wsheet, index++, kk, balance);
							if(send_date==null){

								eu.addString(wsheet, index++, kk, "未发");
							}else{
								eu.addString(wsheet, index++, kk, "已发");
							}
							
							
							if(StringUtil.isEmpty(send_type)){
								eu.addString(wsheet, index++, kk, "");
							}else{

								eu.addString(wsheet, index++, kk, sendTypeMap.get(send_type)==null?"":LocaleUtil.getLocalText(sendTypeMap.get(send_type).toString()));
							}
							

							eu.addString(wsheet, index++, kk, operater_time);

							
							kk++;
						} 
//						
						
	
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