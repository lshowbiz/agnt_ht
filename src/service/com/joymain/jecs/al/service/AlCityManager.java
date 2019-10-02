
package com.joymain.jecs.al.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.dao.AlCityDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface AlCityManager extends Manager {
	
	public Map<String,String> getAlCityMap(String companyCode);
    /**
     * Retrieves all of the alCitys
     */
    public List getAlCitys(AlCity alCity);

    /**
     * Gets alCity's information based on cityId.
     * @param cityId the alCity's cityId
     * @return alCity populated alCity object
     */
    public AlCity getAlCity(final String cityId);

    /**
     * Saves a alCity's information
     * @param alCity the object to be saved
     */
    public void saveAlCity(AlCity alCity);

    /**
     * Removes a alCity from the database by cityId
     * @param cityId the alCity's cityId
     */
    public void removeAlCity(final String cityId);
    //added for getAlCitysByCrm
    public List getAlCitysByCrm(CommonRecord crm, Pager pager);
	/**
	 * 根据省获取城市
	 * @param countryCode
	 * @return
	 */
	public List getAlCityByStateProvinceCode(final String stateProvinceCode);
	public AlCity getAlCityByCityCode(String cityCode);
	public List getAlCityByProvinceId(final Long provinceId);
}

