package com.joymain.jecs.al.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.al.dao.AlStateProvinceDao;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class AlStateProvinceManagerImpl extends BaseManager implements AlStateProvinceManager {
	private AlStateProvinceDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setAlStateProvinceDao(AlStateProvinceDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.al.service.AlStateProvinceManager#getAlStateProvinces(com.joymain.jecs.al.model.AlStateProvince)
	 */
	public List getAlStateProvinces(final AlStateProvince alStateProvince) {
		return dao.getAlStateProvinces(alStateProvince);
	}

	/**
	 * @see com.joymain.jecs.al.service.AlStateProvinceManager#getAlStateProvince(String stateProvinceId)
	 */
	public AlStateProvince getAlStateProvince(final String stateProvinceId) {
		return dao.getAlStateProvince(new Long(stateProvinceId));
	}

	/**
	 * @see com.joymain.jecs.al.service.AlStateProvinceManager#saveAlStateProvince(AlStateProvince alStateProvince)
	 */
	public void saveAlStateProvince(AlStateProvince alStateProvince) {
		dao.saveAlStateProvince(alStateProvince);
	}

	/**
	 * @see com.joymain.jecs.al.service.AlStateProvinceManager#removeAlStateProvince(String stateProvinceId)
	 */
	public void removeAlStateProvince(final String stateProvinceId) {
		dao.removeAlStateProvince(new Long(stateProvinceId));
	}

	/**
	 * 根据外部参数分页获取对应的国家列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getAlStateProvincesByPage(CommonRecord crm, Pager pager) {
		return dao.getAlStateProvincesByPage(crm, pager);
	}

	/**
	 * 根据国家及省份编码获取对应的省份资料
	 * @param stateProvinceCode
	 * @return
	 */
	public AlStateProvince getAlStateProvinceByCode(final String countryId, final String stateProvinceCode) {
		return dao.getAlStateProvinceByCode(countryId, stateProvinceCode);
	}
	
	
	/**
	 * 根据国家获取对应的省/州列表
	 * @param countryCode
	 * @return
	 */
	public List getAlStateProvincesByCountry(final String countryCode){
		return dao.getAlStateProvincesByCountry(countryCode);
	}

	/**
	 * 根据国家获取对应的省/州Map<code,name>
	 * @param countryCode
	 */
	public Map getAlStateProvincesMapByCountry(String countryCode) {
	    List<AlStateProvince> alStateProvinces=this.getAlStateProvincesByCountry(countryCode);
	    Map<String, String> map=new LinkedHashMap<String, String>();
	    if(alStateProvinces!=null){
	    	for(AlStateProvince alStateProvince:alStateProvinces){
	    		map.put(alStateProvince.getStateProvinceId().toString(), alStateProvince.getStateProvinceName());
	    	}
	    }
	    return map;
    }
	
	public Map getAlStateCodeMap(String countryCode) {
		// TODO Auto-generated method stub
		 List<AlStateProvince> alStateProvinces=this.getAlStateProvincesByCountry(countryCode);
		    Map<String, String> map=new LinkedHashMap<String, String>();
		    if(alStateProvinces!=null){
		    	for(AlStateProvince alStateProvince:alStateProvinces){
		    		map.put(alStateProvince.getStateProvinceId().toString(), alStateProvince.getStateProvinceCode());
		    	}
		    }
		    return map;
	}

	public Map getIdByCodeMap(String countryCode) {
		// TODO Auto-generated method stub

		 List<AlStateProvince> alStateProvinces=this.getAlStateProvincesByCountry(countryCode);
		    Map<String, String> map=new LinkedHashMap<String, String>();
		    if(alStateProvinces!=null){
		    	for(AlStateProvince alStateProvince:alStateProvinces){
		    		map.put(alStateProvince.getStateProvinceCode(),alStateProvince.getStateProvinceId().toString());
		    	}
		    }
		    return map;
	}
	
}