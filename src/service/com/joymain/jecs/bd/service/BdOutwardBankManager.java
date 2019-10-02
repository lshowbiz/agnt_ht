
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.dao.BdOutwardBankDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface BdOutwardBankManager extends Manager {
    /**
     * Retrieves all of the bdOutwardBanks
     */
    public List getBdOutwardBanks(BdOutwardBank bdOutwardBank);

    /**
     * Gets bdOutwardBank's information based on bankId.
     * @param bankId the bdOutwardBank's bankId
     * @return bdOutwardBank populated bdOutwardBank object
     */
    public BdOutwardBank getBdOutwardBank(final String bankId);

    /**
     * Saves a bdOutwardBank's information
     * @param bdOutwardBank the object to be saved
     */
    public void saveBdOutwardBank(BdOutwardBank bdOutwardBank);

    /**
     * Removes a bdOutwardBank from the database by bankId
     * @param bankId the bdOutwardBank's bankId
     */
    public void removeBdOutwardBank(final String bankId);
    //added for getBdOutwardBanksByCrm
    public List getBdOutwardBanksByCrm(CommonRecord crm, Pager pager);
}

