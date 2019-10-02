package com.joymain.jecs.po.webapp.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import service.MsgSendService;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.service.FiBillAccountManager;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.webapp.util.SmsSend;

@SuppressWarnings("unchecked")
public class JpozAutoShipQuartz {
	private JpoMemberOrderManager jpoMemberOrderManager;
	private JpmProductSaleNewManager jpmProductSaleNewManager;
	private JfiQuotaManager jfiQuotaManager;
	private FiBillAccountManager fiBillAccountManager;
	private BdPeriodManager bdPeriodManager;
	private JdbcTemplate jdbcTemplate = null;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    protected final Log log = LogFactory.getLog(getClass());
//	private JSONObject jsonObj = new JSONObject();
//	private JSONArray orders = new JSONArray();
//	private List<JpoMemberOrder> jpoMemberOrders;
	
	
	public BdPeriodManager getBdPeriodManager() {
		return bdPeriodManager;
	}


	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}


	private PdSendInfoManager pdSendInfoManager;
	private MsgSendService msgSendService;
	
	
	
	public void getCheckOrder(){	
		String swtich = ListUtil.getListValue("CN", "ordercheck.switch", "3");
		System.out.println("======swtich:"+swtich);
		/*if("Y".equals(swtich)){
			String ur1 = ListUtil.getListValue("CN", "smssend.url", "1");
			String ur2 = ListUtil.getListValue("CN", "smssend.url", "2");
			System.out.println("======发送短信测试:");
			SmsSend.sendSms(ur1,ur2,"13650710137", "测试");
		}
		*/
		//-------------------------------------------------发送
		//获取接口开关标示
		String orderNum = ListUtil.getListValue("CN", "ordercheck.switch", "1");//获取返回订单问题条数
		String mobiles = ListUtil.getListValue("CN", "ordercheck.switch", "2");//获取需要发送短信的手机号码
		String sql = "select distinct t.MEMBER_ORDER_NO from jpo_member_order t where t.status='1' and exists( "+
					 " select 1 from jpo_bankbook_pay_hist t2 where t.member_order_no=t2.member_order_no) "+
					 " and to_char(t.order_time,'yyyy-MM-dd')>='2017-01-16' and rownum<=15";
		
		List<Map<String,String>> list = this.jdbcTemplate.queryForList(sql);
		System.out.println("list:"+list);
		if(list!=null && list.size()>=Integer.parseInt(orderNum) && "Y".equals(swtich)){
			System.out.println("=====进入到发送短信："+orderNum);
			String memberOrderNoStr = "";
			for(Map map : list){
				memberOrderNoStr += memberOrderNoStr+map.get("MEMBER_ORDER_NO").toString()+",";
			}
			
			String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
			String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
			log.info("===url1:"+url1+"-"+url1);
			//字符串
			if(!"".equals(memberOrderNoStr)){
				String[] mobileStrs = mobiles.split(",");
				for(String mobileStr : mobileStrs){
					StringBuffer message = new StringBuffer("审单出现异常！"+memberOrderNoStr);
					log.info("发送短信："+mobileStr+"-"+message.toString());
					SmsSend.sendSms(url1,url2,mobileStr, message.toString());
				}
				
			}
		}
	}
	
	
	public void getSendOrdersTransferTask(){	
		log.info("在类JpozAutoShipQuartz的getSendOrdersTransferTask方法中开始运行：订单推送开始");
		//获取接口开关标示
		String swtichSend = ListUtil.getListValue("CN", "interface.sendswitch", "order.add");  
		if(null != swtichSend && "Y".equals(swtichSend)){
			log.info("在类JpozAutoShipQuartz的getSendOrdersTransferTask方法中开始运行：订单推送消息开关打开");
			sendOrdersTransferTask();
		}
	}
	
	public void sendOrdersTransferTask(){
		//Modify By WUCF 20160101 加上开关标示
		String isOk = "";//如果isOk取值不为N，则调用查询语句
		try{
			String MerKeyFile = this.getClass().getResource("").getPath();
//    	    System.out.println("====MerKeyFile:"+MerKeyFile);
    	    int num = MerKeyFile.indexOf("WEB-INF");
//    	    System.out.println("num:"+num);
//    	    System.out.println(MerKeyFile.substring(0,num));
    	    
    	    Properties props = new Properties();
    		File file = new File(MerKeyFile.substring(0,num)+"member.properties");
//    		System.out.println("file:"+file);
    		InputStream in = new BufferedInputStream (new FileInputStream(file));
    	    props.load(in); 
    	    isOk = props.getProperty ("IS_OK");
    	    System.out.println("isOk:"+isOk);
		    
		}catch(Exception e){
			e.printStackTrace();
			log.info("在类JpozAutoShipQuartz的sendOrdersTransferTask方法中运行异常："+e.toString());
		}
		log.info("零售订单查询开始!");
		List<JpoMemberOrder> jpoMemberOrders = new ArrayList<JpoMemberOrder>();
		if(!"N".equals(isOk)){//如果isOk取值不为N，则调用查询语句
			System.out.println("--");
			log.info("在类JpozAutoShipQuartz的sendOrdersTransferTask方法中运行：isOk开关打开");
			jpoMemberOrders = jpoMemberOrderManager.getNotSendOrders();
		}
		log.info("查询到  " + jpoMemberOrders.size() + "  条订单数据。");
		
		for (int i = 0; i < jpoMemberOrders.size(); i++) {
			sendOrdersTransferTask(jpoMemberOrders.get(i));
			log.info("零售订单 "+ jpoMemberOrders.get(i).getMemberOrderNo() +" 已推送。");
//			new Thread(new JpozMemberordersPostTO(jpoMemberOrders.get(i))).start();
		}
		log.info("零售订单推送完成! 已推送"+ jpoMemberOrders.size() +"条数据。");
	}
	
	/**
	 * 金流限额定时器功能实现
	 */
	public void quartJfiQuota()	{	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		//调用方法
		log.info("调用生成金流限额服务-----start ");
		FiBillAccount f = new FiBillAccount();
		f.setStatus("0");
		List<FiBillAccount> list = fiBillAccountManager.getFiBillAccounts(f);
		for(FiBillAccount fb : list){ 
			JfiQuota entity = new JfiQuota();
			entity.setStatus("0");
			entity.setRemark("后台自动生成");
			entity.setValidityPeriod(sdf.format(nextMonthFirstDate()));
			entity.setAccountId(fb.getAccountId());
			entity.setMaxMoney(fb.getMaxMoney());
			entity.setOperateName("root");
			entity.setOperateTime(new Date());
			jfiQuotaManager.saveJfiQuota(entity);
		}
		log.info("调用生成金流限额服务-----end ");
	}

	
	public String sendOrdersTransferTask(JpoMemberOrder jpoMemberOrder){	
//		orders = new JSONArray();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
				JSONObject moObject  = new JSONObject();
				moObject.put("order_bn", jpoMemberOrder.getMemberOrderNo());//订单号
				moObject.put("member_num", jpoMemberOrder.getSysUser().getUserCode());//会员编号
				moObject.put("uname", jpoMemberOrder.getSysUser().getUserName());//客户名
				moObject.put("buy_time", sdf.format(jpoMemberOrder.getOrderTime()));//购买时间
				moObject.put("order_amount", jpoMemberOrder.getAmount2());//订单金额
				moObject.put("payment", jpoMemberOrder.getPayMode());//支付方式
				moObject.put("consignee", jpoMemberOrder.getFirstName()+jpoMemberOrder.getLastName());//收货人
				moObject.put("consignee_state", jpoMemberOrder.getProvince());
				moObject.put("consignee_city", jpoMemberOrder.getCity());
				moObject.put("consignee_area", jpoMemberOrder.getDistrict());
				moObject.put("consignee_address", jpoMemberOrder.getAddress());//详细地址
				moObject.put("consignee_zip", jpoMemberOrder.getPostalcode());//邮编
				moObject.put("consignee_mobile", jpoMemberOrder.getMobiletele());//手机
				moObject.put("consignee_phone", jpoMemberOrder.getPhone());//电话
				moObject.put("pay_money", jpoMemberOrder.getAmount());//现金支付金额
				moObject.put("pay_time", sdf.format(jpoMemberOrder.getCheckTime()));//付款时间
				moObject.put("consumer_terminal", jpoMemberOrder.getIsMobile());//消费终端
				moObject.put("buy_remark", jpoMemberOrder.getRemark()); //买家备注
				moObject.put("is_shipments", jpoMemberOrder.getIsShipments()); //暂不发货标识
				moObject.put("sale_num", jpoMemberOrder.getSaleNo()); //卖家      -------------?? 经销商编号
				moObject.put("shipping_fee", jpoMemberOrder.getConsumerAmount()); //运费
				if(null != jpoMemberOrder.getIsFullPay()){
					moObject.put("isfull_pay", jpoMemberOrder.getIsFullPay().toString()); //全额支付
				}else{
					moObject.put("isfull_pay", "0"); //非全额支付
				}
				this.getLoInfos(jpoMemberOrder, moObject);
//				orders.add(moObject);

//		int count = orders.size();
		
//		if(count !=0 ){

			
//			for (int i = 0; i < orders.size(); i++) {
//					JSONObject order = (JSONObject) orders.get(i);
				log.info("零售订单推送开始!");
					
					if(("").equals(moObject.get("delivery")) || null == moObject.get("delivery")  ){	
						log.info("零售订单推送信息不完整，没有发货单或者没有发货明细!");
//						count = count - 1;
					}else{
						msgSendService = new MsgSendService();
						
						//获取接口开关标示
						String swtichSendOms = ListUtil.getListValue("CN", "interface.sendswitch", "order.add.oms");  
						if(null != swtichSendOms && "Y".equals(swtichSendOms)){
							//OMS
							msgSendService.setSender(utils.Constants.OMS_SEND);
							String returnnoJsonString1 = msgSendService.post(moObject.toString(), "order.add");
							
							//=============================接口日志写入开始 Modify By WUCF 20150123
							JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
							jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
							jocsInterfaceLog.setSender(Constants.OMS_SEND);//数据的接收方
							jocsInterfaceLog.setMethod("order.add");//方法名称
							jocsInterfaceLog.setContent(moObject.toString());//发送内容content
							jocsInterfaceLog.setReturnDesc(returnnoJsonString1);//返回内容
							ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
							//=============================接口日志写入完毕	
						}
						
						String swtichSendQd = ListUtil.getListValue("CN", "interface.sendswitch", "order.add.qd");  
						if(null != swtichSendQd && "Y".equals(swtichSendQd)){
							
							if(null != jpoMemberOrder.getIsFullPay() && jpoMemberOrder.getIsFullPay() == 1){
								
								//渠道
								msgSendService.setSender(utils.Constants.QU_SEND);
								String returnnoJsonString2 = msgSendService.post(moObject.toString(), "order.add");
								
								//=============================接口日志写入开始 Modify By WUCF 20150123
								JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
								jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
								jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
								jocsInterfaceLog.setMethod("order.add");//方法名称
								jocsInterfaceLog.setContent(moObject.toString());//发送内容content
								jocsInterfaceLog.setReturnDesc(returnnoJsonString2);//返回内容
								ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
								//=============================接口日志写入完毕	
							}
						}
						
						updateOrderSended(jpoMemberOrder);
					}
//				}
		
//		}else{
//			Log.info("没有可推送的零售订单!");
//		}
		return null;
		
	}
	
	/**
	 * 遍历Mo单对应Lo单
	 * @param jpoMemberOrder
	 * @param moObject
	 */
	public void getLoInfos(JpoMemberOrder jpoMemberOrder, JSONObject moObject){
		
		JSONArray loJarray = new JSONArray();
		List<PdSendInfo> pdSendInfos = pdSendInfoManager.getPdSendInfoList(jpoMemberOrder.getMemberOrderNo());
		
		log.info(jpoMemberOrder.getMemberOrderNo()+"单对应的发货单有 "+pdSendInfos.size() +"条。");
			for (PdSendInfo pdSendInfo : pdSendInfos) {
				if(pdSendInfo.getOrderNo().equals(jpoMemberOrder.getMemberOrderNo())){
					JSONObject loObject = new JSONObject();
					loObject.put("lo_bn", pdSendInfo.getSiNo()); //发货单号
					loObject.put("logistics", pdSendInfo.getShNo()); //物流公司
					loObject.put("branch", pdSendInfo.getWarehouseNo()); //发货仓库
					this.getOrderItems(pdSendInfo, loObject);
					loJarray.add(loObject);
					moObject.put("delivery", loJarray);
				}
		}
		
	}
	
	/**
	 * 遍历Mo和Lo单对应的明细
	 * @param pdSendInfo
	 * @param loObject
	 */
	public void getOrderItems(PdSendInfo pdSendInfo, JSONObject loObject){
		
		JSONArray pmJarray = new JSONArray();
		Set<PdSendInfoDetail> pdSendInfoDetailSet = pdSendInfo.getPdSendInfoDetails();
		JpoMemberOrder jpo = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(pdSendInfo.getOrderNo());
		
		log.info(pdSendInfo.getSiNo()+"单对应的商品有 "+pdSendInfoDetailSet.size() +"条。");
		Iterator it = pdSendInfoDetailSet.iterator();
		while(it.hasNext()){
			PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) it.next();
			if (pdSendInfoDetail.getSiNo().equals(pdSendInfo.getSiNo())) {
				JSONObject itemObject = new JSONObject();
				itemObject.put("goods_bn", pdSendInfoDetail.getProductNo()); //商品编码
				String productName = jpmProductSaleNewManager.getPmProductSaleNew(jpo.getCompanyCode(), pdSendInfoDetail.getProductNo()).getProductName();
				itemObject.put("name", productName); //商品名称
				itemObject.put("erp_goods_bn", pdSendInfoDetail.getErpProductNo()); //ERP商品编码
				itemObject.put("nums", pdSendInfoDetail.getQty()); //商品数量
				itemObject.put("price", pdSendInfoDetail.getPrice()); //商品单价
				itemObject.put("combination_product_no", pdSendInfoDetail.getCombinationProductNo()); //所属套餐
				pmJarray.add(itemObject);
				loObject.put("delivery_items", pmJarray);	
				
			}
		}
