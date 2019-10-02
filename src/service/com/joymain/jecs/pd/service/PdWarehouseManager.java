
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.dao.PdWarehouseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdWarehouseManager extends Manager {
    /**
	
     * Retrieves all of the pdWarehouses
     */
	
	public boolean existWarehouseNoByCompany(String warehouseNo,String companyCode);
	public boolean existWarehouseNo(String warehouseNo);
    public List getPdWarehouses(PdWarehouse pdWarehouse);

    /**
     * Gets pdWarehouse's information based on warehouseNo.
     * @param warehouseNo the pdWarehouse's warehouseNo
     * @return pdWarehouse populated pdWarehouse object
     */
    public PdWarehouse getPdWarehouse(final String warehouseNo);

    /**
     * Saves a pdWarehouse's information
     * @param pdWarehouse the object to be saved
     */
    public void savePdWarehouse(PdWarehouse pdWarehouse);

    /**
     * Removes a pdWarehouse from the database by warehouseNo
     * @param warehouseNo the pdWarehouse's warehouseNo
     */
    public void removePdWarehouse(final String warehouseNo);
    //added for getPdWarehousesByCrm
    public List getPdWarehousesByCrm(CommonRecord crm, Pager pager);
    public List getPdWarehousesByCrm(CommonRecord crm);
}

