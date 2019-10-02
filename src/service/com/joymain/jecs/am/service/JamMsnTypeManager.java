
package com.joymain.jecs.am.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.dao.JamMsnTypeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JamMsnTypeManager extends Manager {
    /**
     * Retrieves all of the jamMsnTypes
     */
    public List getJamMsnTypes(JamMsnType jamMsnType);

    /**
     * Gets jamMsnType's information based on mtId.
     * @param mtId the jamMsnType's mtId
     * @return jamMsnType populated jamMsnType object
     */
    public JamMsnType getJamMsnType(final String mtId);

    /**
     * Saves a jamMsnType's information
     * @param jamMsnType the object to be saved
     */
    public void saveJamMsnType(JamMsnType jamMsnType);

    /**
     * Removes a jamMsnType from the database by mtId
     * @param mtId the jamMsnType's mtId
     */
    public void removeJamMsnType(final String mtId);
    //added for getJamMsnTypesByCrm
    public List getJamMsnTypesByCrm(CommonRecord crm, Pager pager);
}

