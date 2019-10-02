
package com.joymain.jecs.am.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.am.model.JamMsnDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JamMsnDetailDao extends Dao {

    /**
     * Retrieves all of the jamMsnDetails
     */
    public List getJamMsnDetails(JamMsnDetail jamMsnDetail);

    /**
     * Gets jamMsnDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param mdId the jamMsnDetail's mdId
     * @return jamMsnDetail populated jamMsnDetail object
     */
    public JamMsnDetail getJamMsnDetail(final Long mdId);

    /**
     * Saves a jamMsnDetail's information
     * @param jamMsnDetail the object to be saved
     */    
    public void saveJamMsnDetail(JamMsnDetail jamMsnDetail);

    /**
     * Removes a jamMsnDetail from the database by mdId
     * @param mdId the jamMsnDetail's mdId
     */
    public void removeJamMsnDetail(final Long mdId);
    //added for getJamMsnDetailsByCrm
    public List getJamMsnDetailsByCrm(CommonRecord crm, Pager pager);
    
    public List getJamMsnDetailsBySql(String userCode);
    public List getJamMsnDetailsBySql(String userCode,String msnKey);
 
    
}

