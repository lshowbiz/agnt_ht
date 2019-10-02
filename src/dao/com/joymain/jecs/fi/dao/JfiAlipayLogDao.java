
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiAlipayLogDao extends Dao {

    /**
     * Retrieves all of the jfiAlipayLogs
     */
    public List getJfiAlipayLogs(JfiAlipayLog jfiAlipayLog);

    /**
     * Gets jfiAlipayLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jfiAlipayLog's logId
     * @return jfiAlipayLog populated jfiAlipayLog object
     */
    public JfiAlipayLog getJfiAlipayLog(final Long logId);

    /**
     * Saves a jfiAlipayLog's information
     * @param jfiAlipayLog the object to be saved
     */    
    public void saveJfiAlipayLog(JfiAlipayLog jfiAlipayLog);

    /**
     * Removes a jfiAlipayLog from the database by logId
     * @param logId the jfiAlipayLog's logId
     */
    public void removeJfiAlipayLog(final Long logId);
    //added for getJfiAlipayLogsByCrm
    public List getJfiAlipayLogsByCrm(CommonRecord crm, Pager pager);
}

