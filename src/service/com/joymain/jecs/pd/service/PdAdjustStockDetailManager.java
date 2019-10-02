
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdAdjustStockDetail;
import com.joymain.jecs.pd.dao.PdAdjustStockDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdAdjustStockDetailManager extends Manager {
    /**
     * Retrieves all of the pdAdjustStockDetails
     */
    public List getPdAdjustStockDetails(PdAdjustStockDetail pdAdjustStockDetail);

    /**
     * Gets pdAdjustStockDetail's information based on uniNo.
     * @param uniNo the pdAdjustStockDetail's uniNo
     * @return pdAdjustStockDetail populated pdAdjustStockDetail object
     */
    public PdAdjustStockDetail getPdAdjustStockDetail(final String uniNo);

    /**
     * Saves a pdAdjustStockDetail's information
     * @param pdAdjustStockDetail the object to be saved
     */
    public void savePdAdjustStockDetail(PdAdjustStockDetail pdAdjustStockDetail);

    /**
     * Removes a pdAdjustStockDetail from the database by uniNo
     * @param uniNo the pdAdjustStockDetail's uniNo
     */
    public void removePdAdjustStockDetail(final String uniNo);
    //added for getPdAdjustStockDetailsByCrm
    public List getPdAdjustStockDetailsByCrm(CommonRecord crm, Pager pager);

	public List getTotalPdAdjustStockDetails(CommonRecord crm);

	public boolean existPdAdjustStockDetail(String productNo, String asNo);
}

