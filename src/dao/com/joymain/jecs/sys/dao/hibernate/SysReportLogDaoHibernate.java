
package com.joymain.jecs.sys.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.sys.dao.SysReportLogDao;
import com.joymain.jecs.sys.model.SysReportLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysReportLogDaoHibernate extends BaseDaoHibernate implements SysReportLogDao {
    /**
     * @see com.joymain.jecs.sys.dao.SysReportLogDao#getSysReportLogs(com.joymain.jecs.sys.model.SysReportLog)
     */
    public List getSysReportLogs(final SysReportLog sysReportLog) {
        return getHibernateTemplate().find("from SysReportLog");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysReportLogDao#getSysReportLog(String reportId)
     */
    public SysReportLog getSysReportLog(final String reportId) {
        SysReportLog sysReportLog = (SysReportLog) getHibernateTemplate().get(SysReportLog.class, reportId);
        if (sysReportLog == null) {
            log.warn("uh oh, sysReportLog with reportId '" + reportId + "' not found...");
            throw new ObjectRetrievalFailureException(SysReportLog.class, reportId);
        }

        return sysReportLog;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysReportLogDao#saveSysReportLog(SysReportLog sysReportLog)
     */    
    public void saveSysReportLog(final SysReportLog sysReportLog) {
        getHibernateTemplate().saveOrUpdate(sysReportLog);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysReportLogDao#removeSysReportLog(String reportId)
     */
    public void removeSysReportLog(final String reportId) {
        getHibernateTemplate().delete(getSysReportLog(reportId));
    }
    //added for getSysReportLogsByCrm
    public List getSysReportLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from SysReportLog sysReportLog where 1=1";
    	
    	String userCode = crm.getString("userCode", "");
    	String companyCode = crm.getString("companyCode", "");
    	
    	if (!Constants.ROOT_ACCOUNT.equals(userCode)) {
    		if(!StringUtils.isEmpty(companyCode) && !"AA".equals(companyCode)){
    			hql+=" and companyCode='"+companyCode+"'";
    		}
    	}
    	// ��������
		if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by reportId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
	 * 获取报表日志
	 * @param companyCode
	 * @param userCode
	 * @return
	 */
    public List getSysReportLogs(final String companyCode, final String userCode) {
    	String hqlQuery="from SysReportLog where 1=1 ";
    	
    	if (!Constants.ROOT_ACCOUNT.equals(userCode)) {
    		if(!StringUtils.isEmpty(companyCode) && !"AA".equals(companyCode)){
    			hqlQuery+=" and companyCode='"+companyCode+"'";
    		}
    	}
    	
		hqlQuery+="  order by reportId desc";
		return this.findObjectsByHqlQuery(hqlQuery);
    }
}
