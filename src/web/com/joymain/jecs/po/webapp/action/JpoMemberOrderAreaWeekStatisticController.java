package com.joymain.jecs.po.webapp.action;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.io.ExcelUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.SmsSend;

/**
 * 订单统计(地区按期别)
 * 
 * @author Alvin
 * 
 */
@SuppressWarnings({"unchecked","unused"})
public class JpoMemberOrderAreaWeekStatisticController extends BaseController
		implements Controller {
	private final Log log = LogFactory
			.getLog(JpoMemberOrderAreaWeekStatisticController.class);

	private JdbcTemplate jdbcTemplate = null;
	private PdSendInfoManager pdSendInfoManager = null;
	
	private BdPeriodManager bdPeriodManager = null;
	
	private JpmProductSaleNewManager jpmProductSaleNewManager = null;
	
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

/*	public JmsTemplate102 jmsTemplate = null; 
	public Destination sendSmsDestination = null; 

	public void setSendSmsDestination(Destination sendSmsDestination) {
		this.sendSmsDestination = sendSmsDestination;
	}

	public void setJmsTemplate(JmsTemplate102 jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}*/

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
/*		final List<SmsSendLog> smsSendLogs = new ArrayList<SmsSendLog>();
		SmsSendLog smsSendLog = new SmsSendLog();
        smsSendLog.setMobile("13929516683");
        smsSendLog.setSendMsg("测试");
        smsSendLog.setSendNum("11");
        smsSendLog.setStatus("1");
        smsSendLogs.add(smsSendLog);
		jmsTemplate.send(sendSmsDestination, new MessageCreator(){
            public Message createMessage(Session session) throws JMSException
            {
                ObjectMessage message = session.createObjectMessage();
                message.setObject((Serializable) smsSendLogs);
                return message;
            }
        });*/

		
/*		JmsEjbUtil jmsEjbUtil = new JmsEjbUtil();
		jmsEjbUtil.setDestination("queue/SendSmsMDBQueueBean");
        List<SmsSendLog> smsSendLogs = new ArrayList<SmsSendLog>();
        SmsSendLog smsSendLog = new SmsSendLog();
        smsSendLog.setMobile("13929516683");
        smsSendLog.setSendMsg("测试");
        smsSendLog.setSendNum("11");
        smsSendLog.setStatus("1");
        smsSendLogs.add(smsSendLog);
		jmsEjbUtil.sendObjectMessage(smsSendLogs);*/
        
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
		String strAction  = request.getParameter("strAction");
		//默认起始/截止日期
		SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd");   
		String defaultEndDate = dateformat2.format(new Date());//默认起始时间
		String defaultStartDate = defaultEndDate.substring(0,7)+"-01";//默认截止时间
		String startLogTime = request.getParameter("startLogTime");//起始时间
		String endLogTime = request.getParameter("endLogTime");//截止时间
		if(StringUtils.isEmpty(startLogTime) || "null".equals(startLogTime)){
			startLogTime = defaultStartDate;       	
        }
		if(StringUtils.isEmpty(endLogTime) || "null".equals(endLogTime)){
			endLogTime = defaultEndDate;       	
        }
		//默认财月
		BdPeriod bdPeriodd =  bdPeriodManager.getBdPeriodByTime(new Date(),null);
		String month = "";
		if(bdPeriodd!=null && String.valueOf(bdPeriodd.getWmonth()).length()==1){
			month = "0"+bdPeriodd.getWmonth();
		}else{
			month = ""+bdPeriodd.getWmonth();
		}
		String defaultYearMonth = bdPeriodd.getWyear()+""+month;
		if(StringUtil.isEmpty(strAction)){//原始的功能保留：期别地区业绩统计
			String search = request.getParameter("search");
			if(!StringUtil.isEmpty(search)){
				String type = request.getParameter("type");
				String orderType = "";
				String excelTitle = "";
				if("1".equals(type)){
					orderType = "'1','2'";
					excelTitle = LocaleUtil.getLocalText("memberFUOrder.statistic");//"会员首购,升级单业绩";
				}else if("2".equals(type)){
					orderType = "'4'";
					excelTitle = LocaleUtil.getLocalText("memberROrder.statistic");//"会员重销单业绩";
				} if("3".equals(type)){
					orderType = "'6'";
					excelTitle = LocaleUtil.getLocalText("storeFOrder.statistic");//"店铺首购单业绩";
				} if("4".equals(type)){
					orderType = "'9'";
					excelTitle = LocaleUtil.getLocalText("storeROrder.statistic");//"店铺重销单业绩";
				} if("5".equals(type)){
					orderType = "'11'";
					excelTitle = LocaleUtil.getLocalText("subStoreFOrder.statistic");//"二级店铺首购单业绩";
				} if("6".equals(type)){
					orderType = "'12'";
					excelTitle = LocaleUtil.getLocalText("subStoreUOrder.statistic");//"二级店铺升级单业绩";
				} if("7".equals(type)){
					orderType = "'14'";
					excelTitle = LocaleUtil.getLocalText("subStoreROrder.statistic");//"二级店铺重销单业绩";
				}
				String sql = "Select w.Week, Jsp.State_Province_Name, w.Sumamount From Jal_State_Province Jsp, (Select a.Week, Sum(b.Amount) Sumamount, b.Province From (Select f_Year || Lpad(f_Week, 2, 0) Week, Start_Time, End_Time From Jbd_Period) a, (Select Amount, Check_Date, Province From Jpo_Member_Order Where order_type In (" + orderType + ") And COMPANY_CODE ='" + loginSysUser.getCompanyCode() + "' and is_retreat_order='0') b Where a.Start_Time <= b.Check_Date And a.End_Time > b.Check_Date Group By a.Week, b.Province) w Where Jsp.State_Province_Id = w.Province Order By w.Week";
				List poMemberOrders = this.jdbcTemplate.queryForList(sql);
				
				String sqlArea = "Select Jsp.State_Province_Name From Jal_State_Province Jsp, (Select a.Week, Sum(b.Amount) Sumamount, b.Province From (Select f_Year || Lpad(f_Week, 2, 0) Week, Start_Time, End_Time From Jbd_Period) a, (Select Amount, Check_Date, Province From Jpo_Member_Order Where order_type In (" + orderType + ") And COMPANY_CODE ='" + loginSysUser.getCompanyCode() + "') b Where a.Start_Time <= b.Check_Date And a.End_Time > b.Check_Date Group By a.Week, b.Province) w Where Jsp.State_Province_Id = w.Province Group By Jsp.State_Province_Name";
				List poMemberOrderAreas = this.jdbcTemplate.queryForList(sqlArea);
				List reBuildList = reBuildList(poMemberOrders);
				
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition", "attachment; filename=Generate Report(Members Vs Commissions)to MemberOrders_" + new Date() + ".xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30" ); 
				OutputStream os = response.getOutputStream();
				ExcelUtil excelUtil = new ExcelUtil();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
				//加入期别时间段显示
				excelUtil.addString(wsheet, 0, 0, excelTitle);
				excelUtil.mergeCells(wsheet, 0, 0, poMemberOrderAreas.size(), 0);
				//标题
				excelUtil.addString(wsheet, 0, 1, LocaleUtil.getLocalText("bdBounsDeduct.wweek"));
				for(int m = 0 ; m < poMemberOrderAreas.size() ; m++){
					Map mapArea = (Map)poMemberOrderAreas.get(m);
					excelUtil.addString(wsheet, m + 1, 1, mapArea.get("state_province_name").toString());
	
					for (int i = 0; i < reBuildList.size(); i++) {
						Map reBuildMap = (Map) reBuildList.get(i);
						Iterator ite = reBuildMap.keySet().iterator();
						if(ite.hasNext()){
							String week = (String)ite.next();
								int index=0;
								excelUtil.addString(wsheet, 0, i + 2, week);
								Map mapTmp = (Map)reBuildMap.get(week);
								
								if(mapTmp.get(mapArea.get("state_province_name").toString()) == null){
									excelUtil.addNumber(wsheet, m + 1, i + 2, Double.parseDouble("0"));
								}else{
									excelUtil.addNumber(wsheet, m + 1, i + 2, Double.parseDouble(mapTmp.get(mapArea.get("state_province_name").toString()).toString()));
								}
								
						}
					}
				}
				
				excelUtil.writeExcel(wwb);
				excelUtil.closeWritableWorkbook(wwb);
				os.close();
				return null;
			}
			return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic");
		}else{//Modify By WUCF 20150522 报表功能
			String flag  = request.getParameter("flag");
			request.setAttribute("strAction", strAction);
			if("Y".equals(flag)){
				//====================Modify By WuCF 20150716 得到查询条件
				String startDate = request.getParameter("startDate");//起始时间
				String endDate = request.getParameter("endDate");//截止时间
				String formatedWeek = request.getParameter("formatedWeek");//期别
				String formatedYear = request.getParameter("formatedYear");//工作年
				String formatedMonth = request.getParameter("formatedMonth");//工作月
				String dateType = request.getParameter("dateType");//日期类型
				if(!StringUtil.isEmpty(startDate)){
					startDate = startDate.trim();
				}
				if(!StringUtil.isEmpty(endDate)){
					endDate = endDate.trim();
				}
				if(!StringUtil.isEmpty(formatedWeek)){
					formatedWeek = formatedWeek.trim();
					//Modify By WuCF 20150811 期别转自然周
					formatedWeek = WeekFormatUtil.getFormatWeek("f", formatedWeek);
				}
				if(!StringUtil.isEmpty(formatedYear)){
					formatedYear = formatedYear.trim();
				}
				if(!StringUtil.isEmpty(formatedMonth)){
					formatedMonth = formatedMonth.trim();
				}
					
				
				System.out.println("-"+startDate+"-"+endDate+"-"+formatedWeek+"-"+formatedYear+"-"+formatedMonth+"-"+strAction);
				//2.1~2.9:如果是9个报表功能的开发，如果输入条件为空的情况，则直接返回
				/*if("netLead".equals(strAction) || "areaLead".equals(strAction) || "recommendLead".equals(strAction) || 
				   "bigJpomemberorder".equals(strAction) || "huicuiProduct".equals(strAction) || "yunnanchongxiao".equals(strAction) || 
				   "daoheWineJpomemberorder".equals(strAction) || "daoheWineJprrefund".equals(strAction) || "jponetfee".equals(strAction)){
					if((!StringUtil.isDate(startDate)||!StringUtil.isDate(endDate))&&StringUtil.isEmpty(formatedWeek)&&(!StringUtil.isInteger(formatedYear)||!StringUtil.isInteger(formatedMonth))){
						this.saveMessage(request, LocaleUtil.getLocalText("please.input.search.condition"));
						return new ModelAndView("jpoMemberOrderAreaWeekStatistic.html?strAction="+strAction);
					}
				}*/
				//====================Modify By WuCF 20150716 得到查询条件
				
				CommonRecord crm = initCommonRecord(request);
				List list = null;
				List userList = null;//临时用户表
				if("zhongcenum".equals(strAction)){//1.中策加入人数统计
					String userCode = request.getParameter("userCode");//会员编号
					String yearMonth = request.getParameter("yearMonth");//财月
					
					//查询数据
					list = pdSendInfoManager.getZhongcenumByCrm(crm);
					userList = pdSendInfoManager.getZcwUsers(crm);
					
					//生成xls文件
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=ZhongCeRenShu_"+yearMonth+".xls");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("ZhongCeRenShu_", 0);
					//加入期别时间段显示
					//标题					
					excelUtil.addString(wsheet, 0, 0, "姓名");	
					excelUtil.mergeCells(wsheet, 0, 0, 0, 1);
					excelUtil.addString(wsheet, 1, 0, "三级");//三级
					excelUtil.mergeCells(wsheet, 1, 0, 3, 0);
					excelUtil.addString(wsheet, 4, 0, "二级");//二级
					excelUtil.mergeCells(wsheet, 4, 0, 6, 0);
					excelUtil.addString(wsheet, 7, 0, "一级");//一级
					excelUtil.mergeCells(wsheet, 7, 0, 9, 0);
					excelUtil.addString(wsheet, 10, 0, "无级别");//无级别
					excelUtil.mergeCells(wsheet, 10, 0, 12, 0);
					excelUtil.addString(wsheet, 13, 0, "优惠顾客");//优惠顾客
					excelUtil.mergeCells(wsheet, 13, 0, 15, 0);
					excelUtil.addString(wsheet, 16, 0, "总计");//总计
					excelUtil.mergeCells(wsheet, 16, 0, 18, 0);
					
					excelUtil.setColumnWidth(wsheet, 0, 25);
					excelUtil.setColumnWidth(wsheet, 1, 8);
					excelUtil.setColumnWidth(wsheet, 2, 8);
					excelUtil.setColumnWidth(wsheet, 3, 8);
					excelUtil.setColumnWidth(wsheet, 4, 8);
					excelUtil.setColumnWidth(wsheet, 5, 8);
					excelUtil.setColumnWidth(wsheet, 6, 8);
					excelUtil.setColumnWidth(wsheet, 7, 8);
					excelUtil.setColumnWidth(wsheet, 8, 8);
					excelUtil.setColumnWidth(wsheet, 9, 8);
					excelUtil.setColumnWidth(wsheet, 10, 8);
					excelUtil.setColumnWidth(wsheet, 11, 8);
					excelUtil.setColumnWidth(wsheet, 12, 8);
					excelUtil.setColumnWidth(wsheet, 13, 8);
					excelUtil.setColumnWidth(wsheet, 14, 8);
					excelUtil.setColumnWidth(wsheet, 15, 8);
					excelUtil.setColumnWidth(wsheet, 16, 8);
					excelUtil.setColumnWidth(wsheet, 17, 8);
					excelUtil.setColumnWidth(wsheet, 18, 8);
					
					//标题
					int m=1;
					excelUtil.addString(wsheet, m++, 1, "实际人数");//三级
					excelUtil.addString(wsheet, m++, 1, "一人单点");
					excelUtil.addString(wsheet, m++, 1, "一人多点");
					excelUtil.addString(wsheet, m++, 1, "实际人数");//二级
					excelUtil.addString(wsheet, m++, 1, "一人单点");
					excelUtil.addString(wsheet, m++, 1, "一人多点");
					excelUtil.addString(wsheet, m++, 1, "实际人数");//一级
					excelUtil.addString(wsheet, m++, 1, "一人单点");
					excelUtil.addString(wsheet, m++, 1, "一人多点");
					excelUtil.addString(wsheet, m++, 1, "实际人数");//无级别
					excelUtil.addString(wsheet, m++, 1, "一人单点");
					excelUtil.addString(wsheet, m++, 1, "一人多点");
					excelUtil.addString(wsheet, m++, 1, "实际人数");//优惠顾客
					excelUtil.addString(wsheet, m++, 1, "一人单点");
					excelUtil.addString(wsheet, m++, 1, "一人多点");
					excelUtil.addString(wsheet, m++, 1, "实际人数");//总计
					excelUtil.addString(wsheet, m++, 1, "一人单点");
					excelUtil.addString(wsheet, m++, 1, "一人多点");
					excelUtil.setColumnWidth(wsheet, 1, 8);
					excelUtil.setColumnWidth(wsheet, 2, 8);
					excelUtil.setColumnWidth(wsheet, 3, 8);
					excelUtil.setColumnWidth(wsheet, 4, 8);
					excelUtil.setColumnWidth(wsheet, 5, 8);
					excelUtil.setColumnWidth(wsheet, 6, 8);
					excelUtil.setColumnWidth(wsheet, 7, 8);
					excelUtil.setColumnWidth(wsheet, 8, 8);
					excelUtil.setColumnWidth(wsheet, 9, 8);
					excelUtil.setColumnWidth(wsheet, 10, 8);
					excelUtil.setColumnWidth(wsheet, 11, 8);
					excelUtil.setColumnWidth(wsheet, 12, 8);
					excelUtil.setColumnWidth(wsheet, 13, 8);
					excelUtil.setColumnWidth(wsheet, 14, 8);
					excelUtil.setColumnWidth(wsheet, 15, 8);
					excelUtil.setColumnWidth(wsheet, 16, 8);
					excelUtil.setColumnWidth(wsheet, 17, 8);
					excelUtil.setColumnWidth(wsheet, 18, 8);
					
					
					//会员级别数据：   5：优惠顾客    0：无级别   10：一级    20：二级    30：三级
					int kk = 2;//行数初始值
					Map map = null;
					Map map2 = null;
					Integer num1 = 0;//三级实际人数
					Integer num2 = 0;//三级一人单点
					Integer num3 = 0;//三级一人多点
					Integer num4 = 0;
					Integer num5 = 0;
					Integer num6 = 0;
					Integer num7 = 0;
					Integer num8 = 0;
					Integer num9 = 0;
					Integer num10 = 0;
					Integer num11 = 0;
					Integer num12 = 0;
					Integer num13 = 0;
					Integer num14 = 0;
					Integer num15 = 0;
					Integer num16 = 0;
					Integer num17 = 0;
					Integer num18 = 0;
					for (int i = 0; i < userList.size(); i++) {//循环中策用户信息
						num1 = 0;//三级实际人数
						num2 = 0;//三级一人单点
						num3 = 0;//三级一人多点
						num4 = 0;
						num5 = 0;
						num6 = 0;
						num7 = 0;
						num8 = 0;
						num9 = 0;
						num10 = 0;
						num11 = 0;
						num12 = 0;
						num13 = 0;
						num14 = 0;
						num15 = 0;
						num16 = 0;
						num17 = 0;
						num18 = 0;
						map = (Map) userList.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_CODE")+"-"+map.get("USER_NAME")));
						for(int j=0; j<list.size(); j++){//循环中策发展会员数量信息
							map2 = (Map) list.get(j);
							if(map.get("USER_CODE").equals(map2.get("TEAM_CODE"))){
								if("30".equals(map2.get("MEMBER_LEVEL"))){
									num1 = Integer.parseInt(map2.get("CNT").toString());
									num2 = Integer.parseInt(map2.get("S").toString());
									num3 = Integer.parseInt(map2.get("M").toString());
								}
								
								if("20".equals(map2.get("MEMBER_LEVEL"))){
									num4 = Integer.parseInt(map2.get("CNT").toString());
									num5 = Integer.parseInt(map2.get("S").toString());
									num6 = Integer.parseInt(map2.get("M").toString());
								}
								
								if("10".equals(map2.get("MEMBER_LEVEL"))){
									num7 = Integer.parseInt(map2.get("CNT").toString());
									num8 = Integer.parseInt(map2.get("S").toString());
									num9 = Integer.parseInt(map2.get("M").toString());
								}
								
								if("0".equals(map2.get("MEMBER_LEVEL"))){
									num10 = Integer.parseInt(map2.get("CNT").toString());
									num11 = Integer.parseInt(map2.get("S").toString());
									num12 = Integer.parseInt(map2.get("M").toString());
								}
								
								if("5".equals(map2.get("MEMBER_LEVEL"))){
									num13 = Integer.parseInt(map2.get("CNT").toString());
									num14 = Integer.parseInt(map2.get("S").toString());
									num15 = Integer.parseInt(map2.get("M").toString());
								}
								
								num16 = num1 + num4 + num7 + num10 + num13;//求和汇总
								num17 = num2 + num5 + num8 + num11 + num14;
								num18 = num3 + num6 + num9 + num12 + num15;
							}
						}
						//设置值
						excelUtil.addNumber(wsheet, index++, kk, num1);
						excelUtil.addNumber(wsheet, index++, kk, num2);
						excelUtil.addNumber(wsheet, index++, kk, num3);
						excelUtil.addNumber(wsheet, index++, kk, num4);
						excelUtil.addNumber(wsheet, index++, kk, num5);
						excelUtil.addNumber(wsheet, index++, kk, num6);
						excelUtil.addNumber(wsheet, index++, kk, num7);
						excelUtil.addNumber(wsheet, index++, kk, num8);
						excelUtil.addNumber(wsheet, index++, kk, num9);
						excelUtil.addNumber(wsheet, index++, kk, num10);
						excelUtil.addNumber(wsheet, index++, kk, num11);
						excelUtil.addNumber(wsheet, index++, kk, num12);
						excelUtil.addNumber(wsheet, index++, kk, num13);
						excelUtil.addNumber(wsheet, index++, kk, num14);
						excelUtil.addNumber(wsheet, index++, kk, num15);
						excelUtil.addNumber(wsheet, index++, kk, num16);
						excelUtil.addNumber(wsheet, index++, kk, num17);
						excelUtil.addNumber(wsheet, index++, kk, num18);
						kk++;
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("dayPerformance".equals(strAction)){//2.日业绩统计 
					//查询数据
					crm.setValue("startLogTime", startLogTime);//起始时间
					crm.setValue("endLogTime", endLogTime);//截止时间
					list = pdSendInfoManager.getDayPerformanceByCrm(crm);
					//统计增长率：
					String[] proportion = null;
					if(list!=null){
						if(list.size()<=1){
							proportion = new String[1];
							proportion[0] = "-";
						}else{
							proportion = new String[list.size()];//增长率
							proportion[0] = "-";
							
							BigDecimal t1;
							BigDecimal t2;
							BigDecimal temp = new BigDecimal(0);
							
							proportion[0] = "-";
							for(int i=0;i<list.size()-1;i++){
								for(int j=1;j<list.size();j++){
//									temp = ((Map<String,BigDecimal>)list.get(j)).get("AMT").subtract(((Map<String,BigDecimal>)list.get(i)).get("AMT")).multiply(new BigDecimal(100));
//									proportion[j] = String.valueOf(temp.divide((Map<String,BigDecimal>)list.get(j)).get("AMT"),2,BigDecimal.ROUND_HALF_UP));
									if(i+1==j){
										t1 = ((Map<String,BigDecimal>)list.get(j)).get("AMT");
										t2 = ((Map<String,BigDecimal>)list.get(i)).get("AMT");
										if("0".equals(String.valueOf(t2))){
											proportion[j] = "∞";
										}else{
											temp = t1.subtract(t2).multiply(new BigDecimal(100));
											temp = temp.divide(t2,2,BigDecimal.ROUND_HALF_UP);
											proportion[j] = String.valueOf(temp)+"%";
										}
									}
								}
							}
						}
					}
					
					
					//生成xls文件
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=rYeJi"+startLogTime+"_"+endLogTime+".xls");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("rYeJi", 0);
					//加入期别时间段显示
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "日期");	
					excelUtil.addString(wsheet, m++, 1, "每日销售额");
					excelUtil.addString(wsheet, m++, 1, "每日PV");
					excelUtil.addString(wsheet, m++, 1, "累计达成");
					excelUtil.addString(wsheet, m++, 1, "累计PV");
					excelUtil.addString(wsheet, m++, 1, "日销售额增长率");
					excelUtil.setColumnWidth(wsheet, 0, 10);
					excelUtil.setColumnWidth(wsheet, 1, 16);
					excelUtil.setColumnWidth(wsheet, 2, 16);
					excelUtil.setColumnWidth(wsheet, 3, 16);
					excelUtil.setColumnWidth(wsheet, 4, 16);
					excelUtil.setColumnWidth(wsheet, 5, 20);
					
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("F_DAY")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PV_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("LJ_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("LJ_PV_AMT")));
						excelUtil.addString(wsheet, index++, kk, proportion[i]);
						kk++;
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("province".equals(strAction)){//3.决策委省份统计
					String yearMonth = request.getParameter("yearMonth");// 财月
					String str ="%s第%s财政月中策业绩报表";
					// 有效财月的查询条件才允许查询
					if (!StringUtil.isEmpty(yearMonth) && (6 == yearMonth.length())) {
						String exercice = yearMonth.substring(0, yearMonth.length() - 2);
						String fortuneMonth = yearMonth.substring(yearMonth.length() - 2, yearMonth.length());
						if (0 < Integer.parseInt(fortuneMonth) && Integer.parseInt(fortuneMonth) < 10) {
							fortuneMonth = yearMonth.substring(yearMonth.length() - 1, yearMonth.length());
						}
						crm.setValue("exercice", exercice);
						crm.setValue("fortuneMonth", fortuneMonth);
						str = String.format(str, new Object[]{exercice,fortuneMonth});
						// 查询数据
						 list = pdSendInfoManager.getProvinceByCrm(crm);
					}
					/* //excel导出相关操作 */
					response.setContentType("application/vnd.ms-excel");// 定义导出文件格式
					response.addHeader("Content-Disposition", "attachment; filename=jueCeWei(" + yearMonth + ").xls");// 定义导出文件的名字
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30");
					String config = request.getSession().getServletContext().getRealPath("./") + "/WEB-INF/xls/JCSjueCeSheng.xls";
					OutputStream os = response.getOutputStream();
					Workbook wb = Workbook.getWorkbook(new File(config)); // 调用模版
					ExcelUtil excelUtil = new ExcelUtil();// 创建一共ExcelUtil对象
					WritableWorkbook wwb = Workbook.createWorkbook(os, wb);
					WritableSheet wsheet = wwb.getSheet(0); //拿到第一个工作簿
					userList = pdSendInfoManager.getZcwUsers(crm);// 获取决策委名单信息
					excelUtil.addString(wsheet, 0, 0, str);
					int m = 2;
					String[] arr = { "0", "1", "2", "黑龙江省", "吉林省", "辽宁省", "青海省", "西藏自治区", "东北汇总", "北京市", "甘肃省", "河北省", "河南省", "内蒙古自治区", "宁夏回族自治区", "山东省", "山西省", "陕西省", "天津市",
							"新疆维吾尔自治区", "华北汇总", "安徽省", "湖北省", "湖南省", "江苏省", "江西省", "上海市", "浙江省", "华东汇总", "福建省", "广东省", "广西壮族自治区", "贵州省", "海南省", "四川省", "云南省", "重庆市", "华南汇总", "总计" };
					int dbIdx = getArrIndex(arr, "东北汇总");
					int hbIdx = getArrIndex(arr, "华北汇总");
					int hdIdx = getArrIndex(arr, "华东汇总");
					int hnIdx = getArrIndex(arr, "华南汇总");
					int zjIdx = getArrIndex(arr, "总计");
					if (list != null && list.size() > 0) { // 绑定数据库中的值
						for (int i = 0; i < list.size(); i++) {
							Map map = (Map) list.get(i);
							if(map.get("PROVINCE")!=null && !"".equals(map.get("PROVINCE"))){
								Integer pIdx = getArrIndex(arr, map.get("PROVINCE").toString());// 行号
								Integer tIdx = getListIndex(userList, map.get("TEAM_CODE").toString());// 列号
								if (pIdx > 2 && tIdx > -1) {
									Double amt = 0.0;
									if (map.get("AMT") != null && !"".equals(map.get("AMT"))) {
										amt = Double.parseDouble(map.get("AMT").toString());
									}
									excelUtil.addString(wsheet, tIdx + 2, pIdx, String.valueOf(amt));
								}
							}
						}
					}
					WritableCellFormat wcf_title = new WritableCellFormat(); // 单元格定义
					wcf_title.setBackground(jxl.format.Colour.YELLOW); // 设置单元格的背景颜色
					wcf_title.setBorder(Border.ALL,BorderLineStyle.THIN);
					
					WritableCellFormat wcf_title2 = new WritableCellFormat(); // 单元格定义
					wcf_title2.setBackground(jxl.format.Colour.GREY_40_PERCENT); // 设置单元格的背景颜色
					wcf_title2.setBorder(Border.ALL,BorderLineStyle.THIN);
					
					WritableCellFormat wcf_title3 = new WritableCellFormat(); // 单元格定义
					wcf_title3.setBorder(Border.ALL,BorderLineStyle.THIN);
					Double dbTotal, hbTotal, hdTotal, hnTotal, total;
					for (int i = 0; i < userList.size(); i++) {
						dbTotal = 0.0;
						hbTotal = 0.0;
						hdTotal = 0.0;
						hnTotal = 0.0;
						total = 0.0;
						Map zcwUsers = (Map) userList.get(i);
						// 定义表头
						excelUtil.addString(wsheet, m++, 2, zcwUsers.get("USER_CODE").toString()+"_"+zcwUsers.get("USER_NAME").toString(), wcf_title);
						for (int k = 3; k < arr.length; k++) {
							String s = excelUtil.getContents(wsheet, i + 2, k);
							if (StringUtils.isNotEmpty(s)) {
								if (k < dbIdx) {
									dbTotal += Double.parseDouble(s);
								} else if (k > dbIdx && k < hbIdx) {
									hbTotal += Double.parseDouble(s);
								} else if (k > hbIdx && k < hdIdx) {
									hdTotal += Double.parseDouble(s);
								} else if (k > hdIdx && k < hnIdx) {
									hnTotal += Double.parseDouble(s);
								}
							} else {
								excelUtil.addNumber(wsheet, i + 2, k, 0,wcf_title3);
							}
						}
						total = dbTotal + hbTotal + hdTotal + hnTotal;
						excelUtil.addNumber(wsheet, i + 2, dbIdx, dbTotal,wcf_title2);
						excelUtil.addNumber(wsheet, i + 2, hbIdx, hbTotal,wcf_title2);
						excelUtil.addNumber(wsheet, i + 2, hdIdx, hdTotal,wcf_title2);
						excelUtil.addNumber(wsheet, i + 2, hnIdx, hnTotal,wcf_title2);
						excelUtil.addNumber(wsheet, i + 2, zjIdx, total,wcf_title);
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					return null;
				}else if("netLead".equals(strAction)){//==========================2.1领导人网体业绩
					//查询数据
					crm.addField("startDate", startDate);
					crm.addField("endDate", endDate);
					crm.addField("fyearweek", formatedWeek);
					crm.addField("wyear", formatedYear);
					crm.addField("wweek", formatedMonth);
					crm.addField("dateType", dateType);
					list = pdSendInfoManager.getNetLeadByCrm(crm);
					
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=lingdaorenwangtiyeji.xls");//设置报表名称
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("sheet1", 0);
					//加入期别时间段显示
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "策略委编号");	
					excelUtil.addString(wsheet, m++, 1, "策略委姓名");
					excelUtil.addString(wsheet, m++, 1, "推荐网体业绩pv");
					excelUtil.addString(wsheet, m++, 1, "推荐网体退单业绩pv");
					excelUtil.addString(wsheet, m++, 1, "推荐网体业绩金额");
					excelUtil.addString(wsheet, m++, 1, "推荐网体退单业绩金额");
					excelUtil.addString(wsheet, m++, 1, "安置网体业绩pv");
					excelUtil.addString(wsheet, m++, 1, "安置网体退单业绩pv");
					excelUtil.addString(wsheet, m++, 1, "安置网体业绩金额");
					excelUtil.addString(wsheet, m++, 1, "安置网体退单业绩金额");
					excelUtil.setColumnWidth(wsheet, 0, 20);
					excelUtil.setColumnWidth(wsheet, 1, 20);
					excelUtil.setColumnWidth(wsheet, 2, 20);
					excelUtil.setColumnWidth(wsheet, 3, 20);
					excelUtil.setColumnWidth(wsheet, 4, 20);
					excelUtil.setColumnWidth(wsheet, 5, 20);
					excelUtil.setColumnWidth(wsheet, 6, 20);//
					excelUtil.setColumnWidth(wsheet, 7, 20);
					excelUtil.setColumnWidth(wsheet, 8, 20);
					excelUtil.setColumnWidth(wsheet, 9, 20);
					
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_CODE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_NAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PV_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("RE_PV_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("AMOUNT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("RE_AMOUNT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("AZPV_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("RE_AZPV_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("AZAMOUNT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("RE_AZAMOUNT")));
						kk++;
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("areaLead".equals(strAction)){//==========================2.2领导人区域业绩
					//查询数据
					crm.addField("startDate", startDate);
					crm.addField("endDate", endDate);
					crm.addField("fyearweek", formatedWeek);
					crm.addField("wyear", formatedYear);
					crm.addField("wweek", formatedMonth);
					crm.addField("dateType", dateType);
					list = pdSendInfoManager.getAreaLeadByCrm(crm);
					
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=lingdaorenquyuyeji.xls");//设置报表名称
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("sheet1", 0);
					//加入期别时间段显示
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "策略委编号");	
					excelUtil.addString(wsheet, m++, 1, "策略委姓名");
					excelUtil.addString(wsheet, m++, 1, "所属大区");
					excelUtil.addString(wsheet, m++, 1, "省市");
					excelUtil.addString(wsheet, m++, 1, "推荐网体各省市业绩pv");
					excelUtil.addString(wsheet, m++, 1, "推荐网体各省市业绩金额");
					excelUtil.setColumnWidth(wsheet, 0, 16);
					excelUtil.setColumnWidth(wsheet, 1, 16);
					excelUtil.setColumnWidth(wsheet, 2, 20);
					excelUtil.setColumnWidth(wsheet, 3, 20);
					excelUtil.setColumnWidth(wsheet, 4, 20);
					excelUtil.setColumnWidth(wsheet, 5, 20);
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_CODE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_NAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("AREA")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PROVINCE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PV_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("AMOUNT")));
						kk++;
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("recommendLead".equals(strAction)){//==========================2.3领导人推荐网体加入人数
					//查询数据
					crm.addField("startDate", startDate);
					crm.addField("endDate", endDate);
					crm.addField("fyearweek", formatedWeek);
					crm.addField("wyear", formatedYear);
					crm.addField("wweek", formatedMonth);
					crm.addField("dateType", dateType);
					list = pdSendInfoManager.getRecommendLeadByCrm(crm);
					
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=lingdaorentuijianwangtirenshu.xls");//设置报表名称
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("sheet1", 0);
					//加入期别时间段显示
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "策略委编号");	
					excelUtil.addString(wsheet, m++, 1, "策略委姓名");
					excelUtil.addString(wsheet, m++, 1, "财月加入人数");
					excelUtil.setColumnWidth(wsheet, 0, 20);
					excelUtil.setColumnWidth(wsheet, 1, 20);
					excelUtil.setColumnWidth(wsheet, 2, 20);
					
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("LINK_CODE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_NAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("CNT")));
						kk++;
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("bigJpomemberorder".equals(request.getParameter("strAction"))){//==========================2.4 30万大单数据
					//查询数据
					crm.addField("startDate", startDate);
					crm.addField("endDate", endDate);
					crm.addField("fyearweek", formatedWeek);
					crm.addField("wyear", formatedYear);
					crm.addField("wweek", formatedMonth);
					list = pdSendInfoManager.getbigJpomemberorderByCrm(crm);
					
					if (StringUtils.isNotEmpty(formatedWeek)) {
						if (formatedWeek.length() != 6) {
							saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
							return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2"); // "redirect:/bdRepeatedConsumption.html"
						} else {
							BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
							if (bdPeriod == null) {
								saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
								return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
							}
						}
					}/*else if(StringUtils.isNotEmpty(formatedYear)&& StringUtils.isNotEmpty(formatedMonth)){
						
					}else{
						if(StringUtil.isEmpty(startDate)){
							this.saveMessage(request, "请选择日期!");
							return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
						}
						if(StringUtil.isEmpty(endDate)){
							this.saveMessage(request, "请选择日期!");
							return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
						}
					}*/
						
					//生成报表	
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=bigJpomemberorder_"+formatedWeek+".xls");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil eu = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					
					WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
					
					//加入期别时间段显示
					eu.addString(wsheet, 0, 0, startDate+"~"+endDate);
					//标题
					int i=0;
					eu.addString(wsheet, i++, 1, "身份证号");
					eu.addString(wsheet, i++, 1, "会员编号");
					eu.addString(wsheet, i++, 1, "会员姓名");	
					eu.addString(wsheet, i++, 1, "会员级别");
					eu.addString(wsheet, i++, 1, "生活馆级别");
					eu.addString(wsheet, i++, 1, "上属中策编号");
					eu.addString(wsheet, i++, 1, "上属中策姓名");
					eu.addString(wsheet, i++, 1, "上属中策类型");
					eu.addString(wsheet, i++, 1, formatedWeek+"财月各项订单业绩金额");
					eu.addString(wsheet, i++, 1, "身份证省");
					eu.addString(wsheet, i++, 1, "身份证市");
					eu.addString(wsheet, i++, 1, "身份证区");
					eu.addString(wsheet, i++, 1, "身份证地址");
					eu.addString(wsheet, i++, 1, "手机号码");
					
					int kk = 2;
					for (int k = 0; k < list.size(); k++) {
						Map<String, String> repMap = (Map<String, String>) list.get(k);
						int index=0;
						eu.addString(wsheet, index++, kk, repMap.get("PAPERNUMBER"));
						eu.addString(wsheet, index++, kk, repMap.get("USER_CODE"));
						eu.addString(wsheet, index++, kk, repMap.get("MI_USER_NAME"));
						eu.addString(wsheet, index++, kk, repMap.get("CARD_TYPE"));
						eu.addString(wsheet, index++, kk, repMap.get("ISSTORE"));
						eu.addString(wsheet, index++, kk, repMap.get("ZCW_CODE"));
						eu.addString(wsheet, index++, kk, repMap.get("ZCW_NAME"));
						eu.addString(wsheet, index++, kk, repMap.get("ZCWTYPE"));
						eu.addString(wsheet, index++, kk, String.valueOf(repMap.get("AMOUNT")));
						eu.addString(wsheet, index++, kk, repMap.get("PROVINCE"));
						eu.addString(wsheet, index++, kk, repMap.get("CITY"));
						eu.addString(wsheet, index++, kk, repMap.get("DISTRICT"));
						eu.addString(wsheet, index++, kk, repMap.get("ADDRESS"));
						eu.addString(wsheet, index++, kk, repMap.get("MOBILETELE"));
						kk++;
					}
					eu.writeExcel(wwb);
					eu.closeWritableWorkbook(wwb);
					os.close();
					return null;
					
				}else if("huicuiProduct".equals(strAction)){//==========================2.5颐萃产品统计
					
					crm.addField("startDate", startDate);
					crm.addField("endDate", endDate);
					crm.addField("fyearweek", formatedWeek);
					crm.addField("wyear", formatedYear);
					crm.addField("wweek", formatedMonth);
					//查询数据
					list = pdSendInfoManager.getHuicuiProductByCrm(crm);
					
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=YICproduct_"+formatedWeek+".xls");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil eu = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					
					WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
					
					//加入期别时间段显示
					eu.addString(wsheet, 0, 0, startDate+"~"+endDate);
					//标题
					int i=0;
					eu.addString(wsheet, i++, 1, "会员编号");
					eu.addString(wsheet, i++, 1, "会员姓名");	
					eu.addString(wsheet, i++, 1, "P1801010101CN0颐萃玛咖片（男士）");
					eu.addString(wsheet, i++, 1, "P18030100101CN0颐萃玛咖片（男士礼盒装）");
					eu.addString(wsheet, i++, 1, "P1802010101CN0颐萃玛咖多肽片（女士）");
					eu.addString(wsheet, i++, 1, "P18040100101CN0颐萃玛咖多肽片（女士礼盒装）");
					eu.addString(wsheet, i++, 1, "P01090100101CN0颐萃经典牌康秀胶囊");
					eu.addString(wsheet, i++, 1, "P01100100101CN0颐萃海洋鱼水解胶原蛋白低聚肽");
					
					int kk = 2;
					for (int k = 0; k < list.size(); k++) {
						Map<String, String> repMap = (Map<String, String>) list.get(k);
						int index=0;
						eu.addString(wsheet, index++, kk, repMap.get("USER_CODE"));
						eu.addString(wsheet, index++, kk, repMap.get("USER_NAME"));
						eu.addString(wsheet, index++, kk, repMap.get("PRODUCT_NO1"));
						eu.addString(wsheet, index++, kk, repMap.get("PRODUCT_NO2"));
						eu.addString(wsheet, index++, kk, repMap.get("PRODUCT_NO3"));
						eu.addString(wsheet, index++, kk, repMap.get("PRODUCT_NO4"));
						eu.addString(wsheet, index++, kk, repMap.get("PRODUCT_NO5"));
						eu.addString(wsheet, index++, kk, repMap.get("PRODUCT_NO6"));
						kk++;
					}
					eu.writeExcel(wwb);
					eu.closeWritableWorkbook(wwb);
					os.close();
					return null;
					
				}else if("yunnanchongxiao".equals(strAction)){//==========================2.6云南团队保养贴
					
					crm.addField("startDate", startDate);
					crm.addField("endDate", endDate);
					crm.addField("fyearweek", formatedWeek);
					crm.addField("wyear", formatedYear);
					crm.addField("wweek", formatedMonth);
					log.info("---2.6:1"+startDate+"---"+endDate);
					if (StringUtils.isNotEmpty(formatedWeek)) {
						if (formatedWeek.length() != 6) {
							saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
							return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2"); // "redirect:/bdRepeatedConsumption.html"
						} else {
							BdPeriod bdPeriod = bdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
							if (bdPeriod == null) {
								saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
								return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
							}
						}
					}/*else if(StringUtils.isNotEmpty(formatedYear)&& StringUtils.isNotEmpty(formatedMonth)){
						
					}else{
						if(StringUtil.isEmpty(startDate)){
							this.saveMessage(request, "请选择日期!");
							return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
						}
						if(StringUtil.isEmpty(endDate)){
							this.saveMessage(request, "请选择日期!");
							return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
						}
					}*/
					/*//查询数据
					log.info("---2.6:2"+startDate+"---"+endDate);
					list = pdSendInfoManager.getYunnanchongxiaoByCrm(crm);
					log.info("---2.6:3"+list);
					//生成报表	
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=yunnanchongxiao_"+formatedWeek+".xls");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil eu = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					
					WritableSheet wsheet = wwb.createSheet("Sheet1", 0);
					
					//加入期别时间段显示
					eu.addString(wsheet, 0, 0, startDate+"~"+endDate);
					log.info("---2.6:4");
					//标题
					int i=0;
					eu.addString(wsheet, i++, 1, "会员编号");
					eu.addString(wsheet, i++, 1, "会员名称");	
					eu.addString(wsheet, i++, 1, "联系方式");
					eu.addString(wsheet, i++, 1, "店铺类型");
					eu.addString(wsheet, i++, 1, "订单类型");
					eu.addString(wsheet, i++, 1, "订单编号");
					eu.addString(wsheet, i++, 1, "商品编号");
					eu.addString(wsheet, i++, 1, "商品数量");
					eu.addString(wsheet, i++, 1, "订购时间");
					log.info("---2.6:5");
					int kk = 2;
					for (int k = 0; k < list.size(); k++) {
						Map<String, String> repMap = (Map<String, String>) list.get(k);
						log.info("---repMap:"+repMap);
						int index=0;
						eu.addString(wsheet, index++, kk, repMap.get("USER_CODE"));
						eu.addString(wsheet, index++, kk, repMap.get("MI_USER_NAME"));
						eu.addString(wsheet, index++, kk, repMap.get("MOBILETELE"));
						eu.addString(wsheet, index++, kk, repMap.get("STYPE"));
						eu.addString(wsheet, index++, kk, repMap.get("ORDER_TYPE"));
						eu.addString(wsheet, index++, kk, repMap.get("MEMBER_ORDER_NO"));
						eu.addString(wsheet, index++, kk, repMap.get("PRODUCT_NO"));
						eu.addString(wsheet, index++, kk, repMap.get("QTY"));
						eu.addString(wsheet, index++, kk, repMap.get("ORDER_TIME"));
						kk++;
					}
					log.info("---2.6:6");
					eu.writeExcel(wwb);
					eu.closeWritableWorkbook(wwb);
					os.close();
					return null;*/
					list = pdSendInfoManager.getYunnanchongxiaoByCrm(crm);
					
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=yunnantuandui.xls");//设置报表名称
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("sheet1", 0);
					//加入期别时间段显示
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "会员编号");	
					excelUtil.addString(wsheet, m++, 1, "会员名称");
					excelUtil.addString(wsheet, m++, 1, "联系方式");
					excelUtil.addString(wsheet, m++, 1, "店铺类型");
					excelUtil.addString(wsheet, m++, 1, "订单类型");
					excelUtil.addString(wsheet, m++, 1, "订单编号");
					excelUtil.addString(wsheet, m++, 1, "商品编号");
					excelUtil.addString(wsheet, m++, 1, "商品数量");
					excelUtil.addString(wsheet, m++, 1, "订购时间");
					excelUtil.setColumnWidth(wsheet, 0, 16);
					excelUtil.setColumnWidth(wsheet, 1, 16);
					excelUtil.setColumnWidth(wsheet, 2, 20);
					excelUtil.setColumnWidth(wsheet, 3, 16);
					excelUtil.setColumnWidth(wsheet, 4, 16);
					excelUtil.setColumnWidth(wsheet, 5, 30);
					excelUtil.setColumnWidth(wsheet, 6, 16);
					excelUtil.setColumnWidth(wsheet, 7, 16);
					excelUtil.setColumnWidth(wsheet, 8, 16);
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, map.get("USER_CODE")!=null ? String.valueOf(map.get("USER_CODE")) :"" );
						excelUtil.addString(wsheet, index++, kk, map.get("MI_USER_NAME")!=null ? String.valueOf(map.get("MI_USER_NAME")) :"" );
						excelUtil.addString(wsheet, index++, kk, map.get("MOBILETELE")!=null ? String.valueOf(map.get("MOBILETELE")) :"" );
						excelUtil.addString(wsheet, index++, kk, map.get("STYPE")!=null ? String.valueOf(map.get("STYPE")) :"" );
						excelUtil.addString(wsheet, index++, kk, map.get("ORDER_TYPE")!=null ? String.valueOf(map.get("ORDER_TYPE")) :"" );
						excelUtil.addString(wsheet, index++, kk, map.get("MEMBER_ORDER_NO")!=null ? String.valueOf(map.get("MEMBER_ORDER_NO")) :"" );
						excelUtil.addString(wsheet, index++, kk, map.get("PRODUCT_NO")!=null ? String.valueOf(map.get("PRODUCT_NO")) :"" );
						excelUtil.addString(wsheet, index++, kk, map.get("QTY")!=null ? String.valueOf(map.get("QTY")) :"" );
						excelUtil.addString(wsheet, index++, kk, map.get("ORDER_TIME")!=null ? String.valueOf(map.get("ORDER_TIME")) :"" );
						/*excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("MI_USER_NAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("MOBILETELE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("STYPE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ORDER_TYPE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("MEMBER_ORDER_NO")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PRODUCT_NO")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("QTY")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ORDER_TIME")));*/
						kk++;
					}
					log.info("---2.6:-----");
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("daoheWineJpomemberorder".equals(strAction)){//==========================2.7道和坛酒数订单
					//查询数据
					list = pdSendInfoManager.getDaoheWineJpomemberorderByCrm(crm);
					
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=lingdaorentuijianwangtirenshu.xls");//设置报表名称
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("sheet1", 0);
					//加入期别时间段显示
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "会员编号");	
					excelUtil.addString(wsheet, m++, 1, "会员名称");
					excelUtil.addString(wsheet, m++, 1, "订单编号");
					excelUtil.addString(wsheet, m++, 1, "对应酒产品编码");
					excelUtil.addString(wsheet, m++, 1, "PV");
					excelUtil.addString(wsheet, m++, 1, "收货地址");
					excelUtil.addString(wsheet, m++, 1, "审核日期");
					excelUtil.addString(wsheet, m++, 1, "所属中策编号");
					excelUtil.addString(wsheet, m++, 1, "所属中策姓名");
					excelUtil.addString(wsheet, m++, 1, "数量");
					excelUtil.setColumnWidth(wsheet, 0, 16);
					excelUtil.setColumnWidth(wsheet, 1, 16);
					excelUtil.setColumnWidth(wsheet, 2, 20);
					excelUtil.setColumnWidth(wsheet, 3, 16);
					excelUtil.setColumnWidth(wsheet, 4, 16);
					excelUtil.setColumnWidth(wsheet, 5, 30);
					excelUtil.setColumnWidth(wsheet, 6, 16);
					excelUtil.setColumnWidth(wsheet, 7, 16);
					excelUtil.setColumnWidth(wsheet, 8, 16);
					excelUtil.setColumnWidth(wsheet, 9, 16);
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_CODE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USERNAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("MEMBER_ORDER_NO")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PRODUCT_NO")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PV_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ADDR")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("CHECK_DATE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ZCW_CODE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ZCW_NAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("QTY")));
						kk++;
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("daoheWineJprrefund".equals(strAction)){//==========================2.8道和坛酒数退单数据
					//查询数据
					list = pdSendInfoManager.getDaoheWineJprrefundByCrm(crm);
					
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=daohetanjiushutuidan.xls");//设置报表名称
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("sheet1", 0);
					//加入期别时间段显示
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "会员编号");	
					excelUtil.addString(wsheet, m++, 1, "会员名称");
					excelUtil.addString(wsheet, m++, 1, "审核日期");
					excelUtil.addString(wsheet, m++, 1, "订单编号");
					excelUtil.addString(wsheet, m++, 1, "对应酒产品编码");
					excelUtil.addString(wsheet, m++, 1, "PV");
					excelUtil.addString(wsheet, m++, 1, "收货地址");
					excelUtil.addString(wsheet, m++, 1, "审核日期");
					excelUtil.addString(wsheet, m++, 1, "所属中策编号");
					excelUtil.addString(wsheet, m++, 1, "所属中策姓名");
					excelUtil.addString(wsheet, m++, 1, "数量");
					excelUtil.setColumnWidth(wsheet, 0, 16);
					excelUtil.setColumnWidth(wsheet, 1, 16);
					excelUtil.setColumnWidth(wsheet, 2, 20);
					excelUtil.setColumnWidth(wsheet, 3, 16);
					excelUtil.setColumnWidth(wsheet, 4, 16);
					excelUtil.setColumnWidth(wsheet, 5, 30);
					excelUtil.setColumnWidth(wsheet, 6, 16);
					excelUtil.setColumnWidth(wsheet, 7, 16);
					excelUtil.setColumnWidth(wsheet, 8, 16);
					excelUtil.setColumnWidth(wsheet, 9, 16);
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_CODE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_NAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("CHECK_DATE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("MEMBER_ORDER_NO")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PRODUCT_NO")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PV_AMT")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ADDR")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("CHECK_DATE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ZCW_CODE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("ZCW_NAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("QTY")));
						kk++;
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("jponetfee".equals(strAction)){//==========================2.9网络费物流费
					//查询数据
					crm.addField("startDate", startDate);
					crm.addField("endDate", endDate);
					crm.addField("fyearweek", formatedWeek);
					crm.addField("wyear", formatedYear);
					crm.addField("wweek", formatedMonth);
					list = pdSendInfoManager.getJponetfeeByCrm(crm);
					
					//生成报表
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=lingdaorentuijianwangtirenshu.xls");//设置报表名称
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("sheet1", 0);
					//加入期别时间段显示
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "结算期别");
					excelUtil.addString(wsheet, m++, 1, "会员首购单物流费");
					excelUtil.addString(wsheet, m++, 1, "会员重消单物流费");
					excelUtil.addString(wsheet, m++, 1, "全年重消单物流费");
					excelUtil.addString(wsheet, m++, 1, "一级店铺首购单物流费");
					excelUtil.addString(wsheet, m++, 1, "一级店铺重消单物流费");
					excelUtil.addString(wsheet, m++, 1, "二级店铺首购单物流费");
					excelUtil.addString(wsheet, m++, 1, "二级店铺重消单物流费");
					excelUtil.addString(wsheet, m++, 1, "二级店铺升级单物流费");
					excelUtil.addString(wsheet, m++, 1, "会员续费单物流费");
					excelUtil.addString(wsheet, m++, 1, "辅销品订单物流费");
					excelUtil.addString(wsheet, m++, 1, "奖衔补单物流费");
					excelUtil.addString(wsheet, m++, 1, "网络费");
					excelUtil.addString(wsheet, m++, 1, "总物流费");
					excelUtil.setColumnWidth(wsheet, 0, 16);
					excelUtil.setColumnWidth(wsheet, 1, 20);
					excelUtil.setColumnWidth(wsheet, 2, 20);
					excelUtil.setColumnWidth(wsheet, 3, 20);
					excelUtil.setColumnWidth(wsheet, 4, 20);
					excelUtil.setColumnWidth(wsheet, 5, 20);
					excelUtil.setColumnWidth(wsheet, 6, 20);
					excelUtil.setColumnWidth(wsheet, 7, 20);
					excelUtil.setColumnWidth(wsheet, 8, 20);
					excelUtil.setColumnWidth(wsheet, 9, 20);
					excelUtil.setColumnWidth(wsheet, 10, 20);
					excelUtil.setColumnWidth(wsheet, 11, 16);
					excelUtil.setColumnWidth(wsheet, 12, 16);
					excelUtil.setColumnWidth(wsheet, 13, 16);
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("PERIOD")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("SD_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("HC_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("QC_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("YDS_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("YDC_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("EDS_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("EDC_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("EDSJ_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("HX_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("FX_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("JX_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("NET_FEE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("TOTAL_FEE")));
						kk++; 
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}else if("batchMobileSend".equals(strAction)){//==========================3.1手机批量发送短信
					//查询数据
					String mobiles = request.getParameter("mobiles");
					System.out.println("mobiles："+mobiles);
					String message = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "seninfo.message");
					String[] mobileStrs = mobiles.split(",");
					SysUser sessionLogin = SessionLogin.getLoginUser(request);
					if(message==null || "".equals(message)){
						message = "尊敬的中脉家人，请及时下载更新最新手机端APP(下载地址：http://e.jmtop.com/m.htm)，并检查订单相关数据，谢谢配合！";
					}
					for(String mobile : mobileStrs){				
						log.info("发货短信message:"+message);
						String url1 = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.url", "1");
						String url2 = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.url", "2");
						System.out.println("========1："+url1+mobile+url2);
						SmsSend.sendSms(url1,url2,mobile, message.toString());
						System.out.println("========2："+url1+mobile+url2);
						//将短信写入到数据库表
						JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
						jpmSmssendInfo.setSmsType("5");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
						jpmSmssendInfo.setSmsRecipient("##########");//短信接收人:用户会员编号
						jpmSmssendInfo.setSmsMessage(message.toString());//短信内容
						jpmSmssendInfo.setSmsTime(new Date());//发送时间
						jpmSmssendInfo.setSmsOperator(sessionLogin.getUserCode());//操作人
						jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
						jpmSmssendInfo.setRemark("批量发送短信");//备注
						jpmSmssendInfo.setPhoneNum(mobile);//手机号码
						jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
					}
					request.setAttribute("strAction", strAction);
					return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
				}else if("shiyetilingdaoshoudan".equals(strAction)){//==========================事业体领导首单5500PV
					//查询数据
					crm.addField("startDate", startDate);
					crm.addField("endDate", endDate);
					crm.addField("fyearweek", formatedWeek);
					crm.addField("wyear", formatedYear);
					crm.addField("wweek", formatedMonth);
					list = pdSendInfoManager.getShiyeTiLingDaoShouDanByCrm(crm);
					
					//生成报表
					response.setContentType("application/vnd.ms-excel");
					response.addHeader("Content-Disposition", "attachment; filename=shiyetilingdaoshoudan.xls");//设置报表名称
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=30" ); 
					OutputStream os = response.getOutputStream();
					ExcelUtil excelUtil = new ExcelUtil();
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					WritableSheet wsheet = wwb.createSheet("sheet1", 0);
					//标题
					int m=0;
					excelUtil.addString(wsheet, m++, 1, "事业体领导人编号");	
					excelUtil.addString(wsheet, m++, 1, "事业体领导人姓名");
					excelUtil.addString(wsheet, m++, 1, "安置网体≥5500pv首购单数量");
					excelUtil.addString(wsheet, m++, 1, "安置网体≥5500pv首购单业绩（总金额+基金总额）");
					excelUtil.setColumnWidth(wsheet, 0, 20);
					excelUtil.setColumnWidth(wsheet, 1, 20);
					excelUtil.setColumnWidth(wsheet, 2, 35);
					excelUtil.setColumnWidth(wsheet, 3, 45);
					
					int kk = 2;
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						int index=0;
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_CODE")==null?"":map.get("USER_CODE")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("USER_NAME")==null?"":map.get("USER_NAME")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("QUANTITY")));
						excelUtil.addString(wsheet, index++, kk, String.valueOf(map.get("AMOUNT")));
						kk++;
					}
					excelUtil.writeExcel(wwb);
					excelUtil.closeWritableWorkbook(wwb);
					os.close();
					
					return null;
				}
				return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
			}else{
				request.setAttribute("startLogTime", startLogTime);
				request.setAttribute("endLogTime", endLogTime);
				request.setAttribute("defaultYearMonth", defaultYearMonth);
				return new ModelAndView("po/jpoMemberOrderAreaWeekStatistic2");
			}
		}
	}
	
	/**
	 * 重构数据
	 * @param list
	 * @param area
	 * @return
	 */
	public List reBuildList(List list){
		List newList = new ArrayList();
		//Map newMap = new HashMap();
//		if( area.size() != 0 ){
			for(int i = 0 ; i < list.size() ; i++){
				Map map = (Map)list.get(i);
				boolean isWeekIn = false;
				for(int m = 0 ; m < newList.size() ; m++){
					Map newMap = (Map)newList.get(m);
					if(newMap.get(map.get("week").toString())!=null){
						Map newMapMap = (Map)newMap.get(map.get("week").toString());
						newMapMap.put(map.get("state_province_name").toString(), map.get("sumamount").toString());
						isWeekIn = true;
						break;
					}
				}
				if(isWeekIn==false){
					Map newMap = new HashMap();
					newMap.put(map.get("week").toString(), new HashMap());
					Map newMapMap = (Map)newMap.get(map.get("week").toString());
					newMapMap.put(map.get("state_province_name").toString(), map.get("sumamount").toString());
					newList.add(newMap);
				}
			}
//		}
		return newList;
	}
	private Integer getArrIndex(Object[] arr, String targetValue) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(targetValue.trim())) {
				return i;
			}
		}
		return -1;
	}

	
	private Integer getListIndex(List list, String targetValue) {
		for (int i = 0; i < list.size(); i++) {
			Map zcwUsers = (Map) list.get(i);
			if (zcwUsers.get("USER_CODE").equals(targetValue.trim())) {
				return i;
			}
		}
		return -1;
	}
}
