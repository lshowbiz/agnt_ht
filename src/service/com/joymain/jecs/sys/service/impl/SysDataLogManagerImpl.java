package com.joymain.jecs.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysDataLogDao;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.sys.service.SysDataLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysDataLogManagerImpl extends BaseManager implements SysDataLogManager {
	public List getPartSysDataLogs(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		return dao.getPartSysDataLogs(crm,pager);
	}

	private SysDataLogDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysDataLogDao(SysDataLogDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysDataLogManager#getSysDataLogs(com.joymain.jecs.sys.model.SysDataLog)
	 */
	public List getSysDataLogs(final SysDataLog sysDataLog) {
		return dao.getSysDataLogs(sysDataLog);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysDataLogManager#getSysDataLog(String logId)
	 */
	public SysDataLog getSysDataLog(final String logId) {
		return dao.getSysDataLog(new Long(logId));
	}
	
	/**
	 * Saves a sysDataLog's information
	 * @param sysDataLog the object to be saved
	 */
	public Map getSysDataLog(final String logId, final String month){
		return dao.getSysDataLog(new Long(logId), month);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysDataLogManager#saveSysDataLog(SysDataLog sysDataLog)
	 */
	public void saveSysDataLog(SysDataLog sysDataLog) {
		dao.saveSysDataLog(sysDataLog);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysDataLogManager#removeSysDataLog(String logId)
	 */
	public void removeSysDataLog(final String logId) {
		dao.removeSysDataLog(new Long(logId));
	}

	/**
	 * 根据外部参数分页获取对应的日志列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysDataLogsByPage(CommonRecord crm, Pager pager) {
		return dao.getSysDataLogsByPage(crm, pager);
	}
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsByOperation(final Long operationLogId){
		return dao.getSysDataLogsByOperation(operationLogId);
	}
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsNewVersion(final Long operationLogId, final String month){
		return dao.getSysDataLogsNewVersion(operationLogId, month);
	}
	
	/**
	 * 获取某个表字段对应的注释
	 * @param tableName
	 * @param fieldName
	 * @return
	 */
	public String getFieldComment(final String tableName, final String fieldName){
		return dao.getFieldComment(tableName, fieldName);
	}
	
	/**
	 * 通过SQL保存日志并且返回新插入的日志的ID
	 * @param sysDataLog
	 * @return
	 */
	public Long saveSysDataLogBySql(final SysDataLog sysDataLog) {
		return dao.saveSysDataLogBySql(sysDataLog);
	}
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsNewVersionByPage(CommonRecord crm, Pager pager){
		return dao.getSysDataLogsNewVersionByPage(crm, pager);
	}
}
