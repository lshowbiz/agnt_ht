
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface AlDistrictDao extends Dao {

    /**
     * Retrieves all of the alDistricts
     */
    public List getAlDistricts(AlDistrict alDistrict);

    /**
     * Gets alDistrict's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param districtId the alDistrict's districtId
     * @return alDistrict populated alDistrict object
     */
    public AlDistrict getAlDistrict(final Long districtId);

    /**
     * Saves a alDistrict's information
     * @param alDistrict the object to be saved
     */    
    public void saveAlDistrict(AlDistrict alDistrict);

    /**
     * Removes a alDistrict from the database by districtId
     * @param districtId the alDistrict's districtId
     */
    public void removeAlDistrict(final Long districtId);
    //added for getAlDistrictsByCrm
    public List getAlDistrictsByCrm(CommonRecord crm, Pager pager);
	/**
	 * 根据城市获取区
	 * @param 
	 * @return
	 */
    public List getAlDistrictByCityCode(final String cityCode);
    
    
    public List getAlDistrictByCityId(final Long cityId);
    public List getAlDistrictsByCountry(String companyCode);
}

