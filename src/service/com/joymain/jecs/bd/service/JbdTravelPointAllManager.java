
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.bd.model.JbdTravelPointAll;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointAllManager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointAlls
     */
    public List getJbdTravelPointAlls(JbdTravelPointAll jbdTravelPointAll);

    /**
     * Gets jbdTravelPointAll's information based on comp_id.
     * @param comp_id the jbdTravelPointAll's comp_id
     * @return jbdTravelPointAll populated jbdTravelPointAll object
     */
    public JbdTravelPointAll getJbdTravelPointAll(final String comp_id);

    /**
     * Saves a jbdTravelPointAll's information
     * @param jbdTravelPointAll the object to be saved
     */
    public void saveJbdTravelPointAll(JbdTravelPointAll jbdTravelPointAll);

    /**
     * Removes a jbdTravelPointAll from the database by comp_id
     * @param comp_id the jbdTravelPointAll's comp_id
     */
    public void removeJbdTravelPointAll(final String comp_id);
    //added for getJbdTravelPointAllsByCrm
    public List getJbdTravelPointAllsByCrm(CommonRecord crm, Pager pager);
    
    public JbdTravelPointAll getJbdTravelPointAll(final String userCode,final Integer fyear);
}

