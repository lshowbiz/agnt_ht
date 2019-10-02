
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiBankbookBalanceDao extends Dao {

    /**
     * Retrieves all of the jfiBankbookBalances
     */
    public List getJfiBankbookBalances(JfiBankbookBalance jfiBankbookBalance);

    /**
     * Gets jfiBankbookBalance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
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
     * @see com.joymain.jecs.fi.dao.FiBankbookBalanceDao#getFiBankbookBalanceForUpdate(String userCode)
     */
    public JfiBankbookBalance getJfiBankbookBalanceForUpdate(final String userCode);

    /**
     * Removes a jfiBankbookBalance from the database by userCode
     * @param userCode the jfiBankbookBalance's userCode
     */
    public void removeJfiBankbookBalance(final String userCode);
    //added for getJfiBankbookBalancesByCrm
    public List getJfiBankbookBalancesByCrm(CommonRecord crm, Pager pager);
}

