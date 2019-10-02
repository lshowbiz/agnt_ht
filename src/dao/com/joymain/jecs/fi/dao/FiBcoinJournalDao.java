
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiBcoinJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiBcoinJournalDao extends Dao {

    /**
     * Retrieves all of the fiBcoinJournals
     */
    public List getFiBcoinJournals(FiBcoinJournal fiBcoinJournal);

    /**
     * Gets fiBcoinJournal's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param journalId the fiBcoinJournal's journalId
     * @return fiBcoinJournal populated fiBcoinJournal object
     */
    public FiBcoinJournal getFiBcoinJournal(final Long journalId);

    /**
     * Saves a fiBcoinJournal's information
     * @param fiBcoinJournal the object to be saved
     */    
    public void saveFiBcoinJournal(FiBcoinJournal fiBcoinJournal);

    /**
     * Removes a fiBcoinJournal from the database by journalId
     * @param journalId the fiBcoinJournal's journalId
     */
    public void removeFiBcoinJournal(final Long journalId);
    //added for getFiBcoinJournalsByCrm
    public List getFiBcoinJournalsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * Add By WuCF 20140320
	 * 基金余额查询总计
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
    
    /**
     * 获取验证ID对应的最后一条存折记录
     * @param uniqueCode
     * @return
     */
    public FiBcoinJournal getLastFiBcoinJournalByUnique(final String uniqueCode,final String dealType);
    /**
     * 获取用户对应的最后一条存折记录
     * @param userCode
     * @return
     */
    public FiBcoinJournal getLastFiBcoinJournal(final String userCode);
    /**
     * 获取某用户的存折流水条数
     * @param companyCode
     * @param agentNo
     * @return
     */
    public long getCountByDate(final String userCode);
}

