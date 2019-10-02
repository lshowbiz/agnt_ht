
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiStateLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiStateLogDao extends Dao {

    /**
     * Retrieves all of the jmiStateLogs
     */
    public List getJmiStateLogs(JmiStateLog jmiStateLog);

    /**
     * Gets jmiStateLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiStateLog's id
     * @return jmiStateLog populated jmiStateLog object
     */
    public JmiStateLog getJmiStateLog(final BigDecimal id);

    /**
     * Saves a jmiStateLog's information
     * @param jmiStateLog the object to be saved
     */    
    public void saveJmiStateLog(JmiStateLog jmiStateLog);

    /**
     * Removes a jmiStateLog from the database by id
     * @param id the jmiStateLog's id
     */
    public void removeJmiStateLog(final BigDecimal id);
    //added for getJmiStateLogsByCrm
    public List getJmiStateLogsByCrm(CommonRecord crm, Pager pager);
}

