
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdYkJiandianList;
import com.joymain.jecs.bd.dao.JbdYkJiandianListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdYkJiandianListDaoHibernate extends BaseDaoHibernate implements JbdYkJiandianListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdYkJiandianListDao#getJbdYkJiandianLists(com.joymain.jecs.bd.model.JbdYkJiandianList)
     */
    public List getJbdYkJiandianLists(final JbdYkJiandianList jbdYkJiandianList) {
        return getHibernateTemplate().find("from JbdYkJiandianList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdYkJiandianList == null) {
            return getHibernateTemplate().find("from JbdYkJiandianList");
        } else {
            // filter on properties set in the jbdYkJiandianList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdYkJiandianList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdYkJiandianList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdYkJiandianListDao#getJbdYkJiandianList(BigDecimal id)
     */
    public JbdYkJiandianList getJbdYkJiandianList(final Long id) {
        JbdYkJiandianList jbdYkJiandianList = (JbdYkJiandianList) getHibernateTemplate().get(JbdYkJiandianList.class, id);
        if (jbdYkJiandianList == null) {
            log.warn("uh oh, jbdYkJiandianList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdYkJiandianList.class, id);
        }

        return jbdYkJiandianList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdYkJiandianListDao#saveJbdYkJiandianList(JbdYkJiandianList jbdYkJiandianList)
     */    
    public void saveJbdYkJiandianList(final JbdYkJiandianList jbdYkJiandianList) {
        getHibernateTemplate().saveOrUpdate(jbdYkJiandianList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdYkJiandianListDao#removeJbdYkJiandianList(BigDecimal id)
     */
    public void removeJbdYkJiandianList(final Long id) {
        getHibernateTemplate().delete(getJbdYkJiandianList(id));
    }
    //added for getJbdYkJiandianListsByCrm
    public List getJbdYkJiandianListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdYkJiandianList jbdYkJiandianList where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
    	
    	//crm.addField("userCodeDetail", userCodeDetail);
    	String userCodeDetail=crm.getString("userCodeDetail", "");
    	if(!StringUtil.isEmpty(userCodeDetail)){
    		hql+=" and userCode='"+userCodeDetail+"'";
    	}
		//crm.addField("wweekDetail", wweekDetail);
    	String wweekDetail=crm.getString("wweekDetail", "");
    	if(!StringUtil.isEmpty(wweekDetail)){
    		hql+=" and wweek="+wweekDetail;
    	}
    	
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
