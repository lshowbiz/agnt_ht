
package com.joymain.jecs.bd.dao;

import java.util.List;

import com.joymain.jecs.bd.model.JbdDayCustRecommendKb;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdDayCustRecommendKbDao extends Dao {

    /**
     * Retrieves all of the jbdDayCustRecommendKbs
     */
    public List getJbdDayCustRecommendKbs(JbdDayCustRecommendKb jbdDayCustRecommendKb);

    /**
     * Gets jbdDayCustRecommendKb's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdDayCustRecommendKb's id
     * @return jbdDayCustRecommendKb populated jbdDayCustRecommendKb object
     */
    public JbdDayCustRecommendKb getJbdDayCustRecommendKb(final Long id);

    /**
     * Saves a jbdDayCustRecommendKb's information
     * @param jbdDayCustRecommendKb the object to be saved
     */    
    public void saveJbdDayCustRecommendKb(JbdDayCustRecommendKb jbdDayCustRecommendKb);

    /**
     * Removes a jbdDayCustRecommendKb from the database by id
     * @param id the jbdDayCustRecommendKb's id
     */
    public void removeJbdDayCustRecommendKb(final Long id);
    //added for getJbdDayCustRecommendKbsByCrm
    public List getJbdDayCustRecommendKbsByCrm(CommonRecord crm, Pager pager);
}

