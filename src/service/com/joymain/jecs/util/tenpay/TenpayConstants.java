package com.joymain.jecs.util.tenpay;

import java.util.HashMap;
import java.util.Map;

public class TenpayConstants {
	//请求URL
	public static String postUrl = "https://www.tenpay.com/cgi-bin/v4.0/pay_set.cgi";
	//版本号
	public static String version = "4";
	//业务类型
	public static String bus_type = "97";
	//接收财付通返回结果的URL
	public static String return_url = "http://ec.joymaingroup.com/jecs/jfiPayTenpayReceiveNotify.html";
	//public static String return_url = "http://58.63.142.195:8080/jecs/jfiPayTenpayReceiveNotify.html";
	//接收财付通返回结果后展示页面
	public static String show_url = "http://ec.joymaingroup.com/jecs/jfiPayTenpayShow.html";
	//public static String show_url = "http://58.63.142.195:8080/jecs/jfiPayTenpayShow.html";
	//现金支付币种
	public static String fee_type = "1";
	//业务代码
	public static String cmdno = "1";
	//页面编码
	public static String _input_charset = "gbk"; 
	//页面编码
	public static String desc = "中脉产品支付"; 
	
	public static Map<String,Map<String,String>> account = new HashMap<String,Map<String,String>>();;
	static {
		Map<String,String> account1 = new HashMap<String,String>();
		account1.put("bargainor_id", "1211667501");
		account1.put("bus_args", "1211662101@mch.tenpay.com^M1^R1|1211662201@mch.tenpay.com^M2^R2");
		account1.put("bus_desc", "A1^代付商户号A1^1^A2^代付商户号A2^2");
		account1.put("key", "04779a0ac9deab7a6ccdf8bf575fc169");
		account.put("163719", account1);
		account.put("163711", account1);
		account.put("163715", account1);
		account.put("1211667501", account1);

		Map<String,String> account2 = new HashMap<String,String>();
		account2.put("bargainor_id", "1211667301");
		account2.put("bus_args", "1211661501@mch.tenpay.com^M1^R1|1211661601@mch.tenpay.com^M2^R2");
		account2.put("bus_desc", "B1^代付商户号B1^1^B2^代付商户号B2^2");
		account2.put("key", "f36a52d9f0570678ac685eb4e738bb47");
		account.put("163707", account2);
		account.put("163708", account2);
		account.put("163709", account2);
		account.put("1211667301", account2);

		Map<String,String> account3 = new HashMap<String,String>();
		account3.put("bargainor_id", "1211667001");
		account3.put("bus_args", "1211661701@mch.tenpay.com^M1^R1|1211661301@mch.tenpay.com^M2^R2");
		account3.put("bus_desc", "C1^代付商户号C1^1^C2^代付商户号C2^2");
		account3.put("key", "8e41d5e5063774662f6be4e47fbd2c3b");
		account.put("163730", account3);
		account.put("163727", account3);
		account.put("163732", account3);
		account.put("null", account3);
		account.put("1211667001", account3);

		Map<String,String> account4 = new HashMap<String,String>();
		account4.put("bargainor_id", "1211667601");
		account4.put("bus_args", "1211661901@mch.tenpay.com^M1^R1|1211662001@mch.tenpay.com^M2^R2");
		account4.put("bus_desc", "D1^代付商户号D1^1^D2^代付商户号D2^2");
		account4.put("key", "0e77efe2478ccea51be1e0cf5dd1ee4a");
		account.put("163725", account4);
		account.put("163722", account4);
		account.put("163724", account4);
		account.put("1211667601", account4);
	}
}
