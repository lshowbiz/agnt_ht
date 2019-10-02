
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiReapalLogDao extends Dao {

    /**
     * Retrieves all of the jfiReapalLogs
     */
    public List getJfiReapalLogs(JfiReapalLog jfiReapalLog);

    /**
     * Gets jfiReapalLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiReapalLog's logId
     * @return jfiReapalLog populated jfiReapalLog object
     */
    public JfiReapalLog getJfiReapalLog(final Long logId);

    /**
     * Saves a jfiReapalLog's information
     * @param jfiReapalLog the object to be saved
     */    
    public void saveJfiReapalLog(JfiReapalLog jfiReapalLog);

    /**
     * Removes a jfiReapalLog from the database by logId
     * @param logId the jfiReapalLog's logId
     */
    public void removeJfiReapalLog(final Long logId);
    //added for getJfiReapalLogsByCrm
    public List getJfiReapalLogsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * Modify By WuCF 20150923 判断
     * @param dealId
     * @return
     */
    public List getJfiReapalLogsByDealId(String dealId);
}

