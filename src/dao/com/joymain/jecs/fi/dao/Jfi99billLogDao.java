
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface Jfi99billLogDao extends Dao {

    /**
     * Retrieves all of the jfi99billLogs
     */
    public List getJfi99billLogs(Jfi99billLog jfi99billLog);

    /**
     * Gets jfi99billLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfi99billLog's logId
     * @return jfi99billLog populated jfi99billLog object
     */
    public Jfi99billLog getJfi99billLog(final Long logId);

    /**
     * Saves a jfi99billLog's information
     * @param jfi99billLog the object to be saved
     */    
    public void saveJfi99billLog(Jfi99billLog jfi99billLog);

    /**
     * Removes a jfi99billLog from the database by logId
     * @param logId the jfi99billLog's logId
     */
    public void removeJfi99billLog(final Long logId);
    //added for getJfi99billLogsByCrm
    public List getJfi99billLogsByCrm(CommonRecord crm, Pager pager);
    
    public List getSuccessJfi99billLogsByConditions(String dealId);
}

