
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointLog2014Dao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointLog2014s
     */
    public List getJbdTravelPointLog2014s(JbdTravelPointLog2014 jbdTravelPointLog2014);

    /**
     * Gets jbdTravelPointLog2014's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jbdTravelPointLog2014's logId
     * @return jbdTravelPointLog2014 populated jbdTravelPointLog2014 object
     */
    public JbdTravelPointLog2014 getJbdTravelPointLog2014(final Long logId);

    /**
     * Saves a jbdTravelPointLog2014's information
     * @param jbdTravelPointLog2014 the object to be saved
     */    
    public void saveJbdTravelPointLog2014(JbdTravelPointLog2014 jbdTravelPointLog2014);

    /**
     * Removes a jbdTravelPointLog2014 from the database by logId
     * @param logId the jbdTravelPointLog2014's logId
     */
    public void removeJbdTravelPointLog2014(final Long logId);
    //added for getJbdTravelPointLog2014sByCrm
    public List getJbdTravelPointLog2014sByCrm(CommonRecord crm, Pager pager);

	public List getJbdTravelPointLogByPassStar(String userCode, String passStar);
}

