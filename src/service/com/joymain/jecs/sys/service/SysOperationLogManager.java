package com.joymain.jecs.sys.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysOperationLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysOperationLogManager extends Manager {
	/**
	 * Retrieves all of the sysOperationLogs
	 */
	public List getSysOperationLogs(SysOperationLog sysOperationLog);

	/**
	 * Gets sysOperationLog's information based on logId.
	 * @param id the sysOperationLog's logId
	 * @return sysOperationLog populated sysOperationLog object
	 */
	public SysOperationLog getSysOperationLog(final String logId);
	
	/**
	 * 根据ID获取对应的日志
	 * @param logId
	 * @param month
	 * @return
	 */
	public Map getSysOperationLog(final String logId, final String month);

	/**
	 * Saves a sysOperationLog's information
	 * @param sysOperationLog the object to be saved
	 */
	public void saveSysOperationLog(SysOperationLog sysOperationLog);

	/**
	 * Removes a sysOperationLog from the database by logId
	 * @param id the sysOperationLog's logId
	 */
	public void removeSysOperationLog(final String logId);

	/**
	 * 根据外部参数分页获取用户操作日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysOperationLogsByCrm(CommonRecord crm, Pager pager);
	
	/**
	 * 根据外部参数分页获取用户操作日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysOperationLogsNewVersion(CommonRecord crm, Pager pager);
	
	/**
	 * 通过SQL保存日志并且返回新插入的日志的ID
	 * @param sysOperationLog
	 * @param month yyyymm
	 * @return
	 */
	public Long saveSysOperationLogBySql(final SysOperationLog sysOperationLog, final String month);
}