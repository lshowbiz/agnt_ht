
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiMemberCompanyNote;
import com.joymain.jecs.mi.dao.JmiMemberCompanyNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiMemberCompanyNoteDaoHibernate extends BaseDaoHibernate implements JmiMemberCompanyNoteDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberCompanyNoteDao#getJmiMemberCompanyNotes(com.joymain.jecs.mi.model.JmiMemberCompanyNote)
     */
    public List getJmiMemberCompanyNotes(final JmiMemberCompanyNote jmiMemberCompanyNote) {
        return getHibernateTemplate().find("from JmiMemberCompanyNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiMemberCompanyNote == null) {
            return getHibernateTemplate().find("from JmiMemberCompanyNote");
        } else {
            // filter on properties set in the jmiMemberCompanyNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiMemberCompanyNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiMemberCompanyNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberCompanyNoteDao#getJmiMemberCompanyNote(long id)
     */
    public JmiMemberCompanyNote getJmiMemberCompanyNote(final Long id) {
        JmiMemberCompanyNote jmiMemberCompanyNote = (JmiMemberCompanyNote) getHibernateTemplate().get(JmiMemberCompanyNote.class, id);
        if (jmiMemberCompanyNote == null) {
            log.warn("uh oh, jmiMemberCompanyNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiMemberCompanyNote.class, id);
        }

        return jmiMemberCompanyNote;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberCompanyNoteDao#saveJmiMemberCompanyNote(JmiMemberCompanyNote jmiMemberCompanyNote)
     */    
    public void saveJmiMemberCompanyNote(final JmiMemberCompanyNote jmiMemberCompanyNote) {
        getHibernateTemplate().saveOrUpdate(jmiMemberCompanyNote);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiMemberCompanyNoteDao#removeJmiMemberCompanyNote(long id)
     */
    public void removeJmiMemberCompanyNote(final Long id) {
        getHibernateTemplate().delete(getJmiMemberCompanyNote(id));
    }
    //added for getJmiMemberCompanyNotesByCrm
    public List getJmiMemberCompanyNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiMemberCompanyNote jmiMemberCompanyNote where 1=1";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "'";
		}
		
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
