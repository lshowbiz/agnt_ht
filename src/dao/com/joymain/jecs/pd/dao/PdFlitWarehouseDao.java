
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdFlitWarehouseDao extends Dao {

    /**
     * Retrieves all of the pdFlitWarehouses
     */
    public List getPdFlitWarehouses(PdFlitWarehouse pdFlitWarehouse);

    /**
     * Gets pdFlitWarehouse's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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

	public Long getOnWayCountByWarehouseNo(String warehouseNo, String productNo);

	public Long getSumOnWayByWarehouseNo(CommonRecord crm, String productNo);
	
	public List getFlitDetail(CommonRecord crm);
}

