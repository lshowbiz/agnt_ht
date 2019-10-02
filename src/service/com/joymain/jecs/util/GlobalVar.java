package com.joymain.jecs.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;



public class GlobalVar {
	 /**
	 * <li>team Map</li>
	 */
	 public static Map<String,String> teamMap = new HashMap<String, String>();
	 public static Map<String,String> teamNameMap = new HashMap<String, String>();
	 /**
	  * <li>order Type map</li>
	  */
	 public static Map<String,String> orderMap = new HashMap<String, String>();
	 public static Set<String> comSet = new HashSet<String>();
	 
	 /**单独购买产品列表（9个）*/
	 public static List<String> jpoList=new ArrayList<String>();
	 
	 /**中秋礼盒单独购买产品列表（2个）*/
	 public static List<String> zqList=new ArrayList<String>();
	 /**
	  * US Tax setting
	  */
	 public static Map<String,BigDecimal> taxMap= new HashMap<String, BigDecimal>();
	 public static final String MEMBER="M";
	 
	 /**
	  * 201512月非套餐包促销
	  */
	 public static List<String> jpoList20151201 = new ArrayList<String>();
	 
	 static{
		 
		 teamMap.put("CN10829722", "PD");
		 teamMap.put("CN49698390", "SXH");
		 teamMap.put("CN54623113", "LT");
		 teamMap.put("CN18645446", "YN");
		 teamMap.put("CN40449939", "QD");
		 teamMap.put("CN18766575", "TT2-1");
		 teamMap.put("CN16481747", "TT2-2");
		 teamMap.put("CN10919893", "TT2-3");
		 teamMap.put("CN55092684", "TT2-4");
		 teamMap.put("CN18728599", "TT2-5");
		 teamMap.put("CN62827846", "TT2-6");
		 teamMap.put("CN58387198", "TT2-7");
		 teamMap.put("CN70575434", "WJM");
		 teamMap.put("CN19499609", "SZ");
		 teamMap.put("CN66223208", "WYJ");
		 teamMap.put("CN35917263", "MYQ");
		 teamMap.put("CN00010007","LJY");
		 teamMap.put("CN11178310", "RW");
		 //teamMap.put("CN18736617", "SZF");
		 teamMap.put("CN54416666", "ZJ");
		 
		 teamNameMap.put("CN10829722", "广州排毒");
		 teamNameMap.put("CN49698390", "沈小慧");
		 teamNameMap.put("CN54623113", "骆天");
		 teamNameMap.put("CN18645446", "刘建强");
		 teamNameMap.put("CN40449939", "青岛三生");
		 teamNameMap.put("CN18766575", "田阳1");
		 teamNameMap.put("CN16481747", "田阳2");
		 teamNameMap.put("CN10919893", "田阳3");
		 teamNameMap.put("CN55092684", "田阳4");
		 teamNameMap.put("CN18728599", "田阳5");
		 teamNameMap.put("CN62827846", "田阳6");
		 teamNameMap.put("CN58387198", "田阳7");
		 teamNameMap.put("CN19499609", "数字1");
		 teamNameMap.put("CN70575434", "王建梅");
		 teamNameMap.put("CN66223208", "吴亚君");
		 teamNameMap.put("CN35917263", "毛玉琴");
		 teamNameMap.put("CN00010007", "罗景耀");
		 teamNameMap.put("CN11178310", "任伟团队");
		 //teamNameMap.put("CN18736617", "宋自甫");
		 teamNameMap.put("CN54416666", "张洁");	

		 orderMap.put("1", "首购单");
		 orderMap.put("2", "升级单");
		 orderMap.put("3", "续约单");
		 orderMap.put("4", "重消单");
		 orderMap.put("5", "辅销单");
		 orderMap.put("6", "店铺首购");
		 orderMap.put("9", "店铺重消");
		 orderMap.put("11", "二级店铺首购");
		 orderMap.put("12", "二级店铺升级");
		 orderMap.put("14", "二级店铺重消");
		 orderMap.put("40", "全年重消单");
		 orderMap.put("32", "奖衔补单");
		 orderMap.put("93", "代金券换购单");
		 
		 comSet.add("US");
		 comSet.add("CN");
		 comSet.add("RU");
		 comSet.add("TW");
		
		taxMap.put("20008144P01010100101EN0", new BigDecimal("0.03"));//Utah,Galaxy 
		taxMap.put("20008144P05010300101EN0", new BigDecimal("0.0625"));//Utah,Energy Cup
		taxMap.put("20008144P06010100101EN0", new BigDecimal("0.0625"));//Utah,Intense
		taxMap.put("20008104P01010100101EN0", new BigDecimal("0"));//CA,Galaxy 
		taxMap.put("20008104P05010300101EN0", new BigDecimal("0.0875"));//CA,Energy Cup
		taxMap.put("20008104P06010100101EN0", new BigDecimal("0.0875"));//CA,Intense
		taxMap.put("20008128P01010100101EN0", new BigDecimal("0"));//Nevada,Galaxy 
		taxMap.put("20008128P05010300101EN0", new BigDecimal("0.0685"));//Nevada,Energy Cup
		taxMap.put("20008128P06010100101EN0", new BigDecimal("0.0685"));//Nevada,Intense
		
		jpoList.add("P25060100101CN0");  // P09070100101CN0
		jpoList.add("P25060200101CN0");  // P03080100101CN0
		jpoList.add("P25060300101CN0");  // P03150100101CN0
		jpoList.add("P25060400101CN0");
		jpoList.add("P25060500101CN0");
		jpoList.add("P25070100101CN0");
		jpoList.add("P25070200101CN0");
		jpoList.add("P25070300101CN0");
		jpoList.add("P25010100101CN0");
		
		zqList.add("P23030100101CN0");
		zqList.add("P23030200101CN0");
		
		
		
//		jpoList20151201.add("P21560100101CN0");
//		jpoList20151201.add("P21560200101CN0");
//		jpoList20151201.add("P21560300101CN0");
//		jpoList20151201.add("P21560400101CN0");
//		jpoList20151201.add("P21560500101CN0");
//		jpoList20151201.add("P21560600101CN0");
//		jpoList20151201.add("P21560700101CN0");
//		jpoList20151201.add("P21560800101CN0");
//		jpoList20151201.add("P21560900101CN0");
//		jpoList20151201.add("P21561000101CN0");
//		jpoList20151201.add("P21561100101CN0");
//		jpoList20151201.add("P21561200101CN0");
//		jpoList20151201.add("P21561300101CN0");
//		jpoList20151201.add("P21561400101CN0");
		
		//201601追加
//		jpoList20151201.add("P21570100101CN0");
//		jpoList20151201.add("P21570200101CN0");
//		jpoList20151201.add("P21570300101CN0");
//		jpoList20151201.add("P21570400101CN0");
//		jpoList20151201.add("P21570500101CN0");
//		jpoList20151201.add("P21570600101CN0");
//		jpoList20151201.add("P21570700101CN0");
		//事业部
//		jpoList20151201.add("P21570800101CN0");
//		jpoList20151201.add("P21570900101CN0");
//		jpoList20151201.add("P21571000101CN0");
//		jpoList20151201.add("P21571100101CN0");
//		jpoList20151201.add("P21571200101CN0");
		
//		jpoList20151201.add("P21571300101CN0"); //追加空气净化器套餐		
//		jpoList20151201.add("P21571400101CN0"); //追加颐萃茸聚糖压片糖果套餐包
		
		//201602追加
//		jpoList20151201.add("P21580100101CN0");
//		jpoList20151201.add("P21580200101CN0");
//		jpoList20151201.add("P21580300101CN0");
//		jpoList20151201.add("P21580400101CN0");
//		jpoList20151201.add("P21580500101CN0");
//		jpoList20151201.add("P21580600101CN0");
//		jpoList20151201.add("P21580700101CN0");
//		jpoList20151201.add("P21580800101CN0");
//		jpoList20151201.add("P21580900101CN0");
//		jpoList20151201.add("P21581000101CN0");
//		jpoList20151201.add("P21581100101CN0");
//		jpoList20151201.add("P21581200101CN0");
//		jpoList20151201.add("P21581300101CN0");
//		jpoList20151201.add("P21581400101CN0");
//		jpoList20151201.add("P21581500101CN0");
//		jpoList20151201.add("P21581600101CN0");
		
		//201604
//		jpoList20151201.add("P21590100101CN0");
//		jpoList20151201.add("P21590200101CN0");
//		jpoList20151201.add("P21590300101CN0");
//		jpoList20151201.add("P21590400101CN0");
//		jpoList20151201.add("P21590500101CN0");
//		jpoList20151201.add("P21590600101CN0");
//		jpoList20151201.add("P21590700101CN0");
//		jpoList20151201.add("P21590800101CN0");
//		jpoList20151201.add("P21590900101CN0");
//		jpoList20151201.add("P21591000101CN0");
//		jpoList20151201.add("P21591100101CN0");
//		jpoList20151201.add("P21591200101CN0");
//		jpoList20151201.add("P21591300101CN0");
//		jpoList20151201.add("P21591400101CN0");
//		jpoList20151201.add("P21591500101CN0");
//		jpoList20151201.add("P21591600101CN0");
//		jpoList20151201.add("P21591700101CN0");
		
		//201605
//		jpoList20151201.add("P21600100101CN0");
//		jpoList20151201.add("P21600200101CN0");
//		jpoList20151201.add("P21600300101CN0");
//		jpoList20151201.add("P21600400101CN0");
//		jpoList20151201.add("P21600500101CN0");
//		jpoList20151201.add("P21600600101CN0");
//		jpoList20151201.add("P21600700101CN0");
//		jpoList20151201.add("P21600800101CN0");
//		jpoList20151201.add("P21600900101CN0");
//		jpoList20151201.add("P21601000101CN0");
//		jpoList20151201.add("P21601100101CN0");
//		jpoList20151201.add("P21601200101CN0");
//		jpoList20151201.add("P21601300101CN0");
//		jpoList20151201.add("P21601400101CN0");
//		jpoList20151201.add("P21601500101CN0");
//		jpoList20151201.add("P21601600101CN0");
		
		//201606
		jpoList20151201.add("P21610100101CN0");
		jpoList20151201.add("P21610200101CN0");
		jpoList20151201.add("P21610300101CN0");
		jpoList20151201.add("P21610400101CN0");
		jpoList20151201.add("P21610500101CN0");
		jpoList20151201.add("P21610600101CN0");
		jpoList20151201.add("P21610700101CN0");
		jpoList20151201.add("P21610800101CN0");
		jpoList20151201.add("P21610900101CN0");
		jpoList20151201.add("P21611000101CN0");
		jpoList20151201.add("P21611100101CN0");
		jpoList20151201.add("P21611200101CN0");
		jpoList20151201.add("P21611300101CN0");
		jpoList20151201.add("P21611400101CN0");
		jpoList20151201.add("P21611500101CN0");
		jpoList20151201.add("P21611600101CN0");
		
		jpoList20151201.add("P21611700101CN0");
		jpoList20151201.add("P21611800101CN0");
		jpoList20151201.add("P21611900101CN0");
		jpoList20151201.add("P21612000101CN0");
		jpoList20151201.add("P21612100101CN0");
		jpoList20151201.add("P21612200101CN0");
		jpoList20151201.add("P21612300101CN0");
		jpoList20151201.add("P21612400101CN0");
		jpoList20151201.add("P21613200101CN0");
		jpoList20151201.add("P21613300101CN0");
		jpoList20151201.add("P21613400101CN0");
		jpoList20151201.add("P21612500101CN0");
		jpoList20151201.add("P21612600101CN0");
		jpoList20151201.add("P21612700101CN0");
		jpoList20151201.add("P21612800101CN0");
		
		jpoList20151201.add("P21613500101CN0");
		
		//201607
		jpoList20151201.add("P21620100101CN0");
		jpoList20151201.add("P21620200101CN0");
		jpoList20151201.add("P21620300101CN0");
		jpoList20151201.add("P21620400101CN0");
		jpoList20151201.add("P21620500101CN0");
		jpoList20151201.add("P21620600101CN0");
		jpoList20151201.add("P21620700101CN0");
		jpoList20151201.add("P21620800101CN0");
		jpoList20151201.add("P21620900101CN0");
		jpoList20151201.add("P21621000101CN0");
		jpoList20151201.add("P21621100101CN0");
		jpoList20151201.add("P21621200101CN0");
		jpoList20151201.add("P21621300101CN0");
		jpoList20151201.add("P21623100101CN0");
		jpoList20151201.add("P21621400101CN0");
		jpoList20151201.add("P21621500101CN0");
		jpoList20151201.add("P21621600101CN0");
		jpoList20151201.add("P21621700101CN0");
		jpoList20151201.add("P21621800101CN0");
		jpoList20151201.add("P21621900101CN0");
		jpoList20151201.add("P21622000101CN0");
		jpoList20151201.add("P21622100101CN0");
		jpoList20151201.add("P21622200101CN0");
		jpoList20151201.add("P21622300101CN0");
		jpoList20151201.add("P21622400101CN0");
		jpoList20151201.add("P21622500101CN0");
		jpoList20151201.add("P21622600101CN0");
		jpoList20151201.add("P21622700101CN0");
		jpoList20151201.add("P21622900101CN0");
		jpoList20151201.add("P21622800101CN0");
		jpoList20151201.add("P21623000101CN0");
		
		jpoList20151201.add("P21623200101CN0");
		jpoList20151201.add("P21623300101CN0");
		jpoList20151201.add("P21623400101CN0");
		jpoList20151201.add("P21623500101CN0");
		jpoList20151201.add("P21623600101CN0");




	 }
	 /**
		* 判断是否此团队下的会员
		* <li>user is login user</li>
		* @param user
		* @param JmiMemberManager
		* @return team code
		*/
	 public static String teamStr(SysUser user,JmiMemberManager jmiMemberManager){
		 String str = "0";
		 System.out.println("userType is:["+user.getUserType()+"]");
		 if(MEMBER.equals(user.getUserType())){
			 	Iterator<String> ite = teamMap.keySet().iterator();
	        	while(ite.hasNext()){
	        		String userCode = ite.next();
	        		JmiMember lcMiMember =jmiMemberManager.getJmiMember(userCode);
	        		if(lcMiMember!=null ){
		        		String lcTreeIndex=lcMiMember.getJmiRecommendRef().getTreeIndex();
		        		String loninTreeIndex=user.getJmiMember().getJmiRecommendRef().getTreeIndex();
		        		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, lcTreeIndex.length());
		        		if(loninTreeIndex.length() >= lcTreeIndex.length() && rmemberIndexTmp.equals(lcTreeIndex)){
		        			str = teamMap.get(userCode);
		        			break;
		        		}
		        	 }
	        	}
	     }
		 return str;
	 } 
	 

	 public static int theadListSize=0;//奖金进电子存折 需执行的数量
	 public static int theadSize=0;
	 public static String theadStatus="0";//奖金进电子存折运行状态 0未运行  1运行中
	 

	 public static String BatchRefundStatus="0";//批量退货退款运行状态 0未运行  1运行中
}
