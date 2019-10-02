package com.joymain.jecs.al.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.al.dao.AlStateProvinceDao;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlStateProvinceDaoHibernate extends BaseDaoHibernate implements AlStateProvinceDao {

	/**
	 * @see com.joymain.jecs.al.dao.AlStateProvinceDao#getAlStateProvinces(com.joymain.jecs.al.model.AlStateProvince)
	 */
	public List getAlStateProvinces(final AlStateProvince alStateProvince) {
		return getHibernateTemplate().find("from AlStateProvince a order by a.alCountry.countryCode,a.stateProvinceCode asc");
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlStateProvinceDao#getAlStateProvince(Long stateProvinceId)
	 */
	public AlStateProvince getAlStateProvince(final Long stateProvinceId) {
		AlStateProvince alStateProvince = (AlStateProvince) getHibernateTemplate().get(AlStateProvince.class, stateProvinceId);
//		if (alStateProvince == null) {
//			log.warn("uh oh, alStateProvince with stateProvinceId '" + stateProvinceId + "' not found...");
//			throw new ObjectRetrievalFailureException(AlStateProvince.class, stateProvinceId);
//		}

		return alStateProvince;
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlStateProvinceDao#saveAlStateProvince(AlStateProvince alStateProvince)
	 */
	public void saveAlStateProvince(final AlStateProvince alStateProvince) {
		getHibernateTemplate().saveOrUpdate(alStateProvince);
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlStateProvinceDao#removeAlStateProvince(Long stateProvinceId)
	 */
	public void removeAlStateProvince(final Long stateProvinceId) {
		getHibernateTemplate().delete(getAlStateProvince(stateProvinceId));
	}

	/**
	 * 根据外部参数分页获取对应的国家列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlStateProvincesByPage(CommonRecord crm, Pager pager) {
		String hqlQuery = "from AlStateProvince where 1=1";

		String countryId = crm.getString("countryId", "");
		if (!StringUtils.isEmpty(countryId)) {
			hqlQuery += " and alCountry.countryId='" + countryId + "'";
		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			// 缺省排序
			hqlQuery += " order by stateProvinceCode desc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List alStateProvinces = this.findObjectsByHqlQuery(hqlQuery, pager);

		return alStateProvinces;
	}

	/**
	 * 根据国家及省份编码获取对应的省份资料
	 * @param stateProvinceCode
	 * @return
	 */
	public AlStateProvince getAlStateProvinceByCode(final String countryId, final String stateProvinceCode) {
		return (AlStateProvince) this.getObjectByHqlQuery("from AlStateProvince where alCountry.countryId='" + countryId + "' and stateProvinceCode='" + stateProvinceCode + "'");
	}
	
	/**
	 * 根据国家获取对应的省/州列表
	 * @param countryCode
	 * @return
	 */
	public List getAlStateProvincesByCountry(final String countryCode){
		return this.getHibernateTemplate().find("from AlStateProvince where alCountry.countryCode=? order by stateProvinceCode",countryCode);
	}
}
