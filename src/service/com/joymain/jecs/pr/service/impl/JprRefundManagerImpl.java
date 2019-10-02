package com.joymain.jecs.pr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Set;
import java.math.BigDecimal;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import service.MsgSendService;


import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.json.InterfaceJsonUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.service.JbdSummaryListManager;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.pd.model.PdJprRefundDelete;
import com.joymain.jecs.pd.model.PdJprRefundStatus;
import com.joymain.jecs.pd.model.PdLogisticsBase;
import com.joymain.jecs.pd.model.Returno;
import com.joymain.jecs.pd.model.Returno_items;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.po.dao.JpoMemberOrderListDao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.pr.dao.JprRefundDao;
import com.joymain.jecs.pr.dao.JprRefundListDao;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.util.ListUtil;

public class JprRefundManagerImpl extends BaseManager implements JprRefundManager {
	
	protected final Log log = LogFactory.getLog(getClass());
	private JprRefundDao dao;
	private JprRefundListDao jprRefundListDao;
	private PdWarehouseStockManager pdWarehouseStockManager;
	private JfiBankbookJournalManager jfiBankbookJournalManager;
	private JbdSummaryListManager jbdSummaryListManager;
	private FiBankbookJournalManager fiBankbookJournalManager = null;
	private JpoMemberOrderListDao jpoMemberOrderListDao;
	private JpoMemberOrderDao jpoMemberOrderDao;


