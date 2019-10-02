package com.joymain.jecs.fi.model;

import java.util.HashMap;
import java.util.Map;

public class Bill99PosConstants {
	public static final String url = "https://mas.99bill.com/vpos/oms/omsGateway.htm";
	public static final String loginKey = "zm0113cp";
	public static final String txnType = "PUR";
	public static final Map accountMap = new HashMap();
	static{
		accountMap.put("CNPOS0", "CNPOS00000");
		accountMap.put("CNPOS1", "CNPOS10000");//杭州市滨江区嘉程日用品商行    999310053110007  30000378
		accountMap.put("CNPOS2", "CNPOS20000");//青白江区贤润日用品经营部        999310053110008  30000379
		accountMap.put("CNPOS3", "CNPOS30000");//上海维美赢文化发展有限公司    999310053110009  30000380
		accountMap.put("CNPOS4", "CNPOS40000");//杭州市滨江区王建梅日用品商行999310053110010 30000381
		accountMap.put("CNPOS5", "CNPOS50000");//大连宝丽康贸易有限公司    999310053110011  30000382
		accountMap.put("CNPOS6", "CNPOS60000");//杭州萧山新塘奥盛日用品商行    999310053110012  30000383
		accountMap.put("CNPOS7", "CNPOS70000");//南昌市湾里区陆敏日用品商行    999310053110006  30000377
		accountMap.put("CNPOS8", "CNPOS80000");
		accountMap.put("CNPOS9", "CNPOS90000");
	}
}
