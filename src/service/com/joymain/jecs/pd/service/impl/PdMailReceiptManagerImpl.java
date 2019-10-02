
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.dao.PdMailReceiptDao;
import com.joymain.jecs.pd.service.PdMailReceiptManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdMailReceiptManagerImpl extends BaseManager implements PdMailReceiptManager {
    private PdMailReceiptDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdMailReceiptDao(PdMailReceiptDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdMailReceiptManager#getPdMailReceipts(com.joymain.jecs.pd.model.PdMailReceipt)
     */
    public List getPdMailReceipts(final PdMailReceipt pdMailReceipt) {
        return dao.getPdMailReceipts(pdMailReceipt);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdMailReceiptManager#getPdMailReceipt(String mailReceiptId)
     */
    public PdMailReceipt getPdMailReceipt(final String mailReceiptId) {
        return dao.getPdMailReceipt(new BigDecimal(mailReceiptId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdMailReceiptManager#savePdMailReceipt(PdMailReceipt pdMailReceipt)
     */
    public void savePdMailReceipt(PdMailReceipt pdMailReceipt) {
        dao.savePdMailReceipt(pdMailReceipt);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdMailReceiptManager#removePdMailReceipt(String mailReceiptId)
     */
    public void removePdMailReceipt(final String mailReceiptId) {
        dao.removePdMailReceipt(new BigDecimal(mailReceiptId));
    }
    //added for getPdMailReceiptsByCrm
    public List getPdMailReceiptsByCrm(CommonRecord crm, Pager pager){
	return dao.getPdMailReceiptsByCrm(crm,pager);
    }
}
