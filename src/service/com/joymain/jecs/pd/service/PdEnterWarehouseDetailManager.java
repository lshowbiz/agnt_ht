
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.pd.dao.PdEnterWarehouseDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdEnterWarehouseDetailManager extends Manager {
	public List getTotolPdEnterWarehouseDetails(CommonRecord crm);
	public boolean existPdEnterWarehouseDetail(String productNo,String ewNo); 
	
	public List getPdEnterWarehouseDetails(String productNo,String ewNo); 
    /**
     * Retrieves all of the pdEnterWarehouseDetails
     */
    public List getPdEnterWarehouseDetails(PdEnterWarehouseDetail pdEnterWarehouseDetail);

    /**
     * Gets pdEnterWarehouseDetail's information based on uniNo.
     * @param uniNo the pdEnterWarehouseDetail's uniNo
     * @return pdEnterWarehouseDetail populated pdEnterWarehouseDetail object
     */
    public PdEnterWarehouseDetail getPdEnterWarehouseDetail(final String uniNo);

    /**
     * Saves a pdEnterWarehouseDetail's information
     * @param pdEnterWarehouseDetail the object to be saved
     */
    public void savePdEnterWarehouseDetail(PdEnterWarehouseDetail pdEnterWarehouseDetail);

    /**
     * Removes a pdEnterWarehouseDetail from the database by uniNo
     * @param uniNo the pdEnterWarehouseDetail's uniNo
     */
    public void removePdEnterWarehouseDetail(final String uniNo);
    //added for getPdEnterWarehouseDetailsByCrm
    public List getPdEnterWarehouseDetailsByCrm(CommonRecord crm, Pager pager);
}

