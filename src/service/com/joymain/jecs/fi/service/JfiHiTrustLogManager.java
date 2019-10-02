
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.fi.dao.JfiHiTrustLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiHiTrustLogManager extends Manager {
    /**
     * Retrieves all of the jfiHiTrustLogs
     */
    public List getJfiHiTrustLogs(JfiHiTrustLog jfiHiTrustLog);

    /**
     * Gets jfiHiTrustLog's information based on logId.
     * @param logId the jfiHiTrustLog's logId
     * @return jfiHiTrustLog populated jfiHiTrustLog object
     */
    public JfiHiTrustLog getJfiHiTrustLog(final String logId);

    /**
     * Saves a jfiHiTrustLog's information
     * @param jfiHiTrustLog the object to be saved
     */
    public void saveJfiHiTrustLog(JfiHiTrustLog jfiHiTrustLog);

    /**
     * Removes a jfiHiTrustLog from the database by logId
     * @param logId the jfiHiTrustLog's logId
     */
    public void removeJfiHiTrustLog(final String logId);
    //added for getJfiHiTrustLogsByCrm
    public List getJfiHiTrustLogsByCrm(CommonRecord crm, Pager pager);
}

