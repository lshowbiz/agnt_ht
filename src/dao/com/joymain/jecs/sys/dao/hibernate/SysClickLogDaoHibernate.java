
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysClickLogDao;
import com.joymain.jecs.sys.model.SysClickLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysClickLogDaoHibernate extends BaseDaoHibernate implements SysClickLogDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysClickLogDao#getSysClickLogs(com.joymain.jecs.sys.model.SysClickLog)
     */
    public List getSysClickLogs(final SysClickLog sysClickLog) {
        return getHibernateTemplate().find("from SysClickLog order by logId");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysClickLogDao#getSysClickLog(Long logId)
     */
    public SysClickLog getSysClickLog(final Long logId) {
        SysClickLog sysClickLog = (SysClickLog) getHibernateTemplate().get(SysClickLog.class, logId);
        if (sysClickLog == null) {
            log.warn("uh oh, sysClickLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(SysClickLog.class, logId);
        }

        return sysClickLog;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysClickLogDao#saveSysClickLog(SysClickLog sysClickLog)
     */    
    
	public Map getSysClickLog(final Long logId, final String month) {
		Map sysClickLogMap=this.findOneObjectBySQL("select * from Jsys_Click_Log_"+month+" where log_id='"+logId+"'");
		return sysClickLogMap;
		
	}
	
    public void saveSysClickLog(final SysClickLog sysClickLog) {
        getHibernateTemplate().saveOrUpdate(sysClickLog);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysClickLogDao#removeSysClickLog(Long logId)
     */
    public void removeSysClickLog(final Long logId) {
        getHibernateTemplate().delete(getSysClickLog(logId));
    }
    
    /**
	 * 根据外部参数分页获取用户访问日志
	 * @param crm
	 * @param pager
	 * @return List
	 */
	public List getSysClickLogsByCrm(CommonRecord crm, Pager pager) {
		String hql = "from SysClickLog where 1=1";

		String remoteUser = crm.getString("remoteUser", "");
		if (!StringUtils.isEmpty(remoteUser)) {
			hql += " and remoteUser='" + remoteUser + "'";
		}

		String ipAddress = crm.getString("ipAddress", "");
		if (!StringUtils.isEmpty(ipAddress)) {
			hql += " and ipAddress like '%" + ipAddress + "%'";
		}
		
		String hostName = crm.getString("hostName", "");
		if (!StringUtils.isEmpty(hostName)) {
			hql += " and hostName like '%" + hostName + "%'";
		}
		
		String clickUri = crm.getString("clickUri", "");
		if (!StringUtils.isEmpty(clickUri)) {
			hql += " and clickUri like '%" + clickUri + "%'";
		}

		String startRunDate = crm.getString("startRunDate", "");
		if (!StringUtils.isEmpty(startRunDate)) {
			hql += " and to_char(runDate,'yyyy-mm-dd')>='" + startRunDate + "'";
		}

		String endRunDate = crm.getString("endRunDate", "");
		if (!StringUtils.isEmpty(endRunDate)) {
			hql += " and to_char(runDate,'yyyy-mm-dd')<='" + endRunDate + "'";
		}
		// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
public List getSysClickLogsNewVersion(CommonRecord crm, Pager pager) {
		
		String month=crm.getString("month","");
		
		if(StringUtils.isEmpty(month)){
			return null;
		}
		String hql = "from SysClickLog_"+month+" where 1=1";

		String remoteUser = crm.getString("remoteUser", "");
		if (!StringUtils.isEmpty(remoteUser)) {
			hql += " and remoteUser='" + remoteUser + "'";
		}

		String ipAddress = crm.getString("ipAddress", "");
		if (!StringUtils.isEmpty(ipAddress)) {
			hql += " and ipAddress like '%" + ipAddress + "%'";
		}
		
		String hostName = crm.getString("hostName", "");
		if (!StringUtils.isEmpty(hostName)) {
			hql += " and hostName like '%" + hostName + "%'";
		}
		
		String clickUri = crm.getString("clickUri", "");
		if (!StringUtils.isEmpty(clickUri)) {
			hql += " and clickUri like '%" + clickUri + "%'";
		}

		String startRunDate = crm.getString("startRunDate", "");
		if (!StringUtils.isEmpty(startRunDate)) {
			hql += " and to_char(runDate,'yyyy-mm-dd')>='" + startRunDate + "'";
		}

		String endRunDate = crm.getString("endRunDate", "");
		if (!StringUtils.isEmpty(endRunDate)) {
			hql += " and to_char(runDate,'yyyy-mm-dd')<='" + endRunDate + "'";
		}
		// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}
}