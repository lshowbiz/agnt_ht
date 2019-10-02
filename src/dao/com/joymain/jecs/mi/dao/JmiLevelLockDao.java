
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiLevelLock;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiLevelLockDao extends Dao {

    /**
     * Retrieves all of the jmiLevelLocks
     */
    public List getJmiLevelLocks(JmiLevelLock jmiLevelLock);

    /**
     * Gets jmiLevelLock's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiLevelLock's id
     * @return jmiLevelLock populated jmiLevelLock object
     */
    public JmiLevelLock getJmiLevelLock(final Long id);

    /**
     * Saves a jmiLevelLock's information
     * @param jmiLevelLock the object to be saved
     */    
    public void saveJmiLevelLock(JmiLevelLock jmiLevelLock);

    /**
     * Removes a jmiLevelLock from the database by id
     * @param id the jmiLevelLock's id
     */
    public void removeJmiLevelLock(final Long id);
    //added for getJmiLevelLocksByCrm
    public List getJmiLevelLocksByCrm(CommonRecord crm, Pager pager);
    public JmiLevelLock getJmiLevelLockByUserCode(String userCode);
}

