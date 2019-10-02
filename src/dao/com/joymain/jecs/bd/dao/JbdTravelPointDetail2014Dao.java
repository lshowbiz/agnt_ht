
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointDetail2014;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointDetail2014Dao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointDetail2014s
     */
    public List getJbdTravelPointDetail2014s(JbdTravelPointDetail2014 jbdTravelPointDetail2014);

    /**
     * Gets jbdTravelPointDetail2014's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdTravelPointDetail2014's id
     * @return jbdTravelPointDetail2014 populated jbdTravelPointDetail2014 object
     */
    public JbdTravelPointDetail2014 getJbdTravelPointDetail2014(final Long id);

    /**
     * Saves a jbdTravelPointDetail2014's information
     * @param jbdTravelPointDetail2014 the object to be saved
     */    
    public void saveJbdTravelPointDetail2014(JbdTravelPointDetail2014 jbdTravelPointDetail2014);

    /**
     * Removes a jbdTravelPointDetail2014 from the database by id
     * @param id the jbdTravelPointDetail2014's id
     */
    public void removeJbdTravelPointDetail2014(final Long id);
    //added for getJbdTravelPointDetail2014sByCrm
    public List getJbdTravelPointDetail2014sByCrm(CommonRecord crm, Pager pager);
}

