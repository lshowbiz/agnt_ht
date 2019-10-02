
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointLog2013Dao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointLog2013s
     */
    public List getJbdTravelPointLog2013s(JbdTravelPointLog2013 jbdTravelPointLog2013);

    /**
     * Gets jbdTravelPointLog2013's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jbdTravelPointLog2013's logId
     * @return jbdTravelPointLog2013 populated jbdTravelPointLog2013 object
     */
    public JbdTravelPointLog2013 getJbdTravelPointLog2013(final Long logId);

    /**
     * Saves a jbdTravelPointLog2013's information
     * @param jbdTravelPointLog2013 the object to be saved
     */    
    public void saveJbdTravelPointLog2013(JbdTravelPointLog2013 jbdTravelPointLog2013);

    /**
     * Removes a jbdTravelPointLog2013 from the database by logId
     * @param logId the jbdTravelPointLog2013's logId
     */
    public void removeJbdTravelPointLog2013(final Long logId);
    //added for getJbdTravelPointLog2013sByCrm
    public List getJbdTravelPointLog2013sByCrm(CommonRecord crm, Pager pager);
    
    public List getJbdTravelPointLogByPassStar(String userCode,String passStar);
}

