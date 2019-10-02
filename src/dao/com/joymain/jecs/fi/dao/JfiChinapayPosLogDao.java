
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiChinapayPosLogDao extends Dao {

    /**
     * Retrieves all of the jfiChinapayPosLogs
     */
    public List getJfiChinapayPosLogs(JfiChinapayPosLog jfiChinapayPosLog);

    /**
     * Gets jfiChinapayPosLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiChinapayPosLog's logId
     * @return jfiChinapayPosLog populated jfiChinapayPosLog object
     */
    public JfiChinapayPosLog getJfiChinapayPosLog(final Long logId);

    /**
     * Saves a jfiChinapayPosLog's information
     * @param jfiChinapayPosLog the object to be saved
     */    
    public void saveJfiChinapayPosLog(JfiChinapayPosLog jfiChinapayPosLog);

    /**
     * Removes a jfiChinapayPosLog from the database by logId
     * @param logId the jfiChinapayPosLog's logId
     */
    public void removeJfiChinapayPosLog(final Long logId);
    //added for getJfiChinapayPosLogsByCrm
    public List getJfiChinapayPosLogsByCrm(CommonRecord crm, Pager pager);
}

