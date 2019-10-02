
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiAvailableInvoiceDao extends Dao {

    /**
     * Retrieves all of the fiAvailableInvoices
     */
    public List getFiAvailableInvoices(FiAvailableInvoice fiAvailableInvoice);

    /**
     * Gets fiAvailableInvoice's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
}