//		updateOrderSended(jpo);
//		moids.add(jpo.getMoId());
	}
	
	public static Date nextMonthFirstDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}
	
	public void updateOrderSended(JpoMemberOrder  jpo) {
		jpoMemberOrderManager.updateOrderSended(jpo);
		
	}
	
	public PdSendInfoManager getPdSendInfoManager() {
		return pdSendInfoManager;
	}
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}
	public MsgSendService getMsgSendService() {
		return msgSendService;
	}
	public void setMsgSendService(MsgSendService msgSendService) {
		this.msgSendService = msgSendService;
	}
	public JpoMemberOrderManager getJpoMemberOrderManager() {
		return jpoMemberOrderManager;
	}
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public JpmProductSaleNewManager getJpmProductSaleNewManager() {
		return jpmProductSaleNewManager;
	}
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}
	
	
	
	public JfiQuotaManager getJfiQuotaManager() {
		return jfiQuotaManager;
	}


	public void setJfiQuotaManager(JfiQuotaManager jfiQuotaManager) {
		this.jfiQuotaManager = jfiQuotaManager;
	}


	public FiBillAccountManager getFiBillAccountManager() {
		return fiBillAccountManager;
	}

	public void setFiBillAccountManager(FiBillAccountManager fiBillAccountManager) {
		this.fiBillAccountManager = fiBillAccountManager;
	}


	public static void main(String[] args) {
		/*JpozAutoShipQuartz j = (JpozAutoShipQuartz) ContextUtil.getSpringBeanByName(Constants.context, "jpozAutoShipQuartz");
		j.quartJfiQuota();
		System.out.println("------------");*/
		/*ApplicationContext  ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:*applicationContext-*.xml","classpath:action*.xml"});
		
		JpozAutoShipQuartz q = (JpozAutoShipQuartz) ctx.getBean("quartzJob1");
		q.quartJfiQuota();*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		System.out.println(sdf.format(nextMonthFirstDate()));
	}
}
