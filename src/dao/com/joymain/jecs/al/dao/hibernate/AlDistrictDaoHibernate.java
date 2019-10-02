
package com.joymain.jecs.al.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.dao.AlDistrictDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class AlDistrictDaoHibernate extends BaseDaoHibernate implements AlDistrictDao {

    /**
     * @see com.joymain.jecs.al.dao.AlDistrictDao#getAlDistricts(com.joymain.jecs.al.model.AlDistrict)
     */
    public List getAlDistricts(final AlDistrict alDistrict) {
        return getHibernateTemplate().find("from AlDistrict");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (alDistrict == null) {
            return getHibernateTemplate().find("from AlDistrict");
        } else {
            // filter on properties set in the alDistrict
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(alDistrict).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AlDistrict.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.AlDistrictDao#getAlDistrict(Long districtId)
     */
    public AlDistrict getAlDistrict(final Long districtId) {
        AlDistrict alDistrict = (AlDistrict) getHibernateTemplate().get(AlDistrict.class, districtId);
//        if (alDistrict == null) {
//            log.warn("uh oh, alDistrict with districtId '" + districtId + "' not found...");
//            throw new ObjectRetrievalFailureException(AlDistrict.class, districtId);
//        }

        return alDistrict;
    }

    /**
     * @see com.joymain.jecs.al.dao.AlDistrictDao#saveAlDistrict(AlDistrict alDistrict)
     */    
    public void saveAlDistrict(final AlDistrict alDistrict) {
        getHibernateTemplate().saveOrUpdate(alDistrict);
    }

    /**
     * @see com.joymain.jecs.al.dao.AlDistrictDao#removeAlDistrict(Long districtId)
     */
    public void removeAlDistrict(final Long districtId) {
        getHibernateTemplate().delete(getAlDistrict(districtId));
    }
    //added for getAlDistrictsByCrm
    public List getAlDistrictsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from AlDistrict alDistrict where 1=1";
    	String cityId = crm.getString("cityId");
    	if(StringUtils.isNotEmpty(cityId)){
    		hql += " and alDistrict.alCity.cityId='"+cityId+"'";
    	}
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by districtId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getAlDistrictByCityCode(String cityCode) {
		return this.getHibernateTemplate().find("from AlDistrict where alCity.cityCode=? order by districtCode",cityCode);
	}

	public List getAlDistrictByCityId(Long cityId) {
		if(cityId==null){
			return new ArrayList();
		}
		return this.getHibernateTemplate().find("from AlDistrict where alCity.cityId=? order by districtCode",cityId);
	} 
	public List getAlDistrictsByCountry(String companyCode) {
    	String hql = "from AlDistrict alDistrict where alDistrict.alCity.alStateProvince.alCountry.countryCode='"+companyCode+"'";
		return this.getHibernateTemplate().find(hql);
	}
}
