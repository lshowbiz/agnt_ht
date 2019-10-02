
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiDepositJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiDepositJournalDao extends Dao {

    /**
     * Retrieves all of the jfiDepositJournals
     */
    public List getJfiDepositJournals(JfiDepositJournal jfiDepositJournal);

    /**
     * Gets jfiDepositJournal's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param journalId the jfiDepositJournal's journalId
     * @return jfiDepositJournal populated jfiDepositJournal object
     */
    public JfiDepositJournal getJfiDepositJournal(final Long journalId);

    /**
     * Saves a jfiDepositJournal's information
     * @param jfiDepositJournal the object to be saved
     */    
    public void saveJfiDepositJournal(JfiDepositJournal jfiDepositJournal);

    /**
     * Removes a jfiDepositJournal from the database by journalId
     * @param journalId the jfiDepositJournal's journalId
     */
    public void removeJfiDepositJournal(final Long journalId);
    //added for getJfiDepositJournalsByCrm
    public List getJfiDepositJournalsByCrm(CommonRecord crm, Pager pager);
	/**
	 * 获取验证ID对应的最后一条存折记录
	 * @param uniqueCode 唯一码，用于防止重复
	 * @param dealType 交易类别，A：存入；D：取出
	 * @return
	 */
	public JfiDepositJournal getLastJfiDepositJournalByUnique(final String uniqueCode,final String dealType) ;
	/**
	 * 获取用户对应的最后一条产业基金流水记录
	 * @param userCode
	 * @return
	 */
	public JfiDepositJournal getLastJfiDepositJournal(final String userCode,final String dealType);
	public Map getSumAmountByCrm(CommonRecord crm) ;
}

