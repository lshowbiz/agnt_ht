
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.dao.JbdSummaryListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdSummaryListManager extends Manager {
    /**
     * Retrieves all of the jbdSummaryLists
     */
    public List getJbdSummaryLists(JbdSummaryList jbdSummaryList);

    /**
     * Gets jbdSummaryList's information based on id.
     * @param id the jbdSummaryList's id
     * @return jbdSummaryList populated jbdSummaryList object
     */
    public JbdSummaryList getJbdSummaryList(final String id);

    /**
     * Saves a jbdSummaryList's information
     * @param jbdSummaryList the object to be saved
     */
    public void saveJbdSummaryList(JbdSummaryList jbdSummaryList);

    /**
     * Removes a jbdSummaryList from the database by id
     * @param id the jbdSummaryList's id
     */
    public void removeJbdSummaryList(final String id);
    //added for getJbdSummaryListsByCrm
    public List getJbdSummaryListsByCrm(CommonRecord crm, Pager pager);
}

