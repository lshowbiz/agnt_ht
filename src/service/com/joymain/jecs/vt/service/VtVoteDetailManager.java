
package com.joymain.jecs.vt.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.vt.model.VtVoteDetail;
import com.joymain.jecs.vt.dao.VtVoteDetailDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface VtVoteDetailManager extends Manager {
    /**
     * Retrieves all of the vtVoteDetails
     */
    public List getVtVoteDetails(VtVoteDetail vtVoteDetail);

    /**
     * Gets vtVoteDetail's information based on vdId.
     * @param vdId the vtVoteDetail's vdId
     * @return vtVoteDetail populated vtVoteDetail object
     */
    public VtVoteDetail getVtVoteDetail(final String vdId);

    /**
     * Saves a vtVoteDetail's information
     * @param vtVoteDetail the object to be saved
     */
    public void saveVtVoteDetail(VtVoteDetail vtVoteDetail);

    /**
     * Removes a vtVoteDetail from the database by vdId
     * @param vdId the vtVoteDetail's vdId
     */
    public void removeVtVoteDetail(final String vdId);
    //added for getVtVoteDetailsByCrm
    public List getVtVoteDetailsByCrm(CommonRecord crm, Pager pager);
	public void removeVtVoteDetailByVtId(Long vtId);
}

