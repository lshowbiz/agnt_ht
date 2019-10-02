
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdUserValidList;
import com.joymain.jecs.bd.dao.JbdUserValidListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdUserValidListManager extends Manager {
    /**
     * Retrieves all of the jbdUserValidLists
     */
    public List getJbdUserValidLists(JbdUserValidList jbdUserValidList);

    /**
     * Gets jbdUserValidList's information based on id.
     * @param id the jbdUserValidList's id
     * @return jbdUserValidList populated jbdUserValidList object
     */
    public JbdUserValidList getJbdUserValidList(final String id);

    /**
     * Saves a jbdUserValidList's information
     * @param jbdUserValidList the object to be saved
     */
    public void saveJbdUserValidList(JbdUserValidList jbdUserValidList);

    /**
     * Removes a jbdUserValidList from the database by id
     * @param id the jbdUserValidList's id
     */
    public void removeJbdUserValidList(final String id);
    //added for getJbdUserValidListsByCrm
    public List getJbdUserValidListsByCrm(CommonRecord crm, Pager pager);
}

