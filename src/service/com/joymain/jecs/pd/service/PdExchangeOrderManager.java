package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdExchangeOrderManager extends Manager {
    /**
     * Retrieves all of the pdExchangeOrders
     */
    public List getPdExchangeOrders(PdExchangeOrder pdExchangeOrder);

    public List getTotalPdExchangeOrder(CommonRecord crm);
    /**
     * Gets pdExchangeOrder's information based on eoNo.
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
    public List getPdExchangeOrdersByOrderNo(String orderNo);

	public void addPdExchangeOrder(PdExchangeOrder pdExchangeOrder)throws Exception;

	public void conformPdExchangeOrder(PdExchangeOrder pdExchangeOrder);

	/**
	 * 换货单新增
	 * @author gw 2015-05-29
	 * 
	 */
	public PdExchangeOrder pdExchangeOrderAdd(PdExchangeOrder pdExchangeOrder);


	/**
	 * 
	 * 营业管理统计页面添加换货单的信息
	 * @param createBTime
	 * @param createETime
	 * @param companyCode
	 * @param productType
	 * @return
	 */
	public List getPdExchangeOrderDetailStaticsCheckedCompany(String createBTime,
			String createETime, String companyCode, String productType);
	
	public List getPdExchangeOrderBackStaticsCheckedCompany(String createBTime,
			String createETime, String companyCode, String productType);

	public List getTotalPdExchangeOrder2(CommonRecord crm);		


	/**
	 * 查询未推送到后续系统的自助换货单
	 * @author fu 2016-04-12
	 * @return
	 */
	public List<PdExchangeOrder> getNotSendPdExchangeOrder();

	/**
	 * 自助换货单推送接口消息
	 * @author fu 2016-04-08
	 * @param pdExchangeOrder
	 */
	public void getSendpdExchangeOrder(PdExchangeOrder pdExchangeOrder);

	/**
	 * 将自助换货单状态改为已推送状态
	 * @author fu 2016-04-12
	 * @param eoNo
	 * @return
	 */
	public void reSetpdExchangeOrderSendStatus(String eoNo);

	
	/**
	 * 取消自助换货单
	 * @author fu 2016-04-12
	 * @param eoNo 自助换货单号
	 * @return string
	 */
	public String cancelExchangeY(String eoNo);
	
	/**
	 * 恢复自助换货单
	 * @author fu 2016-04-12
	 * @param eoNo 自助换货单号
	 * @return string
	 */
	public String cancelExchangeN(String eoNo);
	
	/**
	 * 
	 * @param orderType	
	 * @return	
	 */
	 String getOrderTypeDescByOrderType(String orderType);
		
	
	
	
}

