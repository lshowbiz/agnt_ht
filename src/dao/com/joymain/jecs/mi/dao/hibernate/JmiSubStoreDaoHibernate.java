
package com.joymain.jecs.mi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.dao.JmiSubStoreDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiSubStoreDaoHibernate extends BaseDaoHibernate implements JmiSubStoreDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiSubStoreDao#getJmiSubStores(com.joymain.jecs.mi.model.JmiSubStore)
     */
    public List getJmiSubStores(final JmiSubStore jmiSubStore) {
        return getHibernateTemplate().find("from JmiSubStore");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiSubStore == null) {
            return getHibernateTemplate().find("from JmiSubStore");
        } else {
            // filter on properties set in the jmiSubStore
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiSubStore).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiSubStore.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSubStoreDao#getJmiSubStore(Long id)
     */
    public JmiSubStore getJmiSubStore(final Long id) {
        JmiSubStore jmiSubStore = (JmiSubStore) getHibernateTemplate().get(JmiSubStore.class, id);
        if (jmiSubStore == null) {
            log.warn("uh oh, jmiSubStore with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JmiSubStore.class, id);
        }

        return jmiSubStore;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSubStoreDao#saveJmiSubStore(JmiSubStore jmiSubStore)
     */    
    public void saveJmiSubStore(final JmiSubStore jmiSubStore) {
        getHibernateTemplate().saveOrUpdate(jmiSubStore);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiSubStoreDao#removeJmiSubStore(Long id)
     */
    public void removeJmiSubStore(final Long id) {
        getHibernateTemplate().delete(getJmiSubStore(id));
    }
    //added for getJmiSubStoresByCrm
    public List getJmiSubStoresByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiSubStore jmiSubStore where 1=1";


		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and jmiMember.userCode = '" + userCode + "'";
		}
		
		String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode) && !"AA".equals(companyCode)) {
			hql += " and jmiMember.companyCode = '" + companyCode + "'";
		}

		String subStoreStatusNotNull = crm.getString("subStoreStatusNotNull", "");
		if ("subStoreStatusNotNull".equals(subStoreStatusNotNull)) {
			hql += " and jmiMember.subStoreStatus is not null";
		}

		String subStoreStatus = crm.getString("subStoreStatus", "");
		if (!StringUtils.isEmpty(subStoreStatus)) {
			hql += " and addrCheck ='"+subStoreStatus+"'";
		}
		String confirmStatus = crm.getString("confirmStatus", "");
		if (!StringUtils.isEmpty(confirmStatus)) {
			hql += " and addrConfirm ='"+confirmStatus+"'";
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
		
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public JmiSubStore getJmiSubStoresByUserCode(String userCode) {
		return (JmiSubStore) this.getObjectByHqlQuery("from JmiSubStore where jmiMember.userCode='"+userCode+"'");
	}
}
