
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiDepositMoney;
import com.joymain.jecs.fi.dao.JfiDepositMoneyDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiDepositMoneyManager extends Manager {
    /**
     * Retrieves all of the jfiDepositMoneys
     */
    public List getJfiDepositMoneys(JfiDepositMoney jfiDepositMoney);

    /**
     * Gets jfiDepositMoney's information based on id.
     * @param id the jfiDepositMoney's id
     * @return jfiDepositMoney populated jfiDepositMoney object
     */
    public JfiDepositMoney getJfiDepositMoney(final String id);

    /**
     * Saves a jfiDepositMoney's information
     * @param jfiDepositMoney the object to be saved
     */
    public void saveJfiDepositMoney(JfiDepositMoney jfiDepositMoney);

    /**
     * Removes a jfiDepositMoney from the database by id
     * @param id the jfiDepositMoney's id
     */
    public void removeJfiDepositMoney(final String id);
    //added for getJfiDepositMoneysByCrm
    public List getJfiDepositMoneysByCrm(CommonRecord crm, Pager pager);
	public BigDecimal getSumDepositMoney(CommonRecord crm);
}

