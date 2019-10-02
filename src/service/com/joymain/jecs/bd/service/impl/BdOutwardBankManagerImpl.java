
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.dao.BdOutwardBankDao;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class BdOutwardBankManagerImpl extends BaseManager implements BdOutwardBankManager {
    private BdOutwardBankDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setBdOutwardBankDao(BdOutwardBankDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.BdOutwardBankManager#getBdOutwardBanks(com.joymain.jecs.bd.model.BdOutwardBank)
     */
    public List getBdOutwardBanks(final BdOutwardBank bdOutwardBank) {
        return dao.getBdOutwardBanks(bdOutwardBank);
    }

    /**
     * @see com.joymain.jecs.bd.service.BdOutwardBankManager#getBdOutwardBank(String bankId)
     */
    public BdOutwardBank getBdOutwardBank(final String bankId) {
        return dao.getBdOutwardBank(new Long(bankId));
    }

    /**
     * @see com.joymain.jecs.bd.service.BdOutwardBankManager#saveBdOutwardBank(BdOutwardBank bdOutwardBank)
     */
    public void saveBdOutwardBank(BdOutwardBank bdOutwardBank) {
        dao.saveBdOutwardBank(bdOutwardBank);
    }

    /**
     * @see com.joymain.jecs.bd.service.BdOutwardBankManager#removeBdOutwardBank(String bankId)
     */
    public void removeBdOutwardBank(final String bankId) {
        dao.removeBdOutwardBank(new Long(bankId));
    }
    //added for getBdOutwardBanksByCrm
    public List getBdOutwardBanksByCrm(CommonRecord crm, Pager pager){
	return dao.getBdOutwardBanksByCrm(crm,pager);
    }
}
