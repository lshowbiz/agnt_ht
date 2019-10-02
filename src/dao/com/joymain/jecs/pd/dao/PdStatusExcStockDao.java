
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdStatusExcStock;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdStatusExcStockDao extends Dao {

    /**
     * Retrieves all of the pdStatusExcStocks
     */
    public List getPdStatusExcStocks(PdStatusExcStock pdStatusExcStock);

    /**
     * Gets pdStatusExcStock's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param seNo the pdStatusExcStock's seNo
     * @return pdStatusExcStock populated pdStatusExcStock object
     */
    public PdStatusExcStock getPdStatusExcStock(final String seNo);

    /**
     * Saves a pdStatusExcStock's information
     * @param pdStatusExcStock the object to be saved
     */    
    public void savePdStatusExcStock(PdStatusExcStock pdStatusExcStock);

    /**
     * Removes a pdStatusExcStock from the database by seNo
     * @param seNo the pdStatusExcStock's seNo
     */
    public void removePdStatusExcStock(final String seNo);
    //added for getPdStatusExcStocksByCrm
    public List getPdStatusExcStocksByCrm(CommonRecord crm, Pager pager);
}

