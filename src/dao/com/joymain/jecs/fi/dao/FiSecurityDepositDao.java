
package com.joymain.jecs.fi.dao;

import java.util.List;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiSecurityDeposit;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiSecurityDepositDao extends Dao {

    /**
     * Retrieves all of the fiSecurityDeposits
     */
    public List getFiSecurityDeposits(FiSecurityDeposit fiSecurityDeposit);

    /**
     * Gets fiSecurityDeposit's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param fsdId the fiSecurityDeposit's fsdId
     * @return fiSecurityDeposit populated fiSecurityDeposit object
     */
    public FiSecurityDeposit getFiSecurityDeposit(final Long fsdId);

    /**
     * Saves a fiSecurityDeposit's information
     * @param fiSecurityDeposit the object to be saved
     */    
    public void saveFiSecurityDeposit(FiSecurityDeposit fiSecurityDeposit);

    /**
     * Removes a fiSecurityDeposit from the database by fsdId
     * @param fsdId the fiSecurityDeposit's fsdId
     */
    public void removeFiSecurityDeposit(final Long fsdId);
    //added for getFiSecurityDepositsByCrm
    public List getFiSecurityDepositsByCrm(CommonRecord crm, Pager pager);
    
    //查询保证金余额不足的所有帐户
    public List getFiSecurityDepositsByLimit(String limit);
    
    public Integer getCountOfSecurityDepositByUserCode(String userCode);
}

