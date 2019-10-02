
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiChannelLogManager extends Manager {
    /**
     * Retrieves all of the jFiChannelLogs
     */
    public List getFiChannelLogs(FiChannelLog fiChannelLog);

    /**
     * Gets jFiChannelLog's information based on logId.
     * @param logId the jFiChannelLog's logId
     * @return jFiChannelLog populated jFiChannelLog object
     */
    public FiChannelLog getFiChannelLog(final String logId);

    /**
     * Saves a jFiChannelLog's information
     * @param jFiChannelLog the object to be saved
     */
    public void saveFiChannelLog(FiChannelLog fiChannelLog);

    /**
     * Removes a jFiChannelLog from the database by logId
     * @param logId the jFiChannelLog's logId
     */
    public void removeFiChannelLog(final String logId);
    //added for getFiChannelLogsByCrm
    public List getFiChannelLogsByCrm(CommonRecord crm, Pager pager);
}

