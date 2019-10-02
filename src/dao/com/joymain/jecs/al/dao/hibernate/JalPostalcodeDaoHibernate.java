
package com.joymain.jecs.al.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.JalPostalcode;
import com.joymain.jecs.al.dao.JalPostalcodeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JalPostalcodeDaoHibernate extends BaseDaoHibernate implements JalPostalcodeDao {

    /**
     * @see com.joymain.jecs.al.dao.JalPostalcodeDao#getJalPostalcodes(com.joymain.jecs.al.model.JalPostalcode)
     */
    public List getJalPostalcodes(final JalPostalcode jalPostalcode) {
        return getHibernateTemplate().find("from JalPostalcode");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jalPostalcode == null) {
            return getHibernateTemplate().find("from JalPostalcode");
        } else {
            // filter on properties set in the jalPostalcode
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jalPostalcode).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JalPostalcode.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.JalPostalcodeDao#getJalPostalcode(Long postalcodeId)
     */
    public JalPostalcode getJalPostalcode(final Long postalcodeId) {
        JalPostalcode jalPostalcode = (JalPostalcode) getHibernateTemplate().get(JalPostalcode.class, postalcodeId);
        if (jalPostalcode == null) {
            log.warn("uh oh, jalPostalcode with postalcodeId '" + postalcodeId + "' not found...");
            throw new ObjectRetrievalFailureException(JalPostalcode.class, postalcodeId);
        }

        return jalPostalcode;
    }

    /**
     * @see com.joymain.jecs.al.dao.JalPostalcodeDao#saveJalPostalcode(JalPostalcode jalPostalcode)
     */    
    public void saveJalPostalcode(final JalPostalcode jalPostalcode) {
        getHibernateTemplate().saveOrUpdate(jalPostalcode);
    }

    /**
     * @see com.joymain.jecs.al.dao.JalPostalcodeDao#removeJalPostalcode(Long postalcodeId)
     */
    public void removeJalPostalcode(final Long postalcodeId) {
        getHibernateTemplate().delete(getJalPostalcode(postalcodeId));
    }
    //added for getJalPostalcodesByCrm
    public List getJalPostalcodesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JalPostalcode jalPostalcode where 1=1";
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
			hql += " order by postalcodeId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public JalPostalcode getJalPostalcodeByCode(String zipCode) {
		JalPostalcode JalPostalcode=(JalPostalcode) this.getObjectByHqlQuery("from JalPostalcode where postalcode='"+zipCode+"'");
		if(JalPostalcode!=null){
			JalPostalcode.getAlCity().getCityName();
			JalPostalcode.getAlCity().getAlStateProvince().getStateProvinceName();
		}
		return JalPostalcode;
	}
}
