
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface Jfi99billmsLogDao extends Dao {

    /**
     * Retrieves all of the jfi99billmsLogs
     */
    public List getJfi99billmsLogs(Jfi99billmsLog jfi99billmsLog);

    /**
     * Gets jfi99billmsLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfi99billmsLog's logId
     * @return jfi99billmsLog populated jfi99billmsLog object
     */
    public Jfi99billmsLog getJfi99billmsLog(final Long logId);

    /**
     * Saves a jfi99billmsLog's information
     * @param jfi99billmsLog the object to be saved
     */    
    public void saveJfi99billmsLog(Jfi99billmsLog jfi99billmsLog);

    /**
     * Removes a jfi99billmsLog from the database by logId
     * @param logId the jfi99billmsLog's logId
     */
    public void removeJfi99billmsLog(final Long logId);
    //added for getJfi99billmsLogsByCrm
    public List getJfi99billmsLogsByCrm(CommonRecord crm, Pager pager);
}

