package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.pd.model.PdAdjustStock;
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdWarehouseStock;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdWarehouseStockManager extends Manager {
	// public Long enoughStockSend(String warehouseNo, String productNo, Long
	// qty);

	public void updatePdWarehouseStock(PdCombinationOrder pdCombinationOrder);
	
	public void updatePdWarehouseStock(JpoCounterOrder jpoCounterOrder);

	public void updatePdWarehouseStock(PdReturnPurchase pdReturnPurchase);

	public void updatePdWarehouseStock(PdSendInfo pdSendInfo);

	public void updatePdWarehouseStock(JprRefund jprRefund);

	/**
	 * Retrieves all of the pdWarehouseStocks
	 */
	public List getPdWarehouseStocks(PdWarehouseStock pdWarehouseStock);

	public List getPdWarehouseStocksByGroup(CommonRecord crm, String groupType);

	/**
	 * Gets pdWarehouseStock's information based on uniNo.
	 * 
	 * @param uniNo
	 *            the pdWarehouseStock's uniNo
	 * @return pdWarehouseStock populated pdWarehouseStock object
	 */
	public PdWarehouseStock getPdWarehouseStock(final String uniNo);

	/**
	 * Saves a pdWarehouseStock's information
	 * 
	 * @param pdWarehouseStock
	 *            the object to be saved
	 */
	public void savePdWarehouseStock(PdWarehouseStock pdWarehouseStock);

	/**
	 * Removes a pdWarehouseStock from the database by uniNo
	 * 
	 * @param uniNo
	 *            the pdWarehouseStock's uniNo
	 */
	public void removePdWarehouseStock(final String uniNo);

	// added for getPdWarehouseStocksByCrm
	public List getPdWarehouseStocksByCrm(CommonRecord crm, Pager pager);

	public Long enoughStockSend(String warehouseNo, String productNo, long qty);

	public boolean enoughStock(String warehouseNo, String productNo, long qty);

	public void updatePdWarehouseStock(PdEnterWarehouse pdEnterWarehouse);

	public void updatePdWarehouseStock(PdFlitWarehouse pdFlitWarehouse,
			String string);

	public void updatePdWarehouseStock(PdOutWarehouse pdOutWarehouse);

	public void updatePdWarehouseStock(PdAdjustStock pdAdjustStock);
	public void reupdatePdWarehouseStock(PdSendInfo pdSendInfo);
	public void updatePdWarehouseStock(PdExchangeOrder pdExchangeOrder);
	public Long getStock(String companyCode ,String warehouseNo,String productNo);
	//Add By WuCF 20130705 AWT实现加急功能
	public boolean updateHurryFlag(String siNo);
	
	//Add By WuCF 20140624 AWT实现导出订单以及明细并返回下载的URL连接地址
	public String exportOrderAndInfo(String memberOrderNo,String userCode,String logType,String startLogTime,String endLogTime,
			String orderType,String status,String isRetreatOrder,String province,String city,
			String district,String productType,String payByCoin,String payByJJ,String memberType,String operUserCode,String type,String logDesc);
	
	/**
	 * 接口询问机制-公共方法
	 * @author fu 2016-03-21
	 * @param pdLogisticsNumbers 物流单号：发货单号，退货单号，换货单号
	 * @param type 用数字表示询问类型:11发货单编辑、12发货单的暂不发货、13发货单的暂停发货、14发货单的发货作废；21退货单编辑、22退货单删除；31换货单编辑、32换货单删除；
	 * @param methodName 接口方法名
	 * @return  
	 */
	public String getPdLogisticsCanModify(String pdLogisticsNumbers,String type,String methodName);
	
}
