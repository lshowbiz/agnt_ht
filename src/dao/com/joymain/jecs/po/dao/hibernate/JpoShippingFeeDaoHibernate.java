
package com.joymain.jecs.po.dao.hibernate;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.dao.JpoShippingFeeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoShippingFeeDaoHibernate extends BaseDaoHibernate implements JpoShippingFeeDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoShippingFeeDao#getJpoShippingFees(com.joymain.jecs.po.model.JpoShippingFee)
     */
    public List getJpoShippingFees(final JpoShippingFee jpoShippingFee) {
        return getHibernateTemplate().findByExample(jpoShippingFee);

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoShippingFee == null) {
            return getHibernateTemplate().find("from JpoShippingFee");
        } else {
            // filter on properties set in the jpoShippingFee
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoShippingFee).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoShippingFee.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoShippingFeeDao#getJpoShippingFee(Long jpsId)
     */
    public JpoShippingFee getJpoShippingFee(final Long jpsId) {
        JpoShippingFee jpoShippingFee = (JpoShippingFee) getHibernateTemplate().get(JpoShippingFee.class, jpsId);
        if (jpoShippingFee == null) {
            log.warn("uh oh, jpoShippingFee with jpsId '" + jpsId + "' not found...");
            throw new ObjectRetrievalFailureException(JpoShippingFee.class, jpsId);
        }

        return jpoShippingFee;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoShippingFeeDao#saveJpoShippingFee(JpoShippingFee jpoShippingFee)
     */    
    public void saveJpoShippingFee(final JpoShippingFee jpoShippingFee) {
        getHibernateTemplate().saveOrUpdate(jpoShippingFee);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoShippingFeeDao#removeJpoShippingFee(Long jpsId)
     */
    public void removeJpoShippingFee(final Long jpsId) {
        getHibernateTemplate().delete(getJpoShippingFee(jpsId));
    }
    //added for getJpoShippingFeesByCrm
    public List getJpoShippingFeesByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoShippingFee jpoShippingFee where 1=1";

		String countryCode = crm.getString("countryCode", "");
		if(StringUtils.isNotEmpty(countryCode)){
			hql += " and countryCode='" + countryCode + "'";
		}

		String jpsId = crm.getString("jpsId", "");
		if(StringUtils.isNotEmpty(jpsId)){
			hql += " and jpsId=" + jpsId + "";
		}

		String province = crm.getString("province", "");
		if(StringUtils.isNotEmpty(province)){
			hql += " and province='" + province + "'";
		}

		String city = crm.getString("city", "");
		if(StringUtils.isNotEmpty(city)){
			hql += " and city='" + city + "'";
		}

		String district = crm.getString("district", "");
		if(StringUtils.isNotEmpty(district)){
			hql += " and district='" + district + "'";
		}

		String shippingType = crm.getString("shippingType", "");
		if(StringUtils.isNotEmpty(shippingType)){
			hql += " and shippingType='" + shippingType + "'";
		}

		String shippingFee = crm.getString("shippingFee", "");
		if(StringUtils.isNotEmpty(shippingFee)){
			hql += " and shippingFee=" + shippingFee + "";
		}
		
		if(pager==null){
			return this.findObjectsByHqlQuery(hql);
		}

		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by jpsId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		System.out.println(hql);
		return this.findObjectsByHqlQuery(hql, pager);
    }
}
