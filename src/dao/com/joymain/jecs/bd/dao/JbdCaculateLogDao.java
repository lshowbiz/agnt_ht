
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdCaculateLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdCaculateLogDao extends Dao {

    /**
     * Retrieves all of the jbdCaculateLogs
     */
    public List getJbdCaculateLogs(JbdCaculateLog jbdCaculateLog);

    /**
     * Gets jbdCaculateLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdCaculateLog's id
     * @return jbdCaculateLog populated jbdCaculateLog object
     */
    public JbdCaculateLog getJbdCaculateLog(final BigDecimal id);

    /**
     * Saves a jbdCaculateLog's information
     * @param jbdCaculateLog the object to be saved
     */    
    public void saveJbdCaculateLog(JbdCaculateLog jbdCaculateLog);

    /**
     * Removes a jbdCaculateLog from the database by id
     * @param id the jbdCaculateLog's id
     */
    public void removeJbdCaculateLog(final BigDecimal id);
    //added for getJbdCaculateLogsByCrm
    public List getJbdCaculateLogsByCrm(CommonRecord crm, Pager pager);
}

