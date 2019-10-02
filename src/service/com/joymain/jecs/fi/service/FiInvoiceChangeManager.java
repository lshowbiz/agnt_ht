
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.fi.model.FiInvoiceChange;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiInvoiceChangeManager extends Manager {
    /**
     * Retrieves all of the fiInvoiceChanges
     */
    public List getFiInvoiceChanges(FiInvoiceChange fiInvoiceChange);

    /**
     * Gets fiInvoiceChange's information based on id.
     * @param id the fiInvoiceChange's id
     * @return fiInvoiceChange populated fiInvoiceChange object
     */
    public FiInvoiceChange getFiInvoiceChange(final String id);

    /**
     * Saves a fiInvoiceChange's information
     * @param fiInvoiceChange the object to be saved
     */
    public void saveFiInvoiceChange(FiInvoiceChange fiInvoiceChange);

    /**
     * Removes a fiInvoiceChange from the database by id
     * @param id the fiInvoiceChange's id
     */
    public void removeFiInvoiceChange(final String id);
    //added for getFiInvoiceChangesByCrm
    public List getFiInvoiceChangesByCrm(CommonRecord crm, Pager pager);

    /**
     * 合规可用发票审核后向可用发票流水表和可用发票余额表插入数据
     * @author 2015-11-30 fu 
     * @param fiAvailableInvoice
     * @param userCodeLogin
     */
	public void scSavefiInvoiceChange(FiAvailableInvoice fiAvailableInvoice,String userCodeLogin,BigDecimal balance);
}

