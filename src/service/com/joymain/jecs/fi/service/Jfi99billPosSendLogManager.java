
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.Jfi99billPosSendLog;
import com.joymain.jecs.fi.dao.Jfi99billPosSendLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface Jfi99billPosSendLogManager extends Manager {
    /**
     * Retrieves all of the jfi99billPosSendLogs
     */
    public List getJfi99billPosSendLogs(Jfi99billPosSendLog jfi99billPosSendLog);

    /**
     * Gets jfi99billPosSendLog's information based on logId.
     * @param logId the jfi99billPosSendLog's logId
     * @return jfi99billPosSendLog populated jfi99billPosSendLog object
     */
    public Jfi99billPosSendLog getJfi99billPosSendLog(final String logId);

    /**
     * Saves a jfi99billPosSendLog's information
     * @param jfi99billPosSendLog the object to be saved
     */
    public void saveJfi99billPosSendLog(Jfi99billPosSendLog jfi99billPosSendLog);

    /**
     * Removes a jfi99billPosSendLog from the database by logId
     * @param logId the jfi99billPosSendLog's logId
     */
    public void removeJfi99billPosSendLog(final String logId);
    //added for getJfi99billPosSendLogsByCrm
    public List getJfi99billPosSendLogsByCrm(CommonRecord crm, Pager pager);
}

