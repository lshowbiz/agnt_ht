
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdTravelPointDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdTravelPointDetailDao extends Dao {

    /**
     * Retrieves all of the jbdTravelPointDetails
     */
    public List getJbdTravelPointDetails(JbdTravelPointDetail jbdTravelPointDetail);

    /**
     * Gets jbdTravelPointDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdTravelPointDetail's id
     * @return jbdTravelPointDetail populated jbdTravelPointDetail object
     */
    public JbdTravelPointDetail getJbdTravelPointDetail(final Long id);

    /**
     * Saves a jbdTravelPointDetail's information
     * @param jbdTravelPointDetail the object to be saved
     */    
    public void saveJbdTravelPointDetail(JbdTravelPointDetail jbdTravelPointDetail);

    /**
     * Removes a jbdTravelPointDetail from the database by id
     * @param id the jbdTravelPointDetail's id
     */
    public void removeJbdTravelPointDetail(final Long id);
    //added for getJbdTravelPointDetailsByCrm
    public List getJbdTravelPointDetailsByCrm(CommonRecord crm, Pager pager);
}

