
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdNetworkList;
import com.joymain.jecs.bd.dao.JbdNetworkListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdNetworkListDaoHibernate extends BaseDaoHibernate implements JbdNetworkListDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdNetworkListDao#getJbdNetworkLists(com.joymain.jecs.bd.model.JbdNetworkList)
     */
    public List getJbdNetworkLists(final JbdNetworkList jbdNetworkList) {
        return getHibernateTemplate().find("from JbdNetworkList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdNetworkList == null) {
            return getHibernateTemplate().find("from JbdNetworkList");
        } else {
            // filter on properties set in the jbdNetworkList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdNetworkList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdNetworkList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdNetworkListDao#getJbdNetworkList(BigDecimal id)
     */
    public JbdNetworkList getJbdNetworkList(final BigDecimal id) {
        JbdNetworkList jbdNetworkList = (JbdNetworkList) getHibernateTemplate().get(JbdNetworkList.class, id);
        if (jbdNetworkList == null) {
            log.warn("uh oh, jbdNetworkList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdNetworkList.class, id);
        }

        return jbdNetworkList;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdNetworkListDao#saveJbdNetworkList(JbdNetworkList jbdNetworkList)
     */    
    public void saveJbdNetworkList(final JbdNetworkList jbdNetworkList) {
        getHibernateTemplate().saveOrUpdate(jbdNetworkList);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdNetworkListDao#removeJbdNetworkList(BigDecimal id)
     */
    public void removeJbdNetworkList(final BigDecimal id) {
        getHibernateTemplate().delete(getJbdNetworkList(id));
    }
    //added for getJbdNetworkListsByCrm
    public List getJbdNetworkListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdNetworkList jbdNetworkList where 1=1";


		hql+=getHql(crm);
    	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    private String getHql(CommonRecord crm){
    		String hql="";
    		String userCode=crm.getString("userCode", "");
        	if(!StringUtil.isEmpty(userCode)){
        		hql+=" and userCode='"+userCode+"'";
        	}


    		String startWeek=crm.getString("startWeek", "");
        	if(!StringUtil.isEmpty(startWeek)){
        		hql+=" and wweek >='"+startWeek+"'";
        	}

        	String endWeek=crm.getString("endWeek", "");
        	if(!StringUtil.isEmpty(endWeek)){
        		hql+=" and wweek <='"+endWeek+"'";
        	}
        	
        	

        	String companyCode =crm.getString("companyCode", "");
        	if(!"AA".equals(companyCode)&&!StringUtil.isEmpty(companyCode)){
        		hql+=" and companyCode='"+companyCode+"'";
        	}
        	return hql;
    }
    
	public Object[] getJbdNetworkListsByCrmSum(CommonRecord crm) {
		String hql = "select sum(networkMoney),sum(balanceMoney),sum(deductMoney) from JbdNetworkList jbdNetworkList where 1=1";

		hql+=getHql(crm);

		return (Object[]) this.getHibernateTemplate().find(hql).get(0);
	}
}
