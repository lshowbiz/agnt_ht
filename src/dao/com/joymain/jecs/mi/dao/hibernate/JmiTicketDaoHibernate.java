
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiTicket;
import com.joymain.jecs.mi.dao.JmiTicketDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiTicketDaoHibernate extends BaseDaoHibernate implements JmiTicketDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiTicketDao#getJmiTickets(com.joymain.jecs.mi.model.JmiTicket)
     */
    public List getJmiTickets(final JmiTicket jmiTicket) {
        return getHibernateTemplate().find("from JmiTicket");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiTicket == null) {
            return getHibernateTemplate().find("from JmiTicket");
        } else {
            // filter on properties set in the jmiTicket
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiTicket).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiTicket.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiTicketDao#getJmiTicket(BigDecimal id)
     */
    public JmiTicket getJmiTicket(final Long id) {
        JmiTicket jmiTicket = (JmiTicket) getHibernateTemplate().get(JmiTicket.class, id);
/*        if (jmiTicket == null) {
            log.warn("uh oh, jmiTicket with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiTicket.class, id);
        }*/

        return jmiTicket;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiTicketDao#saveJmiTicket(JmiTicket jmiTicket)
     */    
    public void saveJmiTicket(final JmiTicket jmiTicket) {
        getHibernateTemplate().saveOrUpdate(jmiTicket);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiTicketDao#removeJmiTicket(BigDecimal id)
     */
    public void removeJmiTicket(final Long id) {
        getHibernateTemplate().delete(getJmiTicket(id));
    }
    //added for getJmiTicketsByCrm
    public List getJmiTicketsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiTicket jmiTicket where 1=1";
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
    
    public void saveJmiTickets(List<JmiTicket> jmiTickets){
    	this.getHibernateTemplate().saveOrUpdateAll(jmiTickets);
    }
}
