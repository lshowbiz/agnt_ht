
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.dao.JmiLevelNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiLevelNoteDaoHibernate extends BaseDaoHibernate implements JmiLevelNoteDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiLevelNoteDao#getJmiLevelNotes(com.joymain.jecs.mi.model.JmiLevelNote)
     */
    public List getJmiLevelNotes(final JmiLevelNote jmiLevelNote) {
        return getHibernateTemplate().find("from JmiLevelNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiLevelNote == null) {
            return getHibernateTemplate().find("from JmiLevelNote");
        } else {
            // filter on properties set in the jmiLevelNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiLevelNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiLevelNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLevelNoteDao#getJmiLevelNote(BigDecimal id)
     */
    public JmiLevelNote getJmiLevelNote(final Long id) {
        JmiLevelNote jmiLevelNote = (JmiLevelNote) getHibernateTemplate().get(JmiLevelNote.class, id);
/*        if (jmiLevelNote == null) {
            log.warn("uh oh, jmiLevelNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiLevelNote.class, id);
        }
*/
        return jmiLevelNote;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLevelNoteDao#saveJmiLevelNote(JmiLevelNote jmiLevelNote)
     */    
    public void saveJmiLevelNote(final JmiLevelNote jmiLevelNote) {
        getHibernateTemplate().saveOrUpdate(jmiLevelNote);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLevelNoteDao#removeJmiLevelNote(BigDecimal id)
     */
    public void removeJmiLevelNote(final Long id) {
        getHibernateTemplate().delete(getJmiLevelNote(id));
    }
    //added for getJmiLevelNotesByCrm
    public List getJmiLevelNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiLevelNote jmiLevelNote where 1=1";
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
