
package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiLovecoinJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiLovecoinJournalDao extends Dao {

    /**
     * Retrieves all of the fiLovecoinJournals
     */
    public List getFiLovecoinJournals(FiLovecoinJournal fiLovecoinJournal);

    /**
     * Gets fiLovecoinJournal's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param journalId the fiLovecoinJournal's journalId
     * @return fiLovecoinJournal populated fiLovecoinJournal object
     */
    public FiLovecoinJournal getFiLovecoinJournal(final Long journalId);

    /**
     * Saves a fiLovecoinJournal's information
     * @param fiLovecoinJournal the object to be saved
     */    
    public void saveFiLovecoinJournal(FiLovecoinJournal fiLovecoinJournal);

    /**
     * Removes a fiLovecoinJournal from the database by journalId
     * @param journalId the fiLovecoinJournal's journalId
     */
    public void removeFiLovecoinJournal(final Long journalId);
    //added for getFiLovecoinJournalsByCrm
    public List getFiLovecoinJournalsByCrm(CommonRecord crm, Pager pager);
    /**
     * 获取验证ID对应的最后一条存折记录
     * @pLovecoinJournalode
     * @return
     */
    public FiLovecoinJournal getLastFiLovecoinJournalByUnique(final String uniqueCode,final String dealType);
    /**
     * 获取用户对应的最后一条存折记录
     * @param userCode
     * @return
     */
    public FiLovecoinJournal getLastFiLovecoinJournal(final String userCode);
    /**
     * 获取某用户的存折流水条数
     * @param companyCode
     * @param agentNo
     * @return
     */
    public long getCountByDate(final String userCode);
}

