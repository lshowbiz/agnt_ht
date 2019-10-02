
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysVisitLogDao;
import com.joymain.jecs.sys.model.SysVisitLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysVisitLogDaoHibernate extends BaseDaoHibernate implements SysVisitLogDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysVisitLogDao#getSysVisitLogs(com.joymain.jecs.sys.model.SysVisitLog)
     */
    public List getSysVisitLogs(final SysVisitLog sysVisitLog) {
        return getHibernateTemplate().find("from SysVisitLog");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysVisitLogDao#getSysVisitLog(Long logId)
     */
    public SysVisitLog getSysVisitLog(final Long logId) {
        SysVisitLog sysVisitLog = (SysVisitLog) getHibernateTemplate().get(SysVisitLog.class, logId);
        if (sysVisitLog == null) {
            log.warn("uh oh, sysVisitLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(SysVisitLog.class, logId);
        }

        return sysVisitLog;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysVisitLogDao#saveSysVisitLog(SysVisitLog sysVisitLog)
     */    
    public void saveSysVisitLog(final SysVisitLog sysVisitLog) {
        getHibernateTemplate().saveOrUpdate(sysVisitLog);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysVisitLogDao#removeSysVisitLog(Long logId)
     */
    public void removeSysVisitLog(final Long logId) {
        getHibernateTemplate().delete(getSysVisitLog(logId));
    }
    //added for getSysVisitLogsByCrm
    public List getSysVisitLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysVisitLog sysVisitLog where 1=1";
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 根据用户编码,模块编码及访问地址获取唯一的记录
     * @param userCode
     * @param moduleCode
     * @param visitUrl
     * @return
     */
    public SysVisitLog getSysVisitLog(final String userCode, final String moduleCode, final String visitUrl){
    	return (SysVisitLog)this.getObjectByHqlQuery("from SysVisitLog where userCode='"+userCode+"' and moduleCode='"+moduleCode+"' and visitUrl='"+visitUrl+"'");
    }
    
    /**
     * 清除访问记录
     */
    public void removeAllSysVisitLog(){
    	this.executeUpdate("delete from SysVisitLog");
    }
}
