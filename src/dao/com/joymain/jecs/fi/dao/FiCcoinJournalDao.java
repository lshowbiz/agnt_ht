
package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiCcoinJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiCcoinJournalDao extends Dao {

    /**
     * Retrieves all of the fiCcoinJournals
     */
    public List getFiCcoinJournals(FiCcoinJournal fiCcoinJournal);

    /**
     * Gets fiCcoinJournal's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param journalId the fiCcoinJournal's journalId
     * @return fiCcoinJournal populated fiCcoinJournal object
     */
    public FiCcoinJournal getFiCcoinJournal(final Long journalId);

    /**
     * Saves a fiCcoinJournal's information
     * @param fiCcoinJournal the object to be saved
     */    
    public void saveFiCcoinJournal(FiCcoinJournal fiCcoinJournal);

    /**
     * Removes a fiCcoinJournal from the database by journalId
     * @param journalId the fiCcoinJournal's journalId
     */
    public void removeFiCcoinJournal(final Long journalId);
    //added for getFiCcoinJournalsByCrm
    public List getFiCcoinJournalsByCrm(CommonRecord crm, Pager pager);
    /**
     * 获取验证ID对应的最后一条存折记录
     * @param uniqueCode
     * @return
     */
    public FiCcoinJournal getLastFiCcoinJournalByUnique(final String uniqueCode,final String dealType);

    /**
     * 获取用户对应的最后一条存折记录
     * @param userCode
     * @return
     */
    public FiCcoinJournal getLastFiCcoinJournal(final String userCode);
    /**
     * 获取某用户的存折流水条数
     * @param companyCode
     * @param agentNo
     * @return
     */
    public long getCountByDate(final String userCode);
}

