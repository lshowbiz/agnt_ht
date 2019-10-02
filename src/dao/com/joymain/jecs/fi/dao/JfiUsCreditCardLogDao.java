
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiUsCreditCardLogDao extends Dao {

    /**
     * Retrieves all of the jfiUsCreditCardLogs
     */
    public List getJfiUsCreditCardLogs(JfiUsCreditCardLog jfiUsCreditCardLog);

    /**
     * Gets jfiUsCreditCardLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param jucclId the jfiUsCreditCardLog's jucclId
     * @return jfiUsCreditCardLog populated jfiUsCreditCardLog object
     */
    public JfiUsCreditCardLog getJfiUsCreditCardLog(final Long jucclId);

    /**
     * Saves a jfiUsCreditCardLog's information
     * @param jfiUsCreditCardLog the object to be saved
     */    
    public void saveJfiUsCreditCardLog(JfiUsCreditCardLog jfiUsCreditCardLog);

    /**
     * Removes a jfiUsCreditCardLog from the database by jucclId
     * @param jucclId the jfiUsCreditCardLog's jucclId
     */
    public void removeJfiUsCreditCardLog(final Long jucclId);
    //added for getJfiUsCreditCardLogsByCrm
    public List getJfiUsCreditCardLogsByCrm(CommonRecord crm, Pager pager);
}

