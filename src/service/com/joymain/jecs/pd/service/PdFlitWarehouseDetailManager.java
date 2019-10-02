
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.dao.PdFlitWarehouseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdFlitWarehouseDetailManager extends Manager {
	
	public List getOnWayStaticsByCrm(CommonRecord crm);
	public List getFlitWarehouseStaticsByCrm(CommonRecord crm,String flag);
    /**
     * Retrieves all of the pdFlitWarehouseDetails
     */
    public List getPdFlitWarehouseDetails(PdFlitWarehouseDetail pdFlitWarehouseDetail);

    /**
     * Gets pdFlitWarehouseDetail's information based on uniNo.
     * @param uniNo the pdFlitWarehouseDetail's uniNo
     * @return pdFlitWarehouseDetail populated pdFlitWarehouseDetail object
     */
    public PdFlitWarehouseDetail getPdFlitWarehouseDetail(final String uniNo);

    /**
     * Saves a pdFlitWarehouseDetail's information
     * @param pdFlitWarehouseDetail the object to be saved
     */
    public void savePdFlitWarehouseDetail(PdFlitWarehouseDetail pdFlitWarehouseDetail);

    /**
     * Removes a pdFlitWarehouseDetail from the database by uniNo
     * @param uniNo the pdFlitWarehouseDetail's uniNo
     */
    public void removePdFlitWarehouseDetail(final String uniNo);
    //added for getPdFlitWarehouseDetailsByCrm
    public List getPdFlitWarehouseDetailsByCrm(CommonRecord crm, Pager pager);

	public List getTotalPdFlitWarehouseDetails(CommonRecord crm);

	public boolean existPdFlitWarehouseDetail(String productNo, String fwNo);
}

