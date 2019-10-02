
package com.joymain.jecs.al.service;

import java.util.List;
import java.util.Set;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlCountryManager extends Manager {
    /**
     * Retrieves all of the alCountrys
     */
    public List getAlCountrys(AlCountry alCountry);

    /**
     * Gets alCountry's information based on countryId.
     * @param countryId the alCountry's countryId
     * @return alCountry populated alCountry object
     */
    public AlCountry getAlCountry(final String countryId);

    /**
     * Saves a alCountry's information
     * @param alCountry the object to be saved
     */
    public void saveAlCountry(AlCountry alCountry);

    /**
     * Removes a alCountry from the database by countryId
     * @param countryId the alCountry's countryId
     */
    public void removeAlCountry(final String countryId);
    
    /**
	 * 根据外部参数分页获取对应的国家列表
	 * 
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlCountrysByPage(CommonRecord crm, Pager pager) ;
	
	/**
	 * 根据国家编码获取对应的国家资料
	 * @param countryCode
	 * @return
	 */
	public AlCountry getAlCountryByCode(final String countryCode) ;
	
	/**
	 * 获取分公司所管辖的国家/地区列表
	 * @param companyCode
	 * @return
	 */
	public List getAlCountrysByCompany(final String companyCode);
	
	/**
	 * 获取没有分公司管理的国家/地区列表
	 * @return
	 */
	public List getAlCountrysNoCompany();
	
	/**
	 * 批量更新国家/地区所属的公司
	 * @param companyCode
	 * @param countryCodes
	 */
	public void saveAlCountryCompany(final String companyCode, final String[] countryCodes);
	public Set getJalStateProvincesByCompanyCode(String companyCode);
}

