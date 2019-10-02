
package com.joymain.jecs.fi.dao;

import java.util.List;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings("unchecked")
public interface JfiChinapnrLogDao extends Dao {

    /**
     * Retrieves all of the jfiChinapnrLogs
     */
    public List getJfiChinapnrLogs(JfiChinapnrLog jfiChinapnrLog);
    
    public List getChinapnrLogsByMerId(String MerId) ;

    /**
     * Gets jfiChinapnrLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiChinapnrLog's logId
     * @return jfiChinapnrLog populated jfiChinapnrLog object
     */
    public JfiChinapnrLog getJfiChinapnrLog(final Long logId);

    /**
     * Saves a jfiChinapnrLog's information
     * @param jfiChinapnrLog the object to be saved
     */    
    public void saveJfiChinapnrLog(JfiChinapnrLog jfiChinapnrLog);

    /**
     * Removes a jfiChinapnrLog from the database by logId
     * @param logId the jfiChinapnrLog's logId
     */
    public void removeJfiChinapnrLog(final Long logId);
    //added for getJfiChinapnrLogsByCrm
    public List getJfiChinapnrLogsByCrm(CommonRecord crm, Pager pager);
}

