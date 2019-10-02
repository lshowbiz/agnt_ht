
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FiTransferAccount;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiTransferAccountManager extends Manager {
    /**
     * Retrieves all of the fiTransferAccounts
     */
    public List getFiTransferAccounts(FiTransferAccount fiTransferAccount);

    /**
     * Gets fiTransferAccount's information based on taId.
     * @param taId the fiTransferAccount's taId
     * @return fiTransferAccount populated fiTransferAccount object
     */
    public FiTransferAccount getFiTransferAccount(final String taId);

    /**
     * Saves a fiTransferAccount's information
     * @param fiTransferAccount the object to be saved
     */
    public void saveFiTransferAccount(FiTransferAccount fiTransferAccount);

    /**
     * Removes a fiTransferAccount from the database by taId
     * @param taId the fiTransferAccount's taId
     */
    public void removeFiTransferAccount(final String taId);
    //added for getFiTransferAccountsByCrm
    public List getFiTransferAccountsByCrm(CommonRecord crm, Pager pager);
    
    //创建转账单
    public void addTransferAccounts(FiTransferAccount fiTransferAccount, SysUser transferUser);
    
    //核准
    public void checkTransferAccounts(List<FiTransferAccount> fiTransferAccountList);
    
    //撤销
    public void revokeTransferAccounts(List<FiTransferAccount> fiTransferAccountList);
    
    //获取单日转账总额
    public BigDecimal getSumTransferValueByTransferCode(final String transferCode);
    
    /**
     * 自动审单
     */
    public void autoCheckTransferAccounts();
    
    /**
     * 定时任务自动审单(审核电子存折、产业化基金转账单)
     */
    public void checkTransferTask();
}

