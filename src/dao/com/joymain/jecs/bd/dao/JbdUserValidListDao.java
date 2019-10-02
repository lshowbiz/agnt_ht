
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdUserValidList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdUserValidListDao extends Dao {

    /**
     * Retrieves all of the jbdUserValidLists
     */
    public List getJbdUserValidLists(JbdUserValidList jbdUserValidList);

    /**
     * Gets jbdUserValidList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdUserValidList's id
     * @return jbdUserValidList populated jbdUserValidList object
     */
    public JbdUserValidList getJbdUserValidList(final BigDecimal id);

    /**
     * Saves a jbdUserValidList's information
     * @param jbdUserValidList the object to be saved
     */    
    public void saveJbdUserValidList(JbdUserValidList jbdUserValidList);

    /**
     * Removes a jbdUserValidList from the database by id
     * @param id the jbdUserValidList's id
     */
    public void removeJbdUserValidList(final BigDecimal id);
    //added for getJbdUserValidListsByCrm
    public List getJbdUserValidListsByCrm(CommonRecord crm, Pager pager);
}

