
package com.joymain.jecs.vt.dao.hibernate;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.vt.model.VtVoteNote;
import com.joymain.jecs.vt.dao.VtVoteNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class VtVoteNoteDaoHibernate extends BaseDaoHibernate implements VtVoteNoteDao {

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteNoteDao#getVtVoteNotes(com.joymain.jecs.vt.model.VtVoteNote)
     */
    public List getVtVoteNotes(final VtVoteNote vtVoteNote) {
        return getHibernateTemplate().find("from VtVoteNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (vtVoteNote == null) {
            return getHibernateTemplate().find("from VtVoteNote");
        } else {
            // filter on properties set in the vtVoteNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(vtVoteNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(VtVoteNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteNoteDao#getVtVoteNote(Long vnId)
     */
    public VtVoteNote getVtVoteNote(final Long vnId) {
        VtVoteNote vtVoteNote = (VtVoteNote) getHibernateTemplate().get(VtVoteNote.class, vnId);
        if (vtVoteNote == null) {
            log.warn("uh oh, vtVoteNote with vnId '" + vnId + "' not found...");
            throw new ObjectRetrievalFailureException(VtVoteNote.class, vnId);
        }

        return vtVoteNote;
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteNoteDao#saveVtVoteNote(VtVoteNote vtVoteNote)
     */    
    public void saveVtVoteNote(final VtVoteNote vtVoteNote) {
        getHibernateTemplate().saveOrUpdate(vtVoteNote);
    }

    /**
     * @see com.joymain.jecs.vt.dao.VtVoteNoteDao#removeVtVoteNote(Long vnId)
     */
    public void removeVtVoteNote(final Long vnId) {
        getHibernateTemplate().delete(getVtVoteNote(vnId));
    }
    //added for getVtVoteNotesByCrm
    public List getVtVoteNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from VtVoteNote vtVoteNote where 1=1";
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
			hql += " order by vnId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public Long getUserNotes(String userCode, Long vtId) {
		String sql="select count(*) as count from vt_vote t,vt_vote_detail d ,vt_vote_note n " +
				"where t.vt_id=d.vt_id and d.vd_id=n.vd_id and n.user_code='"+userCode+"'  and t.vt_id="+vtId;
		return new Long((String)((Map)this.findObjectsBySQL(sql).get(0)).get("count"));
	}

	public Long getUserNotesCount(Long vtId) {
		String sql="select count(*) as count from vt_vote_detail dd,vt_vote_note nn where dd.vd_id=nn.vd_id and dd.vt_id="+vtId;
		return new Long((String)((Map)this.findObjectsBySQL(sql).get(0)).get("count"));
	}

	public List getUserNotesForVtVote(Long vtId) {
		String sql="select d.vd_id,count(d.vd_id) as note_count from vt_vote_detail d,vt_vote_note n " +
				"where d.vd_id=n.vd_id and d.vt_id="+vtId+" group by d.vd_id";
		return this.findObjectsBySQL(sql);
	}
}
