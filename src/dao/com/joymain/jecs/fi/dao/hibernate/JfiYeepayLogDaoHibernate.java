
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.fi.dao.JfiYeepayLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiYeepayLogDaoHibernate extends BaseDaoHibernate implements JfiYeepayLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiYeepayLogDao#getJfiYeepayLogs(com.joymain.jecs.fi.model.JfiYeepayLog)
     */
    public List getJfiYeepayLogs(final JfiYeepayLog jfiYeepayLog) {
        return getHibernateTemplate().find("from JfiYeepayLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiYeepayLog == null) {
            return getHibernateTemplate().find("from JfiYeepayLog");
        } else {
            // filter on properties set in the jfiYeepayLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiYeepayLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiYeepayLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiYeepayLogDao#getJfiYeepayLog(Long logId)
     */
    public JfiYeepayLog getJfiYeepayLog(final Long logId) {
        JfiYeepayLog jfiYeepayLog = (JfiYeepayLog) getHibernateTemplate().get(JfiYeepayLog.class, logId);
        if (jfiYeepayLog == null) {
            log.warn("uh oh, jfiYeepayLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiYeepayLog.class, logId);
        }

        return jfiYeepayLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiYeepayLogDao#saveJfiYeepayLog(JfiYeepayLog jfiYeepayLog)
     */    
    public void saveJfiYeepayLog(final JfiYeepayLog jfiYeepayLog) {
        getHibernateTemplate().saveOrUpdate(jfiYeepayLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiYeepayLogDao#removeJfiYeepayLog(Long logId)
     */
    public void removeJfiYeepayLog(final Long logId) {
        getHibernateTemplate().delete(getJfiYeepayLog(logId));
    }
    //added for getJfiYeepayLogsByCrm
    public List getJfiYeepayLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiYeepayLog jfiYeepayLog where 1=1";
    	
    	String userCode = crm.getString("userCode", "");
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode = '" + userCode + "'";
		}

		String inc = crm.getString("inc", "");
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc = '" + inc + "'";
		}

		String merchantId = crm.getString("merchantId", "");
		if(StringUtils.isNotEmpty(merchantId)){
			hql += " and merchantId = '" + merchantId + "'";
		}

		String detailId = crm.getString("detailId", "");
		if(StringUtils.isNotEmpty(detailId)){
			hql += " and detailId = '" + detailId + "'";
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
    	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	@Override
	public List getJfiChanjetLogsByDealId(String dealId) {
		
		String hql="from JfiYeepayLog t where t.detailId='"+ dealId +"' and t.inc='1'";
		
		return this.findObjectsByHqlQuery(hql);
	}
}
