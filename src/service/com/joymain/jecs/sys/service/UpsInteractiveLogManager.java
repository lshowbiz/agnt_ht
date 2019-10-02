
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.UpsInteractiveLog;
import com.joymain.jecs.sys.dao.UpsInteractiveLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface UpsInteractiveLogManager extends Manager {
    /**
     * Retrieves all of the upsInteractiveLogs
     */
    public List getUpsInteractiveLogs(UpsInteractiveLog upsInteractiveLog);

    /**
     * Gets upsInteractiveLog's information based on uniId.
     * @param uniId the upsInteractiveLog's uniId
     * @return upsInteractiveLog populated upsInteractiveLog object
     */
    public UpsInteractiveLog getUpsInteractiveLog(final String uniId);

    /**
     * Saves a upsInteractiveLog's information
     * @param upsInteractiveLog the object to be saved
     */
    public void saveUpsInteractiveLog(UpsInteractiveLog upsInteractiveLog);

    /**
     * Removes a upsInteractiveLog from the database by uniId
     * @param uniId the upsInteractiveLog's uniId
     */
    public void removeUpsInteractiveLog(final String uniId);
    //added for getUpsInteractiveLogsByCrm
    public List getUpsInteractiveLogsByCrm(CommonRecord crm, Pager pager);
}

