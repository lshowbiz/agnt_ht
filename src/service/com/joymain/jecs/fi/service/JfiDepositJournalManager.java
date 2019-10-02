
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiDepositJournal;
import com.joymain.jecs.fi.dao.JfiDepositJournalDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiDepositJournalManager extends Manager {
    /**
     * Retrieves all of the jfiDepositJournals
     */
    public List getJfiDepositJournals(JfiDepositJournal jfiDepositJournal);

    /**
     * Gets jfiDepositJournal's information based on journalId.
     * @param journalId the jfiDepositJournal's journalId
     * @return jfiDepositJournal populated jfiDepositJournal object
     */
    public JfiDepositJournal getJfiDepositJournal(final String journalId);

    /**
     * Saves a jfiDepositJournal's information
     * @param jfiDepositJournal the object to be saved
     */
    public void saveJfiDepositJournal(JfiDepositJournal jfiDepositJournal);

    /**
     * Removes a jfiDepositJournal from the database by journalId
     * @param journalId the jfiDepositJournal's journalId
     */
    public void removeJfiDepositJournal(final String journalId);
    //added for getJfiDepositJournalsByCrm
    public List getJfiDepositJournalsByCrm(CommonRecord crm, Pager pager);
  	public void saveFiPayDataVerify(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
  	        String operaterCode, final String uniqueCode, final String[] notes, final String depositType);
  	public void saveFiBankbookDeduct(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
  	        String operaterCode, final String uniqueCode,  final String[] notes, final String depositType);
	public Map getSumAmountByCrm(CommonRecord crm) ;
}

