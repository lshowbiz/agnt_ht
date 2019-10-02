
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdWarehouseFrozenDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdWarehouseFrozenDetailDao extends Dao {

    /**
     * Retrieves all of the pdWarehouseFrozenDetails
     */
    public List getPdWarehouseFrozenDetails(PdWarehouseFrozenDetail pdWarehouseFrozenDetail);

    /**
     * Gets pdWarehouseFrozenDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdWarehouseFrozenDetail's uniNo
     * @return pdWarehouseFrozenDetail populated pdWarehouseFrozenDetail object
     */
    public PdWarehouseFrozenDetail getPdWarehouseFrozenDetail(final Long uniNo);

    /**
     * Saves a pdWarehouseFrozenDetail's information
     * @param pdWarehouseFrozenDetail the object to be saved
     */    
    public void savePdWarehouseFrozenDetail(PdWarehouseFrozenDetail pdWarehouseFrozenDetail);

    /**
     * Removes a pdWarehouseFrozenDetail from the database by uniNo
     * @param uniNo the pdWarehouseFrozenDetail's uniNo
     */
    public void removePdWarehouseFrozenDetail(final Long uniNo);
    //added for getPdWarehouseFrozenDetailsByCrm
    public List getPdWarehouseFrozenDetailsByCrm(CommonRecord crm, Pager pager);
}

