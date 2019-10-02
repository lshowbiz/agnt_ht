package com.joymain.jecs.po.webapp.action;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
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
 * 领导人网体结算业绩报表，领导人团队奖金报表，公司结算业绩查询报表
 * 
 * @author lihao
 * 
 */
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class JpoMemberOrderClearingPerformanceStatisticController extends BaseController
		implements Controller {
	private final Log log = LogFactory
			.getLog(JpoMemberOrderClearingPerformanceStatisticController.class);

	private JdbcTemplate jdbcTemplate = null;
	// private PdSendInfoManager pdSendInfoManager = null;

	private JdbcTemplate jdbcTemplateReport;
	private ExcelUtil excelUtil = null;
	private WritableSheet wsheet = null;
	
	private BdPeriodManager bdPeriodManager = null;

	// private JpmProductSaleNewManager jpmProductSaleNewManager = null;

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void setJdbcTemplateReport(JdbcTemplate jdbcTemplateReport) {
		this.jdbcTemplateReport = jdbcTemplateReport;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
		String strAction = request.getParameter("strAction");
		
		String flag = request.getParameter("flag");
		request.setAttribute("strAction", strAction);
		if ("Y".equals(flag)) {
			// ====================Modify By WuCF 20150716 得到查询条件
			String startDate = request.getParameter("startDate");// 起始时间
			String endDate = request.getParameter("endDate");// 截止时间
			String formatedWeek = request.getParameter("formatedWeek");// 期别(财政周)
			
			if (!StringUtil.isEmpty(startDate)) {
				startDate = startDate.trim();
			}
			if (!StringUtil.isEmpty(endDate)) {
				endDate = endDate.trim();
			}
			if (!StringUtil.isEmpty(formatedWeek)) {
				formatedWeek = formatedWeek.trim();
				// Modify By WuCF 20150811 期别转自然周
				//formatedWeek = WeekFormatUtil.getFormatWeek("f", formatedWeek);
			}
			
			if ("leaderNetClearingPerformance".equals(strAction)) {// ==========================2.1领导人网体结算业绩
				
				// 生成报表
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition",
						"attachment; filename=lingdaorenwangtijiesuanyeji.xls");// 设置报表名称
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30");
				OutputStream os = response.getOutputStream();
				excelUtil = new ExcelUtil();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				wsheet = wwb.createSheet("sheet1", 0);
				// 加入期别时间段显示
				excelUtil.addString(wsheet, 0, 0, startDate+"-"+endDate);
				
				// 标题
				int m = 0;
				excelUtil.addString(wsheet, m++, 1, "领导人编号");
				excelUtil.addString(wsheet, m++, 1, "领导人姓名");
				excelUtil.addString(wsheet, m++, 1, "推荐部门结算业绩PV");
				excelUtil.addString(wsheet, m++, 1, "推荐部门结算业绩余额");
				excelUtil.addString(wsheet, m++, 1, "推荐部门已结算退单业绩PV");
				excelUtil.addString(wsheet, m++, 1, "推荐部门已结算退单业绩金额");
				excelUtil.addString(wsheet, m++, 1, "安置部门结算业绩PV");
				excelUtil.addString(wsheet, m++, 1, "安置部门结算业绩金额");
				excelUtil.addString(wsheet, m++, 1, "安置部门已结算退单业绩PV");
				excelUtil.addString(wsheet, m++, 1, "安置部门已结算退单业绩金额");
				excelUtil.setColumnWidth(wsheet, 0, 20);
				excelUtil.setColumnWidth(wsheet, 1, 20);
				excelUtil.setColumnWidth(wsheet, 2, 20);
				excelUtil.setColumnWidth(wsheet, 3, 20);
				excelUtil.setColumnWidth(wsheet, 4, 20);
				excelUtil.setColumnWidth(wsheet, 5, 20);
				excelUtil.setColumnWidth(wsheet, 6, 20);
				excelUtil.setColumnWidth(wsheet, 7, 20);
				excelUtil.setColumnWidth(wsheet, 8, 20);
				excelUtil.setColumnWidth(wsheet, 9, 20);
		
				//领导人网体结算业绩
				//Select * From Table(Fn_Get_Teamleader_Yj(Date'2016-6-1',Date'2016-6-30'));--调用方式
				
				String sql = "Select * From Table(Fn_Get_Teamleader_Yj(to_date('"+startDate+"','yyyy-mm-dd'),to_date('"+endDate+"','yyyy-mm-dd')))";
				
				this.jdbcTemplateReport.query(sql, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException {
						try {
							int kk = 2;
							//int totalCountRs=0;
							while (rs.next()) {
								/*
								 * --需求一：领导人网体结算业绩
									Select * From Table(Fn_Get_Teamleader_Yj(Date'2016-6-1',Date'2016-6-30'));--调用方式
									    User_Code        Varchar2(20),--领导人编号
									    User_Name        Varchar2(200),--领导人姓名
									    Re_Pv            Number,--推荐部门结算业绩PV
									    Re_Amount        Number,--推荐部门结算业绩金额
									    Re_Pv_Refund     Number,--推荐部门已结算退单业绩PV
									    Re_Amount_Refund Number,--推荐部门已结算退单业绩金额
									    Li_Pv            Number,--安置部门结算业绩PV
									    Li_Amount        Number,--安置部门结算业绩金额
									    Li_Pv_Refund     Number,--安置部门已结算退单业绩PV
									    Li_Amount_Refund Number--安置部门已结算退单业绩金额
								 */
								String userCode = rs.getString("User_Code");
								String userName = rs.getString("User_Name");
								String rePv = rs.getString("Re_Pv");
								String reAmount = rs.getString("Re_Amount");
								String rePvRefund = rs.getString("Re_Pv_Refund");
								String reAmountRefund = rs.getString("Re_Amount_Refund");
								String liPv = rs.getString("Li_Pv");
								String liAmount = rs.getString("Li_Amount");
								String liPvRefund = rs.getString("Li_Pv_Refund");
								String liAmountRefund = rs.getString("Li_Amount_Refund");
								
								int index=0;
				
								excelUtil.addString(wsheet, index++, kk, userCode);
								excelUtil.addString(wsheet, index++, kk, userName);
								excelUtil.addString(wsheet, index++, kk, rePv);
								excelUtil.addString(wsheet, index++, kk, reAmount);
								excelUtil.addString(wsheet, index++, kk, rePvRefund);
								excelUtil.addString(wsheet, index++, kk, reAmountRefund);
								excelUtil.addString(wsheet, index++, kk, liPv);
								excelUtil.addString(wsheet, index++, kk, liAmount);
								excelUtil.addString(wsheet, index++, kk, liPvRefund);
								excelUtil.addString(wsheet, index++, kk, liAmountRefund);
								
								kk++;
								//totalCountRs++;
							} 
							
		
						} catch (Exception e) {
							e.printStackTrace();
						}finally {  
							JdbcUtils.closeResultSet(rs);
						}
						return null;
					}
				});
				
				excelUtil.writeExcel(wwb);
				excelUtil.closeWritableWorkbook(wwb);
				os.close();

				return null;
			} else if ("leaderTeamBonus".equals(strAction)) {// ==========================2.2领导人团队奖金
				
				// 生成报表
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition",
						"attachment; filename=lingdaorentuanduijiangjin.xls");// 设置报表名称
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30");
				OutputStream os = response.getOutputStream();
				excelUtil = new ExcelUtil();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				wsheet = wwb.createSheet("sheet1", 0);
				// 加入期别时间段显示
				excelUtil.addString(wsheet, 0, 0,formatedWeek );
				
				// 标题
				int m = 0;
				excelUtil.addString(wsheet, m++, 1, "领导人编号");
				excelUtil.addString(wsheet, m++, 1, "领导人姓名");
				excelUtil.addString(wsheet, m++, 1, "推荐网体团队总奖金金额");
				excelUtil.addString(wsheet, m++, 1, "安置网体团队总奖金金额");
				excelUtil.addString(wsheet, m++, 1, "所属财月");
				
				excelUtil.setColumnWidth(wsheet, 0, 20);
				excelUtil.setColumnWidth(wsheet, 1, 20);
				excelUtil.setColumnWidth(wsheet, 2, 30);
				excelUtil.setColumnWidth(wsheet, 3, 30);
				excelUtil.setColumnWidth(wsheet, 4, 20);
				
		
				//公司结算业绩查询
				String sql = "Select * From Table(Fn_Get_Teamleader_Bonus(to_number('"+ formatedWeek +"')))";
				this.jdbcTemplateReport.query(sql, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException {
						try {
							int kk = 2;
							//int totalCountRs=0;
							while (rs.next()) {
								/*
								 *  --需求二：领导人团队奖金
									Select * From Table(Fn_Get_Teamleader_Bonus(201606));--调用方式
										User_Code Varchar2(20),--领导人编号
										User_Name Varchar2(200),--领导人姓名
										Re_Bonus  Number,--推荐网体团队总奖金金额
										Li_Bonus  Number,--安置网体团队总奖金金额
										f_Week    Number--所属期别
								 */
								String userCode = rs.getString("User_Code");
								String userName = rs.getString("User_Name");
								String reBouns = rs.getString("Re_Bonus");
								String liBouns = rs.getString("Li_Bonus");
								String fWeek = rs.getString("f_Week");
								
								
								int index=0;
				
								excelUtil.addString(wsheet, index++, kk, userCode);
								excelUtil.addString(wsheet, index++, kk, userName);
								excelUtil.addString(wsheet, index++, kk, reBouns);
								excelUtil.addString(wsheet, index++, kk, liBouns);
								excelUtil.addString(wsheet, index++, kk, fWeek);
								
								kk++;
								//totalCountRs++;
							} 
							
		
						} catch (Exception e) {
							e.printStackTrace();
						}finally {  
							JdbcUtils.closeResultSet(rs);
						}
						return null;
					}
				});
				
				excelUtil.writeExcel(wwb);
				excelUtil.closeWritableWorkbook(wwb);
				os.close();

				return null;
				
				
			} else if ("companyClearingPerformanceQuery".equals(strAction)) {// ==========================2.3公司结算业绩查询
				// 生成报表
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition",
						"attachment; filename=gongsijiesuanyejichaxun.xls");// 设置报表名称
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=30");
				OutputStream os = response.getOutputStream();
				excelUtil = new ExcelUtil();
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				wsheet = wwb.createSheet("sheet1", 0);
				// 加入期别时间段显示
				excelUtil.addString(wsheet, 0, 0, startDate+"-"+endDate);
				
				// 标题
				int m = 0;
				excelUtil.addString(wsheet, m++, 1, "结算业绩PV");
				excelUtil.addString(wsheet, m++, 1, "结算业绩金额");
				excelUtil.addString(wsheet, m++, 1, "已结算退单业绩PV");
				excelUtil.addString(wsheet, m++, 1, "已结算退单业绩金额");
				
				excelUtil.setColumnWidth(wsheet, 0, 20);
				excelUtil.setColumnWidth(wsheet, 1, 20);
				excelUtil.setColumnWidth(wsheet, 2, 20);
				excelUtil.setColumnWidth(wsheet, 3, 20);
				
		
				//公司结算业绩查询
				String sql = "Select * From Table(Fn_Get_Company_Yj(to_date('"+startDate+"','yyyy-mm-dd'),to_date('"+endDate+"','yyyy-mm-dd')))";
				this.jdbcTemplateReport.query(sql, new ResultSetExtractor() {
					public Object extractData(ResultSet rs) throws SQLException {
						try {
							int kk = 2;
							//int totalCountRs=0;
							while (rs.next()) {
								/*
								 *  --需求三：公司结算业绩查询
									Select * From Table(Fn_Get_Company_Yj(Date'2016-6-1',Date'2016-6-30'));--调用方式
									    Pv            Number,--结算业绩PV
									    Amount        Number,--结算业绩金额
									    Pv_Refund     Number,--已结算退单业绩PV
									    Amount_Refund Number--已结算退单业绩金额
								 */
								String pv = rs.getString("Pv");
								String amount = rs.getString("Amount");
								String pvRefund = rs.getString("Pv_Refund");
								String amountRefund = rs.getString("Amount_Refund");
								
								
								int index=0;
				
								excelUtil.addString(wsheet, index++, kk, pv);
								excelUtil.addString(wsheet, index++, kk, amount);

								excelUtil.addString(wsheet, index++, kk, pvRefund);
								excelUtil.addString(wsheet, index++, kk, amountRefund);
								
								kk++;
								//totalCountRs++;
							} 
							
		
						} catch (Exception e) {
							e.printStackTrace();
						}finally {  
							JdbcUtils.closeResultSet(rs);
						}
						return null;
					}
				});
				
				excelUtil.writeExcel(wwb);
				excelUtil.closeWritableWorkbook(wwb);
				os.close();

				return null;
			} else {
				
				return new ModelAndView("po/jpoMemberOrderClearingPerformanceStatistic");
			}
		}
		return new ModelAndView("po/jpoMemberOrderClearingPerformanceStatistic");
		
	}
		
}
