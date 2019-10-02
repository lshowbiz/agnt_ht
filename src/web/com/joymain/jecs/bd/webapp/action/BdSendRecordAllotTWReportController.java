package com.joymain.jecs.bd.webapp.action;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdSendRecordAllotTWReportController extends BaseFormController {
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

	private static final String CHARSET_CODE = "BIG5";
	private static final String REP_STR = " ";
	
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
	private String year="";
	private String remittanceDate="";
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public BdSendRecordAllotTWReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        request.setAttribute("startAllotMoney", 500);

		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "bonusSendTWReport".equalsIgnoreCase(request.getParameter("strAction"))) {
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

			
			//写内容
			totalBonusPv = new Double(0);
			totalRemittanceMoney = new Double(0);

			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(companyCode);
        	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
        	alCountry.getAlStateProvinces().iterator();
			

			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			//设置进度条总记录数
			String condition="";
			String sql="";
			
        	
			String companyName=request.getParameter("companyName");
			remittanceDate=request.getParameter("remittanceDate");
			
			if(StringUtil.isEmpty(companyName)||!StringUtil.isDate(remittanceDate)){
				this.saveMessage(request, this.getText("please.input.search.condition"));
				return showForm(request,response,errors);
			}

			response.reset();
			response.setContentType("application/x-msdownload"); // download
			response.setHeader("Content-Disposition","attachment;   filename=EDI.txt");
			OutputStream out = response.getOutputStream();
			
			year=remittanceDate.split("-")[0];
        	

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
    		//2010-1911=99民国
    		

			byte[] return_line = { Character.LINE_SEPARATOR,
					Character.LETTER_NUMBER };
    		
    		String line1=StringUtil.getFLString("B"+StringUtil.getPreFLString((new Integer(year)-1911)+"", CHARSET_CODE, "0", 3)+DateUtil.dateStrFormat(remittanceDate, "yyyy-MM-dd", "MMdd"), CHARSET_CODE, REP_STR, 0);
    		line1+=StringUtil.getFLString(companyName, CHARSET_CODE, REP_STR, 76);
    		line1+=StringUtil.getFLString("", CHARSET_CODE, "0", 37);
    		line1+=StringUtil.getFLString("", CHARSET_CODE, REP_STR, 99);

    		buffer.append(line1);
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

							totalRemittanceMoney += remittanceMoney;
							
							String bankSn="";
							if(StringUtil.isEmpty(openBank)){
								bankSn="000";
								
							}else{
								bankSn=openBank.split(" ")[0];
								if(!StringUtil.isInteger(bankSn)){
									bankSn="000";
								}
								
							}
							
							
							
							if(kk==1000){
								kk=1;
							}
							String sn=StringUtil.getPreFLString(kk+"", CHARSET_CODE, "0", 3);
							
							String line="";
							
							line+=StringUtil.getFLString("2"+StringUtil.getPreFLString((new Integer(year)-1911)+"", CHARSET_CODE, "0", 3)+DateUtil.dateStrFormat(remittanceDate, "yyyy-MM-dd", "MMdd"), CHARSET_CODE, REP_STR, 0);
							line+=StringUtil.getFLString(name, CHARSET_CODE, REP_STR, 76);
							line+=sn;
							
							if(StringUtil.isEmpty(bankaddress)){
								line+=StringUtil.getFLString(bankSn, CHARSET_CODE, "0", 7);//解款行 暂时补4个零
							}else{
								line+=StringUtil.getFLString(bankSn+bankaddress, CHARSET_CODE, "0", 7);//
							}
							
							
							if(StringUtil.isEmpty(bNum)){
								line+=StringUtil.getFLString("", CHARSET_CODE, "0", 14);
							}else{

								if(!StringUtil.isEmpty(openBank)){
									String curOpenBank[] = openBank.split(" ");
									if(curOpenBank.length==2){
										if("005 土地銀行".equals(openBank.split(" ")[1])){
											line+=StringUtil.getFLString(bNum, CHARSET_CODE, "0", 14);
										}else{
											line+=StringUtil.getPreFLString(bNum, CHARSET_CODE, "0", 14);
										}
									}else{
										line+=StringUtil.getFLString("", CHARSET_CODE, "0", 14);
									}
								}else{
									line+=StringUtil.getFLString("", CHARSET_CODE, "0", 14);
								}
								
							}
							
							line+=StringUtil.getPreFLString(convertRemittanceMoney(remittanceMoney), CHARSET_CODE, "0", 13);
							
							line+=StringUtil.getFLString("", CHARSET_CODE, REP_STR, 76);
							line+=StringUtil.getFLString("", CHARSET_CODE, REP_STR, 23);
							
							
							

							byte[] return_line = { Character.LINE_SEPARATOR,
									Character.LETTER_NUMBER };
							

				    		buffer.append(line);
				    		buffer.append(new String(return_line));
				    		
							kk++;
							totalCountRs++;
						}

			    		String line2=StringUtil.getFLString("3", CHARSET_CODE, REP_STR, 1);
			    		line2+=StringUtil.getPreFLString(""+totalCountRs, CHARSET_CODE, "0", 7);
						line2+=StringUtil.getFLString("", CHARSET_CODE, REP_STR, 76);
						line2+=StringUtil.getFLString("", CHARSET_CODE, "0", 24);

						line2+=StringUtil.getPreFLString(convertRemittanceMoney(totalRemittanceMoney), CHARSET_CODE, "0", 13);
						line2+=StringUtil.getFLString("", CHARSET_CODE, REP_STR, 99);

			    		buffer.append(line2);
			    		
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

	private String convertRemittanceMoney(Double remittanceMoney){
		
		 String pattern = "#0.00";//格式代码，".000"代表保留三位小数，是0的输出0
		  DecimalFormat formatter = new DecimalFormat();
		  formatter.applyPattern(pattern);
		  String res=formatter.format(remittanceMoney);
		  res=res.replaceAll("[.]","");
		  
		return res;
	}

}