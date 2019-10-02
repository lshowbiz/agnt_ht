
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiHiTrustLogDao extends Dao {

    /**
     * Retrieves all of the jfiHiTrustLogs
     */
    public List getJfiHiTrustLogs(JfiHiTrustLog jfiHiTrustLog);

    /**
     * Gets jfiHiTrustLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiHiTrustLog's logId
     * @return jfiHiTrustLog populated jfiHiTrustLog object
     */
    public JfiHiTrustLog getJfiHiTrustLog(final Long logId);

    /**
     * Saves a jfiHiTrustLog's information
     * @param jfiHiTrustLog the object to be saved
     */    
    public void saveJfiHiTrustLog(JfiHiTrustLog jfiHiTrustLog);

    /**
     * Removes a jfiHiTrustLog from the database by logId
     * @param logId the jfiHiTrustLog's logId
     */
    public void removeJfiHiTrustLog(final Long logId);
    //added for getJfiHiTrustLogsByCrm
    public List getJfiHiTrustLogsByCrm(CommonRecord crm, Pager pager);
}

