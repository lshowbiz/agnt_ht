
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdTravelPointLog2012;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2012Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointLog2012Manager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointLog2012s
     */
    public List getJbdTravelPointLog2012s(JbdTravelPointLog2012 jbdTravelPointLog2012);

    /**
     * Gets jbdTravelPointLog2012's information based on logId.
     * @param logId the jbdTravelPointLog2012's logId
     * @return jbdTravelPointLog2012 populated jbdTravelPointLog2012 object
     */
    public JbdTravelPointLog2012 getJbdTravelPointLog2012(final String logId);

    /**
     * Saves a jbdTravelPointLog2012's information
     * @param jbdTravelPointLog2012 the object to be saved
     */
    public void saveJbdTravelPointLog2012(JbdTravelPointLog2012 jbdTravelPointLog2012);

    /**
     * Removes a jbdTravelPointLog2012 from the database by logId
     * @param logId the jbdTravelPointLog2012's logId
     */
    public void removeJbdTravelPointLog2012(final String logId);
    //added for getJbdTravelPointLog2012sByCrm
    public List getJbdTravelPointLog2012sByCrm(CommonRecord crm, Pager pager);
}

