
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
@SuppressWarnings("unchecked")
public interface JfiChinapnrLogManager extends Manager {
    /**
     * Retrieves all of the jfiChinapnrLogs
     */
    public List getJfiChinapnrLogs(JfiChinapnrLog jfiChinapnrLog);

    /**
     * Gets jfiChinapnrLog's information based on logId.
     * @param logId the jfiChinapnrLog's logId
     * @return jfiChinapnrLog populated jfiChinapnrLog object
     */
    public JfiChinapnrLog getJfiChinapnrLog(final String logId);

    /**
     * Saves a jfiChinapnrLog's information
     * @param jfiChinapnrLog the object to be saved
     */
    public void saveJfiChinapnrLog(JfiChinapnrLog jfiChinapnrLog);

    /**
     * Removes a jfiChinapnrLog from the database by logId
     * @param logId the jfiChinapnrLog's logId
     */
    public void removeJfiChinapnrLog(final String logId);
    //added for getJfiChinapnrLogsByCrm
    public List getJfiChinapnrLogsByCrm(CommonRecord crm, Pager pager);
    
    
	public List getChinapnrLogsByMerId(String MerId) ;
}

