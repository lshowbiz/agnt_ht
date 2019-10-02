
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiProductPointBalance;
import com.joymain.jecs.fi.dao.FiProductPointBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiProductPointBalanceManager extends Manager {
    /**
     * Retrieves all of the fiProductPointBalances
     */
    public List getFiProductPointBalances(FiProductPointBalance fiProductPointBalance);

    /**
     * Gets fiProductPointBalance's information based on fbbId.
     * @param fbbId the fiProductPointBalance's fbbId
     * @return fiProductPointBalance populated fiProductPointBalance object
     */
    public FiProductPointBalance getFiProductPointBalance(final String fbbId);

    /**
     * Saves a fiProductPointBalance's information
     * @param fiProductPointBalance the object to be saved
     */
    public void saveFiProductPointBalance(FiProductPointBalance fiProductPointBalance);
    public FiProductPointBalance getFiProductPointBalance(final String userCode,final String backbookType);

    /**
     * Removes a fiProductPointBalance from the database by fbbId
     * @param fbbId the fiProductPointBalance's fbbId
     */
    public void removeFiProductPointBalance(final String fbbId);
    //added for getFiProductPointBalancesByCrm
    public List getFiProductPointBalancesByCrm(CommonRecord crm, Pager pager);
}

