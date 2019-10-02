
package com.joymain.jecs.fi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.dao.Jfi99billLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface Jfi99billLogManager extends Manager {
    /**
     * Retrieves all of the jfi99billLogs
     */
    public List getJfi99billLogs(Jfi99billLog jfi99billLog);

    /**
     * Gets jfi99billLog's information based on logId.
     * @param logId the jfi99billLog's logId
     * @return jfi99billLog populated jfi99billLog object
     */
    public Jfi99billLog getJfi99billLog(final String logId);

    /**
     * Saves a jfi99billLog's information
     * @param jfi99billLog the object to be saved
     */
    public void saveJfi99billLog(Jfi99billLog jfi99billLog);

    /**
     * Removes a jfi99billLog from the database by logId
     * @param logId the jfi99billLog's logId
     */
    public void removeJfi99billLog(final String logId);
    //added for getJfi99billLogsByCrm
    public List getJfi99billLogsByCrm(CommonRecord crm, Pager pager);
    
    public List getSuccessJfi99billLogsByConditions(String dealId);
}

