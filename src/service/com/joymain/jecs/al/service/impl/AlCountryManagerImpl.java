package com.joymain.jecs.al.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.joymain.jecs.al.dao.AlCountryDao;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class AlCountryManagerImpl extends BaseManager implements AlCountryManager {
	private AlCountryDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setAlCountryDao(AlCountryDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCountryManager#getAlCountrys(com.joymain.jecs.al.model.AlCountry)
	 */
	public List getAlCountrys(final AlCountry alCountry) {
		return dao.getAlCountrys(alCountry);
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCountryManager#getAlCountry(String countryId)
	 */
	public AlCountry getAlCountry(final String countryId) {
		return dao.getAlCountry(new Long(countryId));
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCountryManager#saveAlCountry(AlCountry alCountry)
	 */
	public void saveAlCountry(AlCountry alCountry) {
		dao.saveAlCountry(alCountry);
	}

	/**
	 * @see com.joymain.jecs.al.service.AlCountryManager#removeAlCountry(String countryId)
	 */
	public void removeAlCountry(final String countryId) {
		dao.removeAlCountry(new Long(countryId));
	}

	/**
	 * 根据外部参数分页获取对应的国家列表
	 * 
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCountrysByPage(CommonRecord crm, Pager pager) {
		return dao.getAlCountrysByPage(crm, pager);
	}
	
	/**
	 * 根据国家编码获取对应的国家资料
	 * @param countryCode
	 * @return
	 */
	public AlCountry getAlCountryByCode(final String countryCode) {
		return dao.getAlCountryByCode(countryCode);
	}
	
	/**
	 * 获取分公司所管辖的国家/地区列表
	 * @param companyCode
	 * @return
	 */
	public List getAlCountrysByCompany(final String companyCode){
		return dao.getAlCountrysByCompany(companyCode);
	}
	
	/**
	 * 获取没有分公司管理的国家/地区列表
	 * @return
	 */
	public List getAlCountrysNoCompany(){
		return dao.getAlCountrysNoCompany();
	}
	
	/**
	 * 批量更新国家/地区所属的公司
	 * @param companyCode
	 * @param countryCodes
	 */
	public void saveAlCountryCompany(final String companyCode, final String[] countryCodes){
		dao.saveAlCountryCompany(companyCode, countryCodes);
	}
	public Set getJalStateProvincesByCompanyCode(String companyCode) {
		if(StringUtil.isEmpty(companyCode)){
			return new HashSet();
		}
		AlCountry jalCountry = (AlCountry) this.getAlCountrysByCompany(companyCode).get(0);
		AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = this.getAlCountryByCode(jalCountry.getCountryCode());
    	alCountry.getAlStateProvinces().iterator();
    	return alCountry.getAlStateProvinces();
	}
}
