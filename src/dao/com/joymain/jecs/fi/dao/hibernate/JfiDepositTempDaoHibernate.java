
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiDepositTemp;
import com.joymain.jecs.fi.dao.JfiDepositTempDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiDepositTempDaoHibernate extends BaseDaoHibernate implements JfiDepositTempDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositTempDao#getJfiDepositTemps(com.joymain.jecs.fi.model.JfiDepositTemp)
     */
    public List getJfiDepositTemps(final JfiDepositTemp jfiDepositTemp) {
        return getHibernateTemplate().find("from JfiDepositTemp");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiDepositTemp == null) {
            return getHibernateTemplate().find("from JfiDepositTemp");
        } else {
            // filter on properties set in the jfiDepositTemp
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiDepositTemp).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiDepositTemp.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositTempDao#getJfiDepositTemp(BigDecimal tempId)
     */
    public JfiDepositTemp getJfiDepositTemp(final Long tempId) {
        JfiDepositTemp jfiDepositTemp = (JfiDepositTemp) getHibernateTemplate().get(JfiDepositTemp.class, tempId);
        if (jfiDepositTemp == null) {
            log.warn("uh oh, jfiDepositTemp with tempId '" + tempId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiDepositTemp.class, tempId);
        }

        return jfiDepositTemp;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositTempDao#saveJfiDepositTemp(JfiDepositTemp jfiDepositTemp)
     */    
    public void saveJfiDepositTemp(final JfiDepositTemp jfiDepositTemp) {
        getHibernateTemplate().saveOrUpdate(jfiDepositTemp);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiDepositTempDao#removeJfiDepositTemp(BigDecimal tempId)
     */
    public void removeJfiDepositTemp(final Long tempId) {
        getHibernateTemplate().delete(getJfiDepositTemp(tempId));
    }
    //added for getJfiDepositTempsByCrm
    public List getJfiDepositTempsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiDepositTemp jfiDepositTemp where 1=1";
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
			hql += " order by tempId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
