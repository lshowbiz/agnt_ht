
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.JalLibraryPlate;
import com.joymain.jecs.al.dao.JalLibraryPlateDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JalLibraryPlateDaoHibernate extends BaseDaoHibernate implements JalLibraryPlateDao {

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryPlateDao#getJalLibraryPlates(com.joymain.jecs.al.model.JalLibraryPlate)
     */
    public List getJalLibraryPlates(final JalLibraryPlate jalLibraryPlate) {
        return getHibernateTemplate().find("from JalLibraryPlate");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jalLibraryPlate == null) {
            return getHibernateTemplate().find("from JalLibraryPlate");
        } else {
            // filter on properties set in the jalLibraryPlate
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jalLibraryPlate).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JalLibraryPlate.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryPlateDao#getJalLibraryPlate(Long plateId)
     */
    public JalLibraryPlate getJalLibraryPlate(final Long plateId) {
        JalLibraryPlate jalLibraryPlate = (JalLibraryPlate) getHibernateTemplate().get(JalLibraryPlate.class, plateId);
        if (jalLibraryPlate == null) {
            log.warn("uh oh, jalLibraryPlate with plateId '" + plateId + "' not found...");
            throw new ObjectRetrievalFailureException(JalLibraryPlate.class, plateId);
        }

        return jalLibraryPlate;
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryPlateDao#saveJalLibraryPlate(JalLibraryPlate jalLibraryPlate)
     */    
    public void saveJalLibraryPlate(final JalLibraryPlate jalLibraryPlate) {
        getHibernateTemplate().saveOrUpdate(jalLibraryPlate);
    }

    /**
     * @see com.joymain.jecs.al.dao.JalLibraryPlateDao#removeJalLibraryPlate(Long plateId)
     */
    public void removeJalLibraryPlate(final Long plateId) {
        getHibernateTemplate().delete(getJalLibraryPlate(plateId));
    }
    //added for getJalLibraryPlatesByCrm
    public List getJalLibraryPlatesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JalLibraryPlate jalLibraryPlate where 1=1";
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
			hql += " order by plateId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getAllJalLibraryPlates() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from JalLibraryPlate j order by j.plateIndex");
	}
}
