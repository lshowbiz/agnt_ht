package com.joymain.jecs.webapp.util;

import java.io.IOException;

import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * 短信接口
 * @author WuCF
 * @date：2014-01-14
 *
 */
public class SmsSend {
//	public static String URL1 = "http://192.168.20.227:8080/JM-PLATFORM/sms/MobsetSendSMS/sysId/oa/mobileNum/";
//	public static String URL2 = "/message/hello";
	
	public static void sendSms(String url1,String url2,String mobilePhone,String message){
		//Modify By WuCF 20170216 屏蔽短信发送 
		System.out.println("=====:屏蔽短信发送");
		/*String url = url1+mobilePhone+url2;//获得读取短信的URL
		//本地短信服务器访问地址
		ClientResource resource = new ClientResource(url);
		resource.setChallengeResponse(ChallengeScheme.HTTP_BASIC ,   
				"JM-PLATFORM", "JM-PLATFORM");   

		//生成对象
		Form form = null; 
		Representation representation = null;
		try {
			form = new Form();  
			form.add("sysId", "JECS");//登录账号
			form.add("mobileNum", mobilePhone);//设置手机号
			form.add("message", message); //设置内容
			representation = resource.put(form, MediaType.APPLICATION_JSON);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(form!=null){
				form.clear();//关闭资源
			}
		}*/
	}

	public static void main(String[] args) {
		String url1 = "http://192.168.20.227:8080/JM-PLATFORM/sms/MobsetSendSMS/sysId/JECS/mobileNum/";
		String url2 = "/message/hello";
		String mobilePhone = "13650710137";
		String message = "中脉测试！";
		sendSms(url1,url2,mobilePhone, message);
	}
}
