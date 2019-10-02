
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.pd.dao.PdOutWarehouseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdOutWarehouseManager extends Manager {
    /**
     * Retrieves all of the pdOutWarehouses
     */
    public List getPdOutWarehouses(PdOutWarehouse pdOutWarehouse);

    /**
     * Gets pdOutWarehouse's information based on owNo.
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

	public long getProductCountByOwNo(String owNo);

	public void confirmPdOutWarehouse(PdOutWarehouse pdOutWarehouse);
	public List getSumGroupByOwt(CommonRecord crm);
	 public List getOutDetails(CommonRecord crm);
}

