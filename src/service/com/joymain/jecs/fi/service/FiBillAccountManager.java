
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

@SuppressWarnings("rawtypes")
public interface FiBillAccountManager extends Manager {
    /**
     * Retrieves all of the fiBillAccounts
     */
    
	public List getFiBillAccounts(FiBillAccount fiBillAccount);

    /**
     * Gets fiBillAccount's information based on accountId.
     * @param accountId the fiBillAccount's accountId
     * @return fiBillAccount populated fiBillAccount object
     */
    public FiBillAccount getFiBillAccount(final String accountId);

    /**
     * Saves a fiBillAccount's information
     * @param fiBillAccount the object to be saved
     */
    public void saveFiBillAccount(FiBillAccount fiBillAccount);

    /**
     * Removes a fiBillAccount from the database by accountId
     * @param accountId the fiBillAccount's accountId
     */
    public void removeFiBillAccount(final String accountId);
    //added for getFiBillAccountsByCrm
    public List getFiBillAccountsByCrm(CommonRecord crm, Pager pager);
    public List getFiBillAccountsByCrmSql(CommonRecord crm, Pager pager);
    
    /**
     * 验证同省份下是否有其他默认商户号
     * @param billAccountCode
     * @param province
     * @return
     */
    public List getFiBillAccountsByIsDefault(String billAccountCode);
    
    /**
	 * 验证经销商编号是否重复
	 * @param billAccountCode
	 * @param userCode
	 * @return
	 */
	public List getFiBillAccountsByUserCode(String billAccountCode,String userCode);
	
	/**
	 * 验证商户号是否重复
	 * @param billAccountCode
	 * @return
	 */
	public List getFiBillAccountsByBillAccountCode(String billAccountCode);
	
	/**
	 * 根据会议编号获取商户号
	 * @param userCode 会员编号
	 * @return 快钱商户对象
	 */
	public FiBillAccount getFiBillAccountByUserCode(String userCode);
	
	/**
	 * 批量保存
	 */
	public void saveFiBillAccounts(List<FiBillAccount> fiBillAccounts);
}

