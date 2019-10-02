
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.AlRegion;
import com.joymain.jecs.al.dao.AlRegionDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class AlRegionDaoHibernate extends BaseDaoHibernate implements AlRegionDao {

    /**
     * @see com.joymain.jecs.al.dao.AlRegionDao#getAlRegions(com.joymain.jecs.al.model.AlRegion)
     */
    public List getAlRegions(final AlRegion alRegion) {
        return getHibernateTemplate().find("from AlRegion");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (alRegion == null) {
            return getHibernateTemplate().find("from AlRegion");
        } else {
            // filter on properties set in the alRegion
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(alRegion).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AlRegion.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.AlRegionDao#getAlRegion(Long regionId)
     */
    public AlRegion getAlRegion(final Long regionId) {
        AlRegion alRegion = (AlRegion) getHibernateTemplate().get(AlRegion.class, regionId);
        if (alRegion == null) {
            log.warn("uh oh, alRegion with regionId '" + regionId + "' not found...");
            throw new ObjectRetrievalFailureException(AlRegion.class, regionId);
        }

        return alRegion;
    }

    /**
     * @see com.joymain.jecs.al.dao.AlRegionDao#saveAlRegion(AlRegion alRegion)
     */    
    public void saveAlRegion(final AlRegion alRegion) {
        getHibernateTemplate().saveOrUpdate(alRegion);
    }

    /**
     * @see com.joymain.jecs.al.dao.AlRegionDao#removeAlRegion(Long regionId)
     */
    public void removeAlRegion(final Long regionId) {
        getHibernateTemplate().delete(getAlRegion(regionId));
    }
}
