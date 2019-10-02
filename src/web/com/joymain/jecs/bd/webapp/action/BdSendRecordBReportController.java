package com.joymain.jecs.bd.webapp.action;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
import com.joymain.jecs.mi.service.JmiMemberManager;
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
public class BdSendRecordBReportController extends BaseFormController {
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
	private WritableWorkbook wwb = null;
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
	private Double servicePvTotal=null;
	private Double monthConsumerPvTotal = null;//重消类业绩合计
	private Double orderPvTotal = null;//报单奖PV合计
	private int progressCurrentCount;
	private String company=null;
	private HttpServletRequest innerRequest;
	private AlCountry alCountry = new AlCountry();
	private Map memberTypeMap=null;
	private Map storeTypeMap=null;
	private Map identityTypeMap=null;
	private Map freezeStatusMap=null;
	private Map memberLevelMap=null;
	private Map cardTypeMap=null;
	private Boolean flag=false;
	private Map passStarMap=null;
	private Map<String,String> xzMap=null;
	private Map yesNoMap=null;
		
	private int si = 1;//工作蒲码数
	private static final int sp = 60000;//一页放多少条记录

	//改为jdbcTemplateReport
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public BdSendRecordBReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		

        RequestUtil.freshSession(request,"bdSendRecordBReport", Constants.COMPANY_TIME);
        
		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "bdSendRecordBReport".equalsIgnoreCase(request.getParameter("strAction"))) {
			


			if(RequestUtil.saveOperationSession(request, "bdSendRecordBReport", Constants.COMPANY_TIME)!=0){
	       		  return new ModelAndView("redirect:bdSendRecordBReport.html");
	       	 }
			
			//201607期之后的奖金报表导出
			if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",request.getParameter("formatedWeek")))>=201607){
				exportStartWeek201607(request, response);
				return null;
        	}
			
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			final String formatedWeek = request.getParameter("formatedWeek");
			
			//生成excel文件
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions) B_"+formatedWeek+"_"+companyCode+".xls");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=30" ); 
			OutputStream os = response.getOutputStream();
			eu = new ExcelUtil();
			wwb = Workbook.createWorkbook(os);
			//在此创建的新excel文件创建一工作表 
			wsheet = wwb.createSheet("Sheet1", 0);
			//加入期别时间段显示
			
			BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",formatedWeek));
			eu.addString(wsheet, 0, 0, formatedWeek);
			eu.addString(wsheet, 1, 0, bdPeriod.getStartTime()+" - "+bdPeriod.getEndTime());
			//标题
			final int pointWeek=201037;
			int i=0;
			

        	int newWeek=StringUtil.formatInt((String) Constants.sysConfigMap.get("AA").get("new.week"));
        	//boolean flag=false;
        	if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek))>=newWeek){
        		flag=true;
        	}
			
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.passStar"));
			eu.addString(wsheet, i++, 1, "销售级别");
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("sysDataLog.changeType"));
			eu.addString(wsheet, i++, 1, "行政级别");
			
			if(!"HK".equals(companyCode)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("franchise.moeny.pv"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("franchise.moeny"));
			}
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("consumer.amount"));
			
			if(!flag){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.ventureSalesPv"));//创业销售
			}
			 
			if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) < pointWeek){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.ventureLeaderPv"));
			}else{

				if(flag){
					eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.ventureLeaderPv37.pv.jicha"));//级差奖
				}else{
					eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.ventureLeaderPv37.pv"));//感恩奖
				}
				
			}
			if(flag){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.successLeaderPv.pv.daishu"));//代数奖 改名为 销售奖
				
				if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek))>=201532){
					eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdBonus.zyBonus"));
				}else{
					eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.successSalesPv.pv.leaderPv"));//卓越领导奖 改名为 领导奖
				}
				
				
			}
			
			if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) < pointWeek){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.successSalesPv"));//成功销售
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.successLeaderPv"));//成功领导
			}

			if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) >= pointWeek){
				if(!flag){
					eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.ventureFund"));//创业扶持奖
				}
			}
			
			if(flag){

				if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek))>=201532){
					eu.addString(wsheet, i++, 1, "推广奖(PV)");
				}else if("201516".equals(WeekFormatUtil.getFormatWeek("f",formatedWeek))){
					eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdBonus.zyBonus"));
				}else{
					eu.addString(wsheet, i++, 1, "新人奖(pv)");
				}
			}else{
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdBonusReport.recommendBonus"));
			}

			if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) >= 201604){
				eu.addString(wsheet, i++, 1, "服务奖(pv)");//店铺拓展奖
			}
			

			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.storeExpandPv"));//店铺拓展奖
			
			if(!flag){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.storeServePv"));//店铺服务奖
			}
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.storeRecommendPv"));//店铺推荐奖
			
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.deductMoney"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("jbdMemberLinkCalcHist.deductTax"));

			if("CN".equals(company)||"AA".equals(company)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.networkMoney"));
			}

			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bd.totalDev"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bd.deductedDev"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bd.currentDev"));
			
			
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdNetWorkCostReport.rateCH"));
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bd.send.bounspv"));
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bd.send.bounspv")+"+"+LocaleUtil.getLocalText("franchise.moeny.pv"));
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.getBonusMoney"));

			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdSendRecord.bonusMoney"));
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("member.memberType"));
			
			eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.freezestatus"));

			if("AA".equals(company)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.poMemberOrder.company"));
			}
			if("TW".equals(company)){
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.identityType"));
			}
			//写内容
			franchisePvTotal = new Double(0);
			franchiseMoneyTotal = new Double(0);
			consumerAmountTotal = new Double(0);
			ventureSalesPvTotal = new Double(0);
			ventureLeaderPvTotal = new Double(0);
			successSalesPvTotal = new Double(0);
			successLeaderPvTotal = new Double(0);
			deductMoneyTotal = new Double(0);
			deductTaxTotal = new Double(0);
			bounsPvTotal = new Double(0);
			bounsMoneyTotal = new Double(0);
			sendMoneyTotal = new Double(0);
			recommendBonusPvTotal=new Double(0);
			ventureFundPvTotal=new Double(0);

			storeExpandPvTotal = new Double(0);
			storeServePvTotal =new Double(0);
			storeRecommendPvTotal =new Double(0);
			networkMoneyTotal=new Double(0);
			
			bounsFranchisePvTotal=new Double(0);
			
			totaldevTotal = new Double(0);
			deductedDevTotal = new Double(0);
			currentDevTotal = new Double(0);
			servicePvTotal = new Double(0);
			
			
			cardTypeMap=ListUtil.getListOptions(companyCode, "bd.cardtype");
			memberLevelMap=ListUtil.getListOptions(companyCode, "member.level");
			memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
			storeTypeMap=ListUtil.getListOptions(companyCode, "isstore");
			identityTypeMap=ListUtil.getListOptions(companyCode, "identitytype.tw");
			freezeStatusMap=ListUtil.getListOptions(companyCode, "mimember.freezestatus");
			passStarMap=ListUtil.getListOptions(companyCode, "pass.star.zero");
			//设置进度条总记录数
			String condition="where w_week ="+WeekFormatUtil.getFormatWeek("f",formatedWeek);
			if(!"AA".equals(company)){
				condition+=" and b.company_code='"+companyCode+"' ";
			}
			condition+="  order by b.user_code ";
			
			String sql = "select b.*,m.member_type from jbd_bonus_report_b b left join jmi_member m on m.user_code=b.user_code " + condition;
			
			List list=jdbcTemplate.queryForList("select * from jpo_zcw_member");
			xzMap=new HashMap<String,String>();
			for (int j = 0; j < list.size(); j++) {
				Map map=(Map) list.get(j);
				String zcw_deptname=map.get("zcw_deptname").toString();
				String user_code=map.get("user_code").toString();
				xzMap.put(user_code, zcw_deptname);
			}
			
			this.jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						

						
			        	
			        	
						int kk = 2;
						int totalCountRs=0;
						while (rs.next()) {
							
							String user_code = rs.getString("user_code");
							String name = rs.getString("name");
							String cardType = rs.getString("card_type");
							String member_level = rs.getString("member_level");
							String isstore = rs.getString("isstore");
							String company_code = rs.getString("company_code");
							String identity_type = rs.getString("identity_type");
							String freeze_status = rs.getString("freeze_status");
							double franchise_pv = rs.getDouble("franchise_pv");
							double franchise_money = rs.getDouble("franchise_money");
							double consumer_amount = rs.getDouble("consumer_amount");
							double venture_sales_pv = rs.getDouble("venture_sales_pv");//创业销售
							double venture_leader_pv = rs.getDouble("venture_leader_pv");//感恩奖
							double success_sales_pv = rs.getDouble("success_sales_pv");
							double success_leader_pv = rs.getDouble("success_leader_pv");
							double venture_fund = rs.getDouble("venture_fund");//创业扶持奖
							double recommend_bonus_pv = rs.getDouble("recommend_bonus_pv");//推荐奖
							double deduct_money = rs.getDouble("deduct_money");
							double deduct_tax = rs.getDouble("deduct_tax");
							double exchange_rate = rs.getDouble("exchange_rate");
							double bouns_pv = rs.getDouble("bouns_pv");
							double bouns_money = rs.getDouble("bouns_money");
							double send_money = rs.getDouble("send_money");
							
							double store_expand_pv = rs.getDouble("store_expand_pv");
							double store_serve_pv = rs.getDouble("store_serve_pv");
							double store_recommend_pv = rs.getDouble("store_recommend_pv");
							double network_money = rs.getDouble("network_money");
							

							double total_dev = rs.getDouble("total_dev");
							double deducted_dev = rs.getDouble("deducted_dev");
							double current_dev = rs.getDouble("current_dev");

							String pass_star = rs.getString("pass_star");
									
									
							totaldevTotal+=total_dev;
							deductedDevTotal+=deducted_dev;
							currentDevTotal+=current_dev;
							
							
							
	
							franchisePvTotal+=franchise_pv;
							franchiseMoneyTotal+=franchise_money;
							consumerAmountTotal +=consumer_amount;
							ventureSalesPvTotal += venture_sales_pv;
							ventureLeaderPvTotal += venture_leader_pv;
							successSalesPvTotal += success_sales_pv;
							successLeaderPvTotal += success_leader_pv;
							deductMoneyTotal += deduct_money;
							deductTaxTotal += deduct_tax;
							bounsPvTotal += bouns_pv;
							bounsMoneyTotal += bouns_money;
							sendMoneyTotal += send_money;
							recommendBonusPvTotal+= recommend_bonus_pv;
							networkMoneyTotal+=network_money;
							
							storeExpandPvTotal+=store_expand_pv;
							storeServePvTotal+=store_serve_pv;
							storeRecommendPvTotal+=store_recommend_pv;
							
							ventureFundPvTotal+=venture_fund;
							

							bounsFranchisePvTotal+=(bouns_pv+franchise_pv);
							
							int index=0;

							if(kk%sp==0){
								if(kk>0){
									wsheet = wwb.createSheet("Sheet"+(si+1), si);
									kk=0;
									si++;
								}
							}
							
							
							eu.addString(wsheet, index++, kk, user_code);
							
							eu.addString(wsheet, index++, kk, name);
							
							eu.addString(wsheet, index++, kk, passStarMap.get(pass_star)==null?"":LocaleUtil.getLocalText(passStarMap.get(pass_star).toString()));
							
							
							if(flag){
								eu.addString(wsheet, index++, kk, memberLevelMap.get(member_level)==null? member_level : LocaleUtil.getLocalText(memberLevelMap.get(member_level).toString()));
								
							}else{
								eu.addString(wsheet, index++, kk, cardTypeMap.get(cardType)==null? cardType : LocaleUtil.getLocalText(cardTypeMap.get(cardType).toString()));
								
							}
							
						
							
							
							
							
							eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(storeTypeMap.get(isstore).toString()));
							
							if(xzMap.get(user_code)==null){
								eu.addString(wsheet, index++, kk, "会员");
							}else{
								eu.addString(wsheet, index++, kk, xzMap.get(user_code));
							}
							
							
							if(!"HK".equals(company)){
								eu.addNumber(wsheet, index++, kk, franchise_pv);
								eu.addNumber(wsheet, index++, kk, franchise_money);
							}
							
							eu.addNumber(wsheet, index++, kk, consumer_amount);
							
							if(!flag){

								eu.addNumber(wsheet, index++, kk, venture_sales_pv);//创业销售
							}
							if(flag){
								eu.addNumber(wsheet, index++, kk, venture_leader_pv);//级差奖
							}else{
								eu.addNumber(wsheet, index++, kk, venture_leader_pv);//感恩奖
							}

							if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) < pointWeek){
								eu.addNumber(wsheet, index++, kk, success_sales_pv);
								eu.addNumber(wsheet, index++, kk, success_leader_pv);
							}
							if(flag){
								eu.addNumber(wsheet, index++, kk, success_leader_pv);//代数奖
								eu.addNumber(wsheet, index++, kk, success_sales_pv);//卓越领导奖
							}
							
							if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) >= pointWeek){
								if(!flag){
									eu.addNumber(wsheet, index++, kk, venture_fund);//创业扶持奖
								}
								
							}
							eu.addNumber(wsheet, index++, kk, recommend_bonus_pv);//推荐奖


							if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) >= 201604){
								eu.addNumber(wsheet, index++, kk, venture_sales_pv);//服务奖
								servicePvTotal+=venture_sales_pv;
							}
							
							eu.addNumber(wsheet, index++, kk, store_expand_pv);
							
							if(!flag){

								eu.addNumber(wsheet, index++, kk, store_serve_pv);
							}
							eu.addNumber(wsheet, index++, kk, store_recommend_pv);
							
							
							eu.addNumber(wsheet, index++, kk, deduct_money);
							eu.addNumber(wsheet, index++, kk, deduct_tax);

							if("CN".equals(company)||"AA".equals(company)){
								eu.addNumber(wsheet, index++, kk, network_money);
							}
							

							eu.addNumber(wsheet, index++, kk, total_dev);
							eu.addNumber(wsheet, index++, kk, deducted_dev);
							eu.addNumber(wsheet, index++, kk, current_dev);
							
							eu.addNumber(wsheet, index++, kk, exchange_rate);
							eu.addNumber(wsheet, index++, kk, bouns_pv);
							eu.addNumber(wsheet, index++, kk, bouns_pv+franchise_pv);
							
							
							eu.addNumber(wsheet, index++, kk, bouns_money);
							eu.addNumber(wsheet, index++, kk, send_money);
			
							//JmiMember jmiMember=jmiMemberManager.getJmiMember(user_code);
							String member_type = rs.getString("member_type");
							if(!StringUtil.isEmpty(member_type)){
								member_type=member_type.trim();
							}
							eu.addString(wsheet, index++, kk, memberTypeMap.get(member_type)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(member_type).toString().trim()));
							
							eu.addString(wsheet, index++, kk, freezeStatusMap.get(freeze_status)==null?"":LocaleUtil.getLocalText(freezeStatusMap.get(freeze_status).toString()));
		    				
							
							if("AA".equals(company)){
								eu.addString(wsheet, index++, kk, company_code);
							}
							if("TW".equals(company)){
								eu.addString(wsheet, index++, kk, identityTypeMap.get(identity_type)==null?LocaleUtil.getLocalText("identitytype0"):LocaleUtil.getLocalText(identityTypeMap.get(identity_type).toString()));
							}

							
							kk++;
							totalCountRs++;
						} 
