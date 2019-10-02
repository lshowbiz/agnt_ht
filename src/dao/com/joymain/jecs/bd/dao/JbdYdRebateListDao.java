
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdYdRebateList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdYdRebateListDao extends Dao {

    /**
     * Retrieves all of the jbdYdRebateLists
     */
    public List getJbdYdRebateLists(JbdYdRebateList jbdYdRebateList);

    /**
     * Gets jbdYdRebateList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdYdRebateList's id
     * @return jbdYdRebateList populated jbdYdRebateList object
     */
    public JbdYdRebateList getJbdYdRebateList(final Long id);

    /**
     * Saves a jbdYdRebateList's information
     * @param jbdYdRebateList the object to be saved
     */    
    public void saveJbdYdRebateList(JbdYdRebateList jbdYdRebateList);

    /**
     * Removes a jbdYdRebateList from the database by id
     * @param id the jbdYdRebateList's id
     */
    public void removeJbdYdRebateList(final Long id);
    //added for getJbdYdRebateListsByCrm
    public List getJbdYdRebateListsByCrm(CommonRecord crm, Pager pager);
    public List getVJbdYdRebateListsByCrm(CommonRecord crm, Pager pager);
}

