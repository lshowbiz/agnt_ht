
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.dao.JfiUsCreditCardLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiUsCreditCardLogManager extends Manager {
    /**
     * Retrieves all of the jfiUsCreditCardLogs
     */
    public List getJfiUsCreditCardLogs(JfiUsCreditCardLog jfiUsCreditCardLog);

    /**
     * Gets jfiUsCreditCardLog's information based on jucclId.
     * @param jucclId the jfiUsCreditCardLog's jucclId
     * @return jfiUsCreditCardLog populated jfiUsCreditCardLog object
     */
    public JfiUsCreditCardLog getJfiUsCreditCardLog(final String jucclId);

    /**
     * Saves a jfiUsCreditCardLog's information
     * @param jfiUsCreditCardLog the object to be saved
     */
    public void saveJfiUsCreditCardLog(JfiUsCreditCardLog jfiUsCreditCardLog);

    /**
     * Removes a jfiUsCreditCardLog from the database by jucclId
     * @param jucclId the jfiUsCreditCardLog's jucclId
     */
    public void removeJfiUsCreditCardLog(final String jucclId);
    //added for getJfiUsCreditCardLogsByCrm
    public List getJfiUsCreditCardLogsByCrm(CommonRecord crm, Pager pager);
}

