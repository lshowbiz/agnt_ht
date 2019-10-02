
package com.joymain.jecs.sun.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sun.model.SunProductInfo;
import com.joymain.jecs.sun.dao.SunProductInfoDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface SunProductInfoManager extends Manager {
    /**
     * Retrieves all of the sunProductInfos
     */
    public List getSunProductInfos(SunProductInfo sunProductInfo);

    /**
     * Gets sunProductInfo's information based on jpiId.
     * @param jpiId the sunProductInfo's jpiId
     * @return sunProductInfo populated sunProductInfo object
     */
    public SunProductInfo getSunProductInfo(final String jpiId);

    /**
     * Saves a sunProductInfo's information
     * @param sunProductInfo the object to be saved
     */
    public void saveSunProductInfo(SunProductInfo sunProductInfo);

    /**
     * Removes a sunProductInfo from the database by jpiId
     * @param jpiId the sunProductInfo's jpiId
     */
    public void removeSunProductInfo(final String jpiId);
    //added for getSunProductInfosByCrm
    public List getSunProductInfosByCrm(CommonRecord crm, Pager pager);
    public void updateSunOrder(SunProductInfo sunProductInfo);
}

