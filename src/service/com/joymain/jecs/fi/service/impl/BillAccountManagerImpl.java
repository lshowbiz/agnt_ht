
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.BillAccount;
import com.joymain.jecs.fi.dao.BillAccountDao;
import com.joymain.jecs.fi.service.BillAccountManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class BillAccountManagerImpl extends BaseManager implements BillAccountManager {
    private BillAccountDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setBillAccountDao(BillAccountDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.BillAccountManager#getBillAccounts(com.joymain.jecs.fi.model.BillAccount)
     */
    public List getBillAccounts(final BillAccount billAccount) {
        return dao.getBillAccounts(billAccount);
    }

    /**
     * @see com.joymain.jecs.fi.service.BillAccountManager#getBillAccount(String baId)
     */
    public BillAccount getBillAccount(final String baId) {
        return dao.getBillAccount(new Long(baId));
    }

    /**
     * @see com.joymain.jecs.fi.service.BillAccountManager#saveBillAccount(BillAccount billAccount)
     */
    public void saveBillAccount(BillAccount billAccount) {
        dao.saveBillAccount(billAccount);
    }

    /**
     * @see com.joymain.jecs.fi.service.BillAccountManager#removeBillAccount(String baId)
     */
    public void removeBillAccount(final String baId) {
        dao.removeBillAccount(new Long(baId));
    }
    //added for getBillAccountsByCrm
    public List getBillAccountsByCrm(CommonRecord crm, Pager pager){
	return dao.getBillAccountsByCrm(crm,pager);
    }

	public List getBillAccountsByBaCode(String baCode) {
		return dao.getBillAccountsByBaCode(baCode);
	}

	public BigDecimal getBilllAccountsPersent(Long baId) {
		return dao.getBilllAccountsPersent(baId);
	}
}
