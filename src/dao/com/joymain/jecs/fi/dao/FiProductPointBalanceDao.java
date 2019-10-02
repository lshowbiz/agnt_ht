
package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiProductPointBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiProductPointBalanceDao extends Dao {

    /**
     * Retrieves all of the fiProductPointBalances
     */
    public List getFiProductPointBalances(FiProductPointBalance fiProductPointBalance);

    /**
     * Gets fiProductPointBalance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param fbbId the fiProductPointBalance's fbbId
     * @return fiProductPointBalance populated fiProductPointBalance object
     */
    public FiProductPointBalance getFiProductPointBalance(final Long fbbId);
    public FiProductPointBalance getFiProductPointBalance(final String userCode,final String backbookType);

    /**
     * Saves a fiProductPointBalance's information
     * @param fiProductPointBalance the object to be saved
     */    
    public void saveFiProductPointBalance(FiProductPointBalance fiProductPointBalance);
    
    /**
     * @see com.joymain.jecs.fi.dao.FiProductPointBalanceDao#getFiProductPointBalanceForUpdate(Long fbbId)
     */
    public FiProductPointBalance getFiProductPointBalanceForUpdate(final Long fbbId);

    /**
     * Removes a fiProductPointBalance from the database by fbbId
     * @param fbbId the fiProductPointBalance's fbbId
     */
    public void removeFiProductPointBalance(final Long fbbId);
    //added for getFiProductPointBalancesByCrm
    public List getFiProductPointBalancesByCrm(CommonRecord crm, Pager pager);
    /**
     * 获取银行记录
     * @param UserCode
     * @param ProductPointType
     * @return
     */
    public FiProductPointBalance getFiProductPointBalanceByUserCodeAndProductPointType(final String userCode, final String ProductPointType);
}

