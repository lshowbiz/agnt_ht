package com.joymain.jecs.pm.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


import com.joymain.jecs.pm.service.JpmSalesAnalysisManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class JpmSalesAnalysisController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JpmSalesAnalysisController.class);
	private JpmSalesAnalysisManager jpmSalesAnalysisManager = null;
	private JdbcTemplate jdbcTemplate;
    
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
	public JpmSalesAnalysisManager getJpmSalesAnalysisManager() {
		return jpmSalesAnalysisManager;
	}

	public void setJpmSalesAnalysisManager(
			JpmSalesAnalysisManager jpmSalesAnalysisManager) {
		this.jpmSalesAnalysisManager = jpmSalesAnalysisManager;
	}
	
	/**
	 * 查询语句
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		
		//查询年份
		int yearTmp = 2000;
    	int yearC = new Date().getYear()+1900;
    	List yearList = new ArrayList();
    	for (;yearTmp<=yearC;yearC--){
    		yearList.add(yearC);
    	}
    	request.setAttribute("yearList", yearList);
    	
		//获得传递的参数
		String strAction = request.getParameter("strAction");
		String saleFlag2 = request.getParameter("saleFlag2");
		String startLogTime = request.getParameter("startLogTime");
		String endLogTime = request.getParameter("endLogTime");
		String companyCode = request.getParameter("companyCode");
		String userCode = request.getParameter("userCode");
		String search = request.getParameter("search");//查询标识，如果是Y，则查询，默认第一次进入是不查询数据！
		String month = request.getParameter("month");//财政月 Add By WuCF 20140314
		String type = request.getParameter("type");//Modify By WuCF 20140505 统计类型  0：按金额   1：按PV
		String unit = request.getParameter("unit");//环比分析的单位
		if(StringUtils.isEmpty(companyCode)){
			companyCode = "CN";
		}
		
		CommonRecord crm=RequestUtil.toCommonRecord(request); 
		crm.setValue("companyCode", companyCode); 
		crm.setValue("userCode", userCode);
		
        List list = null; 
        //默认日期
    	SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd");   
		String defaultEndDate = dateformat2.format(new Date());
    	String defaultStartDate = (Integer.parseInt(defaultEndDate.substring(0,4))-1)+defaultEndDate.substring(4);
    	String defaultMonth = defaultEndDate.substring(0,7).replace("-", "");//默认财政月
    	
    	if(StringUtils.isEmpty(startLogTime) || "null".equals(startLogTime)){
    		//环比分析的起始日期设置 
            if("mom".equals(strAction)){
        		startLogTime = defaultStartDate.substring(0,4)+"-10-01";//默认从第一财月第一天开始
            }else{//其他统计的起始日期
            	startLogTime = defaultStartDate;
            }            	
        }
        if(StringUtils.isEmpty(endLogTime) || "null".equals(endLogTime)){
        	endLogTime = defaultEndDate;
        }
        if(StringUtils.isEmpty(month) || "null".equals(month)){
        	month = defaultMonth;
        }
        
        if("userArea".equals(strAction)){//1.会员地域分布：默认
			String province = request.getParameter("province");
			crm.setValue("province", province);
			crm.setValue("startLogTime", startLogTime);
			crm.setValue("endLogTime", endLogTime);
			if("Y".equals(search)){
				list = jpmSalesAnalysisManager.getJmiMemberInfo(crm);
			}
    	}else if("memberActivity".equals(strAction)){//2.成员活跃度分析
    		crm.setValue("saleFlag2", saleFlag2);
    		//日期如果为空，默认不查询，查询记录默认是选择日期前10天
    		if("Y".equals(search)){
	    		list = jpmSalesAnalysisManager.getJmiMemberActive(crm);
	    		if("orderType".equals(saleFlag2)){
	    			list = this.changeList(list, 11);
	    		}else{
	    			list = this.changeList(list, 8);
	    		}
    		}
    	}else if("teamAdd".equals(strAction)){//3.团队新增会员
    		String year = request.getParameter("year"); 
    		String userType = request.getParameter("userType");
    		crm.setValue("userType", userType); 
    		if(StringUtils.isEmpty(year) || "null".equals(year)){
    			if(yearList!=null && yearList.size()>=1){
    				year = yearList.get(0).toString();
    			}
    		}
    		if("Y".equals(search)){
	    		list = jpmSalesAnalysisManager.getJmiMemberNewTeams(crm);
	        	list = this.changeList(list, 12);
    		}
        	request.setAttribute("year", year);
    	}else if("mom".equals(strAction)){//4.环比分析
    		if("1".equals(type)){
    			unit = "PV";
    		}else{
    			unit = "RMB";
    		}
    		if("Y".equals(search)){
    			crm.setValue("type", type);
	    		list = jpmSalesAnalysisManager.getJmiMemberPoMemberOrder(crm);
	    		list = this.changeList(list, 13);
    		}
    	}else if("production".equals(strAction)){//5.商品分析
    		if("Y".equals(search)){
	    		list = jpmSalesAnalysisManager.getJmiMemberProduct(crm);
	    		list = this.changeList(list, 8);
    		}
    	}else if("champion".equals(strAction)){//6.推荐冠军
    		if("Y".equals(search)){
	    		list = jpmSalesAnalysisManager.getJmiMemberChampion(crm);
	    		list = this.changeList(list, 10);
    		}
    	}else if("awardTitle".equals(strAction)){//7.团队奖衔
			crm.setValue("startLogTime", startLogTime);
			crm.setValue("endLogTime", endLogTime);
			if("Y".equals(search)){
				String starType = request.getParameter("starType");
				if(StringUtils.isEmpty(starType) || "null".equals(starType)){
					starType = "2";//标示限制，查询所有奖衔
				}
				Map indexMap=new HashMap();
				//SELECT column_value FROM TABLE(JBD_HONOR_ANALY('CN','2010-01-01','2013-12-01','','2')); 
				//--查询类型(0：准奖衔；1：合格奖衔 2:ALL)
				StringBuffer sqlBuf = new StringBuffer("SELECT column_value FROM TABLE(JBD_HONOR_ANALY('");
				sqlBuf.append(companyCode);
				sqlBuf.append("','");
				sqlBuf.append(month); 
				sqlBuf.append("','");
				sqlBuf.append(userCode);
				sqlBuf.append("','");
				sqlBuf.append(starType);
				sqlBuf.append("'))");
				List jmiRecommendRefs = this.jdbcTemplate.queryForList(sqlBuf.toString());
				//[{COLUMN_VALUE=0,1622}]
				if(jmiRecommendRefs!=null && jmiRecommendRefs.size()>=1){ 
					//String[] strTemp = map.get("column_value").toString().split(",");
					Map map = null;
					Map mapT = null;
					String[] strTemp = null;
					list = new ArrayList();
					for(Object obj : jmiRecommendRefs){
						map = (Map)obj;
						mapT = new HashMap<String,String>();
						strTemp = map.get("column_value").toString().split(",");
						mapT.put("NAME", strTemp[0]);
						mapT.put("SUM", strTemp[1]);
						list.add(mapT);
					}
				}
			}
    	}else if("addPromotion".equals(strAction)){//8.新增晋级
    		crm.setValue("startLogTime", startLogTime);
			crm.setValue("endLogTime", endLogTime);
			if("Y".equals(search)){
				Map indexMap=new HashMap();
				//SELECT column_value FROM TABLE(JBD_HONOR_ANALY('CN','2010-01-01','2013-12-01','','2')); 
				//--查询类型(0：准奖衔；1：合格奖衔 2:ALL)
				StringBuffer sqlBuf = new StringBuffer("SELECT column_value FROM TABLE(JBD_HONOR_ANALY_NEWADD('");
				sqlBuf.append(companyCode);
				sqlBuf.append("','");
				sqlBuf.append(month); 
				sqlBuf.append("','");
				sqlBuf.append(userCode); 
				sqlBuf.append("'))");
				List jmiRecommendRefs = this.jdbcTemplate.queryForList(sqlBuf.toString());
				//[{COLUMN_VALUE=0,1622}]
				if(jmiRecommendRefs!=null && jmiRecommendRefs.size()>=1){ 
					//String[] strTemp = map.get("column_value").toString().split(",");
					Map map = null;
					Map mapT = null;
					String[] strTemp = null;
					list = new ArrayList();
					for(Object obj : jmiRecommendRefs){
						map = (Map)obj;
						mapT = new HashMap<String,String>();
						strTemp = map.get("column_value").toString().split(",");
						mapT.put("NAME", strTemp[0]);
						mapT.put("SUM", strTemp[1]);
						list.add(mapT);
					}
				}
			}
    	}else if("achievement".equals(strAction)){//9.区域业绩分布
    		crm.setValue("startLogTime", startLogTime);
			crm.setValue("endLogTime", endLogTime);
			String province = request.getParameter("province");
			if("Y".equals(search)){
				Map indexMap=new HashMap();
				//SELECT column_value FROM TABLE(JBD_HONOR_ANALY('CN','2010-01-01','2013-12-01','','2')); 
				//--查询类型(0：准奖衔；1：合格奖衔 2:ALL)
				StringBuffer sqlBuf = new StringBuffer("SELECT column_value FROM TABLE(JBD_BOUNS_PROVINCE_ANALY('");
				sqlBuf.append(companyCode);
				sqlBuf.append("','");
				sqlBuf.append(startLogTime);
				sqlBuf.append("','");
				sqlBuf.append(endLogTime);
				sqlBuf.append("','");
				sqlBuf.append(userCode); 
				sqlBuf.append("','");
				if(StringUtils.isNotEmpty(province)&& !"null".equals(province)){
					sqlBuf.append(province); 
				}
				sqlBuf.append("'))");
				
				List jmiRecommendRefs = this.jdbcTemplate.queryForList(sqlBuf.toString());
				//[{COLUMN_VALUE=0,1622}]
				if(jmiRecommendRefs!=null && jmiRecommendRefs.size()>=1){ 
					//String[] strTemp = map.get("column_value").toString().split(",");
					Map map = null;
					Map mapT = null;
					String[] strTemp = null;
					list = new ArrayList();
					super.initStateCodeParem(request);
					Map<String,String> alStateProvinceMap = (Map<String,String>)request.getAttribute("alStateProvinceMap");
					Map<String,String> alCityMap = (Map<String,String>)request.getAttribute("alCityMap");
					for(Object obj : jmiRecommendRefs){
						map = (Map)obj;
						mapT = new HashMap<String,String>();
						strTemp = map.get("column_value").toString().split(",");
						if(StringUtils.isEmpty(province) || "null".equals(province)){
							mapT.put("NAME", alStateProvinceMap.get(strTemp[0]));
							mapT.put("CODE", strTemp[0]);
						}else{
							mapT.put("NAME", alCityMap.get(strTemp[0]));
						}
						mapT.put("SUM", strTemp[1]);
						list.add(mapT);
					}
				}
			}
    	}else if("highIncome".equals(strAction)){//10.高收入人群
			String maxNum = request.getParameter("maxNum");
			String minMoney = request.getParameter("minMoney");
			if(StringUtils.isEmpty(maxNum) || "null".equals(maxNum)){
				maxNum = "50";
			}
			request.setAttribute("maxNum",maxNum);
			if("Y".equals(search)){
				Map indexMap=new HashMap();
				//SELECT column_value FROM TABLE(JBD_HONOR_ANALY('CN','2010-01-01','2013-12-01','','2')); 
				//--查询类型(0：准奖衔；1：合格奖衔 2:ALL)
				StringBuffer sqlBuf = new StringBuffer("SELECT column_value FROM TABLE(JBD_BOUNS_TOP_ANALY('");
				sqlBuf.append(companyCode);
				sqlBuf.append("','");
				sqlBuf.append(startLogTime);
				sqlBuf.append("','");
				sqlBuf.append(endLogTime);
				sqlBuf.append("','");
				sqlBuf.append(userCode); 
				sqlBuf.append("','");
				sqlBuf.append(maxNum);
				sqlBuf.append("','");
				sqlBuf.append(minMoney);
				sqlBuf.append("'))");
				List jmiRecommendRefs = this.jdbcTemplate.queryForList(sqlBuf.toString());
				//[{COLUMN_VALUE=0,1622}]
				if(jmiRecommendRefs!=null && jmiRecommendRefs.size()>=1){ 
					//String[] strTemp = map.get("column_value").toString().split(",");
					Map map = null;
					Map mapT = null;
					String[] strTemp = null;
					list = new ArrayList();
					for(Object obj : jmiRecommendRefs){
						map = (Map)obj;
						mapT = new HashMap<String,String>();
						strTemp = map.get("column_value").toString().split(",");
						mapT.put("NAME", strTemp[0]);
						mapT.put("SUM", strTemp[1]);
						list.add(mapT);
					}
				}
			}
    	}
        
        request.setAttribute("list", list);
        request.setAttribute("strAction", strAction);
        request.setAttribute("saleFlag2", saleFlag2);
        request.setAttribute("search", search);
        request.setAttribute("type", type);
        request.setAttribute("startLogTime",startLogTime);
        request.setAttribute("endLogTime",endLogTime);
        request.setAttribute("month", month);
        request.setAttribute("unit", unit);
        
		return new ModelAndView("pm/jpmSalesAnalysisList");
	}

	/**
  	 * 如果集合的数据没有达到指定的Num个，则用空的Map替代，防止页面显示变形
  	 * @param list
  	 * @param num
  	 * @return
  	 */
  	public List<Map<String,Object>> changeList(List<Map<String,Object>> list,Integer num){
  		//空值Map RECOMMEND_NAME=CN42734510, SUM=224
  		Map<String,Object> nullMap = new HashMap<String,Object>();
    	nullMap.put("NAME", "-");
    	nullMap.put("SUM", "0");
    	
  		if(list==null){
  			list = new LinkedList<Map<String,Object>>();
  		}
  		Integer size = list.size();
  		//如果数据个数小于指定，则填充空值
  		for(int i=0;i<num-size;i++){
  			list.add(nullMap);
  		}
  		for(int i=0;i<num-size;i++){
  			if(i>=num){
  				list.remove(i);
  			}
  		}
  		
  		//删除多余的数据
  		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
  		for(int i=0;i<num;i++){
  			returnList.add(list.get(i));
  		}
  		
  		return returnList;
  	}
}
