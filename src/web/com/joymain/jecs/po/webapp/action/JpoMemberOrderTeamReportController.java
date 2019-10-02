package com.joymain.jecs.po.webapp.action;

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

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.log.util.ReportLogUtil;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 
 * 团队订单统计
 */
public class JpoMemberOrderTeamReportController extends BaseFormController {
	private transient final Log log = LogFactory.getLog(JbdMemberLinkCalcHist.class);
	private AlCompanyManager alCompanyManager = null;
	private BdPeriodManager bdPeriodManager=null;
    private AlCountryManager alCountryManager;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private JmiMemberManager jmiMemberManager;
    private JpoMemberOrderManager jpoMemberOrderManager;
    private JprRefundManager jprRefundManager ;
    
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}


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


	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}


	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private JdbcTemplate jdbcTemplateReport = null;
	private ExcelUtil eu = null;
	private WritableSheet wsheet = null;
	
	private int progressCurrentCount;
	private String company=null;
	private String treeIndex=null;
	private HttpServletRequest innerRequest;
	private Map cardTypeMap=null;
	private Map yesNoMap=null;
	private Map provinceMap=null;
	private Map cityMap=null;
	private Map districtMap=null;
	private Map orderTypeMap=null;
	private Double totalPv=null;
	private Double totalAmount=null;
	private Double totalJjAmount=null;
	private Map storeTypeMap=null;
	private int objLayerId=0;



	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplateReport) {
		this.jdbcTemplateReport = jdbcTemplateReport;
	}


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public JpoMemberOrderTeamReportController() {
		setCommandName("bdPeriod");
		setCommandClass(BdPeriod.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		
		SysUser defSysUser=SessionLogin.getLoginUser(request);
		

		AlCompany alCompany = alCompanyManager.getAlCompanyByCode(defSysUser.getCompanyCode());
        AlCountry alCountry = new AlCountry();
    	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());

        RequestUtil.freshSession(request,"jpoMemberOrderTeamReport", 10L);
		

		return new BdPeriod();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "jpoMemberOrderTeamReport".equalsIgnoreCase(request.getParameter("strAction"))) {		

			String userCode = request.getParameter("userCode");
			JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
			String formatedWeek = request.getParameter("formatedWeek");
			
			String formatedMonth = request.getParameter("formatedMonth");
			String formatedYear = request.getParameter("formatedYear");

			

			formatedWeek=WeekFormatUtil.getFormatWeek("f",formatedWeek);
			
			
			
			

			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			
			if(jmiMember==null){
				this.saveMessage(request, this.getText("miMember.notFound"));
				return showForm(request, response, errors);
			}
			if((!StringUtil.isDate(startDate)||!StringUtil.isDate(endDate))&&StringUtil.isEmpty(formatedWeek)&&(!StringUtil.isInteger(formatedYear)||!StringUtil.isInteger(formatedMonth))){
				this.saveMessage(request, this.getText("please.input.search.condition"));
				return showForm(request, response, errors);
			}
			
			if(!StringUtil.isEmpty(formatedMonth)||!StringUtil.isEmpty(formatedYear)){
				List bdPeriodList=bdPeriodManager.getBdPeriodsByFmonth(formatedYear, formatedMonth);
				if(!bdPeriodList.isEmpty()){		
					if(bdPeriodList.size()==1){
						BdPeriod curBdPeriod=(BdPeriod) bdPeriodList.get(0);
						startDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  curBdPeriod.getStartTime());
						long endTimeLong=curBdPeriod.getEndTime().getTime()-1000l;
						endDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm", new Date(endTimeLong));
					}else{
						for (int i = 0; i < bdPeriodList.size(); i++) {
							BdPeriod curBdPeriod=(BdPeriod) bdPeriodList.get(i);
							if(i==0){
								startDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  curBdPeriod.getStartTime());
							}else if(i==(bdPeriodList.size()-1)){
								long endTimeLong=curBdPeriod.getEndTime().getTime()-1000l;
								endDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm", new Date(endTimeLong));
							}
						}
					}
				}else{
					this.saveMessage(request, this.getText("please.input.search.condition"));
					return showForm(request, response, errors);
				}
			}else{
				BdPeriod bdPeriod=bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
				if(bdPeriod!=null){
					startDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  bdPeriod.getStartTime());

					long endTimeLong=bdPeriod.getEndTime().getTime()-1000l;
					endDate=DateUtil.getDateTime("yyyy-MM-dd HH:ss:mm",  new Date(endTimeLong));
				}else{
					startDate+=" 00:00:00";
					endDate+=" 23:59:59";
				}
			}

			
			//不准查询超过一个月的
			int betDates=DateUtil.daysBetweenDates(DateUtil.convertStringToDate(endDate), DateUtil.convertStringToDate(startDate));
			if(betDates>31){
				this.saveMessage(request, "查询时间区间过长");
				return showForm(request, response, errors);
			}
			//

			if(RequestUtil.saveOperationSession(request, "jpoMemberOrderTeamReport", 10L)!=0){
	       		  return new ModelAndView("redirect:jpoMemberOrderTeamReport.html");
	       	 }
			

			//开始日志--start
			Long refId =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "jpoMemberOrderTeamReport", "");
			//开始日志--end
			
			
			this.innerRequest=request;
			String companyCode = SessionLogin.getLoginUser(request).getCompanyCode();
			if (SessionLogin.getLoginUser(request).getIsManager()) {
				companyCode = request.getParameter("companyCode");
			}
			company=companyCode;
			
			String type = request.getParameter("type");
			String dateType = request.getParameter("dateType");
			
			String relationType="";
			
			if("1".equals(type)){
				type="link";
				relationType="jmi_link_ref";
			}else if("2".equals(type)){
				type="recommend";
				relationType="jmi_recommend_ref";
				
			}
			OutputStream os = null;
			WritableWorkbook wwb = null;
			//生成excel文件
			

			int i=0;
			
			if(!"statistic".equals(request.getParameter("statistic"))){

				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition", "attachment; filename=order_team_"+type+"_"+userCode+".xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30" );
				os = response.getOutputStream();
				eu = new ExcelUtil();
				wwb = Workbook.createWorkbook(os);
				wsheet = wwb.createSheet("Sheet1", 0);
				
				

				//在此创建的新excel文件创建一工作表 
				

				//加入期别时间段显示
				eu.addString(wsheet, 0, 0, startDate+"~"+endDate);
				//标题

			
				
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.memberNo"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdCalculatingSubDetail.name"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.petName"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.cardType"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdMemberBounsCalcSell.level"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("sysDataLog.changeType"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("poMemberOrder.memberOrderNo"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("pd.orderType"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("shipping.mobiletele"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.province"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.idAddr2"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("miMember.district"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.direct.pv"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.finance.amount"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("busi.order.jjAmount"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("pd.ordertype.prRefund"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("logType.C"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("pd.checkTime"));
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("fiCoinLog.coin"));

				//add by lihao,执行报表新导入一列 ： 产品积分  Jbd_Team_link过程已经有返回PRODUCT_AMT
				eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("fiProductPointTemp.productamt"));
				
				if("AA".equals(company)){
					eu.addString(wsheet, i++, 1, LocaleUtil.getLocalText("bdReconsumMoneyReport.company"));
				}
			} 
			
			 
			
			
			
			cardTypeMap=ListUtil.getListOptions(companyCode, "member.level");
			
			yesNoMap=ListUtil.getListOptions(companyCode, "yesno");
			
			provinceMap=alStateProvinceManager.getAlStateProvincesMapByCountry(companyCode);
			cityMap=alCityManager.getAlCityMap(companyCode);
			districtMap=alDistrictManager.getAlDistrictMap(companyCode);
			
			orderTypeMap=ListUtil.getListOptions(companyCode, "po.all.order_type");

			storeTypeMap=ListUtil.getListOptions(companyCode, "isstore");
			
			//String treeIndex="";
			

			String functionName="";
			
			if("link".equals(type)){
				treeIndex=jmiMember.getJmiLinkRef()!=null?jmiMember.getJmiLinkRef().getTreeIndex():"";
				objLayerId=jmiMember.getJmiLinkRef()!=null?(int)jmiMember.getJmiLinkRef().getLayerId():0;
				functionName="Jbd_Team_link";
			}else if("recommend".equals(type)){
				treeIndex=jmiMember.getJmiRecommendRef()!=null?jmiMember.getJmiRecommendRef().getTreeIndex():"";
				objLayerId=jmiMember.getJmiRecommendRef()!=null?(int)jmiMember.getJmiRecommendRef().getLayerId():0;
				functionName="jbd_team_recommend";
			}
			
			
			
