
package com.joymain.jecs.sms.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sms.model.SmsSendLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SmsSendLogDao extends Dao {

    /**
     * Retrieves all of the smsSendLogs
     */
    public List getSmsSendLogs(SmsSendLog smsSendLog);

    /**
     * Gets smsSendLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param sslId the smsSendLog's sslId
     * @return smsSendLog populated smsSendLog object
     */
    public SmsSendLog getSmsSendLog(final Long sslId);

    /**
     * Saves a smsSendLog's information
     * @param smsSendLog the object to be saved
     */    
    public void saveSmsSendLog(SmsSendLog smsSendLog);

    /**
     * Removes a smsSendLog from the database by sslId
     * @param sslId the smsSendLog's sslId
     */
    public void removeSmsSendLog(final Long sslId);
    //added for getSmsSendLogsByCrm
    public List getSmsSendLogsByCrm(CommonRecord crm, Pager pager);
}

