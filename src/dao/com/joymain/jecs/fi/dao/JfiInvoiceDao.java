
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiInvoice;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiInvoiceDao extends Dao {

    /**
     * Retrieves all of the jfiInvoices
     */
    public List getJfiInvoices(JfiInvoice jfiInvoice);

    /**
     * Gets jfiInvoice's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jfiInvoice's id
     * @return jfiInvoice populated jfiInvoice object
     */
    public JfiInvoice getJfiInvoice(final Long id);

    /**
     * Saves a jfiInvoice's information
     * @param jfiInvoice the object to be saved
     */    
    public void saveJfiInvoice(JfiInvoice jfiInvoice);

    /**
     * Removes a jfiInvoice from the database by id
     * @param id the jfiInvoice's id
     */
    public void removeJfiInvoice(final Long id);
    //added for getJfiInvoicesByCrm
    public List getJfiInvoicesByCrm(CommonRecord crm, Pager pager);
    
    public List getJfiInvoiceExportByCrm(CommonRecord crm);
    
    public List getJfiDepositExportByCrm(CommonRecord crm);
    
    public List getJfiDepositInvoiceExportByCrm(CommonRecord crm);
}

