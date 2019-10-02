
package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiChannelLogDao extends Dao {

    /**
     * Retrieves all of the FiChannelLogs
     */
    public List getFiChannelLogs(FiChannelLog fiChannelLog);

    /**
     * Gets fiChannelLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the fiChannelLog's logId
     * @return fiChannelLog populated fiChannelLog object
     */
    public FiChannelLog getFiChannelLog(final Long logId);

    /**
     * Saves a FiChannelLog's information
     * @param FiChannelLog the object to be saved
     */    
    public void saveFiChannelLog(FiChannelLog fiChannelLog);

    /**
     * Removes a fiChannelLog from the database by logId
     * @param logId the fiChannelLog's logId
     */
    public void removeFiChannelLog(final Long logId);
    //added for getFiChannelLogsByCrm
    public List getFiChannelLogsByCrm(CommonRecord crm, Pager pager);
}

