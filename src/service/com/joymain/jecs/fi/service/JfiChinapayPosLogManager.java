
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.fi.dao.JfiChinapayPosLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiChinapayPosLogManager extends Manager {
    /**
     * Retrieves all of the jfiChinapayPosLogs
     */
    public List getJfiChinapayPosLogs(JfiChinapayPosLog jfiChinapayPosLog);

    /**
     * Gets jfiChinapayPosLog's information based on logId.
     * @param logId the jfiChinapayPosLog's logId
     * @return jfiChinapayPosLog populated jfiChinapayPosLog object
     */
    public JfiChinapayPosLog getJfiChinapayPosLog(final String logId);

    /**
     * Saves a jfiChinapayPosLog's information
     * @param jfiChinapayPosLog the object to be saved
     */
    public void saveJfiChinapayPosLog(JfiChinapayPosLog jfiChinapayPosLog);

    /**
     * Removes a jfiChinapayPosLog from the database by logId
     * @param logId the jfiChinapayPosLog's logId
     */
    public void removeJfiChinapayPosLog(final String logId);
    //added for getJfiChinapayPosLogsByCrm
    public List getJfiChinapayPosLogsByCrm(CommonRecord crm, Pager pager);
    
    public void saveJfiPayData(JfiChinapayPosLog jfiChinapayPosLog) throws Exception;
}

