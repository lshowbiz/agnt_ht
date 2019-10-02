
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdGradeNote;
import com.joymain.jecs.bd.dao.JbdGradeNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdGradeNoteDaoHibernate extends BaseDaoHibernate implements JbdGradeNoteDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdGradeNoteDao#getJbdGradeNotes(com.joymain.jecs.bd.model.JbdGradeNote)
     */
    public List getJbdGradeNotes(final JbdGradeNote jbdGradeNote) {
        return getHibernateTemplate().find("from JbdGradeNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdGradeNote == null) {
            return getHibernateTemplate().find("from JbdGradeNote");
        } else {
            // filter on properties set in the jbdGradeNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdGradeNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdGradeNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdGradeNoteDao#getJbdGradeNote(Long id)
     */
    public JbdGradeNote getJbdGradeNote(final Long id) {
        JbdGradeNote jbdGradeNote = (JbdGradeNote) getHibernateTemplate().get(JbdGradeNote.class, id);
        if (jbdGradeNote == null) {
            log.warn("uh oh, jbdGradeNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdGradeNote.class, id);
        }

        return jbdGradeNote;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdGradeNoteDao#saveJbdGradeNote(JbdGradeNote jbdGradeNote)
     */    
    public void saveJbdGradeNote(final JbdGradeNote jbdGradeNote) {
        getHibernateTemplate().saveOrUpdate(jbdGradeNote);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdGradeNoteDao#removeJbdGradeNote(Long id)
     */
    public void removeJbdGradeNote(final Long id) {
        getHibernateTemplate().delete(getJbdGradeNote(id));
    }
    //added for getJbdGradeNotesByCrm
    public List getJbdGradeNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdGradeNote jbdGradeNote where 1=1";
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
