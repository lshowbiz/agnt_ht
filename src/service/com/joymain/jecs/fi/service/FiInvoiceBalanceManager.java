
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiInvoiceBalance;
import com.joymain.jecs.fi.dao.FiInvoiceBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiInvoiceBalanceManager extends Manager {
    /**
     * Retrieves all of the fiInvoiceBalances
     */
    public List getFiInvoiceBalances(FiInvoiceBalance fiInvoiceBalance);

    /**
     * Gets fiInvoiceBalance's information based on id.
     * @param id the fiInvoiceBalance's id
     * @return fiInvoiceBalance populated fiInvoiceBalance object
     */
    public FiInvoiceBalance getFiInvoiceBalance(final String id);

    /**
     * Saves a fiInvoiceBalance's information
     * @param fiInvoiceBalance the object to be saved
     */
    public void saveFiInvoiceBalance(FiInvoiceBalance fiInvoiceBalance);

    /**
     * Removes a fiInvoiceBalance from the database by id
     * @param id the fiInvoiceBalance's id
     */
    public void removeFiInvoiceBalance(final String id);
    //added for getFiInvoiceBalancesByCrm
    public List getFiInvoiceBalancesByCrm(CommonRecord crm, Pager pager);

    /**
     * 根据会员编号，获取会员的可用发票余额
     * @author fu 2015-11-30
     * @param userCode
     * @return fiInvoiceBalance
     */
	public FiInvoiceBalance getFiInvoiceBalanceByUserCode(String userCode);
}

