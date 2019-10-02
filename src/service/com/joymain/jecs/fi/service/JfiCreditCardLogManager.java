
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiCreditCardLog;
import com.joymain.jecs.fi.dao.JfiCreditCardLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiCreditCardLogManager extends Manager {
    /**
     * Retrieves all of the jfiCreditCardLogs
     */
    public List getJfiCreditCardLogs(JfiCreditCardLog jfiCreditCardLog);

    /**
     * Gets jfiCreditCardLog's information based on logId.
     * @param logId the jfiCreditCardLog's logId
     * @return jfiCreditCardLog populated jfiCreditCardLog object
     */
    public JfiCreditCardLog getJfiCreditCardLog(final String logId);

    /**
     * Saves a jfiCreditCardLog's information
     * @param jfiCreditCardLog the object to be saved
     */
    public void saveJfiCreditCardLog(JfiCreditCardLog jfiCreditCardLog);

    /**
     * Removes a jfiCreditCardLog from the database by logId
     * @param logId the jfiCreditCardLog's logId
     */
    public void removeJfiCreditCardLog(final String logId);
    //added for getJfiCreditCardLogsByCrm
    public List getJfiCreditCardLogsByCrm(CommonRecord crm, Pager pager);
}

