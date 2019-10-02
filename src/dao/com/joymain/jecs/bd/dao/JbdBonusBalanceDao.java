
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdBonusBalanceDao extends Dao {

    /**
     * Retrieves all of the jbdBonusBalances
     */
    public List getJbdBonusBalances(JbdBonusBalance jbdBonusBalance);

    /**
     * Gets jbdBonusBalance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the jbdBonusBalance's userCode
     * @return jbdBonusBalance populated jbdBonusBalance object
     */
    public JbdBonusBalance getJbdBonusBalance(final String userCode);

    /**
     * Saves a jbdBonusBalance's information
     * @param jbdBonusBalance the object to be saved
     */    
    public void saveJbdBonusBalance(JbdBonusBalance jbdBonusBalance);

    /**
     * Removes a jbdBonusBalance from the database by userCode
     * @param userCode the jbdBonusBalance's userCode
     */
    public void removeJbdBonusBalance(final String userCode);
    //added for getJbdBonusBalancesByCrm
    public List getJbdBonusBalancesByCrm(CommonRecord crm, Pager pager);
    public JbdBonusBalance getJbdBonusBalanceForUpdate(final String userCode);
    
    
}

