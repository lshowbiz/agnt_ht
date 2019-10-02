
package com.joymain.jecs.bd.dao;

import java.util.List; 

import com.joymain.jecs.bd.model.JbdTravelPointAll;
import com.joymain.jecs.bd.model.JbdTravelPointAllPK;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointAllDao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointAlls
     */
    public List getJbdTravelPointAlls(JbdTravelPointAll jbdTravelPointAll);

    /**
     * Gets jbdTravelPointAll's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param comp_id the jbdTravelPointAll's comp_id
     * @return jbdTravelPointAll populated jbdTravelPointAll object
     */
    public JbdTravelPointAll getJbdTravelPointAll(final JbdTravelPointAllPK comp_id);

    /**
     * Saves a jbdTravelPointAll's information
     * @param jbdTravelPointAll the object to be saved
     */    
    public void saveJbdTravelPointAll(JbdTravelPointAll jbdTravelPointAll);

    /**
     * Removes a jbdTravelPointAll from the database by comp_id
     * @param comp_id the jbdTravelPointAll's comp_id
     */
    public void removeJbdTravelPointAll(final JbdTravelPointAllPK comp_id);
    //added for getJbdTravelPointAllsByCrm
    public List getJbdTravelPointAllsByCrm(CommonRecord crm, Pager pager);
    
    public JbdTravelPointAll getJbdTravelPointAll(final String userCode,final Integer fyear);
}

