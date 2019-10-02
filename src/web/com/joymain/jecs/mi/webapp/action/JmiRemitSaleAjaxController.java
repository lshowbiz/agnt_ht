package com.joymain.jecs.mi.webapp.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.interceptor.Soap11FaultOutInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.dao.BdPeriodDao;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.mi.model.JmiRemitSale;
import com.joymain.jecs.mi.service.JmiRemitSaleManager;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysDataLogManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiRemitSaleAjaxController extends BaseController implements Controller {

	private final Log log = LogFactory.getLog(JmiRemitSaleController.class);
	private JmiRemitSaleManager jmiRemitSaleManager;
	// 系统时间
	private BdPeriodDao dao;
	private SysDataLogManager sysDataLogManager = null;

	public SysDataLogManager getSysDataLogManager() {
		return sysDataLogManager;
	}

	public void setSysDataLogManager(SysDataLogManager sysDataLogManager) {
		this.sysDataLogManager = sysDataLogManager;
	}

	public void setJmiRemitSaleManager(JmiRemitSaleManager jmiRemitSaleManager) {
		this.jmiRemitSaleManager = jmiRemitSaleManager;
	}

	public void setBdPeriodDao(BdPeriodDao dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String AjaxMethod = request.getParameter("AjaxMethod");
		String userCode = request.getParameter("userCode");
		String id = request.getParameter("id");
		String startWeek = request.getParameter("startWeek");
		String endWeek = request.getParameter("endWeek");
		String remark = request.getParameter("remark");
		String strAction = request.getParameter("strAction");
		// 拼凑日志sysDataLog
		SysDataLog sysDataLog;
		List<String> sysDataLogColunm = new ArrayList<String>();
		sysDataLogColunm.add("userCode");
		sysDataLogColunm.add("startWeek");
		sysDataLogColunm.add("endWeek");
		sysDataLogColunm.add("remark");
		// 操作用户
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		if ("ajaxSearchStarEndWeek".equals(AjaxMethod)) {
			int flag = 0;
			int startweek = Integer.parseInt(startWeek);
			int endweek = Integer.parseInt(endWeek);
			// 查出输入日期是否已结算 , 结算不允许在输入
			if (isInBdPeriodByTime(startweek) != 0) {
				flag = 2;
			}
			// 查出是属于免重销的客户周期 ,不允许在输入
			if (isRemitSale(userCode, startweek, endweek) != 0) {
				flag = 1;
			}

			response.getWriter().write("{\"starEndWeekflag\":\"" + flag + "\"}");
		}

		if ("ajaxVerifyJMIMEMBER".equals(AjaxMethod)) {
			List<Map<String, Object>> jmimembers = jmiRemitSaleManager.ajaxVerifyJMIMEMBER(userCode);
			int flag = jmimembers.size() > 0 ? jmimembers.size() : 0;

			response.getWriter().write("{\"ajaxVerifyJMIMEMBERflag\":\"" + flag + "\"}");
		}

		if ("permitEdit".equals(AjaxMethod)) {
			int flag = 0;
			int endweek = Integer.parseInt(endWeek);

			flag = isInBdPeriodByTime(endweek);

			response.getWriter().write("{\"permitEditFlag\":\"" + flag + "\"}");
		}

		if ("ajaxEndWeek".equals(AjaxMethod)) {
			int ajaxEndWeekBtnFlag = 0;
			int bdPeriod = getBdPeriod();

			ajaxEndWeekBtnFlag = jmiRemitSaleManager.updateEndWeek(bdPeriod, id);

			response.getWriter().write("{\"ajaxEndWeekBtnFlag\":\"" + ajaxEndWeekBtnFlag + "\"}");
		}

		if ("ajaxMemberById".equals(AjaxMethod)) {
			String flag = "";
			BigDecimal ID = new BigDecimal(id);
			JmiRemitSale jmiRemitSale = jmiRemitSaleManager.findJmiRemitSaleById(ID);
			// 拼凑日志 开始
			List<String> oldjmiRemitSale = new ArrayList<String>();
			oldjmiRemitSale.add(jmiRemitSale.getUserCode());
			oldjmiRemitSale.add(jmiRemitSale.getStartWeek().toString());
			oldjmiRemitSale.add(jmiRemitSale.getEndWeek().toString());
			oldjmiRemitSale.add(jmiRemitSale.getRemark() == null ? "" : jmiRemitSale.getRemark());

			jmiRemitSale.setStartWeek(new BigDecimal(startWeek));
			jmiRemitSale.setEndWeek(new BigDecimal(endWeek));
			jmiRemitSale.setRemark(remark);

			List<String> newjmiRemitSale = new ArrayList<String>();
			newjmiRemitSale.add(jmiRemitSale.getUserCode());
			newjmiRemitSale.add(jmiRemitSale.getStartWeek().toString());
			newjmiRemitSale.add(jmiRemitSale.getEndWeek().toString());
			newjmiRemitSale.add(jmiRemitSale.getRemark() == null ? "" : jmiRemitSale.getRemark());
			// 拼凑日志 结束
			int startweek = Integer.parseInt(startWeek);
			int endweek = Integer.parseInt(endWeek);

			// 判断修改的时间是否与其他记录的免重消时间重复
			int isRemitSaleFlag = isRemitSale(userCode, startweek, endweek, id);
			if (isRemitSaleFlag == 1) {
				flag = "免重消开始时间已经在免重消范围内";
			}
			if (isRemitSaleFlag == 2) {
				flag = "免重消结束时间已经在免重消范围内";
			}
			if (isRemitSaleFlag == 3) {
				flag = "免重消结束时间包含其他免重消时间";
			}
	
			
			if ("".equals(flag) || "" == flag) {
				/*
				 * 更新开始
				 */
				int i = jmiRemitSaleManager.updateJmiRemitSaleById(ID, jmiRemitSale);
				if (i != 0) {

					// 保存日志。开始
					for (int j = 0; j < sysDataLogColunm.size(); j++) {
						sysDataLog = new SysDataLog();
						sysDataLog.setChangeType("update");
						sysDataLog.setTableName("jmiRemitSale");
						sysDataLog.setColumnName(sysDataLogColunm.get(j));
						sysDataLog.setOperatorPerson(defSysUser.getUserCode());
						sysDataLog.setBeforeChange(oldjmiRemitSale.get(j));
						sysDataLog.setAfterChange(newjmiRemitSale.get(j));
						sysDataLog.setOperatorTime(new Timestamp(new Date().getTime()));
						sysDataLog.setIpAddress(request.getRemoteAddr());
						sysDataLog.setHostName(request.getRemoteHost());
						sysDataLog.setModuleName(request.getRequestURI());
						sysDataLog.setOperationLogId((long) 0);

						sysDataLogManager.saveSysDataLog(sysDataLog);
					}
					// 保存日志。结束
					flag = "更新成功";
				} else {
					flag = "更新失败";
				}
				/*
				 * 更新结束
				 */
			}
			response.getWriter().write("{\"ajaxUpdateMemberByIdFlag\":\"" + flag + "\"}");
		}
		
		if ("deleteJmiRemitSale".equals(AjaxMethod)) {
			int flag = 0;
			JmiRemitSale jmiRemitSale = jmiRemitSaleManager.findJmiRemitSaleById(new BigDecimal(id));
			int bdPeriod = getBdPeriod();
			// 拼凑日志 开始
			List<String> oldjmiRemitSale = new ArrayList<String>();
			oldjmiRemitSale.add(jmiRemitSale.getUserCode());
			oldjmiRemitSale.add(jmiRemitSale.getStartWeek().toString());
			oldjmiRemitSale.add(jmiRemitSale.getEndWeek().toString());
			oldjmiRemitSale.add(jmiRemitSale.getRemark() == null ? "" : jmiRemitSale.getRemark());
			
			// 拼凑日志 结束
			
			 flag = jmiRemitSaleManager.deleJmiRemitSaleById(id);
			if (flag != 0) {
				// 保存 日志
				for (int j = 0; j < sysDataLogColunm.size(); j++) {
					sysDataLog = new SysDataLog();
					sysDataLog.setChangeType("delete");
					sysDataLog.setTableName("jmiRemitSale");
					sysDataLog.setColumnName(sysDataLogColunm.get(j));
					sysDataLog.setOperatorPerson(defSysUser.getUserCode());
					sysDataLog.setBeforeChange(oldjmiRemitSale.get(j));
					sysDataLog.setAfterChange(null);
					sysDataLog.setOperatorTime(new Timestamp(new Date().getTime()));
					sysDataLog.setIpAddress(request.getRemoteAddr());
					sysDataLog.setHostName(request.getRemoteHost());
					sysDataLog.setModuleName(request.getRequestURI());
					sysDataLog.setOperationLogId((long) 0);

					sysDataLogManager.saveSysDataLog(sysDataLog);
				}
			}
			response.getWriter().write("{\"deleteJmiRemitSaleFlag\":\"" + flag + "\"}");
		}

		
		if ("bdPeriod".equals(AjaxMethod)) {
			int bdPeriod = getBdPeriod();
			request.setAttribute("bdPeriod", bdPeriod);
			response.getWriter().write("{\"bdPeriod\":\"" + bdPeriod + "\"}");

		}
		// return new ModelAndView("mi/jmiRemitSale", "jmiRemitSaleList",
		// jmiRemitSaleList);
        return null;
	}

	// 用于修改用户资料， 不包括此记录Id的免重消日期
	private int isRemitSale(String userCode, int startweek, int endweek, String notIncludeID) {
		// 查出已經是免重销的客户周期 ,不允许在输入
		List<Map<String, Object>> starEndWeek = jmiRemitSaleManager.ajaxStarEndtWeek(userCode, notIncludeID);

		for (int i = 0; i < starEndWeek.size(); i++) {
			Map<String, Object> Map = (Map<String, Object>) starEndWeek.get(i);

			int value1 = Integer.parseInt(Map.get("startWeek").toString());
			int value2 = Integer.parseInt(Map.get("endWeek").toString());

			if (startweek <= value2 && startweek >= value1) {
				// 警告flag 1 输入日期已经在免重消范围内
				return 1;
			}
			if (endweek <= value2 && endweek >= value1) {
				// 警告flag 2 输入日期已经在免重消范围内
				return 2;
			}
			if (startweek <= value1 && endweek >= value2) {
				return 3;
			}
		}
		return 0;
	}

	private int isRemitSale(String userCode, int startweek, int endweek) {
		// 查出已經是免重销的客户周期 ,不允许在输入
		List<Map<String, Object>> starEndWeek = jmiRemitSaleManager.ajaxStarEndtWeek(userCode);

		for (int i = 0; i < starEndWeek.size(); i++) {
			Map<String, Object> Map = (Map<String, Object>) starEndWeek.get(i);

			int value1 = Integer.parseInt(Map.get("startWeek").toString());
			int value2 = Integer.parseInt(Map.get("endWeek").toString());

			if (startweek <= value2 && startweek >= value1) {
				// 警告flag 1 输入日期已经在免重消范围内
				return 1;
			}
			if (endweek <= value2 && endweek >= value1) {
				// 警告flag 1 输入日期已经在免重消范围内
				return 2;
			}
			if (startweek <= value1 && endweek >= value2) {
				return 3;
			}
		}
		return 0;
	}

	private int isInBdPeriodByTime(int week) {
		BdPeriod bdPeriod = dao.getBdPeriodByTime(new Date(), (long) 1);
		int currentYear = bdPeriod.getWyear();
		// ajaxBdPeriodBonus 1 获取本年已经结算的周期 0 获取本年没结算的周期。
		List<Map<String, Object>> BdPeriodBonus = jmiRemitSaleManager.ajaxBdPeriodBonus("1", currentYear);
		for (int i = 0; i < BdPeriodBonus.size(); i++) {
			Map<String, Object> Map = (Map<String, Object>) BdPeriodBonus.get(i);
			 String tempWeek = Integer.parseInt(Map.get("wWeek").toString())<10?"0"+Integer.parseInt(Map.get("wWeek").toString()):Map.get("wWeek").toString();
					
			int BdPeriodWeek = Integer.parseInt(Map.get("wYear").toString() + tempWeek);
			if (week <= BdPeriodWeek) {
				// 警告flag 2 输入日期已结算
				return 2;
			}
		}
		return 0;
	}

	// 获取最新结算周
	// 获取当前周数 wid随意设置 。
	public int getBdPeriod() {
		int maxBdPeriodWeek = 0;
		BdPeriod bdPeriod = dao.getBdPeriodByTime(new Date(), (long) 1);
		int currentYear = bdPeriod.getWyear();
		// ajaxBdPeriodBonus 1 获取本年已经结算的周期 0 获取本年没结算的周期。
		List<Map<String, Object>> BdPeriodBonus = jmiRemitSaleManager.ajaxBdPeriodBonus("1", currentYear);
		for (int i = 0; i < BdPeriodBonus.size(); i++) {
			Map<String, Object> Map = (Map<String, Object>) BdPeriodBonus.get(i);
			 String tempWeek = Integer.parseInt(Map.get("wWeek").toString())<10?"0"+Integer.parseInt(Map.get("wWeek").toString()):Map.get("wWeek").toString();
			
			int week = Integer.parseInt(Map.get("wYear").toString() + tempWeek);
			if (week > maxBdPeriodWeek) {
				maxBdPeriodWeek = week;
			}
		}
		return maxBdPeriodWeek;
	}

}
