
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.Jfi99billPosLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface Jfi99billPosLogDao extends Dao {

    /**
     * Retrieves all of the jfi99billPosLogs
     */
    public List getJfi99billPosLogs(Jfi99billPosLog jfi99billPosLog);

    /**
     * Gets jfi99billPosLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfi99billPosLog's logId
     * @return jfi99billPosLog populated jfi99billPosLog object
     */
    public Jfi99billPosLog getJfi99billPosLog(final long logId);

    /**
     * Saves a jfi99billPosLog's information
     * @param jfi99billPosLog the object to be saved
     */    
    public void saveJfi99billPosLog(Jfi99billPosLog jfi99billPosLog);

    /**
     * Removes a jfi99billPosLog from the database by logId
     * @param logId the jfi99billPosLog's logId
     */
    public void removeJfi99billPosLog(final long logId);
    //added for getJfi99billPosLogsByCrm
    public List getJfi99billPosLogsByCrm(CommonRecord crm, Pager pager);
}

