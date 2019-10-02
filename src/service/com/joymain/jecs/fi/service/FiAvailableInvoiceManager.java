
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.fi.dao.FiAvailableInvoiceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiAvailableInvoiceManager extends Manager {
    /**
     * Retrieves all of the fiAvailableInvoices
     */
    public List getFiAvailableInvoices(FiAvailableInvoice fiAvailableInvoice);

    /**
     * Gets fiAvailableInvoice's information based on id.
     * @param id the fiAvailableInvoice's id
     * @return fiAvailableInvoice populated fiAvailableInvoice object
     */
    public FiAvailableInvoice getFiAvailableInvoice(final String id);

    /**
     * Saves a fiAvailableInvoice's information
     * @param fiAvailableInvoice the object to be saved
     */
    public void saveFiAvailableInvoice(FiAvailableInvoice fiAvailableInvoice);

    /**
     * Removes a fiAvailableInvoice from the database by id
     * @param id the fiAvailableInvoice's id
     */
    public void removeFiAvailableInvoice(final String id);
    //added for getFiAvailableInvoicesByCrm
    public List getFiAvailableInvoicesByCrm(CommonRecord crm, Pager pager);

    /**
     * 合规可用发票审核
     * @author 2015-11-30 fu
     * @param fiAvailableInvoice
     * @param userCodeLogin
     */
	public void checkFiAvailableInvoice(FiAvailableInvoice fiAvailableInvoice,String userCodeLogin);
}

