
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.JalLibraryPlate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JalLibraryPlateDao extends Dao {

    /**
     * Retrieves all of the jalLibraryPlates
     */
    public List getJalLibraryPlates(JalLibraryPlate jalLibraryPlate);

    /**
     * Gets jalLibraryPlate's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param plateId the jalLibraryPlate's plateId
     * @return jalLibraryPlate populated jalLibraryPlate object
     */
    public JalLibraryPlate getJalLibraryPlate(final Long plateId);

    /**
     * Saves a jalLibraryPlate's information
     * @param jalLibraryPlate the object to be saved
     */    
    public void saveJalLibraryPlate(JalLibraryPlate jalLibraryPlate);

    /**
     * Removes a jalLibraryPlate from the database by plateId
     * @param plateId the jalLibraryPlate's plateId
     */
    public void removeJalLibraryPlate(final Long plateId);
    //added for getJalLibraryPlatesByCrm
    public List getJalLibraryPlatesByCrm(CommonRecord crm, Pager pager);
    
    public List getAllJalLibraryPlates();
}

