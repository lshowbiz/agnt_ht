
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.fi.dao.FiFundbookBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiFundbookBalanceManager extends Manager {
    /**
     * Retrieves all of the fiFundbookBalances
     */
    public List getFiFundbookBalances(FiFundbookBalance fiFundbookBalance);

    /**
     * Gets fiFundbookBalance's information based on fbbId.
     * @param fbbId the fiFundbookBalance's fbbId
     * @return fiFundbookBalance populated fiFundbookBalance object
     */
    public FiFundbookBalance getFiFundbookBalance(final String fbbId);

    /**
     * Saves a fiFundbookBalance's information
     * @param fiFundbookBalance the object to be saved
     */
    public void saveFiFundbookBalance(FiFundbookBalance fiFundbookBalance);

    /**
     * Removes a fiFundbookBalance from the database by fbbId
     * @param fbbId the fiFundbookBalance's fbbId
     */
    public void removeFiFundbookBalance(final String fbbId);
    //added for getFiFundbookBalancesByCrm
    public List getFiFundbookBalancesByCrm(CommonRecord crm, Pager pager);
}

