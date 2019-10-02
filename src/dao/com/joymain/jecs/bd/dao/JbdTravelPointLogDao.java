
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointLogDao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointLogs
     */
    public List getJbdTravelPointLogs(JbdTravelPointLog jbdTravelPointLog);

    /**
     * Gets jbdTravelPointLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jbdTravelPointLog's logId
     * @return jbdTravelPointLog populated jbdTravelPointLog object
     */
    public JbdTravelPointLog getJbdTravelPointLog(final Long logId);

    /**
     * Saves a jbdTravelPointLog's information
     * @param jbdTravelPointLog the object to be saved
     */    
    public void saveJbdTravelPointLog(JbdTravelPointLog jbdTravelPointLog);

    /**
     * Removes a jbdTravelPointLog from the database by logId
     * @param logId the jbdTravelPointLog's logId
     */
    public void removeJbdTravelPointLog(final Long logId);
    //added for getJbdTravelPointLogsByCrm
    public List getJbdTravelPointLogsByCrm(CommonRecord crm, Pager pager);
}

