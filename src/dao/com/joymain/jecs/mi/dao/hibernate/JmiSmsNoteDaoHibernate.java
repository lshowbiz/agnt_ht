
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiSmsNote;
import com.joymain.jecs.mi.dao.JmiSmsNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiSmsNoteDaoHibernate extends BaseDaoHibernate implements JmiSmsNoteDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiSmsNoteDao#getJmiSmsNotes(com.joymain.jecs.mi.model.JmiSmsNote)
     */
    public List getJmiSmsNotes(final JmiSmsNote jmiSmsNote) {
        return getHibernateTemplate().find("from JmiSmsNote");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiSmsNote == null) {
            return getHibernateTemplate().find("from JmiSmsNote");
        } else {
            // filter on properties set in the jmiSmsNote
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiSmsNote).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiSmsNote.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSmsNoteDao#getJmiSmsNote(BigDecimal id)
     */
    public JmiSmsNote getJmiSmsNote(final BigDecimal id) {
        JmiSmsNote jmiSmsNote = (JmiSmsNote) getHibernateTemplate().get(JmiSmsNote.class, id);
        if (jmiSmsNote == null) {
            log.warn("uh oh, jmiSmsNote with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiSmsNote.class, id);
        }

        return jmiSmsNote;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSmsNoteDao#saveJmiSmsNote(JmiSmsNote jmiSmsNote)
     */    
    public void saveJmiSmsNote(final JmiSmsNote jmiSmsNote) {
        getHibernateTemplate().saveOrUpdate(jmiSmsNote);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSmsNoteDao#removeJmiSmsNote(BigDecimal id)
     */
    public void removeJmiSmsNote(final BigDecimal id) {
        getHibernateTemplate().delete(getJmiSmsNote(id));
    }
    //added for getJmiSmsNotesByCrm
    public List getJmiSmsNotesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiSmsNote jmiSmsNote where 1=1";
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
    
    public JmiSmsNote getJmiSmsNoteByUserCode(String userCode){
    	String hqlQuery="from JmiSmsNote where userCode='"+userCode+"' order by createTime desc";
    	
    	return (JmiSmsNote)this.getObjectByHqlQuery(hqlQuery);
    }
}
