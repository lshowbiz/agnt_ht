
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlCityDao extends Dao {

	public List getAlCitysByCountry(String companyCode);
    /**
     * Retrieves all of the alCitys
     */
    public List getAlCitys(AlCity alCity);

    /**
     * Gets alCity's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param cityId the alCity's cityId
     * @return alCity populated alCity object
     */
    public AlCity getAlCity(final Long cityId);

    /**
     * Saves a alCity's information
     * @param alCity the object to be saved
     */    
    public void saveAlCity(AlCity alCity);

    /**
     * Removes a alCity from the database by cityId
     * @param cityId the alCity's cityId
     */
    public void removeAlCity(final Long cityId);
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

