
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdManualCon;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdManualConDao extends Dao {

    /**
     * Retrieves all of the jbdManualCons
     */
    public List getJbdManualCons(JbdManualCon jbdManualCon);

    /**
     * Gets jbdManualCon's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdManualCon's id
     * @return jbdManualCon populated jbdManualCon object
     */
    public JbdManualCon getJbdManualCon(final Long id);

    /**
     * Saves a jbdManualCon's information
     * @param jbdManualCon the object to be saved
     */    
    public void saveJbdManualCon(JbdManualCon jbdManualCon);

    /**
     * Removes a jbdManualCon from the database by id
     * @param id the jbdManualCon's id
     */
    public void removeJbdManualCon(final Long id);
    //added for getJbdManualConsByCrm
    public List getJbdManualConsByCrm(CommonRecord crm, Pager pager);
    public void saveJbdManualCons(List<JbdManualCon> jbdManualCons);
}

