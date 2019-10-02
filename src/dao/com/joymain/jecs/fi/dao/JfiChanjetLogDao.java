
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiChanjetLogDao extends Dao {

    /**
     * Retrieves all of the jfiChanjetLogs
     */
    public List getJfiChanjetLogs(JfiChanjetLog jfiChanjetLog);

    /**
     * Gets jfiChanjetLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiChanjetLog's logId
     * @return jfiChanjetLog populated jfiChanjetLog object
     */
    public JfiChanjetLog getJfiChanjetLog(final Long logId);

    /**
     * Saves a jfiChanjetLog's information
     * @param jfiChanjetLog the object to be saved
     */    
    public void saveJfiChanjetLog(JfiChanjetLog jfiChanjetLog);

    /**
     * Removes a jfiChanjetLog from the database by logId
     * @param logId the jfiChanjetLog's logId
     */
    public void removeJfiChanjetLog(final Long logId);
    //added for getJfiChanjetLogsByCrm
    public List getJfiChanjetLogsByCrm(CommonRecord crm, Pager pager);
    
    public List getJfiChanjetLogsByDealId(String dealId);
}

