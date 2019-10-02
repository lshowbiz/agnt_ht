
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.mi.model.JmiGradeLock;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiGradeLockManager extends Manager {
    /**
     * Retrieves all of the jmiGradeLocks
     */
    public List getJmiGradeLocks(JmiGradeLock jmiGradeLock);

    /**
     * Gets jmiGradeLock's information based on id.
     * @param id the jmiGradeLock's id
     * @return jmiGradeLock populated jmiGradeLock object
     */
    public JmiGradeLock getJmiGradeLock(final String id);

    /**
     * Saves a jmiGradeLock's information
     * @param jmiGradeLock the object to be saved
     */
    public void saveJmiGradeLock(JmiGradeLock jmiGradeLock,SysUser defSysUser);

    /**
     * Removes a jmiGradeLock from the database by id
     * @param id the jmiGradeLock's id
     */
    public void removeJmiGradeLock(final String id);
    //added for getJmiGradeLocksByCrm
    public List getJmiGradeLocksByCrm(CommonRecord crm, Pager pager);
    public JmiGradeLock getJmiGradeLockByUserCode(String userCode, Integer validWeek);
    
    /**
     * @Description:	批量修改会员身份
     * @author:			侯忻宇
     * @date:		    2016-10-10
     * @param jmiGradeLockList
     * @param defSysUser:
     * @exception:
     * @return:
     */
    public void saveJmiGradeLockList( List<JmiGradeLock> jmiGradeLockList,SysUser defSysUser);
}

