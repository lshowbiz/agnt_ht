
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiBusinessNum;
import com.joymain.jecs.fi.dao.JfiBusinessNumDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiBusinessNumDaoHibernate extends BaseDaoHibernate implements JfiBusinessNumDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiBusinessNumDao#getJfiBusinessNums(com.joymain.jecs.fi.model.JfiBusinessNum)
     */
    public List getJfiBusinessNums(final JfiBusinessNum jfiBusinessNum) {
        return getHibernateTemplate().find("from JfiBusinessNum");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiBusinessNum == null) {
            return getHibernateTemplate().find("from JfiBusinessNum");
        } else {
            // filter on properties set in the jfiBusinessNum
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiBusinessNum).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiBusinessNum.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBusinessNumDao#getJfiBusinessNum(Long businessId)
     */
    public JfiBusinessNum getJfiBusinessNum(final Long businessId) {
        JfiBusinessNum jfiBusinessNum = (JfiBusinessNum) getHibernateTemplate().get(JfiBusinessNum.class, businessId);
        if (jfiBusinessNum == null) {
            log.warn("uh oh, jfiBusinessNum with businessId '" + businessId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiBusinessNum.class, businessId);
        }

        return jfiBusinessNum;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBusinessNumDao#saveJfiBusinessNum(JfiBusinessNum jfiBusinessNum)
     */    
    public void saveJfiBusinessNum(final JfiBusinessNum jfiBusinessNum) {
        getHibernateTemplate().saveOrUpdate(jfiBusinessNum);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiBusinessNumDao#removeJfiBusinessNum(Long businessId)
     */
    public void removeJfiBusinessNum(final Long businessId) {
        getHibernateTemplate().delete(getJfiBusinessNum(businessId));
    }
    //added for getJfiBusinessNumsByCrm
    public List getJfiBusinessNumsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiBusinessNum jfiBusinessNum where 1=1";
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
			hql += " order by businessId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
