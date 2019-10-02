
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiTransferAccount;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiTransferAccountDao extends Dao {

    /**
     * Retrieves all of the fiTransferAccounts
     */
    public List getFiTransferAccounts(FiTransferAccount fiTransferAccount);

    /**
     * Gets fiTransferAccount's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param taId the fiTransferAccount's taId
     * @return fiTransferAccount populated fiTransferAccount object
     */
    public FiTransferAccount getFiTransferAccount(final Long taId);

    /**
     * Saves a fiTransferAccount's information
     * @param fiTransferAccount the object to be saved
     */    
    public void saveFiTransferAccount(FiTransferAccount fiTransferAccount);

    /**
     * Removes a fiTransferAccount from the database by taId
     * @param taId the fiTransferAccount's taId
     */
    public void removeFiTransferAccount(final Long taId);
    //added for getFiTransferAccountsByCrm
    public List getFiTransferAccountsByCrm(CommonRecord crm, Pager pager);
    
    public Integer getTransferAccountStatus(final Long taId);
    
    public BigDecimal getSumTransferValueByTransferCode(final String transferCode);
    
    /**
     * 查询待审的转账单
     * @return
     */
    public List<FiTransferAccount> getToCheckTransferAccountList();
}

