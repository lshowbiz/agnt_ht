package com.joymain.jecs.util.alipay;

import java.util.HashMap;
import java.util.Map;

public class AlipayConstants {

	public static String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?";
	
	private static String host = "http://59.41.117.74:8080/jecs/";
	
	public static String postUrl = "https://www.alipay.com/cooperate/gateway.do";
	//页面编码
	public static String _input_charset = "utf-8"; 
	//接口名称
	public static String service = "create_direct_pay_by_user";
	//通知URL
	public static String notify_url = host + "jfiPayAlipayReceiveNotify.html";
	//返回URL
	public static String return_url = host + "jfiPayAlipayReceiveReturn.html";
	//签名方式
	public static String sign_type = "MD5";
	//支付类型
	public static String payment_type = "1";
	//银行支付
	public static String paymethod = "bankPay";
	//商品名称
	public static String subject = "中脉产品";//"商品名称*商品订单号：20100804171256";
	//商品展示网址
	public static String show_url = "";//"商品展示地址";
	//商品描述
	public static String body = "";//"商品描述";
	
	public static Map<String,Map<String,String>> account = new HashMap<String,Map<String,String>>();;
	public static Map<String,String> accountEmail = new HashMap<String,String>();;
	static {
		Map<String,String> account1 = new HashMap<String,String>();
		account1.put("partnerID","2088101568345155");
		account1.put("key", "xu6xamwvgk5b51ahco9sgpbxy1e49ve9");
		account1.put("sellerEmail", "gwl25@126.com");
		account.put("abc", account1);
		
		accountEmail.put("gwl25@126.com", "2088101568345155");
	}
}
