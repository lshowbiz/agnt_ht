
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiPayLogDao extends Dao {

    /**
     * Retrieves all of the jfiPayLogs
     */
    public List getJfiPayLogs(JfiPayLog jfiPayLog);

    /**
     * Gets jfiPayLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiPayLog's logId
     * @return jfiPayLog populated jfiPayLog object
     */
    public JfiPayLog getJfiPayLog(final Long logId);

    /**
     * Saves a jfiPayLog's information
     * @param jfiPayLog the object to be saved
     */    
    public void saveJfiPayLog(JfiPayLog jfiPayLog);

    /**
     * Removes a jfiPayLog from the database by logId
     * @param logId the jfiPayLog's logId
     */
    public void removeJfiPayLog(final Long logId);
    //added for getJfiPayLogsByCrm
    public List getJfiPayLogsByCrm(CommonRecord crm, Pager pager);
}

