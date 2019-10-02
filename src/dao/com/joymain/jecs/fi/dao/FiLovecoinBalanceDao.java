
package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.model.FiLovecoinBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiLovecoinBalanceDao extends Dao {

    /**
     * Retrieves all of the fiBcoinBalances
     */
    public List getFiLovecoinBalances(FiLovecoinBalance fiLovecoinBalance);

    /**
     * Gets fiLovecoinBalance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the fiLovecoinBalance's userCode
     * @return fiLovecoinBalance populated fiLovecoinBalance object
     */
    public FiLovecoinBalance getFiLovecoinBalance(final String userCode);

    /**
     * Saves a fiLovecoinBalance's information
     * @param fiLovecoinBalance the object to be saved
     */    
    public void saveFiLovecoinBalance(FiLovecoinBalance fiLovecoinBalance);

    /**
     * Removes a fiBcoinBalance from the database by userCode
     * @param userCode the fiLovecoinBalance's userCode
     */
    public void removeFiLovecoinBalance(final String userCode);
    //added for getFiLovecoinBalancesByCrm
    public List getFiLovecoinBalancesByCrm(CommonRecord crm, Pager pager);
    public FiLovecoinBalance getFiLovecoinBalanceForUpdate(final String userCode);
}

