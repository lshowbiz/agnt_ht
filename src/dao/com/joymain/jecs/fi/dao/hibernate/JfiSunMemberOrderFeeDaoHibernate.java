
package com.joymain.jecs.fi.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.fi.model.JfiSunMemberOrderFee;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderFeeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JfiSunMemberOrderFeeDaoHibernate extends BaseDaoHibernate implements JfiSunMemberOrderFeeDao {

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderFeeDao#getJfiSunMemberOrderFees(com.joymain.jecs.fi.model.JfiSunMemberOrderFee)
     */
    public List getJfiSunMemberOrderFees(final JfiSunMemberOrderFee jfiSunMemberOrderFee) {
        return getHibernateTemplate().find("from JfiSunMemberOrderFee");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jfiSunMemberOrderFee == null) {
            return getHibernateTemplate().find("from JfiSunMemberOrderFee");
        } else {
            // filter on properties set in the jfiSunMemberOrderFee
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jfiSunMemberOrderFee).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JfiSunMemberOrderFee.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderFeeDao#getJfiSunMemberOrderFee(Long mofId)
     */
    public JfiSunMemberOrderFee getJfiSunMemberOrderFee(final Long mofId) {
        JfiSunMemberOrderFee jfiSunMemberOrderFee = (JfiSunMemberOrderFee) getHibernateTemplate().get(JfiSunMemberOrderFee.class, mofId);
        if (jfiSunMemberOrderFee == null) {
            log.warn("uh oh, jfiSunMemberOrderFee with mofId '" + mofId + "' not found...");
            throw new ObjectRetrievalFailureException(JfiSunMemberOrderFee.class, mofId);
        }

        return jfiSunMemberOrderFee;
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderFeeDao#saveJfiSunMemberOrderFee(JfiSunMemberOrderFee jfiSunMemberOrderFee)
     */    
    public void saveJfiSunMemberOrderFee(final JfiSunMemberOrderFee jfiSunMemberOrderFee) {
        getHibernateTemplate().saveOrUpdate(jfiSunMemberOrderFee);
    }

    /**
     * @see com.joymain.jecs.fi.dao.JfiSunMemberOrderFeeDao#removeJfiSunMemberOrderFee(Long mofId)
     */
    public void removeJfiSunMemberOrderFee(final Long mofId) {
        getHibernateTemplate().delete(getJfiSunMemberOrderFee(mofId));
    }
    //added for getJfiSunMemberOrderFeesByCrm
    public List getJfiSunMemberOrderFeesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JfiSunMemberOrderFee jfiSunMemberOrderFee where 1=1";
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
			hql += " order by mofId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
