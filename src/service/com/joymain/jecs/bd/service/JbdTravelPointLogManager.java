
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdTravelPointLog;
import com.joymain.jecs.bd.dao.JbdTravelPointLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdTravelPointLogManager extends Manager {
    /**
     * Retrieves all of the jbdTravelPointLogs
     */
    public List getJbdTravelPointLogs(JbdTravelPointLog jbdTravelPointLog);

    /**
     * Gets jbdTravelPointLog's information based on logId.
     * @param logId the jbdTravelPointLog's logId
     * @return jbdTravelPointLog populated jbdTravelPointLog object
     */
    public JbdTravelPointLog getJbdTravelPointLog(final String logId);

    /**
     * Saves a jbdTravelPointLog's information
     * @param jbdTravelPointLog the object to be saved
     */
    public void saveJbdTravelPointLog(JbdTravelPointLog jbdTravelPointLog);

    /**
     * Removes a jbdTravelPointLog from the database by logId
     * @param logId the jbdTravelPointLog's logId
     */
    public void removeJbdTravelPointLog(final String logId);
    //added for getJbdTravelPointLogsByCrm
    public List getJbdTravelPointLogsByCrm(CommonRecord crm, Pager pager);
}

