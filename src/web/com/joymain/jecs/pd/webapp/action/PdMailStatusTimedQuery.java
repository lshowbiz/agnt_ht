package com.joymain.jecs.pd.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;
import service.MsgSendService;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.pd.model.LogisticsQueryRequest;
import com.joymain.jecs.pd.model.MailStatus;
import com.joymain.jecs.pd.model.Order;

import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import com.joymain.jecs.pd.service.PdLogisticsBaseNumManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pr.model.LogisticsQueryResponse;
import com.joymain.jecs.pr.model.Step;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.json.InterfaceJsonUtil;
import com.joymain.jecs.util.string.StringUtil;

public class PdMailStatusTimedQuery{

	protected final Log log = LogFactory.getLog(getClass());
	public PdLogisticsBaseNumManager pdLogisticsBaseNumManager;
	public PdSendInfoManager pdSendInfoManager;
	public PdLogisticsBaseManager pdLogisticsBaseManager;
	
	public void setPdLogisticsBaseNumManager(PdLogisticsBaseNumManager pdLogisticsBaseNumManager) {
		this.pdLogisticsBaseNumManager = pdLogisticsBaseNumManager;
	}

	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public void setPdLogisticsBaseManager(PdLogisticsBaseManager pdLogisticsBaseManager) {
		this.pdLogisticsBaseManager = pdLogisticsBaseManager;
	}

