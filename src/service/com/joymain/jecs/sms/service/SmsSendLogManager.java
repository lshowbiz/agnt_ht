
package com.joymain.jecs.sms.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sms.model.SmsSendLog;
import com.joymain.jecs.sms.dao.SmsSendLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface SmsSendLogManager extends Manager {
    /**
     * Retrieves all of the smsSendLogs
     */
    public List getSmsSendLogs(SmsSendLog smsSendLog);

    /**
     * Gets smsSendLog's information based on sslId.
     * @param sslId the smsSendLog's sslId
     * @return smsSendLog populated smsSendLog object
     */
    public SmsSendLog getSmsSendLog(final String sslId);

    /**
     * Saves a smsSendLog's information
     * @param smsSendLog the object to be saved
     */
    public void saveSmsSendLog(SmsSendLog smsSendLog);

    /**
     * Removes a smsSendLog from the database by sslId
     * @param sslId the smsSendLog's sslId
     */
    public void removeSmsSendLog(final String sslId);
    //added for getSmsSendLogsByCrm
    public List getSmsSendLogsByCrm(CommonRecord crm, Pager pager);
}

