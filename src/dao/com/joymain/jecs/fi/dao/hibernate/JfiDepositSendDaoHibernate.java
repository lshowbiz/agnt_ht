
package com.joymain.jecs.fi.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.dao.JfiDepositSendDao;
import com.joymain.jecs.fi.model.JfiDepositSend;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JfiDepositSendDaoHibernate extends BaseDaoHibernate implements JfiDepositSendDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositSendDao#getJfiDepositSends(com.joymain.jecs.fi.model.JfiDepositSend)
     */
    public List getJfiDepositSends(final JfiDepositSend jfiDepositSend) {
        return getHibernateTemplate().find("from JfiDepositSend");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiDepositSend == null) {
            return getHibernateTemplate().find("from JfiDepositSend");
        } else {
            // filter on properties set in the jfiDepositSend
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiDepositSend).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiDepositSend.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositSendDao#getJfiDepositSend(BigDecimal id)
     */
    public JfiDepositSend getJfiDepositSend(final Long id) {
        JfiDepositSend jfiDepositSend = (JfiDepositSend) getHibernateTemplate().get(JfiDepositSend.class, id);
        if (jfiDepositSend == null) {
            log.warn("uh oh, jfiDepositSend with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JfiDepositSend.class, id);
        }

        return jfiDepositSend;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositSendDao#saveJfiDepositSend(JfiDepositSend jfiDepositSend)
     */    
    public void saveJfiDepositSend(final JfiDepositSend jfiDepositSend) {
        getHibernateTemplate().saveOrUpdate(jfiDepositSend);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositSendDao#removeJfiDepositSend(BigDecimal id)
     */
    public void removeJfiDepositSend(final Long id) {
        getHibernateTemplate().delete(getJfiDepositSend(id));
    }
    //added for getJfiDepositSendsByCrm
    public List getJfiDepositSendsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiDepositSend jfiDepositSend where 1=1";

	
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
			String status = crm.getString("status", "");
			if (!StringUtils.isEmpty(status)) {
				hql += " and status='" + status + "'";
			}
			return hql;
		 
	 }
	

	public BigDecimal getSumDepositMoney(CommonRecord crm) {
		String hql="select nvl(sum(round(depositMoney,2)),0) from JfiDepositSend  where 1=1";
		hql+=buildListSqlQuery(crm);
		return (BigDecimal)this.getHibernateTemplate().find(hql).get(0);
		
	}
	
    public void saveJfiDepositSend(List<JfiDepositSend> jfiDepositSends) {
        getHibernateTemplate().saveOrUpdateAll(jfiDepositSends);
    }

}
