
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiGetbillaccountLog;
import com.joymain.jecs.fi.dao.FiGetbillaccountLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiGetbillaccountLogManager extends Manager {
    /**
     * Retrieves all of the fiGetbillaccountLogs
     */
    public List getFiGetbillaccountLogs(FiGetbillaccountLog fiGetbillaccountLog);

    /**
     * Gets fiGetbillaccountLog's information based on logId.
     * @param logId the fiGetbillaccountLog's logId
     * @return fiGetbillaccountLog populated fiGetbillaccountLog object
     */
    public FiGetbillaccountLog getFiGetbillaccountLog(final String logId);

    /**
     * Saves a fiGetbillaccountLog's information
     * @param fiGetbillaccountLog the object to be saved
     */
    public void saveFiGetbillaccountLog(FiGetbillaccountLog fiGetbillaccountLog);

    /**
     * Removes a fiGetbillaccountLog from the database by logId
     * @param logId the fiGetbillaccountLog's logId
     */
    public void removeFiGetbillaccountLog(final String logId);
    //added for getFiGetbillaccountLogsByCrm
    public List getFiGetbillaccountLogsByCrm(CommonRecord crm, Pager pager);
}

