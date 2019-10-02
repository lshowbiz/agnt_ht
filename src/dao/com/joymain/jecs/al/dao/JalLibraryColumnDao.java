
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.JalLibraryColumn;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JalLibraryColumnDao extends Dao {

    /**
     * Retrieves all of the jalLibraryColumns
     */
    public List getJalLibraryColumns(JalLibraryColumn jalLibraryColumn);

    /**
     * Gets jalLibraryColumn's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param columnId the jalLibraryColumn's columnId
     * @return jalLibraryColumn populated jalLibraryColumn object
     */
    public JalLibraryColumn getJalLibraryColumn(final Long columnId);

    /**
     * Saves a jalLibraryColumn's information
     * @param jalLibraryColumn the object to be saved
     */    
    public void saveJalLibraryColumn(JalLibraryColumn jalLibraryColumn);

    /**
     * Removes a jalLibraryColumn from the database by columnId
     * @param columnId the jalLibraryColumn's columnId
     */
    public void removeJalLibraryColumn(final Long columnId);
    //added for getJalLibraryColumnsByCrm
    public List getJalLibraryColumnsByCrm(CommonRecord crm, Pager pager);
    
    public List getJalLibraryColumnsByPlateId(String plateId);
}

