package com.joymain.jecs.util.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.util.date.DateUtil;

public class SocketUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	
	private Socket socket;
	private String host;
	private int port;
	private BufferedReader fromServer;
	private PrintWriter toServer;

	public SocketUtil(final String host, final int port) {
		this.host = host;
		this.port = port;
	}

	public void connectServer() throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);

		//BufferedReader from_user = new BufferedReader(new InputStreamReader(System.in));
		//final PrintWriter to_user = new PrintWriter(System.out, true);
		log.debug("系统正在与服务器(" + host + ":" + port + ")建立连接......");

		this.fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.toServer = new PrintWriter(socket.getOutputStream());
		log.debug("系统已经连接到" + socket.getInetAddress() + " : " + socket.getPort());	
	}
	
	public String SendCommand(final String cmdStr) throws IOException{
		String fromServer;
		//String fromUser;

		this.toServer.println(cmdStr);
		this.toServer.flush();
		while ((fromServer = this.fromServer.readLine()) != null) {
			//接收到信息后退出,避免长期挂起
			break;
		}

		socket.close();
		log.info("客户端关闭连接。");
		return fromServer;
	}
}
