
package com.joymain.jecs.pd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.math.BigDecimal;

import service.MsgSendService;

import net.sf.json.JSONObject;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.pd.model.PdJprRefundStatus;
import com.joymain.jecs.pd.model.PdReturnCheck;
import com.joymain.jecs.pd.model.PdReturnDelete;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.model.PdReturnStatus;
import com.joymain.jecs.pd.model.ReturnProduct;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pd.dao.PdReturnPurchaseDao;
import com.joymain.jecs.pd.service.PdReturnPurchaseManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.pd.service.PdShipmentsDetailManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.json.InterfaceJsonUtil;
import com.joymain.jecs.util.string.StringUtil;

public class PdReturnPurchaseManagerImpl extends BaseManager implements PdReturnPurchaseManager {
	
	private PdReturnPurchaseDao dao;
	private PdWarehouseStockManager pdWarehouseStockManager;
	private PdShipmentsDetailManager pdShipmentsDetailManager;
	private PdSendInfoManager pdSendInfoManager;
	private SysUserManager sysUserManager;
	
    public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void setPdShipmentsDetailManager(
			PdShipmentsDetailManager pdShipmentsDetailManager) {
		this.pdShipmentsDetailManager = pdShipmentsDetailManager;
	}
	
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void confirmPdReturnPurchase(PdReturnPurchase pdReturnPurchase,
			String deport) {
		// TODO Auto-generated method stub
    	if(!"1".equals(pdReturnPurchase.getStockFlag())){
    		pdReturnPurchase.setStockFlag("1");
			/*if("1".endsWith(flag)){
				pdAgentStockManager.updatePdAgentStocks(pdReturnPurchase);
			}*/
			
			//boolean a = pdSendInfoManager.getPdSendInfoForOrderNo(pdReturnPurchase.getRpNo());//a为true代表发货退回单有且仅有一张发货单
			//if(a){
		    pdWarehouseStockManager.updatePdWarehouseStock(pdReturnPurchase);
	//			pdShipmentsDetailManager.addPdShipmentsDetailsByOrder(pdReturnPurchase);
	//			pdAgentStockManager.updatePdAgentStocks(pdReturnPurchase);
			//}
			dao.savePdReturnPurchase(pdReturnPurchase);
			//modify by fu 2016-01-01 将接口推送消息放到最后面推送
			pdSendInfoManager.doWhileOrderConfirmed(pdReturnPurchase);
		}
		
	}

	public long getProductCountByRpNo(String rpNo) {
		// TODO Auto-generated method stub
		long ret =0;
		PdReturnPurchase pdReturnPurchase = dao.getPdReturnPurchase(rpNo);
		Set sets = pdReturnPurchase.getPdReturnPurchaseDetails();
		
		Iterator iterator =sets.iterator();
		while(iterator.hasNext()){
			ret += ((PdReturnPurchaseDetail)(iterator.next())).getQty();
		}
		return ret;
	}

