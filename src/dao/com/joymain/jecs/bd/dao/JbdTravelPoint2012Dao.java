
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPoint2012;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPoint2012Dao extends Dao {

    /**
     * Retrieves all of the jbdTravelPoint2012s
     */
    public List getJbdTravelPoint2012s(JbdTravelPoint2012 jbdTravelPoint2012);

    /**
     * Gets jbdTravelPoint2012's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param userCode the jbdTravelPoint2012's userCode
     * @return jbdTravelPoint2012 populated jbdTravelPoint2012 object
     */
    public JbdTravelPoint2012 getJbdTravelPoint2012(final String userCode);

    /**
     * Saves a jbdTravelPoint2012's information
     * @param jbdTravelPoint2012 the object to be saved
     */    
    public void saveJbdTravelPoint2012(JbdTravelPoint2012 jbdTravelPoint2012);

    /**
     * Removes a jbdTravelPoint2012 from the database by userCode
     * @param userCode the jbdTravelPoint2012's userCode
     */
    public void removeJbdTravelPoint2012(final String userCode);
    //added for getJbdTravelPoint2012sByCrm
    public List getJbdTravelPoint2012sByCrm(CommonRecord crm, Pager pager);
}

