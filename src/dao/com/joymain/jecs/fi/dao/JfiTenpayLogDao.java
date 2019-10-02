
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiTenpayLogDao extends Dao {

    /**
     * Retrieves all of the jfiTenpayLogs
     */
    public List getJfiTenpayLogs(JfiTenpayLog jfiTenpayLog);

    /**
     * Gets jfiTenpayLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiTenpayLog's logId
     * @return jfiTenpayLog populated jfiTenpayLog object
     */
    public JfiTenpayLog getJfiTenpayLog(final Long logId);

    /**
     * Saves a jfiTenpayLog's information
     * @param jfiTenpayLog the object to be saved
     */    
    public void saveJfiTenpayLog(JfiTenpayLog jfiTenpayLog);

    /**
     * Removes a jfiTenpayLog from the database by logId
     * @param logId the jfiTenpayLog's logId
     */
    public void removeJfiTenpayLog(final Long logId);
    //added for getJfiTenpayLogsByCrm
    public List getJfiTenpayLogsByCrm(CommonRecord crm, Pager pager);
}

