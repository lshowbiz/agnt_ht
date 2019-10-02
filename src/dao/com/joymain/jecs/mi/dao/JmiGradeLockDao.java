
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiGradeLock;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiGradeLockDao extends Dao {

    /**
     * Retrieves all of the jmiGradeLocks
     */
    public List getJmiGradeLocks(JmiGradeLock jmiGradeLock);

    /**
     * Gets jmiGradeLock's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiGradeLock's id
     * @return jmiGradeLock populated jmiGradeLock object
     */
    public JmiGradeLock getJmiGradeLock(final Long id);

    /**
     * Saves a jmiGradeLock's information
     * @param jmiGradeLock the object to be saved
     */    
    public void saveJmiGradeLock(JmiGradeLock jmiGradeLock);

    /**
     * Removes a jmiGradeLock from the database by id
     * @param id the jmiGradeLock's id
     */
    public void removeJmiGradeLock(final Long id);
    //added for getJmiGradeLocksByCrm
    public List getJmiGradeLocksByCrm(CommonRecord crm, Pager pager);
    public JmiGradeLock getJmiGradeLockByUserCode(String userCode, Integer validWeek);
}

