
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiLovecoinBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiLovecoinBalanceManager extends Manager {
    /**
     * Retrieves all of the fiLovecoinBalances
     */
    public List getFiLovecoinBalances(FiLovecoinBalance fiLovecoinBalance);

    /**
     * Gets fiLovecoinBalance's information based on userCode.
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
     * Removes a fiLovecoinBalance from the database by userCode
     * @param userCode the fiLovecoinBalance's userCode
     */
    public void removeFiLovecoinBalance(final String userCode);
    //added for getFiLovecoinBalancesByCrm
    public List getFiLovecoinBalancesByCrm(CommonRecord crm, Pager pager);
}

