
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.FiChannelLogDao;
import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class FiChannelLogDaoHibernate extends BaseDaoHibernate implements FiChannelLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.FiChannelLogDao#getFiChannelLogs(com.joymain.jecs.fi.model.FiChannelLog)
     */
    public List getFiChannelLogs(final FiChannelLog FiChannelLog) {
        //return getHibernateTemplate().find("from FiChannelLog");
    	return getHibernateTemplate().findByExample(FiChannelLog);
        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (FiChannelLog == null) {
            return getHibernateTemplate().find("from FiChannelLog");
        } else {
            // filter on properties set in the FiChannelLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(FiChannelLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(FiChannelLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiChannelLogDao#getFiChannelLog(Long logId)
     */
    public FiChannelLog getFiChannelLog(final Long logId) {
        FiChannelLog FiChannelLog = (FiChannelLog) getHibernateTemplate().get(FiChannelLog.class, logId);
        if (FiChannelLog == null) {
            log.warn("uh oh, FiChannelLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(FiChannelLog.class, logId);
        }

        return FiChannelLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiChannelLogDao#saveFiChannelLog(FiChannelLog FiChannelLog)
     */    
    public void saveFiChannelLog(final FiChannelLog fiChannelLog) {
        getHibernateTemplate().saveOrUpdate(fiChannelLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.FiChannelLogDao#removeFiChannelLog(Long logId)
     */
    public void removeFiChannelLog(final Long logId) {
        getHibernateTemplate().delete(getFiChannelLog(logId));
    }
    //added for getFiChannelLogsByCrm
    public List getFiChannelLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from FiChannelLog fiChannelLog where 1=1";

		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode = '" + userCode + "'";
		}

		String inc = crm.getString("inc", "");
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc = '" + inc + "'";
		}

		String pId = crm.getString("pId", "");
		if(StringUtils.isNotEmpty(pId)){
			hql += " and pid = '" + pId + "'";
		}

		String dealId = crm.getString("dealId", "");
		if(StringUtils.isNotEmpty(dealId)){
			hql += " and dealId = '" + dealId + "'";
		}

		String bankDealId = crm.getString("bankDealId", "");
		if(StringUtils.isNotEmpty(bankDealId)){
			hql += " and bankDealId = '" + bankDealId + "'";
		}

		String startCreateTime = crm.getString("startCreateTime", "");
		if(StringUtils.isNotEmpty(startCreateTime)){
			hql += " and createTime >= to_date('" + startCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}

		String endCreateTime = crm.getString("endCreateTime", "");
		if(StringUtils.isNotEmpty(endCreateTime)){
			hql += " and createTime < to_date('" + endCreateTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
