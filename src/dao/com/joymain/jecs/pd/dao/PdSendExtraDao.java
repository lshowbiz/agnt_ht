
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdSendExtra;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdSendExtraDao extends Dao {

    /**
     * Retrieves all of the pdSendExtras
     */
    public List getPdSendExtras(PdSendExtra pdSendExtra);

    /**
     * Gets pdSendExtra's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniId the pdSendExtra's uniId
     * @return pdSendExtra populated pdSendExtra object
     */
    public PdSendExtra getPdSendExtra(final Long uniId);

    /**
     * Saves a pdSendExtra's information
     * @param pdSendExtra the object to be saved
     */    
    public void savePdSendExtra(PdSendExtra pdSendExtra);

    /**
     * Removes a pdSendExtra from the database by uniId
     * @param uniId the pdSendExtra's uniId
     */
    public void removePdSendExtra(final Long uniId);
    //added for getPdSendExtrasByCrm
    public List getPdSendExtrasByCrm(CommonRecord crm, Pager pager);
}

