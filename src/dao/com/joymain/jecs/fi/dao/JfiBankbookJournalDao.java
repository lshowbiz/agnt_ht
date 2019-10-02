
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiBankbookJournal;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiBankbookJournalDao extends Dao {

    /**
     * Retrieves all of the jfiBankbookJournals
     */
    public List getJfiBankbookJournals(JfiBankbookJournal jfiBankbookJournal);

    /**
     * Gets jfiBankbookJournal's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param journalId the jfiBankbookJournal's journalId
     * @return jfiBankbookJournal populated jfiBankbookJournal object
     */
    public JfiBankbookJournal getJfiBankbookJournal(final Long journalId);

    /**
     * Saves a jfiBankbookJournal's information
     * @param jfiBankbookJournal the object to be saved
     */    
    public void saveJfiBankbookJournal(JfiBankbookJournal jfiBankbookJournal);

    /**
     * Removes a jfiBankbookJournal from the database by journalId
     * @param journalId the jfiBankbookJournal's journalId
     */
    public void removeJfiBankbookJournal(final Long journalId);
    //added for getJfiBankbookJournalsByCrm
    public List getJfiBankbookJournalsByCrm(CommonRecord crm, Pager pager);
    
	/**
	 * 审批存折临时条目
	 * @param tempId
	 * @param sysUser
	 * @return
	 */
	public Map callProcCheckJfiBankbookTemp(final Long tempId, final SysUser sysUser);

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
	public BigDecimal getTotalBalanceByDate(final String companyCode, final String queryDate);
	
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode
	 * @return
	 */
	public JfiBankbookJournal getLastJfiBankbookJournalByUnique(final String uniqueCode,final String dealType);

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
	public JfiBankbookJournal getLastJfiBankbookJournal(final String userCode);

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
	public List getJfiBankbookJournalsStat(final String companyCode, final String dealType, final Integer[] moneyTypes,
	        boolean inverseMoneyType, final String startDate, final String endDate);
	/**
	 * @param userCode
	 * @param type
	 * @param nodes
	 * @return
	 */
	public JfiBankbookJournal getBankbookJournal(String userCode, String type,String nodes);
}

