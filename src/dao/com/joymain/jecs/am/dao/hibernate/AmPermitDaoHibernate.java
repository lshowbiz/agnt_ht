
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.am.dao.AmPermitDao;
import com.joymain.jecs.am.model.AmPermit;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AmPermitDaoHibernate extends BaseDaoHibernate implements AmPermitDao {

/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.am.dao.AmPermitDao#getAmPermits(com.joymain.jecs.am.model.AmPermit)
     */
    public List getAmPermits(final AmPermit amPermit) {
        return getHibernateTemplate().find("from AmPermit");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (amPermit == null) {
            return getHibernateTemplate().find("from AmPermit");
        } else {
            // filter on properties set in the amPermit
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(amPermit).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AmPermit.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.AmPermitDao#getAmPermit(String permitNo)
     */
    public AmPermit getAmPermit(final String permitNo) {
        AmPermit amPermit = (AmPermit) getHibernateTemplate().get(AmPermit.class, permitNo);
        if (amPermit == null) {
            log.warn("uh oh, amPermit with permitNo '" + permitNo + "' not found...");
            throw new ObjectRetrievalFailureException(AmPermit.class, permitNo);
        }

        return amPermit;
    }

    /**
     * @see com.joymain.jecs.am.dao.AmPermitDao#saveAmPermit(AmPermit amPermit)
     */    
    public void saveAmPermit(final AmPermit amPermit) {
        getHibernateTemplate().saveOrUpdate(amPermit);
    }

    /**
     * @see com.joymain.jecs.am.dao.AmPermitDao#removeAmPermit(String permitNo)
     */
    public void removeAmPermit(final String permitNo) {
        getHibernateTemplate().delete(getAmPermit(permitNo));
    }
    //added for getAmPermitsByCrm
    public List getAmPermitsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from AmPermit amPermit where 1=1";
    	
    	String permitClass = crm.getString("permitClass","");
    	if(StringUtils.isNotEmpty(permitClass)){
    		hql += " and amPermit.permitClass='"+permitClass+"'";
    	}
    	if(pager == null){
    		return this.getHibernateTemplate().find(hql);
    	}
    	if (!pager.getLimit().getSort().isSorted()) {
			//ȱʡ����
			hql += " order by permitNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
