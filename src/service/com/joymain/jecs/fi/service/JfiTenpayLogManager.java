
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.dao.JfiTenpayLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiTenpayLogManager extends Manager {
    /**
     * Retrieves all of the jfiTenpayLogs
     */
    public List getJfiTenpayLogs(JfiTenpayLog jfiTenpayLog);

    /**
     * Gets jfiTenpayLog's information based on logId.
     * @param logId the jfiTenpayLog's logId
     * @return jfiTenpayLog populated jfiTenpayLog object
     */
    public JfiTenpayLog getJfiTenpayLog(final String logId);

    /**
     * Saves a jfiTenpayLog's information
     * @param jfiTenpayLog the object to be saved
     */
    public void saveJfiTenpayLog(JfiTenpayLog jfiTenpayLog);

    /**
     * Removes a jfiTenpayLog from the database by logId
     * @param logId the jfiTenpayLog's logId
     */
    public void removeJfiTenpayLog(final String logId);
    //added for getJfiTenpayLogsByCrm
    public List getJfiTenpayLogsByCrm(CommonRecord crm, Pager pager);
}

