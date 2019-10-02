
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.fi.dao.FiBankbookBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiBankbookBalanceManager extends Manager {
    /**
     * Retrieves all of the fiBankbookBalances
     */
    public List getFiBankbookBalances(FiBankbookBalance fiBankbookBalance);

    /**
     * Gets fiBankbookBalance's information based on fbbId.
     * @param fbbId the fiBankbookBalance's fbbId
     * @return fiBankbookBalance populated fiBankbookBalance object
     */
    public FiBankbookBalance getFiBankbookBalance(final String fbbId);

    /**
     * Saves a fiBankbookBalance's information
     * @param fiBankbookBalance the object to be saved
     */
    public void saveFiBankbookBalance(FiBankbookBalance fiBankbookBalance);
    public FiBankbookBalance getFiBankbookBalance(final String userCode,final String backbookType);

    /**
     * Removes a fiBankbookBalance from the database by fbbId
     * @param fbbId the fiBankbookBalance's fbbId
     */
    public void removeFiBankbookBalance(final String fbbId);
    //added for getFiBankbookBalancesByCrm
    public List getFiBankbookBalancesByCrm(CommonRecord crm, Pager pager);
    
    public List getFiProductPointBalancesByCrm(CommonRecord crm, Pager pager);
}

