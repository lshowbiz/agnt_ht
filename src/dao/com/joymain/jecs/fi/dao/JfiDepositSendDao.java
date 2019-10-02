
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiDepositSend;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiDepositSendDao extends Dao {

    /**
     * Retrieves all of the jfiDepositSends
     */
    public List getJfiDepositSends(JfiDepositSend jfiDepositSend);

    /**
     * Gets jfiDepositSend's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jfiDepositSend's id
     * @return jfiDepositSend populated jfiDepositSend object
     */
    public JfiDepositSend getJfiDepositSend(final Long id);

    /**
     * Saves a jfiDepositSend's information
     * @param jfiDepositSend the object to be saved
     */    
    public void saveJfiDepositSend(JfiDepositSend jfiDepositSend);

    /**
     * Removes a jfiDepositSend from the database by id
     * @param id the jfiDepositSend's id
     */
    public void removeJfiDepositSend(final Long id);
    //added for getJfiDepositSendsByCrm
    public List getJfiDepositSendsByCrm(CommonRecord crm, Pager pager);
    public void saveJfiDepositSend(List<JfiDepositSend> jfiDepositSends);
	public BigDecimal getSumDepositMoney(CommonRecord crm);
}

