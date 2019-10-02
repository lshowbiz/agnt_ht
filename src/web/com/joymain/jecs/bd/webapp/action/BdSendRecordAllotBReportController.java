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
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdSendRecordAllotBReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	private JmiRecommendRefManager jmiRecommendRefManager;

	public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
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
	private Map memberTypeMap=null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public BdSendRecordAllotBReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);

        request.setAttribute("startAllotMoney", 500);

		String leaderStr=(String) Constants.sysConfigMap.get("AA").get("bd.leader.member");
		String []leaders=leaderStr.split(",");
        request.setAttribute("leaders", leaders);
		
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "bonusSendBReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
//			final String formatedWeek = request.getParameter("formatedWeek");
			
			//鐢熸垚excel鏂囦欢
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate B Report(Members Vs Commissions) to Bank_"+companyCode+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			//鍦ㄦ鍒涘缓鐨勬柊excel鏂囦欢鍒涘缓涓�宸ヤ綔琛� 
			wsheet = wwb.createSheet("Sheet1", 0);

			type=request.getParameter("type");
			
			
			
			//鏍囬
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
			//鍐欏唴瀹�
			totalBonusPv = new Double(0);
			totalRemittanceMoney = new Double(0);

			AlCompany alCompany = alCompanyManager.getAlCompanyByCode(companyCode);
        	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
        	alCountry.getAlStateProvinces().iterator();
			

			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			//璁剧疆杩涘害鏉℃�昏褰曟暟
			String condition="";
			String sql="";
			
			String startAllotMoney=request.getParameter("startAllotMoney");
        	String endAllotMoney=request.getParameter("endAllotMoney");
        	String leader=request.getParameter("leader");
			String leaderIndex="";
        	if(!StringUtil.isEmpty(leader)){
        		leaderIndex=((JmiRecommendRef)jmiRecommendRefManager.getJmiRecommendRef(leader)).getTreeIndex();
        	}
        	
			if("1".equals(type)){
				condition="  where h.active = '0' and h.company_code='"+companyCode+"'  and h.card_type != '0' and h.exit_date is null and h.remittance_money > 0 and send_date is null " ;
				
	        	
	        	if(StringUtil.isInteger(startAllotMoney)){
	        		condition+=" And Exists (Select 1 From Jbd_Bonus_Balance b Where h.User_Code = b.User_Code And b.Bonus_Balance >= "+startAllotMoney+" ";
	        	}else{
	        		condition+=" And Exists (Select 1 From Jbd_Bonus_Balance b Where h.User_Code = b.User_Code And b.Bonus_Balance > 0   ";
	        	}
	        	if(StringUtil.isInteger(endAllotMoney)){
	        		condition+=" And b.Bonus_Balance <= "+endAllotMoney+" )";
	        	}else{
	        		condition+=" )";
	        	}
				if(!StringUtil.isEmpty(leaderIndex)){
					condition+=" and exists (select 1 from jmi_recommend_ref r where r.user_code=h.user_code and  r.tree_index like '"+leaderIndex+"%')";
				}

				
				
					
				
				sql = "select h.card_type,h.w_week,h.user_code,h.name,h.bank,h.bankaddress,h.bankbook,h.bankcard,h.remittance_money,h.register_status,h.reissue_status,m.member_type" +
						" from jbd_send_record_hist h left join jmi_member m on h.user_code = m.user_code " + condition;
			}else if("0".equals(type)){
				condition+=" where 1=1  and m.company_code='"+companyCode+"' ";
				if(StringUtil.isInteger(startAllotMoney)){
					condition+=" and b.bonus_balance >="+startAllotMoney;
				}else{
					condition+=" and b.bonus_balance > 0";
				}
				if(StringUtil.isInteger(endAllotMoney)){
					condition+=" And b.Bonus_Balance <= "+endAllotMoney+" ";
				}
				if(!StringUtil.isEmpty(leaderIndex)){
					condition+=" and exists (select 1 from jmi_recommend_ref r where r.user_code=b.user_code and  r.tree_index like '"+leaderIndex+"%')";
				}

				sql="select b.user_code,m.card_type,m.mi_user_name as name, m.bank, m.bankaddress,m.bankcard,m.bankbook,b.bonus_balance as remittance_money,m.member_type " +
						"from jbd_bonus_balance b left join jmi_member m on b.user_code = m.user_code "+ condition;
			}
    		sql+=" order by user_code ";
			
			
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
							Integer wWeek=null;
							String registerStatus ="";
							if("1".equals(type)){
								wWeek = rs.getInt("w_week");
								registerStatus = rs.getString("register_status");
							}
			
							
							
							Double remittanceMoney = rs.getDouble("remittance_money");
							totalRemittanceMoney += remittanceMoney;
			
							int index=0;
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
							
							kk++;
							totalCountRs++;
						} 
						eu.addString(wsheet, 1, kk, totalCountRs+LocaleUtil.getLocalText("bdSendRecordToBankReport.countCH"));
						int footerIndex=0;
						if("1".equals(type)){
							footerIndex=8;
						}else if("0".equals(type)){
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