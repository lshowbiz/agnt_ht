
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdUserCardList;
import com.joymain.jecs.bd.model.JbdUserCompanyCode;
import com.joymain.jecs.bd.dao.JbdUserCompanyCodeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JbdUserCompanyCodeDaoHibernate extends BaseDaoHibernate implements JbdUserCompanyCodeDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserCompanyCodeDao#getJbdUserCompanyCodes(com.joymain.jecs.bd.model.JbdUserCompanyCode)
     */
    public List getJbdUserCompanyCodes(final JbdUserCompanyCode jbdUserCompanyCode) {
        return getHibernateTemplate().find("from JbdUserCompanyCode");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdUserCompanyCode == null) {
            return getHibernateTemplate().find("from JbdUserCompanyCode");
        } else {
            // filter on properties set in the jbdUserCompanyCode
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdUserCompanyCode).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdUserCompanyCode.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserCompanyCodeDao#getJbdUserCompanyCode(BigDecimal id)
     */
    public JbdUserCompanyCode getJbdUserCompanyCode(final Long id) {
        JbdUserCompanyCode jbdUserCompanyCode = (JbdUserCompanyCode) getHibernateTemplate().get(JbdUserCompanyCode.class, id);
        if (jbdUserCompanyCode == null) {
            log.warn("uh oh, jbdUserCompanyCode with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JbdUserCompanyCode.class, id);
        }

        return jbdUserCompanyCode;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserCompanyCodeDao#saveJbdUserCompanyCode(JbdUserCompanyCode jbdUserCompanyCode)
     */    
    public void saveJbdUserCompanyCode(final JbdUserCompanyCode jbdUserCompanyCode) {
        getHibernateTemplate().saveOrUpdate(jbdUserCompanyCode);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdUserCompanyCodeDao#removeJbdUserCompanyCode(BigDecimal id)
     */
    public void removeJbdUserCompanyCode(final Long id) {
        getHibernateTemplate().delete(getJbdUserCompanyCode(id));
    }
    //added for getJbdUserCompanyCodesByCrm
    public List getJbdUserCompanyCodesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JbdUserCompanyCode jbdUserCompanyCode where 1=1";
    	
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "'";
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
	public JbdUserCompanyCode getPreviousJbdUserCompanyCode(String userCode,Integer wweek) {
		return (JbdUserCompanyCode) this.getObjectByHqlQuery("from JbdUserCompanyCode where userCode='"+userCode+"' and wweek < "+wweek +" order by wweek desc");
	}
	public JbdUserCompanyCode getJbdUserCompanyCodeByUserCodeAndWeek(String userCode, Integer wweek) {
		return (JbdUserCompanyCode) this.getObjectByHqlQuery("from JbdUserCompanyCode where userCode='"+userCode+"' and wweek="+wweek);
	}

	public List<JbdUserCompanyCode> getNextJbdUserCompanyCode(String userCode,Integer wweek) {
		return this.getHibernateTemplate().find("from JbdUserCompanyCode where userCode='"+userCode+"' and wweek > "+wweek +" order by wweek asc");
	}
}
