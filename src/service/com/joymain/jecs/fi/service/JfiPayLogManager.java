
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.dao.JfiPayLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiPayLogManager extends Manager {
    /**
     * Retrieves all of the jfiPayLogs
     */
    public List getJfiPayLogs(JfiPayLog jfiPayLog);

    /**
     * Gets jfiPayLog's information based on logId.
     * @param logId the jfiPayLog's logId
     * @return jfiPayLog populated jfiPayLog object
     */
    public JfiPayLog getJfiPayLog(final String logId);

    /**
     * Saves a jfiPayLog's information
     * @param jfiPayLog the object to be saved
     */
    public void saveJfiPayLog(JfiPayLog jfiPayLog);

    /**
     * Removes a jfiPayLog from the database by logId
     * @param logId the jfiPayLog's logId
     */
    public void removeJfiPayLog(final String logId);
    //added for getJfiPayLogsByCrm
    public List getJfiPayLogsByCrm(CommonRecord crm, Pager pager);
}

