package com.joymain.jecs.util.bill99ms;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Bill99msConstants {
	public static final String localhost = "http://58.62.87.129:8080/";
	public static final String encoding = "UTF-8";
	public static final String showUrl = localhost + "jecs/jfiPay99billmsShow.jsp";
	public static final String postUrl = "https://www.99bill.com/msgateway/recvMsgatewayMerchantInfoAction.htm";
	//public static final String postUrl = "https://sandbox2.99bill.com/msgateway/recvMsgatewayMerchantInfoAction.htm";
	public static final String inputCharset = "1";
	public static final String pageUrl = localhost + "jecs/jfiPay99billmsReceivePage.html";
	public static final String bgUrl = localhost + "jecs/jfiPay99billmsReceive.html";
	public static final String version = "v2.0";
	public static final String language = "1";
	public static final String signType1 = "1";
	public static final String payeeContactType = "1";
	public static final String sharingPayFlag = "1";
	public static final String signType4 = "4";
	public static final String payType10 = "10";
	public static final String payType13 = "13";
	public static final BigDecimal feeP = new BigDecimal("0");
	public static final BigDecimal fee = feeP.add(new BigDecimal("1"));
	public static final String pubKeyName = "99bill.cert.rsa.20140803.cer";
	public static PublicKey publicKey = null;
	
	
	public static Map<String,Map<String,String>> account = new HashMap<String,Map<String,String>>();;
	static {
		Map account1 = new HashMap();
		account1.put("memberCode","xiaojun_236@126.com");
		account1.put("password", "zmgj2013");
		account1.put("priKeyName", "keystore_new_110707.pfx");
		account1.put("sharingData", "1^zhongling_236@126.com^@^0^ms1");
		account1.put("pid", "10019385597");
		account.put(account1.get("memberCode").toString(), account1);
		account.put("163713", account1);
		account.put("163717", account1);
		account.put("163718", account1);
		account.put("163711", account1);
	}
}
