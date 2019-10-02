
package com.joymain.jecs.vt.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.vt.model.VtVotePow;
import com.joymain.jecs.vt.dao.VtVotePowDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface VtVotePowManager extends Manager {
    /**
     * Retrieves all of the vtVotePows
     */
    public List getVtVotePows(VtVotePow vtVotePow);

    /**
     * Gets vtVotePow's information based on vpId.
     * @param vpId the vtVotePow's vpId
     * @return vtVotePow populated vtVotePow object
     */
    public VtVotePow getVtVotePow(final String vpId);

    /**
     * Saves a vtVotePow's information
     * @param vtVotePow the object to be saved
     */
    public void saveVtVotePow(VtVotePow vtVotePow);

    /**
     * Removes a vtVotePow from the database by vpId
     * @param vpId the vtVotePow's vpId
     */
    public void removeVtVotePow(final String vpId);
    //added for getVtVotePowsByCrm
    public List getVtVotePowsByCrm(CommonRecord crm, Pager pager);
    public void removeVtVotePowsByVtId(Long vtId);
}

