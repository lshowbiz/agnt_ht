
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiYeepayLogDao extends Dao {

    /**
     * Retrieves all of the jfiYeepayLogs
     */
    public List getJfiYeepayLogs(JfiYeepayLog jfiYeepayLog);

    /**
     * Gets jfiYeepayLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiYeepayLog's logId
     * @return jfiYeepayLog populated jfiYeepayLog object
     */
    public JfiYeepayLog getJfiYeepayLog(final Long logId);

    /**
     * Saves a jfiYeepayLog's information
     * @param jfiYeepayLog the object to be saved
     */    
    public void saveJfiYeepayLog(JfiYeepayLog jfiYeepayLog);

    /**
     * Removes a jfiYeepayLog from the database by logId
     * @param logId the jfiYeepayLog's logId
     */
    public void removeJfiYeepayLog(final Long logId);
    //added for getJfiYeepayLogsByCrm
    public List getJfiYeepayLogsByCrm(CommonRecord crm, Pager pager);
    
    public List getJfiChanjetLogsByDealId(String dealId);
}

