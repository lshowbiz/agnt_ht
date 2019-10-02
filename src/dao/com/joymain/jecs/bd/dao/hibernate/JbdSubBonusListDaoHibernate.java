
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdSubBonusList;
import com.joymain.jecs.bd.dao.JbdSubBonusListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdSubBonusListDaoHibernate extends BaseDaoHibernate implements JbdSubBonusListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdSubBonusListDao#getJbdSubBonusLists(com.joymain.jecs.bd.model.JbdSubBonusList)
     */
    public List getJbdSubBonusLists(final JbdSubBonusList jbdSubBonusList) {
        return getHibernateTemplate().find("from JbdSubBonusList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdSubBonusList == null) {
            return getHibernateTemplate().find("from JbdSubBonusList");
        } else {
            // filter on properties set in the jbdSubBonusList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdSubBonusList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdSubBonusList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSubBonusListDao#getJbdSubBonusList(BigDecimal id)
     */
    public JbdSubBonusList getJbdSubBonusList(final Long id) {
        JbdSubBonusList jbdSubBonusList = (JbdSubBonusList) getHibernateTemplate().get(JbdSubBonusList.class, id);
/*        if (jbdSubBonusList == null) {
            log.warn("uh oh, jbdSubBonusList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdSubBonusList.class, id);
        }*/

        return jbdSubBonusList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSubBonusListDao#saveJbdSubBonusList(JbdSubBonusList jbdSubBonusList)
     */    
    public void saveJbdSubBonusList(final JbdSubBonusList jbdSubBonusList) {
        getHibernateTemplate().saveOrUpdate(jbdSubBonusList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdSubBonusListDao#removeJbdSubBonusList(BigDecimal id)
     */
    public void removeJbdSubBonusList(final Long id) {
        getHibernateTemplate().delete(getJbdSubBonusList(id));
    }
    //added for getJbdSubBonusListsByCrm
    public List getJbdSubBonusListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdSubBonusList jbdSubBonusList where 1=1";
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
    
    public List getJbdSubBonusListsBySql(CommonRecord crm, Pager pager){
    	String sql="select * from jbd_sub_bonus_list where 1=1";
    	String status=crm.getString("status", "");
    	if(!StringUtil.isEmpty(status)){
    		sql+=" and status='"+status+"'";
    	}
    	String treatedNo=crm.getString("treatedNo", "");
    	if(!StringUtil.isEmpty(treatedNo)){
    		sql+=" and treated_no='"+treatedNo+"'";
    	}
    	if (!pager.getLimit().getSort().isSorted()) {
			//sort
			sql += " order by amount desc";
		} else {
			sql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
    	return this.findObjectsBySQL(sql, pager);
    }
}
