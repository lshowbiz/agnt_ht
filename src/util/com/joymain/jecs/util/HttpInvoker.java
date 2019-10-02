package com.joymain.jecs.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpInvoker {
	public static String readContentFromGet(String GET_URL,String content) throws IOException {
		String returnStr = "";
		//拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
		String getURL = GET_URL + "?" + content;
		URL getUrl = new URL(getURL);
		//根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
		//返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返
		//回的是HttpURLConnection
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		//进行连接，但是实际上get request要在下一句的connection.getInputStream()
		//函数中才会真正发到服务器
		connection.connect();
		//取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String lines;
		while ((lines = reader.readLine()) != null) {
			returnStr += lines;
		}
		reader.close();
		//断开连接
		connection.disconnect();
		return returnStr;
	}

	public static String readContentFromPost(String POST_URL,String content) throws IOException {
		String returnStr = "";
		//Post请求的url，与get不同的是不需要带参数
		URL postUrl = new URL(POST_URL);
		//打开连接
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		//设置是否向connection输出，因为这个是post请求，参数要放在
		//http正文内，因此需要设为true
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		//Post 请求不能使用缓存
		connection.setUseCaches(false);
		//是static函数，作用于所有的URLConnection对象。
		//URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
		connection.setInstanceFollowRedirects(true);
		//配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		//意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		//进行编码
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		//连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		//要注意的是connection.getOutputStream会隐含的进行connect。
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		//DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
		out.writeBytes(content);
		out.flush();
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			returnStr += line;
		}
		reader.close();
		connection.disconnect();
		return returnStr;
	}

	/** */

	/**
	 * 
	 * @param args
	 * 
	 */

	public static void main(String[] args) {
		String GET_URL = "http://www.baidu.com/s";
		String POST_URL = "http://www.baidu.com/s";
		try {
			String get = readContentFromGet(GET_URL,"wd=" + URLEncoder.encode("a", "utf-8"));
			String post = readContentFromPost(POST_URL,"wd=" + URLEncoder.encode("a", "utf-8"));
			System.out.println(get);
			System.out.println("======================================");
			System.out.println(post);
		}	
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
