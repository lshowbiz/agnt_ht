
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdVentureLeaderSubHist;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdVentureLeaderSubHistDao extends Dao {

    /**
     * Retrieves all of the jbdVentureLeaderSubHists
     */
    public List getJbdVentureLeaderSubHists(JbdVentureLeaderSubHist jbdVentureLeaderSubHist);

    /**
     * Gets jbdVentureLeaderSubHist's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdVentureLeaderSubHist's id
     * @return jbdVentureLeaderSubHist populated jbdVentureLeaderSubHist object
     */
    public JbdVentureLeaderSubHist getJbdVentureLeaderSubHist(final Long id);

    /**
     * Saves a jbdVentureLeaderSubHist's information
     * @param jbdVentureLeaderSubHist the object to be saved
     */    
    public void saveJbdVentureLeaderSubHist(JbdVentureLeaderSubHist jbdVentureLeaderSubHist);

    /**
     * Removes a jbdVentureLeaderSubHist from the database by id
     * @param id the jbdVentureLeaderSubHist's id
     */
    public void removeJbdVentureLeaderSubHist(final Long id);
    //added for getJbdVentureLeaderSubHistsByCrm
    public List getJbdVentureLeaderSubHistsByCrm(CommonRecord crm, Pager pager);
    
    public List getJbdVentureLeaderSubHistByUserCode(String userCode,String wweek,String bounsType);
    List getJbdVentureLeaderSubHistDetailByUserCode(String userCode, String wweek);
}

