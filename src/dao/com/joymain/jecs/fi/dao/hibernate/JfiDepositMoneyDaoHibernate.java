
package com.joymain.jecs.fi.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiDepositMoneyDao;
import com.joymain.jecs.fi.model.JfiDepositMoney;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JfiDepositMoneyDaoHibernate extends BaseDaoHibernate implements JfiDepositMoneyDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositMoneyDao#getJfiDepositMoneys(com.joymain.jecs.fi.model.JfiDepositMoney)
     */
    public List getJfiDepositMoneys(final JfiDepositMoney jfiDepositMoney) {
        return getHibernateTemplate().find("from JfiDepositMoney");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiDepositMoney == null) {
            return getHibernateTemplate().find("from JfiDepositMoney");
        } else {
            // filter on properties set in the jfiDepositMoney
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiDepositMoney).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiDepositMoney.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositMoneyDao#getJfiDepositMoney(BigDecimal id)
     */
    public JfiDepositMoney getJfiDepositMoney(final Long id) {
        JfiDepositMoney jfiDepositMoney = (JfiDepositMoney) getHibernateTemplate().get(JfiDepositMoney.class, id);
        if (jfiDepositMoney == null) {
            log.warn("uh oh, jfiDepositMoney with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JfiDepositMoney.class, id);
        }

        return jfiDepositMoney;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositMoneyDao#saveJfiDepositMoney(JfiDepositMoney jfiDepositMoney)
     */    
    public void saveJfiDepositMoney(final JfiDepositMoney jfiDepositMoney) {
        getHibernateTemplate().saveOrUpdate(jfiDepositMoney);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositMoneyDao#removeJfiDepositMoney(BigDecimal id)
     */
    public void removeJfiDepositMoney(final Long id) {
        getHibernateTemplate().delete(getJfiDepositMoney(id));
    }
    //added for getJfiDepositMoneysByCrm
    public List getJfiDepositMoneysByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiDepositMoney jfiDepositMoney where 1=1";

    	hql+=buildListSqlQuery(crm);
    	
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    private String buildListSqlQuery(CommonRecord crm) {
		String hql="";
			String userCode = crm.getString("userCode", "");
			if (!StringUtils.isEmpty(userCode)) {
				hql += " and userCode='" + userCode + "'";
			}
			String userName = crm.getString("userName", "");
			if (!StringUtils.isEmpty(userName)) {
				hql += " and userName='" + userName + "'";
			}

			String wweek = crm.getString("wweek", "");
			if (!StringUtils.isEmpty(wweek)) {
				hql += " and wweek=" + wweek ;
			}

			String wyear = crm.getString("wyear", "");
			if (!StringUtils.isEmpty(wyear)) {
				hql += " and wyear=" + wyear ;
			}
			return hql;
		 
	 }
	

	public BigDecimal getSumDepositMoney(CommonRecord crm) {
		String hql="select nvl(sum(round(depositMoney,2)),0) from JfiDepositMoney  where 1=1";
		hql+=buildListSqlQuery(crm);
		return (BigDecimal)this.getHibernateTemplate().find(hql).get(0);
		
	}
    
    
}
