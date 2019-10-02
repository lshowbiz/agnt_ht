
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.dao.PdMailReceiptDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdMailReceiptManager extends Manager {
    /**
     * Retrieves all of the pdMailReceipts
     */
    public List getPdMailReceipts(PdMailReceipt pdMailReceipt);

    /**
     * Gets pdMailReceipt's information based on mailReceiptId.
     * @param mailReceiptId the pdMailReceipt's mailReceiptId
     * @return pdMailReceipt populated pdMailReceipt object
     */
    public PdMailReceipt getPdMailReceipt(final String mailReceiptId);

    /**
     * Saves a pdMailReceipt's information
     * @param pdMailReceipt the object to be saved
     */
    public void savePdMailReceipt(PdMailReceipt pdMailReceipt);

    /**
     * Removes a pdMailReceipt from the database by mailReceiptId
     * @param mailReceiptId the pdMailReceipt's mailReceiptId
     */
    public void removePdMailReceipt(final String mailReceiptId);
    //added for getPdMailReceiptsByCrm
    public List getPdMailReceiptsByCrm(CommonRecord crm, Pager pager);
}

