
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2013;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointDetail2013Dao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointDetail2013s
     */
    public List getJbdTravelPointDetail2013s(JbdTravelPointDetail2013 jbdTravelPointDetail2013);

    /**
     * Gets jbdTravelPointDetail2013's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdTravelPointDetail2013's id
     * @return jbdTravelPointDetail2013 populated jbdTravelPointDetail2013 object
     */
    public JbdTravelPointDetail2013 getJbdTravelPointDetail2013(final Long id);

    /**
     * Saves a jbdTravelPointDetail2013's information
     * @param jbdTravelPointDetail2013 the object to be saved
     */    
    public void saveJbdTravelPointDetail2013(JbdTravelPointDetail2013 jbdTravelPointDetail2013);

    /**
     * Removes a jbdTravelPointDetail2013 from the database by id
     * @param id the jbdTravelPointDetail2013's id
     */
    public void removeJbdTravelPointDetail2013(final Long id);
    //added for getJbdTravelPointDetail2013sByCrm
    public List getJbdTravelPointDetail2013sByCrm(CommonRecord crm, Pager pager);
}

