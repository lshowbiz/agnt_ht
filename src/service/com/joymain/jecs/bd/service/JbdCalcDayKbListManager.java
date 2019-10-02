
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdCalcDayKbList;
import com.joymain.jecs.bd.dao.JbdCalcDayKbListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdCalcDayKbListManager extends Manager {
    /**
     * Retrieves all of the jbdCalcDayKbLists
     */
    public List getJbdCalcDayKbLists(JbdCalcDayKbList jbdCalcDayKbList);

    /**
     * Gets jbdCalcDayKbList's information based on id.
     * @param id the jbdCalcDayKbList's id
     * @return jbdCalcDayKbList populated jbdCalcDayKbList object
     */
    public JbdCalcDayKbList getJbdCalcDayKbList(final String id);

    /**
     * Saves a jbdCalcDayKbList's information
     * @param jbdCalcDayKbList the object to be saved
     */
    public void saveJbdCalcDayKbList(JbdCalcDayKbList jbdCalcDayKbList);

    /**
     * Removes a jbdCalcDayKbList from the database by id
     * @param id the jbdCalcDayKbList's id
     */
    public void removeJbdCalcDayKbList(final String id);
    //added for getJbdCalcDayKbListsByCrm
    public List getJbdCalcDayKbListsByCrm(CommonRecord crm, Pager pager);
}

