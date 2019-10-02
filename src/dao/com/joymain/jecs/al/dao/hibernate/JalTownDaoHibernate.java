
package com.joymain.jecs.al.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.dao.JalTownDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JalTownDaoHibernate extends BaseDaoHibernate implements JalTownDao {

    /**
     * @see com.joymain.jecs.al.dao.JalTownDao#getJalTowns(com.joymain.jecs.al.model.JalTown)
     */
    public List getJalTowns(final JalTown jalTown) {
        return getHibernateTemplate().find("from JalTown");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jalTown == null) {
            return getHibernateTemplate().find("from JalTown");
        } else {
            // filter on properties set in the jalTown
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jalTown).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JalTown.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.JalTownDao#getJalTown(Long townId)
     */
    public JalTown getJalTown(final Long townId) {
        JalTown jalTown = (JalTown) getHibernateTemplate().get(JalTown.class, townId);
//        if (jalTown == null) {
//            log.warn("uh oh, jalTown with townId '" + townId + "' not found...");
//            throw new ObjectRetrievalFailureException(JalTown.class, townId);
//        }

        return jalTown;
    }

    /**
     * @see com.joymain.jecs.al.dao.JalTownDao#saveJalTown(JalTown jalTown)
     */    
    public void saveJalTown(final JalTown jalTown) {
        getHibernateTemplate().saveOrUpdate(jalTown);
    }

    /**
     * @see com.joymain.jecs.al.dao.JalTownDao#removeJalTown(Long townId)
     */
    public void removeJalTown(final Long townId) {
        getHibernateTemplate().delete(getJalTown(townId));
    }
    //added for getJalTownsByCrm
    public List getJalTownsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JalTown jalTown where 1=1";
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
			hql += " order by townId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getJalTownByDistrictId(Long districtId) {
		if(districtId==null){
			return new ArrayList();
		}
		return this.getHibernateTemplate().find("from JalTown where alDistrict.districtId=? order by townCode",districtId);
	}
}
