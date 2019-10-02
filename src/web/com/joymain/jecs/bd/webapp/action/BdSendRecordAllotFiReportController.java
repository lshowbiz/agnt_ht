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
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
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

public class BdSendRecordAllotFiReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}


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
	private Map cardTypeMap=null;
	private String type=null;
	private String app=null;
	private Map memberTypeMap=null;
	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public BdSendRecordAllotFiReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        request.setAttribute("startAllotMoney", 500);

        RequestUtil.freshSession(request,"bonusSendFiReport", Constants.COMPANY_TIME);
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "bonusSendFiReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			if(RequestUtil.saveOperationSession(request, "bonusSendFiReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:bdSendRecordAllotFiReport.html");
	       	 }
			company=companyCode;
			//鐢熸垚excel鏂囦欢
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=pick_cash_Bank_"+companyCode+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//鍦ㄦ鍒涘缓鐨勬柊excel鏂囦欢鍒涘缓涓�宸ヤ綔琛� 
			wsheet = wwb.createSheet("Sheet1", 0);

			type=request.getParameter("type");

			String startDate=request.getParameter("startDate");
        	String endDate=request.getParameter("endDate");

			eu.addString(wsheet, 0, 0, startDate+""+"~"+endDate+"");
			
			//鏍囬
			int i=0;
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.cardType"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.bankProvince"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.bankCity"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRemittanceReport.openCityCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNameCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNumCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRemittanceReport.openBankCH"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.finance.amount"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("member.memberType"));
			

			if("n".equals(type)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bd.app.time"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.registerStatus"));
			}

			
		
			totalRemittanceMoney = new Double(0);

			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(companyCode);
        	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
        	alCountry.getAlStateProvinces().iterator();
			

			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			//璁剧疆杩涘害鏉℃�昏褰曟暟

			String sql="";
			

			//type a 鍚堣 n 鏄庣粏

        	String lefjoin=" left join jmi_member m on m.user_code = "+type+".user_code ";
        	
        	String condition="  where n.send_date is null and n.register_status != '4'";
        	
        	
        	String timeSql="";
        	if(!StringUtil.isEmpty(startDate)){
        		timeSql+=" and n.create_time >= to_date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') ";
        	}

        	if(!StringUtil.isEmpty(endDate)){
        		timeSql+=" and n.create_time <= to_date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') ";
        	}
        	
        	
			sql+="select "+type+".user_code,m.mi_user_name,m.card_type,m.bank_province,m.bank_city ,m.bank,m.bankaddress,m.bankbook,m.bankcard,"+type+".remittance_money,m.member_type";
			
			
			if("a".equals(type)){
				sql+="  from (select sum(n.remittance_money) as remittance_money,sum(fee) as fee,n.user_code from jbd_send_note n ";

				sql+=condition;
				sql+=timeSql;
				sql+=" group by n.user_code) a ";
				sql+=lefjoin;
			}else{
				sql+=", n.create_time,n.register_status from jbd_send_note n";
				sql+=lefjoin;
				sql+=condition;
				sql+=timeSql;
			}
			
			
			
			
    		this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						int totalCountRs=0;
						while (rs.next()) {
							String userCode = rs.getString("user_code");
							String mi_user_name = rs.getString("mi_user_name");
							String cardType = rs.getString("card_type");
							String bName = rs.getString("bankbook");
							String bNum = rs.getString("bankcard");
							String openBank = rs.getString("bank");
							String bankaddress = rs.getString("bankaddress");
							String bank_province= rs.getString("bank_province");
							String bank_city= rs.getString("bank_city");

							Double remittanceMoney = rs.getDouble("remittance_money");
							
							String memberType = rs.getString("member_type");
							if(!StringUtil.isEmpty(memberType)){
								memberType=memberType.trim();
							}
							totalRemittanceMoney += remittanceMoney;
							
							
							
							String create_time="";
							String registerStatus="";
							if("n".equals(type)){
								create_time = rs.getString("create_time");
								registerStatus = rs.getString("register_status");
							}
							
						
							
			
							int index=0;
			
							eu.addString(wsheet, index++, kk, userCode);

							eu.addString(wsheet, index++, kk, mi_user_name);
							
							eu.addString(wsheet, index++, kk, cardTypeMap.get(cardType)==null?"":LocaleUtil.getLocalText(cardTypeMap.get(cardType).toString()));


							String alStateProvinceStr="";
							 if(!StringUtil.isEmpty(bank_province)){

									AlStateProvince alStateProvince=alStateProvinceManager.getAlStateProvince(bank_province);
									if(alStateProvince!=null){
										alStateProvinceStr=alStateProvince.getStateProvinceName();
									}
							 }
							eu.addString(wsheet, index++, kk,alStateProvinceStr);
							
							

							String alCityStr="";

							 if(!StringUtil.isEmpty(bank_city)){

									AlCity alCity=alCityManager.getAlCity(bank_city);
									if(alCity!=null){
										alCityStr=alCity.getCityName();
									}
							 }
							eu.addString(wsheet, index++, kk,alCityStr);
							
							
							
							eu.addString(wsheet, index++, kk, bankaddress);
							
							eu.addString(wsheet, index++, kk, bName);
					
							eu.addString(wsheet, index++, kk, bNum);
							
							eu.addString(wsheet, index++, kk, openBank);

							eu.addNumber(wsheet, index++, kk, remittanceMoney);
							
							eu.addString(wsheet, index++, kk, memberTypeMap.get(memberType)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(memberType).toString()));

							if("n".equals(type)){
								eu.addString(wsheet, index++, kk, create_time);
								
								if("1".equals(registerStatus)){
									eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText("busi.bd.waitStatus"));
								}else if("3".equals(registerStatus)){
									eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText("bdSendRecord.ReissueStatus2"));
								}
							}
							kk++;
							totalCountRs++;
						} 
						eu.addString(wsheet, 1, kk, totalCountRs+LocaleUtil.getLocalText("bdSendRecordToBankReport.countCH"));
						int footerIndex=0;
						footerIndex=8;
				
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