
package com.joymain.jecs.pd.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import service.MsgSendService;

import net.sf.json.JSONObject;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.dao.PdLogisticsBaseDao;
import com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao;
import com.joymain.jecs.pd.dao.PdLogisticsBaseNumDao;
import com.joymain.jecs.pd.dao.PdSendInfoDao;
import com.joymain.jecs.pd.dao.PdSendInfoDetailDao;
import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.pd.model.MailStatus;
import com.joymain.jecs.pd.model.MailStatusSend;
import com.joymain.jecs.pd.model.PdLogistics;
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;


import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pd.service.MailStatusManager;
import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import com.joymain.jecs.pd.service.PdMailReceiptManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.dao.JpoMemberOrderListDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.json.InterfaceJsonUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.ListUtil;
import com.joymain.jecs.util.SmsSend;


public class PdLogisticsBaseManagerImpl extends BaseManager implements PdLogisticsBaseManager {
	//modify gw 2015-04-21 日志
	protected final Log log = LogFactory.getLog(getClass());

    private PdLogisticsBaseDao dao;
    
	private JpoMemberOrderDao jpoMemberOrderDao;
	
	private JpoMemberOrderListDao jpoMemberOrderListDao;
	
	//物流状态接口物流信息明细的DAO
	private PdLogisticsBaseNumDao pdLogisticsBaseNumDao;
	
	//物流状态接口物流信息明细之商品明细的DAO
	private PdLogisticsBaseDetailDao pdLogisticsBaseDetailDao;
	
	//发货单的DAO
	private PdSendInfoDao pdSendInfoDao;
	private PdSendInfoDetailDao pdSendInfoDetailDao;
	
	//物流跟踪查询-mailStatusManager
	private MailStatusManager mailStatusManager;
	
	private PdMailReceiptManager pdMailReceiptManager;
	
	private String successString = "succ";//接口返回成功的标志；
	private String noSuccessString = "e000006";//接口返回失败的标志
	
	private PdWarehouseStockManager pdWarehouseStockManager;
	
	private JpmProductSaleNewManager jpmProductSaleNewManager ;

	
	 // 渠道接口地址
    private String url = "";
	
    public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
    
	public void setJpoMemberOrderListDao(JpoMemberOrderListDao jpoMemberOrderListDao) {
		this.jpoMemberOrderListDao = jpoMemberOrderListDao;
	}

	public void setPdLogisticsBaseNumDao(PdLogisticsBaseNumDao pdLogisticsBaseNumDao) {
		this.pdLogisticsBaseNumDao = pdLogisticsBaseNumDao;
	}

	public void setPdLogisticsBaseDetailDao(PdLogisticsBaseDetailDao pdLogisticsBaseDetailDao) {
		this.pdLogisticsBaseDetailDao = pdLogisticsBaseDetailDao;
	}

	public void setPdSendInfoDao(PdSendInfoDao pdSendInfoDao) {
		this.pdSendInfoDao = pdSendInfoDao;
	}
	
	public void setPdSendInfoDetailDao(PdSendInfoDetailDao pdSendInfoDetailDao) {
		this.pdSendInfoDetailDao = pdSendInfoDetailDao;
	}

	public void setMailStatusManager(MailStatusManager mailStatusManager) {
		this.mailStatusManager = mailStatusManager;
	}
	
	public void setPdMailReceiptManager(PdMailReceiptManager pdMailReceiptManager) {
		this.pdMailReceiptManager = pdMailReceiptManager;
	}
	
	public void setPdWarehouseStockManager(PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdLogisticsBaseDao(PdLogisticsBaseDao dao) {
        this.dao = dao;
    }
    
    

    public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}

