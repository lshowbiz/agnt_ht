
package com.joymain.jecs.sys.dao;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysClickLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysClickLogDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the sysClickLogs
     */
    public List getSysClickLogs(SysClickLog sysClickLog);

    /**
     * Gets sysClickLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the sysClickLog's logId
     * @return sysClickLog populated sysClickLog object
     */
    public SysClickLog getSysClickLog(final Long logId);

    /**
     * Saves a sysClickLog's information
     * @param sysClickLog the object to be saved
     */    
    public Map getSysClickLog(final Long logId, final String month);
    public void saveSysClickLog(SysClickLog sysClickLog);

    /**
     * Removes a sysClickLog from the database by logId
     * @param logId the sysClickLog's logId
     */
    public void removeSysClickLog(final Long logId);
    
    /**
	 * 根据外部参数分页获取用户访问日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysClickLogsByCrm(CommonRecord crm, Pager pager);
	public List getSysClickLogsNewVersion(CommonRecord crm, Pager pager);
}