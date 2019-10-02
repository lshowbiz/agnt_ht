
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdVentureFundList;
import com.joymain.jecs.bd.dao.JbdVentureFundListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdVentureFundListManager extends Manager {
    /**
     * Retrieves all of the jbdVentureFundLists
     */
    public List getJbdVentureFundLists(JbdVentureFundList jbdVentureFundList);

    /**
     * Gets jbdVentureFundList's information based on id.
     * @param id the jbdVentureFundList's id
     * @return jbdVentureFundList populated jbdVentureFundList object
     */
    public JbdVentureFundList getJbdVentureFundList(final String id);

    /**
     * Saves a jbdVentureFundList's information
     * @param jbdVentureFundList the object to be saved
     */
    public void saveJbdVentureFundList(JbdVentureFundList jbdVentureFundList);

    /**
     * Removes a jbdVentureFundList from the database by id
     * @param id the jbdVentureFundList's id
     */
    public void removeJbdVentureFundList(final String id);
    //added for getJbdVentureFundListsByCrm
    public List getJbdVentureFundListsByCrm(CommonRecord crm, Pager pager);
}

