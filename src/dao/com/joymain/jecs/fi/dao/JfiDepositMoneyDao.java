
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiDepositMoney;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiDepositMoneyDao extends Dao {

    /**
     * Retrieves all of the jfiDepositMoneys
     */
    public List getJfiDepositMoneys(JfiDepositMoney jfiDepositMoney);

    /**
     * Gets jfiDepositMoney's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jfiDepositMoney's id
     * @return jfiDepositMoney populated jfiDepositMoney object
     */
    public JfiDepositMoney getJfiDepositMoney(final Long id);

    /**
     * Saves a jfiDepositMoney's information
     * @param jfiDepositMoney the object to be saved
     */    
    public void saveJfiDepositMoney(JfiDepositMoney jfiDepositMoney);

    /**
     * Removes a jfiDepositMoney from the database by id
     * @param id the jfiDepositMoney's id
     */
    public void removeJfiDepositMoney(final Long id);
    //added for getJfiDepositMoneysByCrm
    public List getJfiDepositMoneysByCrm(CommonRecord crm, Pager pager);
	public BigDecimal getSumDepositMoney(CommonRecord crm);
}

