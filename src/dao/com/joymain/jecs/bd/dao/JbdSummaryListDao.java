
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSummaryListDao extends Dao {

    /**
     * Retrieves all of the jbdSummaryLists
     */
    public List getJbdSummaryLists(JbdSummaryList jbdSummaryList);

    /**
     * Gets jbdSummaryList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdSummaryList's id
     * @return jbdSummaryList populated jbdSummaryList object
     */
    public JbdSummaryList getJbdSummaryList(final BigDecimal id);

    /**
     * Saves a jbdSummaryList's information
     * @param jbdSummaryList the object to be saved
     */    
    public void saveJbdSummaryList(JbdSummaryList jbdSummaryList);

    /**
     * Removes a jbdSummaryList from the database by id
     * @param id the jbdSummaryList's id
     */
    public void removeJbdSummaryList(final BigDecimal id);
    //added for getJbdSummaryListsByCrm
    public List getJbdSummaryListsByCrm(CommonRecord crm, Pager pager);
}

