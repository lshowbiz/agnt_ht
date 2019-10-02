package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.al.model.AlCountry;
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
public class JbdFreezeStatusReportController extends BaseFormController {
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
	private AlCountry alCountry = new AlCountry();
	private Map memberTypeMap=null;
	private Map storeTypeMap=null;
	private Map identityTypeMap=null;
	private String type=null;

	private Map cardTypeMap=null;
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JbdFreezeStatusReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		


        RequestUtil.freshSession(request,"jbdFreezeStatusReport", Constants.COMPANY_TIME);
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jbdFreezeStatusReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			


			if(RequestUtil.saveOperationSession(request, "jbdFreezeStatusReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:jbdFreezeStatusReport.html");
	       	 }
			
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Freeze_Report_"+companyCode+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			

			type=request.getParameter("type");
			
			//标题
			int i=0;
			if("1".equals(type)){
				eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdBounsDeduct.wweek"));
			}
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("miMember.cardType"));
			
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdSendRemittanceReport.openCityCH"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNameCH"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNumCH"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdSendRemittanceReport.openBankCH"));

			if("1".equals(type)){
				eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdSendRecord.registerStatus"));
			}
			
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("bdSendRecord.bonusMoney"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("member.memberType"));
			eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("customerRecord.remark"));
			

//			if("AA".equals(company)){
//				eu.addString(wsheet, i++, 0, LocaleUtil.getLocalText("busi.poMemberOrder.company"));
//			}

			//写内容

			
			totalRemittanceMoney=new Double(0);

			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			storeTypeMap=ListUtil.getListOptions(companyCode, "isstore");
			identityTypeMap=ListUtil.getListOptions(companyCode, "identitytype.tw");
			//设置进度条总记录数
			String condition=" where h.active = '0' and h.card_type != '0' and h.exit_date is null and h.remittance_money > 0 and h.freeze_status=1 and h.send_date is null " ;
			if(!"AA".equals(company)){
				condition+=" and h.company_code='"+companyCode+"' ";
			}

			
			String sql = "";
			
			if("0".equals(type)){
				sql = "select aa.remittance_money,aa.user_code,m.card_type,m.mi_user_name as name,m.bank,m.bankaddress,m.bankbook,m.bankcard, m.member_type,m.remark " +
						"from (select sum(h.remittance_money) as remittance_money, h.user_code from jbd_send_record_hist h";
			}else{
				sql = "select h.card_type,h.w_week,h.user_code,h.name,h.bank,h.bankaddress,h.bankbook,h.bankcard,h.remittance_money,h.register_status,h.reissue_status,m.member_type, m.remark" +
				" from jbd_send_record_hist h left join jmi_member m on h.user_code = m.user_code";
				
			}
			
			sql += condition;
			
			if("0".equals(type)){
				sql+=" group by h.user_code) aa left join jmi_member m on aa.user_code = m.user_code ";
			}
			
			

			sql+="  order by user_code ";
			
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 1;
						int totalCountRs=0;
						while (rs.next()) {
							
							String userCode = rs.getString("user_code");
							String name = rs.getString("name");
							String cardType = rs.getString("card_type");
							String bName = rs.getString("bankbook");
							String bNum = rs.getString("bankcard");
							String openBank = rs.getString("bank");
							String bankaddress = rs.getString("bankaddress");
							String memberType = rs.getString("member_type");
							if(!StringUtil.isEmpty(memberType)){
								memberType=memberType.trim();
							}
							String remark = rs.getString("remark");

							Integer wWeek=null;
							String registerStatus ="";

							if("1".equals(type)){
								wWeek = rs.getInt("w_week");
								registerStatus = rs.getString("register_status");
							}
							int index=0;
							
							
							
							Double remittanceMoney = rs.getDouble("remittance_money");
							totalRemittanceMoney += remittanceMoney;

							if("1".equals(type)){
								eu.addNumber(wsheet, index++, kk, WeekFormatUtil.getFormatWeek("w",wWeek));
							}
			
							eu.addString(wsheet, index++, kk, userCode);

							eu.addString(wsheet, index++, kk, name);
							
							eu.addString(wsheet, index++, kk, cardTypeMap.get(cardType)==null?"":LocaleUtil.getLocalText(cardTypeMap.get(cardType).toString()));

							eu.addString(wsheet, index++, kk, bankaddress);
							
							eu.addString(wsheet, index++, kk, bName);
					
							eu.addString(wsheet, index++, kk, bNum);
							
							eu.addString(wsheet, index++, kk, openBank);

							if("1".equals(type)){
								if("1".equals(registerStatus)){
									eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText("busi.bd.waitStatus"));
								}else if("3".equals(registerStatus)){
									eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText("bdSendRecord.ReissueStatus2"));
								}
							}
							eu.addNumber(wsheet, index++, kk, remittanceMoney);
							
							eu.addString(wsheet, index++, kk, memberTypeMap.get(memberType)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(memberType).toString()));

							eu.addString(wsheet, index++, kk, remark);
							
							kk++;
							totalCountRs++;
						} 
						
						eu.addString(wsheet, 1, kk, totalCountRs+LocaleUtil.getLocalText("bdSendRecordToBankReport.countCH"));
						int footerIndex=8;

						if("0".equals(type)){
							footerIndex=6;
						}
						eu.addString(wsheet, footerIndex++, kk,  LocaleUtil.getLocalText("report.allTotal")+":");
				
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