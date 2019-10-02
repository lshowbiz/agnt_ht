
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointLog2015;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointLog2015Dao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointLog2015s
     */
    public List getJbdTravelPointLog2015s(JbdTravelPointLog2015 jbdTravelPointLog2015);

    /**
     * Gets jbdTravelPointLog2015's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jbdTravelPointLog2015's logId
     * @return jbdTravelPointLog2015 populated jbdTravelPointLog2015 object
     */
    public JbdTravelPointLog2015 getJbdTravelPointLog2015(final Long logId);

    /**
     * Saves a jbdTravelPointLog2015's information
     * @param jbdTravelPointLog2015 the object to be saved
     */    
    public void saveJbdTravelPointLog2015(JbdTravelPointLog2015 jbdTravelPointLog2015);

    /**
     * Removes a jbdTravelPointLog2015 from the database by logId
     * @param logId the jbdTravelPointLog2015's logId
     */
    public void removeJbdTravelPointLog2015(final Long logId);
    //added for getJbdTravelPointLog2015sByCrm
    public List getJbdTravelPointLog2015sByCrm(CommonRecord crm, Pager pager);
}

