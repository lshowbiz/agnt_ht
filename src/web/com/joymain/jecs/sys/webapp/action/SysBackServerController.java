package com.joymain.jecs.sys.webapp.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;


public class SysBackServerController extends BaseController implements
		Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String strAction = request.getParameter("strAction");
		String excuteFlag=request.getParameter("excuteFlag");
		String view ="";
		String host=ConfigUtil.getConfigValue("CN", Constants.JECS_BACK_SERVER_HOST);
		int port = new Integer(ConfigUtil.getConfigValue("CN", Constants.JECS_BACK_SERVER_PORT));
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		socket = new Socket(host,port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), false);
		List message = new ArrayList();
		String ret ="";
		 if("excute".equals(excuteFlag)){
			ret=excuteCommon(request,response,strAction);
		}else{
			try {
				viewBackserver(request,response);
				message.add(LocaleUtil.getLocalText("notice.backserver.success"));
			} catch (AppException e) {
				// TODO Auto-generated catch block
				message.add(LocaleUtil.getLocalText(e.getMessage()));
				log.error(e);
			}
		}
		message.add(LocaleUtil.getLocalText(ret));
		 return new ModelAndView("sys/sysBackServiceList");
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
		
		 try {
			host = ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_HOST);
			port = new Integer(ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_PORT));
		} catch (Exception e) {
	
			throw new AppException("erro.backserver.config");
		}
		
		try {
			socket = new Socket(host,port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), false);
		} catch (Exception e) {
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
	private void viewBackserver(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String host;
		int port;
		 try {
			host = ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_HOST);
			port = new Integer(ConfigUtil.getConfigValue("CN",
					Constants.JECS_BACK_SERVER_PORT));
		} catch (Exception e) {
			// TODO: handle exception
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
			throw new AppException("erro.backserver.running");
		} 
		
		
	}

}
