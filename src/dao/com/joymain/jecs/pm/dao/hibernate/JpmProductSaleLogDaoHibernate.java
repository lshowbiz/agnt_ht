
package com.joymain.jecs.pm.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pm.model.JpmProductSaleLog;
import com.joymain.jecs.pm.dao.JpmProductSaleLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpmProductSaleLogDaoHibernate extends BaseDaoHibernate implements JpmProductSaleLogDao {

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleLogDao#getJpmProductSaleLogs(com.joymain.jecs.pm.model.JpmProductSaleLog)
     */
    public List getJpmProductSaleLogs(final JpmProductSaleLog jpmProductSaleLog) {
        return getHibernateTemplate().find("from JpmProductSaleLog");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpmProductSaleLog == null) {
            return getHibernateTemplate().find("from JpmProductSaleLog");
        } else {
            // filter on properties set in the jpmProductSaleLog
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpmProductSaleLog).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpmProductSaleLog.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleLogDao#getJpmProductSaleLog(Long uniNo)
     */
    public JpmProductSaleLog getJpmProductSaleLog(final Long uniNo) {
        JpmProductSaleLog jpmProductSaleLog = (JpmProductSaleLog) getHibernateTemplate().get(JpmProductSaleLog.class, uniNo);
        if (jpmProductSaleLog == null) {
            log.warn("uh oh, jpmProductSaleLog with uniNo '" + uniNo + "' not found...");
            throw new ObjectRetrievalFailureException(JpmProductSaleLog.class, uniNo);
        }

        return jpmProductSaleLog;
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleLogDao#saveJpmProductSaleLog(JpmProductSaleLog jpmProductSaleLog)
     */    
    public void saveJpmProductSaleLog(final JpmProductSaleLog jpmProductSaleLog) {
        getHibernateTemplate().saveOrUpdate(jpmProductSaleLog);
    }

    /**
     * @see com.joymain.jecs.pm.dao.JpmProductSaleLogDao#removeJpmProductSaleLog(Long uniNo)
     */
    public void removeJpmProductSaleLog(final Long uniNo) {
        getHibernateTemplate().delete(getJpmProductSaleLog(uniNo));
    }
    //added for getJpmProductSaleLogsByCrm
    public List getJpmProductSaleLogsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpmProductSaleLog jpmProductSaleLog where 1=1";
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
			hql += " order by uniNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
