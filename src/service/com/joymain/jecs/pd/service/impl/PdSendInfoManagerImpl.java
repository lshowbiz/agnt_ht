package com.joymain.jecs.pd.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFactory;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.mail.SimpleMailMessage;

import service.MsgSendService;

import com.ibm.icu.util.Calendar;
import com.integracoreb2b.ArrayOfOrder;
import com.integracoreb2b.ArrayOfOrderDetail;
import com.integracoreb2b.ArrayOfOrderResult;
import com.integracoreb2b.Order;
import com.integracoreb2b.OrderDetail;
import com.integracoreb2b.OrderResult;
import com.integracoreb2b.OrderResults;
import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.am.service.JamMsnModuleManager;
import com.joymain.jecs.pd.dao.PdSendInfoDao;
import com.joymain.jecs.pd.dao.PdSendInfoDetailDao;
import com.joymain.jecs.pd.dao.PdWarehouseDao;
import com.joymain.jecs.pd.model.Delivery;
import com.joymain.jecs.pd.model.Delivery_items;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.model.PdExchangeOrderDetail;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.model.PdReturnSend;
import com.joymain.jecs.pd.model.PdReturnSendProduct;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.model.PdSendInfoEdit;
import com.joymain.jecs.pd.model.PdSendInfoStatus;
import com.joymain.jecs.pd.model.PdShipStrategyDetail;
import com.joymain.jecs.pd.model.Reship;
import com.joymain.jecs.pd.model.Returno_items;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pd.service.PdLogisticsBaseManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pd.service.PdShipStrategyDetailManager;
import com.joymain.jecs.pd.service.PdShipmentsDetailManager;
import com.joymain.jecs.pd.service.PdWarehouseAreaManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.model.JocsInterfaceRetransmission;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.model.JpmSmssendInfo;
import com.joymain.jecs.pm.service.JocsInterfaceRetransmissionManager;
import com.joymain.jecs.pm.service.JpmProductCombinationManager;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.pm.service.JpmProductSaleNewManager;
import com.joymain.jecs.pm.service.JpmProductSaleTeamTypeManager;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.UpsInteractiveLog;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.sys.service.UpsInteractiveLogManager;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.jms.JMSContextUtil;
import com.joymain.jecs.util.json.InterfaceJsonUtil;
import com.joymain.jecs.util.mail.MailEngine;
import com.joymain.jecs.util.smssend.SmsSend;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.ups.AccessUtil;
import com.joymain.jecs.util.ups.RateUtil;
import com.joymain.jecs.util.ups.ShipAcceptUtil;
import com.joymain.jecs.util.ups.ShipConfirmUtil;
import com.joymain.jecs.util.ups.VoidUtil;
import com.smsgate.server.jms.model.SmsSendLog;
import com.ups.jaxb.access.request.AccessRequest;
import com.ups.jaxb.rate.request.RatingServiceSelectionRequest;
import com.ups.jaxb.rate.response.RatedShipmentType;
import com.ups.jaxb.rate.response.RatingServiceSelectionResponse;
import com.ups.jaxb.shipaccept.request.ShipmentAcceptRequest;
import com.ups.jaxb.shipaccept.response.PackageResults;
import com.ups.jaxb.shipaccept.response.ShipmentAcceptResponse;
import com.ups.jaxb.shipconfirm.request.ShipmentConfirmRequest;
import com.ups.jaxb.shipconfirm.response.ShipmentConfirmResponse;
import com.ups.jaxb.voidshipment.request.VoidShipmentRequest;
import com.ups.jaxb.voidshipment.response.VoidShipmentResponse;
import com.ups.util.UpsConnection;
import com.ups.util.XmlTool;
 
