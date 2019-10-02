
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.MiCoinLog;
import com.joymain.jecs.mi.dao.MiCoinLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class MiCoinLogDaoHibernate extends BaseDaoHibernate implements MiCoinLogDao {

    /**
     * @see com.joymain.jecs.mi.dao.MiCoinLogDao#getMiCoinLogs(com.joymain.jecs.mi.model.MiCoinLog)
     */
    public List getMiCoinLogs(final MiCoinLog miCoinLog) {
        return getHibernateTemplate().find("from MiCoinLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (miCoinLog == null) {
            return getHibernateTemplate().find("from MiCoinLog");
        } else {
            // filter on properties set in the miCoinLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(miCoinLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(MiCoinLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.MiCoinLogDao#getMiCoinLog(BigDecimal id)
     */
    public MiCoinLog getMiCoinLog(final Long id) {
        MiCoinLog miCoinLog = (MiCoinLog) getHibernateTemplate().get(MiCoinLog.class, id);
/*        if (miCoinLog == null) {
            log.warn("uh oh, miCoinLog with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(MiCoinLog.class, id);
        }*/

        return miCoinLog;
    }

    /**
     * @see com.joymain.jecs.mi.dao.MiCoinLogDao#saveMiCoinLog(MiCoinLog miCoinLog)
     */    
    public void saveMiCoinLog(final MiCoinLog miCoinLog) {
        getHibernateTemplate().saveOrUpdate(miCoinLog);
    }

    /**
     * @see com.joymain.jecs.mi.dao.MiCoinLogDao#removeMiCoinLog(BigDecimal id)
     */
    public void removeMiCoinLog(final Long id) {
        getHibernateTemplate().delete(getMiCoinLog(id));
    }
    //added for getMiCoinLogsByCrm
    public List getMiCoinLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from MiCoinLog miCoinLog where 1=1";
    	

    	hql+=returnHql(crm);
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    public BigDecimal getSumCoin(CommonRecord crm) {
		String hql="select nvl(sum(round(coin,2)),0) from MiCoinLog where 1=1";
		hql+=returnHql(crm);
		return (BigDecimal)this.getHibernateTemplate().find(hql).get(0);
		
	}
    private String returnHql(CommonRecord crm){
    	
    	String hql="";
    	String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "'";
		}
		
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status = '" + status + "'";
		}
		
		return hql;
    }
    
}
