
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdCalcDayKbList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdCalcDayKbListDao extends Dao {

    /**
     * Retrieves all of the jbdCalcDayKbLists
     */
    public List getJbdCalcDayKbLists(JbdCalcDayKbList jbdCalcDayKbList);

    /**
     * Gets jbdCalcDayKbList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdCalcDayKbList's id
     * @return jbdCalcDayKbList populated jbdCalcDayKbList object
     */
    public JbdCalcDayKbList getJbdCalcDayKbList(final Long id);

    /**
     * Saves a jbdCalcDayKbList's information
     * @param jbdCalcDayKbList the object to be saved
     */    
    public void saveJbdCalcDayKbList(JbdCalcDayKbList jbdCalcDayKbList);

    /**
     * Removes a jbdCalcDayKbList from the database by id
     * @param id the jbdCalcDayKbList's id
     */
    public void removeJbdCalcDayKbList(final Long id);
    //added for getJbdCalcDayKbListsByCrm
    public List getJbdCalcDayKbListsByCrm(CommonRecord crm, Pager pager);
}

