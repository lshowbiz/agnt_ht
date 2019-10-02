
package com.joymain.jecs.am.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.AmMessagePermit;
import com.joymain.jecs.am.dao.AmMessagePermitDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class AmMessagePermitDaoHibernate extends BaseDaoHibernate implements AmMessagePermitDao {

    /**
     * @see com.joymain.jecs.am.dao.AmMessagePermitDao#getAmMessagePermits(com.joymain.jecs.am.model.AmMessagePermit)
     */
    public List getAmMessagePermits(final AmMessagePermit amMessagePermit) {
        return getHibernateTemplate().find("from AmMessagePermit");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (amMessagePermit == null) {
            return getHibernateTemplate().find("from AmMessagePermit");
        } else {
            // filter on properties set in the amMessagePermit
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(amMessagePermit).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AmMessagePermit.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.AmMessagePermitDao#getAmMessagePermit(Long ampNo)
     */
    public AmMessagePermit getAmMessagePermit(final Long ampNo) {
        AmMessagePermit amMessagePermit = (AmMessagePermit) getHibernateTemplate().get(AmMessagePermit.class, ampNo);
        if (amMessagePermit == null) {
            log.warn("uh oh, amMessagePermit with ampNo '" + ampNo + "' not found...");
            throw new ObjectRetrievalFailureException(AmMessagePermit.class, ampNo);
        }

        return amMessagePermit;
    }

    /**
     * @see com.joymain.jecs.am.dao.AmMessagePermitDao#saveAmMessagePermit(AmMessagePermit amMessagePermit)
     */    
    public void saveAmMessagePermit(final AmMessagePermit amMessagePermit) {
        getHibernateTemplate().saveOrUpdate(amMessagePermit);
    }

    /**
     * @see com.joymain.jecs.am.dao.AmMessagePermitDao#removeAmMessagePermit(Long ampNo)
     */
    public void removeAmMessagePermit(final Long ampNo) {
        getHibernateTemplate().delete(getAmMessagePermit(ampNo));
    }
    //added for getAmMessagePermitsByCrm
    public List getAmMessagePermitsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from AmMessagePermit amMessagePermit where 1=1";

		String msgClassNo = crm.getString("msgClassNo","");
    	if(!StringUtil.isEmpty(msgClassNo)){
    		hql += " and amMessagePermit.msgClassNo='"+msgClassNo+"'";
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by ampNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getAmMessagePermitsByUserCode(String userCode) {
		return this.getHibernateTemplate().find("from AmMessagePermit where userCode='"+userCode+"'");
	}
}
