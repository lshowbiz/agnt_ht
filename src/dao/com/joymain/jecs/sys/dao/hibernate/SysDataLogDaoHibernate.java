package com.joymain.jecs.sys.dao.hibernate;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysDataLogDao;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysDataLogDaoHibernate extends BaseDaoHibernate implements SysDataLogDao {

	/*private String getTableNameByTime(String date){
		String tableName="jsys_data_log_";
		
	}*/
	public List getPartSysDataLogs(CommonRecord crm, Pager pager) {
		// TODO Auto-generated method stub
		String hqlQuery = "select * from SysDataLog where 1=1";

		String changeType = crm.getString("changeType", "");
		if (!StringUtils.isEmpty(changeType)) {
			hqlQuery += " and changeType='" + changeType + "'";
		}

		String tableName = crm.getString("tableName", "");
		if (!StringUtils.isEmpty(tableName)) {
			hqlQuery += " and tableName='" + tableName + "'";
		}
		
		String columnName = crm.getString("columnName", "");
		if (!StringUtils.isEmpty(columnName)) {
			hqlQuery += " and columnName='" + columnName + "'";
		}
		
		String pidName = crm.getString("pidName", "");
		if (!StringUtils.isEmpty(pidName)) {
			hqlQuery += " and pidName='" + pidName + "'";
		}
		
		String pidValue = crm.getString("pidValue", "");
		if (!StringUtils.isEmpty(pidValue)) {
			hqlQuery += " and pidValue='" + pidValue + "'";
		}
		
		String operatorPerson = crm.getString("operatorPerson", "");
		if (!StringUtils.isEmpty(operatorPerson)) {
			hqlQuery += " and operatorPerson='" + operatorPerson + "'";
		}
		
		String startOperatorTime = crm.getString("startOperatorTime", "");
		if (!StringUtils.isEmpty(startOperatorTime)) {
			hqlQuery += " and to_char(operatorTime,'yyyy-mm-dd  hh24:mi:ss')>='"+startOperatorTime+"'";
		}
		
		String endOperatorTime = crm.getString("endOperatorTime", "");
		if (!StringUtils.isEmpty(endOperatorTime)) {
			hqlQuery += " and to_char(operatorTime,'yyyy-mm-dd  hh24:mi:ss')<='"+startOperatorTime+"'";
		}
		
		String beforeChange = crm.getString("beforeChange", "");
		if (!StringUtils.isEmpty(beforeChange)) {
			hqlQuery += " and beforeChange='" + beforeChange + "'";
		}

		String afterChange = crm.getString("afterChange", "");
		if (!StringUtils.isEmpty(afterChange)) {
			hqlQuery += " and afterChange='" + afterChange + "'";
		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			// 缺省排序
			hqlQuery += " order by logId desc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List sysDataLogs = this.findObjectsByHqlQuery(hqlQuery, pager);

		
		return null;
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysDataLogDao#getSysDataLogs(com.joymain.jecs.sys.model.SysDataLog)
	 */
	public List getSysDataLogs(final SysDataLog sysDataLog) {
		return getHibernateTemplate().find("from SysDataLog");
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysDataLogDao#getSysDataLog(Long logId)
	 */
	public SysDataLog getSysDataLog(final Long logId) {
		SysDataLog sysDataLog = (SysDataLog) getHibernateTemplate().get(SysDataLog.class, logId);
		if (sysDataLog == null) {
			log.warn("uh oh, sysDataLog with logId '" + logId + "' not found...");
			throw new ObjectRetrievalFailureException(SysDataLog.class, logId);
		}

		return sysDataLog;
	}
	
	public Map getSysDataLog(final Long logId, final String month) {
		// TODO Auto-generated method stub
		Map sysDataLogMap = this.findOneObjectBySQL("select * from jsys_Data_Log_" + month + " where log_id='" + logId + "'");
		return sysDataLogMap;
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysDataLogDao#saveSysDataLog(SysDataLog sysDataLog)
	 */
	public void saveSysDataLog(final SysDataLog sysDataLog) {
		getHibernateTemplate().saveOrUpdate(sysDataLog);
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysDataLogDao#removeSysDataLog(Long logId)
	 */
	public void removeSysDataLog(final Long logId) {
		getHibernateTemplate().delete(getSysDataLog(logId));
	}

	/**
	 * 根据外部参数分页获取对应的日志列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysDataLogsByPage(CommonRecord crm, Pager pager) {
		String hqlQuery = "from SysDataLog where 1=1";

		String changeType = crm.getString("changeType", "");
		if (!StringUtils.isEmpty(changeType)) {
			hqlQuery += " and changeType='" + changeType + "'";
		}

		String tableName = crm.getString("tableName", "");
		if (!StringUtils.isEmpty(tableName)) {
			hqlQuery += " and tableName='" + tableName + "'";
		}
		
		String columnName = crm.getString("columnName", "");
		if (!StringUtils.isEmpty(columnName)) {
			hqlQuery += " and columnName='" + columnName + "'";
		}
		
		String pidName = crm.getString("pidName", "");
		if (!StringUtils.isEmpty(pidName)) {
			hqlQuery += " and pidName='" + pidName + "'";
		}
		
		String pidValue = crm.getString("pidValue", "");
		if (!StringUtils.isEmpty(pidValue)) {
			hqlQuery += " and pidValue='" + pidValue + "'";
		}
		
		String operatorPerson = crm.getString("operatorPerson", "");
		if (!StringUtils.isEmpty(operatorPerson)) {
			hqlQuery += " and operatorPerson='" + operatorPerson + "'";
		}
		
		String startOperatorTime = crm.getString("startOperatorTime", "");
		if (!StringUtils.isEmpty(startOperatorTime)) {
			hqlQuery += " and to_char(operatorTime,'yyyy-mm-dd')>='"+startOperatorTime+"'";
		}
		
		String endOperatorTime = crm.getString("endOperatorTime", "");
		if (!StringUtils.isEmpty(endOperatorTime)) {
			hqlQuery += " and to_char(operatorTime,'yyyy-mm-dd')<='"+startOperatorTime+"'";
		}
		
		String beforeChange = crm.getString("beforeChange", "");
		if (!StringUtils.isEmpty(beforeChange)) {
			hqlQuery += " and beforeChange='" + beforeChange + "'";
		}

		String afterChange = crm.getString("afterChange", "");
		if (!StringUtils.isEmpty(afterChange)) {
			hqlQuery += " and afterChange='" + afterChange + "'";
		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			// 缺省排序
			hqlQuery += " order by logId desc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List sysDataLogs = this.findObjectsByHqlQuery(hqlQuery, pager);

		return sysDataLogs;
	}
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsByOperation(final Long operationLogId){
		return this.getHibernateTemplate().find("from SysDataLog where operationLogId=? order by tableName, pidName, pidValue, logId", operationLogId);
	}
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsNewVersion(final Long operationLogId, final String month){
		return this.findObjectsBySQL("select * from jsys_Data_Log_"+month+" where operation_Log_Id='"+operationLogId+"' order by table_Name, p_id_Name, p_id_Value, log_Id");
	}
	
	/**
	 * 通过SQL保存日志并且返回新插入的日志的ID
	 * @param sysDataLog
	 * @return
	 */
	public Long saveSysDataLogBySql(final SysDataLog sysDataLog) {
		Long nextLogId= this.jdbcTemplate.queryForLong("select SEQ_LOG.nextval from dual");
		this.getJdbcTemplate().update("insert into jsys_data_log values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
			new Object[]{
				nextLogId,sysDataLog.getChangeType(),sysDataLog.getTableName(),
				sysDataLog.getColumnName(),sysDataLog.getPidName(),sysDataLog.getPidValue(),
				sysDataLog.getBeforeChange(),sysDataLog.getAfterChange(),sysDataLog.getOperatorPerson(),
				sysDataLog.getOperatorTime(),sysDataLog.getModuleName(),sysDataLog.getHostName(),
				sysDataLog.getIpAddress(),sysDataLog.getPersonType(),sysDataLog.getOperationLogId()
			}, new int[]{
				Types.NUMERIC,Types.VARCHAR,Types.VARCHAR,
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
				Types.DATE,Types.VARCHAR,Types.VARCHAR,
				Types.VARCHAR,Types.VARCHAR,Types.NUMERIC
		});
		return nextLogId;
	}
	
	/**
	 * 获取某次操作对应的数据修改记录
	 * @param operationLogId
	 * @return
	 */
	public List getSysDataLogsNewVersionByPage(CommonRecord crm, Pager pager){
		String month=crm.getString("month","");
		
		if(StringUtils.isEmpty(month)){
			return null;
		}
		String hqlQuery = "select * from jsys_Data_Log where 1=1";

		String changeType = crm.getString("changeType", "");
		if (!StringUtils.isEmpty(changeType)) {
			hqlQuery += " and change_Type='" + changeType + "'";
		}

		String tableName = crm.getString("tableName", "");
		if (!StringUtils.isEmpty(tableName)) {
			hqlQuery += " and table_Name='" + tableName + "'";
		}
		
		String columnName = crm.getString("columnName", "");
		if (!StringUtils.isEmpty(columnName)) {
			hqlQuery += " and column_Name='" + columnName + "'";
		}
		
		String pidName = crm.getString("pidName", "");
		if (!StringUtils.isEmpty(pidName)) {
			hqlQuery += " and p_id_Name='" + pidName + "'";
		}
		
		String pidValue = crm.getString("pidValue", "");
		if (!StringUtils.isEmpty(pidValue)) {
			hqlQuery += " and p_id_Value='" + pidValue + "'";
		}
		
		String operatorPerson = crm.getString("operatorPerson", "");
		if (!StringUtils.isEmpty(operatorPerson)) {
			hqlQuery += " and operator_Person='" + operatorPerson + "'";
		}
		
		String startOperatorTime = crm.getString("startOperatorTime", "");
		if (!StringUtils.isEmpty(startOperatorTime)) {
//			hqlQuery += " and to_char(operator_Time,'yyyy-mm-dd hh24:mi:ss')>='"+startOperatorTime+"'";
			hqlQuery += " and operator_Time >=to_date('"+startOperatorTime+"','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String endOperatorTime = crm.getString("endOperatorTime", "");
		if (!StringUtils.isEmpty(endOperatorTime)) {
//			hqlQuery += " and to_char(operator_Time,'yyyy-mm-dd hh24:mi:ss')<='"+endOperatorTime+"'";
			hqlQuery += " and operator_Time >=to_date('"+endOperatorTime+"','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String beforeChange = crm.getString("beforeChange", "");
		if (!StringUtils.isEmpty(beforeChange)) {
			hqlQuery += " and before_Change='" + beforeChange + "'";
		}

		String afterChange = crm.getString("afterChange", "");
		if (!StringUtils.isEmpty(afterChange)) {
			hqlQuery += " and after_Change='" + afterChange + "'";
		}
		
		String operationLogId = crm.getString("operationLogId", "");
		if (!StringUtils.isEmpty(operationLogId)) {
			hqlQuery += " and OPERATION_LOG_ID=" + operationLogId + "";
		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			// 缺省排序
			hqlQuery += " order by log_Id desc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		//List sysDataLogs = this.findObjectsBySQL(hqlQuery, pager);

		return this.findObjectsBySQL(hqlQuery, pager);
	}
}
