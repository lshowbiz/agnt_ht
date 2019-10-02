
package com.joymain.jecs.sys.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.UpsInteractiveLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface UpsInteractiveLogDao extends Dao {

    /**
     * Retrieves all of the upsInteractiveLogs
     */
    public List getUpsInteractiveLogs(UpsInteractiveLog upsInteractiveLog);

    /**
     * Gets upsInteractiveLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniId the upsInteractiveLog's uniId
     * @return upsInteractiveLog populated upsInteractiveLog object
     */
    public UpsInteractiveLog getUpsInteractiveLog(final Long uniId);

    /**
     * Saves a upsInteractiveLog's information
     * @param upsInteractiveLog the object to be saved
     */    
    public void saveUpsInteractiveLog(UpsInteractiveLog upsInteractiveLog);

    /**
     * Removes a upsInteractiveLog from the database by uniId
     * @param uniId the upsInteractiveLog's uniId
     */
    public void removeUpsInteractiveLog(final Long uniId);
    //added for getUpsInteractiveLogsByCrm
    public List getUpsInteractiveLogsByCrm(CommonRecord crm, Pager pager);
}

