package com.joymain.jecs.util.bill99;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 快钱账户管理配置
 * @author lenovo
 *
 */
public class Bill99Constants {
	public static final String localhost = "http://ddzf.jmtop.com/";
	//public static final String localhost = "http://ddzf.joylifeglobal.net/";
	//public static final String localhost = "http://test.joylifeglobal.net/jecsnew_ht";
	public static final String encoding = "UTF-8";
	//public static final String memberCode = "10018019544";
	public static final String showUrl = localhost + "jecs/jfiPay99billShow.jsp";
	public static final String postUrl = "https://www.99bill.com/gateway/recvMerchantInfoAction.htm";
	//public static final String postUrl = "http://sandbox.99bill.com/gateway/recvMerchantInfoAction.htm";
	public static final String inputCharset = "1";
	public static final String pageUrl = localhost + "jecs/jfiPay99billReceivePage.html";
	public static final String bgUrl = localhost + "jecs/jfiPay99billReceive.html";
	public static final String version = "v2.0";
	public static final String language = "1";
	public static final String signType1 = "1";
	public static final String signType4 = "4";
	//public static final String merchantAcctId = memberCode + "01";
	public static final String payerContactType = "1";
	public static final String payType10 = "10";
	public static final String payType13 = "13";
	public static final String redoFlag = "0";
	public static final String pid = "";
	public static final BigDecimal feeP = new BigDecimal("0");
	public static final BigDecimal fee = feeP.add(new BigDecimal("1"));
	//public static final String key = "7Q3U392SQ795CB8L";
	public static final String pubKeyName = "99bill[1].cert.rsa.20140728.cer";
	public static PublicKey publicKey = null;
	
	
	public static Map<String,Map<String,String>> account = new HashMap<String,Map<String,String>>();;
	static {
		
		//温州特斯心灵贸易有限公司xinling_236@126.com/10023127154/内蒙古、宁夏、山东省、福建省
		Map account1 = new HashMap();
		account1.put("memberCode","1002312715401");
		account1.put("password", "zmgj2013");
		account1.put("priKeyName", "keystore_new_110707.pfx");
		account1.put("key", "7Q3U392SQ795CB8L");
		account1.put("treeIndex", "00100000000000000b");
		account.put("163706", account1);//内蒙古自治区
		account.put("163731", account1);//宁夏回族自治区
		account.put("163716", account1);//山东省
		account.put("163714", account1);//福建省
		
		//余姚市中普利商贸有限公司zhongpuli_236@126.com/10023127961/上海市、浙江省
		Map account2 = new HashMap();
		account2.put("memberCode","1002312796101");
		account2.put("password", "zmgj2013");
		account2.put("priKeyName", "keystore_new_110707.pfx");
		account2.put("key", "7Q3U392SQ795CB8L");
		account2.put("treeIndex", "00100000000000000b");
		account.put("163710", account2);//上海市
		account.put("163712", account2);//浙江省
		
		
		//奉化市夏李日用品有限公司xiali_236@126.com/10023132132/北京市、甘肃省、河北省
		Map account3 = new HashMap();
		account3.put("memberCode","1002313213201");
		account3.put("password", "zmgj2013");
		account3.put("priKeyName", "keystore_new_110707.pfx");
		account3.put("key", "7Q3U392SQ795CB8L");
		account3.put("treeIndex", "00100000000000000b");
		account.put("163702", account3);//北京市
		account.put("163729", account3);//甘肃省
		account.put("163704", account3);//河北省
		
		
		//宁波江东享泰生日用品有限公司xiangtaisheng_236@126.com/10023132346/云南省
		Map account4 = new HashMap();
		account4.put("memberCode","1002313234601");
		account4.put("password", "zmgj2013");
		account4.put("priKeyName", "keystore_new_110707.pfx");
		account4.put("key", "7Q3U392SQ795CB8L");
		account4.put("treeIndex", "00100000000000000b");
		account.put("163726", account4);//云南省
		
		//重庆凰格商贸有限公司huangge_236@126.com/10023132767/重庆市
		Map account5 = new HashMap();
		account5.put("memberCode","1002313276701");
		account5.put("password", "zmgj2013");
		account5.put("priKeyName", "keystore_new_110707.pfx");
		account5.put("key", "7Q3U392SQ795CB8L");
		account5.put("treeIndex", "00100000000000000b");		
		account.put("163723", account5);//重庆市
		
		//深圳市林田商贸有限公司lintian_236@126.com/10023127425/广东省、广西
		Map account6 = new HashMap();
		account6.put("memberCode","1002312742501");
		account6.put("password", "zmgj2013");
		account6.put("priKeyName", "keystore_new_110707.pfx");
		account6.put("key", "7Q3U392SQ795CB8L");
		account6.put("treeIndex", "00100000000000000b");		
		account.put("163720", account6);//广东省
		account.put("163721", account6);//广西壮族自治区
		
		//武汉靖博科技发展有限公司jingbo_236@126.com/10023127667/河南省、湖北省
		Map account7 = new HashMap();
		account7.put("memberCode","1002312766701");
		account7.put("password", "zmgj2013");
		account7.put("priKeyName", "keystore_new_110707.pfx");
		account7.put("key", "7Q3U392SQ795CB8L");
		account7.put("treeIndex", "00100000000000000b");		
		account.put("163717", account7);//河南省
		account.put("163718", account7);//湖北省
		
		//宁波市海曙区丽都贸易有限公司lidu_236@126.com/10023127969/安徽省
		Map account8 = new HashMap();
		account8.put("memberCode","1002312796901");
		account8.put("password", "zmgj2013");
		account8.put("priKeyName", "keystore_new_110707.pfx");
		account8.put("key", "7Q3U392SQ795CB8L");
		account8.put("treeIndex", "00100000000000000b");			
		account.put("163713", account8);//安徽省

		//宁波市鄞州高鸣投资咨询有限公司gaoming_236@126.com/10023128168/黑龙江省、吉林省、辽宁省
		Map account9 = new HashMap();
		account9.put("memberCode","1002312816801");
		account9.put("password", "zmgj2013");
		account9.put("priKeyName", "keystore_new_110707.pfx");
		account9.put("key", "7Q3U392SQ795CB8L");
		account9.put("treeIndex", "00100000000000000b");			
		account.put("163709", account9);//黑龙江省
		account.put("163708", account9);//吉林省
		account.put("163707", account9);//辽宁省
		
		//宁波市鄞州中琪投资咨询有限公司zhongqitouzi_236@126.com/10023128434/贵州省、海南省、四川省
		Map account10 = new HashMap();
		account10.put("memberCode","1002312843401");
		account10.put("password", "zmgj2013");
		account10.put("priKeyName", "keystore_new_110707.pfx");
		account10.put("key", "7Q3U392SQ795CB8L");
		account10.put("treeIndex", "00100000000000000b");
		account.put("163725", account10);//贵州省
		account.put("163722", account10);//海南省
		account.put("163724", account10);//四川省
		
		//瑞安市银荣健康咨询服务有限公司yinrongjiankang@126.com/10023130759/山西省、陕西省、天津市
		Map account11 = new HashMap();
		account11.put("memberCode","1002313075901");
		account11.put("password", "zmgj2013");
		account11.put("priKeyName", "keystore_new_110707.pfx");
		account11.put("key", "7Q3U392SQ795CB8L");
		account11.put("treeIndex", "00100000000000000b");
		account.put("163705", account11);//山西省
		account.put("163728", account11);//陕西省
		account.put("163703", account11);//天津市
		
		
		//成都市泓济贸易有限公司hongji_236@126.com/10023130834/青海省、西藏、新疆、湖南省、江苏省、江西省
		Map account12 = new HashMap();
		account12.put("memberCode","1002313083401");
		account12.put("password", "zmgj2013");
		account12.put("priKeyName", "keystore_new_110707.pfx");
		account12.put("key", "7Q3U392SQ795CB8L");
		account12.put("treeIndex", "00100000000000000b");
		account.put("163730", account12);//青海省
		account.put("163727", account12);//西藏自治区
		account.put("163732", account12);//新疆维吾尔自治区
		account.put("163719", account12);//湖南省
		account.put("163711", account12);//江苏省
		account.put("163715", account12);//江西省
		account.put("null", account12);//默认收款商户号
		
	}
}
