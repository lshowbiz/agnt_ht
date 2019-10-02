
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.fi.dao.Jfi99billmsLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class Jfi99billmsLogDaoHibernate extends BaseDaoHibernate implements Jfi99billmsLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billmsLogDao#getJfi99billmsLogs(com.joymain.jecs.fi.model.Jfi99billmsLog)
     */
    public List getJfi99billmsLogs(final Jfi99billmsLog jfi99billmsLog) {
        //return getHibernateTemplate().find("from Jfi99billmsLog");
    	return getHibernateTemplate().findByExample(jfi99billmsLog);
        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfi99billmsLog == null) {
            return getHibernateTemplate().find("from Jfi99billmsLog");
        } else {
            // filter on properties set in the jfi99billmsLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfi99billmsLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Jfi99billmsLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billmsLogDao#getJfi99billmsLog(Long logId)
     */
    public Jfi99billmsLog getJfi99billmsLog(final Long logId) {
        Jfi99billmsLog jfi99billmsLog = (Jfi99billmsLog) getHibernateTemplate().get(Jfi99billmsLog.class, logId);
        if (jfi99billmsLog == null) {
            log.warn("uh oh, jfi99billmsLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(Jfi99billmsLog.class, logId);
        }

        return jfi99billmsLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billmsLogDao#saveJfi99billmsLog(Jfi99billmsLog jfi99billmsLog)
     */    
    public void saveJfi99billmsLog(final Jfi99billmsLog jfi99billmsLog) {
        getHibernateTemplate().saveOrUpdate(jfi99billmsLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billmsLogDao#removeJfi99billmsLog(Long logId)
     */
    public void removeJfi99billmsLog(final Long logId) {
        getHibernateTemplate().delete(getJfi99billmsLog(logId));
    }
    //added for getJfi99billmsLogsByCrm
    public List getJfi99billmsLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from Jfi99billmsLog jfi99billmsLog where 1=1";

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
			hql += " and pId = '" + pId + "'";
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
