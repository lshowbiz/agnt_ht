
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiDepositList;
import com.joymain.jecs.fi.dao.JfiDepositListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiDepositListDaoHibernate extends BaseDaoHibernate implements JfiDepositListDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositListDao#getJfiDepositLists(com.joymain.jecs.fi.model.JfiDepositList)
     */
    public List getJfiDepositLists(final JfiDepositList jfiDepositList) {
        return getHibernateTemplate().find("from JfiDepositList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiDepositList == null) {
            return getHibernateTemplate().find("from JfiDepositList");
        } else {
            // filter on properties set in the jfiDepositList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiDepositList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiDepositList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositListDao#getJfiDepositList(BigDecimal id)
     */
    public JfiDepositList getJfiDepositList(final Long id) {
        JfiDepositList jfiDepositList = (JfiDepositList) getHibernateTemplate().get(JfiDepositList.class, id);
        if (jfiDepositList == null) {
            log.warn("uh oh, jfiDepositList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JfiDepositList.class, id);
        }

        return jfiDepositList;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositListDao#saveJfiDepositList(JfiDepositList jfiDepositList)
     */    
    public void saveJfiDepositList(final JfiDepositList jfiDepositList) {
        getHibernateTemplate().saveOrUpdate(jfiDepositList);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositListDao#removeJfiDepositList(BigDecimal id)
     */
    public void removeJfiDepositList(final Long id) {
        getHibernateTemplate().delete(getJfiDepositList(id));
    }
    //added for getJfiDepositListsByCrm
    public List getJfiDepositListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiDepositList jfiDepositList where 1=1";
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
