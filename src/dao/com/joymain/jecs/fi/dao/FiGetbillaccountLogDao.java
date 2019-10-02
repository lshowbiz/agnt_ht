
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiGetbillaccountLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiGetbillaccountLogDao extends Dao {

    /**
     * Retrieves all of the fiGetbillaccountLogs
     */
    public List getFiGetbillaccountLogs(FiGetbillaccountLog fiGetbillaccountLog);

    /**
     * Gets fiGetbillaccountLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the fiGetbillaccountLog's logId
     * @return fiGetbillaccountLog populated fiGetbillaccountLog object
     */
    public FiGetbillaccountLog getFiGetbillaccountLog(final Long logId);

    /**
     * Saves a fiGetbillaccountLog's information
     * @param fiGetbillaccountLog the object to be saved
     */    
    public void saveFiGetbillaccountLog(FiGetbillaccountLog fiGetbillaccountLog);

    /**
     * Removes a fiGetbillaccountLog from the database by logId
     * @param logId the fiGetbillaccountLog's logId
     */
    public void removeFiGetbillaccountLog(final Long logId);
    //added for getFiGetbillaccountLogsByCrm
    public List getFiGetbillaccountLogsByCrm(CommonRecord crm, Pager pager);
}

