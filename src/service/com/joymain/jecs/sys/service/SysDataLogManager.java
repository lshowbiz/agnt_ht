
package com.joymain.jecs.sys.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysDataLogManager extends Manager {
	
	public List getPartSysDataLogs(CommonRecord crm, Pager pager);
    /**
     * Retrieves all of the sysDataLogs
     */
    public List getSysDataLogs(SysDataLog sysDataLog);

    /**
     * Gets sysDataLog's information based on logId.
     * @param logId the sysDataLog's logId
     * @return sysDataLog populated sysDataLog object
     */
    public SysDataLog getSysDataLog(final String logId);

    /**
     * Saves a sysDataLog's information
     * @param sysDataLog the object to be saved
     */
    public void saveSysDataLog(SysDataLog sysDataLog);
    
    /**
	 * Saves a sysDataLog's information
	 * @param sysDataLog the object to be saved
	 */
	public Map getSysDataLog(final String logId, final String month);

    /**
     * Removes a sysDataLog from the database by logId
     * @param logId the sysDataLog's logId
     */
    public void removeSysDataLog(final String logId);
    
    /**
	 * 根据外部参数分页获取对应的日志列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysDataLogsByPage(CommonRecord crm, Pager pager) ;
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsByOperation(final Long operationLogId);
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsNewVersion(final Long operationLogId, final String month);
	
	/**
	 * 获取某个表字段对应的注释
	 * @param tableName
	 * @param fieldName
	 * @return
	 */
	public String getFieldComment(final String tableName, final String fieldName);
	
	/**
	 * 通过SQL保存日志并且返回新插入的日志的ID
	 * @param sysDataLog
	 * @return
	 */
	public Long saveSysDataLogBySql(final SysDataLog sysDataLog);
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsNewVersionByPage(CommonRecord crm, Pager pager);
}