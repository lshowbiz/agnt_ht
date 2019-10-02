
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.dao.SysLoginLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface SysLoginLogManager extends Manager {
	
	public SysLoginLog getLastLogByType(String userCode,String type);
    /**
     * Retrieves all of the sysLoginLogs
     */
    public List getSysLoginLogs(SysLoginLog sysLoginLog);

    /**
     * Gets sysLoginLog's information based on llId.
     * @param llId the sysLoginLog's llId
     * @return sysLoginLog populated sysLoginLog object
     */
    public SysLoginLog getSysLoginLog(final String llId);

    /**
     * Saves a sysLoginLog's information
     * @param sysLoginLog the object to be saved
     */
    public void saveSysLoginLog(SysLoginLog sysLoginLog);

    /**
     * Removes a sysLoginLog from the database by llId
     * @param llId the sysLoginLog's llId
     */
    public void removeSysLoginLog(final String llId);
    //added for getSysLoginLogsByCrm
    public List getSysLoginLogsByCrm(CommonRecord crm, Pager pager);
}

