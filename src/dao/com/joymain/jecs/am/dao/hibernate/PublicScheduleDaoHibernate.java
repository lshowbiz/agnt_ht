
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.am.dao.PublicScheduleDao;
import com.joymain.jecs.am.model.PublicSchedule;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PublicScheduleDaoHibernate extends BaseDaoHibernate implements PublicScheduleDao {

    /**
     * @see com.joymain.jecs.am.dao.PublicScheduleDao#getPublicSchedules(com.joymain.jecs.am.model.PublicSchedule)
     */
    public List getPublicSchedules(final PublicSchedule publicSchedule) {
        return getHibernateTemplate().find("from PublicSchedule");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (publicSchedule == null) {
            return getHibernateTemplate().find("from PublicSchedule");
        } else {
            // filter on properties set in the publicSchedule
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(publicSchedule).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(PublicSchedule.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.PublicScheduleDao#getPublicSchedule(Long psId)
     */
    public PublicSchedule getPublicSchedule(final Long psId) {
        PublicSchedule publicSchedule = (PublicSchedule) getHibernateTemplate().get(PublicSchedule.class, psId);
        if (publicSchedule == null) {
            log.warn("uh oh, publicSchedule with psId '" + psId + "' not found...");
            throw new ObjectRetrievalFailureException(PublicSchedule.class, psId);
        }

        return publicSchedule;
    }

    /**
     * @see com.joymain.jecs.am.dao.PublicScheduleDao#savePublicSchedule(PublicSchedule publicSchedule)
     */    
    public void savePublicSchedule(final PublicSchedule publicSchedule) {
        getHibernateTemplate().saveOrUpdate(publicSchedule);
    }

    /**
     * @see com.joymain.jecs.am.dao.PublicScheduleDao#removePublicSchedule(Long psId)
     */
    public void removePublicSchedule(final Long psId) {
        getHibernateTemplate().delete(getPublicSchedule(psId));
    }
    //added for getPublicSchedulesByCrm
    public List getPublicSchedulesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from PublicSchedule publicSchedule where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by psId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
