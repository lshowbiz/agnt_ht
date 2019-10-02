
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTaiwanTravelPoint;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTaiwanTravelPointDao extends Dao {

    /**
     * Retrieves all of the jbdTaiwanTravelPoints
     */
    public List getJbdTaiwanTravelPoints(JbdTaiwanTravelPoint jbdTaiwanTravelPoint);

    /**
     * Gets jbdTaiwanTravelPoint's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdTaiwanTravelPoint's id
     * @return jbdTaiwanTravelPoint populated jbdTaiwanTravelPoint object
     */
    public JbdTaiwanTravelPoint getJbdTaiwanTravelPoint(final BigDecimal id);

    /**
     * Saves a jbdTaiwanTravelPoint's information
     * @param jbdTaiwanTravelPoint the object to be saved
     */    
    public void saveJbdTaiwanTravelPoint(JbdTaiwanTravelPoint jbdTaiwanTravelPoint);

    /**
     * Removes a jbdTaiwanTravelPoint from the database by id
     * @param id the jbdTaiwanTravelPoint's id
     */
    public void removeJbdTaiwanTravelPoint(final BigDecimal id);
    //added for getJbdTaiwanTravelPointsByCrm
    public List getJbdTaiwanTravelPointsByCrm(CommonRecord crm, Pager pager);
}

