
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiStore;
import com.joymain.jecs.mi.dao.JmiStoreDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiStoreDaoHibernate extends BaseDaoHibernate implements JmiStoreDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiStoreDao#getJmiStores(com.joymain.jecs.mi.model.JmiStore)
     */
    public List getJmiStores(final JmiStore jmiStore) {
        return getHibernateTemplate().find("from JmiStore");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiStore == null) {
            return getHibernateTemplate().find("from JmiStore");
        } else {
            // filter on properties set in the jmiStore
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiStore).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiStore.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiStoreDao#getJmiStore(Long id)
     */
    public JmiStore getJmiStore(final Long id) {
        JmiStore jmiStore = (JmiStore) getHibernateTemplate().get(JmiStore.class, id);
//        if (jmiStore == null) {
//            log.warn("uh oh, jmiStore with id '" + id + "' not found...");
//            throw new ObjectRetrievalFailureException(JmiStore.class, id);
//        }

        return jmiStore;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiStoreDao#saveJmiStore(JmiStore jmiStore)
     */    
    public void saveJmiStore(final JmiStore jmiStore) {
        getHibernateTemplate().saveOrUpdate(jmiStore);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiStoreDao#removeJmiStore(Long id)
     */
    public void removeJmiStore(final Long id) {
        getHibernateTemplate().delete(getJmiStore(id));
    }
    //added for getJmiStoresByCrm
    public List getJmiStoresByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiStore jmiStore where 1=1";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and jmiMember.userCode = '" + userCode + "'";
		}
		String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode) && !"AA".equals(companyCode)) {
			hql += " and jmiMember.companyCode = '" + companyCode + "'";
		}


		String timetype= crm.getString("timetype", "");
		
		String startTime = crm.getString("startTime", "");
		if (StringUtil.isDate(startTime)) {
			hql += " and "+timetype+" >= to_date('" + startTime+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String endTime = crm.getString("endTime", "");
		if (StringUtil.isDate(endTime)) {
			hql += " and "+timetype+" <= to_date('" + endTime + " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		

		String checkStatus = crm.getString("checkStatus", "");
		if (!StringUtils.isEmpty(checkStatus)) {
			hql += " and checkStatus ='"+checkStatus+"'";
		}
		String confirmStatus = crm.getString("confirmStatus", "");
		if (!StringUtils.isEmpty(confirmStatus)) {
			hql += " and confirmStatus ='"+confirmStatus+"'";
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
    
    
    public JmiStore getJmiStoreByUserCode(String userCode){
    	return (JmiStore) this.getObjectByHqlQuery("from JmiStore jmiStore where jmiMember.userCode='"+userCode+"'");
    }
}
