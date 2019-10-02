package com.joymain.jecs.al.dao;

import java.util.List;

import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlStateProvinceDao extends Dao {

	/**
	 * Retrieves all of the alStateProvinces
	 */
	public List getAlStateProvinces(AlStateProvince alStateProvince);

	/**
	 * Gets alStateProvince's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param stateProvinceId the alStateProvince's stateProvinceId
	 * @return alStateProvince populated alStateProvince object
	 */
	public AlStateProvince getAlStateProvince(final Long stateProvinceId);

	/**
	 * Saves a alStateProvince's information
	 * @param alStateProvince the object to be saved
	 */
	public void saveAlStateProvince(AlStateProvince alStateProvince);

	/**
	 * Removes a alStateProvince from the database by stateProvinceId
	 * @param stateProvinceId the alStateProvince's stateProvinceId
	 */
	public void removeAlStateProvince(final Long stateProvinceId);

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
}
