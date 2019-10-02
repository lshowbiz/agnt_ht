
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdStatusExcStockDetail;
import com.joymain.jecs.pd.dao.PdStatusExcStockDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdStatusExcStockDetailManager extends Manager {
    /**
     * Retrieves all of the pdStatusExcStockDetails
     */
    public List getPdStatusExcStockDetails(PdStatusExcStockDetail pdStatusExcStockDetail);

    /**
     * Gets pdStatusExcStockDetail's information based on uniNo.
     * @param uniNo the pdStatusExcStockDetail's uniNo
     * @return pdStatusExcStockDetail populated pdStatusExcStockDetail object
     */
    public PdStatusExcStockDetail getPdStatusExcStockDetail(final String uniNo);

    /**
     * Saves a pdStatusExcStockDetail's information
     * @param pdStatusExcStockDetail the object to be saved
     */
    public void savePdStatusExcStockDetail(PdStatusExcStockDetail pdStatusExcStockDetail);

    /**
     * Removes a pdStatusExcStockDetail from the database by uniNo
     * @param uniNo the pdStatusExcStockDetail's uniNo
     */
    public void removePdStatusExcStockDetail(final String uniNo);
    //added for getPdStatusExcStockDetailsByCrm
    public List getPdStatusExcStockDetailsByCrm(CommonRecord crm, Pager pager);
}

