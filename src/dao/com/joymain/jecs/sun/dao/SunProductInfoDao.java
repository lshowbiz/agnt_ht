
package com.joymain.jecs.sun.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sun.model.SunProductInfo;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SunProductInfoDao extends Dao {

    /**
     * Retrieves all of the sunProductInfos
     */
    public List getSunProductInfos(SunProductInfo sunProductInfo);

    /**
     * Gets sunProductInfo's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param jpiId the sunProductInfo's jpiId
     * @return sunProductInfo populated sunProductInfo object
     */
    public SunProductInfo getSunProductInfo(final Long jpiId);

    /**
     * Saves a sunProductInfo's information
     * @param sunProductInfo the object to be saved
     */    
    public void saveSunProductInfo(SunProductInfo sunProductInfo);

    /**
     * Removes a sunProductInfo from the database by jpiId
     * @param jpiId the sunProductInfo's jpiId
     */
    public void removeSunProductInfo(final Long jpiId);
    
    public void updateSunOrder(SunProductInfo sunProductInfo);
    //added for getSunProductInfosByCrm
    public List getSunProductInfosByCrm(CommonRecord crm, Pager pager);
}

