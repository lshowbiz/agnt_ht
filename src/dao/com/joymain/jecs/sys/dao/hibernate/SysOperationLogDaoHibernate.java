package com.joymain.jecs.sys.dao.hibernate;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCharacterValue;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysOperationLogDao;
import com.joymain.jecs.sys.model.SysOperationLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysOperationLogDaoHibernate extends BaseDaoHibernate implements SysOperationLogDao {

	/**
	 * @see com.joymain.jecs.sys.dao.SysOperationLogDao#getSysOperationLogs(com.joymain.jecs.sys.model.SysOperationLog)
	 */
	public List getSysOperationLogs(final SysOperationLog sysOperationLog) {
		return getHibernateTemplate().find("from SysOperationLog order by logId");
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysOperationLogDao#getSysOperationLog(Long logId)
	 */
	public SysOperationLog getSysOperationLog(final Long logId) {
		SysOperationLog sysOperationLog = (SysOperationLog) getHibernateTemplate().get(SysOperationLog.class, logId);
		if (sysOperationLog == null) {
			log.warn("uh oh, sysOperationLog with id '" + logId + "' not found...");
			throw new ObjectRetrievalFailureException(SysOperationLog.class, logId);
		}

		return sysOperationLog;
	}

	/**
	 * 根据ID获取对应的日志
	 * @param logId
	 * @param month
	 * @return
	 */
	public Map getSysOperationLog(final Long logId, final String month) {
		Map sysOperationLogMap = this.findOneObjectBySQL("select * from jsys_Operation_Log_" + month + " where log_id='" + logId + "'");
		return sysOperationLogMap;

	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysOperationLogDao#saveSysOperationLog(SysOperationLog sysOperationLog)
	 */
	public void saveSysOperationLog(final SysOperationLog sysOperationLog) {
		getHibernateTemplate().saveOrUpdate(sysOperationLog);
	}

	/**
	 * @see com.joymain.jecs.sys.dao.SysOperationLogDao#removeSysOperationLog(Long logId)
	 */
	public void removeSysOperationLog(final Long logId) {
		getHibernateTemplate().delete(getSysOperationLog(logId));
	}

	/**
	 * 根据外部参数分页获取用户操作日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysOperationLogsByCrm(CommonRecord crm, Pager pager) {
		String hql = "select * from jsys_Operation_Log where 1=1";

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and company_Code='" + companyCode + "'";
		}

		//除ROOT以外,只能查看当前操作用户可以管理的用户所对应的日志
		/*
		 * String masterUserCode = crm.getString("masterUserCode", ""); if (!StringUtils.isEmpty(masterUserCode) && !masterUserCode.equals(Constants.ROOT_ACCOUNT)) { hql += " and (operater_Code in (select slave_User_Code from jsys_Manager_User where master_User_Code='" + masterUserCode + "'"; hql+=" union all select user_Code from jsys_User where user_Type!='C'"; if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) { hql+=" and company_Code='"+companyCode+"'"; } hql+=") or operater_Code='"+masterUserCode+"')"; }
		 */

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and user_Code='" + userCode + "'";
		}

		String operaterCode = crm.getString("operaterCode", "");
		if (!StringUtils.isEmpty(operaterCode)) {
			hql += " and operater_Code='" + operaterCode + "'";
		}

		String ipAddr = crm.getString("ipAddr", "");
		if (!StringUtils.isEmpty(ipAddr)) {
			hql += " and ip_Addr like '%" + ipAddr + "%'";
		}

		String userType = crm.getString("userType", "");
		if (!StringUtils.isEmpty(userType)) {
			hql += " and user_Type in ('" + userType + "')";
		}

		String moduleName = crm.getString("moduleName", "");
		if (!StringUtils.isEmpty(moduleName)) {
			String characterCode = crm.getString("characterCode", "");
			AlCharacterValue alCharacterValue = (AlCharacterValue) this.getObjectByHqlQuery("from AlCharacterValue where characterValue='"
			        + moduleName + "' and characterCode='" + characterCode + "'");
			if(alCharacterValue!=null){
				hql += " and module_Name ='" + alCharacterValue.getAlCharacterKey().getCharacterKey() + "'";
			}
		}

		String visitUrl = crm.getString("visitUrl", "");
		if (!StringUtils.isEmpty(visitUrl)) {
			hql += " and visit_Url like '%" + visitUrl + "%'";
		}

		String startOperateTime = crm.getString("startOperateTime", "");
		if (!StringUtils.isEmpty(startOperateTime)) {
			hql += " and to_char(operate_Time,'yyyy-mm-dd hh24:mi:ss')>='" + startOperateTime + "'";
		}

		String endOperateTime = crm.getString("endOperateTime", "");
		if (!StringUtils.isEmpty(endOperateTime)) {
			hql += " and to_char(operate_Time,'yyyy-mm-dd hh24:mi:ss')<='" + endOperateTime + "'";
		}

		String viewType = crm.getString("viewType", "");
		//如果是查看作业记录
		if ("listSysOperationLogs".equals(viewType)) {
			hql += " and log_id in (select operation_Log_Id from jsys_Data_Log where change_Type in ('update','insert','delete')";
			String beforeChange = crm.getString("beforeChange", "");
			if (!StringUtils.isEmpty(beforeChange)) {
				hql += " and BEFORE_CHANGE='" + beforeChange + "'";
			}

			String afterChange = crm.getString("afterChange", "");
			if (!StringUtils.isEmpty(afterChange)) {
				hql += " and AFTER_CHANGE='" + afterChange + "'";
			}
			hql += ")";
		}
		// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by operate_Time desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(hql, pager);
	}

	/**
	 * 根据外部参数分页获取用户操作日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysOperationLogsNewVersion(CommonRecord crm, Pager pager) {
		String month = crm.getString("month", "");

		if (StringUtils.isEmpty(month)) {
			return null;
		}

		String hql = "select * from jsys_Operation_Log_" + month + " where 1=1";

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql += " and company_Code='" + companyCode + "'";
		}

		//除ROOT以外,只能查看当前操作用户可以管理的用户所对应的日志
		/*
		 * String masterUserCode = crm.getString("masterUserCode", ""); if (!StringUtils.isEmpty(masterUserCode) && !masterUserCode.equals(Constants.ROOT_ACCOUNT)) { hql += " and (operater_Code in (select slave_User_Code from jsys_Manager_User where master_User_Code='" + masterUserCode + "'"; hql+=" union all select user_Code from jsys_User where user_Type!='C'"; if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) { hql+=" and company_Code='"+companyCode+"'"; } hql+=") or operater_Code='"+masterUserCode+"')"; }
		 */

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and user_Code='" + userCode + "'";
		}

		String operaterCode = crm.getString("operaterCode", "");
		if (!StringUtils.isEmpty(operaterCode)) {
			hql += " and operater_Code='" + operaterCode + "'";
		}

		String ipAddr = crm.getString("ipAddr", "");
		if (!StringUtils.isEmpty(ipAddr)) {
			hql += " and ip_Addr like '%" + ipAddr + "%'";
		}

		String userType = crm.getString("userType", "");
		if (!StringUtils.isEmpty(userType)) {
			hql += " and user_Type in ('" + userType + "')";
		}

		String moduleName = crm.getString("moduleName", "");
		if (!StringUtils.isEmpty(moduleName)) {
			String characterCode = crm.getString("characterCode", "");
			AlCharacterValue alCharacterValue = (AlCharacterValue) this.getObjectByHqlQuery("from AlCharacterValue where characterValue='"
			        + moduleName + "' and characterCode='" + characterCode + "'");
			if (alCharacterValue != null) {
				hql += " and module_Name ='" + alCharacterValue.getAlCharacterKey().getCharacterKey() + "'";
			}
		}

		String visitUrl = crm.getString("visitUrl", "");
		if (!StringUtils.isEmpty(visitUrl)) {
			hql += " and visit_Url like '%" + visitUrl + "%'";
		}

		String startOperateTime = crm.getString("startOperateTime", "");
		if (!StringUtils.isEmpty(startOperateTime)) {
			hql += " and to_char(operate_Time,'yyyy-mm-dd hh24:mi:ss')>='" + startOperateTime + "'";
		}

		String endOperateTime = crm.getString("endOperateTime", "");
		if (!StringUtils.isEmpty(endOperateTime)) {
			hql += " and to_char(operate_Time,'yyyy-mm-dd hh24:mi:ss')<='" + endOperateTime + "'";
		}

		//如果是查看作业记录
		String keyValue = crm.getString("keyValue", "");
		//如果是查看作业记录
		//Modify By WuCF 20140304 日志表，不再用，注释!
		/*if (!StringUtils.isEmpty(keyValue)) {
			hql += " and log_id in (select log_id from jsys_data_log_key_" + month + " where KEY_VALUE='" + keyValue + "')";
		}*/
		// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by operate_Time desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(hql, pager);
	}

	/**
	 * 通过SQL保存日志并且返回新插入的日志的ID
	 * @param sysOperationLog
	 * @param month yyyymm
	 * @return
	 */
	public Long saveSysOperationLogBySql(final SysOperationLog sysOperationLog, final String month) {
		Long nextLogId = this.jdbcTemplate.queryForLong("select SEQ_LOG.nextval from dual");
//		this.jdbcTemplate
//		        .update(
//		                "insert into jsys_operation_log_"
//		                        + month
//		                        + "(LOG_ID, OPERATER_CODE, OPERATER_NAME, IP_ADDR, OPERATE_TIME, VISIT_URL, OPERATE_DATA, COMPANY_CODE, MODULE_NAME,DO_TYPE,USER_TYPE,DO_RESULT,USER_CODE,USER_NAME) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
//		                new Object[] { nextLogId, sysOperationLog.getOperaterCode(), sysOperationLog.getOperaterName(),
//		                        sysOperationLog.getIpAddr(), sysOperationLog.getOperateTime(), sysOperationLog.getVisitUrl(),
//		                        new SqlLobValue(sysOperationLog.getOperateData()), sysOperationLog.getCompanyCode(),
//		                        sysOperationLog.getModuleName(), sysOperationLog.getDoType(), sysOperationLog.getUserType(),
//		                        sysOperationLog.getDoResult(), sysOperationLog.getUserCode(), sysOperationLog.getUserName() }, new int[] {
//		                        Types.NUMERIC, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.CLOB,
//		                        Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.VARCHAR, Types.NUMERIC, Types.VARCHAR, Types.VARCHAR });
//		
		this.jdbcTemplate
        .update(
                "insert into jsys_operation_log_"
                        + month
                        + "(LOG_ID, OPERATER_CODE, OPERATER_NAME, IP_ADDR, OPERATE_TIME, VISIT_URL, COMPANY_CODE, MODULE_NAME,DO_TYPE,USER_TYPE,DO_RESULT,USER_CODE,USER_NAME) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new Object[] { nextLogId, sysOperationLog.getOperaterCode(), sysOperationLog.getOperaterName(),
                        sysOperationLog.getIpAddr(), sysOperationLog.getOperateTime(), sysOperationLog.getVisitUrl(),
                        sysOperationLog.getCompanyCode(),
                        sysOperationLog.getModuleName(), sysOperationLog.getDoType(), sysOperationLog.getUserType(),
                        sysOperationLog.getDoResult(), sysOperationLog.getUserCode(), sysOperationLog.getUserName() }, new int[] {
                        Types.NUMERIC, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, 
                        Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.VARCHAR, Types.NUMERIC, Types.VARCHAR, Types.VARCHAR });
		return nextLogId;
	}
}