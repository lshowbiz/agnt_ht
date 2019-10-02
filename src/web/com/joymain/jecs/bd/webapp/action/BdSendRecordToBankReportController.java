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
 * 交付银行
 * 
 * 
 */
public class BdSendRecordToBankReportController extends BaseFormController {
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
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public BdSendRecordToBankReportController() {
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

		if ("post".equalsIgnoreCase(request.getMethod()) && "bdSendRecordToBankReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			String formatedWeek = request.getParameter("formatedWeek");
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions) to Bank_"+formatedWeek+"_"+companyCode+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
			BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
			eu.addString(wsheet, 0, 0, WeekFormatUtil.getFormatWeek("w",formatedWeek));
			eu.addString(wsheet, 1, 0, bdPeriod.getStartTime()+" - "+bdPeriod.getEndTime());
			//标题
			int i=0;
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdBounsDeduct.wweek"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.cardType"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecordToBankReport.memberIdCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNameCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNumCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRemittanceReport.openBankCH"));
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecordToBankReport.bonusPvCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.bonusMoney"));

			//写内容
			totalBonusPv = new Double(0);
			totalRemittanceMoney = new Double(0);


			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			
			//设置进度条总记录数
			String condition="  where v.exit_date is null and v.active = '0'  and v.Card_Type IN ('1','2','3','4') and v.w_Week=" + WeekFormatUtil.getFormatWeek("f",formatedWeek) + " and v.send_money>0 and v.user_code=m.user_code and v.company_code='" + companyCode + "'";

			condition+=" order by v.user_code ";
			
			String sql = "select v.card_type,v.bankaddress,v.user_code,v.Name,m.papernumber,v.bankbook,v.bankcard,v.bank,v.BOUNS_pv,round(v.SEND_MONEY,2) as remittance_money,v.w_Week" +
					" from v_jbd_member_link_calc v ,jmi_member m " + condition;
			
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						int totalCountRs=0;
						while (rs.next()) {
							String userCode = rs.getString("user_code");
							String name = rs.getString("name");
							String cardType = rs.getString("card_type");
							String papernumber = rs.getString("papernumber");
							String bName = rs.getString("bankbook");
							String bNum = rs.getString("bankcard");
							String openBank = rs.getString("bank");
							Integer wWeek = rs.getInt("w_week");
			
							
							
							Double bonusPv = rs.getDouble("bouns_pv");
							totalBonusPv += bonusPv;
							Double remittanceMoney = rs.getDouble("remittance_money");
							totalRemittanceMoney += remittanceMoney;
			
							int index=0;
							eu.addNumber(wsheet, index++, kk, WeekFormatUtil.getFormatWeek("w",wWeek));
			
							eu.addString(wsheet, index++, kk, userCode);

							eu.addString(wsheet, index++, kk, name);
							
							eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(cardTypeMap.get(cardType).toString()));
				
							eu.addString(wsheet, index++, kk, papernumber);
				
							eu.addString(wsheet, index++, kk, bName);
					
							eu.addString(wsheet, index++, kk, bNum);
							
							eu.addString(wsheet, index++, kk, openBank);
							
		    				
							eu.addNumber(wsheet, index++, kk, bonusPv);
						
							eu.addNumber(wsheet, index++, kk, remittanceMoney);


							
							kk++;
							totalCountRs++;
						} 
						eu.addString(wsheet, 1, kk, totalCountRs+LocaleUtil.getLocalText("bdSendRecordToBankReport.countCH"));
						
						int footerIndex=7;
						eu.addString(wsheet, footerIndex++, kk,  LocaleUtil.getLocalText("report.allTotal")+":");
						eu.addNumber(wsheet, footerIndex++, kk, totalBonusPv);
				
						eu.addNumber(wsheet, footerIndex++, kk, totalRemittanceMoney);
			
	
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