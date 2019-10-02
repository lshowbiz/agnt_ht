
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.dao.JalTownDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JalTownManager extends Manager {
    /**
     * Retrieves all of the jalTowns
     */
    public List getJalTowns(JalTown jalTown);

    /**
     * Gets jalTown's information based on townId.
     * @param townId the jalTown's townId
     * @return jalTown populated jalTown object
     */
    public JalTown getJalTown(final String townId);

    /**
     * Saves a jalTown's information
     * @param jalTown the object to be saved
     */
    public void saveJalTown(JalTown jalTown);

    /**
     * Removes a jalTown from the database by townId
     * @param townId the jalTown's townId
     */
    public void removeJalTown(final String townId);
    //added for getJalTownsByCrm
    public List getJalTownsByCrm(CommonRecord crm, Pager pager);
    public List getJalTownByDistrictId(final Long districtId);
}

