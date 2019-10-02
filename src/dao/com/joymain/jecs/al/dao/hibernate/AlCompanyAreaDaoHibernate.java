
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.AlCompanyArea;
import com.joymain.jecs.al.dao.AlCompanyAreaDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class AlCompanyAreaDaoHibernate extends BaseDaoHibernate implements AlCompanyAreaDao {

/**
		public List getPiProductsByHql(String hql){
			return getHibernateTemplate().find(hql);
		}

		public List getPiProductsBySql(String sql){
		}
**/		
    /**
     * @see com.joymain.jecs.al.dao.AlCompanyAreaDao#getAlCompanyAreas(com.joymain.jecs.al.model.AlCompanyArea)
     */
    public List getAlCompanyAreas(final AlCompanyArea alCompanyArea) {
        return getHibernateTemplate().find("from AlCompanyArea");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (alCompanyArea == null) {
            return getHibernateTemplate().find("from AlCompanyArea");
        } else {
            // filter on properties set in the alCompanyArea
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(alCompanyArea).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AlCompanyArea.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCompanyAreaDao#getAlCompanyArea(Long id)
     */
    public AlCompanyArea getAlCompanyArea(final Long id) {
        AlCompanyArea alCompanyArea = (AlCompanyArea) getHibernateTemplate().get(AlCompanyArea.class, id);
        if (alCompanyArea == null) {
            log.warn("uh oh, alCompanyArea with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(AlCompanyArea.class, id);
        }

        return alCompanyArea;
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCompanyAreaDao#saveAlCompanyArea(AlCompanyArea alCompanyArea)
     */    
    public void saveAlCompanyArea(final AlCompanyArea alCompanyArea) {
        getHibernateTemplate().saveOrUpdate(alCompanyArea);
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCompanyAreaDao#removeAlCompanyArea(Long id)
     */
    public void removeAlCompanyArea(final Long id) {
        getHibernateTemplate().delete(getAlCompanyArea(id));
    }
    //added for getAlCompanyAreasByCrm
    public List getAlCompanyAreasByCrm(CommonRecord crm, Pager pager){
    	String hql = "from AlCompanyArea alCompanyArea where 1=1";
		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
