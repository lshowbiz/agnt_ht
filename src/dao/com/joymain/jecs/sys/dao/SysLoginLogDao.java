
package com.joymain.jecs.sys.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysLoginLogDao extends Dao {

    /**
     * Retrieves all of the sysLoginLogs
     */
    public List getSysLoginLogs(SysLoginLog sysLoginLog);

    /**
     * Gets sysLoginLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param llId the sysLoginLog's llId
     * @return sysLoginLog populated sysLoginLog object
     */
    public SysLoginLog getSysLoginLog(final Long llId);

    /**
     * Saves a sysLoginLog's information
     * @param sysLoginLog the object to be saved
     */    
    public void saveSysLoginLog(SysLoginLog sysLoginLog);

    /**
     * Removes a sysLoginLog from the database by llId
     * @param llId the sysLoginLog's llId
     */
    public void removeSysLoginLog(final Long llId);
    //added for getSysLoginLogsByCrm
    public List getSysLoginLogsByCrm(CommonRecord crm, Pager pager);

	public List getLogsByType(String userCode, String type);
}

