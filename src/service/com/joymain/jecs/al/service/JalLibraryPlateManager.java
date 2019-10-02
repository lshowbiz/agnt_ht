
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.JalLibraryPlate;
import com.joymain.jecs.al.dao.JalLibraryPlateDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JalLibraryPlateManager extends Manager {
    /**
     * Retrieves all of the jalLibraryPlates
     */
    public List getJalLibraryPlates(JalLibraryPlate jalLibraryPlate);

    /**
     * Gets jalLibraryPlate's information based on plateId.
     * @param plateId the jalLibraryPlate's plateId
     * @return jalLibraryPlate populated jalLibraryPlate object
     */
    public JalLibraryPlate getJalLibraryPlate(final String plateId);

    /**
     * Saves a jalLibraryPlate's information
     * @param jalLibraryPlate the object to be saved
     */
    public void saveJalLibraryPlate(JalLibraryPlate jalLibraryPlate);

    /**
     * Removes a jalLibraryPlate from the database by plateId
     * @param plateId the jalLibraryPlate's plateId
     */
    public void removeJalLibraryPlate(final String plateId);
    //added for getJalLibraryPlatesByCrm
    public List getJalLibraryPlatesByCrm(CommonRecord crm, Pager pager);
    
    public List getAllJalLibraryPlates();
}

