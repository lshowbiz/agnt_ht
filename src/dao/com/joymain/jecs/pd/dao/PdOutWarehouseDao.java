
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdOutWarehouseDao extends Dao {

    /**
     * Retrieves all of the pdOutWarehouses
     */
    public List getPdOutWarehouses(PdOutWarehouse pdOutWarehouse);

    /**
     * Gets pdOutWarehouse's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param owNo the pdOutWarehouse's owNo
     * @return pdOutWarehouse populated pdOutWarehouse object
     */
    public PdOutWarehouse getPdOutWarehouse(final String owNo);

    /**
     * Saves a pdOutWarehouse's information
     * @param pdOutWarehouse the object to be saved
     */    
    public void savePdOutWarehouse(PdOutWarehouse pdOutWarehouse);

    /**
     * Removes a pdOutWarehouse from the database by owNo
     * @param owNo the pdOutWarehouse's owNo
     */
    public void removePdOutWarehouse(final String owNo);
    //added for getPdOutWarehousesByCrm
    public List getPdOutWarehousesByCrm(CommonRecord crm, Pager pager);
//    public void updatePdEnterWarehouseAmount(String owNo);
    
    public List getSumGroupByOwt(CommonRecord crm);
    
    public List getOutDetails(CommonRecord crm);
}

