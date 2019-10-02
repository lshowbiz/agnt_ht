
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.fi.dao.JfiChanjetLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiChanjetLogManager extends Manager {
    /**
     * Retrieves all of the jfiChanjetLogs
     */
    public List getJfiChanjetLogs(JfiChanjetLog jfiChanjetLog);

    /**
     * Gets jfiChanjetLog's information based on logId.
     * @param logId the jfiChanjetLog's logId
     * @return jfiChanjetLog populated jfiChanjetLog object
     */
    public JfiChanjetLog getJfiChanjetLog(final String logId);

    /**
     * Saves a jfiChanjetLog's information
     * @param jfiChanjetLog the object to be saved
     */
    public void saveJfiChanjetLog(JfiChanjetLog jfiChanjetLog);

    /**
     * Removes a jfiChanjetLog from the database by logId
     * @param logId the jfiChanjetLog's logId
     */
    public void removeJfiChanjetLog(final String logId);
    //added for getJfiChanjetLogsByCrm
    public List getJfiChanjetLogsByCrm(CommonRecord crm, Pager pager);
    
    public List getJfiChanjetLogsByDealId(String dealId);
}

