
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.ScheduleManage;
import com.joymain.jecs.am.dao.ScheduleManageDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class ScheduleManageDaoHibernate extends BaseDaoHibernate implements ScheduleManageDao {

    /**
     * @see com.joymain.jecs.am.dao.ScheduleManageDao#getScheduleManages(com.joymain.jecs.am.model.ScheduleManage)
     */
    public List getScheduleManages(final ScheduleManage scheduleManage) {
        return getHibernateTemplate().find("from ScheduleManage");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (scheduleManage == null) {
            return getHibernateTemplate().find("from ScheduleManage");
        } else {
            // filter on properties set in the scheduleManage
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(scheduleManage).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(ScheduleManage.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.ScheduleManageDao#getScheduleManage(BigDecimal id)
     */
    public ScheduleManage getScheduleManage(final BigDecimal id) {
        ScheduleManage scheduleManage = (ScheduleManage) getHibernateTemplate().get(ScheduleManage.class, id);
        if (scheduleManage == null) {
            log.warn("uh oh, scheduleManage with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(ScheduleManage.class, id);
        }

        return scheduleManage;
    }

    /**
     * @see com.joymain.jecs.am.dao.ScheduleManageDao#saveScheduleManage(ScheduleManage scheduleManage)
     */    
    public void saveScheduleManage(final ScheduleManage scheduleManage) {
        getHibernateTemplate().saveOrUpdate(scheduleManage);
    }

    /**
     * @see com.joymain.jecs.am.dao.ScheduleManageDao#removeScheduleManage(BigDecimal id)
     */
    public void removeScheduleManage(final BigDecimal id) {
        getHibernateTemplate().delete(getScheduleManage(id));
    }
    //added for getScheduleManagesByCrm
    public List getScheduleManagesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from ScheduleManage scheduleManage where 1=1";
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
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
