
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joymain.jecs.fi.model.FiLovecoinJournal;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface FiLovecoinJournalManager extends Manager {
    /**
     * Retrieves all of the fiLovecoinJournals
     */
    public List getFiLovecoinJournals(FiLovecoinJournal fiLovecoinJournal);

    /**
     * Gets fiLovecoinJournal's information based on journalId.
     * @param journalId the fiLovecoinJournal's journalId
     * @return fiLovecoinJournal populated fiLovecoinJournal object
     */
    public FiLovecoinJournal getFiLovecoinJournal(final String journalId);

    /**
     * Saves a fiLovecoinJournal's information
     * @param fiLovecoinJournal the object to be saved
     */
    public void saveFiLovecoinJournal(FiLovecoinJournal fiLovecoinJournal);

    /**
     * Removes a fiLovecoinJournal from the database by journalId
     * @param journalId the fiLovecoinJournal's journalId
     */
    public void removeFiLovecoinJournal(final String journalId);
    //added for getFiLovecoinJournalsByCrm
    public List getFiLovecoinJournalsByCrm(CommonRecord crm, Pager pager);
    
    //爱心积分账户开户
    public void createLovecoin(String userCode);
    
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
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final Long[] appId, final Date[] moneyDate) throws AppException;
	
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
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final Long[] appId) throws AppException;
}

