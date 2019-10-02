package com.joymain.jecs.bd.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdSendRecordAllotReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
    private AlCountryManager alCountryManager;
    private JbdSendRecordHistManager jbdSendRecordHistManager;
	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}


	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	private JdbcTemplate jdbcTemplateReport = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	private WritableWorkbook wwb = null;
	private Double totalBonusPv = null;
	private Double totalRemittanceMoney = null;
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private AlCountry alCountry = new AlCountry();
	private Map cardTypeMap=null;
	private Map memberLevelMap=null;
	private String type=null;
	private String app=null;
	private Map memberTypeMap=null;
	
	private int si = 1;//工作蒲码数
	private static final int sp = 60000;//一页放多少条记录


	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplateReport) {
		this.jdbcTemplateReport = jdbcTemplateReport;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public BdSendRecordAllotReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        String amountSend = ConfigUtil.getConfigValue(defSysUser.getCompanyCode(), "amount.send");
        request.setAttribute("startAllotMoney", amountSend);
        RequestUtil.freshSession(request,"bonusSendReport", Constants.COMPANY_TIME);
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "bonusSendReport".equalsIgnoreCase(request.getParameter("strAction"))) {
				
			
			if(RequestUtil.saveOperationSession(request, "bonusSendReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:bdSendRecordAllotReport.html");
	       	 }
			
			
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
//			final String formatedWeek = request.getParameter("formatedWeek");
			app=request.getParameter("app");
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions) to Bank_"+companyCode+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);

			type=request.getParameter("type");

			String startDate=request.getParameter("startDate");
        	String endDate=request.getParameter("endDate");

			eu.addString(wsheet, 0, 0, startDate+" 00:00:00"+"~"+endDate+" 23:59:59");

			//标题
			int i=0;
			if("1".equals(type)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdBounsDeduct.wweek"));
			}
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.cardType.old"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.cardType"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRemittanceReport.openCityCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNameCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNumCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRemittanceReport.openBankCH"));
			
			if("1".equals(type)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.registerStatus"));
			}
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.bonusMoney"));

			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("member.memberType"));
			
			if("1".equals(app)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bd.app.time"));
			}
			//写内容
			totalBonusPv = new Double(0);
			totalRemittanceMoney = new Double(0);

			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(companyCode);
        	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
        	alCountry.getAlStateProvinces().iterator();
			

			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");

			memberLevelMap=ListUtil.getListOptions(companyCode, "member.level");
			
			//设置进度条总记录数
			String condition="";
			String sql="";
			
			String startAllotMoney=request.getParameter("startAllotMoney");
        	String endAllotMoney=request.getParameter("endAllotMoney");
        	


        	String bdsendrecordType=(String) Constants.sysConfigMap.get(companyCode).get("bdsendrecord.type");
        	
			
			if("1".equals(type)){
				
				if("1".equals(bdsendrecordType)){
					
					
					
							condition="  where h.User_Code = b.User_Code and h.active = '0' and h.company_code='"+companyCode+"'   and h.exit_date is null and h.remittance_money > 0 and h.freeze_status=0 and h.send_date is null " ;

							if("0".equals(app)){
					        	if(StringUtil.isDouble(startAllotMoney)){
					        		condition+=" And b.Bonus_Balance >= "+startAllotMoney+" ";
					        	}else{
					        		condition+=" And b.Bonus_Balance > 0 ";
					        	}
					        	condition+=" and b.flag = '"+app+"' and b.status='0' ";
					        	if(StringUtil.isDouble(endAllotMoney)){
					        		condition+=" And b.Bonus_Balance <= "+endAllotMoney+" ";
					        	}else{
					        		//condition+=" )";
					        	}
				        	}else{
					        	condition+=" And Exists (Select 1 From Jbd_Bonus_Balance b Where h.User_Code = b.User_Code And b.Bonus_Balance > 0 and b.flag='"+app+"' and b.status='0' ";
					        	
					        	if(StringUtil.isDate(startDate)){
					        		condition+=" and b.flag_time >= to_date('"+startDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ";
					        	}

					        	if(StringUtil.isDate(endDate)){
					        		condition+=" and b.flag_time <= to_date('"+endDate+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ";
					        	}
					        	condition+=" ) ";
					        	
				        	}
							

								
							
							sql = "select h.member_level,h.freeze_status,h.card_type,h.w_week,h.user_code,h.name,h.bank,h.bankaddress,h.bankbook,h.bankcard,h.remittance_money,h.register_status,h.reissue_status,h.member_type" +
									" from jbd_send_record_note h , Jbd_Bonus_Balance b" + condition;
									

				}else if("2".equals(bdsendrecordType)){

					//开始日志--start
					Long refId1 =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "bdSendRecordAllotReport1", "");
					//开始日志--end
					
					int kk = 2;

					int totalCountRs=0;

//					long time=new Date().getTime();
					
					List jbdBonusBalances=jbdSendRecordHistManager.getJbdBonusBalanceUserCodes(startAllotMoney, endAllotMoney, app, "0");

//					System.out.println("jbd_bonus_balance 表耗时："+(new Date().getTime()-time)/1000);
					
					
					for (int j = 0; j < jbdBonusBalances.size(); j++) {
						Map jbdBonusBalanceMap=(Map) jbdBonusBalances.get(j);
						String userCode=jbdBonusBalanceMap.get("user_code").toString();

//						time=new Date().getTime();
						List curBonus=jbdSendRecordHistManager.getJbdSendRecordsByUserCode(userCode, companyCode,"");
//						System.out.println("jbd_send_record 表耗时： 会员:" + userCode+" : " +(new Date().getTime()-time)/1000);
						
						
						for (int k = 0; k < curBonus.size(); k++) {
							Map bonusMap=(Map) curBonus.get(k);
							String name = (String) bonusMap.get("name");
							String cardType =(String) bonusMap.get("card_type");
							String member_level =(String) bonusMap.get("member_level");
							String bName =(String)bonusMap.get("bankbook");
							String bNum =(String) bonusMap.get("bankcard");
							String openBank =(String) bonusMap.get("bank");
							String bankaddress = (String)bonusMap.get("bankaddress");
							String memberType =(String) bonusMap.get("member_type");
							if(!StringUtil.isEmpty(memberType)){
								memberType=memberType.trim();
							}
							String registerStatus = (String) bonusMap.get("register_status");
							String wWeek = (String) bonusMap.get("w_week");
							
							
							
							Double remittanceMoney = StringUtil.formatDouble(bonusMap.get("remittance_money").toString());
							totalRemittanceMoney += remittanceMoney;
			
							int index=0;
							
							if(kk%sp==0){
								if(kk>0){
									wsheet = wwb.createSheet("Sheet"+(si+1), si);
									kk=0;
									si++;
								}
							}

							eu.addNumber(wsheet, index++, kk, StringUtil.formatInt(WeekFormatUtil.getFormatWeek("w",wWeek)));
							
							eu.addString(wsheet, index++, kk, userCode);

							eu.addString(wsheet, index++, kk, name);
							
							eu.addString(wsheet, index++, kk, cardTypeMap.get(cardType)==null?"":LocaleUtil.getLocalText(cardTypeMap.get(cardType).toString()));
							
							eu.addString(wsheet, index++, kk, memberLevelMap.get(member_level+"")==null?"":LocaleUtil.getLocalText(memberLevelMap.get(member_level+"").toString()));

							eu.addString(wsheet, index++, kk, bankaddress);
							
							eu.addString(wsheet, index++, kk, bName);
					
							eu.addString(wsheet, index++, kk, bNum);
							
							eu.addString(wsheet, index++, kk, openBank);

							if("1".equals(registerStatus)){
								eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText("busi.bd.waitStatus"));
							}else if("3".equals(registerStatus)){
								eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText("bdSendRecord.ReissueStatus2"));
							}
							eu.addNumber(wsheet, index++, kk, remittanceMoney);
							
							eu.addString(wsheet, index++, kk, memberTypeMap.get(memberType)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(memberType).toString()));

							kk++;
							totalCountRs++;
							
							
							
							
						}
						
						
						
						
					}
					

					eu.addString(wsheet, 1, kk, totalCountRs+LocaleUtil.getLocalText("bdSendRecordToBankReport.countCH"));
					int footerIndex=9;

					eu.addString(wsheet, footerIndex++, kk,  LocaleUtil.getLocalText("report.allTotal")+":");
			
					eu.addNumber(wsheet, footerIndex++, kk, totalRemittanceMoney);
		

					eu.writeExcel(wwb);
					eu.closeWritableWorkbook(wwb);
					os.close();

					//结束日志--start
					ReportLogUtil.endReport(refId1, "bdSendRecordAllotReport1");
					//结束日志--end
					return null;
					
				}
				
		
				
				
				
			}else if("0".equals(type)){
				condition+=" where 1=1 and b.flag='"+app+"' and b.status='0' and m.company_code='"+companyCode+"' ";
				
				if("0".equals(app)){
					if(StringUtil.isDouble(startAllotMoney)){
						condition+=" and b.bonus_balance >="+startAllotMoney;
					}else{
						condition+=" and b.bonus_balance > 0";
					}
					if(StringUtil.isDouble(endAllotMoney)){
						condition+=" And b.Bonus_Balance <= "+endAllotMoney+" ";
					}
				}else{
					
		        	if(StringUtil.isDate(startDate)){
		        		condition+=" and b.flag_time >= to_date('"+startDate+" 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ";
		        	}

		        	if(StringUtil.isDate(endDate)){
		        		condition+=" and b.flag_time <= to_date('"+endDate+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ";
		        	}
		        	
		        	
				}

				sql="select m.member_level,b.flag_time,b.user_code,m.card_type,m.mi_user_name as name, m.bank, m.bankaddress,m.bankcard,m.bankbook,b.bonus_balance as remittance_money,m.member_type " +
						"from jbd_bonus_balance b left join jmi_member m on b.user_code = m.user_code "+ condition;
			}
    		sql+=" order by user_code ";

			//开始日志--start
			Long refId2 =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "bdSendRecordAllotReport2", "");
			//开始日志--end
			
    		this.jdbcTemplateReport.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						int totalCountRs=0;
						while (rs.next()) {
							String userCode = rs.getString("user_code");
							String name = rs.getString("name");
							String cardType = rs.getString("card_type");
							String member_level = rs.getString("member_level");
							String bName = rs.getString("bankbook");
							String bNum = rs.getString("bankcard");
							String openBank = rs.getString("bank");
							String bankaddress = rs.getString("bankaddress");
							String memberType = rs.getString("member_type");
							
							String flag_time="";
							if("1".equals(app)){
								flag_time = rs.getString("flag_time");
							}
							
							Integer wWeek=null;
							String registerStatus ="";
							if("1".equals(type)){
								wWeek = rs.getInt("w_week");
								registerStatus = rs.getString("register_status");
							}
			
							
							
							Double remittanceMoney = rs.getDouble("remittance_money");
							totalRemittanceMoney += remittanceMoney;
			
							int index=0;
							if(kk%sp==0){
								if(kk>0){
									wsheet = wwb.createSheet("Sheet"+(si+1), si);
									kk=0;
									si++;
								}
							}
							
							
							if("1".equals(type)){
								eu.addNumber(wsheet, index++, kk, wWeek);
							}
			
							eu.addString(wsheet, index++, kk, userCode);

							eu.addString(wsheet, index++, kk, name);
							
							eu.addString(wsheet, index++, kk, cardTypeMap.get(cardType)==null?"":LocaleUtil.getLocalText(cardTypeMap.get(cardType).toString()));
							
							
							eu.addString(wsheet, index++, kk, memberLevelMap.get(member_level+"")==null?"":LocaleUtil.getLocalText(memberLevelMap.get(member_level+"").toString()));


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

							if("1".equals(app)){
								eu.addString(wsheet, index++, kk, flag_time);
							}
							kk++;
							totalCountRs++;
						} 
						eu.addString(wsheet, 1, kk, totalCountRs+LocaleUtil.getLocalText("bdSendRecordToBankReport.countCH"));
						int footerIndex=0;
						if("1".equals(type)){
							footerIndex=9;
						}else if("0".equals(type)){
							footerIndex=7;
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

			//结束日志--start
			ReportLogUtil.endReport(refId2, "bdSendRecordAllotReport2");
			//结束日志--end
			return null;
		}

		return new ModelAndView(getSuccessView());
	}



}