package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdExchangeOrderDao extends Dao {

	public List getTotalPdExchangeOrder(CommonRecord crm);
    /**
     * Retrieves all of the pdExchangeOrders
     */
    public List getPdExchangeOrders(PdExchangeOrder pdExchangeOrder);

    /**
     * Gets pdExchangeOrder's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param eoNo the pdExchangeOrder's eoNo
     * @return pdExchangeOrder populated pdExchangeOrder object
     */
    public PdExchangeOrder getPdExchangeOrder(final String eoNo);

    /**
     * Saves a pdExchangeOrder's information
     * @param pdExchangeOrder the object to be saved
     */    
    public void savePdExchangeOrder(PdExchangeOrder pdExchangeOrder);

    /**
     * Removes a pdExchangeOrder from the database by eoNo
     * @param eoNo the pdExchangeOrder's eoNo
     */
    public void removePdExchangeOrder(final String eoNo);
    //added for getPdExchangeOrdersByCrm
    public List getPdExchangeOrdersByCrm(CommonRecord crm, Pager pager);
    //add by lihao 营业管理模块销售业绩统计表  需要加入换货单的信息
	public List getPdExchangeOrderDetailStaticsCheckedCompany(String startDate,
			String endDate, String companyCode, String productType);
	
	public List getPdExchangeOrderBackStaticsCheckedCompany(String startDate,
			String endDate, String companyCode, String productType);
	
	//add by lihao
	public List getTotalPdExchangeOrder2(CommonRecord crm);	
	
	 /**
		 * 查询未推送到后续系统的自助换货单
		 * @author fu 2016-04-12
		 * @return
		 */
		public List<PdExchangeOrder> getNotSendPdExchangeOrder();
		
		/**
		 * 将自助换货单状态改为已推送状态
		 * @author fu 2016-04-12
		 * @param eoNo
		 * @return
		 */
		public void reSetpdExchangeOrderSendStatus(String eoNo);
		
		/**
		 * 取消或恢复自助换货单
		 * @author fu 2016-04-12
		 * @param eoNo 自助换货单号
		 * @param  cancelExchange  是否取消自助换货单：Y取消，空恢复
		 * @return string
		 */
		public String reSetCancelExchange(String eoNo, String cancelExchange);
		
		
		/**
		 * 获取换货单的状态
		 * @author fu 2016-04-25
		 * @param eoNo
		 * @return
		 */
		public Integer getPdExchangeOrderOrderFlag(String eoNo);
		
}