	public void updateAmount(String rpNo) {
		// TODO Auto-generated method stub
		PdReturnPurchase pdReturnPurchase = dao.getPdReturnPurchase(rpNo);
		Set sets = pdReturnPurchase.getPdReturnPurchaseDetails();
		BigDecimal amount = new BigDecimal(0);
		
		Iterator iterator=sets.iterator();
		while(iterator.hasNext()){
			PdReturnPurchaseDetail pdReturnPurchaseDetail = (PdReturnPurchaseDetail) iterator.next();
			amount.add(new BigDecimal(pdReturnPurchaseDetail.getPrice().floatValue()*pdReturnPurchaseDetail.getQty()));
		}
		pdReturnPurchase.setAmount(amount);
		dao.savePdReturnPurchase(pdReturnPurchase);
	}


    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdReturnPurchaseDao(PdReturnPurchaseDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdReturnPurchaseManager#getPdReturnPurchases(com.joymain.jecs.pd.model.PdReturnPurchase)
     */
    public List getPdReturnPurchases(final PdReturnPurchase pdReturnPurchase) {
        return dao.getPdReturnPurchases(pdReturnPurchase);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdReturnPurchaseManager#getPdReturnPurchase(String rpNo)
     */
    public PdReturnPurchase getPdReturnPurchase(final String rpNo) {
        return dao.getPdReturnPurchase(new String(rpNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdReturnPurchaseManager#savePdReturnPurchase(PdReturnPurchase pdReturnPurchase)
     */
    public void savePdReturnPurchase(PdReturnPurchase pdReturnPurchase) {
    	String finaceRemark =  pdReturnPurchase.getFinanceRemark();
    	//modify by fu 2016-01-01 先将审核的发货退回单保存，然后再推送发货退回单。
    	//
    	 dao.savePdReturnPurchase(pdReturnPurchase);
        //------------begin------将确认之前的发货退回单的消息推送下去
        Integer orderFlag = pdReturnPurchase.getOrderFlag();
   	    Set<PdReturnPurchaseDetail> pdReturnPurchaseDetails = pdReturnPurchase.getPdReturnPurchaseDetails();
        if((null!=orderFlag)&&(orderFlag==1) && (null!=pdReturnPurchaseDetails)&&(pdReturnPurchaseDetails.size()>0)){
       	    log.info("已经审核的发货退回单接口开始-在PdReturnPurchaseManagerImpl类的pushJprRefund方法中运行");
    		String result = this.sendPdReturnPurchaseCheck(pdReturnPurchase);
    		log.info("已经审核的发货退回单接口结束-在PdReturnPurchaseManagerImpl类的pushJprRefund方法中运行");
    		//modify fu 2015-09-11 现在是已审核的发货退回单才推送下去，然后已审核的发货退回单不能转新单，所以之前考虑的问题:收到退货入库消息后，退货编辑仍然保持编辑内容的问题不存在
    		/*if(!StringUtil.isEmpty(result)){
				//modify fu 2015-09-11 为解决在编辑的过程中，收到退货入库消息后，退货编辑仍然保持编辑内容的问题，做以下处理。---begin
    			PdReturnPurchase  pdReturnPurchaseDBA = this.getPdReturnPurchaseDBA(pdReturnPurchase);
    			pdReturnPurchaseDBA.setCustomer(sysUserManager.getSysUser(pdReturnPurchaseDBA.getFinanceUsrCode()));
    			dao.savePdReturnPurchase(pdReturnPurchaseDBA);
    			return;
    		}else{
    			dao.savePdReturnPurchase(pdReturnPurchase);
    			return;
    		}*/
        }
    	//------------end----将确认之前的发货退回单的消息推送下去
        
       
    }

    /**
     * 获取数据库中的pdReturnPurchase对象
     * @author fu 2015-09-14 
     * @param pdReturnPurchase
     * @return pdReturnPurchase
     */
    private PdReturnPurchase getPdReturnPurchaseDBA(PdReturnPurchase pdReturnPurchase) {
		return dao.getPdReturnPurchaseDBA(pdReturnPurchase);
	}

	/**
     * 物流模块接口方法-将已经审核的发货退回单的消息推送下去
     * @author fu 2015-08-20
     * @param pdReturnPurchase
     */
    private String sendPdReturnPurchaseCheck(PdReturnPurchase pdReturnPurchase) {
    	 PdReturnCheck pdReturnCheck = new PdReturnCheck();
    	 pdReturnCheck.setRpNo(pdReturnPurchase.getRpNo());
    	 pdReturnCheck.setCustomerCode(pdReturnPurchase.getCustomer().getUserCode());
    	 pdReturnCheck.setReturnWarehouseNo(pdReturnPurchase.getReturnWarehouseNo());
    	 //modify by fu 2016-02-25 制单备注
    	 pdReturnCheck.setRemark(pdReturnPurchase.getRemark());
    	 List<ReturnProduct> returnProduct = new ArrayList();
    	 Set<PdReturnPurchaseDetail> pdReturnPurchaseDetails = pdReturnPurchase.getPdReturnPurchaseDetails();
    	 Iterator it = pdReturnPurchaseDetails.iterator();
    	 while(it.hasNext()){
    		 ReturnProduct returnProductOb = new ReturnProduct(); 
    		 PdReturnPurchaseDetail pdReturnPurchaseDetail = (PdReturnPurchaseDetail) it.next();
    		 returnProductOb.setProductNo(pdReturnPurchaseDetail.getProductNo());
    		 returnProductOb.setQty(pdReturnPurchaseDetail.getQty().toString());
    		 returnProductOb.setErpProductNo(pdReturnPurchaseDetail.getErpProductNo());
    		 returnProduct.add(returnProductOb);
    	 }
    	 pdReturnCheck.setReturnProduct(returnProduct);
    	 log.info("已经审核的发货退回单接口-在PdReturnPurchaseManagerImpl类的sendPdReturnPurchaseCheck方法中运行:接口数据准备完毕");

    	 JSONObject jo = JSONObject.fromObject(pdReturnCheck);
    	 String joString = jo.toString();

 		//这样退货单接口的数据就准备好了 returno
 		//调用发送接口--向OMS发送----modify gw 2014-12-23----开始
 		MsgSendService msgSendService = new MsgSendService();
 		//方法名
 		String methodName = "ship.savePdReturnPurchase";//OMS那边方法名
 		msgSendService.setSender(Constants.OMS_SEND);//OMS平台
 		
 		
		//modify fu 2015-09-11收到发货退回入库消息后，不往后续系统发送接口消息。---begin
 		String orderFlag = dao.getPdReturnOrderFlag(pdReturnPurchase.getRpNo());
 		if((!StringUtil.isEmpty(orderFlag))&&(3<=Integer.parseInt(orderFlag))){
 			return "noSendInterface";
 			//modify fu 2015-09-11   收到发货退回入库消息后，不往后续系统发送接口消息
 		}
 		//modify fu 2015-09-11  收到发货退回入库消息后，不往后续系统发送接口消息。---end
 		else{
 			    // modify by fu 2017-1-18 将已审核的发货退回单推送到oms的方法注释掉
		 		/*String aa = "";
		 		 // aa = "123";//为了自测方便特意加上这一行，后面要删除掉；
		 		 aa = msgSendService.post(joString, methodName);//真正的接口联调的时候，这一行要放开注释 
		   	    log.info("已经审核的发货退回单接口-在PdReturnPurchaseManagerImpl类的sendPdReturnPurchaseCheck方法中运行:接口数据推送完毕");
		
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
*/		 		return null;
 		}
	}

	/**
     * @see com.joymain.jecs.pd.service.PdReturnPurchaseManager#removePdReturnPurchase(String rpNo)
     */
    public void removePdReturnPurchase(final String rpNo) {
    	try{
	    	/*//发货退回单删除接口  modify fu 20150906
	    	String result = this.pdReturnDeleteInterface(rpNo);
	    	if((!StringUtil.isEmpty(result))&&"noDelete".equals(result)){
				// 在发货退回单接口消息推送下去之前，判断发货退回单有没有接收到接口发货退回单入库操作，如果有，那么就不允许删除发货退回单
	    	}else{
	             dao.removePdReturnPurchase(new String(rpNo));
	    	}*/
    		//modify fu 2015-10-28 因业务需要，发货退回单的删除借口不需要，特将它注释掉（之前测试这个接口是没问题的）
	    	dao.removePdReturnPurchase(new String(rpNo));
	    	
    	}catch(Exception e){
    		e.printStackTrace();
			log.info("在PdReturnPurchaseManagerImpl类的removePdReturnPurchase方法中运行异常:"+e.toString());
    	}
    }
    
    /**
     * 发货退回单删除接口
     * @author fu 20150906
     * @param rpNo
     */
    private String pdReturnDeleteInterface(String rpNo) throws Exception{
			PdReturnDelete pdReturnDelete = new PdReturnDelete();
			pdReturnDelete.setRpNo(rpNo);
			pdReturnDelete.setIsDelete("Y");
			JSONObject jo = JSONObject.fromObject(pdReturnDelete);
	    	String joString = jo.toString();
			log.info("发货退回单删除接口-在PdReturnPurchaseManagerImpl类的pdReturnDeleteInterface方法中运行:接口数据准备完毕");

			
	 		//调用发送接口--向OMS发送----modify gw 2014-12-23----开始
	 		MsgSendService msgSendService = new MsgSendService();
	 		//方法名
	 		String methodName = "ship.removePdReturnPurchase";//OMS那边方法名
	 		msgSendService.setSender(Constants.OMS_SEND);//OMS平台
	 		String orderFlag = dao.getPdReturnOrderFlag(rpNo);
	 		if((!StringUtil.isEmpty(orderFlag))&&"3".equals(orderFlag)){
				// 在发货退回单接口消息推送下去之前，判断发货退回单有没有接收到接口发货退回单入库操作，如果有，那么就不允许删除发货退回单
	 			return "noDelete";
	 		}else{
	 			// modify by fu 2017-1-18 发货退回单删除接口推送到oms系统的方法关闭掉
		 		 /*String aa = "";
		 		 //aa = "123";//为了自测方便特意加上这一行，后面要删除掉；
		 		 aa = msgSendService.post(joString, methodName);//真正的接口联调的时候，这一行要放开注释 
		   	     log.info("发货退回单删除接口-在PdReturnPurchaseManagerImpl类的pdReturnDeleteInterface方法中运行:接口数据推送完毕");
	
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
*/	 		    return null;
	 		}
	}
	
	//added for getPdReturnPurchasesByCrm
    public List getPdReturnPurchasesByCrm(CommonRecord crm, Pager pager){
	return dao.getPdReturnPurchasesByCrm(crm,pager);
    }

	public List getReturnDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getReturnDetails(crm);
	}
	
	 /**
     * 发货退回入库接口
     * @author fu 20150820
     * @param jsonString
     * @return rspEntity
     */
    public RspEntity reSetPdReturnPurchaseStatus(String jsonString){
    	log.info("发货退回入库接口开始-在类PdReturnPurchaseManagerImpl的方法reSetPdReturnPurchaseStatus中运行");
		RspEntity rspEntity = new RspEntity();
		PdReturnStatus pdReturnStatus = new PdReturnStatus();
    	try{
    		if(StringUtil.isEmpty(jsonString)){
    			rspEntity.setSub_msg(" json is not null ");
				log.info("发货退回入库接口：json字符串为空");
				return this.getRspEntityString(rspEntity);
    		}else{
    			Map<String,Class> classMap = new HashMap<String,Class>();
    			pdReturnStatus = InterfaceJsonUtil.returnnoJsonToModel(jsonString,PdReturnStatus.class,classMap);
        		String checkResult = this.getCheckPereturnPurchaseResult(pdReturnStatus);
        		if(null!=checkResult){
        			rspEntity.setSub_msg(checkResult);
        			return this.getRspEntityString(rspEntity);
        		}else{
        			String intoStatus = pdReturnStatus.getIntoStatus();
        			 String rpNo = pdReturnStatus.getRpNo();
        			if("Y".equals(intoStatus)){
        				PdReturnPurchase pdReturnPurchase = dao.getPdReturnPurchaseForUpdate(rpNo);
        				//如果EC系统的发货退回单状态已经是已确认的状态，那么就不去修改发货退回单的状态
        				Integer orderFlag = pdReturnPurchase.getOrderFlag();
        				//如果EC系统的发货退回单状态已经不是已确认的状态，那么就去修改发货退回单的状态
        				if(2>=orderFlag){
        					pdReturnPurchase.setOkUsrCode("rootInterface");
            				pdReturnPurchase.setOkTime(new Date());
        					pdReturnPurchase.setOrderFlag(3);
            				String flag = "1";
            				this.confirmPdReturnPurchase(pdReturnPurchase, flag);
        				}
        			}
        			
        			rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);
                	log.info("发货退回入库接口整个流程成功走完");
        			return this.getRspEntityString(rspEntity);
        		}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
        	log.info("发货退回入库接口开始-在类PdReturnPurchaseManagerImpl的方法reSetPdReturnPurchaseStatus中运行异常:"+e.toString());
        	rspEntity.setSub_msg("发货退回入库接口运行异常");
			return this.getRspEntityString(rspEntity);
    	}
    }

    /**
     * 发货退回入库接口-数据格式校验
     * @author fu 20150820
     * @param pdReturnStatus
     * @return rspEntity
     */
	private String getCheckPereturnPurchaseResult(PdReturnStatus pdReturnStatus) {
		if( null == pdReturnStatus){
			return " json is null ";
		}else{
			 String rpNo = pdReturnStatus.getRpNo();
			 if(StringUtil.isEmpty(rpNo)){
				return "rpNo(发货退回单号) is null ";
			 }else{
				PdReturnPurchase pdReturnPurchase = dao.getPdReturnPurchase(rpNo);
				if(null==pdReturnPurchase){
					return "发货退回单号"+rpNo+"在EC系统中不存在";
				}
			}
			String intoStatus = pdReturnStatus.getIntoStatus();
			if(StringUtil.isEmpty(intoStatus)){
				 return "intoStatus(入库状态) is null ";
			}else{
				if("Y".equals(intoStatus)||"N".equals(intoStatus)){
				}else{
					return "intoStatus(入库状态) must be Y or N ";
				}
			}
		}
		return null;
	}
	
	/**
     * 处理OMS等接口传递过来的数据返回值的处理
     * @author fx 2015-8-20
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
	 * 根据发货退回单号查询发货退回单状态信息
	 * @author fu 2015-09-11 
	 * @param rpNo
	 * @return string
	 */
	public String getPdReturnOrderFlag(String rpNo){
		return dao.getPdReturnOrderFlag(rpNo);
	}
    
    
}
