
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.fi.model.FiCcoinJournal;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiCcoinJournalManager extends Manager {
    /**
     * Retrieves all of the fiCcoinJournals
     */
    public List getFiCcoinJournals(FiCcoinJournal fiCcoinJournal);

    /**
     * Gets fiCcoinJournal's information based on journalId.
     * @param journalId the fiCcoinJournal's journalId
     * @return fiCcoinJournal populated fiCcoinJournal object
     */
    public FiCcoinJournal getFiCcoinJournal(final String journalId);

    /**
     * Saves a fiCcoinJournal's information
     * @param fiCcoinJournal the object to be saved
     */
    public void saveFiCcoinJournal(FiCcoinJournal fiCcoinJournal);

    /**
     * Removes a fiCcoinJournal from the database by journalId
     * @param journalId the fiCcoinJournal's journalId
     */
    public void removeFiCcoinJournal(final String journalId);
    //added for getFiCcoinJournalsByCrm
    public List getFiCcoinJournalsByCrm(CommonRecord crm, Pager pager);
}

