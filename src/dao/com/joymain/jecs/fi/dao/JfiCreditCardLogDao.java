
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiCreditCardLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiCreditCardLogDao extends Dao {

    /**
     * Retrieves all of the jfiCreditCardLogs
     */
    public List getJfiCreditCardLogs(JfiCreditCardLog jfiCreditCardLog);

    /**
     * Gets jfiCreditCardLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiCreditCardLog's logId
     * @return jfiCreditCardLog populated jfiCreditCardLog object
     */
    public JfiCreditCardLog getJfiCreditCardLog(final Long logId);

    /**
     * Saves a jfiCreditCardLog's information
     * @param jfiCreditCardLog the object to be saved
     */    
    public void saveJfiCreditCardLog(JfiCreditCardLog jfiCreditCardLog);

    /**
     * Removes a jfiCreditCardLog from the database by logId
     * @param logId the jfiCreditCardLog's logId
     */
    public void removeJfiCreditCardLog(final Long logId);
    //added for getJfiCreditCardLogsByCrm
    public List getJfiCreditCardLogsByCrm(CommonRecord crm, Pager pager);
}