//						
						int footerIndex=5;
						eu.addString(wsheet, footerIndex++, kk,  LocaleUtil.getLocalText("report.allTotal")+":");
						
						if(!"HK".equals(company)){
							eu.addNumber(wsheet, footerIndex++, kk, franchisePvTotal);
							eu.addNumber(wsheet, footerIndex++, kk, franchiseMoneyTotal);
						}
						
						eu.addNumber(wsheet, footerIndex++, kk, consumerAmountTotal);
						
						if(!flag){
							eu.addNumber(wsheet, footerIndex++, kk, ventureSalesPvTotal);
						}
						
						eu.addNumber(wsheet, footerIndex++, kk, ventureLeaderPvTotal);
						if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) < pointWeek){
							eu.addNumber(wsheet, footerIndex++, kk, successSalesPvTotal);
							eu.addNumber(wsheet, footerIndex++, kk, successLeaderPvTotal);
						}
						if(flag){
							eu.addNumber(wsheet, footerIndex++, kk, successLeaderPvTotal);
							eu.addNumber(wsheet, footerIndex++, kk, successSalesPvTotal);
						}
						
						
						
						if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) >= pointWeek){
							if(!flag){
								eu.addNumber(wsheet, footerIndex++, kk, ventureFundPvTotal);
							}
						}
						eu.addNumber(wsheet, footerIndex++, kk, recommendBonusPvTotal);
						


						if(StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek)) >= 201604){
							eu.addNumber(wsheet, footerIndex++, kk, servicePvTotal);
						}
						
						
						
						eu.addNumber(wsheet, footerIndex++, kk, storeExpandPvTotal);
						if(!flag){
							eu.addNumber(wsheet, footerIndex++, kk, storeServePvTotal);
						}
						
						eu.addNumber(wsheet, footerIndex++, kk, storeRecommendPvTotal);
						
						
						
						eu.addNumber(wsheet, footerIndex++, kk, deductMoneyTotal);
						eu.addNumber(wsheet, footerIndex++, kk, deductTaxTotal);
						

						if("CN".equals(company)||"AA".equals(company)){
							eu.addNumber(wsheet, footerIndex++, kk, networkMoneyTotal);
						}
						

						eu.addNumber(wsheet, footerIndex++, kk, totaldevTotal);
						eu.addNumber(wsheet, footerIndex++, kk, deductedDevTotal);
						eu.addNumber(wsheet, footerIndex++, kk, currentDevTotal);
						
						
						footerIndex++;
						eu.addNumber(wsheet, footerIndex++, kk, bounsPvTotal);
						eu.addNumber(wsheet, footerIndex++, kk, bounsFranchisePvTotal);
						eu.addNumber(wsheet, footerIndex++, kk, bounsMoneyTotal);
						eu.addNumber(wsheet, footerIndex++, kk, sendMoneyTotal);
			
	
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

	/**
	 * 从201607期之后的奖金报表导出
	 * @param request
	 * @param response
	 */
	public void exportStartWeek201607(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
		if (SessionLogin.getLoginUser(request).getIsManager()) {
			companyCode = request.getParameter("companyCode");
		}
		company=companyCode;
		final String formatedWeek = request.getParameter("formatedWeek");
		
		memberTypeMap=ListUtil.getListOptions(companyCode, "membertype");
		memberLevelMap=ListUtil.getListOptions(companyCode, "member.level");
		storeTypeMap=ListUtil.getListOptions(companyCode, "isstore");
		freezeStatusMap=ListUtil.getListOptions(companyCode, "mimember.freezestatus");
		passStarMap=ListUtil.getListOptions(companyCode, "pass.star.zero");
		yesNoMap=ListUtil.getListOptions(companyCode, "yesno");
		
		monthConsumerPvTotal=new Double(0);//重消类业绩合计
		recommendBonusPvTotal=new Double(0);//推荐奖合计
		ventureLeaderPvTotal=new Double(0);//销售奖合计
		ventureSalesPvTotal=new Double(0);//服务奖合计
		successLeaderPvTotal=new Double(0);//感恩奖合计
		orderPvTotal=new Double(0);//报单奖PV合计
		storeRecommendPvTotal =new Double(0);//店铺推荐奖合计
		consumerAmountTotal = new Double(0);//重消物流费合计
		deductMoneyTotal = new Double(0);//扣补款合计
		deductTaxTotal = new Double(0);//扣税合计
		networkMoneyTotal=new Double(0);//网络费合计
		totaldevTotal = new Double(0);//总扣合计
		deductedDevTotal = new Double(0);//已扣合计
		currentDevTotal = new Double(0);//现扣合计
		bounsPvTotal = new Double(0);//奖金PV合计
		bounsMoneyTotal = new Double(0);//所得奖金合计
		sendMoneyTotal = new Double(0);//实发奖金合计

		successSalesPvTotal=new  Double(0);
		
		//生成excel文件
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions) B_"+formatedWeek+"_"+companyCode+".xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30" ); 
		OutputStream os = response.getOutputStream();
		eu = new ExcelUtil();
		wwb = Workbook.createWorkbook(os);
		//在此创建的新excel文件创建一工作表 
		wsheet = wwb.createSheet("Sheet1", 0);
		//加入期别时间段显示
		
		BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(WeekFormatUtil.getFormatWeek("f",formatedWeek));
		eu.addString(wsheet, 0, 0, formatedWeek);
		eu.addString(wsheet, 1, 0, bdPeriod.getStartTime()+" - "+bdPeriod.getEndTime());
		
		int i=0;
		
		eu.addString(wsheet, i++, 1, "会员编号");
		eu.addString(wsheet, i++, 1, "会员名称");
		
		if(StringUtil.formatInt(formatedWeek)>=201701){
			eu.addString(wsheet, i++, 1, "会员级别");
		}
		
		
		eu.addString(wsheet, i++, 1, "店铺类型");
		eu.addString(wsheet, i++, 1, "行政级别");
		eu.addString(wsheet, i++, 1, "奖衔");
		eu.addString(wsheet, i++, 1, "重消类业绩");
		eu.addString(wsheet, i++, 1, "免重销类型");

		if(StringUtil.formatInt(formatedWeek)>201905){
			eu.addString(wsheet, i++, 1, "推广奖");
		}
		eu.addString(wsheet, i++, 1, "销售奖");
		

		if(StringUtil.formatInt(formatedWeek)<201701){
			eu.addString(wsheet, i++, 1, "服务奖");
		}
		
		
		eu.addString(wsheet, i++, 1, "领导奖");
		
		if(StringUtil.formatInt(formatedWeek)>=201701){
			eu.addString(wsheet, i++, 1, "重消奖");
		}
		
		if(StringUtil.formatInt(formatedWeek)<201805){
			eu.addString(wsheet, i++, 1, "代理政策奖励");
		}else{
			
			eu.addString(wsheet, i++, 1, "报单奖");
		}
		//eu.addString(wsheet, i++, 1, "店铺推荐奖");
		eu.addString(wsheet, i++, 1, "重消物流费");
		eu.addString(wsheet, i++, 1, "扣补款");
		eu.addString(wsheet, i++, 1, "扣税");
		eu.addString(wsheet, i++, 1, "网络费");
		eu.addString(wsheet, i++, 1, "总扣");
		eu.addString(wsheet, i++, 1, "已扣");
		eu.addString(wsheet, i++, 1, "现扣");
		eu.addString(wsheet, i++, 1, "汇率");
		eu.addString(wsheet, i++, 1, "奖金PV");
		eu.addString(wsheet, i++, 1, "所得奖金");
		//eu.addString(wsheet, i++, 1, "实发金额");
		eu.addString(wsheet, i++, 1, "应发奖金");
		eu.addString(wsheet, i++, 1, "会员类型");
		eu.addString(wsheet, i++, 1, "冻结状态");
		
		
		String condition="where w_week ="+WeekFormatUtil.getFormatWeek("f",formatedWeek);
		if(!"AA".equals(company)){
			condition+=" and b.company_code='"+companyCode+"' ";
		}
		condition+="  order by b.user_code ";
		
		String sql = "select b.*,m.member_type from jbd_bonus_report_b b left join jmi_member m on m.user_code=b.user_code " + condition;
		
		List list=jdbcTemplate.queryForList("select * from jpo_zcw_member");
		xzMap=new HashMap<String,String>();
		for (int j = 0; j < list.size(); j++) {
			Map map=(Map) list.get(j);
			String zcw_deptname=map.get("zcw_deptname").toString();
			String user_code=map.get("user_code").toString();
			xzMap.put(user_code, zcw_deptname);
		}
		
		this.jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException {
				try {
					int kk = 2;
					while (rs.next()) {
						String user_code = rs.getString("user_code");//会员编号
						String name = rs.getString("name");//会员名称
						String isstore = rs.getString("isstore");//店铺类型
						String pass_star = rs.getString("pass_star");//奖衔
						double month_consumer_pv = rs.getDouble("month_consumer_pv");//重消类业绩
						String first_month = rs.getString("first_month");//免重销类型
						double recommend_bonus_pv = rs.getDouble("recommend_bonus_pv");//推荐奖
						double venture_leader_pv = rs.getDouble("venture_leader_pv");//销售奖
						double venture_sales_pv = rs.getDouble("venture_sales_pv");//服务奖
						double success_leader_pv = rs.getDouble("success_leader_pv");//感恩奖
						double order_pv = rs.getDouble("franchise_pv")+rs.getDouble("store_expand_pv");//报单奖PV=店补+店拓
						double store_recommend_pv = rs.getDouble("store_recommend_pv");//店铺推荐奖
						double consumer_amount = rs.getDouble("consumer_amount");//重消物流费
						double deduct_money = rs.getDouble("deduct_money");//扣补款
						double deduct_tax = rs.getDouble("deduct_tax");//扣税	
						double network_money = rs.getDouble("network_money");//网络费	
						double total_dev = rs.getDouble("total_dev");//总扣
						double deducted_dev = rs.getDouble("deducted_dev");//已扣
						double current_dev = rs.getDouble("current_dev");//现扣
						double exchange_rate = rs.getDouble("exchange_rate");//汇率	
						double bouns_pv = rs.getDouble("bouns_pv")+rs.getDouble("franchise_pv");//奖金PV=	bouns_pv+店补
						double bouns_money = bouns_pv*6.3;//所得奖金	
						double success_sales_pv=rs.getDouble("success_sales_pv");
						//double money = 0;//实发金额
						double send_money = rs.getDouble("send_money");//应发奖金
						String member_type = rs.getString("member_type");//会员类型
						if(!StringUtil.isEmpty(member_type)){
							member_type=member_type.trim();
						}
						String freeze_status = rs.getString("freeze_status");//冻结状态
						Integer member_level=rs.getInt("member_level");
						//计算合计
						monthConsumerPvTotal += month_consumer_pv;//重消类业绩合计
						recommendBonusPvTotal += recommend_bonus_pv;//推荐奖合计
						ventureLeaderPvTotal += venture_leader_pv;//销售奖合计
						ventureSalesPvTotal += venture_sales_pv;//服务奖合计
						successLeaderPvTotal += success_leader_pv;//感恩奖合计
						orderPvTotal += order_pv;//报单奖PV合计
						storeRecommendPvTotal += store_recommend_pv;//店铺推荐奖合计
						consumerAmountTotal += consumer_amount;//重消物流费合计
						deductMoneyTotal += deduct_money;//扣补款合计
						deductTaxTotal += deduct_tax;//扣税合计
						networkMoneyTotal += network_money;//网络费合计
						totaldevTotal += total_dev;//总扣合计
						deductedDevTotal += deducted_dev;//已扣合计
						currentDevTotal += current_dev;//现扣合计
						bounsPvTotal += bouns_pv;//奖金PV合计
						bounsMoneyTotal += bouns_money;//所得奖金合计
						sendMoneyTotal += send_money;//实发奖金合计
						
						

						successSalesPvTotal += success_sales_pv;//
						
						int index=0;

						if(kk%sp==0){
							if(kk>0){
								wsheet = wwb.createSheet("Sheet"+(si+1), si);
								kk=0;
								si++;
							}
						}
						
						eu.addString(wsheet, index++, kk, user_code);//会员编号
						eu.addString(wsheet, index++, kk, name);//会员名称
						
						if(StringUtil.formatInt(formatedWeek)>=201701){
							eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(memberLevelMap.get(member_level+"").toString()));
						}
						
						eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(storeTypeMap.get(isstore).toString()));//店铺类型
						eu.addString(wsheet, index++, kk, xzMap.get(user_code)==null?"会员":xzMap.get(user_code));//行政级别
						eu.addString(wsheet, index++, kk, passStarMap.get(pass_star)==null?"":LocaleUtil.getLocalText(passStarMap.get(pass_star).toString()));//奖衔
						eu.addNumber(wsheet, index++, kk, month_consumer_pv);//重消类业
						eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText(yesNoMap.get(first_month).toString()));//免重销类型
						

						if(StringUtil.formatInt(formatedWeek)>201905){
							eu.addNumber(wsheet, index++, kk, recommend_bonus_pv);//推荐奖
						}
						
						eu.addNumber(wsheet, index++, kk, venture_leader_pv);//销售奖
						

						if(StringUtil.formatInt(formatedWeek)<201701){
							eu.addNumber(wsheet, index++, kk, venture_sales_pv);//服务奖
						}
						
						
						eu.addNumber(wsheet, index++, kk, success_leader_pv);//感恩奖
						
						if(StringUtil.formatInt(formatedWeek)>=201701){
							eu.addNumber(wsheet, index++, kk, success_sales_pv);//重消奖
						}
						
						
						eu.addNumber(wsheet, index++, kk, order_pv);//报单奖PV=店补+店拓
						//eu.addNumber(wsheet, index++, kk, store_recommend_pv);//店铺推荐奖
						eu.addNumber(wsheet, index++, kk, consumer_amount);//重消物流费
						eu.addNumber(wsheet, index++, kk, deduct_money);//扣补款
						eu.addNumber(wsheet, index++, kk, deduct_tax);//扣税
						eu.addNumber(wsheet, index++, kk, network_money);//网络费
						eu.addNumber(wsheet, index++, kk, total_dev);//总扣
						eu.addNumber(wsheet, index++, kk, deducted_dev);//已扣
						eu.addNumber(wsheet, index++, kk, current_dev);//现扣
						eu.addNumber(wsheet, index++, kk, exchange_rate);//汇率	
						eu.addNumber(wsheet, index++, kk, bouns_pv);//奖金PV
						eu.addNumber(wsheet, index++, kk, bouns_money);//所得奖金
						//eu.addNumber(wsheet, index++, kk, 0);//实发金额
						eu.addNumber(wsheet, index++, kk, send_money);//应发奖金
						eu.addString(wsheet, index++, kk, memberTypeMap.get(member_type)==null?"":LocaleUtil.getLocalText(memberTypeMap.get(member_type).toString().trim()));//会员类型
						eu.addString(wsheet, index++, kk, freezeStatusMap.get(freeze_status)==null?"":LocaleUtil.getLocalText(freezeStatusMap.get(freeze_status).toString()));//冻结状态
						kk++;
					}

					
					int footerIndex=4;
					if(StringUtil.formatInt(formatedWeek)>=201701){
						footerIndex+=1;
					}
					eu.addString(wsheet, footerIndex++, kk, "合计:");
					eu.addNumber(wsheet, footerIndex++, kk, monthConsumerPvTotal);//重消类业绩合计
					footerIndex++;

					if(StringUtil.formatInt(formatedWeek)>201905){
						eu.addNumber(wsheet, footerIndex++, kk, recommendBonusPvTotal);//推荐奖合计
					}
					
					eu.addNumber(wsheet, footerIndex++, kk, ventureLeaderPvTotal);//销售奖合计

					if(StringUtil.formatInt(formatedWeek)<201701){
						eu.addNumber(wsheet, footerIndex++, kk, ventureSalesPvTotal);//服务奖合计
					}
					eu.addNumber(wsheet, footerIndex++, kk, successLeaderPvTotal);//感恩奖合计
					

					if(StringUtil.formatInt(formatedWeek)>=201701){
						eu.addNumber(wsheet, footerIndex++, kk, successSalesPvTotal);//重消奖合计
					}
					
					
					
					eu.addNumber(wsheet, footerIndex++, kk, orderPvTotal);//报单奖PV合计
					//eu.addNumber(wsheet, footerIndex++, kk, storeRecommendPvTotal);//店铺推荐奖合计
					eu.addNumber(wsheet, footerIndex++, kk, consumerAmountTotal);//重消物流费合计
					eu.addNumber(wsheet, footerIndex++, kk, deductMoneyTotal);//扣补款合计
					eu.addNumber(wsheet, footerIndex++, kk, deductTaxTotal);//扣税合计
					eu.addNumber(wsheet, footerIndex++, kk, networkMoneyTotal);//网络费合计
					eu.addNumber(wsheet, footerIndex++, kk, totaldevTotal);//总扣合计
					eu.addNumber(wsheet, footerIndex++, kk, deductedDevTotal);//已扣合计
					eu.addNumber(wsheet, footerIndex++, kk, currentDevTotal);//现扣合计
					footerIndex++;
					eu.addNumber(wsheet, footerIndex++, kk, bounsPvTotal);//奖金PV合计
					eu.addNumber(wsheet, footerIndex++, kk, bounsMoneyTotal);//所得奖金合计
					eu.addNumber(wsheet, footerIndex++, kk, sendMoneyTotal);//实发奖金合计
					
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
	}

}