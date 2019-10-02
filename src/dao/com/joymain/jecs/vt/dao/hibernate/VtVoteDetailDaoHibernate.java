
package com.joymain.jecs.vt.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.vt.model.VtVoteDetail;
import com.joymain.jecs.vt.dao.VtVoteDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class VtVoteDetailDaoHibernate extends BaseDaoHibernate implements VtVoteDetailDao {

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteDetailDao#getVtVoteDetails(com.joymain.jecs.vt.model.VtVoteDetail)
     */
    public List getVtVoteDetails(final VtVoteDetail vtVoteDetail) {
        return getHibernateTemplate().find("from VtVoteDetail");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (vtVoteDetail == null) {
            return getHibernateTemplate().find("from VtVoteDetail");
        } else {
            // filter on properties set in the vtVoteDetail
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(vtVoteDetail).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(VtVoteDetail.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteDetailDao#getVtVoteDetail(Long vdId)
     */
    public VtVoteDetail getVtVoteDetail(final Long vdId) {
        VtVoteDetail vtVoteDetail = (VtVoteDetail) getHibernateTemplate().get(VtVoteDetail.class, vdId);
        if (vtVoteDetail == null) {
            log.warn("uh oh, vtVoteDetail with vdId '" + vdId + "' not found...");
            throw new ObjectRetrievalFailureException(VtVoteDetail.class, vdId);
        }

        return vtVoteDetail;
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteDetailDao#saveVtVoteDetail(VtVoteDetail vtVoteDetail)
     */    
    public void saveVtVoteDetail(final VtVoteDetail vtVoteDetail) {
        getHibernateTemplate().saveOrUpdate(vtVoteDetail);
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteDetailDao#removeVtVoteDetail(Long vdId)
     */
    public void removeVtVoteDetail(final Long vdId) {
        getHibernateTemplate().delete(getVtVoteDetail(vdId));
    }
    //added for getVtVoteDetailsByCrm
    public List getVtVoteDetailsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from VtVoteDetail vtVoteDetail where 1=1";
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
			hql += " order by vdId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public void removeVtVoteDetailByVtId(Long vtId) {
		String hqlQuery="delete from VtVoteDetail where vtVote.vtId="+vtId;
    	this.executeUpdate(hqlQuery);
	}
}
