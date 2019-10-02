
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiBillAccountDao extends Dao {

    /**
     * Retrieves all of the fiBillAccounts
     */
    public List getFiBillAccounts(FiBillAccount fiBillAccount);

    /**
     * Gets fiBillAccount's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param accountId the fiBillAccount's accountId
     * @return fiBillAccount populated fiBillAccount object
     */
    public FiBillAccount getFiBillAccount(final Long accountId);

    /**
     * Saves a fiBillAccount's information
     * @param fiBillAccount the object to be saved
     */    
    public void saveFiBillAccount(FiBillAccount fiBillAccount);

    /**
     * Removes a fiBillAccount from the database by accountId
     * @param accountId the fiBillAccount's accountId
     */
    public void removeFiBillAccount(final Long accountId);
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
	 * 根据省份获取快钱商户号
	 * @param province
	 * @return
	 */
	public FiBillAccount getFiBillAccountsByProvince(String province);
	
	/**
	 * 查询省份下面默认商户号
	 * @param province
	 * @return
	 */
	public FiBillAccount getDefaultFiBillAccountByProvince(String province);
	
	/**
	 * 随即查询一个默认商户号
	 * @param province
	 * @return
	 */
	public FiBillAccount getOneDefaultFiBillAccount();
	
	/**
	 * 根据商户号获取商户对象
	 * @param billAccountCode
	 * @return
	 */
	public FiBillAccount getFiBillAccountByBillAccountCode(String billAccountCode);
	
	/**
	 * 根据经销商获取商户号
	 * @param userCode
	 * @return
	 */
	public FiBillAccount getFiBillAccountByUserCode(String userCode, String accountType);
}

