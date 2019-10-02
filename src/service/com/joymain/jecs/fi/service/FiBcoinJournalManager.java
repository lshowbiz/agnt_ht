
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.fi.model.FiBcoinJournal;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface FiBcoinJournalManager extends Manager {
    /**
     * Retrieves all of the fiBcoinJournals
     */
    public List getFiBcoinJournals(FiBcoinJournal fiBcoinJournal);

    /**
     * Gets fiBcoinJournal's information based on journalId.
     * @param journalId the fiBcoinJournal's journalId
     * @return fiBcoinJournal populated fiBcoinJournal object
     */
    public FiBcoinJournal getFiBcoinJournal(final String journalId);

    /**
     * Saves a fiBcoinJournal's information
     * @param fiBcoinJournal the object to be saved
     */
    public void saveFiBcoinJournal(FiBcoinJournal fiBcoinJournal);

    /**
     * Removes a fiBcoinJournal from the database by journalId
     * @param journalId the fiBcoinJournal's journalId
     */
    public void removeFiBcoinJournal(final String journalId);
    //added for getFiBcoinJournalsByCrm
    public List getFiBcoinJournalsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * Add By WuCF 20140320
	 * 欢乐积分查询总计
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
    
    /**
	 * 存入资金至用户帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 */
	public void saveFiPayDataVerify(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final Long[] appId, final Date[] moneyDate, final String dataType);
	
	/**
	 * 从用户帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @throws AppException 存款不够的错误
	 */
	public void saveJfiBankbookDeduct(final String companyCode, final SysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final Long[] appId, final String dataType);
}

