
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.mi.model.JmiLevelLock;
import com.joymain.jecs.mi.dao.JmiLevelLockDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiLevelLockManager extends Manager {
    /**
     * Retrieves all of the jmiLevelLocks
     */
    public List getJmiLevelLocks(JmiLevelLock jmiLevelLock);

    /**
     * Gets jmiLevelLock's information based on id.
     * @param id the jmiLevelLock's id
     * @return jmiLevelLock populated jmiLevelLock object
     */
    public JmiLevelLock getJmiLevelLock(final String id);

    /**
     * Saves a jmiLevelLock's information
     * @param jmiLevelLock the object to be saved
     */
    public void saveJmiLevelLock(JmiLevelLock jmiLevelLock);

    /**
     * Removes a jmiLevelLock from the database by id
     * @param id the jmiLevelLock's id
     */
    public void removeJmiLevelLock(final String id);
    //added for getJmiLevelLocksByCrm
    public List getJmiLevelLocksByCrm(CommonRecord crm, Pager pager);
    public JmiLevelLock getJmiLevelLockByUserCode(String userCode);
    public void saveJmiLevelLockAndMiMember(JmiLevelLock jmiLevelLock,SysUser defSysUser) ;
}

