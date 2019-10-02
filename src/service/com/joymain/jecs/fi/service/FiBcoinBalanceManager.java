
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.dao.FiBcoinBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiBcoinBalanceManager extends Manager {
    /**
     * Retrieves all of the fiBcoinBalances
     */
    public List getFiBcoinBalances(FiBcoinBalance fiBcoinBalance);

    /**
     * Gets fiBcoinBalance's information based on userCode.
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
}

