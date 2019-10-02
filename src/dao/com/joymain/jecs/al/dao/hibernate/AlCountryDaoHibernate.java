package com.joymain.jecs.al.dao.hibernate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.al.dao.AlCountryDao;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlCountryDaoHibernate extends BaseDaoHibernate implements AlCountryDao {

	/**
	 * @see com.joymain.jecs.al.dao.AlCountryDao#getAlCountrys(com.joymain.jecs.al.model.AlCountry)
	 */
	public List getAlCountrys(final AlCountry alCountry) {
		return getHibernateTemplate().find("from AlCountry order by countryCode");
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCountryDao#getAlCountry(Long countryId)
	 */
	public AlCountry getAlCountry(final Long countryId) {
		AlCountry alCountry = (AlCountry) getHibernateTemplate().get(AlCountry.class, countryId);
		if (alCountry == null) {
			log.warn("uh oh, alCountry with countryId '" + countryId + "' not found...");
			throw new ObjectRetrievalFailureException(AlCountry.class, countryId);
		}

		return alCountry;
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCountryDao#saveAlCountry(AlCountry
	 *      alCountry)
	 */
	public void saveAlCountry(final AlCountry alCountry) {
		getHibernateTemplate().saveOrUpdate(alCountry);
	}

	/**
	 * @see com.joymain.jecs.al.dao.AlCountryDao#removeAlCountry(Long
	 *      countryId)
	 */
	public void removeAlCountry(final Long countryId) {
		getHibernateTemplate().delete(getAlCountry(countryId));
	}

	/**
	 * 根据外部参数分页获取对应的国家列表
	 * 
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCountrysByPage(CommonRecord crm, Pager pager) {
		String hqlQuery = "from AlCountry where 1=1";

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			// 缺省排序
			hqlQuery += " order by countryCode asc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List alCountrys = this.findObjectsByHqlQuery(hqlQuery, pager);

		return alCountrys;
	}
	
	/**
	 * 根据国家编码获取对应的国家资料
	 * @param countryCode
	 * @return
	 */
	public AlCountry getAlCountryByCode(final String countryCode) {
		return (AlCountry)this.getObjectByHqlQuery("from AlCountry where countryCode='"+countryCode+"'");
	}
	
	/**
	 * 获取分公司所管辖的国家/地区列表
	 * @param companyCode
	 * @return
	 */
	public List getAlCountrysByCompany(final String companyCode){
		return this.getHibernateTemplate().find("from AlCountry where companyCode=? order by countryCode", companyCode);
	}
	
	/**
	 * 获取没有分公司管理的国家/地区列表
	 * @return
	 */
	public List getAlCountrysNoCompany(){
		return this.getHibernateTemplate().find("from AlCountry where companyCode is null order by countryCode");
	}
	
	/**
	 * 批量更新国家/地区所属的公司
	 * @param companyCode
	 * @param countryCodes
	 */
	public void saveAlCountryCompany(final String companyCode, final String[] countryCodes){
		String countrySql="";
		if(countryCodes!=null && countryCodes.length>0){
			for(int i=0;i<countryCodes.length;i++){
				if(i>0){
					countrySql+=",";
				}
				countrySql+="'"+countryCodes[i]+"'";
			}
			countrySql+=")";
		}
		
		//将以前属于此公司, 但现在并不在countryCodes范围内的地区的companyCode置为空
		String iniSql="update AlCountry set companyCode=null where companyCode='"+companyCode+"'";
		if(!StringUtils.isEmpty(countrySql)){
			iniSql+=" and countryCode not in ("+countrySql+")";
		}
		this.executeUpdate(iniSql);
		//更新在countryCodes范围内的地区的公司
		if(!StringUtils.isEmpty(countrySql)){
			this.executeUpdate("update AlCountry set companyCode='"+companyCode+"' where countryCode in ("+countrySql+")");
		}
	}
}
