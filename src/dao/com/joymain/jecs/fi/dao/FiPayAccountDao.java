
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiPayAccount;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiPayAccountDao extends Dao {

    /**
     * Retrieves all of the fiPayAccounts
     */
    public List getFiPayAccounts(FiPayAccount fiPayAccount);

    /**
     * Gets fiPayAccount's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param accountId the fiPayAccount's accountId
     * @return fiPayAccount populated fiPayAccount object
     */
    public FiPayAccount getFiPayAccount(final Long accountId);

    /**
     * Saves a fiPayAccount's information
     * @param fiPayAccount the object to be saved
     */    
    public void saveFiPayAccount(FiPayAccount fiPayAccount);

    /**
     * Removes a fiPayAccount from the database by accountId
     * @param accountId the fiPayAccount's accountId
     */
    public void removeFiPayAccount(final Long accountId);
    //added for getFiPayAccountsByCrm
    public List getFiPayAccountsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 查询默认商户号
     * @param accountId
     * @return
     */
    public List getDefaultAccounts(Long accountId);
}

