
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiCustomerLevelNote;
import com.joymain.jecs.mi.dao.JmiCustomerLevelNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiCustomerLevelNoteDaoHibernate extends BaseDaoHibernate implements JmiCustomerLevelNoteDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiCustomerLevelNoteDao#getJmiCustomerLevelNotes(com.joymain.jecs.mi.model.JmiCustomerLevelNote)
     */
    public List getJmiCustomerLevelNotes(final JmiCustomerLevelNote jmiCustomerLevelNote) {
        return getHibernateTemplate().find("from JmiCustomerLevelNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiCustomerLevelNote == null) {
            return getHibernateTemplate().find("from JmiCustomerLevelNote");
        } else {
            // filter on properties set in the jmiCustomerLevelNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiCustomerLevelNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiCustomerLevelNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiCustomerLevelNoteDao#getJmiCustomerLevelNote(Long id)
     */
    public JmiCustomerLevelNote getJmiCustomerLevelNote(final Long id) {
        JmiCustomerLevelNote jmiCustomerLevelNote = (JmiCustomerLevelNote) getHibernateTemplate().get(JmiCustomerLevelNote.class, id);
        if (jmiCustomerLevelNote == null) {
            log.warn("uh oh, jmiCustomerLevelNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiCustomerLevelNote.class, id);
        }

        return jmiCustomerLevelNote;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiCustomerLevelNoteDao#saveJmiCustomerLevelNote(JmiCustomerLevelNote jmiCustomerLevelNote)
     */    
    public void saveJmiCustomerLevelNote(final JmiCustomerLevelNote jmiCustomerLevelNote) {
        getHibernateTemplate().saveOrUpdate(jmiCustomerLevelNote);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiCustomerLevelNoteDao#removeJmiCustomerLevelNote(Long id)
     */
    public void removeJmiCustomerLevelNote(final Long id) {
        getHibernateTemplate().delete(getJmiCustomerLevelNote(id));
    }
    //added for getJmiCustomerLevelNotesByCrm
    public List getJmiCustomerLevelNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiCustomerLevelNote jmiCustomerLevelNote where 1=1";
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "' ";
		}
		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode)) {
			hql += " and companyCode = '" + companyCode + "' ";
		}
		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql += " and status = '" + status + "' ";
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
