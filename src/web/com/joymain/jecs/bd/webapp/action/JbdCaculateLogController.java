package com.joymain.jecs.bd.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdCaculateLog;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.bd.service.JbdCaculateLogManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdCaculateLogController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(JbdCaculateLogController.class);
	private JbdCaculateLogManager jbdCaculateLogManager = null;
	private BdPeriodManager bdPeriodManager = null;

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJbdCaculateLogManager(
			JbdCaculateLogManager jbdCaculateLogManager) {
		this.jbdCaculateLogManager = jbdCaculateLogManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String strAction = request.getParameter("strAction");
		String wweek=request.getParameter("wweek");
//		--------------------------------------------------------------------------
		wweek = WeekFormatUtil.getFormatWeek("f", wweek);
	
		String currentStep = request.getParameter("currentStep");
		String ret="";
		List message = new ArrayList();
		if("excuteBackServer".equals(strAction)){
			wweek = bdPeriodManager.getBonusCalculateYearMonth();
			request.setAttribute("wweek", wweek);
			if("bouns_money_calc".equals(currentStep)){
				try {
					ret=bonusCalculate(request,response);
				} catch (AppException e) {
					// TODO Auto-generated catch block
					message.add(LocaleUtil.getLocalText(e.getMessage()));
					log.error(e);
				}
			}else if("Pro_Bouns_Confirm".equals(currentStep)){
				try {
					ret=bonusConfirm(request,response);
				} catch (AppException e) {
					// TODO Auto-generated catch block
					message.add(LocaleUtil.getLocalText(e.getMessage()));
					log.error(e);
				}
			}
		}
		message.add(LocaleUtil.getLocalText(ret));
		request.setAttribute("messages", message);
		
		
		JbdCaculateLog jbdCaculateLog = new JbdCaculateLog();
		// populate object with request parameters
		BeanUtils.populate(jbdCaculateLog, request.getParameterMap());

		// List jbdCaculateLogs =
		// jbdCaculateLogManager.getJbdCaculateLogs(jbdCaculateLog);
		/**** auto pagination ***/
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("jbdCaculateLogListTable", request, 20);
		crm.setValue("wweek", wweek);
		
		List jbdCaculateLogs = null;
		if (wweek != null) {
			log.info("wweek="+crm.getString("wweek"));
			jbdCaculateLogs = jbdCaculateLogManager.getJbdCaculateLogsByCrm(
					crm, pager);
		}
		/*****/
		request.setAttribute("jbdCaculateLogListTable_totalRows", pager
				.getTotalObjects());

		return new ModelAndView("bd/jbdCaculateLogList",
				Constants.JBDCACULATELOG_LIST, jbdCaculateLogs);
	}

	private String bonusConfirm(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String yearMonth = bdPeriodManager.getBonusCalculateYearMonth();
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String wyear = "";
		String wmonth = "";
		if (yearMonth.length() == 6) {
			wyear = yearMonth.substring(0, 4);
			wmonth = yearMonth.substring(4, 6);
		}

		String ret = "";
		List erroList = new ArrayList();
		String host;
		int port;

		Socket socket;
		BufferedReader in;
		PrintWriter out;

		String command = "bonusConfirm-" + wyear + "-" + wmonth;
		try {
			host = ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_HOST);
			port = new Integer(ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_PORT));
		} catch (Exception e) {
			// TODO: handle exception
			// erroList.add("erro.backserver.config");
			throw new AppException("erro.backserver.config");
		}

		try {
			socket = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// erroList.add("erro.backserver.running");
			throw new AppException("erro.backserver.running");
		}

		out.println(command);
		out.flush();
		try {
			ret = in.readLine();
			erroList.add(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// erroList.add("erro.backserver.lostconnect");
			throw new AppException("erro.backserver.lostconnect");
		}
		return ret;
	}

	private String bonusCalculate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String yearMonth = bdPeriodManager.getBonusCalculateYearMonth();
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String wyear = "";
		String wmonth = "";
		if (yearMonth.length() == 6) {
			wyear = yearMonth.substring(0, 4);
			wmonth = yearMonth.substring(4, 6);
		}

		String ret = "";
		// TODO Auto-generated method stub
		// List erroList = new ArrayList();
		String host;
		int port;

		String command = "bonusCalculate-" + wyear + "-" + wmonth + "-"
				+ sessionLogin.getUserCode() + "-" + sessionLogin.getUserName();
		try {
			host = ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_HOST);
			port = new Integer(ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_PORT));
		} catch (Exception e) {
			// TODO: handle exception
			// erroList.add("erro.backserver.config");
			throw new AppException("erro.backserver.config");
		}
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		try {
			socket = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// erroList.add("erro.backserver.running");
			throw new AppException("erro.backserver.running");
		}

		out.println(command);
		out.flush();
		try {
			ret = in.readLine();
			// erroList.add(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// erroList.add("erro.backserver.lostconnect");
			throw new AppException("erro.backserver.lostconnect");
		}
		// request.setAttribute("errors", erroList);
		return ret;
	}
}