	/**
     * 定时器-根据物流单号获取物流信息(每2小时查询并保存一次）
     * @author gw 2016-02-16
     * 
     */
	/*public void getMailStatusTimeQuery(){
			log.info("在类PdMailStatusTimedQuery的方法getMailStatusTimeQuery中运行,根据物流单号获取物流信息定时器开始运行");
			try{
					List list = pdLogisticsBaseNumManager.getDoForSql();
					if(null!=list){
						int m = 0;
						//为了测试的方便才注释掉的，测试完成后还是需要还原的---modify gw 2016-02-18 ----begin
						for(int i=0;i<list.size();i++){
							//判断系统当前时间：小时数是不是奇数，然后分钟数是不是处于45到59之间,如果是，那么强行终止掉物流实时信息查询  modify by fu 2016-02-18---begin
							boolean isTimeEnd = this.getTimeIsEnd();
							if(isTimeEnd){
								return;
							}
							//判断系统当前时间：小时数是不是奇数，然后分钟数是不是处于45到59之间  modify by fu 2016-02-18---end
							
						    //为了测试的方便才注释掉的，测试完成后还是需要还原的---modify gw 2015-06-16 ----end
							//for(int i=0;i<1;i++){//测试的一行，测试完后要干掉
							log.info("在类PdMailStatusTimedQuery的方法getMailStatusTimeQuery中运行,扫描表pd_logistics_base_num后有数据");
							Map map = (Map) list.get(i);// 2016-03-03
							String mailNo = (String)map.get("mailno");
							//modify by fu 如果物流单号为空，那么就不去做查询了 2015-09-22 
							if(StringUtil.isEmpty(mailNo)){
								continue;//continue的功能是结束本次循环跳到下一次循环。
							}
							//modify by fu 2015-09-22 ----------end 
							
							//物流跟踪查询由ec到OMS查询变更到EC到WMS查询,所以这一块代码注释掉--modify by fu 2016-03-09
							MailStatusSend mailStatusSend = new MailStatusSend();
							mailStatusSend.setMailNo(mailNo);
							//将java对象转换成JSON对象
							JSONObject jsonObjectSend = JSONObject.fromObject(mailStatusSend);
							//将JSON对象转换为JSON字符串
							String returnnoJsonStringSend = jsonObjectSend.toString();
							
							
							//modify by fu 2016-03-09 物流跟踪查询由ec到OMS查询变更到EC到WMS查询--modify by fu 2016-03-16 需求变更，现在数据传递格式是JSON
							
							String jsonString = this.getLogisticsQueryRequestJson(mailNo);
							if("".equals(jsonString)|| null == jsonString){
								continue;
							}
							MsgSendService msgSendService = new MsgSendService();
							msgSendService.setSender(Constants.WMS_SEND);// modify by fu 2016-03-09 物流跟踪查询由ec到OMS查询变更到EC到WMS查询,修改之前的数值为OMS_SEND
							//方法名
							String methodName = "LogisticsSerch";//modify by fu 2016-03-09 物流跟踪查询由ec到OMS查询变更到EC到WMS查询,修改之前的数值为ship.search
							log.info("在类PdMailStatusTimedQuery的方法中gainMailStatus运行,发送请求的JSON字符串:"+jsonString);

							
							
						    //调用外部接口------------------------------------------自己测试完重新打开这一行
							String jsonResponseString = msgSendService.postToWms(jsonString,methodName);
							if(StringUtil.isEmpty(jsonResponseString)){
								continue;
							}
							//String xmlResponseString = this.getTestXMLString();
							log.info("在类PdMailStatusTimedQuery的方法中gainMailStatus运行,物流信息返回结果:"+jsonResponseString);
							//=============================接口日志写入开始
							JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
							jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
							jocsInterfaceLog.setSender(Constants.WMS_SEND);// modify by fu 2016-03-09 物流跟踪查询由ec到OMS查询变更到EC到WMS查询,修改之前的数值为OMS_SEND
							jocsInterfaceLog.setMethod(methodName);//方法名称
							jocsInterfaceLog.setContent(jsonString);//发送内容content
							jocsInterfaceLog.setReturnDesc(jsonResponseString);//返回内容
							
							ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作//写入日志操作
							//=============================接口日志写入完毕
							
							log.info("在类PdMailStatusTimedQuery的方法中gainMailStatus运行,成功往日志表中写发送日志");
							String numIdS = (String)map.get("num_id");
						    Long numId = Long.parseLong(numIdS);
					    	String status = (String)map.get("status");
                           
					    	// modify by fu 2016-03-09 物流跟踪查询由ec到OMS查询变更到EC到WMS查询
					    	LogisticsQueryResponse logisticsQueryResponse = this.getJSONLogisticsQueryResponse(jsonResponseString);
					    	if(null==logisticsQueryResponse || null==logisticsQueryResponse.getClientID()){
					    		continue;//XML字符串转换成对象失败或暂无物流信息
					    	}else{
					    		String mailStatusjsonString = this.getMailStatusJSONString(logisticsQueryResponse);
					    		if("".equals(mailStatusjsonString)|| null == mailStatusjsonString){
					    			continue;//对象转换成json字符串失败
					    		}else{
					    			String baseId = (String)map.get("base_id");
					    			//保存或更新物流单号实时信息
					    			pdLogisticsBaseManager.suMailStatus(mailStatusjsonString,status,numId,baseId);
					    		}
					    	}
						}
					}
			}catch(Exception e){
				log.info("在类PdMailStatusTimedQuery的方法中getMailStatusTimeQuery运行,根据物流单号获取物流信息定时器异常"+e);
				e.printStackTrace();
			}
		}
	*/
	//modify by fu 2017-01-18 注释掉物流定时查询
	
	/**
	 * JSON转换成对象LogisticsQueryResponse
	 * @author fu 2016-03-16
	 * @param mailNo
	 * @return logisticsQueryResponse
	 */
	private LogisticsQueryResponse getJSONLogisticsQueryResponse(String jsonString) {
		   LogisticsQueryResponse logisticsQueryResponse = null;
		   log.info("在类PdMailStatusTimedQuery的方法中getJSONLogisticsQueryResponse运行,JSON转换成对象");
		   try{
			    Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
		        classMap.put("orders", com.joymain.jecs.pr.model.Order.class);
		        classMap.put("steps", Step.class);
		        logisticsQueryResponse = InterfaceJsonUtil.returnnoJsonToModel(jsonString,LogisticsQueryResponse.class,classMap);
		   }catch(Exception e){
				log.info("在类PdMailStatusTimedQuery的方法中getJSONLogisticsQueryResponse运行,JSON转换成对象异常"+e);
				e.printStackTrace();
			}
		return logisticsQueryResponse;
	}

