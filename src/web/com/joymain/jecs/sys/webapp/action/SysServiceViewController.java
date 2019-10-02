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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysService;
import com.joymain.jecs.util.net.SocketUtil;

public class SysServiceViewController implements Controller {
	private final Log log = LogFactory.getLog(SysRoleController.class);
	private final String socketCommand = "status";

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		String ret="";
		
		socket = new Socket(Constants.getConfig(Constants.JECS_SERVER_ADDR), Integer.parseInt(Constants.getConfig(Constants.JECS_SERVER_PORT)));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), false);
		out.println("bonusCalculate-2009-40-root-管理员");
		out.flush();
		ret=in.readLine();
		System.out.println(ret);
		ret=in.readLine();
		System.out.println(ret);
		closeAll(in,out,socket);
		

		SocketUtil socketUtil = new SocketUtil(Constants.getConfig(Constants.JECS_SERVER_ADDR), Integer.parseInt(Constants.getConfig(Constants.JECS_SERVER_PORT)));
		String errMsgKey = "";
		try {
			socketUtil.connectServer();
			StringBuffer sb = new StringBuffer();
			sb.append(socketCommand);
			String serverMsg = socketUtil.SendCommand(sb.toString());
			request.setAttribute("serverMsg", serverMsg);
			List<SysService> sysServices=new ArrayList<SysService>();
			if(!StringUtils.isEmpty(serverMsg)){
				String[] serverMessages=serverMsg.split(";");
				for(int i=0;i<serverMessages.length;i++){
					String[] serverMessage=serverMessages[i].split(",");
					SysService sysService=new SysService();
					sysService.setServiceName(serverMessage[0]);
					sysService.setGroupName(serverMessage[1]);
					sysService.setActiveCount(Integer.parseInt(serverMessage[2]));
					sysServices.add(sysService);
				}
			}
			request.setAttribute("sysServices", sysServices);
		} catch (UnknownHostException ex) {
			errMsgKey = "client.error.unknow.server";
		} catch (IOException ex) {
			errMsgKey = "client.error.io";
		}
		request.setAttribute("errMsgKey", errMsgKey);

		return new ModelAndView("sys/sys_service_view");
	}
	
	public   void closeAll(BufferedReader in,PrintWriter out,Socket socket){
		if(in !=null){
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(out !=null){
			out.close();
		}
		if(socket !=null){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
