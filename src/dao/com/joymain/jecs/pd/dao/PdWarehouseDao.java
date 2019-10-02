
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdWarehouseDao extends Dao {

    /**
     * Retrieves all of the pdWarehouses
     */
    public List getPdWarehouses(PdWarehouse pdWarehouse);

    /**
     * Gets pdWarehouse's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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