	/**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseManager#getPdLogisticsBases(com.joymain.jecs.pd.model.PdLogisticsBase)
     */
    public List getPdLogisticsBases(final PdLogisticsBase pdLogisticsBase) {
        return dao.getPdLogisticsBases(pdLogisticsBase);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseManager#getPdLogisticsBase(String baseId)
     */
    public PdLogisticsBase getPdLogisticsBase(final String baseId) {
        return dao.getPdLogisticsBase(new BigDecimal(baseId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseManager#savePdLogisticsBase(PdLogisticsBase pdLogisticsBase)
     */
    public void savePdLogisticsBase(PdLogisticsBase pdLogisticsBase) {
        dao.savePdLogisticsBase(pdLogisticsBase);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdLogisticsBaseManager#removePdLogisticsBase(String baseId)
     */
    public void removePdLogisticsBase(final String baseId) {
        dao.removePdLogisticsBase(new BigDecimal(baseId));
    }
    //added for getPdLogisticsBasesByCrm
    public List getPdLogisticsBasesByCrm(CommonRecord crm, Pager pager){
	return dao.getPdLogisticsBasesByCrm(crm,pager);
    }

    /**
     * 处理OMS等接口传递过来的数据返回值的处理
     * @author gw 2014-12-05
     * @return string
     */
    public RspEntity getRspEntityString(RspEntity rspEntity){
    	
    	String sub_msg = rspEntity.getSub_msg();
    	if(!StringUtil.isEmpty(sub_msg) && Constants.INTERFACE_NORMAL.equals(sub_msg)){
    		rspEntity.setRsp(Constants.SUCCESS_STRING);//succ表明是接口数据保存成功
    	}else{
    		rspEntity.setCode(Constants.NO_SUCCESS_STRING);//e000006表明应用参数错误
    	}
		//将java对象转换成json对象
    	JSONObject jsonObject = JSONObject.fromObject(rspEntity);
		//将json对象转换成json字符串
		//return jsonObject.toString();
    	return rspEntity;
		
    }
    
	 /**
     * 保存或修改DO（WMS的do)的信息--保存或修改接口的信息数据
     * @author gw 2014-11-26
     * @param pdLogisticsBase 传递过来的接口数据转换成的pdLogisticsBase对象
     */
	public RspEntity savePdLogisticsBaseByInter(String jsonString) {
		log.info("do单开始：在PdLogisticsBaseManagerImpl类的方法savePdLogisticsBaseByInter中运行");
		RspEntity rspEntity = new RspEntity();
		if(StringUtil.isEmpty(jsonString)){
			   rspEntity.setSub_msg(" json is not null ");
			log.info("do单开始：json字符串为空");
			return this.getRspEntityString(rspEntity);
		}else{
			PdLogisticsBase pdLogisticsBaseT = null;
			try{
		        Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
		        classMap.put("mail_list", PdLogisticsBaseNum.class);
		        classMap.put("pdLogisticsBaseDetail_items", PdLogisticsBaseDetail.class);
		        
				//将json字符串转换成java对象
				pdLogisticsBaseT = InterfaceJsonUtil.returnnoJsonToModel(jsonString,PdLogisticsBase.class,classMap);
				if(null==pdLogisticsBaseT){
					   rspEntity.setSub_msg(" json is not null ");
						log.info("do单异常：json转换成对象PdLogisticsBase后，PdLogisticsBase对象为空");
						return this.getRspEntityString(rspEntity);
				}else{
					//pdLogisticsBaseT.setNum_order_type("MO");//因其他人误将代码更新到香港测试机，这一块暂时认为的赋值（modify gw 2015-05-18）,后期要注释掉这一行
					//对接口转换成的数据做处理（校验，给主键赋值）然后进行保存
					RspEntity rspEntityy = this.savePdLogisticsBaseByUpdate(pdLogisticsBaseT);
					
					//do保存成功后，给订单明细(jpo_member_order_list)的仓内作业(WAREHOUSE_OPERATION)，物流单号(TRACKING_SINGLE)，确认收货(CONFIRM_RECEIPT)三个字段赋值-begin
					if(Constants.SUCCESS_STRING.equals(rspEntityy.getRsp())){
						log.info("do单保存成功，开始do单后续的业务");

						List list = dao.getPdLogisticsBaseListByBin(pdLogisticsBaseT);
						if(null!=list){
							if(list.size()>0){
								PdLogisticsBase pdLogisticsBase = (PdLogisticsBase) list.get(0);
								//修改仓内作业的状态----这个地方有问题------------modify fu 20150831 仓内作业的功能已经不用了。
								dao.updatePdLogisticsBaseStatus(pdLogisticsBase);
								//修改物流单号信息
								this.updateMaillist(pdLogisticsBase);
								
								//给发货单赋值物流跟踪单号+发货单的部分发货
								this.uSetPdSendInfoLogisticsDo(pdLogisticsBase);
								
								//--------------------------------------------确认收货 WuCF
								//this.confirmReceipt(pdLogisticsBaseT);
								
								//之前的确认收货功能暂时屏蔽掉  2015-04-17 gw
								//this.confirmReceiptTotal(pdLogisticsBaseT);
								
								//modify gw 2015-04-21    订单的部分发货
								this.setInterOkDeliveryTwo(pdLogisticsBaseT);
								
								//modify gw  2015-04-14  确认收货  套餐拆开发货(同一张发货单下的同一种商品没有跨越DO单，那么这里是可以准确的确认收货的。如果同一张发货单下的商品（特别是套餐子商品）跨越了DO单，那么确认收货需要靠定时器功能完成；)
								this.setReceiptDo(pdLogisticsBase);
								
							}
						}
					}
					//do保存成功后，给订单明细(jpo_member_order_list)的仓内作业(WAREHOUSE_OPERATION)，物流单号(TRACKING_SINGLE)，确认收货(CONFIRM_RECEIPT)三个字段赋值-end
					return rspEntityy;
					
				}
			}
			//json字符串转对象时报异常
			catch(Exception e){
				//rspEntity.setSub_msg(" JSON is converted to a string of anomaly ");//json转换成字符串异常
				rspEntity.setSub_msg(" json转换成对象串异常  ");//json转换成字符串异常
				log.info("do单运行中有异常："+e.toString());
				return this.getRspEntityString(rspEntity);
			}
	   }	
	}

	/**
	 * 订单状态：部分发货
	 * @author gw 2015-04-21
	 * @param pdLogisticsBaseT
	 * @return 
	 */
    private void setInterOkDeliveryTwo(PdLogisticsBase pdLogisticsBaseT) {
    	String memberOrderNo = pdLogisticsBaseT.getMember_order_no();
    	String siNo = pdLogisticsBaseT.getSi_no();
    	try{
    		JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(memberOrderNo);
    		if(null!=jpoMemberOrder){
		    		String interOkDelivery = jpoMemberOrder.getInterOkDelivery();
		    		//只有未发货的订单才会将订单状态变成部分发货
		    		if(((!StringUtil.isEmpty(interOkDelivery))&&("0".equals(interOkDelivery)))|| StringUtil.isEmpty(interOkDelivery)){
		    			List<PdLogisticsBaseNum> mailList = pdLogisticsBaseT.getMail_list();
				    		if(null!=mailList){
				    			if(mailList.size()>0){
				    				for(int i=0;i<mailList.size();i++){
					    					PdLogisticsBaseNum pdLogisticsBaseNum = mailList.get(i);
					    					String status = pdLogisticsBaseNum.getStatus();
					    					//status状态含义 0020：已签收,0022：客户拒收,0099：在途中(发货状态)
					    					//do对应的物流单号是发货状态才可能将订单的状态改为发货状态
					    					if((!StringUtil.isEmpty(status))&&"0099".equals(status)){
					    						jpoMemberOrder.setInterOkDelivery("1");//设置订单部分发货
					    						jpoMemberOrderDao.saveJpoMemberOrder(jpoMemberOrder);
					    						return;
					    					}
				    				}
				    			}
				    		}
		    		}
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		log.info("接口相关业务之订单编号为:"+memberOrderNo+"的订单将状态修改为部分发货时发生错误");
    	}
	}

	/**
     * 确认收货(套餐拆开发货)
     * @author gw 2015-04-14
     * @param pdLogisticsBase
     */
	private void setReceiptDo(PdLogisticsBase pdLogisticsBase) {
		  try{
			    String statusBegin = pdLogisticsBase.getStatus();
			    String numOrderType = pdLogisticsBase.getNum_order_type();
			    //仓内作业没有确认收货
			    if(!StringUtil.isEmpty(statusBegin) && "0".equals(statusBegin)){
			    	return;
			    }
			    //非仓内作业有确认收货 
			    else{
			    	String siNo = pdLogisticsBase.getSi_no();//发货单号
			    	if(StringUtil.isEmpty(siNo)){
			    		return;
			    	}
			    	PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfoForSiNo(siNo);
			    	if(null==pdSendInfo){
			    		return;
			    	}
			    	Integer orderFlag = pdSendInfo.getOrderFlag();
			    	//modify fu 2015-11-25 优化收货确认的功能--------------------------------------------------begin
			    	//只有发货单的状态变成已发货，才会进行后续的收货确认的操作
			    	if((null!=orderFlag) && (orderFlag ==2)){
						List<PdLogisticsBaseNum> pdLogisticsBaseNumList = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByInter(pdLogisticsBase.getBaseId());
						if(null==pdLogisticsBaseNumList){
							return;
						}
						for(int i=0;i<pdLogisticsBaseNumList.size();i++){
							PdLogisticsBaseNum	pdLogisticsBaseNum =  pdLogisticsBaseNumList.get(i);
							String status = pdLogisticsBaseNum.getStatus();
							if(!StringUtil.isEmpty(status)){
								//0020：已签收
								if("0020".equals(status)){
									//已签收(收货确认)
								}else{
									//没有签收(没有收货确认)
									return;
								}
							}
						}
						pdSendInfo.setOrderFlag(3);//发货单收货确认
						pdSendInfoDao.savePdSendInfo(pdSendInfo);
				    	if((!StringUtil.isEmpty(numOrderType))&&("MO".equals(numOrderType)||"mo".equals(numOrderType))){
					    		String memberOrderNo = pdLogisticsBase.getMember_order_no();//订单号
						    	if(!StringUtil.isEmpty(memberOrderNo)){
								    	JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(memberOrderNo);
								    	if(null!=jpoMemberOrder){
								    		//判断订单是否已收货
								    		this.jpoMemberOrderIsOrNotReceipt(memberOrderNo,jpoMemberOrder);
								    	}
						    	}
				    	}
			    	}
			    	//modify fu 2015-11-25 优化收货确认的功能--------------------------------------------------end
			    	
			    	
			    	
			    	
			    	
			    	//modify fu 2015-11-25上面对确认收货的功能进行优化了，下面原有的确认收货的功能就暂时屏蔽掉-----begin
			    	/*List<PdSendInfoDetail> pdSendInfoDetailList = pdSendInfoDetailDao.getPdSendInfoInterFaceList(siNo);

					   //modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---begin
			    	  if((!StringUtil.isEmpty(numOrderType))&&("MO".equals(numOrderType)||"mo".equals(numOrderType))){
			  			//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---end

					    	String memberOrderNo = pdLogisticsBase.getMember_order_no();//订单号
					    	if((!StringUtil.isEmpty(siNo))&&(!StringUtil.isEmpty(memberOrderNo))){
						    	JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(memberOrderNo);
						    	if((null==pdSendInfo)||(null==jpoMemberOrder)){
						    		return;
						    	}else{
						    		//修改发货单明细的商品收货状态
						    		this.pdSendInfoDetailSetReceipt(pdLogisticsBase,pdSendInfo,pdSendInfoDetailList);
						    		
						    	}
						    	//判断发货单是否已收货
						    	boolean pdSendInfoIsOrNotReceipt = this.pdSendInfoIsOrNotReceipt(pdSendInfo,pdSendInfoDetailList);
						    	if(pdSendInfoIsOrNotReceipt){
						    		//判断订单是否已收货
						    		this.jpoMemberOrderIsOrNotReceipt(memberOrderNo,jpoMemberOrder);
						    	}
					    	}
			    	  }
			    	  //modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---begin
			    	  else if((!StringUtil.isEmpty(numOrderType))&&("EX".equals(numOrderType)||"ex".equals(numOrderType))){
			    		  //换货单对应的发货单的确认收货
			    		  this.pdSendInfoDetailSetReceipt(pdLogisticsBase,pdSendInfo,pdSendInfoDetailList);
			    		  this.pdSendInfoIsOrNotReceipt(pdSendInfo,pdSendInfoDetailList);
			    	  }
			    	 //modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---begin
                    */			    
			    	//modify fu 2015-11-25对确认收货的功能进行优化了，上面原有的确认收货的功能就暂时屏蔽掉-----end
			    	
			    }
		  }catch(Exception e){
			  log.info("do单保存成功，开始do单后续的业务:在setReceiptDo方法中运行异常："+e.toString());
			  e.printStackTrace();
		  }
		
	}

	/**
	 * 套餐拆开发货
	 * 修改发货单明细的商品收货状态
	 * @author gw 2015-04-15
	 * @param pdLogisticsBase
	 * @param pdSendInfo
	 * 
	 */
	private void pdSendInfoDetailSetReceipt(PdLogisticsBase pdLogisticsBase,PdSendInfo pdSendInfo,List<PdSendInfoDetail> pdSendInfoDetailList) {
		try{
			  log.info("do单保存成功，开始do单后续的业务:在pdSendInfoDetailSetReceipt方法中运行");

			    if(null==pdSendInfoDetailList){
			    	return;
			    }
				//因为配置的问题，所以这个地方不是根据级联关系获取pdLogisticsBaseNumList,而是去数据库查询
				//另外是保证获取同一个do单下的所有物流单号信息--同一个DO的信息可能是分几批传递到JOCS系统的
				List<PdLogisticsBaseNum> pdLogisticsBaseNumList = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByInter(pdLogisticsBase.getBaseId());
				if(!(null==pdLogisticsBaseNumList)){
					for(int i=0;i<pdLogisticsBaseNumList.size();i++){
						PdLogisticsBaseNum	pdLogisticsBaseNum =  pdLogisticsBaseNumList.get(i);
						if((!StringUtil.isEmpty(pdLogisticsBaseNum.getStatus()))&&"0020".equals(pdLogisticsBaseNum.getStatus())){//已经签收的物流单号
							//因为配置的问题，所以这个地方不是根据级联关系获取pdLogisticsBaseNumList,而是去数据库查询
				    		//另外是保证获取同一个物流单号下的所有商品信息--同一个DO的信息可能是分几批传递到JOCS系统的
							List<PdLogisticsBaseDetail> pdLogisticsBaseDetailList = pdLogisticsBaseDetailDao.getPdLogisticsBaseDetailByInterList(pdLogisticsBaseNum);
							//判断商品是否已收货
							if(null!=pdLogisticsBaseDetailList){
								if(pdLogisticsBaseDetailList.size()>0){
									 for(int j=0;j<pdLogisticsBaseDetailList.size();j++){
										 //这个地方需要加一个判断，该do单下同一个商品的物流单号全是已收货，那么该商品才是已收货
										 String productNoDetailDO = pdLogisticsBaseDetailList.get(j).getproduct_no();
										 String qty = pdLogisticsBaseDetailList.get(j).getQty();
										 //modify gw 2015-06-09 接口传递过来的数值包含小数点，因此这里做下数字处理
										 boolean isQty = qty.contains(".");
										 if(isQty){
											 int a = qty.indexOf(".");
											 qty = qty.substring(0, a);
										 }
										 
										 if(StringUtil.isEmpty(qty)){
											 continue;
										 }
										 
										 //遍历发货单的商品
										 for(int m=0;m<pdSendInfoDetailList.size();m++){
											 PdSendInfoDetail pdSendInfoDetail = pdSendInfoDetailList.get(m);
											 String productNoDetail = pdSendInfoDetail.getProductNo();
											 Long qtyDetail = pdSendInfoDetail.getQty();
											 Long receiptQty = pdSendInfoDetail.getReceiptQty();
											 //同一种商品可以在do内部的不同物流单号存在
											 if(productNoDetailDO.equals(productNoDetail)){
												 if(qtyDetail.equals(Long.parseLong(qty))){
													 pdSendInfoDetail.setReceiptQty(0l);
													 pdSendInfoDetail.setConfirmReceipt("2");//确认收货
												 }else{
													 if(null==receiptQty){
														 pdSendInfoDetail.setReceiptQty(Long.parseLong(qty));
														 pdSendInfoDetail.setConfirmReceipt("1");//部分收货
													 }else{
														 receiptQty = Long.parseLong(qty)+receiptQty;
														 if(qtyDetail.equals(receiptQty)){
															 pdSendInfoDetail.setReceiptQty(0l);
															 pdSendInfoDetail.setConfirmReceipt("2");//确认收货
														 }else{
															 pdSendInfoDetail.setReceiptQty(receiptQty);
															 pdSendInfoDetail.setConfirmReceipt("1");//部分收货
														 }
													 }
												 }
												 pdSendInfoDetailDao.savePdSendInfoDetail(pdSendInfoDetail);
												 break;
											 }
										 }
									 }
								}
							}
						}
					}
					//modify gw 2015-04-28
					//遍历发货单的商品---将商品的实际收货数量全部变为0，因为这个实际收货数量就是个临时变量，用完就要重新赋值
					this.sendAcceptQtyZero(pdSendInfoDetailList,pdSendInfo);
				}
		}catch(Exception e){
			log.info("do单保存成功，开始do单后续的业务:在pdSendInfoDetailSetReceipt方法中运行异常："+e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 套餐拆开发货-确认收货
	 * 遍历发货单的商品---将商品的实际收货数量全部变为0，因为这个实际收货数量就是个临时变量，用完就要重新赋值
	 * @author gw 2015-04-28
	 * @param pdSendInfoDetailList
	 * @param pdSendInfo
	 * @return
	 */
	private void sendAcceptQtyZero(List<PdSendInfoDetail> pdSendInfoDetailList,PdSendInfo pdSendInfo) {
	  try{	
		 for(int p=0;p<pdSendInfoDetailList.size();p++){
			 PdSendInfoDetail pdSendInfoDetail = pdSendInfoDetailList.get(p);
			 pdSendInfoDetail.setReceiptQty(0l);
			 pdSendInfoDetailDao.savePdSendInfoDetail(pdSendInfoDetail);
		 }
	  }catch(Exception e){
		  e.printStackTrace();
		  log.info("给发货单明细的商品实际收货数量设为0发生异常，发货单号为"+pdSendInfo.getSiNo());
	  }
	}

	/**
	 * 套餐拆开发货
	 * 判断发货单是否已收货-发货单明细下所有商品都是已收货，那么发货单就是已收货
	 * @author gw 2015-04-15
	 * @param pdSendInfo
	 * @param pdSendInfoDetailList
	 * @return boolean
	 */
	private boolean pdSendInfoIsOrNotReceipt(PdSendInfo pdSendInfo,List<PdSendInfoDetail> pdSendInfoDetailList) {
		log.info("do单保存成功，开始do单后续的业务:在pdSendInfoIsOrNotReceipt方法中运行");
		try{
				if(null!=pdSendInfoDetailList){
					if(pdSendInfoDetailList.size()>0){
						for(int i=0;i<pdSendInfoDetailList.size();i++){
							PdSendInfoDetail pdSendInfoDetail = pdSendInfoDetailList.get(i);
							String confirmReceipt = pdSendInfoDetail.getConfirmReceipt();
							//判断商品的收货状态:确认收货(0未收货，1部分收货，2全部收货）
							if(StringUtil.isEmpty(confirmReceipt)){
								return false;
							}else if(!StringUtil.isEmpty(confirmReceipt)){
								if("2".equals(confirmReceipt)){
								}else{
									return false;
								}
							}
						}
						Integer orderFlag = pdSendInfo.getOrderFlag();
						if(orderFlag<=2){
							pdSendInfo.setOrderFlag(3);//发货单收货确认
							pdSendInfoDao.savePdSendInfo(pdSendInfo);
						}
						return true;
					}
				}
		}catch(Exception e){
			log.info("do单保存成功，开始do单后续的业务:在pdSendInfoIsOrNotReceipt方法中运行异常："+e.toString());
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
	 * 套餐拆开发货
	 * 判断订单是否已收货-订单下所有发货单都是已收货，那么订单就是已收货
	 * @author gw 2015-04-15
	 * @param memberOrderNo
	 * @param jpoMemberOrder
	 * 
	 */
	private void jpoMemberOrderIsOrNotReceipt(String memberOrderNo, JpoMemberOrder jpoMemberOrder) {
		try{
			List<PdSendInfo> pdSendInfoList = pdSendInfoDao.getPdSendInfoList(memberOrderNo);
			if(null!=pdSendInfoList){
				if(pdSendInfoList.size()>0){
					for(int i=0;i<pdSendInfoList.size();i++){
						PdSendInfo pdSendInfo = pdSendInfoList.get(i);
						Integer orderFlag = pdSendInfo.getOrderFlag();
						if(orderFlag<=2){
							return;
						}
					}
					//订单已收货:接口确认收货（0或者空表示未发货，1代表部分发货，2代表已经发货,3代表已确认收货）
					jpoMemberOrder.setInterOkDelivery("3");
					jpoMemberOrderDao.saveJpoMemberOrder(jpoMemberOrder);
				}
			}
		}catch(Exception e){
			log.info("do单保存成功，开始do单后续的业务:在jpoMemberOrderIsOrNotReceipt方法中运行异常："+e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 确认收货业务逻辑方法
	 * @author gw 2015-02-01
	 * @param pdLogisticsBase 由接口json字符串转过来的字符串
	 * @return 
	 */
	public void confirmReceiptTotal(PdLogisticsBase pdLogisticsBase) {
	  try{
		    String statusBegin = pdLogisticsBase.getStatus();
		    if(!StringUtil.isEmpty(statusBegin) && "0".equals(statusBegin)){
		    	return;
		    }
			//修改确认收货状态
			List<PdLogisticsBaseNum> pdLogisticsBaseNumList = pdLogisticsBase.getMail_list();
			for(PdLogisticsBaseNum pbn : pdLogisticsBaseNumList){//物流状态接口物流信息表
				if("0020".equals(pbn.getStatus())){//已经签收的物流单号
						List<PdLogisticsBaseDetail> list = pbn.getPdLogisticsBaseDetail_items();
						for(PdLogisticsBaseDetail pbd : list){
								//查询该订单下的商品所在的发货单是否是发货状态
								PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfoForOrderNoAndProductNo(pdLogisticsBase.getMember_order_no(),pbd.getproduct_no());
								if(null!=pdSendInfo){
									    Integer  orderFlag = pdSendInfo.getOrderFlag();
										//发货单状态为已发货或之后的收货确认等状态
										if(orderFlag>=2){
												//查询该商品的物流跟踪单号
												JpoMemberOrderList jpoMemberOrderList = jpoMemberOrderListDao.getJpoMemberOrderListForOrderNoAndProductNo(pdLogisticsBase.getMember_order_no(),pbd.getproduct_no());
											    if(null!=jpoMemberOrderList){
												    	//获取订单的商品的物流跟踪单号-----begin
												    	String trackingSingle = jpoMemberOrderList.getTrackingSingle();
												    	List<String> trackingSingleList = new ArrayList<String>();
									          			if(!StringUtil.isEmpty(trackingSingle)){
									          				String[] b = trackingSingle.split("</br>");
									          		    	for(int i=0;i<b.length;i++){
									          		    		trackingSingleList.add(b[i]);
									          		    	}
									          			}
												    	//获取订单的商品的物流跟踪单号-----end
		
												    	//查询物流单号的物流状态----begin
												    	List<String> statusList = new ArrayList<String>();
									          			for(int j=0;j<trackingSingleList.size();j++){
									          				    PdLogisticsBaseNum pdLogisticsBaseNum = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByPdLogisticsBaseNumno(trackingSingleList.get(j));
									          			        if(null!=pdLogisticsBaseNum){
									          			        	 String status = pdLogisticsBaseNum.getStatus();
									          			        	 statusList.add(status);
									          			        }else{
									          			        	return;
									          			        }
									          			}
									          		    //查询物流单号的物流状态----end
									          			
									          		    //依据物流单号的状态，判断是部分收货，还是全部收货--begin
									          			if(statusList.size()>0){
										          			     for(int p=0;p<statusList.size();p++){
										          			    	  if(!("0020".equals(statusList.get(p)))){
										          			    		jpoMemberOrderList.setConfirmReceipt("1");
										          			    		jpoMemberOrderListDao.saveJpoMemberOrderList(jpoMemberOrderList);
										          			    		return ;
										          			    	  }
										          			     }
										          			   jpoMemberOrderList.setConfirmReceipt("2");
										          			   jpoMemberOrderListDao.saveJpoMemberOrderList(jpoMemberOrderList);
									          			}
								          		         //依据物流单号的状态，判断是部分收货，还是全部收货--end
									          			
												    	
											    }
										}
								}
						}
				}
			}
			
			//判断订单下所有的商品是不是已经全部收货,如果是的话，那么就让订单的状态变成已收货--begin
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(pdLogisticsBase.getMember_order_no());
			if(null!=jpoMemberOrder){
				Set set = jpoMemberOrder.getJpoMemberOrderList();
				Iterator it = set.iterator();
				while(it.hasNext()){
					JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) it.next();
					String confirmReceipt = jpoMemberOrderList.getConfirmReceipt();
				    if(!StringUtil.isEmpty(confirmReceipt)&& "2".equals(confirmReceipt)){
						
					}else{
						return;
					}
				}
				//订单已经收货确认
				jpoMemberOrder.setInterOkDelivery("3");
				//既然订单都已经收货了，那么发货单也自然就是已经收货了----begin
				List<PdSendInfo> listPdSendInfo = pdSendInfoDao.getPdSendInfoList(jpoMemberOrder.getMemberOrderNo());
				for(int m=0;m<listPdSendInfo.size();m++){
					PdSendInfo pdSendInfo = listPdSendInfo.get(m);
					Integer inte = pdSendInfo.getOrderFlag();
					if((null!=inte)&&(!StringUtil.isEmpty(inte.toString()))&&(2==inte)){
						pdSendInfo.setOrderFlag(3);
						pdSendInfoDao.savePdSendInfo(pdSendInfo);
					}
				}
				//既然订单都已经收货了，那么发货单也自然就是已经收货了----begin
				jpoMemberOrderDao.saveJpoMemberOrder(jpoMemberOrder);
			}
			//判断订单下所有的商品是不是已经全部收货,如果是的话，那么就让订单的状态变成已收货--end
			
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	}

	/**
	 * 修改物流单号信息
	 * @author gw 2015-01-25
	 * @param pdLogisticsBase
	 */
	public void updateMaillist(PdLogisticsBase pdLogisticsBase) {
		try{
			log.info("do单保存成功，开始do单后续的业务：在updateMaillist方法中运行（修改物流单号信息）");

			//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---begin
			//只有订单下的商品明细需要展示物流跟踪单号
			String numOrderType = pdLogisticsBase.getNum_order_type();
			if((!StringUtil.isEmpty(numOrderType))&&("MO".equals(numOrderType)||"mo".equals(numOrderType))){
			//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---end
					String status = pdLogisticsBase.getStatus();
					if((!StringUtil.isEmpty(status)) && ("1".equals(status))){
			             //获取发货单相关的订单的信息
			             JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(pdLogisticsBase.getMember_order_no());
		    			 Set sset = jpoMemberOrder.getJpoMemberOrderList();
		    			 //获取物流信息（物流单号+商品）的集合
		    			 List<PdLogisticsBaseNum> list = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByInter(pdLogisticsBase.getBaseId());
					     if(null!=list){
					    	 if(list.size()>0){
					    		 //遍历每一个物流信息（物流单号+商品）
					    		 for(int i=0;i<list.size();i++){
					    			 PdLogisticsBaseNum pdLogisticsBaseNum = list.get(i);
					    			 
					    			 //modify fu 2015-09-22  如果物流单号为空，那么就不用在订单明细里面赋值物流单号了---begin
					    			 String pdLogisticsBaseNum_no = pdLogisticsBaseNum.getPdLogisticsBaseNum_no();
					    			 if(StringUtil.isEmpty(pdLogisticsBaseNum_no)){
					    				 continue;//continue的功能是结束本次循环跳到下一次循环。
					    			 }
					    			 //modify fu 2015-09-22---------------------------------------------------------end
					    			 
					    			 List<PdLogisticsBaseDetail> listPdLDetail =  pdLogisticsBaseDetailDao.getPdLogisticsBaseDetailByInterList(pdLogisticsBaseNum);
					    			 //遍历物流单号下所有的商品信息
					    			 for(int j=0;j<listPdLDetail.size();j++){
					    				 PdLogisticsBaseDetail pdLogisticsBaseDetail = listPdLDetail.get(j);
					    				 String productNoInter = pdLogisticsBaseDetail.getproduct_no();
					    				 
					    				 //modify gw 2015-04-14 套餐拆开发货 所属套餐编码  ----begin
					    				 String combinationProductNo = pdLogisticsBaseDetail.getCombination_product_no();
					    				 //modify gw 2015-04-14 套餐拆开发货 所属套餐编码  ----end
					    				 
					    				 //modify fu 2015-11-21 因为订单下同种商品的可有多条数据,为避免有多余的物流单号.特意做价格和数量的比较--beign
					    				 BigDecimal price = pdLogisticsBaseDetail.getPrice();
					    				 String qty = pdLogisticsBaseDetail.getQty();
					    				 int qtyPd = 0;
				    				     //去掉数量的小数点
										 if(!StringUtil.isEmpty(qty)){
										    int a = qty.indexOf(".");
										    if(-1<a){
										    	qty = qty.substring(0, a);
										    }
										    qtyPd = Integer.parseInt(qty);
										 }
					    				 //modify fu 2015-11-21 因为订单下同种商品的可有多条数据,为避免有多余的物流单号.特意做价格和数量的比较--end

					    				 
					    				 //遍历相同订单下的商品信息
					    				 Iterator it = sset.iterator();
						    			 while(it.hasNext()){
						    				 JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) it.next();
						    				 String productNoJPO = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
						    				 
						    				 //modify fu 2015-11-21 因为订单下同种商品的可有多条数据,为避免有多余的物流单号.特意做价格和数量的比较
						    				 BigDecimal priceMO = jpoMemberOrderList.getPrice();
						    				 int qtyMO = jpoMemberOrderList.getQty();
						    				    //if((null!=price)&&(null!=priceMO)&&(price==priceMO))
								    				 //modify fu 2015-11-21 因为订单下同种商品的可有多条数据,为避免有多余的物流单号.特意做价格和数量的比较--end
		
								    				 //DO单下的商品编号与订单明细下的商品是同一个商品，那么就更新物流单号信息
								    				 if(((!StringUtil.isEmpty(productNoInter))&&(!StringUtil.isEmpty(productNoJPO)))&&
								    						 (productNoInter.equals(productNoJPO)&&(StringUtil.isEmpty(combinationProductNo)))){
								    					 
								    					    // modify fu 2015-11-24 
									    					//因为订单下同种商品在下列情况下会多条数据：会员购买套餐送商品A，会员自己还购买了商品A，这样的情况下，就会出现同种商品有多条记录，但是赠品的价格会为0
								    					    //
								    					   if((null!=price)&&(null!=priceMO)&&(price.compareTo(priceMO)==0)&&(0!=qtyPd)&&(0!=qtyMO)&&(qtyPd==qtyMO)){ 
												    					 if(StringUtil.isEmpty(jpoMemberOrderList.getTrackingSingle())){
												    						 jpoMemberOrderList.setTrackingSingle(pdLogisticsBaseNum.getPdLogisticsBaseNum_no());
												    					 }else{
												    	          		    	//物流单号重复性检查，如果物流单号已经存在，那么就不做操作，反之做修改操作---end
												    						    String[] a = jpoMemberOrderList.getTrackingSingle().split("</br>");
												    						    int b = 0;
												    	          		    	for(int p=0;p<a.length;p++){
												    	          		    		if(a[p].equals(pdLogisticsBaseNum.getPdLogisticsBaseNum_no())){
												    	          		    			b=1;
												    	          		    			break;
												    	          		    		}
												    	          		    	}
												    	          		    	if(b == 0){
													    	          		    	//物流单号重复性检查，如果物流单号已经存在，那么就不做操作，反之做修改操作---end
													    						    jpoMemberOrderList.setTrackingSingle(jpoMemberOrderList.getTrackingSingle()+"</br>"+pdLogisticsBaseNum.getPdLogisticsBaseNum_no());
												    	          		    	}
												    					 }
									    					 }
								    					 //跟新物流单号的信息
								    					 jpoMemberOrderListDao.saveJpoMemberOrderList(jpoMemberOrderList);
								    				 }else  if(((!StringUtil.isEmpty(productNoInter))&&(!StringUtil.isEmpty(productNoJPO)))&&
								    						 ((!StringUtil.isEmpty(combinationProductNo))&&(combinationProductNo.equals(productNoJPO)))){
									    					 if(StringUtil.isEmpty(jpoMemberOrderList.getTrackingSingle())){
									    						 jpoMemberOrderList.setTrackingSingle(pdLogisticsBaseNum.getPdLogisticsBaseNum_no());
									    					 }else{
									    	          		    	//物流单号重复性检查，如果物流单号已经存在，那么就不做操作，反之做修改操作---end
									    						    String[] a = jpoMemberOrderList.getTrackingSingle().split("</br>");
									    						    int b = 0;
									    	          		    	for(int p=0;p<a.length;p++){
									    	          		    		if(a[p].equals(pdLogisticsBaseNum.getPdLogisticsBaseNum_no())){
									    	          		    			b=1;
									    	          		    			break;
									    	          		    		}
									    	          		    	}
									    	          		    	if(b == 0){
										    	          		    	//物流单号重复性检查，如果物流单号已经存在，那么就不做操作，反之做修改操作---end
										    						    jpoMemberOrderList.setTrackingSingle(jpoMemberOrderList.getTrackingSingle()+"</br>"+pdLogisticsBaseNum.getPdLogisticsBaseNum_no());
									    	          		    	}
									    					 }
									    					 //跟新物流单号的信息
									    					 jpoMemberOrderListDao.saveJpoMemberOrderList(jpoMemberOrderList);
									    			}
						    			 }
					    			 }
					    		 }
					    	 }
					     }
					}
			}
		}catch(Exception e){
			log.info("do单保存成功，开始do单后续的业务：在updateMaillist方法中运行（修改物流单号信息）异常"+e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改发货单的物流单号信息+部分发货
	 * @author gw 2015-11-17
	 * @param pdLogisticsBase
	 */
	public void uSetPdSendInfoLogisticsDo(PdLogisticsBase pdLogisticsBase) {
		try{
			log.info("do单保存成功，开始do单后续的业务：在uSetPdSendInfoLogisticsDo方法中运行（修改发货单物流单号信息）");
			//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---begin
			String numOrderType = pdLogisticsBase.getNum_order_type();
			if((!StringUtil.isEmpty(numOrderType))&&("MO".equals(numOrderType)||"mo".equals(numOrderType))){
			//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---end
					String status = pdLogisticsBase.getStatus();
					if((!StringUtil.isEmpty(status)) && ("1".equals(status))){
							String siNo = pdLogisticsBase.getSi_no();
							PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfoForSiNo(siNo);
							String logisticsDo = pdSendInfo.getLogisticsDo();//由DO单号获取的物流单号  LOGISTICS_DO
							List<PdLogisticsBaseNum> list = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByInter(pdLogisticsBase.getBaseId());
							if(null!=list){
						    	 if(list.size()>0){
						    		 //遍历每一个物流信息（物流单号+商品）
						    		 for(int i=0;i<list.size();i++){
						    			 PdLogisticsBaseNum pdLogisticsBaseNum = list.get(i);
						    			 //modify fu 2015-09-22  如果物流单号为空，那么就不用在订单明细里面赋值物流单号了---begin
						    			 String pdLogisticsBaseNum_no = pdLogisticsBaseNum.getPdLogisticsBaseNum_no();
						    			 if(StringUtil.isEmpty(pdLogisticsBaseNum_no)){
						    				 continue;//continue的功能是结束本次循环跳到下一次循环。
						    			 }
						    			 if(StringUtil.isEmpty(logisticsDo)){
						    				 logisticsDo = pdLogisticsBaseNum_no;
						    			 }else{
						    				    //物流单号重复性检查，如果物流单号已经存在，那么就不做操作，反之做修改操作---end
				    						    //String[] a = logisticsDo.split(",");
				    						    String[] a = logisticsDo.split("</br>");
				    						    int b = 0;
				    	          		    	for(int p=0;p<a.length;p++){
				    	          		    		if(a[p].equals(pdLogisticsBaseNum_no)){
				    	          		    			b=1;
				    	          		    			break;
				    	          		    		}
				    	          		    	}
				    	          		    	if(b == 0){
					    	          		    	//物流单号重复性检查，如果物流单号已经存在，那么就不做操作，反之做修改操作---end
				    	          		    		//logisticsDo = logisticsDo+","+pdLogisticsBaseNum_no;
				    	          		    	    //</br>
				    	          		    		logisticsDo = logisticsDo+"</br>"+pdLogisticsBaseNum_no;
				    	          		    	}
						    			 }
						    			 
						    		 }
						    		 pdSendInfo.setLogisticsDo(logisticsDo);
						    		 Integer orderFlag = pdSendInfo.getOrderFlag();
						    		 //如果发货单的状态是已审核，那么接收到DO单的消息后，该发货单就是部分发货了
						    		 if((null!=orderFlag)&&1==orderFlag){
						    			 pdSendInfo.setPartSend("0");
						    		 }
						    		 pdSendInfoDao.savePdSendInfo(pdSendInfo);
						    	 }
							 }
					}
					//modify by fu  2016-1-19 DO单的拣货单过来，那么发货单的是否已备货状态变成已备货----begin
					else if((!StringUtil.isEmpty(status)) && ("0".equals(status))){
						 PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfoForSiNo(pdLogisticsBase.getSi_no());
						 pdSendInfo.setWhetherStock("Y");
			    		 pdSendInfoDao.savePdSendInfo(pdSendInfo);
					}
					//modify by fu  2016-1-19 DO单的拣货单过来，那么发货单的是否已备货状态变成已备货----end
			}
		}catch(Exception e){
			log.info("do单保存成功，开始do单后续的业务：在uSetPdSendInfoLogisticsDo方法中运行（修改发货单物流单号信息）异常"+e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 确认收货
	 * @author WuCF
	 * 2015-01-30
	 */
	public void confirmReceipt(PdLogisticsBase pdLogisticsBase) {
		try{
			dao.confirmReceipt(pdLogisticsBase);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
     * 对WMS的接口数据进行校验和保存
     * @author gw 2014-12-03
     * @param pdLogisticsBase 传递过来的接口数据转换成的pdLogisticsBase对象
     */
	public RspEntity savePdLogisticsBaseByUpdate(PdLogisticsBase pdLogisticsBase) {
		RspEntity rspEntity = new RspEntity();
		try{
				 //首先校验数据的合法性
				 String legitimacyResult = this.getLegitimacyResult(pdLogisticsBase);
				 //数据合法性校验不通过
                 if(!Constants.INTERFACE_NORMAL.equals(legitimacyResult)){
                	 rspEntity.setSub_msg(legitimacyResult);
					 log.info("do单数据合法性校验不通过："+legitimacyResult);
                	 return this.getRspEntityString(rspEntity); 
                 }
                 //数据合法性校验通过
                 else{
					 log.info("do单数据合法性校验通过："+legitimacyResult);
                	 //查询数据库中原有的pdLogisticsBase对象
					 List pdLogisticsBaseList = dao.getPdLogisticsBaseListByBin(pdLogisticsBase);
                	 //判断是否为仓内作业
                	 //仓内作业
                	 String status = pdLogisticsBase.getStatus();
                	 //pdLogisticsBase.getStatus()的status 0:仓内作业    1:已发货
                     if("0".equals(status)){
                          //仓内作业的保存方法
                    	  String binResult = dao.getBinResult(pdLogisticsBaseList,pdLogisticsBase);
                    	  if(!Constants.INTERFACE_NORMAL.equals(binResult)){
                         	  rspEntity.setSub_msg(binResult);
         					  log.info("do单数据舱内作业保存异常："+binResult);
                    		  return this.getRspEntityString(rspEntity);//保存异常
                    	  }else{
                    		  rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);//正常结果返回
							  return this.getRspEntityString(rspEntity);
                    	  }
                     }
                	 //非仓内作业
                     else{
                    	 //判断数据库中是否存在这条非仓内作业的数据--------开始
                    	 PdLogisticsBase  pdLogisticsBaseTwo = dao.getPdLogisticsBaseFirstNotBin(pdLogisticsBase);
                    	 //判断数据库中是否存在这条非仓内作业的数据--------开始
                    	 
                    	 //数据库中不存在该si_no和wms_do的数据---之前的仓内作业数据没保存进数据库
                         if(null==pdLogisticsBaseTwo){
                        	 pdLogisticsBase.setBaseId(null);
                        	 //新增
                        	 String firstNotBin = dao.saveFirstNotBinPdLogisticsBase(pdLogisticsBase);
                    	     if(null==firstNotBin){
                    	    	 rspEntity.setSub_msg(" save the anomaly ");
            					  log.info("do单数据保存异常：");
                    	    	 return this.getRspEntityString(rspEntity);//保存异常
                    	     }else{
                    	    	 rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);//正常结果返回
                    	    	  //------------------物流单号信息查询---modify gw 2015-01-09
                    	    	 //modifw gw 2015-06-17 因为有了定时器定时去其他系统获取物流实时信息，所以这里暂时注释掉
                    	    	 //this.getMailNoByPdLogisticsBase(pdLogisticsBase);
   							     return this.getRspEntityString(rspEntity);
                    	     }
                         }
                         //数据库中存在该si_no和wms_do的数据----之前的仓内作业数据保存进数据库了
                         //do的第二层数据库中存在----后面的非仓内作业数据保存（之前的仓内作业数据保存进数据库了）
                         else{
                        	    
                        	    	   Long baseId = pdLogisticsBaseTwo.getBaseId();
                        	    	   List<PdLogisticsBaseNum>  list = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByInter(baseId);
		                        	    if(null!=list && list.size()>0){
				                       	     //后面几次仓内作业的保存--开始
				                       		 //更新
				                        	 pdLogisticsBase.setBaseId(pdLogisticsBaseTwo.getBaseId());
				                    		 //非第一次仓内作业的数据保存之前，做一下特殊处理
				                        	 String threeString = this.getSecondNotBinPdLogisticsBase(pdLogisticsBase);
				                        	 rspEntity.setSub_msg(threeString);
				                        	 //------------------物流单号信息查询---modify gw 2015-01-09
				                        	 if(Constants.INTERFACE_NORMAL.equals(threeString)){
				                        		 //modifw gw 2015-06-17 因为有了定时器定时去其他系统获取物流实时信息，所以这里暂时注释掉
				                        		 //this.getMailNoByPdLogisticsBase(pdLogisticsBase);
				                        	 }
				                   	    	 return this.getRspEntityString(rspEntity);
				                       	     //后面几次仓内作业的保存--开始
		                        	    }
		                        	    //第一次非仓内作业的保存---开始
		                        	    else{
		                        	    	
		                        	    	 pdLogisticsBaseTwo.setMember_order_no(pdLogisticsBase.getMember_order_no());
		                        	    	 pdLogisticsBaseTwo.setSi_no(pdLogisticsBase.getSi_no());
		                        	    	 pdLogisticsBaseTwo.setStatus(pdLogisticsBase.getStatus());
		                        	    	 pdLogisticsBaseTwo.setWms_do(pdLogisticsBase.getWms_do());
		                        	    	 pdLogisticsBaseTwo.setStatus_name(pdLogisticsBase.getStatus_name());
		                        	    	 pdLogisticsBaseTwo.setStatus_code(pdLogisticsBase.getStatus_code());
		                        	    	 pdLogisticsBaseTwo.setStatus_time(pdLogisticsBase.getStatus_time());
		                        	    	 pdLogisticsBaseTwo.setOperator(pdLogisticsBase.getOperator());
		                        	    	 //2015-04-28 订单号类型(MO表示是订单，EX表示是关联到换货单)  
		                        	    	 pdLogisticsBaseTwo.setNum_order_type(pdLogisticsBase.getNum_order_type());
		                        			 //-----------------------modify fu 订单全额支付的字段，这个是第二期要上线的功能，因此现在将这个校验屏蔽掉，第二期的时候再打开
		                        	    	 //pdLogisticsBaseTwo.setIsfull_pay(pdLogisticsBase.getIsfull_pay());//modify gw 2015-07-15 订单全额支付
		                        	    	 
		                        	    	 pdLogisticsBaseTwo.setMail_list(pdLogisticsBase.getMail_list());
		                        	    	 String firstNotBin = dao.saveFirstNotBinPdLogisticsBase(pdLogisticsBaseTwo);
			                        	    	 /*pdLogisticsBase.setBaseId(pdLogisticsBaseTwo.getBaseId());
				                        		  //更新
				                               	 String firstNotBin = dao.saveFirstNotBinPdLogisticsBase(pdLogisticsBase);*/
				                           	     if(null==firstNotBin){
				                           	    	 rspEntity.setSub_msg(" save the anomaly ");
				                           	    	 return this.getRspEntityString(rspEntity);//保存异常
				                           	     }else{
				                           	         //------------------物流单号信息查询---modify gw 2015-01-09
				                           	         //modifw gw 2015-06-17 因为有了定时器定时去其他系统获取物流实时信息，所以这里暂时注释掉
				                           	    	 //this.getMailNoByPdLogisticsBase(pdLogisticsBase);
				                           	    	 rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);//正常结果返回
				      							     return this.getRspEntityString(rspEntity);
				                           	     }
		                        	     }
                   	    	             //第一次非仓内作业的保存---结束
                    	 }
                     }
                 }
		}
		//表明数据保存过程中出现异常--//接口传递过来的格式有误
		catch(Exception e){
			log.info("do单数据保存异常："+e.toString());
			rspEntity.setSub_msg(e.toString());//
			return this.getRspEntityString(rspEntity);
		}
	}

	/**
	 * 校验接口DO数据的合法性
	 * @author gw 2014-12-02
	 * @param pdLogisticsBase
	 * @return
	 */
	private String getLegitimacyResult(PdLogisticsBase pdLogisticsBase) {
		 log.info("do单数据合法性校验第一层开始：在getLegitimacyResult方法中运行");

		//对DO层的第一层数据的合法性进行校验------------------------------------开始
		//首先校验pdLogisticsBase第一层不为空,这个不为空的校验包括：订单号，发货单号，wms_do单号,状态，状态编号，状态名称
		if(StringUtil.isEmpty(pdLogisticsBase.getMember_order_no()) || StringUtil.isEmpty(pdLogisticsBase.getSi_no())
				|| StringUtil.isEmpty(pdLogisticsBase.getWms_do()) || StringUtil.isEmpty(pdLogisticsBase.getStatus()) 
				|| StringUtil.isEmpty(pdLogisticsBase.getStatus_code()) || StringUtil.isEmpty(pdLogisticsBase.getStatus_name())
				|| StringUtil.isEmpty(pdLogisticsBase.getNum_order_type())||StringUtil.isEmpty(pdLogisticsBase.getOperator())){//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)
						 if(StringUtil.isEmpty(pdLogisticsBase.getMember_order_no())){
							 return " member_order_no(订单号) is not null ";
						 }
						 else if(StringUtil.isEmpty(pdLogisticsBase.getSi_no())){
							 return " si_no(发货单号) is not null ";
						 }
						 else if(StringUtil.isEmpty(pdLogisticsBase.getWms_do())){
							 return " wms_do(WMS单号) is not null ";
						 }
						 else if(StringUtil.isEmpty(pdLogisticsBase.getStatus())){
							 return " status(状态类型) is not null ";
						 }
						 else if(StringUtil.isEmpty(pdLogisticsBase.getStatus_code())){
							 return " status_code(状态编号) is not null ";
						 }
						 else if(StringUtil.isEmpty(pdLogisticsBase.getStatus_name())){
							 return " status_name(状态名称) is not  null ";
						 }
						 //modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)
						 else if(StringUtil.isEmpty(pdLogisticsBase.getNum_order_type())){
							 return "num_order_type(订单号类型) is not null ";
						 }
						 else if(StringUtil.isEmpty(pdLogisticsBase.getOperator())){
							 return "operator(操作人) is not null ";
						 }
						 
						 else{
							 return " json is null ";
						 }
		}else{
			if(null==pdLogisticsBase.getStatus_time()){
				 return "status_time(状态时间) is not null ";
			}
			
			//status_code和status_name之前对应的有仓内作业的信息，这两个字段有多种对应关系，现在仓内作业的信息取消了，现在这两个字段只有一种对应数据，因此就没有必要再做校验了。
			
			//判断订单号是不是JOCS传过去的原数据，就是订单号是否是正规的
			String  MEMBER_ORDER_NO = pdLogisticsBase.getMember_order_no();
			JpoMemberOrderDao ppp = jpoMemberOrderDao;
			String numOrderType = pdLogisticsBase.getNum_order_type();
			String status = pdLogisticsBase.getStatus();
			if((!"0".equals(status))&&(!"1".equals(status))){
				return "status is must be 1(非仓内作业) ";//status is must be 0(仓内作业) or 1(非仓内作业),其中仓内作业业务作废
			}
			
			//modify  by fu 2015-09-24 DO单只穿MO类型的，换货单类型的DO，EC系统不需要这个基础数据 
			/*if((!("MO".equals(numOrderType)))&&(!("mo".equals(numOrderType)))
					&&(!("EX".equals(numOrderType)))&&(!("ex".equals(numOrderType)))){
				return "num_order_type(订单号类型) is must be MO or EX ";
			}*/
			
			if((!("MO".equals(numOrderType)))&&(!("mo".equals(numOrderType)))){
				return "num_order_type(订单号类型) is must be MO ";
			}
			
			//-----------------------modify fu 订单全额支付的字段，这个是第二期要上线的功能，因此现在将这个校验屏蔽掉，第二期的时候再打开--begin
			/*Integer isFullPay = pdLogisticsBase.getIsfull_pay();
			if(null!=isFullPay){
				if(0==isFullPay){
				}else if(1==isFullPay){
				}else{
					return "isfull_pay(订单全额支付) 的值有误 ";
				}
			}*/
			//------------------------modify fu --------------------end 
			//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)-------begin
			if("EX".equals(numOrderType) || "ex".equals(numOrderType)){
				List<PdSendInfo> pdSendInfoList = pdSendInfoDao.getPdSendInfoList(MEMBER_ORDER_NO);//换货单对应的发货单
				if(null!=pdSendInfoList){
					if(pdSendInfoList.size()>0){
					}else{
						return MEMBER_ORDER_NO+"(换货订单号)  is invalid ";
					}
				}else{
					return MEMBER_ORDER_NO+"(换货订单号)  is invalid ";
				}
			}else{
			//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)-------end
				JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(MEMBER_ORDER_NO);
				if(null==jpoMemberOrder){
					return MEMBER_ORDER_NO+"(订单号)  is invalid ";//订单号不是原始订单号，即订单号在JOCS系统中不存在
				}else{
					if(!StringUtil.isEmpty(jpoMemberOrder.getMemberOrderNo())){
						//表明订单号是正规的订单号
					}else{
						return MEMBER_ORDER_NO+"(订单号) is invalid ";//订单号不是原始订单号，即订单号在JOCS系统中不存在
					}
				}
			}	
			//判断发货单号是不是JOCS传过去的原数据，就是发货单号是否是正规的--------------------------------
			PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfoForSiNo(pdLogisticsBase.getSi_no());
			if(null!=pdSendInfo){
				if(!StringUtil.isEmpty(pdSendInfo.getSiNo())){
					//表明发货单号是正规的发货单号
				}else{
					return pdLogisticsBase.getSi_no()+"(发货单号) is invalid ";//发货单号不是原始发货单号，即发货单号在JOCS系统中不存在
				}
			}else{
				return pdLogisticsBase.getSi_no()+"(发货单号) is invalid ";//发货单号不是原始发货单号，即发货单号在JOCS系统中不存在
			}
		//对DO层的第一层数据的合法性进行校验-----------------------------------结束
			
			//对仓内作业的数据的合法性校验--------------开始
			//pdLogisticsBase.getStatus()的status 0:仓内作业    1:已发货
			if("0".equals(pdLogisticsBase.getStatus())){
				/*List<PdLogisticsBaseNum> list = pdLogisticsBase.getMail_list();
				if(null!=list){
					if(list.size()>0){
						return null;//表明数据是不合法的
					}else{
						return Constants.INTERFACE_NORMAL;
					}
				}else{
					return Constants.INTERFACE_NORMAL;
				}*/
				
				//仓内作业的话，那么数据校验结束，并且数据都是合法的
				log.info("do单数据合法性校验第一层结束：在getLegitimacyResult方法中运行");
				return Constants.INTERFACE_NORMAL;
			}
			//对仓内作业的数据的合法性校验--------------结束
			
			//非仓内作业，那么就要对DO的第二层和第三层数据进行合法性校验了-----------开始
			else{
				String theSecondResult = this.getCheckSecondResult(pdLogisticsBase);
				log.info("do单数据合法性校验第一层结束：在getLegitimacyResult方法中运行");
				if(Constants.INTERFACE_NORMAL.equals(theSecondResult)){
					return Constants.INTERFACE_NORMAL;//正常返回结果
				}else{
					return theSecondResult;//表明数据是不合法的
				}
			}
			//非仓内作业，那么就要对DO的第二层和第三层数据进行合法性校验了-----------结束
		}
	}

	/**
	 * 校验DO接口的第二层数据合法性
	 * @author gw 2014-12-02
	 * @param pdLogisticsBase 
	 * @return string
	 */
	private String getCheckSecondResult(PdLogisticsBase pdLogisticsBase) {
		log.info("do单数据合法性校验第二层开始：在getCheckSecondResult方法中运行");
		List<PdLogisticsBaseNum> mail_list =  pdLogisticsBase.getMail_list();
		if(null==mail_list){
			return " mail_list(物流信息) is not null ";//表明数据是不合法的
		}else{
			if(mail_list.size()>0){
				for(int i=0;i<mail_list.size();i++){
					PdLogisticsBaseNum pdLogisticsBaseNum = mail_list.get(i);
					//modify fu 2015-09-22 fu 特殊的虚拟商品是不会发货的，这个时候返回的DO单就没有物流公司，物流单号，物流状态的。因此DO单的第二层校验注释掉  begin
					/*if(StringUtil.isEmpty(pdLogisticsBaseNum.getPdLogisticsBaseNum_no()) 
							|| StringUtil.isEmpty(pdLogisticsBaseNum.getName()) 
							|| StringUtil.isEmpty(pdLogisticsBaseNum.getStatus()) ){
							  if(StringUtil.isEmpty(pdLogisticsBaseNum.getPdLogisticsBaseNum_no())){
								  return " pdLogisticsBaseNum_no(物流单号) is not null ";
							  }
							  else if(StringUtil.isEmpty(pdLogisticsBaseNum.getName())){
								  return " name(物流公司) is null ";
							  }
							  else if(StringUtil.isEmpty(pdLogisticsBaseNum.getStatus())){
								  return " status(物流状态) is null ";
							  }else{
								  return " mail_list is null ";
							  }
					}else{
						//status字段值的校验,0020：已签收 0022：客户拒收 0099：在途中
						if((!"0020".equals(pdLogisticsBaseNum.getStatus()))
								&&(!"0022".equals(pdLogisticsBaseNum.getStatus()))
								&&(!"0099".equals(pdLogisticsBaseNum.getStatus()))){
							  return " status(物流状态) must be 0020 or 0022 or 0099 ";
						}
						
						//去校验DO接口的第三层数据的合法性
						String thirdResult = this.getCheckThirdResult(mail_list.get(i).getPdLogisticsBaseDetail_items(),pdLogisticsBase);
						if(Constants.INTERFACE_NORMAL.equals(thirdResult)){
						   //正常返回结果
						}else{
							return thirdResult;//表明数据是不合法的	
						}
					}*/
					//modify fu 2015-09-22 fu -------------------------------------------- end

					//modify by fu 2015-09-22 
					//status字段值的校验,0020：已签收 0022：客户拒收 0099：在途中
					if(!StringUtil.isEmpty(pdLogisticsBaseNum.getStatus())){
						if((!"0020".equals(pdLogisticsBaseNum.getStatus()))
								&&(!"0022".equals(pdLogisticsBaseNum.getStatus()))
								&&(!"0099".equals(pdLogisticsBaseNum.getStatus()))){
							  return " status(物流状态) must be 0020 or 0022 or 0099 ";
						}
					}
					//去校验DO接口的第三层数据的合法性
					String thirdResult = this.getCheckThirdResult(mail_list.get(i).getPdLogisticsBaseDetail_items(),pdLogisticsBase);
					if(Constants.INTERFACE_NORMAL.equals(thirdResult)){
					   //正常返回结果
					}else{
						return thirdResult;//表明数据是不合法的	
					}
				}
			}else{
				log.info("do单数据合法性校验第二层结束：在getCheckSecondResult方法中运行");
				return " mail_list(物流信息) is invalid ";//表明数据是不合法的
			}
			log.info("do单数据合法性校验第二层结束：在getCheckSecondResult方法中运行");
			return Constants.INTERFACE_NORMAL;//正常返回结果
		}
	}

	/**
	 * 校验DO接口的第三层数据合法性
	 * @author gw 2014-12-02
	 * @param pdLogisticsBaseDetailItems
	 * @param pdLogisticsBase 
	 * @return string
	 */
	private String getCheckThirdResult(List<PdLogisticsBaseDetail> pdLogisticsBaseDetailItems,PdLogisticsBase pdLogisticsBase) {
		log.info("do单数据合法性校验第三层开始：在getCheckThirdResult方法中运行");
		if(null==pdLogisticsBaseDetailItems){
			return " pdLogisticsBaseDetail_items(商品明细)  is not null";
		}else{
			if(pdLogisticsBaseDetailItems.size()>0){
		    	List<PdSendInfoDetail> pdSendInfoDetailList = pdSendInfoDetailDao.getPdSendInfoInterFaceList(pdLogisticsBase.getSi_no());
				for(int i=0;i<pdLogisticsBaseDetailItems.size();i++){
				    PdLogisticsBaseDetail pdLogisticsBaseDetail = pdLogisticsBaseDetailItems.get(i);
				    String productNo = pdLogisticsBaseDetail.getproduct_no();
				    String qty = pdLogisticsBaseDetail.getQty();
				    String erpProductNo = pdLogisticsBaseDetail.getErp_goods_bn();
				    //modify gw 2015-04-27------------begin
				    //价格和所属套餐编码
				    BigDecimal price = pdLogisticsBaseDetail.getPrice();//因其他人误将代码更新到香港测试机modify gw 2015-05-18，后期不能注释掉这一行
				    String combinationProductNo = pdLogisticsBaseDetail.getCombination_product_no();
				    //modify gw 2015-04-27------------end
				    if(StringUtil.isEmpty(qty)){
				    	return " qty(商品数量) is not null ";
				    }
				    //modify gw 2015-04-27------------begin
				    //因其他人误将代码更新到香港测试机modify gw 2015-05-18,后期不能注释掉这一行
				    //价格和所属套餐编码
				    if(null==price){
				    	return " price(价格) is not null ";
				    }else{
				    	
				    }
				    //modify gw 2015-04-27------------end
				    if(StringUtil.isEmpty(erpProductNo)){
				    	return " erp_goods_bn(erp商品编号) is not null ";
				    }
				    if(StringUtil.isEmpty(productNo)){
				    	return " product_no(商品编号) is not null ";
				    }else{
				    	//------------------------------------------------套餐拆开发货功能实现之前的代码(modify gw 2015-04-22 注释)---begin
				    	/*JpoMemberOrderDao pp = jpoMemberOrderDao;
				    	//首先校验数据的合理性，看看原订单下面是不是有这个商品的信息
					    JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(pdLogisticsBase.getMember_order_no());
					    if(null == jpoMemberOrder){
							return pdLogisticsBase.getMember_order_no()+"(订单号) is invalid ";//订单号不是原始订单号，即接口中的订单号在JOCS系统中不存在
					    }else{
						    	Set jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
						    	Iterator iterator = jpoMemberOrderList.iterator();
						    	//a变量用来判断迭代循环的订单明细表中是否存在接口中productNo商品
						    	int a = 0;
								while (iterator.hasNext()) {
									JpoMemberOrderList poMemberOrderList = (JpoMemberOrderList) iterator.next();
									String productNoTwo = poMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
									if(productNoTwo.equals(productNo)){
										a=0;
										break;
										//表明接口中的商品编码在JOCS系统中式存在的
									}else{
										a=1;
									}
								}
								//a变量用来判断迭代循环的订单明细表中是否存在接口中productNo商品
								//表明接口中的商品在原订单中是不存在的
								if(1==a){
									return productNo+"(商品编号) is invalid ";//商品编码不是原始商品编码，即接口中的商品编码在JOCS系统中不存在
								}
				       }*/
					  //------------------------------------------------套餐拆开发货功能实现之前的代码---end
						
				    	//------------modify fu 2015-09-11 发货单明细下面是否有这个商品----这个校验没必要
						//modify gw 2015-04-13------------------------------------------------begin
					    //校验数据的合法性，看发货单明细下面是否有这个商品
				    	
					   /* if(null ==pdSendInfoDetailList){
					    	return pdLogisticsBase.getSi_no()+"(发货单号) is invalid ";//发货单号不是原始发货单号，即接口中的发货单号在JOCS系统中不存在
					    }else if(null!=pdSendInfoDetailList){
					    	if(pdSendInfoDetailList.size()>0){
							    	int b = 0;
							    	for(int m=0;m<pdSendInfoDetailList.size();m++){ 
								    		PdSendInfoDetail pdSendInfoDetail = pdSendInfoDetailList.get(m); 
								    		String productNopd = pdSendInfoDetail.getProductNo();
								    		//因其他人误将代码更新到香港测试机modify gw 2015-05-18,后期不能注释掉这一行
								    		BigDecimal pricePd = pdSendInfoDetail.getPrice();
								    		//校验套餐所属编码字段是否正确
								    		String combinationProductNopd = pdSendInfoDetail.getCombinationProductNo();
								    		if(productNopd.equals(productNo)){
								    			//因其他人误将代码更新到香港测试机modify gw 2015-05-18,后期不能注释掉这一行
									    			if(null!=pricePd){
									    				BigDecimal priceXs = price.setScale(2, BigDecimal.ROUND_HALF_UP);
										    			BigDecimal pricePdXs = pricePd.setScale(2, BigDecimal.ROUND_HALF_UP);
									    				if(priceXs.equals(pricePdXs)){
									    				}else{
									    					return "商品编码"+productNo+"的价格是"+pricePd+"而不是"+price;
									    				}
									    			}
									    			//校验套餐所属编码字段是否正确
									    			if((!StringUtil.isEmpty(combinationProductNo)) && (!StringUtil.isEmpty(combinationProductNopd))){
										    			if(combinationProductNo.equals(combinationProductNopd)){
										    			}else{
										    				return "(combination_product_no套餐所属编码:"+combinationProductNo+") is invalid ";
										    			}
										    		}
								    			b=0;
												break;
												//表明接口中的商品编码在JOCS系统中式存在的
								    		}else{
								    			b=1;
								    		}
							    	}
							    	//表明接口中的商品在原订单中是不存在的
									if(1==b){
										return productNo+"(商品编号) is invalid ";//商品编码不是原始商品编码，即接口中的商品编码在JOCS系统中不存在
									}
					       }
					    //modify gw 2015-04-13------------------------------------------------end
					    //校验数据的合法性，看发货单明细下面是否有这个商品
					   }*/
				   }
			  }
				log.info("do单数据合法性校验第三层结束：在getCheckThirdResult方法中运行");
				return Constants.INTERFACE_NORMAL;//正常返回结果
			}else{
				log.info("do单数据合法性校验第三层结束：在getCheckThirdResult方法中运行");
				return " pdLogisticsBaseDetail_items(商品明细)  is  invalid ";
			}
		}
	}
	
	/**
	 * 后面几次非仓内作业的接口数据的处理：比如加上主键的值等,然后进行保存
	 * @author gw 2014-12-03
	 * @param pdLogisticsBase
	 * @return
	 */
	private String getSecondNotBinPdLogisticsBase(PdLogisticsBase pdLogisticsBase) {
		log.info("do单----在方法getSecondNotBinPdLogisticsBase中运行：");
		List list = dao.getPdLogisticsBaseListByBin(pdLogisticsBase);
		//校验do单是否已经存在过----下面表示已经存在，也数是正常数据
		if(list.size()>0){
			PdLogisticsBase pdLogisticsBaseTwo = (PdLogisticsBase) list.get(0);
			pdLogisticsBase.setBaseId(pdLogisticsBaseTwo.getBaseId());
			//为了使用hibernate进行保存操作，特将数据进行一下特殊处理(数据合理性校验及相关的赋值）------------------------------begin
			List<PdLogisticsBaseNum> mail_list = pdLogisticsBase.getMail_list();
			
			//modify fu 2015-11-26  修改物流单号重复的问题---begin
			String wmsDo = pdLogisticsBase.getWms_do();
			//modify fu 2015-11-26  修改物流单号重复的问题---end
			
			for(int j=0;j<mail_list.size();j++){
				        
						//modify fu 2015-11-26  修改物流单号重复的问题---begin
						String pdLogisticsBaseNum_no = mail_list.get(j).getPdLogisticsBaseNum_no();
						String otherOne = wmsDo+"wmsDo"+pdLogisticsBaseNum_no;//do单号+wmsDo+物流单号
						//modify fu 2015-11-26  修改物流单号重复的问题---end
						
				
						//重新赋值--------------
					    //pdLogisticsBase.getMail_list().get(j).setBaseId(pdLogisticsBase.getBaseId().toString());
						//根据物流单号和baseId获取PdLogisticsBaseNum对象的主键
						PdLogisticsBaseNum pdLogisticsBaseNum = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByBaseIdAndNo(pdLogisticsBase.getBaseId().toString(), mail_list.get(j).getPdLogisticsBaseNum_no(),otherOne);
						if(null==pdLogisticsBaseNum){
							//----modify gw 2014-12-24
							 pdLogisticsBase.getMail_list().get(j).setPdLogisticsBase(pdLogisticsBase);
							 pdLogisticsBase.getMail_list().get(j).setMailTime(new Date());
							 
							//modify fu 2015-11-26  修改物流单号重复的问题---begin
							pdLogisticsBase.getMail_list().get(j).setOtherOne(otherOne);
							//modify fu 2015-11-26  修改物流单号重复的问题---end
							 
							//表明do第二层的数据之前在JOCS系统中没有保存
						}else{
							    //校验DO第二层数据（PdLogisticsBaseNum）的合法性-------------------------end		
							    pdLogisticsBase.getMail_list().get(j).setNumId(pdLogisticsBaseNum.getNumId());
							    
							    //----modify gw 2014-12-24
							    pdLogisticsBase.getMail_list().get(j).setPdLogisticsBase(pdLogisticsBase);
							    pdLogisticsBase.getMail_list().get(j).setMailTime(pdLogisticsBaseNum.getMailTime());
							    
							    //modify fu 2015-11-26  修改物流单号重复的问题---begin
							    pdLogisticsBase.getMail_list().get(j).setOtherOne(otherOne);
							    //modify fu 2015-11-26  修改物流单号重复的问题---end
							    
							    
							    List<PdLogisticsBaseDetail> pdLogisticsBaseDetail_items = mail_list.get(j).getPdLogisticsBaseDetail_items();
							    //校验DO第三层数据（PdLogisticsBaseDetail）的合法性-------------------------begin
								List<PdLogisticsBaseDetail> listThree = this.checkPdLogisticsBaseDetailList(pdLogisticsBaseDetail_items, pdLogisticsBase.getMember_order_no(), pdLogisticsBaseNum);
								if(null==listThree){
				                	 return " pdLogisticsBaseDetail_items is null "; 
							    }else{
							    	 pdLogisticsBase.getMail_list().get(j).setPdLogisticsBaseDetail_items(listThree);
							    }
						}
				
			}
			log.info("do单----在方法checkPdLogisticsBaseDetailList中运行-----在保存前");
			PdLogisticsBase pdLogisticsBaseTotal = this.getPdLogisticsBaseTotal(pdLogisticsBase,pdLogisticsBaseTwo);
		    return dao.savePdLogisticsBaseTheSecondNotBin(pdLogisticsBaseTotal);
		}
		//表明传过来的数据中有不正确的数据----比如：这条数据在原来的数据库中没有被记录下来
		else{
			log.info("do单----在方法getSecondNotBinPdLogisticsBase中运行异常：he original data anomaly");
			//表明之前仓内作业的数据没有在数据库中正确的保存下来
			return " the original data anomaly ";//翻译成中文就是：原来数据异常
		}
	}
	
	
	
	 /**
	  * 为解决保存PdLogisticsBase对象时和缓存中的对象冲突（主键唯一性冲突），做下数据处理
	  * @author gw 2014-12-24
	  * @param pdLogisticsBaseJson  json字符串转过来的对象
	  * @param pdLogisticsBaseDBA 原数据库中的对象
	  * @return pdLogisticsBase  
	  */
	 private PdLogisticsBase getPdLogisticsBaseTotal(PdLogisticsBase pdLogisticsBaseJson, PdLogisticsBase pdLogisticsBaseDBA) {
		 log.info("do单----在方法getPdLogisticsBaseTotal中运行-----在保存前");
		 pdLogisticsBaseDBA.setMember_order_no(pdLogisticsBaseJson.getMember_order_no());
		 pdLogisticsBaseDBA.setSi_no(pdLogisticsBaseJson.getSi_no());
		 pdLogisticsBaseDBA.setStatus(pdLogisticsBaseJson.getStatus());
		 pdLogisticsBaseDBA.setWms_do(pdLogisticsBaseJson.getWms_do());
		 pdLogisticsBaseDBA.setStatus_name(pdLogisticsBaseJson.getStatus_name());
		 pdLogisticsBaseDBA.setStatus_code(pdLogisticsBaseJson.getStatus_code());
		 pdLogisticsBaseDBA.setStatus_time(pdLogisticsBaseJson.getStatus_time());
		 pdLogisticsBaseDBA.setOperator(pdLogisticsBaseJson.getOperator());
		 //modify gw 2015-04-28
		 pdLogisticsBaseDBA.setNum_order_type(pdLogisticsBaseJson.getNum_order_type());
		//-----------------------modify fu 订单全额支付的字段，这个是第二期要上线的功能，因此现在将这个校验屏蔽掉，第二期的时候再打开
		// pdLogisticsBaseDBA.setIsfull_pay(pdLogisticsBaseJson.getIsfull_pay());//modify gw 2015-07-15 订单全额支付
		 
		 //josn字符串中的集合mail_listJson
		 List<PdLogisticsBaseNum> mail_listJson = pdLogisticsBaseJson.getMail_list();
		 //准备存进数据库的集合
		 List<PdLogisticsBaseNum> mail_listDBA = new ArrayList();
		 //准备存进数据库的集合
		 List<PdLogisticsBaseDetail> baseDetail_itemsDBA  = new ArrayList();
		 //准备存进数据库的对象
		 
		//modify fu 2015-11-26  修改物流单号重复的问题---begin
		String wmsDo = pdLogisticsBaseDBA.getWms_do();
		//modify fu 2015-11-26  修改物流单号重复的问题---end
		 
		 PdLogisticsBaseNum pdLogisticsBaseNumDBA = new PdLogisticsBaseNum();
		 
		 PdLogisticsBaseDetail pdLogisticsBaseDetailDBA = new PdLogisticsBaseDetail();
		 
		 for(int j=0;j<mail_listJson.size();j++){
			    
			    //modify fu 2015-11-26  修改物流单号重复的问题---begin
				String pdLogisticsBaseNum_no = mail_listJson.get(j).getPdLogisticsBaseNum_no();
				String otherOne = wmsDo+"wmsDo"+pdLogisticsBaseNum_no;//do单号+wmsDo+物流单号
				//modify fu 2015-11-26  修改物流单号重复的问题---end
				
			    //数据库中的对象
				 pdLogisticsBaseNumDBA = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByBaseIdAndNo(pdLogisticsBaseJson.getBaseId().toString(), mail_listJson.get(j).getPdLogisticsBaseNum_no(),otherOne);
				//DO第二层的数据之前数据库中没有，这里是新增
				if(null==pdLogisticsBaseNumDBA){
						Long idGenerate = dao.definitionIdGenerate();
						pdLogisticsBaseNumDBA = new PdLogisticsBaseNum();
						pdLogisticsBaseNumDBA.setNumId(idGenerate);
						pdLogisticsBaseNumDBA.setPdLogisticsBase(pdLogisticsBaseDBA);
						pdLogisticsBaseNumDBA.setPdLogisticsBaseNum_no(mail_listJson.get(j).getPdLogisticsBaseNum_no());
						pdLogisticsBaseNumDBA.setName(mail_listJson.get(j).getName());
						pdLogisticsBaseNumDBA.setStatus(mail_listJson.get(j).getStatus());
						pdLogisticsBaseNumDBA.setMailTime(new Date());
						
						//modify fu 2015-11-26  修改物流单号重复的问题---begin
						pdLogisticsBaseNumDBA.setOtherOne(otherOne);
						//modify fu 2015-11-26  修改物流单号重复的问题---end
						
						List<PdLogisticsBaseDetail> baseDetail_itemsJsonOne = mail_listJson.get(j).getPdLogisticsBaseDetail_items();
						
						
						//在新增DO第二层数据的同时，新增DO第三层的数据
						if(null!=baseDetail_itemsJsonOne){
							if(baseDetail_itemsJsonOne.size()>0){
								 List<PdLogisticsBaseDetail> baseDetail_itemsDBA2  = new ArrayList();
								for(int p=0;p<baseDetail_itemsJsonOne.size();p++){
									Long idGenerateTwo = dao.definitionIdGenerate();
									pdLogisticsBaseDetailDBA = new PdLogisticsBaseDetail();
									pdLogisticsBaseDetailDBA.setDetailId(idGenerateTwo);
									pdLogisticsBaseDetailDBA.setPdLogisticsBaseNum(pdLogisticsBaseNumDBA);
									pdLogisticsBaseDetailDBA.setErp_goods_bn(baseDetail_itemsJsonOne.get(p).getErp_goods_bn());
									pdLogisticsBaseDetailDBA.setproduct_no(baseDetail_itemsJsonOne.get(p).getproduct_no());
									pdLogisticsBaseDetailDBA.setQty(baseDetail_itemsJsonOne.get(p).getQty());
									pdLogisticsBaseDetailDBA.setCreateTime(new Date());
									//modifw gw 2015-04-22 添加套餐所属编码字段和商品单价
									pdLogisticsBaseDetailDBA.setCombination_product_no(baseDetail_itemsJsonOne.get(p).getCombination_product_no());
									//因其他人误将代码更新到香港测试机modify gw 2015-05-18,后期不能注释掉这一行
									pdLogisticsBaseDetailDBA.setPrice(baseDetail_itemsJsonOne.get(p).getPrice());

									baseDetail_itemsDBA2.add(pdLogisticsBaseDetailDBA);
								}
								pdLogisticsBaseNumDBA.setPdLogisticsBaseDetail_items(baseDetail_itemsDBA2);
							}
						}
						//mail_listTwo添加对象PdLogisticsBaseNum
						mail_listDBA.add(pdLogisticsBaseNumDBA);
				}
				//DO第二层的数据之前数据库中是存在的，这里是更新
				else{
					pdLogisticsBaseNumDBA.setPdLogisticsBaseNum_no(mail_listJson.get(j).getPdLogisticsBaseNum_no());
					pdLogisticsBaseNumDBA.setName(mail_listJson.get(j).getName());
					pdLogisticsBaseNumDBA.setStatus(mail_listJson.get(j).getStatus());
				  		
					//modify fu 2015-11-26  修改物流单号重复的问题---begin
					pdLogisticsBaseNumDBA.setOtherOne(otherOne);
					//modify fu 2015-11-26  修改物流单号重复的问题---end
					
					    List<PdLogisticsBaseDetail> baseDetail_itemsJsonTwo = mail_listJson.get(j).getPdLogisticsBaseDetail_items();
						if(null!=baseDetail_itemsJsonTwo){
								if(baseDetail_itemsJsonTwo.size()>0){
										 for(int z=0;z<baseDetail_itemsJsonTwo.size();z++){
											 PdLogisticsBaseDetail pdLogisticsBaseDetailJson = baseDetail_itemsJsonTwo.get(z);
											 String productNoJson = pdLogisticsBaseDetailJson.getproduct_no();
											 
											 //modify fu 2015-11-21 
											 BigDecimal price = pdLogisticsBaseDetailJson.getPrice();
											 String qty = pdLogisticsBaseDetailJson.getQty();
											 String combination_product_no = pdLogisticsBaseDetailJson.getCombination_product_no();
											 pdLogisticsBaseDetailDBA = pdLogisticsBaseDetailDao.getPdLogisticsBaseDetailByInter(mail_listJson.get(j).getNumId().toString(),productNoJson,price,qty,combination_product_no);
											 //DO第三层的数据，数据库中原来存在，这里做新增操作	
											 if(null==pdLogisticsBaseDetailDBA){
												        //modifw gw 2015-04-22 解决空指针异常---begin
												        pdLogisticsBaseDetailDBA = new PdLogisticsBaseDetail();
												        //modifw gw 2015-04-22 解决空指针异常---end
												        
														Long idGenerateThree = dao.definitionIdGenerate();
														pdLogisticsBaseDetailDBA.setDetailId(idGenerateThree);
														pdLogisticsBaseDetailDBA.setPdLogisticsBaseNum(pdLogisticsBaseNumDBA);
														pdLogisticsBaseDetailDBA.setErp_goods_bn(baseDetail_itemsJsonTwo.get(z).getErp_goods_bn());
														pdLogisticsBaseDetailDBA.setproduct_no(baseDetail_itemsJsonTwo.get(z).getproduct_no());
														pdLogisticsBaseDetailDBA.setQty(baseDetail_itemsJsonTwo.get(z).getQty());
														pdLogisticsBaseDetailDBA.setCreateTime(new Date());
														//modifw gw 2015-04-22 添加套餐所属编码字段
														pdLogisticsBaseDetailDBA.setCombination_product_no(baseDetail_itemsJsonTwo.get(z).getCombination_product_no());
														//因其他人误将代码更新到香港测试机modify gw 2015-05-18,后期不能注释掉这一行
														pdLogisticsBaseDetailDBA.setPrice(baseDetail_itemsJsonTwo.get(z).getPrice());

											 }
												//之前do第三层的数据在数据库中已经存在，这里做更新操作
												else{
														pdLogisticsBaseDetailDBA.setErp_goods_bn(pdLogisticsBaseDetailJson.getErp_goods_bn());
														pdLogisticsBaseDetailDBA.setproduct_no(pdLogisticsBaseDetailJson.getproduct_no());
														pdLogisticsBaseDetailDBA.setQty(pdLogisticsBaseDetailJson.getQty());
														//modifw gw 2015-04-22 添加套餐所属编码字段
														pdLogisticsBaseDetailDBA.setCombination_product_no(pdLogisticsBaseDetailJson.getCombination_product_no());
														//因其他人误将代码更新到香港测试机，这一块暂时认为的赋值（modify gw 2015-05-18）,后期不能注释掉这一行
														pdLogisticsBaseDetailDBA.setPrice(pdLogisticsBaseDetailJson.getPrice());

												}
												baseDetail_itemsDBA.add(pdLogisticsBaseDetailDBA);
										 }
										 pdLogisticsBaseNumDBA.setPdLogisticsBaseDetail_items(baseDetail_itemsDBA);
								}
					     }
						mail_listDBA.add(pdLogisticsBaseNumDBA);
				}
	      }
		 pdLogisticsBaseDBA.setMail_list(mail_listDBA);
		 return pdLogisticsBaseDBA;
	}

	/**
     * 更新JOCS发货单的真实发货数据 
     * @author gw 2014-11-26
     * @param pdLogisticsBaseList 传递过来的接口数据转换成的list集合
     */
	public  RspEntity  savePdLogisticsBaseList(String jsonString) {
		log.info("LO单开始-----------------------------------------------------------");
		RspEntity rspEntity = new RspEntity();
		if(!StringUtil.isEmpty(jsonString)){
			List<PdLogisticsBase> pdLogisticsBaseList = new ArrayList();
			try{
			         Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
			         classMap.put("pdLogisticsBase_list", PdLogisticsBase.class);
			         classMap.put("mail_list", PdLogisticsBaseNum.class);
				     classMap.put("pdLogisticsBaseDetail_items", PdLogisticsBaseDetail.class);
					 //将json字符串转换成java对象
			         PdLogistics pdLogistics =InterfaceJsonUtil.returnnoJsonToModel(jsonString,PdLogistics.class,classMap);
			         //modify by fu 2016-02-25 条形码
			         String barCode = pdLogistics.getBar_code();
					 pdLogisticsBaseList = pdLogistics.getPdLogisticsBase_list();
					 //防止发个空的LO单过来
			         if(null!=pdLogisticsBaseList && pdLogisticsBaseList.size()>0){
							//更新的LO单（发货单）数据后，同步的将相关的发货单状态改为已发货
							String restult = this.setPdSendInfoShipped(pdLogisticsBaseList,barCode);
							
							String memberOrderNo = pdLogisticsBaseList.get(0).getMember_order_no();
							JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(memberOrderNo);
							
							//modify by fu 2016-05-16 给中脉EC系统第三\四期接口添加开关-退货单保存---这个等第四期功能上线后在配置参数值为Y
							//获取接口开关标示
							String swtichSend = ListUtil.getListValue("CN", "interface.sendswitch", "ship.loUpdate");
					        if((!StringUtil.isEmpty(swtichSend))&&"Y".equals(swtichSend)){
					        	log.info("LO单在savePdLogisticsBaseList方法中运行：ship.loUpdate开关打开");
							//全额支付的LO单推送给渠道接口，新合规第四期才上线，因此现在注释掉，新合规第四期再放开--------modify gw 2015-08-03 begin
							if((null!=jpoMemberOrder)&&(null!=jpoMemberOrder.getIsFullPay())&& ("1".equals(jpoMemberOrder.getIsFullPay().toString())) ){
								//只有是全额支付的订单才向渠道推送LO单数据
								//将JSON对象转换为JSON字符串
								MsgSendService msgSendService = new MsgSendService();
								//方法名
								String methodName = "ship.loUpdate";
								msgSendService.setSender(Constants.QU_SEND);//渠道平台
								//调用外部接口
								String quDaoResult = msgSendService.post(jsonString, methodName);
					        	log.info("LO单推送渠道结束，接口返回:"+quDaoResult);
								rspEntity.setRsp(Constants.SUCCESS_STRING);
								//=============================接口日志写入开始 Modify By WUCF 20150123
								JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
								jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
								jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
								jocsInterfaceLog.setMethod(methodName);//方法名称
								jocsInterfaceLog.setContent(jsonString.toString());//发送内容content
								jocsInterfaceLog.setReturnDesc(quDaoResult);//返回内容
								
								ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
								//=============================接口日志写入完毕	
							
							}
							//全额支付的LO单推送给渠道接口，新合规第四期才上线，因此现在注释掉，新合规第四期再放开--------modify gw 2015-08-03 begin
					        }
							
							
							rspEntity.setRsp(Constants.SUCCESS_STRING);
							return rspEntity;
			        }else{
			        	rspEntity.setRsp(" pdLogisticsBase_list is null ");
						return rspEntity;
			        }
			}
			 catch (AppException e) {// 捕获库存不足
				    rspEntity.setSub_msg("EC商品库存不足");
					log.info("LO单异常：EC商品库存不足");
					throw new AppException("pd.notEnoughStock");
			 }catch(Exception e){
				log.info("LO单业务异常："+e.toString());
				//接口传递过来的数据转换成对象的时候异常  INTERFACE_JSON_FORMAT = "interfaceJsonFormat"
				rspEntity.setSub_msg(Constants.INTERFACE_JSON_FORMAT);
				return this.getRspEntityString(rspEntity);
			}
			// modify gw 依据业务需求，这个方法不会被使用   2015-01-23
			//return this.savePdLogisticsBaseListTwo(pdLogisticsBaseList,jsonString);
		}else{
			log.info("LO单JSON字符串异常");
			rspEntity.setSub_msg(" json is null ");
			return this.getRspEntityString(rspEntity);
		}
	}
	
	/**
	 * 对do第三层或lo第四层的数据进行处理
	 * @author gw  2014-12-02
	 * @param pdLogisticsBaseDetail_items  list集合（接口数据DO的第三层或LO第四层pdLogisticsBaseDetail_items）
	 * @param memberOrderNo 订单号
	 * @param pdLogisticsBaseNum 物流状态接口物流信息的对象
	 * @return list
	 */
	public List<PdLogisticsBaseDetail> checkPdLogisticsBaseDetailList(List<PdLogisticsBaseDetail> pdLogisticsBaseDetail_items,String memberOrderNo,PdLogisticsBaseNum pdLogisticsBaseNum){
		log.info("do单----在方法checkPdLogisticsBaseDetailList中运行");
		if(null!=pdLogisticsBaseDetail_items){
				if(pdLogisticsBaseDetail_items.size()>0){
					 for(int z=0;z<pdLogisticsBaseDetail_items.size();z++){
						 PdLogisticsBaseDetail pdLogisticsBaseDetail = pdLogisticsBaseDetail_items.get(z);
						 String productNno = pdLogisticsBaseDetail.getproduct_no();
						 
						 //modify fu 2015-11-21 fu
						 BigDecimal price= pdLogisticsBaseDetail.getPrice();
						 String qty = pdLogisticsBaseDetail.getQty();
						 String combination_product_no = pdLogisticsBaseDetail.getCombination_product_no();
						 //modify fu 2015-11-21 fu
						 
						 PdLogisticsBaseDetail  pdLogisticsBaseDetailTwo = pdLogisticsBaseDetailDao.getPdLogisticsBaseDetailByInter(pdLogisticsBaseNum.getNumId().toString(),productNno,price,qty,combination_product_no);
							if(null==pdLogisticsBaseDetailTwo){
								//之前do第三层的数据在数据库中没有保存
								pdLogisticsBaseDetail_items.get(z).setCreateTime(new Date());
								
								pdLogisticsBaseDetail_items.get(z).setPdLogisticsBaseNum(pdLogisticsBaseNum);
							}
							//之前do第三层的数据在数据库中已经保存
							else{
								//对PdLogisticsBaseNum的子对象PdLogisticsBaseDetail重新赋主键值
								pdLogisticsBaseDetail_items.get(z).setDetailId(pdLogisticsBaseDetailTwo.getDetailId());
								//将原发货时间重新给对象
								pdLogisticsBaseDetail_items.get(z).setCreateTime(pdLogisticsBaseDetailTwo.getCreateTime());
								
								pdLogisticsBaseDetail_items.get(z).setPdLogisticsBaseNum(pdLogisticsBaseNum);
							}
					 }
					 //正常的返回值
					 return pdLogisticsBaseDetail_items;
				}
	     }
		return null;
	}
	
	/**
	 * 更新的LO单（发货单）数据后，同步的将相关的发货单状态改为已发货,将符合条件的订单转换成已发货状态
	 * @author gw 2014-12-02
	 * @param barCode modify by fu 2016-02-25 条形码
	 * @param 
	 */
	public String setPdSendInfoShipped(List<PdLogisticsBase> pdLogisticsBaseList,String barCode){
		log.info("LO单业务：在setPdSendInfoShipped方法中运行");
		
		//根据这个接口LO单（发货单）的信息，表明发货单已经发货，那么就将LO单的发货状态改为已发货
		PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfo(((PdLogisticsBase)pdLogisticsBaseList.get(0)).getSi_no());
		if(null!=pdSendInfo){
			pdSendInfo.setShipType("0");
			pdSendInfo.setOkTime(new Date());
			Integer orderFlag = pdSendInfo.getOrderFlag();
			
			//modify by fu 自助换货功能上线，这部分不上线的功能暂时屏蔽掉 2016-09-12
			//modify by fu 2016-09-06  解决发货单编辑的乐观锁问题（Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)）------------begin
			/*String OrderFlagS = pdSendInfoDao.getPdSendInfoOrderFlag(((PdLogisticsBase)pdLogisticsBaseList.get(0)).getSi_no());
			if(!StringUtil.isEmpty(OrderFlagS)){
			     orderFlag = Integer.parseInt(OrderFlagS);
			}*/
			//modify by fu 2016-09-06------------end
			
			//modify by fu 2016-02-25 条形码
			pdSendInfo.setBarCode(barCode);
			if(null!=orderFlag && 2>orderFlag){
				pdSendInfo.setOrderFlag(2);
				pdSendInfo.setOkUsrCode("interface");
				pdSendInfo.setStockFlag("1");
				//modify by fu 2015-09-23 经需求重新定义，发货单改库存，但是不判断库存是否足够
				/*//modify fu 2015-09-23 判断库存-------------------begin
				Set sets = pdSendInfo.getPdSendInfoDetails();
				Iterator iterator = sets.iterator();
				while (iterator.hasNext()) {
					PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
					if (!pdWarehouseStockManager.enoughStock(pdSendInfo.getWarehouseNo(), pdSendInfoDetail
							.getProductNo(), pdSendInfoDetail.getQty())) {
						return "EC product Insufficient inventory";
					}
				}
				//modify fu 2015-09-23 判断库存------------------end
                */				
				pdWarehouseStockManager.updatePdWarehouseStock(pdSendInfo);
				
				pdSendInfoDao.savePdSendInfo(pdSendInfo);

			}else{
				
				//modify by fu 自助换货功能上线，这部分不上线的功能暂时屏蔽掉 2016-09-12
				//modify by fu 2016-09-06 更新条形码----begin
				//pdSendInfoDao.reSetPdSendInfoBarCode(((PdLogisticsBase)pdLogisticsBaseList.get(0)).getSi_no(), barCode);
				//modify by fu 2016-09-06更新条形码-----end
				
				pdSendInfoDao.savePdSendInfo(pdSendInfo);////modify by fu 自助换货功能上线，这部分暂时打开，后面在屏蔽掉 2016-09-12
				
			}
			String numOrderType = ((PdLogisticsBase)pdLogisticsBaseList.get(0)).getNum_order_type();
			//numOrderType = "MO";//因其他人误将代码更新到香港测试机，这一块暂时认为的赋值（modify gw 2015-05-18）,后期要注释掉这一行

		    //modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---begin
    	    if((!StringUtil.isEmpty(numOrderType))&&("MO".equals(numOrderType)||"mo".equals(numOrderType))){
  			//modify  gw 添加订单号类型判断  2015-04-24  订单号类型(MO表示是订单，EX表示是关联到换货单)---end
				//将发货单相关的订单的状态变成已发货--前提是所有的发货单都已经发货
				String memberOrderNo = pdSendInfo.getOrderNo();
				if(!StringUtil.isEmpty(memberOrderNo)){
				      List<PdSendInfo> list = pdSendInfoDao.getPdSendInfoList(memberOrderNo);
				      if((null!=list) && list.size()>0){
				    	      List<Integer> listY = new ArrayList<Integer>();
				    	      //将发货单的发货状态放集合中
					    	  for(int i=0;i<list.size();i++){
					    		  PdSendInfo pdSendInfoL = list.get(i);
					    		  listY.add(pdSendInfoL.getOrderFlag());
					    	  }
					    	  
					    	  //
					    	  for(int j=0;j<listY.size();j++){
					    		  if(listY.get(j)<2){
					    			  return null;
					    		  }
					    	  }
					    	  //将订单的状态变成已发货
					    	 JpoMemberOrder jpoMemberOrder =  jpoMemberOrderDao.getJpoMemberOrderByInterface(memberOrderNo);
					    	 if(null!=jpoMemberOrder){
					    	     jpoMemberOrder.setInterOkDelivery("2");
					    	     jpoMemberOrderDao.saveJpoMemberOrder(jpoMemberOrder);
					    	 }
				      }
			   }
		   }
		}
		return null;
	}
	
	/**
	 * 在保存成功DO后，去查询已签收或已拒收的物流信息
	 * @author gw 2015-01-09 modigy by gw 2015-06-17 因为有了定时器定时去其他系统获取物流实时信息，因此这个方法暂时弃用
	 * @param pdLogisticsBase
	 */
	public void getMailNoByPdLogisticsBase(PdLogisticsBase pdLogisticsBase){
		try{
			MailStatus mailStatus = new MailStatus();
			if(null!=pdLogisticsBase){
				List<PdLogisticsBaseNum> mail_list = pdLogisticsBase.getMail_list();
				if(null!=mail_list){
					if(mail_list.size()>0){
						for(int i=0;i<mail_list.size();i++){
							    PdLogisticsBaseNum pdLogisticsBaseNum = mail_list.get(i);
							    if(null!=pdLogisticsBaseNum){
							    	
					    			 //modify fu 2015-09-22  不用查询物流单号信息了---begin
							    	String pdLogisticsBaseNum_no = pdLogisticsBaseNum.getPdLogisticsBaseNum_no();
							    	if(!StringUtil.isEmpty(pdLogisticsBaseNum_no)){
							    		continue;
							    	}
					    			 //modify fu 2015-09-22----end

							    	String name = pdLogisticsBaseNum.getName();
							    	String status = pdLogisticsBaseNum.getStatus();
							    	//status 0020：已签收;0022：客户拒收
							    	if(Constants.RECEIVED.equals(status)||Constants.CUSTOMER_REJECTION.equals(status)){
							    		if(!StringUtil.isEmpty(pdLogisticsBaseNum_no)){
							    			mailStatus.setMail_no(pdLogisticsBaseNum_no);
							    			mailStatus.setLogist_comp(name);
							    			//将java对象转换成json对象
							    			JSONObject jsonObject = JSONObject.fromObject(mailStatus);
							    			//将json对象转换成json字符串
							    			String returnnoJsonString = jsonObject.toString();
							    			
							    			//待公共类和公共方法有了话，这个再放开---------modigy gw 2015-08-03 begin
							    			/*//调用发送接口--向OMS发送----modify gw 2014-12-23----开始
							    			MsgSendService msgSendService = new MsgSendService();
							    			//方法名
							    			String methodName = "ship.search";
							    			msgSendService.setSender(Constants.OMS_SEND);//OMS平台
							    			String jsonString = msgSendService.post(returnnoJsonString, methodName);
							    			//=============================接口日志写入开始 Modify By WUCF 20150123
											JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
											jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
											jocsInterfaceLog.setSender(Constants.OMS_SEND);//数据的接收方
											jocsInterfaceLog.setMethod(methodName);//方法名称
											jocsInterfaceLog.setContent(returnnoJsonString.toString());//发送内容content
											jocsInterfaceLog.setReturnDesc(jsonString);//返回内容
											
											ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
											//=============================接口日志写入完毕	
											mailStatusManager.saveMailStatuss(jsonString);*/
							    			
							    			//待公共类和公共方法有了话，这个再放开---------modigy gw 2015-08-03 end

							    		}
							    	}
							    }
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Add By WuCF 201411
	 * 中心物流状态结构数据接收并修改本地订单明细数据：仓内作业状态、物流跟踪单号、确认收货状态；
	 * @param pdLogisticsBase
	 */
	public void updateJpoMemberOrderList(PdLogisticsBase pdLogisticsBase){
		dao.updateJpoMemberOrderList(pdLogisticsBase);
	}

	/**
     * 定时器-根据物流单号获取物流信息(每小时查询并保存一次）
     * @author gw 2015-06-15
     * 
     */
	public void gainMailStatus() {
		log.info("在类PdLogisticsBaseManagerImpl的方法gaiMailStatus中运行,根据物流单号获取物流信息定时器开始运行");
		try{
				List<PdLogisticsBaseNum> list = pdLogisticsBaseNumDao.getPdLogisticsBaseNumForPdMailReceipt();
				if(null!=list){
					int m = 0;
					//为了测试的方便才注释掉的，测试完成后还是需要还原的---modify gw 2015-06-16 ----begin
					for(int i=0;i<list.size();i++){
						
					//为了测试的方便才注释掉的，测试完成后还是需要还原的---modify gw 2015-06-16 ----end
						//for(int i=0;i<1;i++){//测试的一行，测试完后要干掉
						log.info("在类PdLogisticsBaseManagerImpl的方法gaiMailStatus中运行,扫描表pd_logistics_base_num后有数据");
						PdLogisticsBaseNum pdLogisticsBaseNum = list.get(i);
						String mailNo = pdLogisticsBaseNum.getPdLogisticsBaseNum_no();
						
						//modify by fu 如果物流单号为空，那么就不去做查询了 2015-09-22 
						if(StringUtil.isEmpty(mailNo)){
							continue;//continue的功能是结束本次循环跳到下一次循环。
						}
						//modify by fu 2015-09-22 ----------end 
						
						//modify by fu 2016-1-22 已经收货确认的发货单就不查具体物流实时信息了------------begin
						PdLogisticsBase pdLogisticsBase = pdLogisticsBaseNum.getPdLogisticsBase();
						if(null!=pdLogisticsBase){
							String siNo = pdLogisticsBase.getSi_no();
							if(!StringUtil.isEmpty(siNo)){
								PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfoForSiNo(siNo);
								if(null==pdSendInfo){
									continue;//continue的功能是结束本次循环跳到下一次循环。
								}else{
									Integer orderFlag = pdSendInfo.getOrderFlag();
									if(null!=orderFlag){
										if(orderFlag>2){
											continue;//continue的功能是结束本次循环跳到下一次循环。
										}
									}
								}
							}else{
								continue;//continue的功能是结束本次循环跳到下一次循环。
							}
						}
						//modify by fu 2016-1-22 已经收货确认的发货单就不查具体物流实时信息了------------end
						MailStatusSend mailStatusSend = new MailStatusSend();
						mailStatusSend.setMailNo(mailNo);
						
		    			//待公共类和公共方法有了话，这个再放开---------modigy gw 2015-08-03 begin
						
						//将java对象转换成JSON对象
						JSONObject jsonObjectSend = JSONObject.fromObject(mailStatusSend);
						//将JSON对象转换为JSON字符串
						String returnnoJsonStringSend = jsonObjectSend.toString();
						
						MsgSendService msgSendService = new MsgSendService();
						msgSendService.setSender(Constants.OMS_SEND);//OMS平台
						//方法名
						String methodName = "ship.search";
						//调用外部接口------------------------------------------自己测试完重新打开这一行
						String jsonString = msgSendService.post(returnnoJsonStringSend, methodName);
						log.info("在类PdLogisticsBaseManagerImpl的方法中gainMailStatus运行,物流信息返回结果:"+jsonString);
						//=============================接口日志写入开始
						JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
						jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
						jocsInterfaceLog.setSender(Constants.OMS_SEND);//数据的接收方
						jocsInterfaceLog.setMethod(methodName);//方法名称
						jocsInterfaceLog.setContent(returnnoJsonStringSend);//发送内容content
						jocsInterfaceLog.setReturnDesc(jsonString);//返回内容
						
						ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作//写入日志操作
						//=============================接口日志写入完毕
						log.info("在类PdLogisticsBaseManagerImpl的方法中gainMailStatus运行,成功往日志表中写发送日志");
						String status = pdLogisticsBaseNum.getStatus();
						//保存或更新物流单号实时信息
						boolean succ = this.suMailStatus(jsonString,status,pdLogisticsBaseNum.getNumId(),pdLogisticsBaseNum.getPdLogisticsBase().getBaseId().toString());
		    			//待公共类和公共方法有了话，这个再放开---------modigy gw 2015-08-03 end

						//modify by fu 2016-1-29------------------每次只能执行查询操作7000次，从此之后就静静的等待下次查询了
						if(succ){
							m = m+1;
							if(m>7000){
								log.info("-------------------------"+m);
								return;//
							}
					    }
						//modify by fu 2016-1-29------------------每次只能执行查询操作7000次，从此之后就静静的等待下次查询了
						
						/*//下面是特意为测试才造的数据--------modify gw 2015-06-16---begin
						if(i==0){
						MailStatus mailStatus = new MailStatus();
						mailStatus.setMail_no("120000015301");
						mailStatus.setLogist_comp("广州大田物流");
						List<Items> items = new ArrayList();
						
						Items itemsOne = new Items();
						itemsOne.setAcceptAddress("广州一号");
						itemsOne.setRemark("发货一号");
						itemsOne.setAcceptTime(new Date().toString());
						items.add(itemsOne);
						
						Items itemsTwo = new Items();
						itemsTwo.setAcceptAddress("广州二号");
						itemsTwo.setRemark("发货二号");
						itemsTwo.setAcceptTime(new Date().toString());
						items.add(itemsTwo);
						Items itemsTwo1 = new Items();
						itemsTwo1.setAcceptAddress("广州二1号");
						itemsTwo1.setRemark("发货二1号");
						itemsTwo1.setAcceptTime(new Date().toString());
						items.add(itemsTwo1);
						
						Items itemsTwo2 = new Items();
						itemsTwo2.setAcceptAddress("广州二2号");
						itemsTwo2.setRemark("发货二2号");
						itemsTwo2.setAcceptTime(new Date().toString());
						items.add(itemsTwo2);
						
						mailStatus.setItems(items);
						JSONObject jsonObjectOne = JSONObject.fromObject(mailStatus);
				    	String jsonString = jsonObjectOne.toString();
				    	String status = pdLogisticsBaseNum.getStatus();
							//保存或更新物流单号实时信息
						this.suMailStatus(jsonString,status,pdLogisticsBaseNum.getNumId());
						}else{
                        String a = "";
                        a.substring(0, 3);
							
						MailStatus mailStatusTwo = new MailStatus();
						mailStatusTwo.setMail_no("120000015302");
						mailStatusTwo.setLogist_comp("广州大田物流");
						List<Items> itemssTwo = new ArrayList();
						
						Items itemsThree = new Items();
						itemsThree.setAcceptAddress("广州三号");
						itemsThree.setRemark("发货四号");
						itemsThree.setAcceptTime(new Date().toString());
						itemssTwo.add(itemsThree);
						
						Items itemsFour = new Items();
						itemsFour.setAcceptAddress("广州五号");
						itemsFour.setRemark("发货六号");
						itemsFour.setAcceptTime(new Date().toString());
						Items itemsFive = new Items();
						itemsFive.setAcceptAddress("广州12号");
						itemsFive.setRemark("发货12号");
						itemsFive.setAcceptTime(new Date().toString());
						itemssTwo.add(itemsFour);
						itemssTwo.add(itemsFive);
						
						Items itemsSex = new Items();
						itemsSex.setAcceptAddress("广州6号");
						itemsSex.setRemark("发货6号");
						itemsSex.setAcceptTime(new Date().toString());
						itemssTwo.add(itemsSex);
						
						mailStatusTwo.setItems(itemssTwo);

						JSONObject jsonObjectTwo = JSONObject.fromObject(mailStatusTwo);
				    	String jsonStringTwo = jsonObjectTwo.toString();
				    	String statusTwo = "0020";
							//保存或更新物流单号实时信息
						this.suMailStatus(jsonStringTwo,statusTwo,list.get(1).getNumId());
				    	//上面是特意为测试才造的数据--------modify gw 2015-06-16---end
						}*/
					}
				}
		}catch(Exception e){
			log.info("在类PdLogisticsBaseManagerImpl的方法中gainMailStatus运行,根据物流单号获取物流信息定时器异常"+e);
			e.printStackTrace();
		}
		
	}

	/**
	 * 保存或更新物流单号实时信息(定时器用到)
	 * @author gw 2015-06-15
	 * @param jsonString 由接口查询到的物流单实时信息
	 * 
	 */
	public boolean suMailStatus(String jsonString,String status,Long numId,String baseId) {
	  try{
			    log.info("在类PdLogisticsBaseManagerImpl的方法中suMailStatus运行,保存或更新物流单号实时信息开始");
				if(!StringUtil.isEmpty(jsonString)){
					Map<String, Class> classMap = new HashMap<String, Class>();
					classMap.put("items", Items.class);
					//将json字符串转换成java对象
					MailStatus mailStatus = InterfaceJsonUtil.returnnoJsonToModel(jsonString, MailStatus.class, classMap);
					log.info("在类PdLogisticsBaseManagerImpl的方法中gainMailStatus运行,JSON转对象正常");
					if(null!=mailStatus){
						String mailNo = mailStatus.getMail_no();
						if(StringUtil.isEmpty(mailNo)){
							return false;
						}
						//保存或更新物流单号实时信息
						boolean result = mailStatusManager.sOrUMailStatus(mailStatus,numId);
						//保存已收货标志----modify by fu 2016-1-29 因为生产库表pd_logistics_base_num的status全部都是0099，因此这部分代码不会被执行
						if((Constants.RECEIVED.equals(status)||Constants.CUSTOMER_REJECTION.equals(status))&&(result)){
							//modify by fu 物流实时信息优化，这部门代码特意注释掉
							/*PdMailReceipt pdMailReceipt = new PdMailReceipt();
							Long id = mailStatusManager.getId();
							pdMailReceipt.setMailReceiptId(id);
							pdMailReceipt.setStatusId(numId.toString());//pd_logistics_base_num表的主键
							mailStatusManager.ssavePdMailReceiptReport(pdMailReceipt);*/
							int a = dao.reSetOtherOne(baseId);
							if(1==a){
								log.info("在类PdLogisticsBaseManagerImpl的方法中suMailStatus运行,修改other_one成功");
							}
						}
						return result;
					}
			   }
				return false;
		}catch(Exception e){
			log.info("在类PdLogisticsBaseManagerImpl的方法中suMailStatus运行异常------------"+e);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * 判断发货单有没有关联的DO单传过来
     * @author fu 2015-09-16
     * @param siNo
     * @return boolean
     */
	public boolean getDoResult(String siNo){
		return dao.getDoResult(siNo);
	}
	
	/**
	 * DO保存成功，发送短信
	 * @author gw 2015-09-24
	 * @param jsonString
	 */
	public void sendMessage(String jsonString){
		log.info("DO单发送短信-在PdLogisticsBaseManagerImpl类的sendMessage方法中运行");
		try{
			Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
	        classMap.put("mail_list", PdLogisticsBaseNum.class);
	        classMap.put("pdLogisticsBaseDetail_items", PdLogisticsBaseDetail.class);
			//将json字符串转换成java对象
	        PdLogisticsBase pdLogisticsBase = InterfaceJsonUtil.returnnoJsonToModel(jsonString,PdLogisticsBase.class,classMap);
	        if(null!=pdLogisticsBase){
	        	List<PdLogisticsBaseNum> mail_list = pdLogisticsBase.getMail_list();
	        	//有三层DO数据结构的才会发送短信
			    if(null!=mail_list && mail_list.size()>0){
				    //同WMS的开发人员沟通后，DO单的第二层有且仅有一个数据，就是pdLogisticsBase和PdLogisticsBaseNum其实是一对一的对应关系
			    	for(int i=0;i<mail_list.size();i++){
			    		PdLogisticsBaseNum pdLogisticsBaseNum = mail_list.get(i);
			    		if(null == pdLogisticsBaseNum){
			    			continue;
			    		}
			    		String pdLogisticsBaseNum_no = pdLogisticsBaseNum.getPdLogisticsBaseNum_no();//物流跟踪单号 
			    		String status = pdLogisticsBaseNum.getStatus();
			    		//物流单号为空，或 物流单号和物流状态均不为空，但是物流状态为0020(已签收),0022(已拒收)
			    		if(StringUtil.isEmpty(pdLogisticsBaseNum_no) || 
			    				((!StringUtil.isEmpty(pdLogisticsBaseNum_no)) && (!StringUtil.isEmpty(status)) &&("0020".equals(status)||"0022".equals(status)))){
			    			continue;
			    		}
						PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfoForSiNo(pdLogisticsBase.getSi_no());
						log.info("DO单发送短信-在PdLogisticsBaseManagerImpl类的sendMessage方法中运行：开始发送短信");
						//Modify By WuCF 20140310 添加短信开关控制
						String smsSendOpenStatus = ConfigUtil.getConfigValue("CN", "smssend.open.status");
						
						//哪些仓库发货可以发送短信，在参数中配置
						String smsSendWarehouseNos = ConfigUtil.getConfigValue("CN", "smssend.warehouseno");
						
						//Modify By WuCF 20140117 中脉系统短信发送
						String mobilePhone = pdSendInfo.getRecipientMobile();
						//Modify By WuCF 20150424 是否包含字母或数字
						String isContain = this.isNumberOrWord(pdLogisticsBaseNum_no); 
						if(smsSendWarehouseNos.contains(","+pdSendInfo.getWarehouseNo()+",") && "Y".equals(smsSendOpenStatus)){// && isContain!=null && !"".equals(isContain)
							/*短信内容：亲爱的中脉家人，您的会员号CN********，发货单号LO01*************,已于*月*日发出。
							运单号：*****（系统上传的物流跟踪号），物流公司：*****（系统上传的物流公司）。
							请您查收，并注意开箱验货。 */
							/*SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日");   
							String dateStr = dateformat2.format(new Date());
							
							StringBuffer message = new StringBuffer("亲爱的中脉家人，您的会员号");
							message.append(pdSendInfo.getCustomer().getUserCode());
							message.append("，发货单号");
							message.append(pdSendInfo.getSiNo());
							message.append("，已于"+dateStr+"发出。");
							if(StringUtils.isNotEmpty(pdLogisticsBaseNum_no)){
								message.append("运单号：");
								message.append(pdLogisticsBaseNum_no);
								message.append("，");
							}
							message.append("物流公司：");
							message.append(ListUtil.getListValue("CN", "pd.shno", pdSendInfo.getShNo()));
							message.append("。请您查收，并注意开箱验货。");
							log.info("发货短信message:"+message);
							String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
							String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
							System.out.println(message);
							SmsSend.sendSms(url1,url2,mobilePhone, message.toString());*/
						
							/**亲爱的中脉家人，您CN********下LO01************** 已于*月*日发出，**物流 ********* 请您注意查收货物。
							 * 中脉有效查货期30天，超过此期限及签收后反馈少货公司将不予受理，敬请知悉。
							 */
							SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日");   
							String dateStr = dateformat2.format(new Date());
							
							StringBuffer message = new StringBuffer("亲爱的中脉家人，您");
							message.append(pdSendInfo.getCustomer().getUserCode());
							message.append("下");
							message.append(pdSendInfo.getSiNo());
							message.append("已于"+dateStr+"发出，");
							message.append(pdLogisticsBaseNum.getName());
							message.append("物流");
							if(StringUtils.isNotEmpty(pdLogisticsBaseNum_no)){
								message.append(pdLogisticsBaseNum_no);
							}
							message.append("请您注意查收货物。中脉有效查货期30天，超过此期限及签收后反馈少货公司将不予受理，敬请知悉。");
							log.info("发货短信message:"+message);
							String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
							String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
							System.out.println( message.toString());
							SmsSend.sendSms(url1,url2,mobilePhone, message.toString());
							
                            //modify by fu 2016-1-20 变更短信内容---------------------------------------end
							
							//将短信写入到数据库表
							JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
							jpmSmssendInfo.setSmsType("1");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
							jpmSmssendInfo.setSmsRecipient(pdSendInfo.getCustomer().getUserCode());//短信接收人:用户会员编号
							jpmSmssendInfo.setSmsMessage(message.toString());//短信内容
							jpmSmssendInfo.setSmsTime(new Date());//发送时间
							jpmSmssendInfo.setSmsOperator("interface");//操作人
							jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
							jpmSmssendInfo.setRemark("单个发货确认！仓库："+pdSendInfo.getWarehouseNo());//备注
							jpmSmssendInfo.setPhoneNum(mobilePhone);//手机号码
							jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
						}
				    }	
			  }	
			}
		}catch(Exception e){
			log.info("DO单发送短信-在PdLogisticsBaseManagerImpl类的sendMessage方法中运行：发送短信异常---"+e.toString());
            e.printStackTrace();
		}
	}
	
	/**
	 * Add By WuCF 20150424
	 * 判断是否包含字母，数字
	 * @param number
	 * @return
	 */
	public String isNumberOrWord(String str){
	     Pattern pattern =Pattern.compile("[a-zA-Z]|\\d");
	     if(!StringUtil.isEmpty(str)){
		     Matcher matcher = pattern.matcher(str);
		     while (matcher.find()) {
		    	 // 如匹配成功即走到这里
		    	 return matcher.group();
		     }
	     }
	     return "";
	}
	
}
