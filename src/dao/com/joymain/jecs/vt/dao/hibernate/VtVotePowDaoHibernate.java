
package com.joymain.jecs.vt.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.vt.model.VtVotePow;
import com.joymain.jecs.vt.dao.VtVotePowDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class VtVotePowDaoHibernate extends BaseDaoHibernate implements VtVotePowDao {

    /**
     * @see com.joymain.jecs.vt.dao.VtVotePowDao#getVtVotePows(com.joymain.jecs.vt.model.VtVotePow)
     */
    public List getVtVotePows(final VtVotePow vtVotePow) {
        return getHibernateTemplate().find("from VtVotePow");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (vtVotePow == null) {
            return getHibernateTemplate().find("from VtVotePow");
        } else {
            // filter on properties set in the vtVotePow
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(vtVotePow).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(VtVotePow.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVotePowDao#getVtVotePow(Long vpId)
     */
    public VtVotePow getVtVotePow(final Long vpId) {
        VtVotePow vtVotePow = (VtVotePow) getHibernateTemplate().get(VtVotePow.class, vpId);
        if (vtVotePow == null) {
            log.warn("uh oh, vtVotePow with vpId '" + vpId + "' not found...");
            throw new ObjectRetrievalFailureException(VtVotePow.class, vpId);
        }

        return vtVotePow;
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVotePowDao#saveVtVotePow(VtVotePow vtVotePow)
     */    
    public void saveVtVotePow(final VtVotePow vtVotePow) {
        getHibernateTemplate().saveOrUpdate(vtVotePow);
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVotePowDao#removeVtVotePow(Long vpId)
     */
    public void removeVtVotePow(final Long vpId) {
        getHibernateTemplate().delete(getVtVotePow(vpId));
    }
    //added for getVtVotePowsByCrm
    public List getVtVotePowsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from VtVotePow vtVotePow where 1=1";
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
			hql += " order by vpId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public void removeVtVotePowsByVtId(Long vtId) {
		String hqlQuery="delete from VtVotePow where vtVote.vtId="+vtId;
    	this.executeUpdate(hqlQuery);
	}
    
}
