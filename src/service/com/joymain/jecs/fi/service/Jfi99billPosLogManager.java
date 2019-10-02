
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.fi.model.Jfi99billPosLog;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface Jfi99billPosLogManager extends Manager {
    /**
     * Retrieves all of the jfi99billPosLogs
     */
    public List getJfi99billPosLogs(Jfi99billPosLog jfi99billPosLog);

    /**
     * Gets jfi99billPosLog's information based on logId.
     * @param logId the jfi99billPosLog's logId
     * @return jfi99billPosLog populated jfi99billPosLog object
     */
    public Jfi99billPosLog getJfi99billPosLog(final String logId);

    /**
     * Saves a jfi99billPosLog's information
     * @param jfi99billPosLog the object to be saved
     */
    public void saveJfi99billPosLog(Jfi99billPosLog jfi99billPosLog);
    
    /**
     * POS支付进电子存折
     * @param jfi99billPosLog
     * @param sysUser
     */
    public void saveJfi99billPosLogAndBankAccount(Jfi99billPosLog jfi99billPosLog, SysUser sysUser);

    /**
     * Removes a jfi99billPosLog from the database by logId
     * @param logId the jfi99billPosLog's logId
     */
    public void removeJfi99billPosLog(final String logId);
    //added for getJfi99billPosLogsByCrm
    public List getJfi99billPosLogsByCrm(CommonRecord crm, Pager pager);
}

