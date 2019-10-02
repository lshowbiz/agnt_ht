
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface BdOutwardBankDao extends Dao {

    /**
     * Retrieves all of the bdOutwardBanks
     */
    public List getBdOutwardBanks(BdOutwardBank bdOutwardBank);

    /**
     * Gets bdOutwardBank's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param bankId the bdOutwardBank's bankId
     * @return bdOutwardBank populated bdOutwardBank object
     */
    public BdOutwardBank getBdOutwardBank(final Long bankId);

    /**
     * Saves a bdOutwardBank's information
     * @param bdOutwardBank the object to be saved
     */    
    public void saveBdOutwardBank(BdOutwardBank bdOutwardBank);

    /**
     * Removes a bdOutwardBank from the database by bankId
     * @param bankId the bdOutwardBank's bankId
     */
    public void removeBdOutwardBank(final Long bankId);
    //added for getBdOutwardBanksByCrm
    public List getBdOutwardBanksByCrm(CommonRecord crm, Pager pager);
}

