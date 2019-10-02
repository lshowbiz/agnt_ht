
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.BillAccount;
import com.joymain.jecs.fi.dao.BillAccountDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface BillAccountManager extends Manager {
    /**
     * Retrieves all of the billAccounts
     */
    public List getBillAccounts(BillAccount billAccount);

    /**
     * Gets billAccount's information based on baId.
     * @param baId the billAccount's baId
     * @return billAccount populated billAccount object
     */
    public BillAccount getBillAccount(final String baId);

    /**
     * Saves a billAccount's information
     * @param billAccount the object to be saved
     */
    public void saveBillAccount(BillAccount billAccount);

    /**
     * Removes a billAccount from the database by baId
     * @param baId the billAccount's baId
     */
    public void removeBillAccount(final String baId);
    //added for getBillAccountsByCrm
    public List getBillAccountsByCrm(CommonRecord crm, Pager pager);
	public List getBillAccountsByBaCode(String baCode);
	public BigDecimal getBilllAccountsPersent(Long baId);
}

