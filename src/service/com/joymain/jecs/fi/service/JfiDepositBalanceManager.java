
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiDepositBalance;
import com.joymain.jecs.fi.dao.JfiDepositBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiDepositBalanceManager extends Manager {
    /**
     * Retrieves all of the jfiDepositBalances
     */
    public List getJfiDepositBalances(JfiDepositBalance jfiDepositBalance);

    /**
     * Gets jfiDepositBalance's information based on fdbId.
     * @param fdbId the jfiDepositBalance's fdbId
     * @return jfiDepositBalance populated jfiDepositBalance object
     */
    public JfiDepositBalance getJfiDepositBalance(final String fdbId);

    /**
     * Saves a jfiDepositBalance's information
     * @param jfiDepositBalance the object to be saved
     */
    public void saveJfiDepositBalance(JfiDepositBalance jfiDepositBalance);

    /**
     * Removes a jfiDepositBalance from the database by fdbId
     * @param fdbId the jfiDepositBalance's fdbId
     */
    public void removeJfiDepositBalance(final String fdbId);
    //added for getJfiDepositBalancesByCrm
    public List getJfiDepositBalancesByCrm(CommonRecord crm, Pager pager);
}

