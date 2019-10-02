
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FiFundbookJournal;
import com.joymain.jecs.fi.dao.FiFundbookJournalDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface FiFundbookJournalManager extends Manager {
    /**
     * Retrieves all of the fiFundbookJournals
     */
    public List getFiFundbookJournals(FiFundbookJournal fiFundbookJournal);

    /**
     * Gets fiFundbookJournal's information based on journalId.
     * @param journalId the fiFundbookJournal's journalId
     * @return fiFundbookJournal populated fiFundbookJournal object
     */
    public FiFundbookJournal getFiFundbookJournal(final String journalId);

    /**
     * Saves a fiFundbookJournal's information
     * @param fiFundbookJournal the object to be saved
     */
    public void saveFiFundbookJournal(FiFundbookJournal fiFundbookJournal);

    /**
     * Removes a fiFundbookJournal from the database by journalId
     * @param journalId the fiFundbookJournal's journalId
     */
    public void removeFiFundbookJournal(final String journalId);
    //added for getFiFundbookJournalsByCrm
    public List getFiFundbookJournalsByCrm(CommonRecord crm, Pager pager);
    
	/**
	 * 存入资金至产业基金帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 */
	public void saveFiPayDataVerify(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String bankbookType, final String dataType);

	/**
	 * 从产业基金帐户扣取金额
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
	public void saveFiBankbookDeduct(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String bankbookType, final String dataType) throws AppException;

	
	/**
     * Add By WuCF 20140320
	 * 基金余额查询总计
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
	
	/**
	 * 临时条目审批通过，将临时条目插入基金流水表
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public void saveFiFundbookTempCheck(final String tempId, final SysUser sysUser) throws AppException;
	
	/**
	 * 获取某段日期内,公司在指定资金类别所对应的进出金额
	 * @param companyCode
	 * @param dealType
	 * @param moneyTypes
	 * @param inverseMoneyType 如果为true,则查询不包含在moneyTypes里的记录,如果为false,则为包含在moneyTypes的记录
	 * @param startDate yyyy-MM-dd
	 * @param endDate yyyy-MM-dd
	 * @return
	 */
	public List getFiFundbookJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate, final String bankbookType);
	
	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户电子存折余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate, final String bankbookType);
	
	/**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm);
}

