
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiFundbookJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiFundbookJournalDao extends Dao {

    /**
     * Retrieves all of the fiFundbookJournals
     */
    public List getFiFundbookJournals(FiFundbookJournal fiFundbookJournal);

    /**
     * Gets fiFundbookJournal's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param journalId the fiFundbookJournal's journalId
     * @return fiFundbookJournal populated fiFundbookJournal object
     */
    public FiFundbookJournal getFiFundbookJournal(final Long journalId);

    /**
     * Saves a fiFundbookJournal's information
     * @param fiFundbookJournal the object to be saved
     */    
    public void saveFiFundbookJournal(FiFundbookJournal fiFundbookJournal);

    /**
     * Removes a fiFundbookJournal from the database by journalId
     * @param journalId the fiFundbookJournal's journalId
     */
    public void removeFiFundbookJournal(final Long journalId);
    
    //根据条件进行查询产业基金流水列表
    public List getFiFundbookJournalsByCrm(CommonRecord crm, Pager pager);
    
    /**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm);
	
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode 唯一码，用于防止重复
	 * @param dealType 交易类别，A：存入；D：取出
	 * @return
	 */
	public FiFundbookJournal getLastFiFundbookJournalByUnique(final String uniqueCode,final String dealType);
	
	/**
	 * 获取某用户的产业基金流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode);
	
	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户产业基金余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate, final String bankbookType);
	
	/**
	 * 获取用户对应的最后一条产业基金流水记录
	 * @param userCode
	 * @return
	 */
	public FiFundbookJournal getLastFiFundbookJournal(final String userCode,final String dealType);
	
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
     * Add By WuCF 20140320 查询基金查询统计数据
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
}

