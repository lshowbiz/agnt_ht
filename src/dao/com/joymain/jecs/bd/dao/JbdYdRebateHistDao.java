
package com.joymain.jecs.bd.dao;

import java.util.List;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdYdRebateHist;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdYdRebateHistDao extends Dao {

    /**
     * Retrieves all of the jbdYdRebateHists
     */
    public List getJbdYdRebateHists(JbdYdRebateHist jbdYdRebateHist);

    /**
     * Gets jbdYdRebateHist's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdYdRebateHist's id
     * @return jbdYdRebateHist populated jbdYdRebateHist object
     */
    public JbdYdRebateHist getJbdYdRebateHist(final Long id);

    /**
     * Saves a jbdYdRebateHist's information
     * @param jbdYdRebateHist the object to be saved
     */    
    public void saveJbdYdRebateHist(JbdYdRebateHist jbdYdRebateHist);
    public void save(JbdYdRebateHist jbdYdRebateHist);
    /**
     * Removes a jbdYdRebateHist from the database by id
     * @param id the jbdYdRebateHist's id
     */
    public void removeJbdYdRebateHist(final Long id);
    //added for getJbdYdRebateHistsByCrm
    public List getJbdYdRebateHistsByCrm(CommonRecord crm, Pager pager);
}

