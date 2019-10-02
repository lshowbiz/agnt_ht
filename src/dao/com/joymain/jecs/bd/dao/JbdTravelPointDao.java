
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointDao extends Dao {

    /**
     * Retrieves all of the jbdTravelPoints
     */
    public List getJbdTravelPoints(JbdTravelPoint jbdTravelPoint);

    /**
     * Gets jbdTravelPoint's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the jbdTravelPoint's userCode
     * @return jbdTravelPoint populated jbdTravelPoint object
     */
    public JbdTravelPoint getJbdTravelPoint(final String userCode);

    /**
     * Saves a jbdTravelPoint's information
     * @param jbdTravelPoint the object to be saved
     */    
    public void saveJbdTravelPoint(JbdTravelPoint jbdTravelPoint);

    /**
     * Removes a jbdTravelPoint from the database by userCode
     * @param userCode the jbdTravelPoint's userCode
     */
    public void removeJbdTravelPoint(final String userCode);
    //added for getJbdTravelPointsByCrm
    public List getJbdTravelPointsByCrm(CommonRecord crm, Pager pager);
    
    public List getRecommendVip(String userCode,String startTime,String endTime);
}

