
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointLogAll;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointLogAllDao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointLogAlls
     */
    public List getJbdTravelPointLogAlls(JbdTravelPointLogAll jbdTravelPointLogAll);

    /**
     * Gets jbdTravelPointLogAll's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param logId the jbdTravelPointLogAll's logId
     * @return jbdTravelPointLogAll populated jbdTravelPointLogAll object
     */
    public JbdTravelPointLogAll getJbdTravelPointLogAll(final Long logId);

    /**
     * Saves a jbdTravelPointLogAll's information
     * @param jbdTravelPointLogAll the object to be saved
     */    
    public void saveJbdTravelPointLogAll(JbdTravelPointLogAll jbdTravelPointLogAll);

    /**
     * Removes a jbdTravelPointLogAll from the database by logId
     * @param logId the jbdTravelPointLogAll's logId
     */
    public void removeJbdTravelPointLogAll(final Long logId);
    //added for getJbdTravelPointLogAllsByCrm
    public List getJbdTravelPointLogAllsByCrm(CommonRecord crm, Pager pager);
}

