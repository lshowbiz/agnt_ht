
package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiBcoinBalanceDao extends Dao {

    /**
     * Retrieves all of the fiBcoinBalances
     */
    public List getFiBcoinBalances(FiBcoinBalance fiBcoinBalance);

    /**
     * Gets fiBcoinBalance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the fiBcoinBalance's userCode
     * @return fiBcoinBalance populated fiBcoinBalance object
     */
    public FiBcoinBalance getFiBcoinBalance(final String userCode);

    /**
     * Saves a fiBcoinBalance's information
     * @param fiBcoinBalance the object to be saved
     */    
    public void saveFiBcoinBalance(FiBcoinBalance fiBcoinBalance);

    /**
     * Removes a fiBcoinBalance from the database by userCode
     * @param userCode the fiBcoinBalance's userCode
     */
    public void removeFiBcoinBalance(final String userCode);
    //added for getFiBcoinBalancesByCrm
    public List getFiBcoinBalancesByCrm(CommonRecord crm, Pager pager);
    public FiBcoinBalance getFiBcoinBalanceForUpdate(final String userCode);
}

