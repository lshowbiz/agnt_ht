
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.mi.dao.JmiMemberLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiMemberLogManager extends Manager {
    /**
     * Retrieves all of the jmiMemberLogs
     */
    public List getJmiMemberLogs(JmiMemberLog jmiMemberLog);

    /**
     * Gets jmiMemberLog's information based on logId.
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

