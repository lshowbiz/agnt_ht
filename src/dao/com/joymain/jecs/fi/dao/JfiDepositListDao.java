
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiDepositList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiDepositListDao extends Dao {

    /**
     * Retrieves all of the jfiDepositLists
     */
    public List getJfiDepositLists(JfiDepositList jfiDepositList);

    /**
     * Gets jfiDepositList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jfiDepositList's id
     * @return jfiDepositList populated jfiDepositList object
     */
    public JfiDepositList getJfiDepositList(final Long id);

    /**
     * Saves a jfiDepositList's information
     * @param jfiDepositList the object to be saved
     */    
    public void saveJfiDepositList(JfiDepositList jfiDepositList);

    /**
     * Removes a jfiDepositList from the database by id
     * @param id the jfiDepositList's id
     */
    public void removeJfiDepositList(final Long id);
    //added for getJfiDepositListsByCrm
    public List getJfiDepositListsByCrm(CommonRecord crm, Pager pager);
}

