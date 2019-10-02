
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.JalLibraryColumn;
import com.joymain.jecs.al.dao.JalLibraryColumnDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JalLibraryColumnManager extends Manager {
    /**
     * Retrieves all of the jalLibraryColumns
     */
    public List getJalLibraryColumns(JalLibraryColumn jalLibraryColumn);

    /**
     * Gets jalLibraryColumn's information based on columnId.
     * @param columnId the jalLibraryColumn's columnId
     * @return jalLibraryColumn populated jalLibraryColumn object
     */
    public JalLibraryColumn getJalLibraryColumn(final String columnId);

    /**
     * Saves a jalLibraryColumn's information
     * @param jalLibraryColumn the object to be saved
     */
    public void saveJalLibraryColumn(JalLibraryColumn jalLibraryColumn);

    /**
     * Removes a jalLibraryColumn from the database by columnId
     * @param columnId the jalLibraryColumn's columnId
     */
    public void removeJalLibraryColumn(final String columnId);
    //added for getJalLibraryColumnsByCrm
    public List getJalLibraryColumnsByCrm(CommonRecord crm, Pager pager);
    
    public List getJalLibraryColumnsByPlateId(final String plateId);
}

