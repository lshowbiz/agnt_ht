
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.BillAccount;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface BillAccountDao extends Dao {

    /**
     * Retrieves all of the billAccounts
     */
    public List getBillAccounts(BillAccount billAccount);

    /**
     * Gets billAccount's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param baId the billAccount's baId
     * @return billAccount populated billAccount object
     */
    public BillAccount getBillAccount(final Long baId);

    /**
     * Saves a billAccount's information
     * @param billAccount the object to be saved
     */    
    public void saveBillAccount(BillAccount billAccount);

    /**
     * Removes a billAccount from the database by baId
     * @param baId the billAccount's baId
     */
    public void removeBillAccount(final Long baId);
    //added for getBillAccountsByCrm
    public List getBillAccountsByCrm(CommonRecord crm, Pager pager);
    
    public List getBillAccountsByBaCode(String baCode);
    
    public BigDecimal getBilllAccountsPersent(Long baId);
}

