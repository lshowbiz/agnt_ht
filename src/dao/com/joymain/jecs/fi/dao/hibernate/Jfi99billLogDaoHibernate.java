
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.dao.Jfi99billLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class Jfi99billLogDaoHibernate extends BaseDaoHibernate implements Jfi99billLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billLogDao#getJfi99billLogs(com.joymain.jecs.fi.model.Jfi99billLog)
     */
    public List getJfi99billLogs(final Jfi99billLog jfi99billLog) {
        //return getHibernateTemplate().find("from Jfi99billLog");
    	return getHibernateTemplate().findByExample(jfi99billLog);
        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfi99billLog == null) {
            return getHibernateTemplate().find("from Jfi99billLog");
        } else {
            // filter on properties set in the jfi99billLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfi99billLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Jfi99billLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billLogDao#getJfi99billLog(Long logId)
     */
    public Jfi99billLog getJfi99billLog(final Long logId) {
        Jfi99billLog jfi99billLog = (Jfi99billLog) getHibernateTemplate().get(Jfi99billLog.class, logId);
        if (jfi99billLog == null) {
            log.warn("uh oh, jfi99billLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(Jfi99billLog.class, logId);
        }

        return jfi99billLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billLogDao#saveJfi99billLog(Jfi99billLog jfi99billLog)
     */    
    public void saveJfi99billLog(final Jfi99billLog jfi99billLog) {
        getHibernateTemplate().saveOrUpdate(jfi99billLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.Jfi99billLogDao#removeJfi99billLog(Long logId)
     */
    public void removeJfi99billLog(final Long logId) {
        getHibernateTemplate().delete(getJfi99billLog(logId));
    }
    //added for getJfi99billLogsByCrm
    public List getJfi99billLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from Jfi99billLog jfi99billLog where 1=1";

		String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode = '" + userCode + "'";
		}

		String inc = crm.getString("inc", "");
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc = '" + inc + "'";
		}

		String merchantAcctId = crm.getString("merchantAcctId", "");
		if(StringUtils.isNotEmpty(merchantAcctId)){
			hql += " and merchantAcctId = '" + merchantAcctId + "'";
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
		
		String dataType = crm.getString("dataType", "");
		if(StringUtils.isNotEmpty(dataType)){
			hql += " and dataType = '" + dataType + "'";
		}
		
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	@Override
	public List getSuccessJfi99billLogsByConditions(String dealId) {
		
		String hql="select t from Jfi99billLog t where t.inc='1' and t.dealId='"+dealId+"'";
		return this.findObjectsByHqlQuery(hql);
	}
}
