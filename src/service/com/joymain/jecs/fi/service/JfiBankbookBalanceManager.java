
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.dao.JfiBankbookBalanceDao;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiBankbookBalanceManager extends Manager {
    /**
     * Retrieves all of the jfiBankbookBalances
     */
    public List getJfiBankbookBalances(JfiBankbookBalance jfiBankbookBalance);

    /**
     * Gets jfiBankbookBalance's information based on userCode.
     * @param userCode the jfiBankbookBalance's userCode
     * @return jfiBankbookBalance populated jfiBankbookBalance object
     */
    public JfiBankbookBalance getJfiBankbookBalance(final String userCode);

    /**
     * Saves a jfiBankbookBalance's information
     * @param jfiBankbookBalance the object to be saved
     */
    public void saveJfiBankbookBalance(JfiBankbookBalance jfiBankbookBalance);

    /**
     * Removes a jfiBankbookBalance from the database by userCode
     * @param userCode the jfiBankbookBalance's userCode
     */
    public void removeJfiBankbookBalance(final String userCode);
    //added for getJfiBankbookBalancesByCrm
    public List getJfiBankbookBalancesByCrm(CommonRecord crm, Pager pager);
}

