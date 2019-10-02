
package com.joymain.jecs.vt.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.vt.model.VtVote;
import com.joymain.jecs.vt.dao.VtVoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class VtVoteDaoHibernate extends BaseDaoHibernate implements VtVoteDao {

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteDao#getVtVotes(com.joymain.jecs.vt.model.VtVote)
     */
    public List getVtVotes(final VtVote vtVote) {
        return getHibernateTemplate().find("from VtVote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (vtVote == null) {
            return getHibernateTemplate().find("from VtVote");
        } else {
            // filter on properties set in the vtVote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(vtVote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(VtVote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteDao#getVtVote(Long vtId)
     */
    public VtVote getVtVote(final Long vtId) {
        VtVote vtVote = (VtVote) getHibernateTemplate().get(VtVote.class, vtId);
        if (vtVote == null) {
            log.warn("uh oh, vtVote with vtId '" + vtId + "' not found...");
            throw new ObjectRetrievalFailureException(VtVote.class, vtId);
        }

        return vtVote;
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteDao#saveVtVote(VtVote vtVote)
     */    
    public void saveVtVote(final VtVote vtVote) {
        getHibernateTemplate().saveOrUpdate(vtVote);
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteDao#removeVtVote(Long vtId)
     */
    public void removeVtVote(final Long vtId) {
        getHibernateTemplate().delete(getVtVote(vtId));
    }
    //added for getVtVotesByCrm
    public List getVtVotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from VtVote vtVote where 1=1";

		String companyCode = crm.getString("companyCode", "");
		if(!StringUtil.isEmpty(companyCode)){
			hql += " and companyCode='"+companyCode+"'";
		}

		String subject = crm.getString("subject", "");
		if(!StringUtil.isEmpty(subject)){
			hql += " and subject='"+subject+"'";
		}
		String status = crm.getString("status", "");
		if(!StringUtil.isEmpty(status)){
			hql += " and status='"+status+"'";
		}
		String voterCompanyCode = crm.getString("voterCompanyCode", "");
		String voterUserType = crm.getString("voterUserType", "");
		if(!StringUtil.isEmpty(voterCompanyCode)&&!StringUtil.isEmpty(voterUserType)){
			hql += " and vtId in (select p.vtVote.vtId from VtVotePow p where p.companyCode = '"+voterCompanyCode+"' and p.userType = '"+voterUserType+"')";
		}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by vtId asc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
