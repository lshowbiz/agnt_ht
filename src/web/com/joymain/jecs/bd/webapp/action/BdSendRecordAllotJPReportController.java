package com.joymain.jecs.bd.webapp.action;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdSendRecordAllotJPReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	private StringBuffer buffer =null;

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	} 
	private JdbcTemplate jdbcTemplate = null;

	private static final String CHARSET_CODE = "UTF-8"; 
	private static final String REP_STR = " ";
	
	private Double totalBonusPv = null;
	private BigDecimal totalRemittanceMoney = null;
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private AlCountry alCountry = new AlCountry();
	private Map cardTypeMap=null;
	private String type=null;
	private String app=null;
	private Map memberTypeMap=null;
	private String year="";
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public BdSendRecordAllotJPReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        String amountSend = ConfigUtil.getConfigValue(defSysUser.getCompanyCode(), "amount.send");
        
        request.setAttribute("startAllotMoney", amountSend);

		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "bonusSendJPReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			
//			try {
//				log.info("exportEDI=" + getSendInfoBuffer(list).toString());
//			} catch (Exception e) {
//				log.info("exportEDI,erro=" + e);
//			}
			
			String remittanceDate=request.getParameter("remittanceDate");
			
			if(!StringUtil.isDate(remittanceDate)){
				this.saveMessage(request, this.getText("please.input.search.condition"));
				return showForm(request,response,errors);
			}
			
			//写内容
			totalRemittanceMoney = new BigDecimal(0);

			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(companyCode);
        	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
        	alCountry.getAlStateProvinces().iterator();
			

			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			//设置进度条总记录数
			String condition="";
			String sql="";
			

			response.reset();
			response.setContentType("application/x-msdownload"); // download
			response.setHeader("Content-Disposition","attachment;   filename=EDI.txt");
			OutputStream out = response.getOutputStream();
			
//			year=remittanceDate.split("-")[0];
        	

			String startAllotMoney=request.getParameter("startAllotMoney");
        	String endAllotMoney=request.getParameter("endAllotMoney");
			
			
				condition+=" where 1=1 and b.flag='0' and b.status='0' and m.company_code='"+companyCode+"' ";

				if(StringUtil.isInteger(startAllotMoney)){
					condition+=" and b.bonus_balance >="+startAllotMoney;
				}else{
					condition+=" and b.bonus_balance > 0";
				}
				if(StringUtil.isInteger(endAllotMoney)){
					condition+=" And b.Bonus_Balance <= "+endAllotMoney+" ";
				}
				
				sql="select b.flag_time,b.user_code,m.card_type,m.mi_user_name as name, m.bank, m.bankaddress,m.bankcard,m.bankbook,trunc(b.bonus_balance,2) as remittance_money,m.member_type " +
						"from jbd_bonus_balance b left join jmi_member m on b.user_code = m.user_code "+ condition;
				
    		sql+=" order by user_code ";
    		
    		buffer = new StringBuffer("");

 
	    	
	    	DateFormat dateFormat = new SimpleDateFormat("MMdd");

			byte[] return_line = { Character.LINE_SEPARATOR,
					Character.LETTER_NUMBER };

			
			
			//header
			String headerLine="109"+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 3)+"17063239"+"ﾁﾕｳﾐﾔｸｼﾞﾔﾊﾟﾝ(ｶ"+
			StringUtil.getFLString("", CHARSET_CODE, REP_STR , 26)+DateUtil.getDateTime("MMdd",DateUtil.convertStringToDate("yyyy-MM-dd", remittanceDate))+"9900"+
			"ﾕｳﾁﾖｷﾞﾝｺｳ"+StringUtil.getFLString("", CHARSET_CODE, REP_STR , 6+20+6+17);
			buffer.append(headerLine);
			buffer.append(new String(return_line));

    		
    		this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int totalCountRs=0;
						int kk=1;
						while (rs.next()) {
							String userCode = rs.getString("user_code");
							String name = rs.getString("name");
							String cardType = rs.getString("card_type");
							String bName = rs.getString("bankbook");
							String bNum = rs.getString("bankcard");
							String openBank = rs.getString("bank");
							String bankaddress = rs.getString("bankaddress");
							String memberType = rs.getString("member_type");
							Double remittanceMoney = rs.getDouble("remittance_money");

							if(!StringUtil.isEmpty(bNum) && bNum.length() > 7 ){
								bNum=bNum.substring(0,7);
							}
						
							
							if(!StringUtil.isEmpty(bankaddress)&&bankaddress.length()==5){
								bankaddress=bankaddress.substring(1, 4);
							}
							
							
							
							int bNameLength=0;
							if(!StringUtil.isEmpty(bName)){
								bNameLength=bName.length();
							}
							
							BigDecimal bonus=(new BigDecimal(remittanceMoney).setScale(0, BigDecimal.ROUND_HALF_UP)).subtract(new BigDecimal("150"));
							totalRemittanceMoney=totalRemittanceMoney.add(bonus);
							
							String dataLine="29900"+"ﾕｳｾｲｼﾖｳﾁﾖｷﾝｷﾖｸ"+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 1)+bankaddress+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 20)+bNum+
							bName+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 30-bNameLength)+StringUtil.getPreFLString(bonus.toString(), CHARSET_CODE, "0", 10)+"1"+
							StringUtil.getPreFLString("", CHARSET_CODE, "0", 19)+"1"+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 1)+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 6)+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 2);

							byte[] return_line = { Character.LINE_SEPARATOR,
									Character.LETTER_NUMBER };
							
							
							buffer.append(dataLine);
							buffer.append(new String(return_line));
							
							
							

							

				    		
							kk++;
							totalCountRs++;
						}

			    		

						byte[] return_line = { Character.LINE_SEPARATOR,
								Character.LETTER_NUMBER };
						

						//trailer
						
						String trailerLine="8"+StringUtil.getPreFLString(totalCountRs+"", CHARSET_CODE, "0", 6)+StringUtil.getPreFLString(totalRemittanceMoney+"", CHARSET_CODE, "0", 12)+
						StringUtil.getFLString("", CHARSET_CODE, "0", 6)+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 95);
						buffer.append(trailerLine);
						buffer.append(new String(return_line));
						
						
						//end 
						String endLine="9"+StringUtil.getFLString("", CHARSET_CODE, REP_STR, 119);
						buffer.append(endLine);
						
						
			    		
					} catch (Exception e) {
						e.printStackTrace();
					}finally {  
						JdbcUtils.closeResultSet(rs);
					}
					return null;
				}
			});
    		
    		
    		
    		

			BufferedWriter bufWriter = new BufferedWriter(
					new OutputStreamWriter(out, CHARSET_CODE));
			
			bufWriter.write(buffer.toString());
			bufWriter.flush();
			bufWriter.close();
			out.close();
			return null;

		}

		return new ModelAndView(getSuccessView());
	}


}