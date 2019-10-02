
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdCaculateLog;
import com.joymain.jecs.bd.dao.JbdCaculateLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdCaculateLogManager extends Manager {
    /**
     * Retrieves all of the jbdCaculateLogs
     */
    public List getJbdCaculateLogs(JbdCaculateLog jbdCaculateLog);

    /**
     * Gets jbdCaculateLog's information based on id.
     * @param id the jbdCaculateLog's id
     * @return jbdCaculateLog populated jbdCaculateLog object
     */
    public JbdCaculateLog getJbdCaculateLog(final String id);

    /**
     * Saves a jbdCaculateLog's information
     * @param jbdCaculateLog the object to be saved
     */
    public void saveJbdCaculateLog(JbdCaculateLog jbdCaculateLog);

    /**
     * Removes a jbdCaculateLog from the database by id
     * @param id the jbdCaculateLog's id
     */
    public void removeJbdCaculateLog(final String id);
    //added for getJbdCaculateLogsByCrm
    public List getJbdCaculateLogsByCrm(CommonRecord crm, Pager pager);
}

