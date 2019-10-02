
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2012;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointDetail2012Dao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointDetail2012s
     */
    public List getJbdTravelPointDetail2012s(JbdTravelPointDetail2012 jbdTravelPointDetail2012);

    /**
     * Gets jbdTravelPointDetail2012's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdTravelPointDetail2012's id
     * @return jbdTravelPointDetail2012 populated jbdTravelPointDetail2012 object
     */
    public JbdTravelPointDetail2012 getJbdTravelPointDetail2012(final Long id);

    /**
     * Saves a jbdTravelPointDetail2012's information
     * @param jbdTravelPointDetail2012 the object to be saved
     */    
    public void saveJbdTravelPointDetail2012(JbdTravelPointDetail2012 jbdTravelPointDetail2012);

    /**
     * Removes a jbdTravelPointDetail2012 from the database by id
     * @param id the jbdTravelPointDetail2012's id
     */
    public void removeJbdTravelPointDetail2012(final Long id);
    //added for getJbdTravelPointDetail2012sByCrm
    public List getJbdTravelPointDetail2012sByCrm(CommonRecord crm, Pager pager);
}

