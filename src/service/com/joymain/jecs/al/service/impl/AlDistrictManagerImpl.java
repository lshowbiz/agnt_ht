
package com.joymain.jecs.al.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.dao.AlDistrictDao;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class AlDistrictManagerImpl extends BaseManager implements AlDistrictManager {
    private AlDistrictDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlDistrictDao(AlDistrictDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlDistrictManager#getAlDistricts(com.joymain.jecs.al.model.AlDistrict)
     */
    public List getAlDistricts(final AlDistrict alDistrict) {
        return dao.getAlDistricts(alDistrict);
    }

    /**
     * @see com.joymain.jecs.al.service.AlDistrictManager#getAlDistrict(String districtId)
     */
    public AlDistrict getAlDistrict(final String districtId) {
        return dao.getAlDistrict(new Long(districtId));
    }

    /**
     * @see com.joymain.jecs.al.service.AlDistrictManager#saveAlDistrict(AlDistrict alDistrict)
     */
    public void saveAlDistrict(AlDistrict alDistrict) {
        dao.saveAlDistrict(alDistrict);
    }

    /**
     * @see com.joymain.jecs.al.service.AlDistrictManager#removeAlDistrict(String districtId)
     */
    public void removeAlDistrict(final String districtId) {
        dao.removeAlDistrict(new Long(districtId));
    }
    //added for getAlDistrictsByCrm
    public List getAlDistrictsByCrm(CommonRecord crm, Pager pager){
	return dao.getAlDistrictsByCrm(crm,pager);
    }

	public List getAlDistrictByCityCode(String cityCode) {
		return dao.getAlDistrictByCityCode(cityCode);
	}

	public List getAlDistrictByCityId(Long cityId) {
		return dao.getAlDistrictByCityId(cityId);
	}

	public Map getAlDistrictMap(String companyCode) {
		Map<String,String> map = new HashMap();
		List list = dao.getAlDistrictsByCountry(companyCode);
		for(int i=0;i<list.size();i++){
			AlDistrict alDistrict = (AlDistrict) list.get(i);
			map.put(alDistrict.getDistrictId().toString(), alDistrict.getDistrictName());
		}
		
		return map;
	}
}
