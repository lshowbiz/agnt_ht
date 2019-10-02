
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiSpecialList;
import com.joymain.jecs.mi.dao.JmiSpecialListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiSpecialListDaoHibernate extends BaseDaoHibernate implements JmiSpecialListDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiSpecialListDao#getJmiSpecialLists(com.joymain.jecs.mi.model.JmiSpecialList)
     */
    public List getJmiSpecialLists(final JmiSpecialList jmiSpecialList) {
        return getHibernateTemplate().find("from JmiSpecialList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiSpecialList == null) {
            return getHibernateTemplate().find("from JmiSpecialList");
        } else {
            // filter on properties set in the jmiSpecialList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiSpecialList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiSpecialList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSpecialListDao#getJmiSpecialList(BigDecimal id)
     */
    public JmiSpecialList getJmiSpecialList(final Long id) {
        JmiSpecialList jmiSpecialList = (JmiSpecialList) getHibernateTemplate().get(JmiSpecialList.class, id);
/*        if (jmiSpecialList == null) {
            log.warn("uh oh, jmiSpecialList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiSpecialList.class, id);
        }*/

        return jmiSpecialList;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSpecialListDao#saveJmiSpecialList(JmiSpecialList jmiSpecialList)
     */    
    public void saveJmiSpecialList(final JmiSpecialList jmiSpecialList) {
        getHibernateTemplate().saveOrUpdate(jmiSpecialList);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSpecialListDao#removeJmiSpecialList(BigDecimal id)
     */
    public void removeJmiSpecialList(final Long id) {
        getHibernateTemplate().delete(getJmiSpecialList(id));
    }
    //added for getJmiSpecialListsByCrm
    public List getJmiSpecialListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiSpecialList jmiSpecialList where 1=1";
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
    
    public void saveJmiSpecialList(List<JmiSpecialList> jmiSpecialList) {
        getHibernateTemplate().saveOrUpdateAll(jmiSpecialList);
    }
    public boolean getJmiSpecialExist(String userCode){
    	List list=this.findObjectsBySQL("select * from jmi_special_list where user_code='"+userCode+"' and sc_type='3' ");
    	if(list.isEmpty()){
    		return false;
    	}else{
    		return true;
    	}
    }
}
