
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiDepositList;
import com.joymain.jecs.fi.dao.JfiDepositListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiDepositListManager extends Manager {
    /**
     * Retrieves all of the jfiDepositLists
     */
    public List getJfiDepositLists(JfiDepositList jfiDepositList);

    /**
     * Gets jfiDepositList's information based on id.
     * @param id the jfiDepositList's id
     * @return jfiDepositList populated jfiDepositList object
     */
    public JfiDepositList getJfiDepositList(final String id);

    /**
     * Saves a jfiDepositList's information
     * @param jfiDepositList the object to be saved
     */
    public void saveJfiDepositList(JfiDepositList jfiDepositList);

    /**
     * Removes a jfiDepositList from the database by id
     * @param id the jfiDepositList's id
     */
    public void removeJfiDepositList(final String id);
    //added for getJfiDepositListsByCrm
    public List getJfiDepositListsByCrm(CommonRecord crm, Pager pager);
}

