
package com.joymain.jecs.fi.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiProductPointJournal;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiProductPointJournalDao extends Dao {

    /**
     * Retrieves all of the fiProductPointJournals
     */
    public List getFiProductPointJournals(FiProductPointJournal fiProductPointJournal);

    /**
     * Gets fiProductPointJournal's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param journalId the fiProductPointJournal's journalId
     * @return fiProductPointJournal populated fiProductPointJournal object
     */
    public FiProductPointJournal getFiProductPointJournal(final Long journalId);

    /**
     * Saves a fiProductPointJournal's information
     * @param fiProductPointJournal the object to be saved
     */    
    public void saveFiProductPointJournal(FiProductPointJournal fiProductPointJournal);

    /**
     * Removes a fiProductPointJournal from the database by journalId
     * @param journalId the fiProductPointJournal's journalId
     */
    public void removeFiProductPointJournal(final Long journalId);
    //added for getFiProductPointJournalsByCrm
    public List getFiProductPointJournalsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * Add By WuCF 20140320
	 * 基金余额查询总计
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
    
	/**
	 * 审批存折临时条目
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public Map callProcCheckFiProductPointTemp(final Long tempId, final SysUser sysUser);

	/**
	 * 获取收支统计金额 incTotal:收入 expTotal:支出
	 * @param crm
	 * @return
	 */
	public Map getIncExpStatMap(CommonRecord crm);

	/**
	 * 获取公司截止至某一日期时(包含此日期)的用户电子存折余额总计
	 * @param companyCode
	 * @param queryDate yyyy-MM-dd
	 * @return
	 */
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate, final String ProductPointType);
	
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	public FiProductPointJournal getLastFiProductPointJournalByUnique(final String uniqueCode,final String dealType);

	/**
	 * 获取某用户的存折流水条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	public long getCountByDate(final String companyCode, final String userCode);

	/**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	public FiProductPointJournal getLastFiProductPointJournal(final String userCode,final String dealType);

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
	public List getFiProductPointJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate, final String productPointType);

	/**
	 * 查询退单的退基金情况
	 * @author gw 2015-07-10
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getBeforeJprRefundJj(String roNo);
	
	/**
	 * 查看这张退单之前退了多少基金
	 * @author gw 2015-07-13
	 * @param roNo
	 * @return bigDecimal
	 */
	public BigDecimal getJprRefundJjBack(String roNo);

	
}

