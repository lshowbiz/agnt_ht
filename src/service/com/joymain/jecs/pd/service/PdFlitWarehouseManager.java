
package com.joymain.jecs.pd.service;

import java.util.List;

import org.springframework.validation.BindException;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.pd.dao.PdFlitWarehouseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdFlitWarehouseManager extends Manager {
	
	public Long getOnWayCountByWarehouseNo(String warehouseNo,String productNo);
	public Long getSumOnWayByWarehouseNo(CommonRecord crm,String productNo);
	public long getProductCountByFwNo(String fwNo); 
	public void updateAmount(final String fwNo);
	public void confirmFlitWarehouse(PdFlitWarehouse pdFlitWarehouse,String flag);
    /**
     * Retrieves all of the pdFlitWarehouses
     */
    public List getPdFlitWarehouses(PdFlitWarehouse pdFlitWarehouse);

    /**
     * Gets pdFlitWarehouse's information based on fwNo.
     * @param fwNo the pdFlitWarehouse's fwNo
     * @return pdFlitWarehouse populated pdFlitWarehouse object
     */
    public PdFlitWarehouse getPdFlitWarehouse(final String fwNo);

    /**
     * Saves a pdFlitWarehouse's information
     * @param pdFlitWarehouse the object to be saved
     */
    public void savePdFlitWarehouse(PdFlitWarehouse pdFlitWarehouse);

    /**
     * Removes a pdFlitWarehouse from the database by fwNo
     * @param fwNo the pdFlitWarehouse's fwNo
     */
    public void removePdFlitWarehouse(final String fwNo);
    //added for getPdFlitWarehousesByCrm
    public List getPdFlitWarehousesByCrm(CommonRecord crm, Pager pager);
    
    public List getFlitDetail(CommonRecord crm);
    
    /**
     * 录入或编辑之前，不为空的校验
     * @author gw 2015-01-21
     * @param outWarehouseNo
     * @param inWarehouseNo
     * @param errors
     * @param characterCoding
     * @return boolean
     */
    public boolean getEmptyCheckResult(String outWarehouseNo,String inWarehouseNo,BindException errors,String characterCoding);
    
}

