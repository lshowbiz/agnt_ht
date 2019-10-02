
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JamMsnTypeDao extends Dao {

    /**
     * Retrieves all of the jamMsnTypes
     */
    public List getJamMsnTypes(JamMsnType jamMsnType);

    /**
     * Gets jamMsnType's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param mtId the jamMsnType's mtId
     * @return jamMsnType populated jamMsnType object
     */
    public JamMsnType getJamMsnType(final Long mtId);

    /**
     * Saves a jamMsnType's information
     * @param jamMsnType the object to be saved
     */    
    public void saveJamMsnType(JamMsnType jamMsnType);

    /**
     * Removes a jamMsnType from the database by mtId
     * @param mtId the jamMsnType's mtId
     */
    public void removeJamMsnType(final Long mtId);
    //added for getJamMsnTypesByCrm
    public List getJamMsnTypesByCrm(CommonRecord crm, Pager pager);
}

