
package com.joymain.jecs.pd.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdWarehouseStock;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdWarehouseStockDao extends Dao {

	public List getPdWarehouseStocksByWarehouseNo(String warehouseNo);
	public List getPdWarehouseStocksByProductNo(CommonRecord crm,String groupType);
    /**
     * Retrieves all of the pdWarehouseStocks
     */
    public List getPdWarehouseStocks(PdWarehouseStock pdWarehouseStock);

    /**
     * Gets pdWarehouseStock's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdWarehouseStock's uniNo
     * @return pdWarehouseStock populated pdWarehouseStock object
     */
    public PdWarehouseStock getPdWarehouseStock(final Long uniNo);

    /**
     * Saves a pdWarehouseStock's information
     * @param pdWarehouseStock the object to be saved
     */    
    public void savePdWarehouseStock(PdWarehouseStock pdWarehouseStock);

    /**
     * Removes a pdWarehouseStock from the database by uniNo
     * @param uniNo the pdWarehouseStock's uniNo
     */
    public void removePdWarehouseStock(final Long uniNo);
    //added for getPdWarehouseStocksByCrm
    public List getPdWarehouseStocksByCrm(CommonRecord crm, Pager pager);

	public PdWarehouseStock getPdWarehouseStockByProductNo(String resendSpNo,
			String productNo);

	public Integer getUnSendInfo(String warehouseNo, String productNo);

	public List getUnSendInfo(CommonRecord crm);

	public List getOnWay(CommonRecord crm);
	public List getSumOnWay(CommonRecord crm);
	public List getSumUnSendInfo(CommonRecord crm);
	public Long getStock(String companyCode ,String warehouseNo,String productNo);
	//Add By WuCF 20130705 AWT实现加急功能
	public boolean updateHurryFlag(String siNo);
	
	/**
	 * Add By WuCF 20140508
	 * 出库单已经审核状态
	 * @param crm
	 * @return
	 */
	public List getOutWarehouses(CommonRecord crm);
	
	/**
	 * Add By WuCF 20140508
	 * 调拨已经审核状态数量
	 * @param crm
	 * @return
	 */
	public List getPdFlitWarehouse(CommonRecord crm);
}

