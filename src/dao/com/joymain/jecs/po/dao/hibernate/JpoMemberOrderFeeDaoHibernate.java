
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.dao.JpoMemberOrderFeeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoMemberOrderFeeDaoHibernate extends BaseDaoHibernate implements JpoMemberOrderFeeDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderFeeDao#getJpoMemberOrderFees(com.joymain.jecs.po.model.JpoMemberOrderFee)
     */
    public List getJpoMemberOrderFees(final JpoMemberOrderFee jpoMemberOrderFee) {
        return getHibernateTemplate().find("from JpoMemberOrderFee");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoMemberOrderFee == null) {
            return getHibernateTemplate().find("from JpoMemberOrderFee");
        } else {
            // filter on properties set in the jpoMemberOrderFee
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoMemberOrderFee).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoMemberOrderFee.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderFeeDao#getJpoMemberOrderFee(Long mofId)
     */
    public JpoMemberOrderFee getJpoMemberOrderFee(final Long mofId) {
        JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) getHibernateTemplate().get(JpoMemberOrderFee.class, mofId);
        if (jpoMemberOrderFee == null) {
            log.warn("uh oh, jpoMemberOrderFee with mofId '" + mofId + "' not found...");
            throw new ObjectRetrievalFailureException(JpoMemberOrderFee.class, mofId);
        }

        return jpoMemberOrderFee;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderFeeDao#saveJpoMemberOrderFee(JpoMemberOrderFee jpoMemberOrderFee)
     */    
    public void saveJpoMemberOrderFee(final JpoMemberOrderFee jpoMemberOrderFee) {
        getHibernateTemplate().saveOrUpdate(jpoMemberOrderFee);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberOrderFeeDao#removeJpoMemberOrderFee(Long mofId)
     */
    public void removeJpoMemberOrderFee(final Long mofId) {
        getHibernateTemplate().delete(getJpoMemberOrderFee(mofId));
    }
    //added for getJpoMemberOrderFeesByCrm
    public List getJpoMemberOrderFeesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoMemberOrderFee jpoMemberOrderFee where 1=1";
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
