
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdVentureFundList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdVentureFundListDao extends Dao {

    /**
     * Retrieves all of the jbdVentureFundLists
     */
    public List getJbdVentureFundLists(JbdVentureFundList jbdVentureFundList);

    /**
     * Gets jbdVentureFundList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdVentureFundList's id
     * @return jbdVentureFundList populated jbdVentureFundList object
     */
    public JbdVentureFundList getJbdVentureFundList(final Long id);

    /**
     * Saves a jbdVentureFundList's information
     * @param jbdVentureFundList the object to be saved
     */    
    public void saveJbdVentureFundList(JbdVentureFundList jbdVentureFundList);

    /**
     * Removes a jbdVentureFundList from the database by id
     * @param id the jbdVentureFundList's id
     */
    public void removeJbdVentureFundList(final Long id);
    //added for getJbdVentureFundListsByCrm
    public List getJbdVentureFundListsByCrm(CommonRecord crm, Pager pager);
}

