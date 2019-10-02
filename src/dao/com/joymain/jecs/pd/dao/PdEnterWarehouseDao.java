
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdEnterWarehouseDao extends Dao {

    /**
     * Retrieves all of the pdEnterWarehouses
     */
    public List getPdEnterWarehouses(PdEnterWarehouse pdEnterWarehouse);

    /**
     * Gets pdEnterWarehouse's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param ewNo the pdEnterWarehouse's ewNo
     * @return pdEnterWarehouse populated pdEnterWarehouse object
     */
    public PdEnterWarehouse getPdEnterWarehouse(final String ewNo);

    /**
     * Saves a pdEnterWarehouse's information
     * @param pdEnterWarehouse the object to be saved
     */    
    public void savePdEnterWarehouse(PdEnterWarehouse pdEnterWarehouse);

    /**
     * Removes a pdEnterWarehouse from the database by ewNo
     * @param ewNo the pdEnterWarehouse's ewNo
     */
    public void removePdEnterWarehouse(final String ewNo);
    //added for getPdEnterWarehousesByCrm
    public List getPdEnterWarehousesByCrm(CommonRecord crm, Pager pager);

	public List getEnterDetails(CommonRecord crm);
}