	/**
	 * 获取物流跟踪查询单号的JSON串
	 * @author fu 2016-03-16
	 * @param mailNo
	 * @return string
	 */
	private String getLogisticsQueryRequestJson(String mailNo) {
	      log.info("在类PdMailStatusTimedQuery的方法中getLogisticsQueryRequestJson运行,获取物流跟踪查询单号的JSON串");
		  String result = "";
		  try{
				  LogisticsQueryRequest logisticsQueryRequest = new LogisticsQueryRequest("ZM");
			      List<Order> orders = new ArrayList();
			      Order order = new Order();
			      order.setMailNo(mailNo);
			      orders.add(order);
			      logisticsQueryRequest.setOrders(orders);
			      JSONObject joThree = JSONObject.fromObject(logisticsQueryRequest);
			      result = joThree.toString();
		  }catch(Exception e){
			    log.info("在类PdMailStatusTimedQuery的方法中getLogisticsQueryRequestJson运行,获取物流跟踪查询单号的JSON串异常"+e);
				e.printStackTrace(); 
		  }
		  return result;
	}

	/**
	 * 判断系统当前时间：小时数是不是奇数，然后分钟数是不是处于40到59之间
	 * @author fu 2016-02-18
	 * @param
	 * @return
	 * 
	 */
	public boolean getTimeIsEnd(){
		Date date = new Date();
		int hours = date.getHours();
		log.info("在类PdMailStatusTimedQuery的方法中getTimeIsEnd运行,系统当前时间的小时数:"+hours);
		System.out.println("小时数:"+hours);
		int minutes = date.getMinutes();
		System.out.println("分钟数:"+minutes);
		
		//modify by fu 2016-05-05 当前时间的小时数是22并且分钟数是30<分钟<59，那么循环终止,即返回true---begin
		if(22==hours && (30<minutes && minutes<59)){
			return true;
		}else{
			return false;
		}
		//当前时间的小时数是22并且分钟数是30<分钟<59，那么循环终止,即返回true---end

		
		/*//当前时间的小时数是奇数并且分钟数是45<分钟<59，那么循环终止,即返回true---------------------begin
		if(hours%2==0){
		    return false;             
		}else{
			log.info("在类PdMailStatusTimedQuery的方法中getTimeIsEnd运行,系统当前时间的分钟数:"+minutes);
			if(40<minutes && minutes<59){
				return true;
			}else{
				return false;
			}
		}
		//当前时间的小时数是奇数并且分钟数是45<分钟<59，那么循环终止,即返回true---------------------end
       */
		
	}
	
	/**
	 * 将对象logisticsQueryResponse转换成对象mailStatus对象的json格式字符串
	 * @author fu 2016-03-08
	 * @param logisticsQueryResponse
	 * @return string
	 */
	public String getMailStatusJSONString(LogisticsQueryResponse logisticsQueryResponse){
		String result = "";
		try{
			log.info("在类PdMailStatusTimedQuery的方法中getMailStatusJSONString运行-开始");
			if(null!=logisticsQueryResponse){
				List<com.joymain.jecs.pr.model.Order> orders = logisticsQueryResponse.getOrders();
				if(null!=orders){
					for(int i=0;i<orders.size();i++){
						com.joymain.jecs.pr.model.Order orderO = orders.get(0);
						MailStatus mailStatus = new MailStatus();
						mailStatus.setMail_no(orderO.getMailNo());
						mailStatus.setLogist_comp(orderO.getLogisticProviderID());
						
						List<Step> steps = orderO.getSteps();
						List<Items> items = new ArrayList();
						if(null!=steps){
							for(int p=0;p<steps.size();p++){
								Step step = steps.get(p);
								Items item = new Items();
								item.setAcceptTime(step.getAcceptTime());
								item.setAcceptAddress(step.getAcceptAddress());
								item.setRemark(step.getRemark());
								items.add(item);
							}
							mailStatus.setItems(items);
						}
						log.info("在类PdMailStatusTimedQuery的方法中getMailStatusJSONString运行:Java对象开始转换成json字符串");
						JSONObject jsonObject = JSONObject.fromObject(mailStatus);
						result = jsonObject.toString();
						log.info("在类PdMailStatusTimedQuery的方法中getMailStatusJSONString运行:Java对象转换成json字符串为:"+result);
	                    return result;
					}
				}
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			log.info("在类PdMailStatusTimedQuery的方法中getMailStatusJSONString运行:Java对象转换成json字符串异常"+e.toString());
			return result;
		}
	}
	
}
