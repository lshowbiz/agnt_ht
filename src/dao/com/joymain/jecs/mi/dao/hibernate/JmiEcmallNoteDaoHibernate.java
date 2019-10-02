
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiEcmallNote;
import com.joymain.jecs.mi.dao.JmiEcmallNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiEcmallNoteDaoHibernate extends BaseDaoHibernate implements JmiEcmallNoteDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiEcmallNoteDao#getJmiEcmallNotes(com.joymain.jecs.mi.model.JmiEcmallNote)
     */
    public List getJmiEcmallNotes(final JmiEcmallNote jmiEcmallNote) {
        return getHibernateTemplate().find("from JmiEcmallNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiEcmallNote == null) {
            return getHibernateTemplate().find("from JmiEcmallNote");
        } else {
            // filter on properties set in the jmiEcmallNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiEcmallNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiEcmallNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiEcmallNoteDao#getJmiEcmallNote(BigDecimal id)
     */
    public JmiEcmallNote getJmiEcmallNote(final BigDecimal id) {
        JmiEcmallNote jmiEcmallNote = (JmiEcmallNote) getHibernateTemplate().get(JmiEcmallNote.class, id);
        if (jmiEcmallNote == null) {
            log.warn("uh oh, jmiEcmallNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiEcmallNote.class, id);
        }

        return jmiEcmallNote;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiEcmallNoteDao#saveJmiEcmallNote(JmiEcmallNote jmiEcmallNote)
     */    
    public void saveJmiEcmallNote(final JmiEcmallNote jmiEcmallNote) {
        getHibernateTemplate().saveOrUpdate(jmiEcmallNote);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiEcmallNoteDao#removeJmiEcmallNote(BigDecimal id)
     */
    public void removeJmiEcmallNote(final BigDecimal id) {
        getHibernateTemplate().delete(getJmiEcmallNote(id));
    }
    //added for getJmiEcmallNotesByCrm
    public List getJmiEcmallNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiEcmallNote jmiEcmallNote where 1=1";
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
