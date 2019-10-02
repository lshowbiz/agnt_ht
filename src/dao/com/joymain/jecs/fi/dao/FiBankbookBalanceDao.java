
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiBankbookBalanceDao extends Dao {

    /**
     * Retrieves all of the fiBankbookBalances
     */
    public List getFiBankbookBalances(FiBankbookBalance fiBankbookBalance);

    /**
     * Gets fiBankbookBalance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param fbbId the fiBankbookBalance's fbbId
     * @return fiBankbookBalance populated fiBankbookBalance object
     */
    public FiBankbookBalance getFiBankbookBalance(final Long fbbId);
    public FiBankbookBalance getFiBankbookBalance(final String userCode,final String backbookType);

    /**
     * Saves a fiBankbookBalance's information
     * @param fiBankbookBalance the object to be saved
     */    
    public void saveFiBankbookBalance(FiBankbookBalance fiBankbookBalance);
    
    /**
     * @see com.joymain.jecs.fi.dao.FiBankbookBalanceDao#getFiBankbookBalanceForUpdate(Long fbbId)
     */
    public FiBankbookBalance getFiBankbookBalanceForUpdate(final Long fbbId);

    /**
     * Removes a fiBankbookBalance from the database by fbbId
     * @param fbbId the fiBankbookBalance's fbbId
     */
    public void removeFiBankbookBalance(final Long fbbId);
    //added for getFiBankbookBalancesByCrm
    public List getFiBankbookBalancesByCrm(CommonRecord crm, Pager pager);
    /**
     * 获取银行记录
     * @param UserCode
     * @param bankbookType
     * @return
     */
    public FiBankbookBalance getFiBankbookBalanceByUserCodeAndBankbookType(final String userCode, final String bankbookType);
    
    public List getFiProductPointBalancesByCrm(CommonRecord crm, Pager pager);
}

