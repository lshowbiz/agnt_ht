
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FiSecurityDeposit;
import com.joymain.jecs.fi.dao.FiSecurityDepositDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface FiSecurityDepositManager extends Manager {
    /**
     * Retrieves all of the fiSecurityDeposits
     */
    public List getFiSecurityDeposits(FiSecurityDeposit fiSecurityDeposit);

    /**
     * Gets fiSecurityDeposit's information based on fsdId.
     * @param fsdId the fiSecurityDeposit's fsdId
     * @return fiSecurityDeposit populated fiSecurityDeposit object
     */
    public FiSecurityDeposit getFiSecurityDeposit(final String fsdId);

    /**
     * Saves a fiSecurityDeposit's information
     * @param fiSecurityDeposit the object to be saved
     */
    public void saveFiSecurityDeposit(FiSecurityDeposit fiSecurityDeposit);

    /**
     * Removes a fiSecurityDeposit from the database by fsdId
     * @param fsdId the fiSecurityDeposit's fsdId
     */
    public void removeFiSecurityDeposit(final String fsdId);
    //added for getFiSecurityDepositsByCrm
    public List getFiSecurityDepositsByCrm(CommonRecord crm, Pager pager);
    
    public void makeUpFiSecurityDeposit(String fsdId, SysUser operationSysUser, String limit) throws AppException;
    
    public void makeUpAllFiSecurityDeposit(SysUser operationSysUser, String limit) throws AppException;
    
    public Integer getCountOfSecurityDepositByUserCode(String userCode);
    
    public void downFiSecurityDeposit(String fsdId, SysUser operationSysUser, String amount, String remark) throws AppException;
}

