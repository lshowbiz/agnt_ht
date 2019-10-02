
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JalTownDao extends Dao {

    /**
     * Retrieves all of the jalTowns
     */
    public List getJalTowns(JalTown jalTown);

    /**
     * Gets jalTown's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param townId the jalTown's townId
     * @return jalTown populated jalTown object
     */
    public JalTown getJalTown(final Long townId);

    /**
     * Saves a jalTown's information
     * @param jalTown the object to be saved
     */    
    public void saveJalTown(JalTown jalTown);

    /**
     * Removes a jalTown from the database by townId
     * @param townId the jalTown's townId
     */
    public void removeJalTown(final Long townId);
    //added for getJalTownsByCrm
    public List getJalTownsByCrm(CommonRecord crm, Pager pager);
    public List getJalTownByDistrictId(final Long districtId);
}

