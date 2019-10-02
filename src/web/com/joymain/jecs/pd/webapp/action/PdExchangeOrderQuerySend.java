package com.joymain.jecs.pd.webapp.action;

import java.util.List;

import org.jfree.util.Log;

import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.service.PdExchangeOrderManager;

/**
 * 自助换货单的定时推送
 * @author fu 2016-04-12
 *
 */
public class PdExchangeOrderQuerySend {
	private PdExchangeOrderManager pdExchangeOrderManager;

	public void setPdExchangeOrderManager(
			PdExchangeOrderManager pdExchangeOrderManager) {
		this.pdExchangeOrderManager = pdExchangeOrderManager;
	}
	
	/*public void getPdExchangeOrderTransferTask(){
		try{
			Log.info("在PdExchangeOrderQuerySend类的getPdExchangeOrderTransferTask方法中开始运行:自助换货单的定时推送开始");
			//查询满足条件未推送到后续系统的换货单数据
			List<PdExchangeOrder> lists = pdExchangeOrderManager.getNotSendPdExchangeOrder();
			if((null!=lists)&&(lists.size()>0)){
				for(int i=0;i<lists.size();i++){
					PdExchangeOrder pdExchangeOrder = lists.get(i);
					//将自助换货单状态改为已推送状态
					pdExchangeOrderManager.reSetpdExchangeOrderSendStatus(pdExchangeOrder.getEoNo());
					//自助换货单推送
					pdExchangeOrderManager.getSendpdExchangeOrder(pdExchangeOrder);
					Log.info("在PdExchangeOrderQuerySend类的getPdExchangeOrderTransferTask方法中运行:自助换货单"+pdExchangeOrder.getEoNo()+"推送成功");
					
					Log.info("在PdExchangeOrderQuerySend类的getPdExchangeOrderTransferTask方法中运行:自助换货单"+pdExchangeOrder.getEoNo()+"修改为已推送状态成功");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			Log.info("在PdExchangeOrderQuerySend类的getPdExchangeOrderTransferTask方法中运行-自助换货单的定时推送异常:"+e.toString());
		}
	}*/
    //modify by fu 2017-1-18注释掉自助换货的接口推送方法
}
