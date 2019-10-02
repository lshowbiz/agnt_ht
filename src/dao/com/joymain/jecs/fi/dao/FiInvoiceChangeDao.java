
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiInvoiceChange;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiInvoiceChangeDao extends Dao {

    /**
     * Retrieves all of the fiInvoiceChanges
     */
    public List getFiInvoiceChanges(FiInvoiceChange fiInvoiceChange);

    /**
     * Gets fiInvoiceChange's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the fiInvoiceChange's id
     * @return fiInvoiceChange populated fiInvoiceChange object
     */
    public FiInvoiceChange getFiInvoiceChange(final BigDecimal id);

    /**
     * Saves a fiInvoiceChange's information
     * @param fiInvoiceChange the object to be saved
     */    
    public void saveFiInvoiceChange(FiInvoiceChange fiInvoiceChange);

    /**
     * Removes a fiInvoiceChange from the database by id
     * @param id the fiInvoiceChange's id
     */
    public void removeFiInvoiceChange(final BigDecimal id);
    //added for getFiInvoiceChangesByCrm
    public List getFiInvoiceChangesByCrm(CommonRecord crm, Pager pager);
}

