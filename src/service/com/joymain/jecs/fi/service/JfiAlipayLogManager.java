
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.dao.JfiAlipayLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiAlipayLogManager extends Manager {
    /**
     * Retrieves all of the jfiAlipayLogs
     */
    public List getJfiAlipayLogs(JfiAlipayLog jfiAlipayLog);

    /**
     * Gets jfiAlipayLog's information based on logId.
     * @param logId the jfiAlipayLog's logId
     * @return jfiAlipayLog populated jfiAlipayLog object
     */
    public JfiAlipayLog getJfiAlipayLog(final String logId);

    /**
     * Saves a jfiAlipayLog's information
     * @param jfiAlipayLog the object to be saved
     */
    public void saveJfiAlipayLog(JfiAlipayLog jfiAlipayLog);

    /**
     * Removes a jfiAlipayLog from the database by logId
     * @param logId the jfiAlipayLog's logId
     */
    public void removeJfiAlipayLog(final String logId);
    //added for getJfiAlipayLogsByCrm
    public List getJfiAlipayLogsByCrm(CommonRecord crm, Pager pager);
}

