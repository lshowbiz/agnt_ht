
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdWarehouseFrozenBatch;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdWarehouseFrozenBatchDao extends Dao {

    /**
     * Retrieves all of the pdWarehouseFrozenBatchs
     */
    public List getPdWarehouseFrozenBatchs(PdWarehouseFrozenBatch pdWarehouseFrozenBatch);

    /**
     * Gets pdWarehouseFrozenBatch's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param batchId the pdWarehouseFrozenBatch's batchId
     * @return pdWarehouseFrozenBatch populated pdWarehouseFrozenBatch object
     */
    public PdWarehouseFrozenBatch getPdWarehouseFrozenBatch(final Long batchId);

    /**
     * Saves a pdWarehouseFrozenBatch's information
     * @param pdWarehouseFrozenBatch the object to be saved
     */    
    public void savePdWarehouseFrozenBatch(PdWarehouseFrozenBatch pdWarehouseFrozenBatch);

    /**
     * Removes a pdWarehouseFrozenBatch from the database by batchId
     * @param batchId the pdWarehouseFrozenBatch's batchId
     */
    public void removePdWarehouseFrozenBatch(final Long batchId);
    //added for getPdWarehouseFrozenBatchsByCrm
    public List getPdWarehouseFrozenBatchsByCrm(CommonRecord crm, Pager pager);

	public void addFrozenDetail(Long batchCode);
}

