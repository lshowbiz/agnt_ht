
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.bd.dao.JbdTravelPointLogAllDao;
import com.joymain.jecs.bd.model.JbdTravelPointLogAll;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JbdTravelPointLogAllDaoHibernate extends BaseDaoHibernate implements JbdTravelPointLogAllDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLogAllDao#getJbdTravelPointLogAlls(com.joymain.jecs.bd.model.JbdTravelPointLogAll)
     */
    public List getJbdTravelPointLogAlls(final JbdTravelPointLogAll jbdTravelPointLogAll) {
        return getHibernateTemplate().find("from JbdTravelPointLogAll");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdTravelPointLogAll == null) {
            return getHibernateTemplate().find("from JbdTravelPointLogAll");
        } else {
            // filter on properties set in the jbdTravelPointLogAll
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdTravelPointLogAll).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdTravelPointLogAll.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLogAllDao#getJbdTravelPointLogAll(Long logId)
     */
    public JbdTravelPointLogAll getJbdTravelPointLogAll(final Long logId) {
        JbdTravelPointLogAll jbdTravelPointLogAll = (JbdTravelPointLogAll) getHibernateTemplate().get(JbdTravelPointLogAll.class, logId);
        if (jbdTravelPointLogAll == null) {
            log.warn("uh oh, jbdTravelPointLogAll with logId '" + logId + "' not found...");
            throw new ObjectRetrievalFailureException(JbdTravelPointLogAll.class, logId);
        }

        return jbdTravelPointLogAll;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLogAllDao#saveJbdTravelPointLogAll(JbdTravelPointLogAll jbdTravelPointLogAll)
     */    
    public void saveJbdTravelPointLogAll(final JbdTravelPointLogAll jbdTravelPointLogAll) {
        getHibernateTemplate().saveOrUpdate(jbdTravelPointLogAll);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdTravelPointLogAllDao#removeJbdTravelPointLogAll(Long logId)
     */
    public void removeJbdTravelPointLogAll(final Long logId) {
        getHibernateTemplate().delete(getJbdTravelPointLogAll(logId));
    }
    //added for getJbdTravelPointLogAllsByCrm
    public List getJbdTravelPointLogAllsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdTravelPointLogAll jbdTravelPointLogAll where 1=1";
    	String userCode = crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
			hql += " and jbdTravelPointLogAll.userCode='"+userCode+"'";
		}
    	String dealType = crm.getString("dealType", "");
		if(!StringUtil.isEmpty(dealType)){
			hql += " and jbdTravelPointLogAll.dealType='"+dealType+"'";
		}
		String fyear = crm.getString("fyear", "");
		if(!StringUtil.isEmpty(fyear)){
			hql += " and jbdTravelPointLogAll.fyear='"+fyear+"'";
		}
		String startTime = crm.getString("startTime", "");
		if(!StringUtil.isEmpty(startTime)){
			hql += " and jbdTravelPointLogAll.createTime >= to_date('" + startTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}

		String endTime = crm.getString("endTime", "");
		if(!StringUtil.isEmpty(endTime)){
			hql += " and jbdTravelPointLogAll.createTime <= to_date('" + endTime
			+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
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
