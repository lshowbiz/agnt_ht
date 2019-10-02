
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.fi.dao.JfiChanjetLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiChanjetLogDaoHibernate extends BaseDaoHibernate implements JfiChanjetLogDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiChanjetLogDao#getJfiChanjetLogs(com.joymain.jecs.fi.model.JfiChanjetLog)
     */
    public List getJfiChanjetLogs(final JfiChanjetLog jfiChanjetLog) {
        return getHibernateTemplate().find("from JfiChanjetLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiChanjetLog == null) {
            return getHibernateTemplate().find("from JfiChanjetLog");
        } else {
            // filter on properties set in the jfiChanjetLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiChanjetLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiChanjetLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiChanjetLogDao#getJfiChanjetLog(Long logId)
     */
    public JfiChanjetLog getJfiChanjetLog(final Long logId) {
        JfiChanjetLog jfiChanjetLog = (JfiChanjetLog) getHibernateTemplate().get(JfiChanjetLog.class, logId);
        if (jfiChanjetLog == null) {
            log.warn("uh oh, jfiChanjetLog with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiChanjetLog.class, logId);
        }

        return jfiChanjetLog;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiChanjetLogDao#saveJfiChanjetLog(JfiChanjetLog jfiChanjetLog)
     */    
    public void saveJfiChanjetLog(final JfiChanjetLog jfiChanjetLog) {
        getHibernateTemplate().saveOrUpdate(jfiChanjetLog);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiChanjetLogDao#removeJfiChanjetLog(Long logId)
     */
    public void removeJfiChanjetLog(final Long logId) {
        getHibernateTemplate().delete(getJfiChanjetLog(logId));
    }
    
    @Override
	public List getJfiChanjetLogsByDealId(String dealId) {

		String hql="from JfiChanjetLog t where t.detailId='"+ dealId +"' and t.inc='1'";
		
		return this.findObjectsByHqlQuery(hql);
	}
    
    //added for getJfiChanjetLogsByCrm
    public List getJfiChanjetLogsByCrm(CommonRecord crm, Pager pager){
    	
    	String hql = "from JfiChanjetLog jfiChanjetLog where 1=1";
    	
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
		
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by logId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