	public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}
	
	

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setJprRefundDao(JprRefundDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.pr.service.JprRefundManager#getJprRefunds(com.joymain.jecs.pr.model.JprRefund)
	 */
	public List getJprRefunds(final JprRefund jprRefund) {
		return dao.getJprRefunds(jprRefund);
	}

	/**
	 * @see com.joymain.jecs.pr.service.JprRefundManager#getJprRefund(String
	 *      roNo)
	 */
	public JprRefund getJprRefund(final String roNo) {
		return dao.getJprRefund(new String(roNo));
	}

	/**
	 * @see com.joymain.jecs.pr.service.JprRefundManager#saveJprRefund(JprRefund
	 *      jprRefund)
	 */
	public void saveJprRefund(JprRefund jprRefund) {
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrder(jprRefund.getJpoMemberOrder().getMoId());
		Set jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
		Iterator poMemberOrderList = jpoMemberOrderList.iterator();
		while(poMemberOrderList.hasNext()){
			JpoMemberOrderList jpoMemberOrderlistTwo = (JpoMemberOrderList) poMemberOrderList.next();
			Long molId = jpoMemberOrderlistTwo.getMolId();
			Integer qty = jpoMemberOrderListDao.getJpoMemberOrderListForReturn( jpoMemberOrderlistTwo.getMolId());
			jpoMemberOrderlistTwo.setQty(qty);
		}
		
		jprRefund.setJpoMemberOrder(jpoMemberOrder);
    	
    	
		
		
		
		
		dao.saveJprRefund(jprRefund);
	}

	/**
	 * 生成明细
	 * 
	 * @param jprRefund
	 * @param JprRefundSet
	 */
	public void saveJprRefund(JprRefund jprRefund, Set jprRefundSet) {
     try{
			Set jprRefundIts = jprRefund.getJprReFundList();
			Iterator its = jprRefundIts.iterator();
			while (its.hasNext()) {
				JprRefundList jprRefundList = (JprRefundList) its.next();
				jprRefundListDao.removeJprRefundList(jprRefundList.getPrlId());
			}
			jprRefund.getJprReFundList().clear();
			jprRefund.setJprReFundList(jprRefundSet);
			
			//modify gw 2015-06-25 修改退单保存时更改订单数量的bug
			//将原数据库中的商品数量重新赋值给订单
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrder(jprRefund.getJpoMemberOrder().getMoId());
			Set jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
			Iterator poMemberOrderList = jpoMemberOrderList.iterator();
			while(poMemberOrderList.hasNext()){
				JpoMemberOrderList jpoMemberOrderlistTwo = (JpoMemberOrderList) poMemberOrderList.next();
				Long molId = jpoMemberOrderlistTwo.getMolId();
				Integer qty = jpoMemberOrderListDao.getJpoMemberOrderListForReturn( jpoMemberOrderlistTwo.getMolId());
				jpoMemberOrderlistTwo.setQty(qty);
			}
			
			jprRefund.setJpoMemberOrder(jpoMemberOrder);
			
			
			//logisticsFeeSix--退货单消息是否推送出去标识：isNew表明退货单没有推送出去;noNew表明退货单已经推送出去
			String logisticsFeeSix = jprRefund.getLogisticsFeeSix();
			String interFaceReturn = null;
			if("isNew".equals(logisticsFeeSix)){
				//判断订单是否是挂起状态，如果订单是挂起状态，那么就不让制退货单---modify by fu 2016-03-16---begin
				String logisticsFeeSeven = jprRefund.getLogisticsFeeSeven();
				if((!StringUtil.isEmpty(logisticsFeeSeven))&&"Y".equals(logisticsFeeSeven)){
					boolean isOrNotReturnStatus = jpoMemberOrderDao.getOrderReturnableStatus(jpoMemberOrder.getMemberOrderNo());
					if(isOrNotReturnStatus){
						return;//判断订单是否是挂起状态，如果订单是挂起状态，那么就不让制退货单
					}
				}
				
				//判断订单是否是挂起状态，如果订单是挂起状态，那么就不让制退货单---modify by fu 2016-03-16-----end

				//退货单推送接口消息优化-----特将原方法注释掉-----modify by fu 2016-06-24---begin
			    //interFaceReturn = this.pushJprRefund(jprRefund,jprRefundSet);
				//log.info("在JprRefundManagerImpl类的saveJprRefund方法中执行:退货单消息推送成功");
				//退货单推送接口消息优化-----特将原方法注释掉-----modify by fu 2016-06-24---end
				
				jprRefund.setLogisticsFeeSix("noNew");
				jprRefund.setLogisticsFeeSeven("");
				
				//modify by fu 退货单接口优化---------------------------------------begin
				//获取退货单的推送消息准备
				Returno returno = this.getReturno(jprRefund,jprRefundSet);
                //判断退货单是否已经做了入库操作，如果做了入库操作，则不允许编辑；否则允许编辑
				//modify fu 2015-09-10   在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
				String  intoStatus = dao.getIntoStatus(jprRefund.getRoNo());
				// 在退货单接口消息推送下去之前，退货单接送到入库的消息，因此不允许编辑及推送接口消息
				if((!StringUtil.isEmpty(intoStatus)) && "2".equals(intoStatus)){
					interFaceReturn = Constants.INTERFACE_NULL;
				}
				// 在退货单接口消息推送下去之前，退货单没有接送到入库的消息，因此允许编辑和推送接口消息
				else{
					dao.saveJprRefund(jprRefund);//先保存退货单
					this.pushJprRefundByReturno(returno,jprRefund);//最后推送接口消息
					return;
				}
				//modify by fu 退货单接口优化---------------------------------------end
			}
			
			if(Constants.INTERFACE_NULL.equals(interFaceReturn)){
				//modify fu 2015-09-11 为解决在编辑的过程中，收到退货入库消息后，退货编辑仍然保持编辑内容的问题，做以下处理。---begin
				List list = dao.getJprRefundForRoNo(jprRefund.getRoNo());
				if(null!=list){
					Map map = (Map) list.get(0);
					jprRefund.setResendSpNo((String)map.get("RESEND_SP_NO"));
					jprRefund.setAmount((BigDecimal)map.get("AMOUNT"));
					jprRefund.setPvAmt((BigDecimal)map.get("PV_AMT"));
					jprRefund.setIntoStatus((String)map.get("INTO_STATUS"));
					jprRefund.setIntoRemark((String)map.get("INTO_REMARK"));
					jprRefund.setRefundStatus((String)map.get("REFUND_STATUS"));
					jprRefund.setRefundRemark((String)map.get("REFUND_REMARK"));
					jprRefund.setRemark((String)map.get("REMARK"));
					jprRefund.setLocked((String)map.get("LOCKED"));
					jprRefund.setOrderStatus((String)map.get("ORDER_STATUS"));
					jprRefund.setRepairFee((String)map.get("REPAIR_FEE"));
					jprRefund.setRenovationFee((String)map.get("RENOVATION_FEE"));
					jprRefund.setLogisticsFee((String)map.get("LOGISTICS_FEE"));
					jprRefund.setSettledBonus((String)map.get("SETTLED_BONUS"));
					jprRefund.setFillFreight((String)map.get("FILL_FREIGHT"));
					jprRefund.setLogisticsFeeSix((String)map.get("LOGISTICS_FEE_SIX"));
					jprRefund.setLogisticsFeeSeven((String)map.get("LOGISTICS_FEE_SEVEN"));
					jprRefund.setLogisticsFeeEight((String)map.get("LOGISTICS_FEE_EIGHT"));
					jprRefund.setStjFlag((Integer)map.get("STJ_FLAG"));
					jprRefund.setRefundTye((String)map.get("REFUND_TYPE"));

					Set jprReFundList = jprRefundListDao.getRefundListForRono(jprRefund,jprRefundSet);
					jprRefund.getJprReFundList().clear();
					jprRefund.setJprReFundList(jprReFundList);	
					
				}
				//在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
				//modify fu 2015-09-11 为解决在编辑的过程中，收到退货入库消息后，退货编辑仍然保持编辑内容的问题，做以下处理。---end
			}
			dao.saveJprRefund(jprRefund);
			
			
     }catch(Exception e){
    	 e.printStackTrace();
			log.info("在JprRefundManagerImpl类的saveJprRefund方法中执行异常:"+e.toString());
     }
	}

	/**
	 * @author fu 2016-06-24 退货单推送的接口消息准备
	 * @param jprRefund
	 * @param jprRefundSet
	 * @return returno
	 */
	private Returno getReturno(JprRefund jprRefund, Set jprRefundSet) {
		log.info("物流退货接口在getReturno方法中开始运行-退货单推送的接口消息开始准备");
		Returno returno = new Returno();
		returno.setOrder_bn(jprRefund.getJpoMemberOrder().getMemberOrderNo().toString());
		returno.setEo_bo(jprRefund.getRoNo());
		returno.setReturnWarehouse(jprRefund.getResendSpNo());// modify fu 2015-09-10 添加退货入库仓库的字段
		//退货单的结算费用
		returno.setRepair_Fee(StringUtil.isEmpty(jprRefund.getRepairFee())?0:Double.parseDouble(jprRefund.getRepairFee()));//维修费
		returno.setRenovation_Fee(StringUtil.isEmpty(jprRefund.getRenovationFee())?0:Double.parseDouble(jprRefund.getRenovationFee()));//翻新费
		returno.setLogistics_Fee(StringUtil.isEmpty(jprRefund.getLogisticsFee())?0:Double.parseDouble(jprRefund.getLogisticsFee()));//物流费
		returno.setSettled_Bonus(StringUtil.isEmpty(jprRefund.getSettledBonus())?0:Double.parseDouble(jprRefund.getSettledBonus()));//不能扣回的奖金
		returno.setFill_Freight(StringUtil.isEmpty(jprRefund.getFillFreight())?0:Double.parseDouble(jprRefund.getFillFreight()));//回补运费
		
		Set returno_itemss = jprRefundSet;
		Iterator iterator = returno_itemss.iterator();
		List<Returno_items> returno_items = new ArrayList();
		while (iterator.hasNext()) {
		     JprRefundList jprRefundList = (JprRefundList)iterator.next();
		     Returno_items returnoItems = new Returno_items();
		     returnoItems.setErp_goods_bn(jprRefundList.getErpProductNo());
		     returnoItems.setGoods_bn(jprRefundList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
		     returnoItems.setName(jprRefundList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductName());
		     returnoItems.setNums(jprRefundList.getQty());
		     returnoItems.setPrice(null==jprRefundList.getPrice()?0:jprRefundList.getPrice().doubleValue());
		     returno_items.add(returnoItems);
		}
		returno.setReturno_items(returno_items);
		log.info("物流退货接口在getReturno方法中开始运行-退货单推送的接口消息准备结束");
		return returno;
	}

	/**
	 * @author fu 2016-06-24 退货单接口消息推送
	 * @param returno
	 * @param jprRefund
	 * @return 
	 */
	private void pushJprRefundByReturno(Returno returno,JprRefund jprRefund) {
		log.info("物流退货接口在pushJprRefundByReturno方法中运行");
		//将java对象转换成json对象
		JSONObject jsonObject = JSONObject.fromObject(returno);
		//将json对象转换成json字符串
		String returnnoJsonString = jsonObject.toString();
		

		
		//目前缺少接口的公共类和公共方法，先注释掉，等有接口的公共类和公共方法的时候再放开---modify gw 2015-07-30 begin

		//这样退货单接口的数据就准备好了 returno
		//调用发送接口--向OMS发送----modify gw 2014-12-23----开始
		MsgSendService msgSendService = new MsgSendService();
		//方法名
		String methodName = "ship.returnAdd";
		msgSendService.setSender(Constants.OMS_SEND);//OMS平台
		
		log.info("物流退货接口在pushJprRefundByReturno方法中运行：EC向OMS推送退单数据开始");

		//modify by fu 2017-1-18 退货单推送到OMS系统的接口方法关闭掉   
		/*String aa = "";
	
		aa = msgSendService.post(returnnoJsonString, methodName);
		
		log.info("物流退货接口在pushJprRefundByReturno方法中运行：EC向OMS推送退单数据结束,接口返回:"+aa);

		//调用发送接口--向OMS发送----modify gw 2014-12-23----结束
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
		//目前缺少接口的公共类和公共方法，先注释掉，等有接口的公共类和公共方法的时候再放开---modify gw 2015-07-30 end
		
		
		//modify by fu 2016-05-16 给中脉EC系统第三\四期接口添加开关-退货单保存---这个等第四期功能上线后在配置参数值为Y
		//获取接口开关标示
		String swtichSend = ListUtil.getListValue("CN", "interface.sendswitch", "ship.returnAdd");
        if((!StringUtil.isEmpty(swtichSend))&&"Y".equals(swtichSend)){
        	log.info("物流退货接口在pushJprRefundByReturno方法中运行：ship.returnAdd开关打开");
			//向渠道推送的接口在新合规项目的第四期才上，目前将改接口注释掉，在第四期上线前在打开---modify gw 2015-07-30 begin
			//只有全额支付的订单的退单数据才向渠道推送数据
			JpoMemberOrder jpoMemberOrder = jprRefund.getJpoMemberOrder();
			Integer isFullPay = jpoMemberOrder.getIsFullPay();
			if((null!=isFullPay) && ("1".equals(isFullPay.toString()))){
				log.info("物流退货接口在pushJprRefundByReturno方法中运行：JOCS向渠道推送退单数据开始");
				//调用发送接口--JOCS向渠道推送
				MsgSendService msgSendServiceQD = new MsgSendService();
				msgSendServiceQD.setSender(Constants.QU_SEND);//渠道平台
				
				//modify by fu 2017-1-18 退货单推送到渠道系统的方法注释掉；
				/*String bb = "";
				try{
					bb = msgSendServiceQD.post(returnnoJsonString, methodName);
					log.info("退货单推送渠道结束，接口返回:"+bb);
				}catch(Exception e){
					log.info("物流退货接口在pushJprRefundByReturno方法中运行：JOCS向渠道推送退单数据异常"+e.toString());
					e.printStackTrace();
				}
				log.info("物流退货接口在pushJprRefundByReturno方法中运行：JOCS向渠道推送退单数据结束");
		
				//=============================接口日志写入开始 Modify By WUCF 20150123
				jocsInterfaceLog = new JocsInterfaceLog();
				jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
				jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
				jocsInterfaceLog.setMethod(methodName);//方法名称
				jocsInterfaceLog.setContent(returnnoJsonString.toString());//发送内容content
				jocsInterfaceLog.setReturnDesc(bb);//返回内容
				
				ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
				//=============================接口日志写入完毕	
				log.info("物流退货接口在pushJprRefundByReturno方法中运行结束");*/
			}
			//向渠道推送的接口在新合规项目的第四期才上，目前将改接口注释掉，在第四期上线前在打开---modify gw 2015-07-30 end
        }
	}

	/**
	 * 入库
	 * 
	 * @param jprRefund
	 */
	public void saveJprRefundInto(JprRefund jprRefund) {
		pdWarehouseStockManager.updatePdWarehouseStock(jprRefund);
		this.saveJprRefund(jprRefund);
	}
	
	 //获取要退的现金数(电子存折数)
	private BigDecimal getRefundMoney(JprRefund jprRefund) {
		log.info("----------------getRefundMoney方法:退货单号为"+jprRefund.getRoNo());
		//退款
    	if("2".equals(jprRefund.getRefundStatus())){
	    	//modify gw 2015-07-10 修改解决发展基金多退，电子存折(现金)少退但是退的总额没问题的bug  gw-----begin
	    	//获取订单之前退单的发展基金总额
	    	BigDecimal beforeJjAmount = this.getBeforeJjAmount(jprRefund);
	    	log.info("----------------getRefundMoney方法:beforeJjAmount为"+beforeJjAmount);
	    	if(jprRefund.getJpoMemberOrder().getJjAmount()!=null){
	    		log.info("----------------getRefundMoney方法:jprRefund.getJpoMemberOrder().getJjAmount()为"+jprRefund.getJpoMemberOrder().getJjAmount());
	    		    //(原订单的发展基金总额 ) 大于( 订单之前几次退单的发展基金总额) ,那么还需要继续退发展基金
		    		if(((jprRefund.getJpoMemberOrder().getJjAmount()).compareTo(beforeJjAmount))==1){
			    			//该订单剩余可退发展基金数
			    			BigDecimal returnableJj = jprRefund.getJpoMemberOrder().getJjAmount().subtract(beforeJjAmount);
			    			log.info("----------------getRefundMoney方法:returnableJj为"+returnableJj);
			    			//现在这张退单的总金额   大于  ( 订单剩余可退发展基金数)
			    			if(jprRefund.getAmount().compareTo(returnableJj)==1){
			    				log.info("----------------jprRefund.getAmount().subtract(returnableJj)方法:0"+jprRefund.getAmount().subtract(returnableJj));
			    				return jprRefund.getAmount().subtract(returnableJj);//退款-可退基金数
			    			}
			    			//现在这张退单的总金额   小于等于( 订单剩余可退发展基金数),那么不退电子存折(现金),全退发展基金
			    			else{
			    				log.info("----------------getRefundMoney方法:0"+0);
			    				return new BigDecimal("0");
			    			}
					}
					//(原订单的发展基金总额 ) 小于等于 ( 订单之前几次退单的发展基金总额) ,那么不需要继续退发展基金,全退电子存折(现金)
					else{
						log.info("----------------getRefundMoney方法:jprRefund.getAmount()为"+jprRefund.getAmount());
						return jprRefund.getAmount();
					}
	    		//modify gw 2015-07-10 修改解决发展基金多退，电子存折(现金)少退但是退的总额没问题的  gw-----end
	    	}
    	}
    	//不退款或未退款----------由已退款改成未退款或不退款
    	else{
    		    //查看这张退单之前退了多少基金
    		    BigDecimal moneyBackJi = fiBankbookJournalManager.getJprRefundJjBack(jprRefund.getRoNo());
    		    if(null!=moneyBackJi){
    		    	if(jprRefund.getAmount().compareTo(moneyBackJi)==1){
    		    		return jprRefund.getAmount().subtract(moneyBackJi);//返回这张退单之前退的现金数（电子存折）
    		    	}else{
    		    		return new BigDecimal("0");//返回这张退单之前退的现金数（电子存折）
    		    	}
    		    }else{
    		    	return jprRefund.getAmount();//之前这张单退的全是现金（电子存折），那么返回现金（电子存折）
    		    }
	    		/*//修改bug,将之前的代码注释掉
	    		if(jprRefund.getAmount().compareTo(jprRefund.getJpoMemberOrder().getJjAmount())==1){//退款 >基金总数=现金
	    			return jprRefund.getAmount().subtract(jprRefund.getJpoMemberOrder().getJjAmount());//退款-基金总数
	    		}else{
	    			return new BigDecimal("0");//退款<基金总数
	    		}*/
	    }
    	return jprRefund.getAmount();
	}

	 /**
     * 获取订单之前退单的基金总额
     * @author gw 2015-07-10
     * @param jprRefund
     * @return bigDecimal
     */
    private BigDecimal getBeforeJjAmount(JprRefund jprRefund){
    	//modify gw 2015-07-10 修改解决基金多退，存折少退但是退的总额没问题的bug  gw-----begin
    	BigDecimal moneyJiAmount = new BigDecimal(0);
    	if(jprRefund.getJpoMemberOrder().getJjAmount()!=null){
    		//获取这张订单所关联的退货单
    		List<JprRefund> jprRefundList = dao.getJprRefundForMemberOrder(jprRefund.getJpoMemberOrder().getMemberOrderNo());
    		if(null!=jprRefundList){
    			    for(int i=0;i<jprRefundList.size();i++){
    			    	JprRefund jprRefundOb = jprRefundList.get(i);
    			    	//首先查询这张订单之前每一张退单的退基金款情况
    			    	BigDecimal jprRefundObJj = fiBankbookJournalManager.getBeforeJprRefundJj(jprRefundOb.getRoNo());
    			    	moneyJiAmount = moneyJiAmount.add(jprRefundObJj);
    			    }
    		}
    	}
    	return moneyJiAmount;
    	//modify gw 2015-07-10 修改解决基金多退，存折少退但是退的总额没问题的bug  gw-----end
    }

	/**
     * 退款----退单相关的订单使用的现金部分都退到电子存折,使用的基金都存到发展基金
	 * @param jprRefund
	 */
	public void saveJprRefundRefund(JprRefund jprRefund, SysUser operaterSysUser) {
		log.info("退货退款---------------------------------开始运行");
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.getJpoMemberOrder(jprRefund.getJpoMemberOrder().getMoId());
		Set jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
		Iterator poMemberOrderList = jpoMemberOrderList.iterator();
		while(poMemberOrderList.hasNext()){
			JpoMemberOrderList jpoMemberOrderlistTwo = (JpoMemberOrderList) poMemberOrderList.next();
			Long molId = jpoMemberOrderlistTwo.getMolId();
			Integer qty = jpoMemberOrderListDao.getJpoMemberOrderListForReturn( jpoMemberOrderlistTwo.getMolId());
			jpoMemberOrderlistTwo.setQty(qty);
		}
		
		jprRefund.setJpoMemberOrder(jpoMemberOrder);
		this.dao.saveJprRefund(jprRefund);
		log.info("退货退款--------------------------------保存成功");

    	//modify gw 2015-07-08  查看这张订单之前的退单退款情况情况,解决1717的bug
		
		BigDecimal moneyTmp = this.getRefundMoney(jprRefund);//退款 -基金总数=现金    modify gw 2015-07-10 //退款-可退基金数=现金
		BigDecimal moneyTmpJJ = jprRefund.getAmount().subtract(moneyTmp);
		//BigDecimal moneyTmpJJ = jprRefund.getAmount().subtract(this.getRefundMoney(jprRefund));//基金总数=退款-(退款 -基金总数)   modify gw 2015-07-10 //可退基金数=退款-(退款-可退基金数)
		log.info("退货退款--------------------------------财务开始退款");
		if ("2".equals(jprRefund.getRefundStatus())) {
			if (!"1".equals(jprRefund.getJpoMemberOrder().getCompanyPay())) {
				//String note[] = { jprRefund.getRoNo() };
				//modify fu 2015-10-20 将存在查询或电子基金查询的摘要由退单编号改成 订单编号
				String note[] = { jprRefund.getJpoMemberOrder().getMemberOrderNo() };
				Integer[] moneyType = { 15 };
				if (moneyTmp.compareTo(new BigDecimal("0")) == 1) {
					log.info("退货退款--------------------------------财务开始退款saveJfiPayDataVerify");
					BigDecimal[] money = { moneyTmp };
					jfiBankbookJournalManager.saveJfiPayDataVerify(jprRefund.getCompanyCode(), jprRefund.getSysUser(), moneyType, money, operaterSysUser.getUserCode(),operaterSysUser.getUserName(), jprRefund.getRoNo(), note, "1");
				}
				log.info("退货退款--------------------------------财务开始退款saveFiPayDataVerify----before");
				log.info("-------------one"+moneyTmpJJ);
				log.info("-------------Two"+moneyTmpJJ.compareTo(new BigDecimal("0")));
				log.info(moneyTmpJJ.compareTo(new BigDecimal("0")) == 1);
				if (moneyTmpJJ.compareTo(new BigDecimal("0")) == 1) {
					log.info("退货退款--------------------------------财务开始退款saveFiPayDataVerify");
					BigDecimal[] moneyJJ = { moneyTmpJJ };
					fiBankbookJournalManager.saveFiPayDataVerify(jprRefund.getCompanyCode(), jprRefund.getSysUser(), moneyType, moneyJJ, operaterSysUser.getUserCode(),operaterSysUser.getUserName(), jprRefund.getRoNo(), note, "1", "1");
				}
				// 降级代码
			}
		} else {
			Integer[] moneyType = { 16 };
			//String[] note = { jprRefund.getRoNo() };
			//modify fu 2015-10-20 将存在查询或电子基金查询的摘要由退单编号改成 订单编号
			String note[] = { jprRefund.getJpoMemberOrder().getMemberOrderNo() };
			if (moneyTmp.compareTo(new BigDecimal("0")) == 1) {
				BigDecimal[] money = { moneyTmp };
				log.info("退货退款--------------------------------财务开始退款saveJfiBankbookDeduct");
				jfiBankbookJournalManager.saveJfiBankbookDeduct(jprRefund.getCompanyCode(), jprRefund.getSysUser(), moneyType, money, operaterSysUser.getUserCode(),operaterSysUser.getUserName(), jprRefund.getRoNo(), note, "1");
			}
			if (moneyTmpJJ.compareTo(new BigDecimal("0")) == 1) {
				BigDecimal[] moneyJJ = { moneyTmpJJ };
				log.info("退货退款--------------------------------财务开始退款saveFiBankbookDeduct");
				fiBankbookJournalManager.saveFiBankbookDeduct(jprRefund.getCompanyCode(), jprRefund.getSysUser(), moneyType, moneyJJ, operaterSysUser.getUserCode(),operaterSysUser.getUserName(), jprRefund.getRoNo(), note, "1", "1");
			}

		}

		log.info("退货退款--------------------------------插入每日计算表");
		// 插入每日计算表
		JbdSummaryList jbdSummaryList = new JbdSummaryList();
		jbdSummaryList.setUserCode(jprRefund.getSysUser().getUserCode());
		log.info("退货退款--------------------------------------11");
		jbdSummaryList.setCardType(jprRefund.getSysUser().getJmiMember().getCardType());
		log.info("退货退款--------------------------------------22");
		if ("2".equals(jprRefund.getRefundStatus())) {
			jbdSummaryList.setInType(6);
		} else {
			jbdSummaryList.setInType(10);
		}
		log.info("退货退款--------------------------------------33");

		jbdSummaryList.setCreateTime(new Date());
		jbdSummaryList.setOrderType(jprRefund.getJpoMemberOrder().getOrderType());
		log.info("退货退款--------------------------------------44");

		jbdSummaryList.setOldCheckDate(null);
		jbdSummaryList.setNewCheckDate(null);
		jbdSummaryList.setPvAmt(jprRefund.getPvAmt());
		log.info("退货退款--------------------------------------55");

		jbdSummaryList.setOldLinkNo(null);
		jbdSummaryList.setNewLinkNo(null);
		jbdSummaryList.setOldRecommendNo(null);
		jbdSummaryList.setNewRecommendNo(null);

		jbdSummaryList.setNewCompanyCode(jprRefund.getJpoMemberOrder().getCompanyCode());
		log.info("退货退款--------------------------------------66");

		jbdSummaryList.setUserCreateTime(null);
		jbdSummaryList.setOrderNo(jprRefund.getRoNo());
		log.info("退货退款--------------------------------------77");

		if (jprRefund.getWweek() == 0) {
			jbdSummaryList.setWweek(null);
		} else {
			jbdSummaryList.setWweek(jprRefund.getWweek());
		}
		log.info("退货退款--------------------------------整个流程走完");

		jbdSummaryListManager.saveJbdSummaryList(jbdSummaryList);
		log.info("退货退款--------------------------------------88");

	}

	/**
	 * 根据时间获取订单总价格与总PV
	 * 
	 * @param companyCode
	 * @param createBTime
	 * @param createETime
	 * @return
	 */
	public List getJprRefundCByTime(String companyCode, String createBTime, String createETime) {
		return dao.getJprRefundCByTime(companyCode, createBTime, createETime);
	}

	/**
	 * @see com.joymain.jecs.pr.service.JprRefundManager#removeJprRefund(String
	 *      roNo)
	 */
	public void removeJprRefund(final String roNo) {
		log.info("物流退货单删除接口之在JprRefundManagerImpl类的removeJprRefund方法中运行：JOCS向OMS推送退单删除数据开始");
		//modify gw 2015-08-14
		//构建退货单删除接口信息
		PdJprRefundDelete pdJprRefundDelete = new PdJprRefundDelete();
		pdJprRefundDelete.setRoNo(roNo);
		pdJprRefundDelete.setWhetherDelete("Y");
		JSONObject jo = JSONObject.fromObject(pdJprRefundDelete);//将java对象转换成json对象
		String joString = jo.toString();//将json对象转换成json字符串
		//调用发送接口--向OMS发送----modify gw 2014-12-23----开始
		MsgSendService msgSendService = new MsgSendService();
		//方法名
		String methodName = "ship.removeJprRefund";//OMS那边退单删除接口方法名
		msgSendService.setSender(Constants.OMS_SEND);//OMS平台
		
		//modify fu 2015-09-10   在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
		String  intoStatus = dao.getIntoStatus(roNo);
		if((!StringUtil.isEmpty(intoStatus)) && "2".equals(intoStatus)){
			// 在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
		}else{
			//modify fu 2015-09-10 
			
			// modify by fu 2017-1-18 退货单删除接口注释掉
			/*String aa = "";
			try{
			    aa = msgSendService.post(joString, methodName);
			}catch(Exception e){
				log.info("物流退货单删除接口之在JprRefundManagerImpl类的removeJprRefund方法中运行：JOCS向OMS推送退单删除数据异常"+e.toString());
				e.printStackTrace();
			}
			log.info("物流退货单删除接口之在JprRefundManagerImpl类的removeJprRefund方法中运行：JOCS向OMS推送退单数据结束");
	
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
*/			
//			dao.removeJprRefund(roNo);
			//由于历史数据问题  修改为sql删除  
			jprRefundListDao.removeJprRefundListBySql(roNo);
			dao.removeJprRefundBySql(roNo);
			
		}
	}

	// added for getJprRefundsByCrm
	public List getJprRefundsByCrm(CommonRecord crm, Pager pager) {
		return dao.getJprRefundsByCrm(crm, pager);
	}

	/**
	 * 根据条件统计商品销售量
	 * 
	 * @param crm
	 * @return
	 */
	public List statisticProductSale(CommonRecord crm) {
		return dao.statisticProductSale(crm);
	}

	/**
	 * 获取一段时间内各个公司(如指定companyCode,则获取对应的公司)的已退货订单
	 * 
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @param returnType
	 * @return
	 */
	public List getJprReRefundStaticsCheckedCompany(final String startDate, final String endDate, final String companyCode, final String productType, final String returnType) {
		return dao.getJprReRefundStaticsCheckedCompany(startDate, endDate, companyCode, productType, returnType);
	}

	public void setJprRefundListDao(JprRefundListDao jprRefundListDao) {
		this.jprRefundListDao = jprRefundListDao;
	}

	public void setPdWarehouseStockManager(PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public List getRefundGathering(CommonRecord crm) {
		return dao.getRefundGathering(crm);
	}

	public List getRefundSum(CommonRecord crm) {
		return dao.getRefundSum(crm);
	}

	public List getJprReRefundStaticsCheckedCompanyByTree(String startDate, String endDate, String companyCode, String returnType, String treeIndex, String relationType) {
		return dao.getJprReRefundStaticsCheckedCompanyByTree(startDate, endDate, companyCode, returnType, treeIndex, relationType);
	}

	public void setJbdSummaryListManager(JbdSummaryListManager jbdSummaryListManager) {
		this.jbdSummaryListManager = jbdSummaryListManager;
	}

	public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}

	@Override
	public JprRefund getRefundByMoId(Long moId,String roNo) {

		List<JprRefund> refundList = dao.getJprRefundByMoId(moId,roNo);
		Set<Long> molIdSet = new HashSet<Long>();
		Integer stjFlag = null;

		// 筛选已退单的molID
		for (JprRefund rf : refundList) {
			if (null != rf.getStjFlag()) { //45w，65w
				stjFlag = rf.getStjFlag();
			}
			Iterator<JprRefundList> reListIte = rf.getJprReFundList().iterator();
			while (reListIte.hasNext()) {
				JprRefundList reList = reListIte.next();
				molIdSet.add(reList.getMolId());
			}
		}

		Iterator<Long> iteMolid = molIdSet.iterator();
		// 已退单商品及数量
		List<JpoMemberOrderList> orderList_Arr = new ArrayList<JpoMemberOrderList>();
		while (iteMolid.hasNext()) {

			Long ite_molId = iteMolid.next();
			List<JprRefundList> refList = jprRefundListDao.getRefundListByMolId(ite_molId,roNo);

			// 同一个商品已退数量
			int countQty = 0;
			for (JprRefundList jrl : refList)
				countQty += jrl.getQty();

			JpoMemberOrderList orderList = jpoMemberOrderListDao.getJpoMemberOrderList(ite_molId);

			// 框架事务问题, 后面抽时间测试
			JpoMemberOrderList orderItem = new JpoMemberOrderList();
			orderItem.setMolId(orderList.getMolId());
			orderItem.setJpmProductSaleTeamType(orderList.getJpmProductSaleTeamType());
			orderItem.setJpoMemberOrder(orderList.getJpoMemberOrder());
			orderItem.setProductConfigAmount(orderList.getProductConfigAmount());
			orderItem.setProductName(orderList.getProductName());
			orderItem.setProductType(orderList.getProductType());
			orderItem.setPv(orderList.getPv());
			orderItem.setPrice(orderList.getPrice());
			orderItem.setVolume(orderList.getVolume());
			orderItem.setWeight(orderList.getWeight());
			orderItem.setQty(countQty);

			orderList_Arr.add(orderItem);
		}

		JprRefund refund = new JprRefund();
		JpoMemberOrder order = jpoMemberOrderDao.getJpoMemberOrder(moId);
		List<JpoMemberOrderList> delOrderList_arr = new ArrayList<JpoMemberOrderList>();

		Iterator<JpoMemberOrderList> orItem = order.getJpoMemberOrderList().iterator();

		while (orItem.hasNext()) {
			JpoMemberOrderList ol = orItem.next();
			log.info("orderList ==" + ol.getMolId() + " and qty==" + ol.getQty());
			for (JpoMemberOrderList orderItem : orderList_Arr) {
				if (ol.getMolId() == orderItem.getMolId()) {

					int olQty = ol.getQty();
					int orderItemQty = orderItem.getQty();

					if (olQty > orderItemQty) {
						ol.setQty(olQty - orderItemQty);
					} else {
						delOrderList_arr.add(ol);
					}
				}
			}
		}

		order.getJpoMemberOrderList().removeAll(delOrderList_arr);

		refund.setJpoMemberOrder(order);
		refund.setSysUser(order.getSysUser());
		refund.setOrderType(order.getOrderType());
		refund.setCompanyCode(order.getCompanyCode());
		refund.setReturnType("0");
		refund.setWyear(0);
		refund.setWmonth(0);
		refund.setWweek(0);
		refund.setLocked("N");
		refund.setStjFlag(stjFlag);

		return refund;
	}

	/**
	 * 调用sql函数 执行大订单结算降级黄砖会员，幸福会员
	 * 
	 * @param moId
	 *            退货单主键 (退货单编号)
	 */
	public String getFunctionRp(String moId, Integer njtcType) {
		//return dao.getFunctionRp(moId, njtcType);
		return "1";
	}

	public JpoMemberOrderListDao getJpoMemberOrderListDao() {
		return jpoMemberOrderListDao;
	}

	public void setJpoMemberOrderListDao(JpoMemberOrderListDao jpoMemberOrderListDao) {
		this.jpoMemberOrderListDao = jpoMemberOrderListDao;
	}

	public JpoMemberOrderDao getJpoMemberOrderDao() {
		return jpoMemberOrderDao;
	}

	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}

	/**
	 * 退货单的接口准备数据
	 * @author gw 2014-11-28
	 * @param jprRefund
	 * @param jprRefundSet
	 * @return string
	 */
	public String pushJprRefund(JprRefund jprRefund, Set jprRefundSet) throws Exception {
		log.info("物流退货接口在pushJprRefund方法中开始运行");
		Returno returno = new Returno();
		returno.setOrder_bn(jprRefund.getJpoMemberOrder().getMemberOrderNo().toString());
		returno.setEo_bo(jprRefund.getRoNo());
		returno.setReturnWarehouse(jprRefund.getResendSpNo());// modify fu 2015-09-10 添加退货入库仓库的字段
		//退货单的结算费用
		returno.setRepair_Fee(StringUtil.isEmpty(jprRefund.getRepairFee())?0:Double.parseDouble(jprRefund.getRepairFee()));//维修费
		returno.setRenovation_Fee(StringUtil.isEmpty(jprRefund.getRenovationFee())?0:Double.parseDouble(jprRefund.getRenovationFee()));//翻新费
		returno.setLogistics_Fee(StringUtil.isEmpty(jprRefund.getLogisticsFee())?0:Double.parseDouble(jprRefund.getLogisticsFee()));//物流费
		returno.setSettled_Bonus(StringUtil.isEmpty(jprRefund.getSettledBonus())?0:Double.parseDouble(jprRefund.getSettledBonus()));//不能扣回的奖金
		returno.setFill_Freight(StringUtil.isEmpty(jprRefund.getFillFreight())?0:Double.parseDouble(jprRefund.getFillFreight()));//回补运费
		
		Set returno_itemss = jprRefundSet;
		Iterator iterator = returno_itemss.iterator();
		List<Returno_items> returno_items = new ArrayList();
		while (iterator.hasNext()) {
		     JprRefundList jprRefundList = (JprRefundList)iterator.next();
		     Returno_items returnoItems = new Returno_items();
		     returnoItems.setErp_goods_bn(jprRefundList.getErpProductNo());
		     returnoItems.setGoods_bn(jprRefundList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo());
		     returnoItems.setName(jprRefundList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductName());
		     returnoItems.setNums(jprRefundList.getQty());
		     returnoItems.setPrice(null==jprRefundList.getPrice()?0:jprRefundList.getPrice().doubleValue());
		     returno_items.add(returnoItems);
		}
		returno.setReturno_items(returno_items);
		
		//将java对象转换成json对象
		JSONObject jsonObject = JSONObject.fromObject(returno);
		//将json对象转换成json字符串
		String returnnoJsonString = jsonObject.toString();
		
		log.info("物流退货接口在pushJprRefund方法中运行：JOCS向OMS推送退单数据开始");

		
		//目前缺少接口的公共类和公共方法，先注释掉，等有接口的公共类和公共方法的时候再放开---modify gw 2015-07-30 begin

		//这样退货单接口的数据就准备好了 returno
		//调用发送接口--向OMS发送----modify gw 2014-12-23----开始
		MsgSendService msgSendService = new MsgSendService();
		//方法名
		String methodName = "ship.returnAdd";
		msgSendService.setSender(Constants.OMS_SEND);//OMS平台
		
		//modify fu 2015-09-10   在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
		String  intoStatus = dao.getIntoStatus(jprRefund.getRoNo());
		if((!StringUtil.isEmpty(intoStatus)) && "2".equals(intoStatus)){
			return Constants.INTERFACE_NULL;
			// 在退货单接口消息推送下去之前，判断退货单有没有接收到接口退货入库操作，如果有，那么就不允许修改这张退货单
		}
		//modify fu 2015-09-10 
		
		//退货单推送到oms的接口数据准备方法注释；
		/*String aa = "";
	
		aa = msgSendService.post(returnnoJsonString, methodName);
		
		log.info("物流退货接口在pushJprRefund方法中运行：JOCS向OMS推送退单数据结束,接口返回:"+aa);

		//调用发送接口--向OMS发送----modify gw 2014-12-23----结束
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
		//目前缺少接口的公共类和公共方法，先注释掉，等有接口的公共类和公共方法的时候再放开---modify gw 2015-07-30 end
		
		
		//modify by fu 2016-05-16 给中脉EC系统第三\四期接口添加开关-退货单保存---这个等第四期功能上线后在配置参数值为Y
		//获取接口开关标示
		String swtichSend = ListUtil.getListValue("CN", "interface.sendswitch", "ship.returnAdd");
        if((!StringUtil.isEmpty(swtichSend))&&"Y".equals(swtichSend)){
        	log.info("物流退货接口在pushJprRefund方法中运行：ship.returnAdd开关打开");
			//向渠道推送的接口在新合规项目的第四期才上，目前将改接口注释掉，在第四期上线前在打开---modify gw 2015-07-30 begin
			//只有全额支付的订单的退单数据才向渠道推送数据
			JpoMemberOrder jpoMemberOrder = jprRefund.getJpoMemberOrder();
			Integer isFullPay = jpoMemberOrder.getIsFullPay();
			if((null!=isFullPay) && ("1".equals(isFullPay.toString()))){
				log.info("物流退货接口在pushJprRefund方法中运行：JOCS向渠道推送退单数据开始");
				//调用发送接口--JOCS向渠道推送
				MsgSendService msgSendServiceQD = new MsgSendService();
				msgSendServiceQD.setSender(Constants.QU_SEND);//渠道平台
				
				//modify by fu 2017-1-18 退货单接口推送到渠道系统的方法注释掉
				/*String bb = "";
				try{
					bb = msgSendServiceQD.post(returnnoJsonString, methodName);
					log.info("退货单推送渠道结束，接口返回:"+bb);
				}catch(Exception e){
					log.info("物流退货接口在pushJprRefund方法中运行：JOCS向渠道推送退单数据异常"+e.toString());
					e.printStackTrace();
				}
				log.info("物流退货接口在pushJprRefund方法中运行：JOCS向渠道推送退单数据结束");
		
				//=============================接口日志写入开始 Modify By WUCF 20150123
				jocsInterfaceLog = new JocsInterfaceLog();
				jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
				jocsInterfaceLog.setSender(Constants.QU_SEND);//数据的接收方
				jocsInterfaceLog.setMethod(methodName);//方法名称
				jocsInterfaceLog.setContent(returnnoJsonString.toString());//发送内容content
				jocsInterfaceLog.setReturnDesc(bb);//返回内容
				
				ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
				//=============================接口日志写入完毕	
				log.info("物流退货接口在pushJprRefund方法中运行结束");*/
			}
			//向渠道推送的接口在新合规项目的第四期才上，目前将改接口注释掉，在第四期上线前在打开---modify gw 2015-07-30 end
        }
		return Constants.INTERFACE_NORMAL;
	}
	
	/**
	 * 退货入库接口
	 * @author fx  2015-08-04
	 * @param jsonString
	 * @return rspEntity
	 */
	public RspEntity reSetJprRefundStatus(String jsonString){
		log.info("退货入库接口开始运行：在JprRefundManagerImpl类的reSetJprRefundStatus方法中运行");
		RspEntity rspEntity = new RspEntity();
		try{
				PdJprRefundStatus pdJprRefundStatus = null;
				if(StringUtil.isEmpty(jsonString)){
					rspEntity.setSub_msg(" json is not null ");
					log.info("退货入库接口：json字符串为空");
					return this.getRspEntityString(rspEntity);
				}else{
					Map<String,Class> classMap = new HashMap<String,Class>();
					pdJprRefundStatus = InterfaceJsonUtil.returnnoJsonToModel(jsonString,PdJprRefundStatus.class,classMap);
					if(null==pdJprRefundStatus){
						    rspEntity.setSub_msg(" json is not null ");
							log.info("退货入库接口：json转换成对象pdJprRefundStatus后，pdJprRefundStatus对象为空");
							return this.getRspEntityString(rspEntity);
					}else{
					         String checkResult = this.getCheckResult(pdJprRefundStatus);
					         if(null!=checkResult){
					        	 rspEntity.setSub_msg(checkResult);
					        	 log.info("退货入库接口数据格式校验不通过:"+checkResult);
								 return this.getRspEntityString(rspEntity);
					         }else{
	                    		 rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);//正常结果返回
					        	 if("Y".equals(pdJprRefundStatus.getIntoStatus())){
					        		 //修改入库单的状态
					        		 JprRefund jprRefund = dao.getJprRefund(pdJprRefundStatus.getRoNo());
					        		 String intoStatus = jprRefund.getIntoStatus();
					        		 if((!StringUtil.isEmpty(intoStatus))&&(!"2".equals(intoStatus))){
					        			 jprRefund.setIntoTime(new Date());
					 					 jprRefund.setIntoUNo("interFaceRoot");
					 					 jprRefund.setIntoRemark("interFaceRoot");
					        			 jprRefund.setIntoStatus("2");
					        			 //退货入库修改库存
					        			 pdWarehouseStockManager.updatePdWarehouseStock(jprRefund);
						        		 dao.saveJprRefund(jprRefund);
					        		 }
					        		 
					        	 }
					        	 
					        	 return this.getRspEntityString(rspEntity);
					         }
					}
				}
		}catch(Exception e){
			e.printStackTrace();
			log.info("退货入库接口：在JprRefundManagerImpl类的reSetJprRefundStatus方法中运行异常:"+e.toString());
			rspEntity.setSub_msg(" 退货入库接口异常 ");
			return this.getRspEntityString(rspEntity);
		}
	}
	

	/**
     * 退货入库接口数据格式校验
     * @author fx 2015-8-04
     * @return string
     */
	private String getCheckResult(PdJprRefundStatus pdJprRefundStatus) {
		String roNo = pdJprRefundStatus.getRoNo();
		if(StringUtil.isEmpty(roNo)){
			return "roNo(退货单号) is null ";
		}else{
			JprRefund jprRefund = dao.getJprRefund(roNo);
			if(null==jprRefund){
				return "退货单号"+roNo+"在EC系统中不存在";
			}
		}
		
		String intoStatus = pdJprRefundStatus.getIntoStatus(); 
		if(StringUtil.isEmpty(intoStatus)){
			return " intoStatus(入库状态) is null ";
		}else{
			if((!"Y".equals(intoStatus)) && (!"N".equals(intoStatus))){
				return " intoStatus(入库状态)必须是Y或N ";
			}
		}
		return null;
	}



	/**
     * 处理OMS等接口传递过来的数据返回值的处理
     * @author fx 2015-8-04
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
	 * 根据退货单号查询退货入库状态
	 * @author fu 2015-09-10 
	 * @param roNo
	 * @return string 
	 */
    public String getIntoStatus(String roNo){
    	return dao.getIntoStatus(roNo);
    }

    /**
	 * Modify By WuCF 20151211
     * 修改订单的IS_RETREAT_ORDER2标示
     * @param jprRefund
     * @param JprRefundSet
     */
    public String updateJpoMemberOrderFlag(JprRefund jprRefund){
    	return dao.updateJpoMemberOrderFlag(jprRefund);
    }

	public List<JprRefund> getJprRefundForMemberOrder(String memberOrderNo) {
		// TODO Auto-generated method stub
		return dao.getJprRefundForMemberOrder(memberOrderNo);
	}
}