/*			String sql="select o.*,m.isstore,r.layer_id,m.pet_name,m.card_type,n.mo_id as refund,m.mi_user_name from  "+relationType+" r,jpo_member_order o " +
					"left join jmi_member m on m.user_code=o.user_code " +
					"left join jpr_refund n on n.mo_id=o.mo_id and n.refund_status='2' where o.user_code = r.user_code " +
					"and r.tree_index like '"+treeIndex+"%' " +
					"and o.check_date >=To_Date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') " +
					"And o.check_date <= To_Date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') And Status = '2' " ;*/
/*			String sql="Select o.company_code,r.isstore,o.Mo_Id,o.User_Code,o.Member_Order_No,o.Order_Type,o.Mobiletele,o.Province," +
					"o.City,o.District,o.Pv_Amt,o.Amount, o.Jj_Amount,o.Check_Date, o.Check_Time, p.Ro_No as refund,q.Tree_Index," +
					" q.Layer_Id,r.Mi_User_Name,r.Pet_Name,r.Card_Type From Jpo_Member_Order o,Jmi_Link_Ref     q,Jpr_Refund       p," +
					"Jmi_Member       r  Where o.User_Code = q.User_Code And o.User_Code = r.User_Code " +
					"And o.Mo_Id = p.Mo_Id(+) And p. Refund_Status(+) = '2' And o.Check_Date >=To_Date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss')" +
							" And o.Check_Date <= To_Date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') " +
					" And Status = '2'  ";
			String province=request.getParameter("province");		
			if(!StringUtil.isEmpty(province)){
				sql+=" and o.province='"+province+"'";
			}
			String city=request.getParameter("city");
			if(!StringUtil.isEmpty(city)){
				sql+=" and o.city='"+city+"'";
			}
			
			if(!"AA".equals(company)){
				sql+=" and o.company_code='"+companyCode+"'";
			}*/
			
			//sql+=" order by r.layer_id asc";
			
			if("AA".equals(company)){
				company="";
			}
			
			String province=request.getParameter("province");	
			if(StringUtil.isEmpty(province)){
				province="";
			}
			String city=request.getParameter("city");
			if(StringUtil.isEmpty(city)){
				city="";
			}
			
			String sql="Select Column_Value From Table("+functionName+"('"+userCode+"','"+company+"', '"+province+"', '"+city+"',To_Date('"+startDate+"','yyyy-mm-dd hh24:mi:ss')," +
					"To_Date('"+endDate+"','yyyy-mm-dd hh24:mi:ss'),"+dateType+"))";
			
			
			
			
			if("statistic".equals(request.getParameter("statistic"))){
				String checkType="";
				if("2".equals(dateType)){
					checkType="C";
				}

				//开始日志--start
				Long refId2 =ReportLogUtil.beginReport(SessionLogin.getLoginUser(request).getUserCode(), RequestUtil.getIpAddr(request), "jpoMemberOrderTeamReport2", "");
				//开始日志--end
				
				List poMemberOrderList = jpoMemberOrderManager.getJpoMemberOrderStaticsCheckedCompanyByTree(startDate, endDate, companyCode, checkType, treeIndex, relationType);//会员订单
				List prRefundList = jprRefundManager.getJprReRefundStaticsCheckedCompanyByTree(startDate, endDate, companyCode, "0", treeIndex, relationType);//会员退货订
				

				request.setAttribute("startTime", startDate);
				request.setAttribute("endTime", endDate);
				request.setAttribute("poMemberOrderList", poMemberOrderList);
				request.setAttribute("prRefundList", prRefundList);

				//结束日志--start
				ReportLogUtil.endReport(refId2, "jpoMemberOrderTeamReport2");
				//结束日志--end
				return new ModelAndView("po/jpoMemberOrderTeamStatisticReport");
			}

			
			
			totalPv=new Double(0);
			totalAmount=new Double(0);
			totalJjAmount=new Double(0);
			
			
			this.jdbcTemplateReport.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					try {
						int kk = 2;
						while (rs.next()) {
							String Column_Value = rs.getString("Column_Value");
							
							String str[]=Column_Value.split("&&");
							String userCode =str[0];
							String mi_user_name =str[1];
							String pet_name =str[2];
							String card_type =str[3];
							String layer_id =str[4];
							String member_order_no =str[5];
							String order_type =str[6];
							String mobiletele =str[7];
							String province =str[8];
							String city =str[9];
							String district =str[10];
							
							String pv_amt =str[11];
							String amount =str[12];
							String jj_amount =str[13];
							
							
							Double pv_amt_double=0d;
							Double amount_double=0d;
							Double jj_amount_double=0d;
							
							
							if(StringUtil.isDouble(pv_amt)){
								pv_amt_double=StringUtil.formatDouble(pv_amt);
							}
							if(StringUtil.isDouble(amount)){
								amount_double=StringUtil.formatDouble(amount);
							}
							if(StringUtil.isDouble(jj_amount)){
								jj_amount_double=StringUtil.formatDouble(jj_amount);
							}
							
							
							String refund =str[14];
							String check_date =str[15];
							String check_time =str[16];
							String isstore =str[17];
							String company_code =str[18];

							String discount_amount =str[19];
							
							
							
							Double discount_amount_double=0d;
							if(StringUtil.isDouble(discount_amount)){
								discount_amount_double=StringUtil.formatDouble(discount_amount);
							}
							
							//add by lihao,执行报表新导入一列 ： 产品积分  Jbd_Team_link过程已经有返回PRODUCT_AMT
							String product_amt=str[20];
							
							Double product_amt_double=0d;
							if(StringUtil.isDouble(product_amt)){
								product_amt_double=StringUtil.formatDouble(product_amt);
							}
							
							
							
							/*String userCode = rs.getString("user_code");

							String member_order_no = rs.getString("member_order_no");
							String mobiletele = rs.getString("mobiletele");
							String province = rs.getString("province");
							String city = rs.getString("city");
							String district = rs.getString("district");
							String order_type = rs.getString("order_type");
							String isstore = rs.getString("isstore");
							
							Double pv_amt = rs.getDouble("pv_amt");
							Double amount = rs.getDouble("amount");
							Double jj_amount = rs.getDouble("jj_amount");
							String company_code = rs.getString("company_code");
							String mi_user_name = rs.getString("mi_user_name");
							
							String pet_name = rs.getString("pet_name");
							int layer_id = rs.getInt("layer_id");

							String refund = rs.getString("refund");
							String card_type = rs.getString("card_type");
							String Tree_Index = rs.getString("Tree_Index");
							

							Date check_date = rs.getDate("check_date");
							Date check_time = rs.getDate("check_time");*/
							
							totalPv+=pv_amt_double;
							totalAmount+=amount_double;
							totalJjAmount+=jj_amount_double;
							
							//card_type=cardTypeMap.get(card_type).toString();
							
							province=provinceMap.get(province)==null?"":provinceMap.get(province).toString();

							city=cityMap.get(city)==null?"":cityMap.get(city).toString();
							
							district=districtMap.get(district)==null?"":districtMap.get(district).toString();

							int index=0;


							eu.addString(wsheet, index++, kk, userCode);
							eu.addString(wsheet, index++, kk, mi_user_name);
							eu.addString(wsheet, index++, kk, pet_name);
							eu.addString(wsheet, index++, kk, cardTypeMap.get(card_type)==null?"":LocaleUtil.getLocalText(cardTypeMap.get(card_type).toString()));
							
							if(StringUtil.isInteger(layer_id)){
								if(StringUtil.formatInt(layer_id)-objLayerId==0){
									eu.addString(wsheet, index++, kk, "");
								}else{
									eu.addNumber(wsheet, index++, kk, StringUtil.formatInt(layer_id)-objLayerId);
								}
							}else{
								eu.addString(wsheet, index++, kk, "");
							}
							
							
							
							eu.addString(wsheet, index++, kk, storeTypeMap.get(isstore)==null?"":LocaleUtil.getLocalText(storeTypeMap.get(isstore).toString()));
							
							eu.addString(wsheet, index++, kk, member_order_no);
							
							eu.addString(wsheet, index++, kk, orderTypeMap.get(order_type)==null?"":LocaleUtil.getLocalText(orderTypeMap.get(order_type).toString()));
							
							eu.addString(wsheet, index++, kk, mobiletele);
							eu.addString(wsheet, index++, kk, province);
							eu.addString(wsheet, index++, kk, city);
							eu.addString(wsheet, index++, kk, district);

							eu.addNumber(wsheet, index++, kk, pv_amt_double);
						
							eu.addNumber(wsheet, index++, kk, amount_double);
							eu.addNumber(wsheet, index++, kk, jj_amount_double);

							if(StringUtil.isEmpty(refund.trim())){
								eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText("yesno.no"));
							}else{
								eu.addString(wsheet, index++, kk, LocaleUtil.getLocalText("yesno.yes"));
							}

							eu.addString(wsheet, index++, kk, check_date+"");
							eu.addString(wsheet, index++, kk, check_time+"");
							eu.addNumber(wsheet, index++, kk, discount_amount_double);
							
							//add by lihao
							eu.addNumber(wsheet, index++, kk, product_amt_double);
							if("AA".equals(company)){
								eu.addString(wsheet, index++, kk, company_code);
							}
						
							
			
			
							kk++;
						} 
						
						eu.addString(wsheet, 11, kk,  LocaleUtil.getLocalText("report.allTotal")+":");
						eu.addNumber(wsheet, 12, kk,  totalPv);
						eu.addNumber(wsheet, 13, kk,  totalAmount);
						eu.addNumber(wsheet, 14, kk,  totalJjAmount);
				
			
	
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
			ReportLogUtil.endReport(refId, "jpoMemberOrderTeamReport");
			//结束日志--end
			
			return null;
		}

		return new ModelAndView(getSuccessView());
	}


	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}


	public void setJprRefundManager(JprRefundManager jprRefundManager) {
		this.jprRefundManager = jprRefundManager;
	}



}