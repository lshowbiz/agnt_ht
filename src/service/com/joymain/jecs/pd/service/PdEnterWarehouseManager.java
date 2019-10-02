
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.dao.PdEnterWarehouseDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdEnterWarehouseManager extends Manager {
    /**
     * Retrieves all of the pdEnterWarehouses
     */
    public List getPdEnterWarehouses(PdEnterWarehouse pdEnterWarehouse);

    /**
     * Gets pdEnterWarehouse's information based on ewNo.
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

	public Long getProductCountByEwNo(String ewNo);

	public void confirmPdEnterWarehouse(PdEnterWarehouse pdEnterWarehouse);
	
	public List getEnterDetails(CommonRecord crm);
}

