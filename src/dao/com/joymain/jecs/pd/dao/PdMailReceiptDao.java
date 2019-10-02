
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdMailReceiptDao extends Dao {

    /**
     * Retrieves all of the pdMailReceipts
     */
    public List getPdMailReceipts(PdMailReceipt pdMailReceipt);

    /**
     * Gets pdMailReceipt's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param mailReceiptId the pdMailReceipt's mailReceiptId
     * @return pdMailReceipt populated pdMailReceipt object
     */
    public PdMailReceipt getPdMailReceipt(final BigDecimal mailReceiptId);

    /**
     * Saves a pdMailReceipt's information
     * @param pdMailReceipt the object to be saved
     */    
    public void savePdMailReceipt(PdMailReceipt pdMailReceipt);

    /**
     * Removes a pdMailReceipt from the database by mailReceiptId
     * @param mailReceiptId the pdMailReceipt's mailReceiptId
     */
    public void removePdMailReceipt(final BigDecimal mailReceiptId);
    //added for getPdMailReceiptsByCrm
    public List getPdMailReceiptsByCrm(CommonRecord crm, Pager pager);
}

