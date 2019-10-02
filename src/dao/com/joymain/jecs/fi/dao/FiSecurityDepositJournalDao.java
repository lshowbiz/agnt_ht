
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiSecurityDepositJournal;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiSecurityDepositJournalDao extends Dao {

    /**
     * Retrieves all of the fiSecurityDepositJournals
     */
    public List getFiSecurityDepositJournals(FiSecurityDepositJournal fiSecurityDepositJournal);

    /**
     * Gets fiSecurityDepositJournal's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param journalId the fiSecurityDepositJournal's journalId
     * @return fiSecurityDepositJournal populated fiSecurityDepositJournal object
     */
    public FiSecurityDepositJournal getFiSecurityDepositJournal(final Long journalId);

    /**
     * Saves a fiSecurityDepositJournal's information
     * @param fiSecurityDepositJournal the object to be saved
     */    
    public void saveFiSecurityDepositJournal(FiSecurityDepositJournal fiSecurityDepositJournal);

    /**
     * Removes a fiSecurityDepositJournal from the database by journalId
     * @param journalId the fiSecurityDepositJournal's journalId
     */
    public void removeFiSecurityDepositJournal(final Long journalId);
    //added for getFiSecurityDepositJournalsByCrm
    public List getFiSecurityDepositJournalsByCrm(CommonRecord crm, Pager pager);
}

