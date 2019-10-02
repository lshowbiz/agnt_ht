
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdOutWarehouseDetail;
import com.joymain.jecs.pd.dao.PdOutWarehouseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdOutWarehouseDetailManager extends Manager {
    /**
     * Retrieves all of the pdOutWarehouseDetails
     */
    public List getPdOutWarehouseDetails(PdOutWarehouseDetail pdOutWarehouseDetail);

    /**
     * Gets pdOutWarehouseDetail's information based on uniNo.
     * @param uniNo the pdOutWarehouseDetail's uniNo
     * @return pdOutWarehouseDetail populated pdOutWarehouseDetail object
     */
    public PdOutWarehouseDetail getPdOutWarehouseDetail(final String uniNo);

    /**
     * Saves a pdOutWarehouseDetail's information
     * @param pdOutWarehouseDetail the object to be saved
     */
    public void savePdOutWarehouseDetail(PdOutWarehouseDetail pdOutWarehouseDetail);

    /**
     * Removes a pdOutWarehouseDetail from the database by uniNo
     * @param uniNo the pdOutWarehouseDetail's uniNo
     */
    public void removePdOutWarehouseDetail(final String uniNo);
    //added for getPdOutWarehouseDetailsByCrm
    public List getPdOutWarehouseDetailsByCrm(CommonRecord crm, Pager pager);

	public List getTotalPdOutWarehouseDetails(CommonRecord crm);

	public boolean existPdOutWarehouseDetail(String productNo, String owNo);

	public boolean existPdOutWarehouseDetail(String productNo, String owNo,
			Long uniNo);
}

