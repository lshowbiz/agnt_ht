
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.fi.dao.JfiYeepayLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiYeepayLogManager extends Manager {
    /**
     * Retrieves all of the jfiYeepayLogs
     */
    public List getJfiYeepayLogs(JfiYeepayLog jfiYeepayLog);

    /**
     * Gets jfiYeepayLog's information based on logId.
     * @param logId the jfiYeepayLog's logId
     * @return jfiYeepayLog populated jfiYeepayLog object
     */
    public JfiYeepayLog getJfiYeepayLog(final String logId);

    /**
     * Saves a jfiYeepayLog's information
     * @param jfiYeepayLog the object to be saved
     */
    public void saveJfiYeepayLog(JfiYeepayLog jfiYeepayLog);

    /**
     * Removes a jfiYeepayLog from the database by logId
     * @param logId the jfiYeepayLog's logId
     */
    public void removeJfiYeepayLog(final String logId);
    //added for getJfiYeepayLogsByCrm
    public List getJfiYeepayLogsByCrm(CommonRecord crm, Pager pager);
    
    public List getJfiYeepayLogsByDealId(String dealId);
}

