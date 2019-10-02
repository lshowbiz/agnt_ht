package com.joymain.jecs.util.smssend;

import java.io.IOException;

import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

import net.sf.json.JSONObject;

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
		String url = url1+mobilePhone+url2;//获得读取短信的URL
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
		}
	}
	/**
	 * 发送短信验证码到手机
	 * @param jsonObject 短信参数
	 * @param templateCode 模板code
	 * @param phoneNum 接收短信验证码的手机号
	 */
	public static String sendAliSms(JSONObject jsonObject,String phoneNum,String templateCode,String accessKeyId ,String accessKeySecret,String signName){
		

		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		

		try {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			// 控制台创建的签名名称
			request.setSignName(signName);
			// 控制台创建的模板CODE
			request.setTemplateCode(templateCode);
			// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			request.setParamString(jsonObject.toString());
			// 接收号码
			request.setRecNum(phoneNum);
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			String requestId = httpResponse.getRequestId();
			String model = httpResponse.getModel();
		} catch (ServerException e) {
			e.printStackTrace();
			return "验证码发送失败，请检查短信接口！";
		} catch (ClientException e) {
			e.printStackTrace();
			return "验证码发送失败，请检查短信接口！";
		}
		return "";
	}
	/**
	 * 发送短信验证码到手机
	 * @param verifyCode 验证码
	 * @param validTime 有效时间
	 * @param phoneNum 接收短信验证码的手机号
	 */
	public static String sendSmsVerifyCode(String verifyCode,String validTime,String phoneNum,String accessKeyId,String accessKeySecret,String signName,String templateCode,String paramstring){
		/*String accessKeyId = (String)ConfigUtil.getConfigValue("CN","sms.accesskeyid");
		String accessKeySecret = (String)ConfigUtil.getConfigValue("CN","sms.accesskeysecret");
		String signName = (String)ConfigUtil.getConfigValue("CN","sms.signname");//阿里云短信控制台创建的签名名称
		String templateCode = (String)ConfigUtil.getConfigValue("CN","sms.templatecode");//阿里云短信控制台创建的模板CODE
		
		String paramstring = (String)ConfigUtil.getConfigValue("CN","sms.cloudshop.paramstring");*/
		paramstring = paramstring.replace("${verifyCode}", verifyCode);
		paramstring = paramstring.replace("${validTime}", validTime);
		
		try {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName(signName);// 控制台创建的签名名称
			request.setTemplateCode(templateCode);// 控制台创建的模板CODE
			request.setParamString(paramstring);// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			request.setRecNum(phoneNum);// 接收号码
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			String requestId = httpResponse.getRequestId();
			String model = httpResponse.getModel();
		} catch (ServerException e) {
			e.printStackTrace();
			return "验证码发送失败，请检查短信接口！";
		} catch (ClientException e) {
			e.printStackTrace();
			return "验证码发送失败，请检查短信接口！";
		}
		return "";
	}
	public static void main(String[] args) {
		String url1 = "http://192.168.5.227:8080/JM-PLATFORM/sms/MobsetSendSMS/sysId/JECS/mobileNum/";
		String url2 = "/message/hello";
		String mobilePhone = "15902032978";
		String message = "中脉测试！";
		//sendSms(url1,url2,mobilePhone, message);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("userCode", "AJ");
		jsonObject.put("dateStr", "2017-03-04");
		jsonObject.put("invoiceNo", "");
		jsonObject.put("companyName", "");
		//SmsSend.sendAliSms(jsonObject,"SMS_109360231",mobilePhone);
	}
}
