
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiDepositBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiDepositBalanceDao extends Dao {

    /**
     * Retrieves all of the jfiDepositBalances
     */
    public List getJfiDepositBalances(JfiDepositBalance jfiDepositBalance);

    /**
     * Gets jfiDepositBalance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param fdbId the jfiDepositBalance's fdbId
     * @return jfiDepositBalance populated jfiDepositBalance object
     */
    public JfiDepositBalance getJfiDepositBalance(final Long fdbId);

    /**
     * Saves a jfiDepositBalance's information
     * @param jfiDepositBalance the object to be saved
     */    
    public void saveJfiDepositBalance(JfiDepositBalance jfiDepositBalance);

    /**
     * Removes a jfiDepositBalance from the database by fdbId
     * @param fdbId the jfiDepositBalance's fdbId
     */
    public void removeJfiDepositBalance(final Long fdbId);
    //added for getJfiDepositBalancesByCrm
    public List getJfiDepositBalancesByCrm(CommonRecord crm, Pager pager);
    public JfiDepositBalance getJfiDepositBalanceByUserCodeAndDepositType(final String userCode, final String depositType);
    public JfiDepositBalance getJfiDepositBalanceForUpdate(Long fdbId);
}

