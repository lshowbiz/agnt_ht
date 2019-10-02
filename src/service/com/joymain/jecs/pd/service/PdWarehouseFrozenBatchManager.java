
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdWarehouseFrozenBatch;
import com.joymain.jecs.pd.dao.PdWarehouseFrozenBatchDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdWarehouseFrozenBatchManager extends Manager {
    /**
     * Retrieves all of the pdWarehouseFrozenBatchs
     */
    public List getPdWarehouseFrozenBatchs(PdWarehouseFrozenBatch pdWarehouseFrozenBatch);

    /**
     * Gets pdWarehouseFrozenBatch's information based on batchId.
     * @param batchId the pdWarehouseFrozenBatch's batchId
     * @return pdWarehouseFrozenBatch populated pdWarehouseFrozenBatch object
     */
    public PdWarehouseFrozenBatch getPdWarehouseFrozenBatch(final String batchId);

    /**
     * Saves a pdWarehouseFrozenBatch's information
     * @param pdWarehouseFrozenBatch the object to be saved
     */
    public void savePdWarehouseFrozenBatch(PdWarehouseFrozenBatch pdWarehouseFrozenBatch);

    /**
     * Removes a pdWarehouseFrozenBatch from the database by batchId
     * @param batchId the pdWarehouseFrozenBatch's batchId
     */
    public void removePdWarehouseFrozenBatch(final String batchId);
    //added for getPdWarehouseFrozenBatchsByCrm
    public List getPdWarehouseFrozenBatchsByCrm(CommonRecord crm, Pager pager);
    public void excuteFrozen();
}

