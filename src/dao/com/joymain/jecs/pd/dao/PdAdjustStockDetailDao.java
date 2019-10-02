
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdAdjustStockDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdAdjustStockDetailDao extends Dao {

    /**
     * Retrieves all of the pdAdjustStockDetails
     */
    public List getPdAdjustStockDetails(PdAdjustStockDetail pdAdjustStockDetail);

    /**
     * Gets pdAdjustStockDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdAdjustStockDetail's uniNo
     * @return pdAdjustStockDetail populated pdAdjustStockDetail object
     */
    public PdAdjustStockDetail getPdAdjustStockDetail(final Long uniNo);

    /**
     * Saves a pdAdjustStockDetail's information
     * @param pdAdjustStockDetail the object to be saved
     */    
    public void savePdAdjustStockDetail(PdAdjustStockDetail pdAdjustStockDetail);

    /**
     * Removes a pdAdjustStockDetail from the database by uniNo
     * @param uniNo the pdAdjustStockDetail's uniNo
     */
    public void removePdAdjustStockDetail(final Long uniNo);
    //added for getPdAdjustStockDetailsByCrm
    public List getPdAdjustStockDetailsByCrm(CommonRecord crm, Pager pager);

	public List getTotalPdAdjustStockDetails(CommonRecord crm);

	public List getPdAdjustStockDetails(String productNo, String asNo);
}

