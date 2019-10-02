package com.joymain.jecs.pd.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import service.MsgSendService;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.dao.PdExchangeOrderDao;
import com.joymain.jecs.pd.dao.PdSendInfoDetailDao;
import com.joymain.jecs.pd.model.Delivery;
import com.joymain.jecs.pd.model.Delivery_items;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.model.Reship;
import com.joymain.jecs.pd.model.Returno_items;
import com.joymain.jecs.pd.service.PdExchangeOrderBackManager;
import com.joymain.jecs.pd.service.PdExchangeOrderDetailManager;
import com.joymain.jecs.pd.service.PdExchangeOrderManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pd.service.PdShipmentsDetailManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

@SuppressWarnings("rawtypes")
public class PdExchangeOrderManagerImpl extends BaseManager implements
		PdExchangeOrderManager {
	private PdExchangeOrderDao dao;
	private JpoMemberOrderDao jpoMemberOrderDao;
	private PdExchangeOrderBackManager pdExchangeOrderBackManager;
	private PdExchangeOrderDetailManager pdExchangeOrderDetailManager;
	private JpmProductSaleManager jpmProductSaleManager;

	private PdWarehouseStockManager pdWarehouseStockManager;
	private PdShipmentsDetailManager pdShipmentsDetailManager;
	private PdSendInfoManager pdSendInfoManager;
	private JpmProductManager jpmProductManager;
	private PdSendInfoDetailDao pdSendInfoDetailDao;

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void setPdShipmentsDetailManager(
			PdShipmentsDetailManager pdShipmentsDetailManager) {
		this.pdShipmentsDetailManager = pdShipmentsDetailManager;
	}

	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public void setJpmProductSaleManager(
			JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public void setPdExchangeOrderDetailManager(
			PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
		this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
	}

	public void setPdExchangeOrderBackManager(
			PdExchangeOrderBackManager pdExchangeOrderBackManager) {
		this.pdExchangeOrderBackManager = pdExchangeOrderBackManager;
	}

	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
	
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public void setPdSendInfoDetailDao(PdSendInfoDetailDao pdSendInfoDetailDao) {
		this.pdSendInfoDetailDao = pdSendInfoDetailDao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdExchangeOrderDao(PdExchangeOrderDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdExchangeOrderManager#getPdExchangeOrders(com.joymain.jecs.pd.model.PdExchangeOrder)
	 */
	public List getPdExchangeOrders(final PdExchangeOrder pdExchangeOrder) {
		return dao.getPdExchangeOrders(pdExchangeOrder);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdExchangeOrderManager#getPdExchangeOrder(String
	 *      eoNo)
	 */
	public PdExchangeOrder getPdExchangeOrder(final String eoNo) {
		return dao.getPdExchangeOrder(new String(eoNo));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdExchangeOrderManager#savePdExchangeOrder(PdExchangeOrder
	 *      pdExchangeOrder)
	 */
	public void savePdExchangeOrder(PdExchangeOrder pdExchangeOrder) {
		//modify by fu 2016-05-06        
		dao.savePdExchangeOrder(pdExchangeOrder);
		//modify by fu 2016-05-06
		
		
		//modify by fu 2016-04-07 自助换货单的后台审核-判断自助换货单是否需要支付及支付的金额------begin
		Integer orderFlag = pdExchangeOrder.getOrderFlag();
		if((null!=orderFlag)&&(orderFlag==1)){
			String selfReplacement = pdExchangeOrder.getSelfReplacement();
			if((!StringUtil.isEmpty(selfReplacement))&&("Y".equals(selfReplacement))){
				pdExchangeOrder.setSelfCheckStatus("Y");//自助换货单审核状态：Y审核通过，N审核不通过，空表示初始化制单的时候不用赋值
				pdExchangeOrder = this.getHandleNeedPay(pdExchangeOrder);
			}
		}
		//modify by fu 2016-04-07 自助换货单的后台审核-判断自助换货单是否需要支付及支付的金额------end
		
		dao.savePdExchangeOrder(pdExchangeOrder);
	}

	/**
	 * 判断自助换货单是否需要支付及支付的金额
	 * @author fu 2016-04-07
	 * @param pdExchangeOrder
	 * @return PdExchangeOrder
	 */
	private PdExchangeOrder getHandleNeedPay(PdExchangeOrder pdExchangeOrder) {
			BigDecimal amountEx = pdExchangeOrder.getAmountEx();
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(pdExchangeOrder.getOrderNo());
		    if(null!=jpoMemberOrder){
		    	BigDecimal amount = jpoMemberOrder.getAmount();
		    	if((null!=amountEx)&&(null!=amount)){
		    		BigDecimal amountDifference = amountEx.subtract(amount);//差价=换货单总金额-原订单的总金额
		    		int compareResult = amountDifference.compareTo(new BigDecimal(0));
		    		if(compareResult==1){
		    			pdExchangeOrder.setNeedPay("Y");//自助换货单需要支付
		    			pdExchangeOrder.setNeedPayAmount(amountDifference);///自助换货单需要支付的金额
		    		}else{
		    			pdExchangeOrder.setNeedPay("N");//自助换货单不需要支付
		    		}
		    	}
		    }
		return pdExchangeOrder;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdExchangeOrderManager#removePdExchangeOrder(String
	 *      eoNo)
	 */
	public void removePdExchangeOrder(final String eoNo) {
		dao.removePdExchangeOrder(new String(eoNo));
	}

	// added for getPdExchangeOrdersByCrm
	
	public List getPdExchangeOrdersByCrm(CommonRecord crm, Pager pager) {
		return dao.getPdExchangeOrdersByCrm(crm, pager);
	}

	public List getTotalPdExchangeOrder(CommonRecord crm) {
		//list 里面元素对象包含4个属性:BProductNo，DProductNo，bqty，dqty
		return dao.getTotalPdExchangeOrder(crm);
	}
	
	public List getTotalPdExchangeOrder2(CommonRecord crm){
		//list 里面元素对象包含3个属性，FProductNo,bqty，dqty
		return dao.getTotalPdExchangeOrder2(crm);
	}
	
	public List getPdExchangeOrdersByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("orderNo", orderNo);
			crm.setValue("orderFlag", 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao.getPdExchangeOrdersByCrm(crm, null);
	}

	/**
	 * 换货单新增
	 * @author gw 2015-05-29
	 * 
	 */
	 public PdExchangeOrder pdExchangeOrderAdd(PdExchangeOrder pdExchangeOrder) {
			pdExchangeOrder.setCreateTime(new Date());
			pdExchangeOrder.setOrderFlag(-1);
			pdExchangeOrder.setStockFlag("0");
		    dao.savePdExchangeOrder(pdExchangeOrder);
            return pdExchangeOrder;
      }

	public void addPdExchangeOrder(PdExchangeOrder pdExchangeOrder)
			throws Exception {
		// TODO Auto-generated method stub
		dao.savePdExchangeOrder(pdExchangeOrder);
		String eoNo = pdExchangeOrder.getEoNo();
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao
				.getJpoMemberOrderByMemberOrderNo(pdExchangeOrder.getOrderNo());
		Set jpoMemberOrderDetails = jpoMemberOrder.getJpoMemberOrderList();
		Iterator iterator = jpoMemberOrderDetails.iterator();
		while (iterator.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) iterator
					.next();
			PdExchangeOrderBack pdExchangeOrderBack = new PdExchangeOrderBack();
			pdExchangeOrderBack.setEoNo(eoNo);
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*pdExchangeOrderBack.setProductNo(jpoMemberOrderList
					.getJpmProductSale().getJpmProduct().getProductNo());*/
			pdExchangeOrderBack.setProductNo(jpoMemberOrderList
					.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
			pdExchangeOrderBack.setOriginalQty(new Long(jpoMemberOrderList
					.getQty()));
			pdExchangeOrderBack.setPrice(jpoMemberOrderList.getPrice());
			pdExchangeOrderBack.setPv(jpoMemberOrderList.getPv());
			pdExchangeOrderBack.setQty(new Long(jpoMemberOrderList
					.getQty()));
			
			//modify gw 2014-11-21 添加ERP商品编码和ERP商品价格
			pdExchangeOrderBack.setErpProductNo(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getErpProductNo());
					
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*if (!"1".equals(jpoMemberOrderList.getJpmProductSale()
					.getChangeabledFlag())) {*/
			if (!"1".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getChangeabledFlag())) {
				pdExchangeOrderBackManager
						.savePdExchangeOrderBack(pdExchangeOrderBack);
			}

		}

		List products = jpmProductSaleManager.getExProductSales(jpoMemberOrder
				.getCompanyCode(), jpoMemberOrder.getOrderType(), jpoMemberOrder.getTeamCode());//默认取值中脉

		for (int i = 0; i < products.size(); i++) {
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*JpmProductSale jpmProductSale = (JpmProductSale) products.get(i);*/
			JpmProductSaleTeamType jpmProductSaleTeamType = (JpmProductSaleTeamType) products.get(i);
			PdExchangeOrderDetail pdExchangeOrderDetail = new PdExchangeOrderDetail();
			pdExchangeOrderDetail.setEoNo(eoNo);
			pdExchangeOrderDetail.setQty(new Long(0));
			pdExchangeOrderDetail.setProductNo(jpmProductSaleTeamType.getJpmProductSaleNew().getJpmProduct()
					.getProductNo());
			pdExchangeOrderDetail.setPrice(jpmProductSaleTeamType.getPrice());
			pdExchangeOrderDetail.setPv(jpmProductSaleTeamType.getPv());
			
			//modify gw 2014-11-21 添加ERP商品编码和ERP商品价格
			pdExchangeOrderDetail.setErpProductNo(jpmProductSaleTeamType.getJpmProductSaleNew().getJpmProduct().getErpProductNo());
				
			switch (Integer.parseInt(jpoMemberOrder.getOrderType())) {
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*case 1: // 会员首购单
				pdExchangeOrderDetail.setPrice(jpmProductSale.getFpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getFpPv());
				break;
			case 2:// 会员升级单
				pdExchangeOrderDetail.setPrice(jpmProductSale.getFpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getFpPv());
				break;

			case 4:// 会员重消单
				pdExchangeOrderDetail.setPrice(jpmProductSale.getMpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getMpPv());
				break;
			case 3:// 会员返单
				pdExchangeOrderDetail.setPrice(jpmProductSale.getMpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getMpPv());
				break;
			case 5:// 辅销品订单
				pdExchangeOrderDetail.setPrice(jpmProductSale.getFpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getFpPv());
				break;
			case 6:// 店铺首购单
				pdExchangeOrderDetail
						.setPrice(jpmProductSale.getStoreFpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getStoreFpPv());
				break;
			case 9:// 店铺重消单
				pdExchangeOrderDetail
						.setPrice(jpmProductSale.getStoreMpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getStoreMpPv());
				break;
			case 11:// 二级店铺首购单
				pdExchangeOrderDetail.setPrice(jpmProductSale
						.getSubstoreFpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getSubstoreFpPv());
				break;
			case 12:// 二级店铺升级单
				pdExchangeOrderDetail.setPrice(jpmProductSale
						.getSubstoreFpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getSubstoreFpPv());
				break;
			case 14:// 二级店铺重消单
				pdExchangeOrderDetail.setPrice(jpmProductSale
						.getSubstoreMpPrice());
				pdExchangeOrderDetail.setPv(jpmProductSale.getSubstoreMpPv());
				break;*/
			}
			
			PdExchangeOrderDetail PdExchangeOrderDetailTwo =  pdExchangeOrderDetailManager.getPdExchangeOrderDetailForEP(pdExchangeOrderDetail);
			if(null==PdExchangeOrderDetailTwo){
				pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail);
			}
			
		}

	}

	public void conformPdExchangeOrder(PdExchangeOrder pdExchangeOrder) {
		try{
				if (!"1".equals(pdExchangeOrder.getStockFlag())) {
					pdExchangeOrder.setStockFlag("1");
					/*
					 * if("1".endsWith(flag)){
					 * pdAgentStockManager.updatePdAgentStocks(pdReturnPurchase); }
					 */
					dao.savePdExchangeOrder(pdExchangeOrder);
					pdWarehouseStockManager.updatePdWarehouseStock(pdExchangeOrder);
					
					//modify by fu 2016-01-01 将接口推送的消息放到最后推送
				    pdSendInfoManager.doWhileOrderConfirmed(pdExchangeOrder);
				    //modify gw 2014-11-07 因报单中心没有真实的库存，所以这个地方隐藏起来
					//modify gw 2014-11-25 需求变更，又重新需要库存
					// pdShipmentsDetailManager.addPdShipmentsDetailsByOrder(pdExchangeOrder);
					// pdAgentStockManager.updatePdAgentStocks(pdReturnPurchase);
			   }
		}catch(Exception e){
			e.printStackTrace();
			log.info("在PdExchangeOrderManagerImpl类的conformPdExchangeOrder方法中运行，换货单确认时发生异常"+e.toString());
		}
	}


	@Override
	public List getPdExchangeOrderBackStaticsCheckedCompany(String createBTime,
			String createETime, String companyCode, String productType) {
		return dao.getPdExchangeOrderBackStaticsCheckedCompany(createBTime,createETime, companyCode, productType);
	}
	
	@Override
	public List getPdExchangeOrderDetailStaticsCheckedCompany(String createBTime,
			String createETime, String companyCode, String productType) {
		return dao.getPdExchangeOrderDetailStaticsCheckedCompany(createBTime,createETime, companyCode, productType);
	}


	/**
	 * 自助换货单推送接口消息
	 * @author fu 2016-04-08
	 * @param pdExchangeOrder
	 */
	public void getSendpdExchangeOrder(PdExchangeOrder pdExchangeOrder){
		//解决HQL查询获取不到pd_exchange_order表的pd_exchange_order_back对象时报错:com.sun.jdi.InvocationException occurred invoking method.--begin
		//重新查询
		pdExchangeOrder = dao.getPdExchangeOrder(pdExchangeOrder.getEoNo());
		//解决HQL查询获取不到pd_exchange_order表的pd_exchange_order_back对象时报错:com.sun.jdi.InvocationException occurred invoking method.--end

		log.info("在类PdExchangeOrderManagerImpl的方法getSendpdExchangeOrder中运行：自助换货接口推送消息开始准备数据");
		Reship reship = this.getReshipECToOms(pdExchangeOrder);
		//换货单时的发货信息----------------end
		//将java对象转换成json对象
		JSONObject jsonObject = JSONObject.fromObject(reship);
		//将json对象转换成json字符串
		String returnnoJsonString = jsonObject.toString();
		log.info("在类PdExchangeOrderManagerImpl的方法getSendpdExchangeOrder中运行：自助换货接口推送消息准备数据完成");
		//modify gw 2014-11-26  换货单确认时准备数据------------------------------end
		//---------------调用接口，将数据推送给OMS
		//调用发送接口----modify gw 2014-12-23----开始
		MsgSendService msgSendService = new MsgSendService();

		msgSendService.setSender(Constants.OMS_SEND);//OMS平台

		//方法名-----OMS那边的接口名称
		String methodName = "ship.reshipAdd";
		
		// modify by fu 2017-1-18 注释掉自助换货单推送到后续系统的接口方法
		
	    /*String aa = msgSendService.post(returnnoJsonString, methodName);
        //调用发送接口----modify gw 2014-12-23----结束
		log.info("在类PdExchangeOrderManagerImpl的方法getSendpdExchangeOrder中运行：JOCS向OMS推送自助换货单数据完成");
		//=============================接口日志写入开始 Modify By WUCF 20150123
		JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
		jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
		jocsInterfaceLog.setSender(Constants.OMS_SEND);//数据的接收方
		jocsInterfaceLog.setMethod(methodName);//方法名称
		jocsInterfaceLog.setContent(returnnoJsonString.toString());//发送内容content
		jocsInterfaceLog.setReturnDesc(aa);//返回内容
		
		ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
		//=============================接口日志写入完毕
*/		
	}
	
	/**
	 * 自助换货单接口数据对象reship准备
	 * @author fu 2016-04-08
	 * @param pdExchangeOrder
	 */
	private Reship getReshipECToOms(PdExchangeOrder pdExchangeOrder){
		log.info("在类PdExchangeOrderManagerImpl的方法getReshipECToOms中运行：自助换货单接口数据对象reship准备-开始");
		Reship reship = new Reship();
		reship.setOrder_bn(pdExchangeOrder.getOrderNo());
		reship.setOrder_bn_ex(pdExchangeOrder.getEoNo());
		
		//换货单时的退货信息-----------------begin
		Set pdExchangeOrderBacks = pdExchangeOrder.getPdExchangeOrderBacks();
		Iterator iteratorOne = pdExchangeOrderBacks.iterator();
		while (iteratorOne.hasNext()) {
			PdExchangeOrderBack pdExchangeOrderBack = (PdExchangeOrderBack) iteratorOne.next();
			Returno_items returno_items = new Returno_items();
			returno_items.setGoods_bn(pdExchangeOrderBack.getProductNo());
			returno_items.setName(jpmProductManager.getJpmProduct(pdExchangeOrderBack.getProductNo()).getProductName());
			returno_items.setErp_goods_bn(pdExchangeOrderBack.getErpProductNo());
			returno_items.setNums(null==pdExchangeOrderBack.getQty()? 0:pdExchangeOrderBack.getQty().intValue());
			returno_items.setPrice(null==pdExchangeOrderBack.getPrice()?0:(pdExchangeOrderBack.getPrice().doubleValue()));
			reship.getReturno_items().add(returno_items);
		}
		//换货单时的退货信息----------------end
		log.info("在类PdExchangeOrderManagerImpl的方法getReshipECToOms中运行：自助换货单退货信息");

		//换货单时的发货信息----------------begin
		//查询换货单对应已经生成几张发货单
		List<PdSendInfo> pdSendInfoList = pdSendInfoManager.getPdSendInfoList(pdExchangeOrder.getEoNo());
		if((null!=pdSendInfoList)&&(pdSendInfoList.size()>0)){
			for(int i=0;i<pdSendInfoList.size();i++){
				PdSendInfo pdSendInfo = pdSendInfoList.get(i);
				Delivery delivery = new Delivery();
				delivery.setLo_bn(pdSendInfo.getSiNo());
				//这个是物流公司名称，还是物流公司编号----?? JOCS这边传递的是物流公司编号
				delivery.setLogistics(pdSendInfo.getShNo());
				//这个是仓库编号，还是仓库名称----?? JOCS这边传递的是仓库编号
				delivery.setBranch(pdSendInfo.getWarehouseNo());
				//modify by fu 2016-1-13 换货单对应的发货单的收货人信息----begin			
				delivery.setConsignee(pdSendInfo.getRecipientName());//收货人
				delivery.setConsignee_state(pdSendInfo.getRecipientCaNo());//收货省        
				delivery.setConsignee_city(pdSendInfo.getCity());//城市string
				delivery.setConsignee_area(pdSendInfo.getDistrict());//地区
				delivery.setConsignee_address(pdSendInfo.getRecipientAddr());//详细地址
				delivery.setConsignee_zip(pdSendInfo.getRecipientZip());//邮编
				delivery.setConsignee_mobile(pdSendInfo.getRecipientPhone());//手机
				delivery.setConsignee_phone(pdSendInfo.getRecipientMobile());//电话
				//modify by fu 2016-1-13 换货单对应的发货单的收货人信息----end
				List list = pdSendInfoDetailDao.getPdSendInfoInterFaceList(pdSendInfo.getSiNo());
				if(null!=list){
						for(int p=0;p<list.size();p++){
							PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) list.get(p);
							if(pdSendInfoDetail.getQty()>0){
								Delivery_items delivery_items = new Delivery_items();
								delivery_items.setGoods_bn(pdSendInfoDetail.getProductNo());
								delivery_items.setName(jpmProductManager.getJpmProduct(pdSendInfoDetail.getProductNo()).getProductName());
								delivery_items.setErp_goods_bn(pdSendInfoDetail.getErpProductNo());
								delivery_items.setNums(null==pdSendInfoDetail.getQty()? 0:pdSendInfoDetail.getQty().intValue());
								delivery_items.setPrice(null==pdSendInfoDetail.getPrice()?0:(pdSendInfoDetail.getPrice().doubleValue()));
								delivery_items.setCombination_product_no(pdSendInfoDetail.getCombinationProductNo());
								delivery.getDelivery_items().add(delivery_items);
							}
						}
				}
				log.info("在类PdExchangeOrderManagerImpl的方法getReshipECToOms中运行：自助换货单发货信息");
				reship.getDeliverys().add(delivery);
			}
		}
		log.info("在类PdExchangeOrderManagerImpl的方法getReshipECToOms中运行：自助换货单接口数据对象reship准备-结束");
		return reship;
	}
	
	/**
	 * 查询未推送到后续系统的自助换货单
	 * @author fu 2016-04-12
	 * @return
	 */
	public List<PdExchangeOrder> getNotSendPdExchangeOrder(){
		return dao.getNotSendPdExchangeOrder();
	}
	
	/**
	 * 将自助换货单状态改为已推送状态
	 * @author fu 2016-04-12
	 * @param eoNo
	 * @return
	 */
	public void reSetpdExchangeOrderSendStatus(String eoNo){
		dao.reSetpdExchangeOrderSendStatus(eoNo);
	}
	
	/**
	 * 取消自助换货单
	 * @author fu 2016-04-12
	 * @param eoNo 自助换货单号
	 * @return string
	 */
	public String cancelExchangeY(String eoNo){
		//只有未提交、未审核的自助换货单才允许取消自助换货-------begin
		//modify by fu 2016-04-25
		Integer orderFlag = dao.getPdExchangeOrderOrderFlag(eoNo);
		if((null==orderFlag)||orderFlag>=1){
			return "ysh";//已审核\已支付的自助换货单不能取消自助换货单！
		}
		//只有未提交、未审核的自助换货单才允许取消自助换货-------end
		
		String ce = this.getCheckIsOrNocancelExchange(eoNo);
		if("yzf".equals(ce)){
			return ce;//已支付的自助换货单不能取消自助换货单！
		}else if("yscfhd".equals(ce)){
			return ce;//已生成发货单的自助换货单不能取消自助换货单！
		}else{
		    return dao.reSetCancelExchange(eoNo,"Y");
		}
	}
	
	/**
	 * 恢复自助换货单
	 * @author fu 2016-04-12
	 * @param eoNo 自助换货单号
	 * @return string
	 */
	public String cancelExchangeN(String eoNo){
		return dao.reSetCancelExchange(eoNo,"");//恢复自助换货单的同时，将自助换货单变成未审核状态；
	}
	
	/**
	 * 判断是否可以取消自助换货单
	 * @author fu 2016-04-12
	 * @param eoNo 自助换货单号
	 * @return string
	 */
	public String getCheckIsOrNocancelExchange(String eoNo){
		PdExchangeOrder pdExchangeOrder = dao.getPdExchangeOrder(eoNo);
		//判断是否可以取消自助换货单:需要支付的自助换货单在已支付情况下不能取消自助换货单;不需要支付的自助换货单在已生成发货单的情况下不能取消自助换货单---begin
		String needPay =  pdExchangeOrder.getNeedPay();
		//需要支付的自助换货单
		if((!StringUtil.isEmpty(needPay))&&("Y".equals(needPay))){
			String isPay = pdExchangeOrder.getIsPay();
			if((!StringUtil.isEmpty(isPay))&&("Y".equals(isPay))){//已支付的自助换货单不能取消自助换货单！
				return "yzf";
			}
		}
		//不需要支付的自助换货单
		else{
			String whetherPd = pdExchangeOrder.getWhetherPd();
			if((!StringUtil.isEmpty(whetherPd))&&(("1".equals(whetherPd))||("2".equals(whetherPd)))){//已生成发货单的自助换货单不能取消自助换货单！
				return "yscfhd";
			}
		}
		//判断是否可以取消自助换货单:需要支付的自助换货单在已支付情况下不能取消自助换货单;不需要支付的自助换货单在已生成发货单的情况下不能取消自助换货单---end
		return "canCancel";
	}
	
	/**
	 * 
	 * @param orderType	
	 * @return	orderTypeDesc
	 */
	@Override
	 public String getOrderTypeDescByOrderType(String orderType){
		 String desc = "";
		 switch (Integer.parseInt(orderType)) {
			
			case 1: // 会员首购单
				desc ="会员首购单";
				break;
			case 2:// 会员升级单
				desc = "会员升级单";
				break;
			case 3://会员续约单
				desc = "会员续约单";
				break;
			case 4:// 会员重消单
				desc = "会员重消单";
				break;
			case 5:// 辅销品订单
				desc ="辅销品订单";
				break;
			case 6:// 店铺首购单
				desc = "一级店铺首购单";
				break;
			case 9:// 店铺重消单
				desc = "一级店铺重消单";
				break;
			case 11:// 二级店铺首购单
				desc = "二级店铺首购单";
				break;
			case 12:// 二级店铺升级单
				desc = "二级店铺升级单";
				break;
			case 14:// 二级店铺重消单
				desc = "二级店铺重消单";
				break;
			case 21://梦想馆首单
				desc = "梦想馆首单";
				break;
			case 24://梦想馆重消单
				desc = "梦想馆重消单";
				break;
			case 30://积分换购
				desc = "积分换购单";
				break;
			case 40://全年重消
				desc = "全年重消单";
				break;
			case 32 ://奖衔补单
				desc = "奖衔补单";
				break;
			default :
				desc = "";
				break;
		 }
		 return desc;
	}
	

}