package com.joymain.jecs.sys.webapp.action;

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

import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.sys.model.SysBackServiceInfo;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBackServiceInfoManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SysBackServiceInfoController extends BaseController implements
		Controller {
	private final Log log = LogFactory
			.getLog(SysBackServiceInfoController.class);
	private SysBackServiceInfoManager sysBackServiceInfoManager = null;
	private BdPeriodManager bdPeriodManager = null;
	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setSysBackServiceInfoManager(
			SysBackServiceInfoManager sysBackServiceInfoManager) {
		this.sysBackServiceInfoManager = sysBackServiceInfoManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String ret="";
		List message = new ArrayList();
		SysBackServiceInfo sysBackServiceInfo = new SysBackServiceInfo();
		// populate object with request parameters
		BeanUtils.populate(sysBackServiceInfo, request.getParameterMap());
		String yearMonth = bdPeriodManager.getBonusCalculateYearMonth();
		//-------------------------------------------------------------------------
		yearMonth = WeekFormatUtil.getFormatWeek("w", yearMonth);
		CommonRecord crm = initCommonRecord(request);
		String wyear="";
		String wmonth="";
		if(yearMonth.length()==6){
			wyear=yearMonth.substring(0, 4);
			wmonth=yearMonth.substring(4, 6);
			
			crm.setValue("wyear", wyear);
			crm.setValue("wmonth", wmonth);
		}
//		System.out.println("wyear="+wyear+wmonth);
		
		String strAction = request.getParameter("strAction");
		String serviceCommand = request.getParameter("serviceCommand");
		
		if("viewBackserver".equals(strAction)){
			try {
				viewBackserver(request,response);
				message.add(LocaleUtil.getLocalText("notice.backserver.success"));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				message.add(LocaleUtil.getLocalText(e.getMessage()));
				log.error(e);
			}
		}else if("excuteBackServer".equals(strAction)){
			if("bonusCalculate".equals(serviceCommand)){
				try {
					ret=bonusCalculate(request,response);
				} catch (AppException e) {
					// TODO Auto-generated catch block
					message.add(LocaleUtil.getLocalText(e.getMessage()));
					log.error(e);
				}
			}else if("bonusConfirm".equals(serviceCommand)){
				try {
					ret=bonusConfirm(request,response);
				} catch (AppException e) {
					// TODO Auto-generated catch block
					message.add(LocaleUtil.getLocalText(e.getMessage()));
					log.error(e);
				}
			}else{
				excuteCommon(request,response,serviceCommand);
			}
		}
		message.add(LocaleUtil.getLocalText(ret));
		/*
		String host=ConfigUtil.getConfigValue("CN", Constants.JECS_BACK_SERVER_HOST);
		int port = new Integer(ConfigUtil.getConfigValue("CN", Constants.JECS_BACK_SERVER_PORT));
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		socket = new Socket(host,port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), false);
		*/
		// List sysBackServiceInfos =
		// sysBackServiceInfoManager.getSysBackServiceInfos(sysBackServiceInfo);
		/**** auto pagination ***/
		
		Pager pager = new Pager("sysBackServiceInfoListTable", request, 20);
		List sysBackServiceInfos = sysBackServiceInfoManager
				.getSysBackServiceInfosByCrm(crm, pager);
		request.setAttribute("sysBackServiceInfoListTable_totalRows", pager
				.getTotalObjects());
		/*****/
		request.setAttribute("messages", message);
		//修改成财政年
		
		request.setAttribute("wyear", yearMonth.substring(0, 4));
		request.setAttribute("wmonth", yearMonth.substring(4, 6));
		return new ModelAndView("sys/sysBackServiceInfoList",
				Constants.SYSBACKSERVICEINFO_LIST, sysBackServiceInfos);
	}
	
	private String  excuteCommon(HttpServletRequest request,
			HttpServletResponse response, String serviceCommand) {
		// TODO Auto-generated method stub
		String ret="";
		List erroList = new ArrayList();
		String host;
		int port;
		
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		/*SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String wyear = request.getParameter("wyear");
		String wmonth = request.getParameter("wmonth");*/
//		String command = "bonusConfirm-"+wyear+"-"+wmonth;
		 try {
			host = ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_HOST);
			port = new Integer(ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_PORT));
		} catch (Exception e) {
			// TODO: handle exception
//			erroList.add("erro.backserver.config");
			throw new AppException("erro.backserver.config");
		}
		
		try {
			socket = new Socket(host,port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			erroList.add("erro.backserver.running");
			throw new AppException("erro.backserver.running");
		} 
		
		out.println(serviceCommand);
		out.flush();
		try {
			 ret = in.readLine();
			erroList.add(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			erroList.add("erro.backserver.lostconnect");
			throw new AppException("erro.backserver.lostconnect");
		}
		return ret;
	}

	private void excuteCommon(String serviceCommand) {
		// TODO Auto-generated method stub
		
	}

	private String bonusConfirm(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String ret="";
		List erroList = new ArrayList();
		String host;
		int port;
		
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String wyear = request.getParameter("wyear");
		String wmonth = request.getParameter("wmonth");
		//----------------------------------------------------------------
		String yearMonth = WeekFormatUtil.getFormatWeek("f", wyear+wmonth);
		 wyear = yearMonth.substring(0, 4);
		 wmonth = yearMonth.substring(4, 6);
		String command = "bonusConfirm-"+wyear+"-"+wmonth;
		 try {
			host = ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_HOST);
			port = new Integer(ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_PORT));
		} catch (Exception e) {
			// TODO: handle exception
//			erroList.add("erro.backserver.config");
			throw new AppException("erro.backserver.config");
		}
		
		try {
			socket = new Socket(host,port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			erroList.add("erro.backserver.running");
			throw new AppException("erro.backserver.running");
		} 
		
		out.println(command);
		out.flush();
		try {
			 ret = in.readLine();
			erroList.add(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			erroList.add("erro.backserver.lostconnect");
			throw new AppException("erro.backserver.lostconnect");
		}
		return ret;
	}

	private String bonusCalculate(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String ret="";
		// TODO Auto-generated method stub
//		List erroList = new ArrayList();
		String host;
		int port;
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String wyear = request.getParameter("wyear");
		String wmonth = request.getParameter("wmonth");
//		-----------------------------------------------------------------
		String yearMonth = WeekFormatUtil.getFormatWeek("f", wyear+wmonth);
		 wyear = yearMonth.substring(0, 4);
		 wmonth = yearMonth.substring(4, 6);
		
		
		String command = "bonusCalculate-"+wyear+"-"+wmonth+"-"+sessionLogin.getUserCode()+"-"+sessionLogin.getUserName();
		 try {
			host = ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_HOST);
			port = new Integer(ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_PORT));
		} catch (Exception e) {
			// TODO: handle exception
//			erroList.add("erro.backserver.config");
			throw new AppException("erro.backserver.config");
		}
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		try {
			socket = new Socket(host,port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			erroList.add("erro.backserver.running");
			throw new AppException("erro.backserver.running");
		} 
		
		out.println(command);
		out.flush();
		try {
			 ret = in.readLine();
//			erroList.add(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			erroList.add("erro.backserver.lostconnect");
			throw new AppException("erro.backserver.lostconnect");
		}
//		request.setAttribute("errors", erroList);
		return ret;
	}

	private void viewBackserver(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
	
//		List erroList = new ArrayList();
		String host;
		int port;
		
		 try {
			host = ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_HOST);
			port = new Integer(ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_PORT));
		} catch (Exception e) {
			// TODO: handle exception
//			erroList.add("erro.backserver.config");
			throw new AppException("erro.backserver.config");
		}
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		try {
			socket = new Socket(host,port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			erroList.add("erro.backserver.running");
			throw new AppException("erro.backserver.running");
		} 
		
//		request.setAttribute("erroList", erroList);
		
	}
}
