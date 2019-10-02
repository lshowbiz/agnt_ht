
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdWarehouseFrozenDetail;
import com.joymain.jecs.pd.dao.PdWarehouseFrozenDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdWarehouseFrozenDetailManager extends Manager {
    /**
     * Retrieves all of the pdWarehouseFrozenDetails
     */
    public List getPdWarehouseFrozenDetails(PdWarehouseFrozenDetail pdWarehouseFrozenDetail);

    /**
     * Gets pdWarehouseFrozenDetail's information based on uniNo.
     * @param uniNo the pdWarehouseFrozenDetail's uniNo
     * @return pdWarehouseFrozenDetail populated pdWarehouseFrozenDetail object
     */
    public PdWarehouseFrozenDetail getPdWarehouseFrozenDetail(final String uniNo);

    /**
     * Saves a pdWarehouseFrozenDetail's information
     * @param pdWarehouseFrozenDetail the object to be saved
     */
    public void savePdWarehouseFrozenDetail(PdWarehouseFrozenDetail pdWarehouseFrozenDetail);

    /**
     * Removes a pdWarehouseFrozenDetail from the database by uniNo
     * @param uniNo the pdWarehouseFrozenDetail's uniNo
     */
    public void removePdWarehouseFrozenDetail(final String uniNo);
    //added for getPdWarehouseFrozenDetailsByCrm
    public List getPdWarehouseFrozenDetailsByCrm(CommonRecord crm, Pager pager);
}

