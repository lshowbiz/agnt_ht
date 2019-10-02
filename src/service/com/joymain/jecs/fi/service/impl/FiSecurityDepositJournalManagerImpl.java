
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiSecurityDepositJournal;
import com.joymain.jecs.fi.dao.FiSecurityDepositJournalDao;
import com.joymain.jecs.fi.service.FiSecurityDepositJournalManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiSecurityDepositJournalManagerImpl extends BaseManager implements FiSecurityDepositJournalManager {
    private FiSecurityDepositJournalDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiSecurityDepositJournalDao(FiSecurityDepositJournalDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiSecurityDepositJournalManager#getFiSecurityDepositJournals(com.joymain.jecs.fi.model.FiSecurityDepositJournal)
     */
    public List getFiSecurityDepositJournals(final FiSecurityDepositJournal fiSecurityDepositJournal) {
        return dao.getFiSecurityDepositJournals(fiSecurityDepositJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiSecurityDepositJournalManager#getFiSecurityDepositJournal(String journalId)
     */
    public FiSecurityDepositJournal getFiSecurityDepositJournal(final String journalId) {
        return dao.getFiSecurityDepositJournal(new Long(journalId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiSecurityDepositJournalManager#saveFiSecurityDepositJournal(FiSecurityDepositJournal fiSecurityDepositJournal)
     */
    public void saveFiSecurityDepositJournal(FiSecurityDepositJournal fiSecurityDepositJournal) {
        dao.saveFiSecurityDepositJournal(fiSecurityDepositJournal);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiSecurityDepositJournalManager#removeFiSecurityDepositJournal(String journalId)
     */
    public void removeFiSecurityDepositJournal(final String journalId) {
        dao.removeFiSecurityDepositJournal(new Long(journalId));
    }
    //added for getFiSecurityDepositJournalsByCrm
    public List getFiSecurityDepositJournalsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiSecurityDepositJournalsByCrm(crm,pager);
    }
}
