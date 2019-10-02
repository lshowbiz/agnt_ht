
package com.joymain.jecs.al.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.dao.AlCityDao;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class AlCityManagerImpl extends BaseManager implements AlCityManager {
   

	private AlCityDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlCityDao(AlCityDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlCityManager#getAlCitys(com.joymain.jecs.al.model.AlCity)
     */
    public List getAlCitys(final AlCity alCity) {
        return dao.getAlCitys(alCity);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCityManager#getAlCity(String cityId)
     */
    public AlCity getAlCity(final String cityId) {
        return dao.getAlCity(new Long(cityId));
    }

    /**
     * @see com.joymain.jecs.al.service.AlCityManager#saveAlCity(AlCity alCity)
     */
    public void saveAlCity(AlCity alCity) {
        dao.saveAlCity(alCity);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCityManager#removeAlCity(String cityId)
     */
    public void removeAlCity(final String cityId) {
        dao.removeAlCity(new Long(cityId));
    }
    //added for getAlCitysByCrm
    public List getAlCitysByCrm(CommonRecord crm, Pager pager){
	return dao.getAlCitysByCrm(crm,pager);
    }

	public List getAlCityByStateProvinceCode(String stateProvinceCode) {
		return dao.getAlCityByStateProvinceCode(stateProvinceCode);
	}

	public AlCity getAlCityByCityCode(String cityCode) {
		return dao.getAlCityByCityCode(cityCode);
	}

	public List getAlCityByProvinceId(Long provinceId) {
		return dao.getAlCityByProvinceId(provinceId);
	}

	public Map<String,String> getAlCityMap(String companyCode) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap();
		List list = dao.getAlCitysByCountry(companyCode);
		for(int i=0;i<list.size();i++){
			AlCity alCity = (AlCity) list.get(i);
			map.put(alCity.getCityId().toString(), alCity.getCityName());
		}
		
		return map;
	}
}
