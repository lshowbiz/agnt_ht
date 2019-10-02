
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiSecurityDepositJournal;
import com.joymain.jecs.fi.dao.FiSecurityDepositJournalDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiSecurityDepositJournalManager extends Manager {
    /**
     * Retrieves all of the fiSecurityDepositJournals
     */
    public List getFiSecurityDepositJournals(FiSecurityDepositJournal fiSecurityDepositJournal);

    /**
     * Gets fiSecurityDepositJournal's information based on journalId.
     * @param journalId the fiSecurityDepositJournal's journalId
     * @return fiSecurityDepositJournal populated fiSecurityDepositJournal object
     */
    public FiSecurityDepositJournal getFiSecurityDepositJournal(final String journalId);

    /**
     * Saves a fiSecurityDepositJournal's information
     * @param fiSecurityDepositJournal the object to be saved
     */
    public void saveFiSecurityDepositJournal(FiSecurityDepositJournal fiSecurityDepositJournal);

    /**
     * Removes a fiSecurityDepositJournal from the database by journalId
     * @param journalId the fiSecurityDepositJournal's journalId
     */
    public void removeFiSecurityDepositJournal(final String journalId);
    //added for getFiSecurityDepositJournalsByCrm
    public List getFiSecurityDepositJournalsByCrm(CommonRecord crm, Pager pager);
}

