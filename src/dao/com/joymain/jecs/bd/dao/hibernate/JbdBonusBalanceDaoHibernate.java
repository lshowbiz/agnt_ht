
package com.joymain.jecs.bd.dao.hibernate;

import java.util.List;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.dao.JbdBonusBalanceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.hibernate.LockMode;

public class JbdBonusBalanceDaoHibernate extends BaseDaoHibernate implements JbdBonusBalanceDao {

    /**
     * @see com.joymain.jecs.bd.dao.JbdBonusBalanceDao#getJbdBonusBalances(com.joymain.jecs.bd.model.JbdBonusBalance)
     */
    public List getJbdBonusBalances(final JbdBonusBalance jbdBonusBalance) {
        return getHibernateTemplate().find("from JbdBonusBalance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jbdBonusBalance == null) {
            return getHibernateTemplate().find("from JbdBonusBalance");
        } else {
            // filter on properties set in the jbdBonusBalance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jbdBonusBalance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JbdBonusBalance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdBonusBalanceDao#getJbdBonusBalance(String userCode)
     */
    public JbdBonusBalance getJbdBonusBalance(final String userCode) {
        JbdBonusBalance jbdBonusBalance = (JbdBonusBalance) getHibernateTemplate().get(JbdBonusBalance.class, userCode);


        return jbdBonusBalance;
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdBonusBalanceDao#saveJbdBonusBalance(JbdBonusBalance jbdBonusBalance)
     */    
    public void saveJbdBonusBalance(final JbdBonusBalance jbdBonusBalance) {
        getHibernateTemplate().saveOrUpdate(jbdBonusBalance);
    }

    /**
     * @see com.joymain.jecs.bd.dao.JbdBonusBalanceDao#removeJbdBonusBalance(String userCode)
     */
    public void removeJbdBonusBalance(final String userCode) {
        getHibernateTemplate().delete(getJbdBonusBalance(userCode));
    }
    //added for getJbdBonusBalancesByCrm
    public List getJbdBonusBalancesByCrm(CommonRecord crm, Pager pager){
    	String hql = "select t.user_code,t.mi_user_name,t.create_time,b.status,b.check_time,b.check_user from jmi_member t ,jbd_bonus_balance b " +
    			"where t.user_code = b.user_code ";

    	String status=crm.getString("status", "");
    	if(!StringUtil.isEmpty(status)){
    		hql+=" and b.status='"+status+"'";
    	}
    	String userCode=crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		hql+=" and t.user_code='"+userCode+"'";
    	}

		String createBTime = crm.getString("createBTime", "");
		if (StringUtil.isDate(createBTime)) {
			hql += " and t.createTime >= to_date('" + createBTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String createETime = crm.getString("createETime", "");
		if (StringUtil.isDate(createETime)) {
			hql += " and t.createTime <= to_date('" + createETime
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
    	String companyCode=crm.getString("companyCode", "");
    	if(!StringUtil.isEmpty(companyCode)){
    		hql+=" and t.company_code='"+companyCode+"'";
    	}

    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by user_code desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsBySQL(hql, pager);
    }

	public JbdBonusBalance getJbdBonusBalanceForUpdate(String userCode) {
		JbdBonusBalance jbdBonusBalance = (JbdBonusBalance) getHibernateTemplate().get(JbdBonusBalance.class, userCode, LockMode.UPGRADE);
        return jbdBonusBalance;
	}
}
