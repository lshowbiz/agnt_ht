
package com.joymain.jecs.al.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.dao.AlCityDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class AlCityDaoHibernate extends BaseDaoHibernate implements AlCityDao {

    public List getAlCitysByCountry(String companyCode) {
		// TODO Auto-generated method stub
    	String hql = "from AlCity alCity where alCity.alStateProvince.alCountry.countryCode='"+companyCode+"'";
    	
		return this.getHibernateTemplate().find(hql);
	}

	/**
     * @see com.joymain.jecs.al.dao.AlCityDao#getAlCitys(com.joymain.jecs.al.model.AlCity)
     */
    public List getAlCitys(final AlCity alCity) {
        return getHibernateTemplate().find("from AlCity");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (alCity == null) {
            return getHibernateTemplate().find("from AlCity");
        } else {
            // filter on properties set in the alCity
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(alCity).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AlCity.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCityDao#getAlCity(Long cityId)
     */
    public AlCity getAlCity(final Long cityId) {
        AlCity alCity = (AlCity) getHibernateTemplate().get(AlCity.class, cityId);
//        if (alCity == null) {
//            log.warn("uh oh, alCity with cityId '" + cityId + "' not found...");
//            throw new ObjectRetrievalFailureException(AlCity.class, cityId);
//        }

        return alCity;
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCityDao#saveAlCity(AlCity alCity)
     */    
    public void saveAlCity(final AlCity alCity) {
        getHibernateTemplate().saveOrUpdate(alCity);
    }

    /**
     * @see com.joymain.jecs.al.dao.AlCityDao#removeAlCity(Long cityId)
     */
    public void removeAlCity(final Long cityId) {
        getHibernateTemplate().delete(getAlCity(cityId));
    }
    //added for getAlCitysByCrm
    public List getAlCitysByCrm(CommonRecord crm, Pager pager){
    	String hql = "from AlCity alCity where 1=1";
    	String stateProvinceId = crm.getString("stateProvinceId");
    	if(StringUtils.isNotEmpty(stateProvinceId)){
    		hql += " and alCity.alStateProvince.stateProvinceId='"+stateProvinceId+"'";
    	}

    	// 
    	if(pager == null){
    		return this.getHibernateTemplate().find(hql);
    	}
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by cityId desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

	public List getAlCityByStateProvinceCode(String stateProvinceCode) {
		return this.getHibernateTemplate().find("from AlCity where alStateProvince.stateProvinceCode=? order by cityCode",stateProvinceCode);
	}

	public AlCity getAlCityByCityCode(String cityCode) {
		return (AlCity) this.getObjectByHqlQuery(" from AlCity where cityCode='"+cityCode+"'");
	}

	public List getAlCityByProvinceId(Long provinceId) {
		if(provinceId==null){
			return new ArrayList();
		}
		return this.getHibernateTemplate().find("from AlCity where alStateProvince.stateProvinceId=? order by cityCode",provinceId);
	}

	
	
}
