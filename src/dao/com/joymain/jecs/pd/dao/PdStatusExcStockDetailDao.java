
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdStatusExcStockDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdStatusExcStockDetailDao extends Dao {

    /**
     * Retrieves all of the pdStatusExcStockDetails
     */
    public List getPdStatusExcStockDetails(PdStatusExcStockDetail pdStatusExcStockDetail);

    /**
     * Gets pdStatusExcStockDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdStatusExcStockDetail's uniNo
     * @return pdStatusExcStockDetail populated pdStatusExcStockDetail object
     */
    public PdStatusExcStockDetail getPdStatusExcStockDetail(final Long uniNo);

    /**
     * Saves a pdStatusExcStockDetail's information
     * @param pdStatusExcStockDetail the object to be saved
     */    
    public void savePdStatusExcStockDetail(PdStatusExcStockDetail pdStatusExcStockDetail);

    /**
     * Removes a pdStatusExcStockDetail from the database by uniNo
     * @param uniNo the pdStatusExcStockDetail's uniNo
     */
    public void removePdStatusExcStockDetail(final Long uniNo);
    //added for getPdStatusExcStockDetailsByCrm
    public List getPdStatusExcStockDetailsByCrm(CommonRecord crm, Pager pager);
}

