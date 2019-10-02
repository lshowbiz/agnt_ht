
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysClickLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysClickLogManager extends Manager {
    /**
     * Retrieves all of the sysClickLogs
     */
    public List getSysClickLogs(SysClickLog sysClickLog);

    /**
     * Gets sysClickLog's information based on logId.
     * @param logId the sysClickLog's logId
     * @return sysClickLog populated sysClickLog object
     */
    public SysClickLog getSysClickLog(final String logId);

    /**
     * Saves a sysClickLog's information
     * @param sysClickLog the object to be saved
     */
    public void saveSysClickLog(SysClickLog sysClickLog);

    /**
     * Removes a sysClickLog from the database by logId
     * @param logId the sysClickLog's logId
     */
    public void removeSysClickLog(final String logId);
    
    /**
	 * 根据外部参数分页获取用户访问日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysClickLogsByCrm(CommonRecord crm, Pager pager);
}