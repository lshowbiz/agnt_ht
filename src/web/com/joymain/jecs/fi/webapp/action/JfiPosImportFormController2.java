package com.joymain.jecs.fi.webapp.action;

import mobset.smsSDK;
import mobset.str_SendMsg;

import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class JfiPosImportFormController2 extends BaseFormController {

	public static void main(String[] args) {
		JfiPosImport jpi = new JfiPosImport();
		jpi.setTel("13650710137");
		jpi.setMessageCode("手验短信验证");
		
		sendSms(null);
//		sendSms2();
//		sendSms3(jpi);
	}
	
	/**
	 * 测试手机发送短信提醒功能 
	 * @param jfiPosImport
	 */
	private static void sendSms(JfiPosImport jfiPosImport){
    	if(jfiPosImport==null){
    		jfiPosImport = new JfiPosImport();
    		jfiPosImport.setTel("13650710137");
    		jfiPosImport.setMessageCode("手验短信验证测试:您的货已经发出,请留意! ");
    	} 
		smsSDK sdk = new smsSDK();
		try {
			int iRet = sdk.Sms_Connect("www.mobset.com", 111043, "test", "281512", 30); // 测试时请更改企业ID,用户名,密码
			if (iRet == 0){// 登录成功
				iRet = sdk.Sms_KYSms();
				if (iRet >= 0) {
					str_SendMsg[] sendMsg = new str_SendMsg[1];
					sendMsg[0] = new str_SendMsg();
					sendMsg[0].strExNum = ""; // 扩展号码，不用扩展请留空。
					sendMsg[0].strMobile = jfiPosImport.getTel(); // 目标手机号码，测试时请更改号码。
					System.out.println(LocaleUtil.getLocalText("zh_CN","tel.code","tel.code"));
//					sendMsg[0].strMsg = LocaleUtil.getLocalText("tel.code").replace("{code}", jfiPosImport.getMessageCode()); // 短信内容
					System.out.println(LocaleUtil.getLocalText("zh_CN","tel.code","tel.code").replace("{code}", jfiPosImport.getMessageCode()));
					sendMsg[0].strMsg = LocaleUtil.getLocalText("zh_CN","tel.code","tel.code").replace("{code}", jfiPosImport.getMessageCode());
					iRet = sdk.Sms_Send(sendMsg, 1);
					if (iRet > 0) {
						System.out.println("发送短消息成功");
					}else{
						System.out.println("发送短消息失败:"+iRet);
					}
				}else{
					System.out.println("获取短消息失败:"+iRet);
				}
			}else{
				System.out.println("登录失败:"+iRet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sdk.Sms_DisConnect(); // 断开与服务器的连接
			sdk = null;
		}
    }

	/**
	 * 测试
	 * @param jfiPosImport
	 */
	public static void sendSms4(JfiPosImport jfiPosImport){
		smsSDK sdk = new smsSDK();
		try {
			int iRet = sdk.Sms_Connect("www.mobset.com", 111043, "test", "281512", 30); // 测试时请更改企业ID,用户名,密码
			System.out.println("还可以发送【"+sdk.Sms_KYSms()+"】条短信！");
			if (iRet == 0){// 登录成功
				iRet = sdk.Sms_KYSms();
				if (iRet >= 0) {
					str_SendMsg[] sendMsg = new str_SendMsg[1];
					sendMsg[0] = new str_SendMsg();
					sendMsg[0].strExNum = ""; // 扩展号码，不用扩展请留空。
					sendMsg[0].strMobile = jfiPosImport.getTel(); // 目标手机号码，测试时请更改号码。
//					sendMsg[0].strMsg = LocaleUtil.getLocalText("tel.code").replace("{code}", jfiPosImport.getMessageCode()); // 短信内容
					System.out.println("tel:"+jfiPosImport.getTel());
//					System.out.println("message:"+LocaleUtil.getLocalText("tel.code"));
					System.out.println("message:"+LocaleUtil.getLocalText("zh_CN","tel.code","tel.code").replace("{code}", jfiPosImport.getMessageCode()));
					sendMsg[0].strMsg = LocaleUtil.getLocalText("zh_CN","tel.code","tel.code").replace("{code}", jfiPosImport.getMessageCode()); // 短信内容
//					sendMsg[0].strMsg = jfiPosImport.getMessageCode(); // 短信内容
					iRet = sdk.Sms_Send(sendMsg, 1);
					if (iRet > 0) {
						System.out.println("发送短消息成功");
					}else{
						System.out.println("发送短消息失败:"+iRet);
					}
				}else{
					System.out.println("获取短消息失败:"+iRet);
				}
			}else{
				System.out.println("登录失败:"+iRet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sdk.Sms_DisConnect(); // 断开与服务器的连接
			sdk = null;
		}
	}

	/**
	 * 这样发回出现乱码情况
	 */
	public static void sendSms2(){
		//    	if(jfiPosImport==null){
		//    		jfiPosImport = new JfiPosImport();
		//    		jfiPosImport.setTel("13650710137");
		//    		jfiPosImport.setMessageCode("手验短信验证");
		//    	}
		smsSDK sdk = new smsSDK();
		try {
			int iRet = sdk.Sms_Connect("www.mobset.com", 111043, "test", "281512", 30); // 测试时请更改企业ID,用户名,密码
			System.out.println("还可以发送【"+sdk.Sms_KYSms()+"】条短信！");
			if (iRet == 0){// 登录成功
				iRet = sdk.Sms_KYSms();
				if (iRet >= 0) {
					str_SendMsg[] sendMsg = new str_SendMsg[1];
					sendMsg[0] = new str_SendMsg();
					sendMsg[0].strExNum = ""; // 扩展号码，不用扩展请留空。
					sendMsg[0].strMobile = "13650710137"; // 目标手机号码，测试时请更改号码。 13650710137
					sendMsg[0].strMsg = "pei pei! Have a good time! --small cat cat"; // 短信内容
					iRet = sdk.Sms_Send(sendMsg, 1);
					System.out.println("获取短消息成功:"+iRet);
				}else{
					System.out.println("获取短消息失败:"+iRet);
				}
			}else{
				System.out.println("登录失败:"+iRet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sdk.Sms_DisConnect(); // 断开与服务器的连接
			sdk = null;
		}
	}


	/**
	 * 测试是否正常显示汉字
	 * @param jfiPosImport
	 */
	public static void sendSms3(JfiPosImport jfiPosImport){
		if(jfiPosImport==null){
    		jfiPosImport = new JfiPosImport();
    		jfiPosImport.setTel("13650710137");
    		jfiPosImport.setMessageCode("手验短信验证");    		 
    	}else{
    		jfiPosImport.setMessageCode("手验短信验证");   
    	}
		System.out.println("tel.code:"+LocaleUtil.getLocalText("UTF-8","伍采峰"));
		System.out.println("messageCode:"+jfiPosImport.getMessageCode());
		LocaleUtil.getLocalText("UTF-8","tel.code").replace("{code}", jfiPosImport.getMessageCode());
    }
}
