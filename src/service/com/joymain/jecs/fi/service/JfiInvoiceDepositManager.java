
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiInvoiceDeposit;
import com.joymain.jecs.fi.dao.JfiInvoiceDepositDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiInvoiceDepositManager extends Manager {
    /**
     * Retrieves all of the jfiInvoiceDeposits
     */
    public List getJfiInvoiceDeposits(JfiInvoiceDeposit jfiInvoiceDeposit);

    /**
     * Gets jfiInvoiceDeposit's information based on id.
     * @param id the jfiInvoiceDeposit's id
     * @return jfiInvoiceDeposit populated jfiInvoiceDeposit object
     */
    public JfiInvoiceDeposit getJfiInvoiceDeposit(final String id);

    /**
     * Saves a jfiInvoiceDeposit's information
     * @param jfiInvoiceDeposit the object to be saved
     */
    public void saveJfiInvoiceDeposit(JfiInvoiceDeposit jfiInvoiceDeposit);

    /**
     * Removes a jfiInvoiceDeposit from the database by id
     * @param id the jfiInvoiceDeposit's id
     */
    public void removeJfiInvoiceDeposit(final String id);
    //added for getJfiInvoiceDepositsByCrm
    public List getJfiInvoiceDepositsByCrm(CommonRecord crm, Pager pager);
}