public class PdSendInfoManagerImpl extends BaseManager implements
		PdSendInfoManager {
	private static final String lock = "lock";
	private JamMsnModuleManager jamMsnModuleManager;

	private JpmProductSaleManager jpmProductSaleManager;
	private JpmProductSaleNewManager jpmProductSaleNewManager;
	private AlDistrictManager alDistrictManager;
	private PdSendInfoDao dao;
	private SysIdManager sysIdManager; 
	private SysUserManager sysUserManager;
	private PdWarehouseDao pdWarehouseDao;
	private PdSendInfoDetailDao pdSendInfoDetailDao;
	private PdWarehouseAreaManager pdWarehouseAreaManager;
	private PdShipmentsDetailManager pdShipmentsDetailManager; 
	private PdWarehouseStockManager pdWarehouseStockManager;
	private AlCityManager alCityManager;
	private AlStateProvinceManager alStateProvinceManager;
	private UpsInteractiveLogManager upsInteractiveLogManager;
	private JpoMemberOrderDao jpoMemberOrderDao;
	protected MailEngine mailEngine = null;
	
	private JpmProductManager jpmProductManager;
	private JpmProductCombinationManager jpmProductCombinationManager;
	private JpmProductSaleTeamTypeManager jpmProductSaleTeamTypeManager;
	
	private PdLogisticsBaseManager pdLogisticsBaseManager;
	private JocsInterfaceRetransmissionManager jocsInterfaceRetransmissionManager;
	
	/**
	 * @param pdShipStrategyDetailManager the pdShipStrategyDetailManager to set
	 */
	public void setPdShipStrategyDetailManager(
			PdShipStrategyDetailManager pdShipStrategyDetailManager) {
		this.pdShipStrategyDetailManager = pdShipStrategyDetailManager;
	}

	public JocsInterfaceRetransmissionManager getJocsInterfaceRetransmissionManager() {
		return jocsInterfaceRetransmissionManager;
	}



	public void setJocsInterfaceRetransmissionManager(
			JocsInterfaceRetransmissionManager jocsInterfaceRetransmissionManager) {
		this.jocsInterfaceRetransmissionManager = jocsInterfaceRetransmissionManager;
	}

	protected SimpleMailMessage mailMessage = null;
	private PdShipStrategyDetailManager pdShipStrategyDetailManager = null;
	
	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}

	

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}



	public void setUpsInteractiveLogManager(
			UpsInteractiveLogManager upsInteractiveLogManager) {
		this.upsInteractiveLogManager = upsInteractiveLogManager;
	}

	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}

	public void setJamMsnModuleManager(JamMsnModuleManager jamMsnModuleManager) {
		this.jamMsnModuleManager = jamMsnModuleManager;
	}

	public void setJpmProductSaleManager(
			JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}
	
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setPdWarehouseDao(PdWarehouseDao pdWarehouseDao) {
		this.pdWarehouseDao = pdWarehouseDao;
	}

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void setPdShipmentsDetailManager(
			PdShipmentsDetailManager pdShipmentsDetailManager) {
		this.pdShipmentsDetailManager = pdShipmentsDetailManager;
	}

	
	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
	
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public void setJpmProductCombinationManager(
			JpmProductCombinationManager jpmProductCombinationManager) {
		this.jpmProductCombinationManager = jpmProductCombinationManager;
	}


	public void setJpmProductSaleTeamTypeManager(
			JpmProductSaleTeamTypeManager jpmProductSaleTeamTypeManager) {
		this.jpmProductSaleTeamTypeManager = jpmProductSaleTeamTypeManager;
	}

	public void setPdLogisticsBaseManager(
			PdLogisticsBaseManager pdLogisticsBaseManager) {
		this.pdLogisticsBaseManager = pdLogisticsBaseManager;
	}
	
	public void setPdWarehouseAreaManager(
			PdWarehouseAreaManager pdWarehouseAreaManager) {
		this.pdWarehouseAreaManager = pdWarehouseAreaManager;
	}

	public void setPdSendInfoDetailDao(PdSendInfoDetailDao pdSendInfoDetailDao) {
		this.pdSendInfoDetailDao = pdSendInfoDetailDao;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	
	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdSendInfoDao(PdSendInfoDao dao) {
		this.dao = dao;
	}

	public List getShippingReportList(CommonRecord crm) {
		// TODO Auto-generated method stub
		/*
		 * String companyCode = crm.getString("companyCode");
		 * if("CN".equals(companyCode)){ return
		 * this.getCnShippingReportList(crm); }
		 */
		return dao.getShippingReportList(crm);
	}

	private List getTwShippingReportList(CommonRecord crm) {
		List retList = new ArrayList();
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		/*Map jpmProductSaleMap = jpmProductSaleManager
				.getCompanyProductMap("TW");
		Map statMap = alStateProvinceManager
				.getAlStateProvincesMapByCountry("TW");
		Map cityMap = alCityManager.getAlCityMap("TW");
		List list = dao.getPdSendInfosByCrm(crm, null);
		for (int i = 0; i < list.size(); i++) {
			Map retMap = new HashMap();
			PdSendInfo pdSendInfo = (PdSendInfo) list.get(i);
			// retMap.put("pdSendInfo", pdSendInfo);
			retMap.put("siNo", pdSendInfo.getSiNo());
			retMap.put("orderNo", pdSendInfo.getOrderNo());
			retMap.put("checkTime", pdSendInfo.getCheckTime());
			retMap.put("customerCode", pdSendInfo.getCustomer().getUserCode());
			retMap.put("customerName", pdSendInfo.getCustomer().getUserName());
			retMap.put("shipTo", pdSendInfo.getRecipientName());
			retMap.put("zipCode", pdSendInfo.getRecipientZip());

			retMap.put("state", statMap.get(pdSendInfo.getRecipientCaNo()));
			retMap.put("city", cityMap.get(pdSendInfo.getCity()));
			retMap.put("address", pdSendInfo.getRecipientAddr());
			retMap.put("phone", pdSendInfo.getRecipientPhone());
			retMap.put("mobile", pdSendInfo.getRecipientMobile());
			retMap.put("email", pdSendInfo.getEmail());

			Set set = pdSendInfo.getPdSendInfoDetails();
			Iterator iterator = set.iterator();
			StringBuffer detail = new StringBuffer();
			while (iterator.hasNext()) {
				PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator
						.next();
				detail.append(pdSendInfoDetail.getProductNo()
						+ jpmProductSaleMap
								.get(pdSendInfoDetail.getProductNo()) + "("
						+ pdSendInfoDetail.getQty() + ") ");
			}
			retMap.put("detail", detail.toString());
			retList.add(retMap);
		}*/
		return retList;
		
	}
	
	@SuppressWarnings("rawtypes")
	private List getCnShippingReportList(CommonRecord crm) {
		log.info("在类PdSendInfoManagerImpl的方法getCnShippingReportList中开始运行");
		List retList = new ArrayList();
		try{
		
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		Map jpmProductSaleMap = jpmProductSaleNewManager
				.getCompanyProductMap("CN");
		Map statMap = alStateProvinceManager
				.getAlStateProvincesMapByCountry("CN");
		Map cityMap = alCityManager.getAlCityMap("CN");
		log.info("在类PdSendInfoManagerImpl的方法getCnShippingReportList中运行---开始查询地区数据");
		log.info("在类PdSendInfoManagerImpl的方法getCnShippingReportList中运行-------定位1111111111111111");
		List list = dao.getPdSendInfosReport(crm, null);
		
		log.info("在类PdSendInfoManagerImpl的方法getCnShippingReportList中开始运行，发货报表基础数据查询完毕");

		//Modify By WuCF 判断结果集合是否超过限度，则直接返回！
		if(list!=null && list.size()==1 && "1".equals(list.get(0).toString())){
			return list;
		}
		
		for (int i = 0; i < list.size(); i++) {
			Map retMap = new HashMap();
			PdSendInfo pdSendInfo = (PdSendInfo) list.get(i);
			// retMap.put("pdSendInfo", pdSendInfo);
			retMap.put("siNo", pdSendInfo.getSiNo());//);
			retMap.put("orderNo", pdSendInfo.getOrderNo());
			retMap.put("checkTime", pdSendInfo.getCheckTime());
			retMap.put("customerCode", pdSendInfo.getCustomer().getUserCode());
			retMap.put("customerName", pdSendInfo.getCustomer().getUserName());//Modify BY WuCF 
			retMap.put("shipTo", pdSendInfo.getRecipientName()); 
			retMap.put("zipCode", pdSendInfo.getRecipientZip());

			retMap.put("state", statMap.get(pdSendInfo.getRecipientCaNo()));
			retMap.put("city", cityMap.get(pdSendInfo.getCity()));
			if(!StringUtil.isEmpty(pdSendInfo.getDistrict())){
			    AlDistrict alDistrict  = alDistrictManager.getAlDistrict(pdSendInfo.getDistrict());
			    if(null!=alDistrict){
			    	retMap.put("district", alDistrict.getDistrictName());//modify by fu 2016-11-23 添加区
			    }
			}
			log.info("在类PdSendInfoManagerImpl的方法getCnShippingReportList中开始运行，地区赋值结束"+pdSendInfo.getDistrict());
			retMap.put("address", pdSendInfo.getRecipientAddr());
			retMap.put("phone", pdSendInfo.getRecipientPhone());
			retMap.put("mobile", pdSendInfo.getRecipientMobile());
			retMap.put("email", pdSendInfo.getEmail());
			retMap.put("codFlag", pdSendInfo.getCodFlag());
			retMap.put("hurryFlag", pdSendInfo.getHurryFlag());
			
			
			Set set = pdSendInfo.getPdSendInfoDetails();
			Iterator iterator = set.iterator();
			StringBuffer detail = new StringBuffer();
			PdSendInfoDetail pdSendInfoDetail = null;//Modify By WuCF 20140515 对象生成一个即可
			while (iterator.hasNext()) {
				pdSendInfoDetail = (PdSendInfoDetail) iterator
						.next();
				detail.append(pdSendInfoDetail.getProductNo()
						+ ((JpmProductSaleNew)jpmProductSaleMap
								.get(pdSendInfoDetail.getProductNo())).getProductName() + "("
						+ pdSendInfoDetail.getQty() + ") ");
			}
			retMap.put("detail", detail.toString());
			log.info("retMap is :------------"+retMap.toString());
			retList.add(retMap);
		}
		return retList;
		}catch(Exception e){
			log.info("在类PdSendInfoManagerImpl的方法getCnShippingReportList中开始运行报错"+e.toString());
			e.printStackTrace();
			return retList;
		}
	}
	
	public static String dealStr(String myString){ 
		if(myString==null){
			myString = "";
		}
        String newString=null;  
        Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");  
        Matcher m = CRLF.matcher(myString);  
        if (m.find()) {  
          newString = m.replaceAll("");  
        }  
        return newString;  
    }  

	public void doWhileOrderConfirmed(JpoMemberOrder order) {
		jpoMemberOrderDao.saveJpoMemberOrder(order);//修改jpoMemberOrder的状态后，将其保存
		
		pdShipmentsDetailManager.addPdShipmentsDetailsByOrder(order);
		this.autoSaveShipInfo(order);
		/**@author 罗婷
		 * 将审核成功后发邮件的方法提到了JpoMemberOrderCheckController中
		 */
	/*	try {
			if("US".equals(order.getCompanyCode())){
				this.sendEmail(order);
			}
			
		} catch (Exception e) {
			log.debug("send email error...>>"+order.getMemberOrderNo());
		}*/
	}

	public void doWhileOrderConfirmed(PdReturnPurchase order) {
		// TODO Auto-generated method stub
		pdShipmentsDetailManager.addPdShipmentsDetailsByOrder(order);
		this.autoCreatePdSendInfo(order);
	}

	public void doWhileOrderConfirmed(PdExchangeOrder pdExchangeOrder) throws Exception {
		// TODO Auto-generated method stub
		pdShipmentsDetailManager.addPdShipmentsDetailsByOrder(pdExchangeOrder);
		this.autoCreatePdSendInfo(pdExchangeOrder);
	}

	private String autoCreatePdSendInfo(PdExchangeOrder order) throws Exception{

		log.info("换货单确认时生成发货单（在类PdSendInfoManagerImpl的autoCreatePdSendInfo方法中运行）：----------------开始");
		PdSendInfo pdSendInfo = new PdSendInfo();
		
				// TODO Auto-generated method stub
		
				// TODO Auto-generated method stub
				Set set = order.getPdExchangeOrderDetails();
				Iterator iterator = set.iterator();
				
				pdSendInfo.setOrderType("3");
				pdSendInfo.setCompanyCode(order.getCompanyCode());
				pdSendInfo.setSiNo(sysIdManager.buildIdStrTwo("sino"));//modify by fu 2016-03-17 换货单对应的发货单编号用新的序列生成器
				pdSendInfo.setCompanyCode(order.getCompanyCode());
				pdSendInfo.setAmount(new BigDecimal(0));
				pdSendInfo.setShipType(order.getShipType());
				// pdSendInfo.setShNo(shNo);//需要匹配
		
				/*
				 * String warehouseNo =
				 * pdWarehouseAreaManager.getPdWarehouseNo(order.getCompanyCode(),
				 * order.getProvince(),pdSendInfo.getShNo());
				 * 
				 * if(StringUtils.isEmpty(warehouseNo)){
				 * warehouseNo=Constants.sysConfigMap
				 * .get(order.getCompanyCode()).get("pd.autoship.warehouseno"); }
				 */
				pdSendInfo.setWarehouseNo(order.getWarehouseNo());// 需要匹配
				pdSendInfo.setShNo(pdWarehouseDao.getPdWarehouse(
						order.getWarehouseNo()).getShNo());
				pdSendInfo.setCustomer(order.getCustomer());
				// pdSendInfo.setRecipientName(pdSendInfo.getCustomer().getUserName());
		
				/*
				 * JmiAddrBook jmiAddrBook = new JmiAddrBook(); Set addressSet =
				 * pdSendInfo.getCustomer().getJmiMember().getJmiAddrBooks(); Iterator
				 * addrIterator=addressSet.iterator(); while(addrIterator.hasNext()){
				 * jmiAddrBook = (JmiAddrBook) addrIterator.next(); }
				 */
		
				pdSendInfo.setCountryCode(order.getCompanyCode());
				pdSendInfo.setRecipientName(order.getFirstName() + order.getLastName());
				pdSendInfo.setRecipientCaNo(order.getProvince());
				pdSendInfo.setCity(order.getCity());
				String districtName = "";
				
				if(!"PH".equals(order.getCompanyCode())){
					if (StringUtils.isNotEmpty(order.getDistrict())) {
						districtName = alDistrictManager.getAlDistrict(order.getDistrict())
								.getDistrictName();
					}
				}
				pdSendInfo.setDistrict(order.getDistrict());
				
		
				pdSendInfo.setRecipientAddr(districtName + " " + order.getAddress());
				pdSendInfo.setRecipientZip(order.getPostalcode());
				//Modify By WuCF 20131226 修改可能存在的电话为空的情况，但发货单的电话不能为空，所以默认设置为“#” 
				pdSendInfo.setRecipientPhone(StringUtil.isEmpty(order.getPhone()) ? "#" : order.getPhone());
				pdSendInfo.setRecipientMobile(order.getMobiletele());
				pdSendInfo.setEmail(order.getEmail());
		
				// pdSendInfo.setShipType(shipType);//需要匹配
				pdSendInfo.setOrderNo(order.getEoNo());
		
				pdSendInfo.setCreateTime(new Date());
		
				String createUsrCode = Constants.sysConfigMap.get(
						order.getCompanyCode()).get("pd.autoship.creatercode");
				pdSendInfo.setCreateUsrCode(createUsrCode);// 配置
		
		//		pdSendInfo.setCheckTime(new Date());
		
				// String checkUsrCode =
				// Constants.sysConfigMap.get(order.getCompanyCode()).get("pd.autoship.checkercode");
				// pdSendInfo.setCheckUsrCode(checkUsrCode);//配置
		
				pdSendInfo.setOrderFlag(1);//modify fu 换货单生成的发货单的状态为已审核
		
				pdSendInfo.setCheckUsrCode(createUsrCode);//modify fu 2016-03-09  换货单生成的发货单的状态为已审核的 审核人
				pdSendInfo.setCheckTime(new Date());//modify fu 2016-03-09 换货单生成的发货单的状态为已审核的 审核时间
				
				
				BigDecimal  weight = new BigDecimal(0);
				BigDecimal  volume = new BigDecimal(0);
				
				//modify gw 2015-04-29 套餐拆开发货
				String userCode = order.getCustomer().getUserCode();
				String memberOrderNo = order.getOrderNo();
				JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByInterface(memberOrderNo);
				String teamCode = jpoMemberOrder.getTeamCode();//团队标志
				String orderType = jpoMemberOrder.getOrderType();//订单类型
				String companyCode = order.getCompanyCode();
				
				
				while (iterator.hasNext()) {
					
					PdExchangeOrderDetail pdExchangeOrderDetail = (PdExchangeOrderDetail) iterator
							.next();
					if(pdExchangeOrderDetail.getQty()>0){
					
					
						
						//modify gw 2015-04-29 判断商品是否是套餐商品
						String productOrderDetail = pdExchangeOrderDetail.getProductNo();
						boolean productIsOrNotCom = jpmProductManager.getCombinationJudgmentResult(productOrderDetail);
						//查询套餐下子商品
						List list = jpmProductCombinationManager.getCombinationProduct(productOrderDetail);
						Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
						if(productIsOrNotCom&&(null!=list && list.size()>0)){
								for(int a=0;a<list.size();a++){
									//modify gw 2015-04-30-----------------------------------取套餐子商品的值---begin
									Object o = list.get(a);
									JSONObject jsonObject = JSONObject.fromObject(o);
									Map result = new HashMap();
							        Iterator iterator1 = jsonObject.keys();
							        String key = null;
							        String value = null;
							        while (iterator1.hasNext()) {
							            key = (String) iterator1.next();
							            value = jsonObject.getString(key);
							            result.put(key, value);
							        }
							        Long qtyJ = 0L ;
									String productNoCombination = (String) result.get("PRODUCT_NO");//套餐商品编码
								    String subProductNo = (String) result.get("SUB_PRODUCT_NO");//子商品编码
								    String qtyString = (String) result.get("QTY");
								    if(!StringUtil.isEmpty(qtyString)){
								    	qtyJ = Long.parseLong(qtyString);
								    }
									//-----------------------------------取套餐子商品的值---end

								    //获取套餐商品的价格
								    BigDecimal subProductNoPrice = jpmProductSaleTeamTypeManager.getSubProductNoPrice(companyCode,orderType,teamCode,subProductNo);
								    if(null==subProductNoPrice){
								    	subProductNoPrice = new BigDecimal(0);
								    }
								    Long qty = qtyJ*pdExchangeOrderDetail.getQty();//数量
								    PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
									pdSendInfoDetail.setProductNo(subProductNo);
									pdSendInfoDetail.setPrice(subProductNoPrice);//子商品的价格
									pdSendInfoDetail.setQty(qty);
									pdSendInfoDetail.setAcceptQty(qty);
									pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo());
									
									pdSendInfoDetail.setErpProductNo(jpmProductManager.getJpmProduct(subProductNo).getErpProductNo());//erp商品编码获取
									pdSendInfoDetail.setCombinationProductNo(productNoCombination);
									
									//WuCF JpmProductSaleNew Modify By WuCF 20130917
									/*JpmProductSale jpmProductSale =jpmProductSaleManager.getJpmProductSale(order.getCompanyCode(), pdExchangeOrderDetail.getProductNo());*/
									JpmProductSaleNew jpmProductSale =jpmProductSaleManager.getJpmProductSaleNew(order.getCompanyCode(), pdExchangeOrderDetail.getProductNo());
									weight = weight.add(jpmProductSale.getWeight().multiply(new BigDecimal(pdExchangeOrderDetail.getQty())));
									volume = volume.add(jpmProductSale.getVolume().multiply(new BigDecimal(pdExchangeOrderDetail.getQty())));
									pdSendInfoDetailDao.savePdSendInfoDetail(pdSendInfoDetail);
								}
						}else{
								PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
								pdSendInfoDetail
										.setProductNo(pdExchangeOrderDetail.getProductNo());
								pdSendInfoDetail.setPrice(pdExchangeOrderDetail.getPrice());
								pdSendInfoDetail.setQty(pdExchangeOrderDetail.getQty());
								pdSendInfoDetail.setAcceptQty(pdExchangeOrderDetail.getQty());
								pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo());
								
								//modify gw 2014-11-21 添加ERP商品编码
								pdSendInfoDetail.setErpProductNo(pdExchangeOrderDetail.getErpProductNo());
								
								//WuCF JpmProductSaleNew Modify By WuCF 20130917
								/*JpmProductSale jpmProductSale =jpmProductSaleManager.getJpmProductSale(order.getCompanyCode(), pdExchangeOrderDetail.getProductNo());*/
								JpmProductSaleNew jpmProductSale =jpmProductSaleManager.getJpmProductSaleNew(order.getCompanyCode(), pdExchangeOrderDetail.getProductNo());
								weight = weight.add(jpmProductSale.getWeight().multiply(new BigDecimal(pdExchangeOrderDetail.getQty())));
								volume = volume.add(jpmProductSale.getVolume().multiply(new BigDecimal(pdExchangeOrderDetail.getQty())));
								pdSendInfoDetailDao.savePdSendInfoDetail(pdSendInfoDetail);
						}
					}
					
				}
				pdSendInfo.setWeight(weight);
				pdSendInfo.setVolume(volume);
				
				//modify fu 2015-11-24 换货单在确认的时候生成暂停发货、已审核的发货单
				pdSendInfo.setShipType("3");
				
				dao.savePdSendInfo(pdSendInfo);
				
				log.info("换货单确认时生成发货单（在类PdSendInfoManagerImpl的autoCreatePdSendInfo方法中运行）---------------------开始");
				//modify gw 2014-11-26  换货单确认时准备数据------------------------------begin
				Reship reship = new Reship();
				reship.setOrder_bn(order.getOrderNo());
				reship.setOrder_bn_ex(order.getEoNo());
				
				//--------------------------换货单时的退货信息----------------
		
				Set pdExchangeOrderBacks = order.getPdExchangeOrderBacks();
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
				//--------------------------换货单时的退货信息----------------
				
				//--------------------------换货单时的发货信息----------------
				Delivery delivery = new Delivery();
				delivery.setLo_bn(pdSendInfo.getSiNo());
				//这个是物流公司名称，还是物流公司编号----?? JOCS这边传递的是物流公司编号
				delivery.setLogistics(pdSendInfo.getShNo());
				//这个是仓库编号，还是仓库名称----?? JOCS这边传递的是仓库编号
				delivery.setBranch(pdSendInfo.getWarehouseNo());
				//modify by fu 2016-1-13 换货单对应的发货单的收货人信息----begin
				/*private String consignee;//收货人                                                         是否为空: 否
				private String consignee_state;//收货省                                         是否为空: 否
				private String consignee_city;//string                是否为空: 否
				private String consignee_area;//地区                                                 是否为空: 是
				private String consignee_address;//详细地址                               是否为空: 否
				private String consignee_zip;//邮编                                                    是否为空: 否
				private String consignee_mobile;//手机                                            是否为空: 否
				private String consignee_phone;//电话                                               是否为空: 是
*/				
				delivery.setConsignee(pdSendInfo.getRecipientName());
				delivery.setConsignee_state(pdSendInfo.getRecipientCaNo());
				delivery.setConsignee_city(pdSendInfo.getCity());
				delivery.setConsignee_area(pdSendInfo.getDistrict());
				delivery.setConsignee_address(pdSendInfo.getRecipientAddr());
				delivery.setConsignee_zip(pdSendInfo.getRecipientZip());
				delivery.setConsignee_mobile(pdSendInfo.getRecipientPhone());
				delivery.setConsignee_phone(pdSendInfo.getRecipientMobile());
				//modify by fu 2016-1-13 换货单对应的发货单的收货人信息----end
				/*Iterator iteratorTwo = set.iterator();
				while (iteratorTwo.hasNext()) {
					PdExchangeOrderDetail pdSendInfoDetailTwo = (PdExchangeOrderDetail) iteratorTwo.next();
					if(pdSendInfoDetailTwo.getQty()>0){
						Delivery_items delivery_items = new Delivery_items();
						delivery_items.setGoods_bn(pdSendInfoDetailTwo.getProductNo());
						delivery_items.setName(jpmProductManager.getJpmProduct(pdSendInfoDetailTwo.getProductNo()).getProductName());
						delivery_items.setErp_goods_bn(pdSendInfoDetailTwo.getErpProductNo());
						delivery_items.setNums(null==pdSendInfoDetailTwo.getQty()? 0:pdSendInfoDetailTwo.getQty().intValue());
						delivery_items.setPrice(null==pdSendInfoDetailTwo.getPrice()?0:(pdSendInfoDetailTwo.getPrice().doubleValue()));
						delivery.getDelivery_items().add(delivery_items);
					}
				}*/
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
				//reship.setDelivery(delivery); //modify by fu 2016-04-09 注释掉
				//modify by fu 2016-04-09 换货单因为添加聊自助换货单，自助换货单可以生成多个发货的，因此修改了实体类
				reship.getDeliverys().add(delivery);
				
				//--------------------------换货单时的发货信息----------------
				//将java对象转换成json对象
				JSONObject jsonObject = JSONObject.fromObject(reship);
				//将json对象转换成json字符串
				String returnnoJsonString = jsonObject.toString();
				log.info("换货单确认时生成发货单（在类PdSendInfoManagerImpl的autoCreatePdSendInfo方法中运行）：发货单数据准备完成");

				//modify gw 2014-11-26  换货单确认时准备数据------------------------------end
				//---------------调用接口，将数据推送给OMS
				//调用发送接口----modify gw 2014-12-23----开始
				MsgSendService msgSendService = new MsgSendService();
		
				msgSendService.setSender(Constants.OMS_SEND);//OMS平台
		
				//方法名-----OMS那边的接口名称
				String methodName = "ship.reshipAdd";
				
				//modify by fu 2017-1-18 换货单确认生成发货单推送到OMS系统的方法关闭掉
			   /* String aa = msgSendService.post(returnnoJsonString, methodName);
		        //调用发送接口----modify gw 2014-12-23----结束
				
				log.info("换货单确认时生成发货单（在类PdSendInfoManagerImpl的autoCreatePdSendInfo方法中运行）：JOCS向OMS推送换货单数据完成");
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
		return pdSendInfo.getSiNo();
	
	}

	public String autoSaveShipInfo(JpoMemberOrder order) {
		// TODO Auto-generated method stub
		String ret = null;
		String flag = Constants.sysConfigMap.get(order.getCompanyCode()).get(
				"pd.autoship.flag");
		if ("1".equals(flag)) {
			ret = this.autoCreatePdSendInfo(order);
		}else if("jms".equals(flag)){
			
		}else if("categoryDelivery".equals(flag)){
			 
			if(StringUtils.isNotBlank(order.getShippingSpecial())){
				ret = this.autoCreatePdSendInfo(order);
			}else{
				categoryDelivery(order);
			}
			
			
		}
		/*if("TW".equals(order.getCompanyCode())){
			Set set =order.getJpoMemberOrderList();
			Iterator iterator =set.iterator();
			while(iterator.hasNext()){
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) iterator.next();
				if("1".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getCombineFlag())){
					Long unsend=dao.getUnsendByProductNo(order.getCompanyCode(), jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo(), null);
					Long normalQty = pdWarehouseStockManager.getStock(order.getCompanyCode(), null, jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo());
					if(normalQty<=unsend){
						this.unSellProduct(jpoMemberOrderList.getJpmProductSale());
					}
				}
				
			}
		}*/
		return ret;
	}

	/**
	 * 业务忒TNND复杂
	 * @param order
	 */
	private void categoryDelivery(JpoMemberOrder order) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		log.info("categoryDelivery:start>>"+cal.getTimeInMillis());
		Map<String,PdSendInfo> sendMap = new HashMap<String,PdSendInfo>();
		Set set = order.getJpoMemberOrderList();
		Iterator iterator = set.iterator();
		Integer orderFlag = new Integer(1);
		String remark ="";
		if("1".equals(order.getIsShipments())){
			 orderFlag = new Integer(-1);
			 remark="暂停发货";
		}
		while (iterator.hasNext()) {
			JpoMemberOrderList orderList = (JpoMemberOrderList) iterator.next();
//			PdSendInfo pdSendInfo = sendMap.get(orderList.getJpmProductSale().getShipStrategy());
			int j = 1;
			
			//当是特定的发货策略时，存储12次pdSendInfo对象
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*if (orderList.getJpmProductSale().getShipStrategy().equals("10")) {*/
			if (orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getShipStrategy().equals("10")) {
				j = 6;
			}
			for (int i = 0; i < j; i++) {
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				PdSendInfo pdSendInfo = sendMap.get(orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getShipStrategy()+i);	
				/*PdSendInfo pdSendInfo = sendMap.get(orderList.getJpmProductSale().getShipStrategy()+i);	*/
				 
			if(pdSendInfo == null){
				pdSendInfo = new PdSendInfo();
				pdSendInfo.setCodFlag(order.getPayByCoin());
				pdSendInfo.setCompanyCode(order.getCompanyCode());
				pdSendInfo.setSiNo(sysIdManager.buildIdStr("sino"));
				pdSendInfo.setOrderType("1");
				pdSendInfo.setCompanyCode(order.getCompanyCode());
				pdSendInfo.setAmount(order.getAmount());
				pdSendInfo.setShipType(order.getPickup());
				pdSendInfo.setSubOrderType(order.getOrderType());
				
				pdSendInfo.setRecipientTime(order.getShippingDay());
//				pdSendInfo.setCodFlag(order.getShippingPay());
				pdSendInfo.setCustomer(order.getSysUser());
				pdSendInfo.setRecipientName(StringUtils.defaultString(order
						.getFirstName(), "")
						+ " " + StringUtils.defaultString(order.getLastName(), ""));
				pdSendInfo.setRecipientFirstName(order
						.getFirstName());
				pdSendInfo.setRecipientLastName(order.getLastName());
				pdSendInfo.setCountryCode(order.getCountryCode());
				pdSendInfo.setRecipientCaNo(StringUtils.defaultIfEmpty(order.getProvince(), "N/A"));
				pdSendInfo.setCity(order.getCity());
				
				
				
				//新增城市名称
				Map cityMap = alCityManager.getAlCityMap(order.getCompanyCode());
				pdSendInfo.setCityName((String) cityMap.get(order.getCity()));
				if(StringUtils.isEmpty(pdSendInfo.getCityName())){
					pdSendInfo.setCityName(order.getCity());
				}
				
//				pdSendInfo.setCodFlag(order.getShippingPay());
				
				String districtName = "";
				if(!"PH".equals(order.getCompanyCode())){
					if (StringUtils.isNotEmpty(order.getDistrict())) {
						districtName = alDistrictManager.getAlDistrict(order.getDistrict())
								.getDistrictName();
					}
				}
				
				pdSendInfo.setDistrict(order.getDistrict());
				pdSendInfo.setTownId(order.getTown());
				pdSendInfo.setRecipientAddr(districtName + " " + order.getAddress());
				pdSendInfo.setRecipientZip(StringUtils.defaultIfEmpty(order.getPostalcode(), "N/A"));
				pdSendInfo.setRecipientPhone(StringUtils.defaultIfEmpty(order.getPhone(),"N/A"));
				pdSendInfo.setRecipientMobile(StringUtils.defaultIfEmpty(order.getMobiletele(),"N/A"));
				pdSendInfo.setEmail(order.getEmail());

				
				// pdSendInfo.setShipType(shipType);//需要匹配
				pdSendInfo.setOrderNo(order.getMemberOrderNo());

				pdSendInfo.setCreateTime(new Date());
				
				String createUsrCode = Constants.sysConfigMap.get(
						order.getCompanyCode()).get("pd.autoship.creatercode");
				pdSendInfo.setCreateUsrCode(createUsrCode);// 配置
				
				
				Calendar rightNow = Calendar.getInstance();
		        rightNow.setTime(new Date());
		        rightNow.add(Calendar.MONTH, i*2);//日期加i个月
		        Date dt1 = rightNow.getTime();
				pdSendInfo.setCheckTime(dt1);

				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*PdShipStrategyDetail pdShipStrategyDetail = pdShipStrategyDetailManager.getPdShipStrategyDetail(orderList
						.getJpmProductSale().getShipStrategy(), pdSendInfo.getRecipientCaNo());*/
				PdShipStrategyDetail pdShipStrategyDetail = pdShipStrategyDetailManager.getPdShipStrategyDetail(orderList
						.getJpmProductSaleTeamType().getJpmProductSaleNew().getShipStrategy(), pdSendInfo.getRecipientCaNo());
				if(pdShipStrategyDetail == null){
					throw new AppException("shipStrategy.notfound");
				}
				pdSendInfo.setWarehouseNo(pdShipStrategyDetail.getWarehouseNo());
				pdSendInfo.setShNo(pdShipStrategyDetail.getShNo());
				
				//默认已审核,部分仓库走例外
				pdSendInfo.setOrderFlag(orderFlag);
				pdSendInfo.setRemark(remark);
				String extWarehouseNo = Constants.sysConfigMap.get(order.getCompanyCode()).get("pd.extwarehouseno");

				if(StringUtils.contains(extWarehouseNo, pdSendInfo.getWarehouseNo())){
					pdSendInfo.setOrderFlag(0);
				}
				//技术重量体积
				BigDecimal  weight = orderList.getWeight().multiply(new BigDecimal(orderList.getQty()));
				BigDecimal  volume = orderList.getVolume().multiply(new BigDecimal(orderList.getQty()));
				BigDecimal amount = orderList.getPrice().multiply(new BigDecimal(orderList.getQty()));
				pdSendInfo.setWeight(weight);
				pdSendInfo.setVolume(volume);
				pdSendInfo.setAmount(amount);
				
				Set<PdSendInfoDetail> pdSendInfoDetails = new HashSet<PdSendInfoDetail>();
				 
				PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				pdSendInfoDetail.setProductNo(orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
				/*pdSendInfoDetail.setProductNo(orderList
						.getJpmProductSale().getJpmProduct().getProductNo());*/
				pdSendInfoDetail.setPrice(orderList.getPrice());
				pdSendInfoDetail.setQty(new Long(orderList.getQty()/j));
				pdSendInfoDetail
						.setAcceptQty(new Long(orderList.getQty()/j));
				pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo());
				pdSendInfoDetails.add(pdSendInfoDetail);
				pdSendInfo.setPdSendInfoDetails(pdSendInfoDetails);
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*sendMap.put(orderList.getJpmProductSale().getShipStrategy()+i, pdSendInfo);*/
				sendMap.put(orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getShipStrategy()+i, pdSendInfo);
			}else{
				//技术重量体积
				BigDecimal  weight = orderList.getWeight().multiply(new BigDecimal(orderList.getQty()));
				BigDecimal  volume = orderList.getVolume().multiply(new BigDecimal(orderList.getQty()));
				BigDecimal amount = orderList.getPrice().multiply(new BigDecimal(orderList.getQty()));
				pdSendInfo.setWeight(pdSendInfo.getWeight().add(weight));
				pdSendInfo.setVolume(pdSendInfo.getVolume().add(volume));
				pdSendInfo.setAmount(pdSendInfo.getAmount().add(amount));
				
				PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*pdSendInfoDetail.setProductNo(orderList
						.getJpmProductSale().getJpmProduct().getProductNo());*/
				pdSendInfoDetail.setProductNo(orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
				pdSendInfoDetail.setPrice(orderList.getPrice());
				pdSendInfoDetail.setQty(new Long(orderList.getQty()/j));
				pdSendInfoDetail
						.setAcceptQty(new Long(orderList.getQty()/j));
				pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo());
				
				pdSendInfo.getPdSendInfoDetails().add(pdSendInfoDetail);
				
			}
			}
		}
		
		Set<String> keyS = sendMap.keySet();
		for(String key : keyS){
			PdSendInfo pdSendInfo = sendMap.get(key);
			dao.savePdSendInfo(pdSendInfo);
		}
		cal = Calendar.getInstance();
		log.info("categoryDelivery:end>>"+cal.getTimeInMillis());
	}



	public String autoSaveShipInfo(PdReturnPurchase order) {
		// TODO Auto-generated method stub
		String ret = null;
		String flag = Constants.sysConfigMap.get(order.getCompanyCode()).get(
				"pd.autoship.flag");
		if ("1".equals(flag)) {
			ret = this.autoCreatePdSendInfo(order);
		}
		return ret;
	}

	private String autoCreatePdSendInfo(PdReturnPurchase order) {
		// TODO Auto-generated method stub
		Set set = order.getPdReturnPurchaseDetails();
		Iterator iterator = set.iterator();

		PdSendInfo pdSendInfo = new PdSendInfo();
		pdSendInfo.setOrderType("2");
		pdSendInfo.setCompanyCode(order.getCompanyCode());
		pdSendInfo.setSiNo(sysIdManager.buildIdStrTwo("sino"));//modify by fu 2016-03-17发货退回单对应的发货单编号用新的序列生成器
		pdSendInfo.setCompanyCode(order.getCompanyCode());
		pdSendInfo.setAmount(order.getAmount());

		// pdSendInfo.setShNo(shNo);//需要匹配

		/*
		 * String warehouseNo =
		 * pdWarehouseAreaManager.getPdWarehouseNo(order.getCompanyCode(),
		 * order.getProvince(),pdSendInfo.getShNo());
		 * 
		 * if(StringUtils.isEmpty(warehouseNo)){
		 * warehouseNo=Constants.sysConfigMap
		 * .get(order.getCompanyCode()).get("pd.autoship.warehouseno"); }
		 */
		pdSendInfo.setWarehouseNo(order.getReturnWarehouseNo());// 需要匹配
		pdSendInfo.setShNo(pdWarehouseDao.getPdWarehouse(
				order.getReturnWarehouseNo()).getShNo());
		pdSendInfo.setCustomer(order.getCustomer());
		// pdSendInfo.setRecipientName(pdSendInfo.getCustomer().getUserName());

		/*
		 * JmiAddrBook jmiAddrBook = new JmiAddrBook(); Set addressSet =
		 * pdSendInfo.getCustomer().getJmiMember().getJmiAddrBooks(); Iterator
		 * addrIterator=addressSet.iterator(); while(addrIterator.hasNext()){
		 * jmiAddrBook = (JmiAddrBook) addrIterator.next(); }
		 */

		pdSendInfo.setCountryCode(order.getCompanyCode());
		pdSendInfo.setRecipientName(order.getFirstName() + order.getLastName());
		pdSendInfo.setRecipientCaNo(order.getProvince());
		pdSendInfo.setCity(order.getCity());
		String districtName = "";
		if (StringUtils.isNotEmpty(order.getDistrict())) {
			AlDistrict district = alDistrictManager.getAlDistrict(order
					.getDistrict());
			districtName = district.getDistrictName();
		}

		pdSendInfo.setRecipientAddr(districtName + " " + order.getAddress());
		pdSendInfo.setRecipientZip(order.getPostalcode());
		pdSendInfo.setRecipientPhone(order.getPhone());
		pdSendInfo.setRecipientMobile(order.getMobiletele());
		pdSendInfo.setEmail(order.getEmail());

		// pdSendInfo.setShipType(shipType);//需要匹配
		pdSendInfo.setOrderNo(order.getRpNo());

		pdSendInfo.setCreateTime(new Date());

		String createUsrCode = Constants.sysConfigMap.get(
				order.getCompanyCode()).get("pd.autoship.creatercode");
		pdSendInfo.setCreateUsrCode(createUsrCode);// 配置

		pdSendInfo.setCheckTime(new Date());

		// String checkUsrCode =
		// Constants.sysConfigMap.get(order.getCompanyCode()).get("pd.autoship.checkercode");
		// pdSendInfo.setCheckUsrCode(checkUsrCode);//配置

		pdSendInfo.setOrderFlag(1);//modify fu 20150910发货退回单生成的发货单的状态为已审核
		pdSendInfo.setCheckUsrCode(createUsrCode);//modify fu 20160309发货退回单生成的发货单的状态为已审核
		pdSendInfo.setCheckTime(new Date());//modify fu 20160309发货退回单生成的发货单的状态为已审核
		
		pdSendInfo.setShipType("3");
		
		//modify fu 2015-12-26 
		//发货退回订单入库生成发货单之前首先判断该单有没有发货单生成
		boolean havePd = dao.getPdSendInfoOrderForOrderNo(order.getRpNo());
		//true:发货退回单之前没有生成发货单  false:发货退回单已经生成了发货单
		if(havePd){
			        System.out.println("---------------------------------------------aaaaaa"+(new Date()).toString());
					dao.savePdSendInfo(pdSendInfo);//modify fu 这一行不可删除，这个是用来解决EC接收到多次入库消息的,这一行也不可移动，否则会出问题
					BigDecimal  weight = new BigDecimal(0);
					BigDecimal  volume = new BigDecimal(0);
					while (iterator.hasNext()) {
						PdReturnPurchaseDetail pdReturnPurchaseDetail = (PdReturnPurchaseDetail) iterator
								.next();
						PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
						pdSendInfoDetail
								.setProductNo(pdReturnPurchaseDetail.getProductNo());
						pdSendInfoDetail.setPrice(pdReturnPurchaseDetail.getPrice());
						pdSendInfoDetail.setQty(pdReturnPurchaseDetail.getQty());
						pdSendInfoDetail.setAcceptQty(pdReturnPurchaseDetail.getQty());
						pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo());
						//WuCF JpmProductSaleNew Modify By WuCF 20130917
						/*JpmProductSale jpmProductSale =jpmProductSaleManager.getJpmProductSale(order.getCompanyCode(), pdReturnPurchaseDetail.getProductNo());*/
						JpmProductSaleNew jpmProductSale =jpmProductSaleManager.getJpmProductSaleNew(order.getCompanyCode(), pdReturnPurchaseDetail.getProductNo());
						weight = weight.add(jpmProductSale.getWeight().multiply(new BigDecimal(pdReturnPurchaseDetail.getQty())));
						volume = volume.add(jpmProductSale.getVolume().multiply(new BigDecimal(pdReturnPurchaseDetail.getQty())));
						pdSendInfoDetailDao.savePdSendInfoDetail(pdSendInfoDetail);
					}
					pdSendInfo.setWeight(weight);
					pdSendInfo.setVolume(volume);
					
					//modify fu 2015-11-24 发货退回单确认是生成的发货单是暂停发货、已审核的发货单
					
					
					dao.savePdSendInfo(pdSendInfo);
					
					//推送"生成发货单的发货退回单"接口数据---modify fu 20150821
					this.sendPdReturnSend(pdSendInfo,set);
		            return pdSendInfo.getSiNo();
		}else{
			 System.out.println("---------------------------------------------111111"+(new Date()).toString());
			 return null;
		}
	}
	
	/**
	 * 推送"生成发货单的发货退回单"接口数据
	 * @author fu 20150821
	 * @param pdSendInfo
	 */
	public void sendPdReturnSend(PdSendInfo pdSendInfo,Set set){
        //生成发货单的发货退回单接口------------------modify fu 20150821 -----begin
		//准备接口数据-----开始
		log.info("生成发货单的发货退回单接口一在PdSendInfoManagerImpl类的sendPdReturnSend方法中开始运行");
		PdReturnSend pdReturnSend = new PdReturnSend();
		pdReturnSend.setRpNo(pdSendInfo.getOrderNo());
		pdReturnSend.setCustomerCode(pdSendInfo.getCustomer().getUserCode());
		pdReturnSend.setRecipientName(pdSendInfo.getRecipientName());
		pdReturnSend.setRecipientCaNo(pdSendInfo.getRecipientCaNo());
		pdReturnSend.setCity(pdSendInfo.getCity());
		pdReturnSend.setDistrict(pdSendInfo.getDistrict());
		pdReturnSend.setRecipientAddr(pdSendInfo.getRecipientAddr());
		pdReturnSend.setRecipientZip(pdSendInfo.getRecipientZip());
		pdReturnSend.setRecipientMobile(pdSendInfo.getRecipientMobile());
		pdReturnSend.setRecipientPhone(pdSendInfo.getRecipientPhone());
		pdReturnSend.setSiNo(pdSendInfo.getSiNo());
		pdReturnSend.setShNo(pdSendInfo.getShNo());
		pdReturnSend.setWarehouseNo(pdSendInfo.getWarehouseNo());
		
		List<PdReturnSendProduct>  pdReturnSendProduct = new ArrayList<PdReturnSendProduct>();

		Iterator it = set.iterator();
		while(it.hasNext()){
			PdReturnPurchaseDetail pdReturnPurchaseDetail  = (PdReturnPurchaseDetail) it.next();
			PdReturnSendProduct pdReturnSendProductOb = new PdReturnSendProduct();
			pdReturnSendProductOb.setProductNo(pdReturnPurchaseDetail.getProductNo());
			pdReturnSendProductOb.setErpProductNo(pdReturnPurchaseDetail.getErpProductNo());
			pdReturnSendProductOb.setQty(Integer.parseInt(pdReturnPurchaseDetail.getQty().toString()));
			pdReturnSendProduct.add(pdReturnSendProductOb);
		}
		
		pdReturnSend.setPdReturnSendProduct(pdReturnSendProduct);
		log.info("生成发货单的发货退回单接口一在PdSendInfoManagerImpl类的sendPdReturnSend方法:接口数据准备完毕");
		JSONObject jo = JSONObject.fromObject(pdReturnSend);
		String joString = jo.toString();
		//准备接口数据-----结束
		

		//调用发送接口--向OMS发送----开始
		MsgSendService msgSendService = new MsgSendService();
		//方法名
		String methodName = "ship.sendPdReturnSend";//OMS方法名未知
		msgSendService.setSender(Constants.OMS_SEND);//OMS平台
		//String aa = "123";//为了自测方便特意加上这一行，后面要删除掉；
		
		//modify by fu 2017-1-18 发货退回单确认时生成发货单推送接口方法注释掉
		/*String aa = msgSendService.post(joString, methodName);//真正的接口联调的时候，这一行要放开注释 
		
		log.info("生成发货单的发货退回单接口一在PdSendInfoManagerImpl类的sendPdReturnSend方法:接口数据推送完毕");

		//调用发送接口--向OMS发送----modify gw 2014-12-23----结束
		//=============================接口日志写入开始 Modify By WUCF 20150123
		JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
		jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
		jocsInterfaceLog.setSender(Constants.OMS_SEND);//数据的接收方
		jocsInterfaceLog.setMethod(methodName);//方法名称
		jocsInterfaceLog.setContent(joString);//发送内容content
		jocsInterfaceLog.setReturnDesc(aa);//返回内容
		
		ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
		//=============================接口日志写入完毕	
		log.info("生成发货单的发货退回单接口数据成功推送");*/

	}

	/*
	 * Add By WuCF 20150706 新增request参数
	 * 确认订货单，需要更新库存 (non-Javadoc)
	 * 
	 * @see
	 * com.joymain.jecs.pd.service.PdSendInfoManager#confirmSendInfo(com.joymain
	 * .jecs.pd.model.PdSendInfo, com.joymain.jecs.sys.model.SysUser)
	 */
	public void confirmSendInfo(PdSendInfo pdSendInfo, SysUser sessionLogin,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String ret = null;
		if (!"1".equals(pdSendInfo.getStockFlag()) && pdSendInfo.getOrderFlag() ==1) {
		
//			ret = pdShipmentsDetailManager.saveMatchSendInfo(pdSendInfo);

			pdSendInfo.setOkTime(new Date());
			pdSendInfo.setOkUsrCode(sessionLogin.getUserCode());

			pdSendInfo.setStockFlag("1");
			pdSendInfo.setOrderFlag(2);
			
			/*if("US".equals(pdSendInfo.getCompanyCode())&& "UPS".equalsIgnoreCase(pdSendInfo.getShNo())){
				this.upsAcceptRequest(pdSendInfo);
			}else{
				dao.savePdSendInfo(pdSendInfo);
			}*/
			List<String> listTemp = new ArrayList<String>();
			Set pdSendInfoDetails = pdSendInfo.getPdSendInfoDetails();
			Iterator iterator = pdSendInfoDetails.iterator();
			BigDecimal bd = new BigDecimal(0);
			while (iterator.hasNext()) {
				PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
				String productNo2 = request.getParameter(pdSendInfoDetail.getProductNo()+"-productNo2");
				String qty = request.getParameter(pdSendInfoDetail.getProductNo()+ "-qty");//Modify By WUCF 待发商品数量
				String qty2 = request.getParameter(pdSendInfoDetail.getProductNo()+ "-qty2");//实际发送数量
				String price2 = request.getParameter(pdSendInfoDetail.getProductNo()+"-price2");//单价
				if(Integer.parseInt(qty2)<Integer.parseInt(qty)){
					//如果实际发送数量小于发货单数量，则记录
					listTemp.add(productNo2+","+qty2+","+(Integer.parseInt(qty)-Integer.parseInt(qty2))+","+price2);
					pdSendInfoDetail.setQty(Long.parseLong(qty2));
					pdSendInfoDetail.setAcceptQty(Long.parseLong(qty2));
					pdSendInfoDetailDao.savePdSendInfoDetail(pdSendInfoDetail);
				}
				//总价累计
				bd = bd.add(pdSendInfoDetail.getPrice().multiply(new BigDecimal(pdSendInfoDetail.getQty())));
				
				//如果发货数量为0，则直接把原始发货单中的商品数据删除
				if(Integer.parseInt(qty2) == 0){//如果金额为0，则直接把数据删掉
					pdSendInfoDetailDao.deletePdSendInfoDetail(pdSendInfoDetail.getUniNo());
				}
			}
			pdSendInfo.setAmount(bd);//重新设置金额
			
			//如果有未全部发送的商品的发货单数据，则会生成欠货单
			if(listTemp!=null && listTemp.size()>0){
				for(String strTemp : listTemp){
					String[] strs = strTemp.split(",");
					for(Object object : pdSendInfo.getPdSendInfoDetails()){
						PdSendInfoDetail psid = (PdSendInfoDetail)object;
						if(strs[0].equals(psid.getProductNo()) && !"0".equals(strs[1])){//如果是指定商品，则修改实际发货数量
							psid.setQty(Long.parseLong(strs[1]));
							psid.setAcceptQty(Long.parseLong(strs[1]));
						}
					}
				}
				dao.savePdSendInfo(pdSendInfo);//修改商品实际库存数量
				//生成欠货单：
				this.autoCreatePdSendInfo(pdSendInfo, listTemp);
				
			}else{//全部发送，则直接修改状态
				dao.savePdSendInfo(pdSendInfo);//修改商品实际库存数量
			}
			
			this.callBack(pdSendInfo);//新增回调订单方法
			log.info("msg.sendflag="+Constants.sysConfigMap.get(pdSendInfo.getCompanyCode()).get("msg.sendflag"));
			try {
				if("1".equals(Constants.sysConfigMap.get(pdSendInfo.getCompanyCode()).get("msg.sendflag"))){
					try {
						this.getMsg(pdSendInfo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.error("sendMsg erro:"+pdSendInfo.getSiNo()+">>"+e.toString());
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.fillInStackTrace();
				log.error("sendMsg erro:"+pdSendInfo.getSiNo()+">>"+e.toString());
			}
				//修改对应仓库的商品库存
				pdWarehouseStockManager.updatePdWarehouseStock(pdSendInfo);
				 
		}
		/**
		 * @author 罗婷 测试物流soap接口
		 * @param pdsendInfo
		 */
		/*if ("US".equals(pdSendInfo.getCompanyCode())) {
			this.getPdsendInfoInterface(pdSendInfo);
		}*/
	
	}
	
	/**
	 * 生成欠货单数据   Add By WuCF 20150706
	 * @param order
	 * @return
	 */
	private String autoCreatePdSendInfo(PdSendInfo pdSendInfoOld,List<String> listTemp) {
		PdSendInfo pdSendInfo = new PdSendInfo();
		pdSendInfo.setOrderType(pdSendInfoOld.getOrderType());//订单类型
		pdSendInfo.setCompanyCode(pdSendInfoOld.getCompanyCode());
		pdSendInfo.setSiNo(sysIdManager.buildIdStr("sino"));
		pdSendInfo.setAmount(new BigDecimal(0));//金额
		pdSendInfo.setShipType(pdSendInfoOld.getShipType());
		pdSendInfo.setRecipientFirstName(pdSendInfoOld.getRecipientFirstName());
		pdSendInfo.setRecipientLastName(pdSendInfoOld.getRecipientLastName());
		pdSendInfo.setCityName(pdSendInfoOld.getCityName());
		pdSendInfo.setSubOrderType(pdSendInfoOld.getSubOrderType());
		// pdSendInfo.setShNo(shNo);//需要匹配

		/*
		 * String warehouseNo =
		 * pdWarehouseAreaManager.getPdWarehouseNo(order.getCompanyCode(),
		 * order.getProvince(),pdSendInfo.getShNo());
		 * 
		 * if(StringUtils.isEmpty(warehouseNo)){
		 * warehouseNo=Constants.sysConfigMap
		 * .get(order.getCompanyCode()).get("pd.autoship.warehouseno"); }
		 */
		pdSendInfo.setWarehouseNo(pdSendInfoOld.getWarehouseNo());// 需要匹配
		pdSendInfo.setShNo(pdSendInfoOld.getShNo());
		pdSendInfo.setCustomer(pdSendInfoOld.getCustomer());
		// pdSendInfo.setRecipientName(pdSendInfo.getCustomer().getUserName());

		/*
		 * JmiAddrBook jmiAddrBook = new JmiAddrBook(); Set addressSet =
		 * pdSendInfo.getCustomer().getJmiMember().getJmiAddrBooks(); Iterator
		 * addrIterator=addressSet.iterator(); while(addrIterator.hasNext()){
		 * jmiAddrBook = (JmiAddrBook) addrIterator.next(); }
		 */

		pdSendInfo.setCountryCode(pdSendInfoOld.getCountryCode());
		pdSendInfo.setRecipientName(pdSendInfoOld.getRecipientName());
		pdSendInfo.setRecipientCaNo(pdSendInfoOld.getRecipientCaNo());
		pdSendInfo.setCity(pdSendInfoOld.getCity());
		pdSendInfo.setDistrict(pdSendInfoOld.getDistrict());
		

		pdSendInfo.setRecipientAddr(pdSendInfoOld.getRecipientAddr());
		pdSendInfo.setRecipientZip(pdSendInfoOld.getRecipientZip());
		//Modify By WuCF 20131226 修改可能存在的电话为空的情况，但发货单的电话不能为空，所以默认设置为“#” 
		pdSendInfo.setRecipientPhone(pdSendInfoOld.getRecipientPhone());
		pdSendInfo.setRecipientMobile(pdSendInfoOld.getRecipientMobile());
		pdSendInfo.setEmail(pdSendInfoOld.getEmail());

		// pdSendInfo.setShipType(shipType);//需要匹配
		pdSendInfo.setOrderNo(pdSendInfoOld.getOrderNo());

		pdSendInfo.setCreateTime(new Date());//创建时间
		pdSendInfo.setCreateUsrCode(pdSendInfoOld.getCreateUsrCode());//创建人

//		pdSendInfo.setCheckTime(new Date());

		// String checkUsrCode =
		// Constants.sysConfigMap.get(order.getCompanyCode()).get("pd.autoship.checkercode");
		// pdSendInfo.setCheckUsrCode(checkUsrCode);//配置

		pdSendInfo.setOrderFlag(1);//已审核
		pdSendInfo.setCheckTime(new Date());//审核时间
		pdSendInfo.setCheckUsrCode(pdSendInfoOld.getCreateUsrCode());//审核人
		pdSendInfo.setRemark(pdSendInfoOld.getRemark()+"|"+pdSendInfoOld.getSiNo()+"生成的欠货单");
		BigDecimal  weight = new BigDecimal(0);
		BigDecimal  volume = new BigDecimal(0);
		BigDecimal bd = new BigDecimal(0);
		BigDecimal b1 = new BigDecimal(0);
		BigDecimal b2 = new BigDecimal(0);
		BigDecimal b3 = new BigDecimal(0);
		for(String strTemp : listTemp){
			log.info("%%%strTemp:"+strTemp);
			String[] strs = strTemp.split(",");
			if(Long.parseLong(strs[2])>0){
				PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
				pdSendInfoDetail.setProductNo(strs[0]);
				pdSendInfoDetail.setPrice(new BigDecimal(strs[3]));
				pdSendInfoDetail.setQty(Long.parseLong(strs[2]));
				pdSendInfoDetail.setAcceptQty(Long.parseLong(strs[2]));
				pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo());
				
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*JpmProductSale jpmProductSale =jpmProductSaleManager.getJpmProductSale(order.getCompanyCode(), pdExchangeOrderDetail.getProductNo());*/
				JpmProductSaleNew jpmProductSale =jpmProductSaleManager.getJpmProductSaleNew(pdSendInfo.getCompanyCode(), strs[0]);
				weight = weight.add(jpmProductSale.getWeight().multiply(new BigDecimal(Long.parseLong(strs[2]))));
				volume = volume.add(jpmProductSale.getVolume().multiply(new BigDecimal(Long.parseLong(strs[2]))));
				pdSendInfoDetailDao.savePdSendInfoDetail(pdSendInfoDetail);
				b1 = new BigDecimal(strs[2]);
				b2 = new BigDecimal(strs[3]);
				b3 = b1.multiply(b2);
				log.info("===b1:"+b1+"-"+b2+"-"+b3);
			}
			bd = bd.add(b3);
		}
		log.info("===bd:"+bd);
		pdSendInfo.setAmount(bd);//总金额
		pdSendInfo.setWeight(weight);
		pdSendInfo.setVolume(volume);
		dao.savePdSendInfo(pdSendInfo);
		return pdSendInfo.getSiNo();
	
	}
	
	/*
	 * 确认订货单，需要更新库存 (non-Javadoc)
	 * 
	 * @see
	 * com.joymain.jecs.pd.service.PdSendInfoManager#confirmSendInfo(com.joymain
	 * .jecs.pd.model.PdSendInfo, com.joymain.jecs.sys.model.SysUser)
	 */
	public void confirmSendInfo(PdSendInfo pdSendInfo, SysUser sessionLogin) throws Exception {
		// TODO Auto-generated method stub
		String ret = null;
		if (!"1".equals(pdSendInfo.getStockFlag()) && pdSendInfo.getOrderFlag() ==1) {
		
//			ret = pdShipmentsDetailManager.saveMatchSendInfo(pdSendInfo);

			//modify fu 2015-09-20 防止LO单接口和发货单发货确认菜单扣两次库存,所以先去数据库中查询发货单的状态,
			//modify fu 2015-10-21 因WMS未上线，EC需要校验库存，所以这部分先注释掉，等后期在完善
			//boolean a = dao.getPdSendInfoOrderFlagDBA(pdSendInfo);
			
			
			pdSendInfo.setOkTime(new Date());
			pdSendInfo.setOkUsrCode(sessionLogin.getUserCode());

			pdSendInfo.setStockFlag("1");
			pdSendInfo.setOrderFlag(2);
			
			/*if("US".equals(pdSendInfo.getCompanyCode())&& "UPS".equalsIgnoreCase(pdSendInfo.getShNo())){
				this.upsAcceptRequest(pdSendInfo);
			}else{
				dao.savePdSendInfo(pdSendInfo);
			}*/
		
			dao.savePdSendInfo(pdSendInfo);
		
			
			this.callBack(pdSendInfo);//新增回调订单方法
			log.info("msg.sendflag="+Constants.sysConfigMap.get(pdSendInfo.getCompanyCode()).get("msg.sendflag"));
			try {
				if("1".equals(Constants.sysConfigMap.get(pdSendInfo.getCompanyCode()).get("msg.sendflag"))){
					try {
						this.getMsg(pdSendInfo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.error("sendMsg erro:"+pdSendInfo.getSiNo()+">>"+e.toString());
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.fillInStackTrace();
				log.error("sendMsg erro:"+pdSendInfo.getSiNo()+">>"+e.toString());
			}
			    //modify fu 2015-10-21 因WMS未上线，EC需要校验库存，所以这部分先注释掉，等后期在完善
				/* if(!a){
					pdWarehouseStockManager.updatePdWarehouseStock(pdSendInfo);
				 }*/
				 pdWarehouseStockManager.updatePdWarehouseStock(pdSendInfo);
		}
		/**
		 * @author 罗婷 测试物流soap接口
		 * @param pdsendInfo
		 */
		/*if ("US".equals(pdSendInfo.getCompanyCode())) {
			this.getPdsendInfoInterface(pdSendInfo);
		}*/
	
	}

	
	
	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.joymain.jecs.pd.service.PdSendInfoManager#autoCreatePdSendInfo(com
	 * .joymain.jecs.po.model.JpoMemberOrder)
	 */
	public String autoCreatePdSendInfo(JpoMemberOrder order) {
		// TODO Auto-generated method stub
		Set set = order.getJpoMemberOrderList();
		Iterator iterator = set.iterator();

		PdSendInfo pdSendInfo = new PdSendInfo();
		pdSendInfo.setCompanyCode(order.getCompanyCode());
		pdSendInfo.setSiNo(sysIdManager.buildIdStr("sino"));
		pdSendInfo.setOrderType("1");
		pdSendInfo.setCompanyCode(order.getCompanyCode());
		pdSendInfo.setAmount(order.getAmount());
		pdSendInfo.setShipType(order.getPickup());
		pdSendInfo.setSubOrderType(order.getOrderType());
		
		pdSendInfo.setRecipientTime(order.getShippingDay());
		pdSendInfo.setCodFlag(order.getShippingPay());
		//需要匹配shNo
		String shNo = "";
		String warehouseNo="";
		
		/************批量倒订单********************/
		if(StringUtils.isNotBlank(order.getShippingSpecial())){
			warehouseNo=order.getResendSpNo();
			shNo = order.getShippingCompany();
		}
		
		/********************************/
		else{
			// pdSendInfo.setShNo(shNo);//需要匹配
			Set fees = order.getJpoMemberOrderFee();
			Iterator feesIterator = fees.iterator();
			
			
			while (feesIterator.hasNext()) {
				JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) feesIterator
						.next();
				if ("1".equals(jpoMemberOrderFee.getFeeType())) {
					shNo = jpoMemberOrderFee.getDetailType();
				}
			}
			
			
			if (StringUtils.isEmpty(shNo)) {
				shNo = Constants.sysConfigMap.get(order.getCompanyCode())
				.get("pd.autoship.shNo");
			}
			
			
			
			
			
			
			//需要匹配warehouseNo
			
			
			/************中国大陆开始**************/
			if ("5".equals(order.getOrderType())
					&& "CN".equals(order.getCompanyCode())) {
				warehouseNo = "NJRDC2";
				shNo="0000";
			} else {
				warehouseNo = pdWarehouseAreaManager.getPdWarehouseNo(order
						.getCompanyCode(), order.getProvince(), shNo);
			}
			
			
			
			//新疆的都由乌鲁木齐发
			if("163732".equals(pdSendInfo.getRecipientCaNo())){
				
				if("DTW".equals(shNo)){
					warehouseNo="WLMQRDC2";
				}else{
					warehouseNo="WLMQRDC";
					shNo="ZJS";
				}
				
				
			}
			
			
			if("LC".equals(order.getProductType())){
				warehouseNo="LC";
				shNo="0000";
			}
			/************中国大陆end**************/
			/************台湾**************/
			
		
			if("1".equals(order.getPickup()) && "TW".equals(order.getCompanyCode())){
				shNo="0000";
				warehouseNo="TWRDC";
			}
			/************台湾end**************/
			
			/**********USA*******************/
			if("US".equals(order.getCompanyCode())){
				shNo="UPS";
			}
			
			/**********USA end*******************/
			/************菲律宾**************/
			
			
			if("1".equals(order.getPickup()) && "PH".equals(order.getCompanyCode())){
				shNo="0000";
				warehouseNo="PH-WH-2";
			}
			/************菲律宾end**************/
			if (StringUtils.isEmpty(warehouseNo)) {
				warehouseNo = Constants.sysConfigMap.get(order.getCompanyCode())
						.get("pd.autoship.warehouseno");
			}
			
			if (StringUtils.isEmpty(shNo)) {
				throw new AppException("erro.pd.shNo.isEmpty");
			}
			
			
			if (StringUtils.isEmpty(warehouseNo)) {
				throw new AppException("erro.pd.warehouseNo.isEmpty");
			}
			
		}
		
		/************批量倒订单********************/
		if(StringUtils.isNotBlank(order.getShippingSpecial())){
			warehouseNo=order.getResendSpNo();
			shNo = order.getShippingCompany();
		}
		
		/********************************/
		
		
		pdSendInfo.setShNo(shNo);
		pdSendInfo.setWarehouseNo(warehouseNo);// 需要匹配

		pdSendInfo.setCustomer(order.getSysUser());
		pdSendInfo.setRecipientName(StringUtils.defaultString(order
				.getFirstName(), "")
				+ " " + StringUtils.defaultString(order.getLastName(), ""));
		pdSendInfo.setRecipientFirstName(order
				.getFirstName());
		pdSendInfo.setRecipientLastName(order.getLastName());
		pdSendInfo.setCountryCode(order.getCountryCode());
		pdSendInfo.setRecipientCaNo(StringUtils.defaultIfEmpty(order.getProvince(), "N/A"));
		pdSendInfo.setCity(order.getCity());
		
		
		//新增城市名称
		Map cityMap = alCityManager.getAlCityMap(order.getCompanyCode());
		pdSendInfo.setCityName((String) cityMap.get(order.getCity()));
		if(StringUtils.isEmpty(pdSendInfo.getCityName())){
			pdSendInfo.setCityName(order.getCity());
		}
		
		pdSendInfo.setCodFlag(order.getShippingPay());
		
		String districtName = "";
		if(!"PH".equals(order.getCompanyCode())){
			if (StringUtils.isNotEmpty(order.getDistrict())) {
				districtName = alDistrictManager.getAlDistrict(order.getDistrict())
						.getDistrictName();
			}
		}
		
		pdSendInfo.setDistrict(order.getDistrict());
		pdSendInfo.setTownId(order.getTown());
		pdSendInfo.setRecipientAddr(districtName + " " + order.getAddress());
		pdSendInfo.setRecipientZip(StringUtils.defaultIfEmpty(order.getPostalcode(), "N/A"));
		pdSendInfo.setRecipientPhone(StringUtils.defaultIfEmpty(order.getPhone(),"N/A"));
		pdSendInfo.setRecipientMobile(StringUtils.defaultIfEmpty(order.getMobiletele(),"N/A"));
		pdSendInfo.setEmail(order.getEmail());

		
		// pdSendInfo.setShipType(shipType);//需要匹配
		pdSendInfo.setOrderNo(order.getMemberOrderNo());

		pdSendInfo.setCreateTime(new Date());
		
		String createUsrCode = Constants.sysConfigMap.get(
				order.getCompanyCode()).get("pd.autoship.creatercode");
		pdSendInfo.setCreateUsrCode(createUsrCode);// 配置

		pdSendInfo.setCheckTime(new Date());

		/*if("JP".equals(order.getCompanyCode()) && order.getShippingDay() != null){
			pdSendInfo.setCheckTime(order.getShippingDay());
		}*/
		// String checkUsrCode =
		// Constants.sysConfigMap.get(order.getCompanyCode()).get("pd.autoship.checkercode");
		// pdSendInfo.setCheckUsrCode(checkUsrCode);//配置
		//默认已审核
		pdSendInfo.setOrderFlag(1);
//		String extProvince = Constants.sysConfigMap.get(order.getCompanyCode()).get("pd.extProvince");
//		if(StringUtils.contains(extProvince, order.getProvince())){
//			pdSendInfo.setOrderFlag(0);
//		}
		String extWarehouseNo = Constants.sysConfigMap.get(order.getCompanyCode()).get("pd.extwarehouseno");
//		log.info("extWarehouseNo="+extWarehouseNo);
		if(StringUtils.contains(extWarehouseNo, pdSendInfo.getWarehouseNo())){
			log.info("extWarehouseNo2="+pdSendInfo.getWarehouseNo());
			pdSendInfo.setOrderFlag(0);
		}
		BigDecimal  weight = new BigDecimal(0);
		BigDecimal  volume = new BigDecimal(0);
		
		while (iterator.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) iterator
					.next();
			PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*pdSendInfoDetail.setProductNo(jpoMemberOrderList
					.getJpmProductSale().getJpmProduct().getProductNo());*/
			pdSendInfoDetail.setProductNo(jpoMemberOrderList
					.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
			pdSendInfoDetail.setPrice(jpoMemberOrderList.getPrice());
			pdSendInfoDetail.setQty(new Long(jpoMemberOrderList.getQty()));
			pdSendInfoDetail
					.setAcceptQty(new Long(jpoMemberOrderList.getQty()));
			pdSendInfoDetail.setSiNo(pdSendInfo.getSiNo());
			pdSendInfoDetail.setWeight(jpoMemberOrderList.getWeight());
			weight=weight.add(jpoMemberOrderList.getWeight().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			volume = volume.add(jpoMemberOrderList.getVolume().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			pdSendInfoDetailDao.savePdSendInfoDetail(pdSendInfoDetail);
		}
		pdSendInfo.setWeight(weight);
		pdSendInfo.setVolume(volume);
		dao.savePdSendInfo(pdSendInfo);
		// throw new AppException("test");
		return pdSendInfo.getSiNo();
	}
	
	
	private void getMsg(final PdSendInfo pdSendInfo) {
		log.info("sendMsg>>start>>");
		try {
			Calendar cal = Calendar.getInstance();
			String province = alStateProvinceManager.getAlStateProvince(
					pdWarehouseDao.getPdWarehouse(pdSendInfo.getWarehouseNo())
							.getStateCode()).getStateProvinceName();
			log.info("msg>>province" + province);
			String[] obj = new String[] { pdSendInfo.getRecipientName(),
					pdSendInfo.getSiNo(),
					Integer.toString(cal.get(Calendar.MONTH) + 1),
					Integer.toString(cal.get(Calendar.DAY_OF_MONTH)), province,
					pdSendInfo.getTrackingNo() };
			log.info("msg>>obj" + obj);
			String msg = jamMsnModuleManager.getJamMsnModeulBySql(pdSendInfo
					.getCustomer().getUserCode(), "msg.ship", obj);
			log.info("msg>>" + msg);
			
			if (msg != null) {
				
				SmsSendLog smsSendLog = new SmsSendLog();
				smsSendLog.setMobile(pdSendInfo.getRecipientMobile());
				smsSendLog.setSendMsg(msg);
				smsSendLog.setSendNum(Long.toString(cal.getTimeInMillis()));
				smsSendLog.setStatus("1");
				smsSendLog.setUserCode(pdSendInfo.getCustomer().getUserCode());
				
				JMSContextUtil.getJMSSender().sendObjecsMessage(smsSendLog);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("getMsg="+e.getMessage());
			e.printStackTrace();
		}finally{
			log.info("sendMsg>>end>>");
		}
	}

	public List getShippingReportListByCompany(CommonRecord crm) {
		// TODO Auto-generated method stub
		List ret = new ArrayList();
		String companyCode = crm.getString("companyCode");
		if ("CN".equals(companyCode)) {
			ret = this.getCnShippingReportList(crm);
		}else if ("TW".equals(companyCode)) {
			ret = this.getTwShippingReportList(crm);
		}
		return ret;
	}

	
	public List getSunShipmentsByCrm(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		return dao.getSunShipmentsByCrm(crm, pager);
	}

	

	

	/**
	 * @see com.joymain.jecs.pd.service.PdSendInfoManager#getPdSendInfos(com.joymain.jecs.pd.model.PdSendInfo)
	 */
	public List getPdSendInfos(final PdSendInfo pdSendInfo) {
		return dao.getPdSendInfos(pdSendInfo);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdSendInfoManager#getPdSendInfo(String
	 *      siNo)
	 */
	public PdSendInfo getPdSendInfo(final String siNo) {
		return dao.getPdSendInfo(new String(siNo));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdSendInfoManager#savePdSendInfo(PdSendInfo
	 *      pdSendInfo)
	 */
	public void savePdSendInfo(PdSendInfo pdSendInfo) {
		
		//modify fu 2015-09-16 发货单的暂不发货，暂停发货，正常发货这三个发货方式的变化，现在通过发货单基本信息编辑接口实现 所以这个接口的功能屏蔽掉。
		/*//shipTypeNow
		//原来发货方式和现在不同的话，就向其他系统推送接口；
		String shipTypeNow = pdSendInfo.getShipType();//现在发货方式
		String hipTypeOld = pdSendInfo.getShipmentIdentificationNumber();//原来发货方式
		//原来发货方式和现在发货方式都是正常发货，这个就不用推送接口数据
		if(StringUtil.isEmpty(shipTypeNow)&& StringUtil.isEmpty(hipTypeOld)){
			
		}
		//现在是正常发货，原来是暂不发货或暂停发货，那么这个需要推送接口数据
		else if(StringUtil.isEmpty(shipTypeNow)&& (!StringUtil.isEmpty(hipTypeOld))&&("2".equals(hipTypeOld)||"3".equals(hipTypeOld))){
					try {
						this.reSetPdSendInfoStopOrNo(pdSendInfo.getSiNo(),pdSendInfo.getShipType());
					} catch (Exception e) {
						e.printStackTrace();
					}
		}
		//现在是暂不发货或暂停发货，原来是正常发货，那么这个需要推送接口数据
		else if((!StringUtil.isEmpty(shipTypeNow))&&("2".equals(shipTypeNow)||"3".equals(shipTypeNow)) && StringUtil.isEmpty(hipTypeOld)){
					try {
						this.reSetPdSendInfoStopOrNo(pdSendInfo.getSiNo(),pdSendInfo.getShipType());
					} catch (Exception e) {
						e.printStackTrace();
					}
		}else{
			if(shipTypeNow.equals(hipTypeOld)){//现在发货方式与原来发货方式相同，不推送发货单发货方式的接口
			}else{
				try {
					this.reSetPdSendInfoStopOrNo(pdSendInfo.getSiNo(),pdSendInfo.getShipType());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}*/
		//modify fu 2015-09-16 发货单的暂不发货，暂停发货，正常发货这三个发货方式的变化，现在通过发货单基本信息编辑接口实现 所以上面接口的功能屏蔽掉。

		
		//在发货单状态发生变更之前，判断有没有DO单过来，有的话，那么就不允许修改发货单 modify fu 2015-09-16
		
		Integer orderFlag = pdSendInfo.getOrderFlag();
		if((null!=orderFlag)&& (orderFlag>=2)){//发货确认之后的发货单状态，就不需要向后续系统推送接口数据，同时也不需要判断有没有DO单过来。
			pdSendInfo.setShipmentIdentificationNumber("");//用这个值，在前台判断发货单有没有DO单传过来。haveDo表示有DO单过来,空值表示没有DO单过来
			dao.savePdSendInfo(pdSendInfo);
		}else{
			boolean haveDo = pdLogisticsBaseManager.getDoResult(pdSendInfo.getSiNo());
			String orderNo = pdSendInfo.getOrderNo();
			boolean noMo = true;
			 if(!StringUtil.isEmpty(orderNo)){
				 String mo = orderNo.substring(0, 2);
				 if("M0".equals(mo)){
					 noMo = false;
				 }
			 }
			String ShipmentHaveDo = pdSendInfo.getShipmentIdentificationNumber();
			//订单关联的发货单有DO单  或  发货退回单和换货单生成的发货单有发货状态回传---这两种情况不允许对发货单进行编辑
			if(haveDo || (noMo && "haveDo".equals(ShipmentHaveDo))){
				 PdSendInfo pdSendInfoDBA = dao.getPdSendInfoDBA(pdSendInfo);
				 dao.savePdSendInfo(pdSendInfoDBA);
			}else{
				
				pdSendInfo.setShipmentIdentificationNumber("");//用这个值，在前台判断发货单有没有DO单传过来。haveDo表示有DO单过来,空值表示没有DO单过来
				//在发货单编辑保存之前去WMS询问发货单能否编辑----begin modify by fu 2016-03-21
				String methodNameWMS = "checkstatus";
				String typeWMS = "11";
				String canDelete = pdWarehouseStockManager.getPdLogisticsCanModify(pdSendInfo.getSiNo(), typeWMS, methodNameWMS);
				if((!StringUtil.isEmpty(canDelete))&&(!"allow".equals(canDelete))){
					PdSendInfo pdSendInfoDBA = dao.getPdSendInfoDBA(pdSendInfo);
					//modify by fu 20160627 发货单作废功能优化（接口询问机制，如果WMS放回XXX发货单已撤单，那么EC就将对应的发货单设置成发货作废状态）--begin
					String cdbz = "发货单已撤单";//WMS撤单标志
					if(!StringUtil.isEmpty(cdbz)){
						int cd = canDelete.indexOf(cdbz);
						if(cd!=(-1)){
							pdSendInfoDBA.setShipType("4");//发货作废
						}
					}
					//modify by fu 20160627 发货单作废功能优化（接口询问机制，如果WMS放回XXX发货单已撤单，那么EC就将对应的发货单设置成发货作废状态）--end

					dao.savePdSendInfo(pdSendInfoDBA);
					return;
				}
				//在发货单编辑保存之前去WMS询问发货单能否编辑----end modify by fu 2016-03-21
				
				dao.savePdSendInfo(pdSendInfo);
				//modify fu 2015-09-16 发货单编辑接口
				this.getPdSendInfoEditInterfaceResult(pdSendInfo);
			}
		}
	}


    /**
     * 获取发货单编辑接口的对象
     * @author fu 2015-09-16 
     * @param pdSendInfo
     * @return string 
     */
	public String getPdSendInfoEditInterfaceResult(PdSendInfo pdSendInfo){
	 try{
		    log.info("发货单编辑接口-在PdSendInfoManagerImpl类的getPdSendInfoEditInterfaceResult方法中开始运行");
		    		    
			Integer orderFlag = pdSendInfo.getOrderFlag();
			String canDo = pdSendInfo.getCanDo();
			if((null!=orderFlag) && ((orderFlag == 0)||(orderFlag == 1)||((-1) == orderFlag))){
				PdSendInfoEdit pdSendInfoEdit = new PdSendInfoEdit();
				pdSendInfoEdit.setSiNo(pdSendInfo.getSiNo());
				pdSendInfoEdit.setOrderNo(pdSendInfo.getOrderNo());
				pdSendInfoEdit.setWarehouseNo(pdSendInfo.getWarehouseNo());
				pdSendInfoEdit.setRecipientName(pdSendInfo.getRecipientName());
				pdSendInfoEdit.setRecipientCaNo(pdSendInfo.getRecipientCaNo());
				pdSendInfoEdit.setCity(pdSendInfo.getCity());
				pdSendInfoEdit.setRecipientZip(pdSendInfo.getRecipientZip());
				pdSendInfoEdit.setRecipientAddr(pdSendInfo.getRecipientAddr());
				pdSendInfoEdit.setRecipientPhone(pdSendInfo.getRecipientPhone());
				pdSendInfoEdit.setRecipientMobile(pdSendInfo.getRecipientMobile());
				pdSendInfoEdit.setShipType(pdSendInfo.getShipType());
				pdSendInfoEdit.setShNo(pdSendInfo.getShNo());
				if((null!=orderFlag)&&(orderFlag == 1)){
					pdSendInfoEdit.setCanDo("");
				}else if((null!=orderFlag)&&((-1) == orderFlag)){
					pdSendInfoEdit.setCanDo("N");
				}else if((null!=orderFlag)&&(orderFlag == 0)){
					pdSendInfoEdit.setCanDo("N");
				}else{
					pdSendInfoEdit.setCanDo(canDo);//N表示不可以生成DO单，空值表示可以生成DO单
				}
				pdSendInfoEdit.setHurryFlag(pdSendInfo.getHurryFlag());//加急发货 modify fu 2015-10-29 
				JSONObject jo = JSONObject.fromObject(pdSendInfoEdit);
				String joString = jo.toString();
				
				//---------------调用接口，将数据推送给OMS
				MsgSendService msgSendService = new MsgSendService();
				msgSendService.setSender(Constants.OMS_SEND);//OMS平台
		
				//方法名-----OMS那边的接口名称
				String methodName = "ship.loEdit";
				
			    //String aa = "123";
				
				//modify by fu 2017-1-18 发货单编辑接口注释掉
			   /* String aa =	msgSendService.post(joString, methodName);
			    log.info("发货单编辑接口-在PdSendInfoManagerImpl类的getPdSendInfoEditInterfaceResult方法中运行：接口推送数据结束.");
				//=============================接口日志写入开始 Modify By WUCF 20150123
				JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
				jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
				jocsInterfaceLog.setSender(Constants.OMS_SEND);//数据的接收方
				jocsInterfaceLog.setMethod(methodName);//方法名称
				jocsInterfaceLog.setContent(joString.toString());//发送内容content
				jocsInterfaceLog.setReturnDesc(aa);//返回内容
				
				ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
				//=============================接口日志写入完毕	
*/			}
			return null;
	 }catch(Exception e){
		 e.printStackTrace();
		 log.info("发货单编辑接口-在PdSendInfoManagerImpl类的getPdSendInfoEditInterfaceResult方法中运行出现异常："+e.toString());
		 return "haveException";
	 }
	 
	}



	/**
	 * @see com.joymain.jecs.pd.service.PdSendInfoManager#removePdSendInfo(String
	 *      siNo)
	 */
	public void removePdSendInfo(final String siNo) {
		dao.removePdSendInfo(new String(siNo));
	}

	// added for getPdSendInfosByCrm
	public List getPdSendInfosByCrm(CommonRecord crm, Pager pager) {
		return dao.getPdSendInfosByCrm(crm, pager);
	}

	public BigDecimal getUPSRateByOrder(JpoMemberOrder order) throws Exception {
		// TODO Auto-generated method stub
		UpsInteractiveLog upsInteractiveLog = new UpsInteractiveLog();
		upsInteractiveLog.setCreateTime(new Date());
		upsInteractiveLog.setOrderNo(order.getMemberOrderNo());
		upsInteractiveLog.setType("rate");
		
		BigDecimal totalRate = new BigDecimal(0);
		Map cityMap =alCityManager.getAlCityMap(order.getCompanyCode());
		Map stateMap = alStateProvinceManager.getAlStateCodeMap(order.getCompanyCode());
		String serviceUrl = AccessUtil.RATE_SERVER_URL;
//		HttpURLConnection con=UpsConnection.getUpsConnection(serviceUrl);
//		OutputStream out = con.getOutputStream();
		AccessRequest accessRequest = new AccessRequest(); 
		AccessUtil.populateAccessRequest(accessRequest);
		String accessRequestXML = XmlTool.getXMLFromObjectNew(AccessRequest.class, accessRequest);
		
		RatingServiceSelectionRequest ratingServiceSelectionRequest = new RatingServiceSelectionRequest();
		RateUtil.populateRatingServiceSelectionRequest(ratingServiceSelectionRequest, order, cityMap, stateMap);
		String requestXML = XmlTool.getXMLFromObjectNew(RatingServiceSelectionRequest.class,ratingServiceSelectionRequest);
		String responseXML = UpsConnection.contactService(serviceUrl, accessRequestXML+requestXML);
//		UpsConnection.setRequest(out, accessRequestXML+requestXML);
		upsInteractiveLog.setSend(accessRequestXML+requestXML);
		upsInteractiveLog.setReceive(responseXML);
		log.info("requestXML="+accessRequestXML+requestXML);
		log.info("responseXML="+responseXML);
		upsInteractiveLogManager.saveUpsInteractiveLog(upsInteractiveLog);
		RatingServiceSelectionResponse response =(RatingServiceSelectionResponse) XmlTool.getResponseFromXML(RatingServiceSelectionResponse.class, responseXML);
		
		
		if("1".equals(response.getResponse().getResponseStatusCode())){
			List list = response.getRatedShipment();
			for(int i=0;i<list.size();i++){
				RatedShipmentType rate = (RatedShipmentType) list.get(i);
				totalRate=totalRate.add(new BigDecimal(rate.getTotalCharges().getMonetaryValue()));
			}
		}else{
			throw new AppException(response.getResponse().getResponseStatusDescription());
		}
		return totalRate;
	}

	private void unSellProduct(JpmProductSaleNew jpmProductSale){
		jpmProductSale.setStatus("0");
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		/*jpmProductSaleManager.saveJpmProductSale(jpmProductSale);*/
		
	}
	public void upsAcceptRequest(PdSendInfo pdSendInfo) throws Exception {
		// TODO Auto-generated method stub
		UpsInteractiveLog upsInteractiveLog = new UpsInteractiveLog();
		upsInteractiveLog.setCreateTime(new Date());
		upsInteractiveLog.setOrderNo(pdSendInfo.getSiNo());
		upsInteractiveLog.setType("shipAccept");
//		Map cityMap =alCityManager.getAlCityMap(pdSendInfo.getCompanyCode());
//		Map stateMap = alStateProvinceManager.getAlStateCodeMap(pdSendInfo.getCompanyCode());
		String serviceUrl = AccessUtil.SHIPACCEPT_SERVER_URL;
		
		AccessRequest accessRequest = new AccessRequest(); 
		AccessUtil.populateAccessRequest(accessRequest);
		String accessRequestXML = XmlTool.getXMLFromObjectNew(AccessRequest.class, accessRequest);
	
		ShipmentAcceptRequest request = new ShipmentAcceptRequest();
		ShipAcceptUtil.populateShipAcceptRequest(request, pdSendInfo);
		String requestXML = XmlTool.getXMLFromObjectNew(ShipmentAcceptRequest.class,request);
		String responseXML = UpsConnection.contactService(serviceUrl, accessRequestXML+requestXML);
		upsInteractiveLog.setSend(accessRequestXML+requestXML);
		upsInteractiveLog.setReceive(responseXML);
		log.info("requestXML="+accessRequestXML+requestXML);
		log.info("responseXML="+responseXML);
		try {
			upsInteractiveLogManager.saveUpsInteractiveLog(upsInteractiveLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShipmentAcceptResponse response = (ShipmentAcceptResponse) XmlTool.getResponseFromXML(ShipmentAcceptResponse.class, responseXML);
		
		
		if("1".equals(response.getResponse().getResponseStatusCode())){
			pdSendInfo.setOrderFlag(2);
//			pdSendInfo.setShipmentDigest(shipmentDigest);
//			pdSendInfo.setTrackingNo(shipmentIdentificationNumber);
			
			
			dao.savePdSendInfo(pdSendInfo);
			List<PackageResults> list =response.getShipmentResults().getPackageResults();
			Set set = pdSendInfo.getPdSendInfoDetails();
			Iterator iterator = set.iterator();
			for(int i=0;i<list.size();i++){
				PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
				PackageResults pack = list.get(i);
				pdSendInfoDetail.setLabelCode(pack.getLabelImage().getGraphicImage());
				pdSendInfoDetail.setTrackingNo(pack.getTrackingNumber());
				pdSendInfoDetailDao.saveObject(pdSendInfoDetail);
			}
			
		}else{
			throw new AppException(response.getResponse().getResponseStatusDescription());
		}
		
	}

	public void upsConfirmRequest(PdSendInfo pdSendInfo) throws Exception {
		// TODO Auto-generated method stub
		UpsInteractiveLog upsInteractiveLog = new UpsInteractiveLog();
		upsInteractiveLog.setCreateTime(new Date());
		upsInteractiveLog.setOrderNo(pdSendInfo.getSiNo());
		upsInteractiveLog.setType("shipConfirm");
		Map cityMap =alCityManager.getAlCityMap(pdSendInfo.getCompanyCode());
		Map stateMap = alStateProvinceManager.getAlStateCodeMap(pdSendInfo.getCompanyCode());
		String serviceUrl = AccessUtil.SHIPCONFIRM_SERVER_URL;
		AccessRequest accessRequest = new AccessRequest(); 
		AccessUtil.populateAccessRequest(accessRequest);
		String accessRequestXML = XmlTool.getXMLFromObjectNew(AccessRequest.class, accessRequest);
		
		ShipmentConfirmRequest request = new ShipmentConfirmRequest();
		ShipConfirmUtil.populateShipConfirmRequest(request,pdSendInfo,cityMap,stateMap);
		String requestXML = XmlTool.getXMLFromObjectNew(ShipmentConfirmRequest.class,request);
		String responseXML = UpsConnection.contactService(serviceUrl, accessRequestXML+requestXML);
		upsInteractiveLog.setSend(accessRequestXML+requestXML);
		upsInteractiveLog.setReceive(responseXML);
		log.info("requestXML="+accessRequestXML+requestXML);
		log.info("responseXML="+responseXML);
		try {
			upsInteractiveLogManager.saveUpsInteractiveLog(upsInteractiveLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ShipmentConfirmResponse response = (ShipmentConfirmResponse) XmlTool.getResponseFromXML(ShipmentConfirmResponse.class, responseXML);
		
		
		if("1".equals(response.getResponse().getResponseStatusCode())){
			String shipmentDigest = response.getShipmentDigest();
			String shipmentIdentificationNumber = response.getShipmentIdentificationNumber();
			pdSendInfo.setShipmentDigest(shipmentDigest);
			pdSendInfo.setTrackingNo(shipmentIdentificationNumber);
			
			dao.savePdSendInfo(pdSendInfo);
		}else{
			String error="";
			List<com.ups.jaxb.shipconfirm.response.Error> errors = response.getResponse().getError();
			for(int i=0;i<errors.size();i++){
				error+="["+errors.get(i).getErrorDescription()+"]";
			}
			throw new AppException(error);
		}
		
		
	}

	public void upsVoidRequest(PdSendInfo pdSendInfo) throws Exception {
		// TODO Auto-generated method stub
		UpsInteractiveLog upsInteractiveLog = new UpsInteractiveLog();
		upsInteractiveLog.setCreateTime(new Date());
		upsInteractiveLog.setOrderNo(pdSendInfo.getSiNo());
		upsInteractiveLog.setType("shipVoid");
		
		String serviceUrl = AccessUtil.SHIPVOID_SERVER_URL;
		AccessRequest accessRequest = new AccessRequest(); 
		AccessUtil.populateAccessRequest(accessRequest);
		String accessRequestXML = XmlTool.getXMLFromObjectNew(AccessRequest.class, accessRequest);
		
		VoidShipmentRequest  request = new VoidShipmentRequest();
		VoidUtil.populatevoidRequest(request, pdSendInfo);
		
		String requestXML = XmlTool.getXMLFromObjectNew(VoidShipmentRequest.class,request);
		String responseXML = UpsConnection.contactService(serviceUrl, accessRequestXML+requestXML);
		
		upsInteractiveLog.setSend(accessRequestXML+requestXML);
		upsInteractiveLog.setReceive(responseXML);
		try {
			upsInteractiveLogManager.saveUpsInteractiveLog(upsInteractiveLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VoidShipmentResponse response = (VoidShipmentResponse) XmlTool.getResponseFromXML(VoidShipmentResponse.class, responseXML);
		
	}

	public void doWhileVoidOrder(JpoMemberOrder order) {
		// TODO Auto-generated method stub
		PdSendInfo pdSendInfo = this.getPdSendInfoByOrderNo(order.getMemberOrderNo());
		if(pdSendInfo!=null){
			if(pdSendInfo.getOrderFlag()>1){
				throw new AppException("can.not.return"); 
			}else{
				dao.removePdSendInfo(pdSendInfo.getSiNo());
			}
		}
	}

	public PdSendInfo getPdSendInfoByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		PdSendInfo pdSendInfo=null;
		CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("orderNo", orderNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = dao.getPdSendInfosByCrm(crm, null);
		if(list.size()>0){
			 pdSendInfo = (PdSendInfo) list.get(0);
		}
		return pdSendInfo;
	}
	
	private void callBack(PdSendInfo pdSendInfo){
		if("1".equals(pdSendInfo.getOrderType())&& "JP".equals(pdSendInfo.getCompanyCode())){
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrderByMemberOrderNo(pdSendInfo.getOrderNo());
			jpoMemberOrder.setIsPay("1");
			jpoMemberOrderDao.saveJpoMemberOrder(jpoMemberOrder);
		}
	}

	public void checkPdSendInfo(PdSendInfo pdSendInfo) throws Exception {
		// TODO Auto-generated method stub
		/*if("US".equals(pdSendInfo.getCompanyCode())&&"UPS".equalsIgnoreCase(pdSendInfo.getShNo())){	
				this.upsConfirmRequest(pdSendInfo);		
		}else{
			dao.savePdSendInfo(pdSendInfo);
		}*/
		if("US".equals(pdSendInfo.getCompanyCode())){
			try {
				getPdsendInfoInterface(pdSendInfo);
			} catch (Exception e) {
				
				// TODO Auto-generated catch block
				log.equals("erro!getPdsendInfoInterface");
			}
		}
		this.savePdSendInfo(pdSendInfo);
		
	}
	
	private void sendEmail(JpoMemberOrder jpoMemberOrder){
		mailMessage.setTo(jpoMemberOrder.getEmail());
		mailMessage.setBcc("foruforo@msn.com");
		Map model = new HashMap();
		model.put("firstName", jpoMemberOrder.getFirstName());
		model.put("orderNo", jpoMemberOrder.getMemberOrderNo());
		model.put("details", jpoMemberOrder.getJpoMemberOrderList());
		mailEngine.send( mailMessage, "order.fmt", model);
	}
	
	private void separateOrder(JpoMemberOrder order){
		Set orders = order.getJpoMemberOrderList();
		
	}  
	


	/**@author 罗婷
		 * @param jpoMemberOrder
		 * 发送邮件
		 */
		public void getSendEmail(JpoMemberOrder jpoMemberOrder) {
			try {
				mailMessage.setTo(jpoMemberOrder.getEmail());
				mailMessage.setCc("109629728@qq.com");
				mailMessage.setSubject("订单详细信息");
				mailMessage.setFrom("luoting");
				// mailMessage.setBcc("foruforo@msn.com");
				// mailMessage.setBcc("xiaoluo1980126@yahoo");
				Map model = new HashMap();
				model.put("firstName", jpoMemberOrder.getFirstName());
				model.put("orderNo", jpoMemberOrder.getMemberOrderNo());
				model.put("jpoMemberOrder", jpoMemberOrder);
				//订单状态
				Map memberTypeMapO = Constants.sysListMap.get("po.status");
				String[] values = (String[]) memberTypeMapO.get(jpoMemberOrder
						.getStatus());
				model.put(
						"state",
						Constants.localLanguageMap.get(
								jpoMemberOrder.getSysUser()
										.getDefCharacterCoding())
								.get(values[0]));
				//付款方式
				Map memberTypeMapP = Constants.sysListMap.get("po.paymode");
				if (jpoMemberOrder.getPayMode() != null) {
					String[] valuePayM = (String[]) memberTypeMapP
							.get(jpoMemberOrder.getPayMode());
					System.out.println("我的值是====" + valuePayM);
					if (valuePayM != null && valuePayM.length != 0) {
						model.put(
								"paymentM",
								Constants.localLanguageMap.get(
										jpoMemberOrder.getSysUser()
												.getDefCharacterCoding()).get(
										valuePayM[0]));
					} else {
						model.put("paymentM", "没有选择支付方式");
					}
				} else {
					model.put("paymentM", "没有选择支付方式");
				}
				//获取省份
				AlStateProvince alProvince = alStateProvinceManager
						.getAlStateProvince(jpoMemberOrder.getProvince());
				if (alProvince != null) {
					model.put("alProvince", alProvince.getStateProvinceName());
				}
				//获取城市
				AlCity alCity = alCityManager.getAlCity(jpoMemberOrder
						.getCity());
				if (alCity != null) {
					model.put("alCity", alCity.getCityName());
				}
				//获取地区
				if (jpoMemberOrder.getDistrict() != null) {
					AlDistrict alDistrct = alDistrictManager
							.getAlDistrict(jpoMemberOrder.getDistrict());
					if (alDistrct != null) {
						model.put("alDistrct", alDistrct.getDistrictName());
					}
				} else {

					model.put("alDistrct", " ");
				}
				//
				BigDecimal total = new BigDecimal(0);
				total = total.add(jpoMemberOrder.getAmount());
				BigDecimal taxFee = new BigDecimal(0);
				BigDecimal awardFee = new BigDecimal(0);
				BigDecimal shippingFee = new BigDecimal(0);
				for (Object obj : jpoMemberOrder.getJpoMemberOrderFee()) {
					JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) obj;

					if (jpoMemberOrderFee.getFeeType() == "1") {
						shippingFee = shippingFee.add(jpoMemberOrderFee
								.getFee());
						total = total.add(jpoMemberOrderFee.getFee());
					}

					else if (jpoMemberOrderFee.getFeeType() == "9") {
						awardFee = awardFee.add(jpoMemberOrderFee.getFee());
						total = total.add(jpoMemberOrderFee.getFee());
					} else {
						taxFee = taxFee.add(jpoMemberOrderFee.getFee());
						total = total.add(jpoMemberOrderFee.getFee());
					}
				}
				model.put("shipping", shippingFee);
				model.put("award", awardFee);
				model.put("tax", taxFee);
				BigDecimal sub_total = new BigDecimal(0);
				for (Object obj : jpoMemberOrder.getJpoMemberOrderList()) {
					JpoMemberOrderList orderList = (JpoMemberOrderList) obj;
					sub_total = sub_total.add(orderList.getPrice().multiply(
							new BigDecimal(orderList.getQty())));
				}
				model.put("sub_total", sub_total);
				model.put("total", total);
				mailEngine.send(mailMessage, "order2.fmt", model);
			} catch (Exception e) {
				// TODO: handle exception
				throw new AppException("error.email");
			}
		}

		/**
		 * @author 罗婷
		 * @param pdsendInfo
		 * 
		 */
		public void getPdsendInfoInterface(PdSendInfo pdsi) {
			Map stateMap = alStateProvinceManager.getAlStateCodeMap("US");
			PdSendInfo pdsendInfo = this.getPdSendInfo(pdsi.getSiNo());
			Object[] reply = null;
			try {
				// 解析soap
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory
						.newInstance();
				org.apache.cxf.endpoint.Client client = dcf
						.createClient("http://www.integracoreb2b.com/IntCore/IncomingOrders.asmx?wsdl");
				// 打印发送过去的信息
				client.getInInterceptors().add(
						new org.apache.cxf.interceptor.LoggingInInterceptor());
				client.getOutInterceptors().add(
						new org.apache.cxf.interceptor.LoggingOutInterceptor());

				// 发送Header的验证信息
				List<Header> headers = new ArrayList<Header>();
				SOAPFactory sf = SOAPFactory.newInstance();
				SOAPElement authElement = sf.createElement(new QName(
						"http://www.integracoreb2b.com/", "AuthHeader"));
				SOAPElement userElement = authElement.addChildElement("Username");
				SOAPElement pwdElement = authElement.addChildElement("Password");
				SOAPElement testElement = authElement.addChildElement("Test");
				userElement.addTextNode("Joylife");
				pwdElement.addTextNode("L!v3lif3");
				testElement.addTextNode("true");
				SoapHeader authHeader = new SoapHeader(new QName(
						"http://www.integracoreb2b.com/", "AuthHeader"),
						authElement);
				headers.add(authHeader);
				client.getRequestContext().put(Header.HEADER_LIST, headers);
				// 获取订单详细信息
				Order order = new Order();
				order.setOrderNumber(pdsendInfo.getSiNo());
				order.setShipToCustID(pdsendInfo.getCustomer().getUserCode());
				order.setBillToCustID(pdsendInfo.getCustomer().getUserCode());
				order.setCreateDate(DateUtil
						.convertToXMLGregorianCalendar(new Date()));
				order.setRequiredShipDate(DateUtil
						.convertToXMLGregorianCalendar(new Date()));
				order.setCarrier("FDX");
				order.setCarrierServiceCode("FDX 11");
				order.setThirdPartyAccountNumber(null);
				order.setPrepaidOrCollect("3");
				order.setShipToContact(pdsendInfo.getRecipientPhone());
				order.setShipToName(pdsendInfo.getRecipientName());
				order.setShipToAddr1(pdsendInfo.getRecipientAddr());
				order.setShipToCity(pdsendInfo.getCityName());
				order
						.setShipToState((String) stateMap.get(pdsi
								.getRecipientCaNo()));// 待定
				order.setShipToZip(pdsendInfo.getRecipientZip());
				order.setShipToCountry("United States");
				order.setShipToTelephone(pdsendInfo.getRecipientPhone());
				order.setShipToFax(null);
				order.setCustPoNum(null);
				order.setBillToPoNum(null);
				order.setRecipientEmailAddress(pdsendInfo.getEmail());
				order.setCustomData1(null);
				order.setCustomData2(null);
				order.setCustomData3(null);
				order.setDropShip("T");
				order.setResidentialFlag(null);
				ArrayOfOrderDetail oDetailList = new ArrayOfOrderDetail();
				if (pdsendInfo.getPdSendInfoDetails() != null) {

					for (Object obj : pdsendInfo.getPdSendInfoDetails()) {
						PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) obj;
						OrderDetail orderDetail = new OrderDetail();
						orderDetail.setItemID(pdSendInfoDetail.getProductNo());
						orderDetail.setQuantity(pdSendInfoDetail.getQty());
						orderDetail.setSalePrice(pdSendInfoDetail.getPrice()
								.floatValue());
						oDetailList.getOrderDetail().add(orderDetail);
					}
				}
				order.setOrderDetails(oDetailList);
				ArrayOfOrder orderList = new ArrayOfOrder();
				orderList.getOrder().add(order);
				// 返回的结果
				reply = client.invoke("OrderImport", new Object[] { orderList });
				if (reply != null) {
					OrderResults results = (OrderResults) reply[0];
					if (results != null) {
						System.out.println("total：" + results.getError());
						;
						ArrayOfOrderResult orderMessage = results.getOrderMessage();
						List<OrderResult> orderResult = orderMessage
								.getOrderResult();
						for (OrderResult result : orderResult) {
							System.out
									.println(result.getOrderNumber() + " - "
											+ result.getError() + '-'
											+ result.isAccepted());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}



		public void excuteShipTask(Long moId){
			JpoMemberOrder order=jpoMemberOrderDao.getJpoMemberOrder(moId);
			if(!"1".equals(order.getIsShipping())){
				
			}
		}
		public void excuteShipTask() {
			log.info("excuteShipTask>>>开始本轮执行>>>"+(new Date()));
			synchronized (lock) {
				log.info("excuteShipTask>>>开始执行at:>>>"+ (new Date()));
//					int count=20;
					// TODO Auto-generated method stub
					CommonRecord crm = new CommonRecord();
					try {
						crm.setValue("isShipping", 1);
						crm.setValue("shippingPay", 1);
						crm.setValue("checkTimeF", "2013-3-20");
						crm.setValue("status", 2);
					} catch (Exception e) {
						// TODO: handle exception
						throw new AppException("excuteShipTask...error");
					}
					List<JpoMemberOrder> jpoMemberOrders = jpoMemberOrderDao.getJpoMemberOrdersByCrm(crm,null);
					for(int i=0;i<jpoMemberOrders.size();i++){
						if(i<100){
							JpoMemberOrder order = jpoMemberOrders.get(i);
							log.info("excuteShipTask>>>excute:>>>"+order.getMemberOrderNo()+">>>"+i);
							
							order.setIsShipping("1");
							doWhileOrderConfirmed(order);
						}else{
							break;
						}
					}
				
			}
			log.info("excuteShipTask>>>结束本轮执行at:>>>"+ (new Date()));
		}


		/**
		 * 判断物流公司是否存在
		 * @param listCode:list编码
		 * @param valueCode：list值
		 * @return
		 */
		public List jsysListValues(String listCode,String valueCode) {
			return dao.jsysListValues(listCode,valueCode);
		}
		
		public Map<String,String> getAlCityMap(String companyCode) {
			// TODO Auto-generated method stub
			Map<String,String> map = new HashMap();
			List list = dao.getAlCitysByCountry(companyCode);
			for(int i=0;i<list.size();i++){
				AlCity alCity = (AlCity) list.get(i);
				map.put(alCity.getCityId().toString(), alCity.getCityName());
			}
			
			return map;
		}


		/**
		 * 根据国家获取对应的省/州Map<code,name>
		 * @param countryCode
		 */
		public Map getAlStateProvincesMapByCountry(String countryCode) {
		    List<AlStateProvince> alStateProvinces=this.getAlStateProvincesByCountry(countryCode);
		    Map<String, String> map=new LinkedHashMap<String, String>();
		    if(alStateProvinces!=null){
		    	for(AlStateProvince alStateProvince:alStateProvinces){
		    		map.put(alStateProvince.getStateProvinceId().toString(), alStateProvince.getStateProvinceName());
		    	}
		    }
		    return map;
	    }
		
		/**
		 * 根据国家获取对应的省/州列表
		 * @param countryCode
		 * @return
		 */
		public List getAlStateProvincesByCountry(final String countryCode){
			return dao.getAlStateProvincesByCountry(countryCode);
		}
		
		/**
		 * 根据国家获取对应的省/州列表
		 * @param countryCode
		 * @return
		 */
		public List getCompanyCode(){
			return dao.getCompanyCode();
		}


		/**
		 * 修改指定区域、商品编号的库存警戒量
		 * @param companyCode：区域
		 * @param productNo：商品编码
		 * @param storageCordon：警戒库存量
		 * @return
		 */
		public int updateStorageCordon(String companyCode, String productNo,String storageCordon) {
			return dao.updateStorageCordon(companyCode,productNo,storageCordon);
		}
		

		public AlCity getAlCity(Long cityId) {
			 return dao.getAlCity(cityId);
		}

		/**
		 * 查询发货单信息
		 * @return
		 */
		public String getPdSendInfoJsons() { 
//			List list = dao.getPdSendInfos();
//			JSONArray jArray=JSONArray.fromObject(list);
//			return jArray.toString();
			return "";
		}
		
		/**
		 * 查询发货单信息
		 * @return
		 */
		public String getPdSendInfoDetailJsons() {
			List list = dao.getPdSendInfoDetails();
//			JSONArray jArray=new JSONArray();
//			jArray.put(list);
//			return jArray.toString();
			return "";
		}



		/**
		 * Add By WuCF
		 * 定时任务，自动处理暂不发货发送短信
		 * @return
		 */
		public void checkSmssendTransferTask() {
			List<Map<String,String>> list = dao.checkSmssendTransferTask();
			String memberOrderNo = "";
			String mobileTele = "";
			String userCode = "";
			for(Map<String,String> map : list){
				//1.得到值
				memberOrderNo = map.get("MEMBER_ORDER_NO");
				mobileTele = map.get("MOBILETELE");
				userCode = map.get("USER_CODE");
				
				//2.发送短信，并写到数据库
				SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日");   
				String dateStr = dateformat2.format(new Date());
				
				StringBuffer message = new StringBuffer("亲爱的中脉家人，您的会员号");
				message.append(userCode);
				message.append("，发货单号");
				message.append("，已于"+dateStr+"发出。");
				message.append("物流公司：");
				message.append("。请您查收，并注意开箱验货。");
				log.info("发货短信message:"+message);
				String url1 = "";
				String url2 = "";
				//SmsSend.sendSms(url1,url2,mobileTele, message.toString());
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("userCode", userCode);
				jsonObject.put("dateStr", dateStr);
				jsonObject.put("invoiceNo", "");
				jsonObject.put("companyName", "");
				String templateCode =  ConfigUtil.getConfigValue("CN","sms.templatecode.pdsendinfo");//阿里云短信控制台创建的签名名称
				String accessKeyId = (String)ConfigUtil.getConfigValue("CN","sms.accesskeyid");
				String accessKeySecret = (String) ConfigUtil.getConfigValue("CN","sms.accesskeysecret");
				String signName = (String) ConfigUtil.getConfigValue("CN","sms.signname");//阿里云短信控制台创建的签名名称
				
				
				String sendResult=SmsSend.sendAliSms(jsonObject,mobileTele, templateCode, accessKeyId , accessKeySecret, signName);
			
				//将短信写入到数据库表
				JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
				jpmSmssendInfo.setSmsType("4");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种 4：仓储费提醒短信
				jpmSmssendInfo.setSmsRecipient(userCode);//短信接收人:用户会员编号
				jpmSmssendInfo.setSmsMessage(message.toString());//短信内容
				jpmSmssendInfo.setSmsTime(new Date());//发送时间
				jpmSmssendInfo.setSmsOperator("root");//操作人
				if(StringUtil.isEmpty(sendResult)){
					jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！

				}else{
					
					jpmSmssendInfo.setSmsStatus("0");
				}
				jpmSmssendInfo.setRemark("");//备注
				jpmSmssendInfo.setPhoneNum(mobileTele);//手机号码
				jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
			}
		}
		
		/**
		 * 查找发货单
		 */
		public List<PdSendInfo> getPdSendInfoList(String memberOrderNo){
			return dao.getPdSendInfoList(memberOrderNo);
		}

		/**
		 * 1.中策加入人数统计
		 * @param crm
		 * @return
		 */
		public List getZhongcenumByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getZhongcenumByCrm(crm);
		}
		
		/**
		 * 2.日业绩统计
		 * @param crm
		 * @return
		 */
		public List getDayPerformanceByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getDayPerformanceByCrm(crm);
		}
		
		/**
		 *  3.决策委省份统计
		 * @param crm
		 * @return
		 */
		public List getProvinceByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getProvinceByCrm(crm);
		}
		
		/**
		 * 查询临时表中的数据
		 * @param crm
		 * @return
		 */
		public List getZcwUsers(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getZcwUsers(crm);
		}
		
		/**
		 *  2.1领导人网体业绩
		 * @param crm
		 * @return
		 */
		public List getNetLeadByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getNetLeadByCrm(crm);
		}
		
		/**
		 *  2.2领导人区域业绩
		 * @param crm
		 * @return
		 */
		public List getAreaLeadByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getAreaLeadByCrm(crm);
		}

		/**
		 *  2.3领导人推荐网体加入人数
		 * @param crm
		 * @return
		 */
		public List getRecommendLeadByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getRecommendLeadByCrm(crm);
		}

		/**
		 *  2.430万大单数据
		 * @param crm
		 * @return
		 */
		public List getbigJpomemberorderByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getbigJpomemberorderByCrm(crm);
		}

		/**
		 *  2.5颐萃产品统计
		 * @param crm
		 * @return
		 */
		public List getHuicuiProductByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getHuicuiProductByCrm(crm);
		}

		/**
		 *  2.6云南团队保养贴
		 * @param crm
		 * @return
		 */
		public List getYunnanchongxiaoByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getYunnanchongxiaoByCrm(crm);
		}

		/**
		 *  2.7道和坛酒数订单
		 * @param crm
		 * @return
		 */
		public List getDaoheWineJpomemberorderByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getDaoheWineJpomemberorderByCrm(crm);
		}

		/**
		 *  2.8道和坛酒数退单数据
		 * @param crm
		 * @return
		 */
		public List getDaoheWineJprrefundByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getDaoheWineJprrefundByCrm(crm);
		}

		/**
		 *  2.9网络费物流费
		 * @param crm
		 * @return
		 */
		public List getJponetfeeByCrm(CommonRecord crm) {
			// TODO Auto-generated method stub
			return dao.getJponetfeeByCrm(crm);
		}
		
		/**
		 * 获取业体领导人安置网体首单5500PV及以上业绩
		 * @param crm
		 * @return
		 */
		public List getShiyeTiLingDaoShouDanByCrm(CommonRecord crm){
			return dao.getShiyeTiLingDaoShouDanByCrm(crm);
		}



		/**
		 * 换货单和发货退回单直接关联的发货单状态接口
		 * @author fx  2015-08-14 
		 * @param jsonString
		 * @return rspEntity
		 */
		public RspEntity reSetPdSendInfoStatus(String jsonString) {
			log.info("换货单和发货退回单直接关联的发货单状态接口一在PdSendInfoManagerImpl类的reSetPdSendInfoStatus方法中开始运行");
			RspEntity rspEntity = new RspEntity();
			try{
				String checkResult = this.getCheckPdSendInfoStatusjs(jsonString);
	            if(Constants.INTERFACE_NORMAL.equals(checkResult)){
	            	 Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
			         classMap.put("pdLogisticsBase_list", PdSendInfoStatus.class);
					 //将json字符串转换成java对象
			         PdSendInfoStatus pdSendInfoStatus =InterfaceJsonUtil.returnnoJsonToModel(jsonString,PdSendInfoStatus.class,classMap);
			         String siNo = pdSendInfoStatus.getSiNo();
			         String status = pdSendInfoStatus.getStatus();
			         PdSendInfo pdSendInfo = dao.getPdSendInfoForSiNo(siNo);
			         pdSendInfo.setShipmentIdentificationNumber("haveDo");//用这个值，在前台判断发货单有没有DO单传过来。
			         pdSendInfo.setCanDo("");
		            pdSendInfo.setShipType("0");
		            String inteferaceShNo = pdSendInfoStatus.getShNo();

					
					Integer orderFlag = pdSendInfo.getOrderFlag();
					if(null!=orderFlag && 2>orderFlag && "Y".equals(status)){
						pdSendInfo.setOrderFlag(2);
						pdSendInfo.setOkUsrCode("interface");
						pdSendInfo.setStockFlag("1");
						pdSendInfo.setOkTime(new Date());
						pdSendInfo.setLogisticsDo(pdSendInfoStatus.getLogisticsDo());
						pdSendInfo.setInteferaceShNo(inteferaceShNo);

						//modify by fu 2015-09-23 经需求重新定义，发货单改库存，但是不判断库存是否足够
						/*//modify fu 2015-09-23 判断库存-------------------begin
						Set sets = pdSendInfo.getPdSendInfoDetails();
						Iterator iterator = sets.iterator();
						while (iterator.hasNext()) {
							PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
							if (!pdWarehouseStockManager.enoughStock(pdSendInfo.getWarehouseNo(), pdSendInfoDetail
									.getProductNo(), pdSendInfoDetail.getQty())) {
								rspEntity.setSub_msg("EC product Insufficient inventory");
								return this.getRspEntityString(rspEntity);
							}
						}
						//modify fu 2015-09-23 判断库存------------------end
                        */
						
						pdWarehouseStockManager.updatePdWarehouseStock(pdSendInfo);
						dao.savePdSendInfo(pdSendInfo);
					}else{
						pdSendInfo.setLogisticsDo(pdSendInfoStatus.getLogisticsDo());
						pdSendInfo.setInteferaceShNo(inteferaceShNo);
						//modify by fu 2016-1-20 添加已备货----begin
						pdSendInfo.setWhetherStock("Y");
						//modify by fu 2016-1-20 添加已备货----end
						dao.savePdSendInfo(pdSendInfo);
					}
			         rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);
	            }else{
	            	rspEntity.setSub_msg(checkResult);
	            }
			}catch (AppException e) {// 捕获代理商库存不足
				rspEntity.setSub_msg("EC商品库存不足");
				e.printStackTrace();
				log.info("换货单和发货退回单直接关联的发货单状态接口一在PdSendInfoManagerImpl类的reSetPdSendInfoStatus方法中运行异常：EC商品库存不足");
				throw new AppException("pd.notEnoughStock");
			}
			catch(Exception e){
				rspEntity.setSub_msg("接口方法异常");
				e.printStackTrace();
				log.info("换货单和发货退回单直接关联的发货单状态接口一在PdSendInfoManagerImpl类的reSetPdSendInfoStatus方法中运行异常："+e.toString());
			}
			return this.getRspEntityString(rspEntity);
		}



		/**
		 * 换货单和发货退回单直接关联的发货单状态接口一接口传过来的数据格式校验
		 * @author fx  2015-08-14 
		 * @param jsonString
		 * @return rspEntity
		 * @throws Exception 
		 */
		private String getCheckPdSendInfoStatusjs(String jsonString) throws Exception {
			log.info("换货单和发货退回单直接关联的发货单状态接口一在PdSendInfoManagerImpl类的getCheckPdSendInfoStatusjs方法中开始运行");
			if(StringUtil.isEmpty(jsonString)){
				return " jsonString is null ";
			}else{
				 Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
				 //将json字符串转换成java对象
		         PdSendInfoStatus pdSendInfoStatus =InterfaceJsonUtil.returnnoJsonToModel(jsonString,PdSendInfoStatus.class,classMap);
				 String siNo = pdSendInfoStatus.getSiNo();
				 if(StringUtil.isEmpty(siNo)){
					 return " siNo(发货单号) is  null ";
				 }else{
					 //校验发货单号的合理性
					 PdSendInfo pdSendInfo = dao.getPdSendInfoForSiNo(siNo);
					 if(null==pdSendInfo){
						 return " siNo(发货单号) 在EC系统中不存在 ";
					 }else{
						 String orderNo = pdSendInfo.getOrderNo();
						 if(!StringUtil.isEmpty(orderNo)){
							 String mo = orderNo.substring(0, 2);
							 if("M0".equals(mo)){
								 return " siNo(发货单号)不是发货退回单或换货单生成的发货单的单号 ";
							 }
						 }
					 }
				 }
				 
				 String status = pdSendInfoStatus.getStatus();
				 if(StringUtil.isEmpty(status)){
					 return " status(发货单状态) is null ";
				 }else{
					 if((!"Y".equals(status)&&(!"D".equals(status)))){
						 return " statur(发货单状态) must be Y or D";
					 }
				 }
			}
			log.info("换货单和发货退回单直接关联的发货单状态接口一在PdSendInfoManagerImpl类的getCheckPdSendInfoStatusjs方法中运行结束");
			return Constants.INTERFACE_NORMAL;
		}
		
		/**
	     * 处理OMS等接口传递过来的数据返回值的处理
	     * @author fx 2015-8-17
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
	    	return rspEntity;
	    }
	    
	    /**
		 * 发货单暂不发货/暂停发货接口---这个方法因业务需要不用了
		 * @author fx 2015-08-14
		 * @param jsonString
		 * @param shipType:发货方式:0正常发货;2暂不发货;3暂停发货
		 * @return string
		 */
		public String reSetPdSendInfoStopOrNo(String siNo,String shipType) throws Exception{
				log.info("发货单暂不发货/暂停发货接口接口一在PdSendInfoManagerImpl类的reSetPdSendInfoStopOrNo方法中开始运行");
		    	//if((!StringUtil.isEmpty(shipType))&&("2".equals(shipType)||"3".equals(shipType))){
				    if(StringUtil.isEmpty(shipType)){
				    	shipType = "0";
				    }
		    		PdSendInfoStatus  pdSendInfoStatus = new PdSendInfoStatus();
		    		pdSendInfoStatus.setSiNo(siNo);
		    		pdSendInfoStatus.setStatus(shipType);
		    		JSONObject jo = JSONObject.fromObject(pdSendInfoStatus);
		    		String joString = jo.toString();
		    		
		    		log.info("发货单暂不发货/暂停发货接口接口一在PdSendInfoManagerImpl类的reSetPdSendInfoStopOrNo方法中运行：JOCS向OMS推送发货单暂不发货或暂停发货接口数据begin");

		    		//调用发送接口--向OMS发送
		    		MsgSendService msgSendService = new MsgSendService();
		    		//方法名
		    		String methodName = "123";//方法名等待OMS告诉,modify fu 20150825 为了自测，特意随便写个方法
		    		msgSendService.setSender(Constants.OMS_SEND);//OMS平台
		    	
		    		String aa = "123";

		    		//modify fu 20150825 为了自测，特意将方法注释掉；
		    		//String aa = msgSendService.post(joString, methodName);
		    		
		    		log.info("发货单暂不发货/暂停发货接口接口一在PdSendInfoManagerImpl类的reSetPdSendInfoStopOrNo方法中运行：JOCS向OMS推送发货单暂不发货或暂停发货接口数据end");

		    		//调用发送接口--向OMS发送----modify gw 2014-12-23----结束
		    		//=============================接口日志写入开始 Modify By WUCF 20150123
		    		JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
		    		jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
		    		jocsInterfaceLog.setSender(Constants.OMS_SEND);//数据的接收方
		    		jocsInterfaceLog.setMethod(methodName);//方法名称
		    		jocsInterfaceLog.setContent(joString.toString());//发送内容content
		    		jocsInterfaceLog.setReturnDesc(aa);//返回内容
		    		
		    		ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
		    		//=============================接口日志写入完毕	
		    	//}
	    	   return null;
	    }
		
		/**
		 * 确认正常发货
		 * @author fu 2015-10-22
		 * param:siNo 发货单号
		 * return: 
		 */
		public String updateShipType(String siNo){
			if(!StringUtil.isEmpty(siNo)){
			  try{	
			    boolean haveDo = pdLogisticsBaseManager.getDoResult(siNo);
			    if(haveDo){
			    	//return false;//有DO单存在的话，就不让加急发货
			    	return "haveDo";
			    }
			    
			    //在发货单编辑保存之前去WMS询问发货单能否编辑----begin modify by fu 2016-03-21
				String methodNameWMS = "checkstatus";
				String typeWMS = "11";
				String canDelete = pdWarehouseStockManager.getPdLogisticsCanModify(siNo, typeWMS, methodNameWMS);
				if((!StringUtil.isEmpty(canDelete))&&(!"allow".equals(canDelete))){
					//return "notUpdateShipType";
					return canDelete;
				}
				//在发货单编辑保存之前去WMS询问发货单能否编辑----end modify by fu 2016-03-21
			    
				boolean res = dao.updateShipType(siNo);
				if(res){
					PdSendInfo pdSendInfo = dao.getPdSendInfo(siNo);
					//未发货之前的的发货单的发货方式变更向后续系统推送接口消息
					if((null!=pdSendInfo)&&(2>pdSendInfo.getOrderFlag())){
						pdSendInfo.setCanDo("");
						this.getPdSendInfoEditInterfaceResult(pdSendInfo);
					}
					//return true;
					return "succ";
				}else{
					//return false;
					return "fail";
				}
			  }catch(Exception e){
				e.printStackTrace();
				log.info("发货单编辑页面的确认正常发货的按钮操作失败,出现异常"+e.toString());
				//return false;
				return "fail";
			  }
			}else{
				//return false;
				return "fail";
			}
		}
		
		/**
	     * 加急发货
	     * @author fu 20151119 
	     * @param pdSendInfo
		 * return: 返回修改状态  true:成功  false:失败
	     */
		public String updateHurryFlag(String siNo) {
			if(!StringUtil.isEmpty(siNo)){
				 try{	
					    
					    boolean haveDo = pdLogisticsBaseManager.getDoResult(siNo);
					    if(haveDo){
					    	//return false;//有DO单存在的话，就不让加急发货
					    	return "haveDo";
					    }
					    
					    //在发货单编辑保存之前去WMS询问发货单能否编辑----begin modify by fu 2016-03-21
						String methodNameWMS = "checkstatus";
						String typeWMS = "11";
						String canDelete = pdWarehouseStockManager.getPdLogisticsCanModify(siNo, typeWMS, methodNameWMS);
						if((!StringUtil.isEmpty(canDelete))&&(!"allow".equals(canDelete))){
							//return "notUpdateHurryFlag";
							return canDelete;
						}
						//在发货单编辑保存之前去WMS询问发货单能否编辑----end modify by fu 2016-03-21
					    
						boolean res = dao.updateHurryFlag(siNo);
						if(res){
							PdSendInfo pdSendInfo = dao.getPdSendInfo(siNo);
							//未发货之前的的发货单的发货方式变更向后续系统推送接口消息
							if((null!=pdSendInfo)&&(2>pdSendInfo.getOrderFlag())){
								pdSendInfo.setCanDo("");
								this.getPdSendInfoEditInterfaceResult(pdSendInfo);
							}
							return "succ";
						}else{
							return "fail";
						}
					  }catch(Exception e){
						e.printStackTrace();
						log.info("发货单编辑页面的加急发货的按钮操作失败,出现异常"+e.toString());
						//return false;
						return "fail";
					  }
			}else{
				//return false;
				return "fail";
			}
		}
		
		/**
		 * 发货退回订单入库是否只生成一张发货单
		 * @author modify fu 2015-12-26 
		 * param:siNo 发货单号
		 * return: 返回修改状态  true:发货退回单之前有且仅有一张发货单  false:发货退回单已经生成多张发货单
		 *//*
		public boolean getPdSendInfoForOrderNo(String rpNo){
			 return dao.getPdSendInfoForOrderNo(rpNo);
		}*/
	    
		
		/**
		 * 根据发货单查询发货单号
		 * @author gw 2016-02-16
		 * @param siNo
		 * @return pdSendInfo
		 */
		public PdSendInfo getPdSendInfoForSiNo(String siNo){
			 return dao.getPdSendInfoForSiNo(siNo);
		}
		
		/**
		 * @author fu 2016-06-24 将WMS那边已撤单的发货单设为发货作废
		 * @param siNo
		 * @return 
		 */
		public void reSetPdSendInfoShipTypeFour(String siNo){
			dao.reSetPdSendInfoShipTypeFour(siNo);
		}



		/**
		 * @author WuCF 20160905 订单发送重发日志接口数据
		 * @return 
		 */
		@Override
		public void reJocsInterfaceTransmission() {
			List<Map<String,Object>> logIdList = dao.geetJocsInterfaceRetranLogids();
			System.out.println("==========调用重发方法！"+logIdList.size());
			try{
				//查询需要发送的消息接口数据
				for(Map<String,Object> logIdMap : logIdList){
					System.out.println(logIdMap.get("LOG_ID"));
					//将数据加入到Map对象集合
					JocsInterfaceRetransmission jifrm = jocsInterfaceRetransmissionManager.getJocsInterfaceRetransmission(String.valueOf(logIdMap.get("LOG_ID")));
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("logId", jifrm.getLogId());
					map.put("logType", jifrm.getLogType());
					map.put("flag", jifrm.getFlag());
					map.put("method", jifrm.getMethod());
					map.put("type", jifrm.getType());
					map.put("charset", jifrm.getCharset());
					map.put("ver", jifrm.getVer());
					map.put("content", jifrm.getContent());
					
					//保存完成后修改状态
					jifrm.setSender(jifrm.getSender());
					jifrm.setRetranStatus("1");
					jifrm.setRetranType("1");//手动重发
					jocsInterfaceRetransmissionManager.saveJocsInterfaceRetransmission(jifrm);
					
					//调用发送接口---------------------------开始
					MsgSendService msgSendService = new MsgSendService();
					msgSendService.setSender(jifrm.getSender());
//					//方法名
					String aa = msgSendService.post(jifrm.getContent(), jifrm.getMethod());
					//调用发送接口---------------------------结束
					
					//=============================接口日志写入开始
					JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
					jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
					jocsInterfaceLog.setSender(jifrm.getSender());//数据的接收方
					jocsInterfaceLog.setMethod(jifrm.getMethod());//方法名称
					jocsInterfaceLog.setContent(jifrm.getContent());//发送内容content
					jocsInterfaceLog.setReturnDesc(aa);//返回内容
					
					ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
					//=============================接口日志写入完毕
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
}
