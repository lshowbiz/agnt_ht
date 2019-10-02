
package com.joymain.jecs.mi.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.dao.JmiValidLevelListDao;
import com.joymain.jecs.mi.model.JmiValidLevelList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JmiValidLevelListDaoHibernate extends BaseDaoHibernate implements JmiValidLevelListDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiValidLevelListDao#getJmiValidLevelLists(com.joymain.jecs.mi.model.JmiValidLevelList)
     */
    public List getJmiValidLevelLists(final JmiValidLevelList jmiValidLevelList) {
        return getHibernateTemplate().find("from JmiValidLevelList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiValidLevelList == null) {
            return getHibernateTemplate().find("from JmiValidLevelList");
        } else {
            // filter on properties set in the jmiValidLevelList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiValidLevelList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiValidLevelList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiValidLevelListDao#getJmiValidLevelList(BigDecimal id)
     */
    public JmiValidLevelList getJmiValidLevelList(final Long id) {
        JmiValidLevelList jmiValidLevelList = (JmiValidLevelList) getHibernateTemplate().get(JmiValidLevelList.class, id);
/*        if (jmiValidLevelList == null) {
            log.warn("uh oh, jmiValidLevelList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiValidLevelList.class, id);
        }*/

        return jmiValidLevelList;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiValidLevelListDao#saveJmiValidLevelList(JmiValidLevelList jmiValidLevelList)
     */    
    public void saveJmiValidLevelList(final JmiValidLevelList jmiValidLevelList) {
        getHibernateTemplate().saveOrUpdate(jmiValidLevelList);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiValidLevelListDao#removeJmiValidLevelList(BigDecimal id)
     */
    public void removeJmiValidLevelList(final Long id) {
        getHibernateTemplate().delete(getJmiValidLevelList(id));
    }
    //added for getJmiValidLevelListsByCrm
    public List getJmiValidLevelListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiValidLevelList jmiValidLevelList where 1=1";
		String userCode = crm.getString("userCode", "");
		if(!StringUtil.isEmpty(userCode)){
			hql += " and userCode='"+userCode+"'";
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

	@Override
	public JmiValidLevelList getJmiValidLevelListsByUserCode(String userCode, Integer wweek) {
		return (JmiValidLevelList) this.getObjectByHqlQuery("from JmiValidLevelList where userCode='"+userCode+"' and wweek="+wweek);
	}
}
