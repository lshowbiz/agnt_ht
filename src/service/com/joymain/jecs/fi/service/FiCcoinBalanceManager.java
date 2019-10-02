
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiCcoinBalance;
import com.joymain.jecs.fi.dao.FiCcoinBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiCcoinBalanceManager extends Manager {
    /**
     * Retrieves all of the fiCcoinBalances
     */
    public List getFiCcoinBalances(FiCcoinBalance fiCcoinBalance);

    /**
     * Gets fiCcoinBalance's information based on userCode.
     * @param userCode the fiCcoinBalance's userCode
     * @return fiCcoinBalance populated fiCcoinBalance object
     */
    public FiCcoinBalance getFiCcoinBalance(final String userCode);

    /**
     * Saves a fiCcoinBalance's information
     * @param fiCcoinBalance the object to be saved
     */
    public void saveFiCcoinBalance(FiCcoinBalance fiCcoinBalance);

    /**
     * Removes a fiCcoinBalance from the database by userCode
     * @param userCode the fiCcoinBalance's userCode
     */
    public void removeFiCcoinBalance(final String userCode);
    //added for getFiCcoinBalancesByCrm
    public List getFiCcoinBalancesByCrm(CommonRecord crm, Pager pager);
}

