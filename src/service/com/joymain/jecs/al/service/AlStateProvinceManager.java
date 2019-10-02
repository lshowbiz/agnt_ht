package com.joymain.jecs.al.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlStateProvinceManager extends Manager {
	/**
	 * Retrieves all of the alStateProvinces
	 */
	public List getAlStateProvinces(AlStateProvince alStateProvince);

	/**
	 * Gets alStateProvince's information based on stateProvinceId.
	 * @param stateProvinceId the alStateProvince's stateProvinceId
	 * @return alStateProvince populated alStateProvince object
	 */
	public AlStateProvince getAlStateProvince(final String stateProvinceId);

	/**
	 * Saves a alStateProvince's information
	 * @param alStateProvince the object to be saved
	 */
	public void saveAlStateProvince(AlStateProvince alStateProvince);

	/**
	 * Removes a alStateProvince from the database by stateProvinceId
	 * @param stateProvinceId the alStateProvince's stateProvinceId
	 */
	public void removeAlStateProvince(final String stateProvinceId);

	/**
	 * 根据外部参数分页获取对应的国家列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlStateProvincesByPage(CommonRecord crm, Pager pager);

	/**
	 * 根据国家及省份编码获取对应的省份资料
	 * @param stateProvinceCode
	 * @return
	 */
	public AlStateProvince getAlStateProvinceByCode(final String countryId, final String stateProvinceCode);
	
	/**
	 * 根据国家获取对应的省/州列表
	 * @param countryCode
	 * @return
	 */
	public List getAlStateProvincesByCountry(final String countryCode);
	
	/**
	 * 根据国家获取对应的省/州Map<code,name>
	 * @param countryCode
	 */
	public Map getAlStateProvincesMapByCountry(final String countryCode);
	
	public Map getIdByCodeMap(String countryCode);
	public Map getAlStateCodeMap(String countryCode);
}
