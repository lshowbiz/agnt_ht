
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiInvoiceDeposit;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiInvoiceDepositDao extends Dao {

    /**
     * Retrieves all of the jfiInvoiceDeposits
     */
    public List getJfiInvoiceDeposits(JfiInvoiceDeposit jfiInvoiceDeposit);

    /**
     * Gets jfiInvoiceDeposit's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jfiInvoiceDeposit's id
     * @return jfiInvoiceDeposit populated jfiInvoiceDeposit object
     */
    public JfiInvoiceDeposit getJfiInvoiceDeposit(final Long id);

    /**
     * Saves a jfiInvoiceDeposit's information
     * @param jfiInvoiceDeposit the object to be saved
     */    
    public void saveJfiInvoiceDeposit(JfiInvoiceDeposit jfiInvoiceDeposit);

    /**
     * Removes a jfiInvoiceDeposit from the database by id
     * @param id the jfiInvoiceDeposit's id
     */
    public void removeJfiInvoiceDeposit(final Long id);
    //added for getJfiInvoiceDepositsByCrm
    public List getJfiInvoiceDepositsByCrm(CommonRecord crm, Pager pager);
}

