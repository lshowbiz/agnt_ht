
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiMemberLogDao extends Dao {

    /**
     * Retrieves all of the jmiMemberLogs
     */
    public List getJmiMemberLogs(JmiMemberLog jmiMemberLog);

    /**
     * Gets jmiMemberLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jmiMemberLog's logId
     * @return jmiMemberLog populated jmiMemberLog object
     */
    public JmiMemberLog getJmiMemberLog(final String logId);

    /**
     * Saves a jmiMemberLog's information
     * @param jmiMemberLog the object to be saved
     */    
    public void saveJmiMemberLog(JmiMemberLog jmiMemberLog);

    /**
     * Removes a jmiMemberLog from the database by logId
     * @param logId the jmiMemberLog's logId
     */
    public void removeJmiMemberLog(final String logId);
    //added for getJmiMemberLogsByCrm
    public List getJmiMemberLogsByCrm(CommonRecord crm, Pager pager);
}

