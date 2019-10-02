
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdVentureLeaderSubHist;
import com.joymain.jecs.bd.dao.JbdVentureLeaderSubHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdVentureLeaderSubHistManager extends Manager {
    /**
     * Retrieves all of the jbdVentureLeaderSubHists
     */
    public List getJbdVentureLeaderSubHists(JbdVentureLeaderSubHist jbdVentureLeaderSubHist);

    /**
     * Gets jbdVentureLeaderSubHist's information based on id.
     * @param id the jbdVentureLeaderSubHist's id
     * @return jbdVentureLeaderSubHist populated jbdVentureLeaderSubHist object
     */
    public JbdVentureLeaderSubHist getJbdVentureLeaderSubHist(final String id);

    /**
     * Saves a jbdVentureLeaderSubHist's information
     * @param jbdVentureLeaderSubHist the object to be saved
     */
    public void saveJbdVentureLeaderSubHist(JbdVentureLeaderSubHist jbdVentureLeaderSubHist);

    /**
     * Removes a jbdVentureLeaderSubHist from the database by id
     * @param id the jbdVentureLeaderSubHist's id
     */
    public void removeJbdVentureLeaderSubHist(final String id);
    //added for getJbdVentureLeaderSubHistsByCrm
    public List getJbdVentureLeaderSubHistsByCrm(CommonRecord crm, Pager pager);
    public List getJbdVentureLeaderSubHistByUserCode(String userCode,String wweek,String bounsType);
    public List getJbdVentureLeaderSubHistDetailByUserCode(String userCode, String wweek);
}

